package uk.ac.ox.oucs.oxpoints.gaboto;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.Vector;
import java.util.logging.Logger;

import javax.xml.parsers.ParserConfigurationException;

import net.sf.gaboto.Gaboto;
import net.sf.gaboto.GabotoFactory;
import net.sf.gaboto.GabotoRuntimeException;
import net.sf.gaboto.time.ImmutableTimeInstant;
import net.sf.gaboto.time.TimeInstant;
import net.sf.gaboto.time.TimeSpan;
import net.sf.gaboto.util.XMLUtils;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import uk.ac.ox.oucs.oxpoints.gaboto.beans.Address;
import uk.ac.ox.oucs.oxpoints.gaboto.beans.Fax;
import uk.ac.ox.oucs.oxpoints.gaboto.beans.OnlineAccount;
import uk.ac.ox.oucs.oxpoints.gaboto.beans.Tel;
import uk.ac.ox.oucs.oxpoints.gaboto.beans.Voice;
import uk.ac.ox.oucs.oxpoints.gaboto.entities.Measure;
import uk.ac.ox.oucs.oxpoints.gaboto.entities.Image;
import uk.ac.ox.oucs.oxpoints.gaboto.entities.OxpEntity;
import uk.ac.ox.oucs.oxpoints.gaboto.entities.Place;
import uk.ac.ox.oucs.oxpoints.gaboto.entities.SpatialThing;
import uk.ac.ox.oucs.oxpoints.gaboto.entities.Unit;

public class SeparatedTEIImporter {

	private Gaboto gaboto;
	private WarningHandler warningHandler;

	public final static String XML_NS = "http://www.w3.org/XML/1998/namespace";
	private Logger logger = Logger.getLogger("uk.ac.ox.oucs.oxpoints.importer");

	private Map<String, SegmentedOxpEntity> entitiesByURI = new HashMap<String, SegmentedOxpEntity>();
	
	public SeparatedTEIImporter(Gaboto gaboto) {
		this.gaboto = gaboto;
		this.warningHandler = new WarningHandler();
	}
	
	public void load(File path) {
		if (path.isDirectory())
			loadDirectory(path);
		else
			loadFile(path);
	}
	
	public void loadDirectory(File directory) {
		assert directory.isDirectory();
		
		for (File file : directory.listFiles()) {
			if (file.toString().endsWith(".xml"))
				loadFile(file);
		}
		
		SegmentedOxpEntity.constrainRelations(entitiesByURI, warningHandler);
		
		for (SegmentedOxpEntity entity : entitiesByURI.values()) {
			entity.addToGaboto(entitiesByURI);
		}
	}
	
	public void loadFile(File file) {
		warningHandler.setFilename(file.getName());
		Vector<Element> elements = new Vector<Element>();
		Document document = null;
		String oxpID, uri;
		
		try {
			document = XMLUtils.readInputFileIntoJAXPDoc(file);
	} catch (IOException e) {
		logger.warning("Could not read from file '" + file.getAbsolutePath() + "'");
		return;
	} catch (SAXException e) {
		logger.warning("SAX exception parsing '" + file.getAbsolutePath() + "'");
		return;
	} catch (ParserConfigurationException e) {
		throw new GabotoRuntimeException();
	}
		
		Element element = document.getDocumentElement();

		// Handle lists of figures
		if (element.getTagName().equals("list")) {
			NodeList figures = element.getElementsByTagName("figure");
			for (int i = 0; i < figures.getLength(); i++) {
				Element figure = (Element) figures.item(0);
				parseDates(figure, TimeSpan.BIG_BANG, TimeSpan.DOOMS_DAY);
				loadImage((Element) figure, file.getName());
			}
			return;
		}

		oxpID = element.getAttribute("oxpID");
		uri = gaboto.getConfig().getNSData() + oxpID;
		
		if (element.getTagName().equals("listPlace") || element.getTagName().equals("listOrg")) {
			NodeList nodes = element.getChildNodes();
			for (int i = 0; i < nodes.getLength(); i++) {
				if (!(nodes.item(i) instanceof Element))
					continue;
				Element e = (Element) nodes.item(i);

				elements.add(e);
			}
		} else {
			elements.add(element);
		}
		
		// The oxpID of the entity described by this file must match the filename
		if (!file.getName().equals(oxpID+".xml"))
			warningHandler.addWarning(file.getName(), "oxpID '"+oxpID+"' doesn't match filename ('"+file.getName()+"'.");
		
		for (Element elem : elements) {
			parseDates(elem, TimeSpan.BIG_BANG, TimeSpan.DOOMS_DAY);
		}

		loadEntity(uri, elements, file.getName());
	}
	
	public void loadEntity(String uri, Vector<Element> elements, String filename) {
		loadEntity(uri, elements, TimeSpan.BIG_BANG, TimeSpan.DOOMS_DAY, filename);
	}
	
	public void loadEntity(String uri, Element element, TimeInstant lower, TimeInstant upper, String filename) {
		Vector<Element> elements = new Vector<Element>();
		elements.add(element);
		loadEntity(uri, elements, lower, upper, filename);
	}
	
	public void loadEntity(String uri, Vector<Element> elements, TimeInstant lower, TimeInstant upper, String filename) {
		if (elements.size() > 1)
			throw new AssertionError("We can't yet handle discontinuous entities.");
		
		if (uri.equals("")) {
			warningHandler.addWarning("Entity has no oxpID");
			return;
		}
		
		if (entitiesByURI.containsKey(uri)) {
			warningHandler.addWarning(filename, "Entity "+uri+" described more than once.");
			return;
		}
			
		SegmentedOxpEntity entity = new SegmentedOxpEntity(gaboto, warningHandler, uri, elements.get(0), filename);
		entitiesByURI.put(uri, entity);

		for (String sameAs : elements.get(0).getAttribute("corresp").split(" "))
			if (sameAs.length() > 0) {
				entity.addProperty("addSameAs", sameAs, elements.get(0));
			}
		
		for (String subtype : elements.get(0).getAttribute("subtype").split(" "))
			if (subtype.length() > 0)
				entity.addProperty("addDcType", subtype, elements.get(0));

		if (elements.get(0).hasAttribute("type"))
			entity.addType(elements.get(0).getAttribute("type"), elements.get(0));

		if (elements.get(0).hasAttribute("floor"))
			try {
				entity.addProperty("setFloor", Float.valueOf(elements.get(0).getAttribute("floor")), elements.get(0));
			} catch (NumberFormatException e) {
				warningHandler.addWarning(filename, "floor attribute must be a number, not '"+elements.get(0).getAttribute("floor")+"'.");
			}

		for (Element element : elements) {
			NodeList nodes = element.getChildNodes();
			for (int i = 0; i < nodes.getLength(); i++) {
				if (!(nodes.item(i) instanceof Element))
					continue;
				Element elem = (Element) nodes.item(i);
				String tagName = elem.getTagName();
				String elemType = elem.getAttribute("type");

				TimeInstant from = new ImmutableTimeInstant(elem.getAttribute("inferredFrom"));
				TimeInstant to = new ImmutableTimeInstant(elem.getAttribute("inferredTo"));

				if (tagName.equals("trait") && elem.getAttribute("type").equals("type")) {
					entity.addType(elem);
					
				} else if (tagName.equals("trait") && elem.getAttribute("type").equals("subtype")) {
					try{
						Element subtype = (Element) elem.getElementsByTagName("desc").item(0);
						entity.addProperty("addDcType", subtype);
					} catch (Exception e) {
						warningHandler.addWarning(filename, "subtype trait malformed.");
					}
				
				} else if (tagName.equals("trait") && elem.getAttribute("type").equals("measures")) {
					Element desc = (Element) elem.getElementsByTagName("desc").item(0);
					entity.addProperty("setMeasures", "http://purl.org/meter/resource/" + desc.getTextContent(), elem);
				} else if (tagName.equals("trait") && elem.getAttribute("type").equals("url")) {
					entity.addProperty("setHomepage", getWebsite(elem, filename, true), from, to);
				} else if (tagName.equals("trait") && elem.getAttribute("type").equals("iturl")) {
					entity.addProperty("setItHomepage", getWebsite(elem, filename, false), from, to);
				} else if (tagName.equals("trait") && elem.getAttribute("type").equals("liburl")) {
					entity.addProperty("setLibraryHomepage", getWebsite(elem, filename, false), from, to);
				} else if (tagName.equals("trait") && elem.getAttribute("type").equals("weblearn")) {
					entity.addProperty("setWeblearn", getWebsite(elem, filename, false), from, to);
				} else if (tagName.equals("trait") && elem.getAttribute("type").equals("telephone")) {
					entity.addProperty("addTelephoneNumber", getTelephoneNumber(elem), from, to, Tel.class);
				} else if (tagName.equals("trait") && elem.getAttribute("type").equals("facsimile")) {
					entity.addProperty("addTelephoneNumber", getTelephoneNumber(elem), from, to, Tel.class);

				} else if (tagName.equals("placeName") || tagName.equals("orgName")) {
					Set<String> types = new HashSet<String>();
					for (String type : elem.getAttribute("type").split("\\s+"))
						if (type.length() > 0)
							types.add(type);
					if (types.isEmpty())
						types.add("preferred");
					if (types.contains("preferred")) {
						entity.addProperty("setName", elem);
						entity.addProperty("setPrefLabel", elem);
					}
					if (types.contains("sort"))
						entity.addProperty("setSortLabel", elem);
					if (types.contains("alternate"))
						entity.addProperty("addAltLabel", elem);
					if (types.contains("hidden"))
						entity.addProperty("addHiddenLabel", elem);
					
				} else if (tagName.equals("desc")) {
					entity.addProperty("setDescription", elem);

				} else if (tagName.equals("idno")) {
					if (elemType.equals("oucs"))
						entity.addProperty("setOUCSCode", elem);
					else if (elemType.equals("obn"))
						entity.addProperty("setOBNCode", elem);
					else if (elemType.equals("olis"))
						entity.addProperty("addOLISCode", elem);
					else if (elemType.equals("finance"))
						entity.addProperty("setFinanceCode", elem);
					else if (elemType.equals("metering"))
						entity.addProperty("setMeasureIdentifier", elem);
					else if (elemType.equals("osm")) {
						entity.addProperty("setOsmId", elem);
						entity.addProperty("addSameAs", "http://linkedgeodata.org/triplify/"+elem.getTextContent()+"#id", elem);
					} else if (elemType.equals("twitter")) {
						OnlineAccount account = new OnlineAccount();
						account.setAccountServiceHomepage("http://www.twitter.com/");
						account.setAccountName(elem.getTextContent());
						account.setAccountProfilePage("http://www.twitter.com/" + elem.getTextContent());
						entity.addProperty("addOnlineAccount", account, elem);
					}

				} else if (tagName.equals("location")) {
					NodeList children = elem.getChildNodes();

					for (int k=0; k<children.getLength(); k++) {
						if (!(children.item(k) instanceof Element))
							continue;
						Element child = (Element) children.item(k);
						if (child.getTagName().equals("geo")) {
							try {
								String[] coords = child.getTextContent().split(" ");
								entity.addProperty("setLongitude", Float.valueOf(coords[0]), child);
								entity.addProperty("setLatitude", Float.valueOf(coords[1]), child);
							} catch (NumberFormatException e) {
								warningHandler.addWarning(filename, "location element should contain two floats separated by a space.");
							} catch (IndexOutOfBoundsException e) {
								warningHandler.addWarning(filename, "location element should contain two floats separated by a space.");
							}
						} else if (child.getTagName().equals("address")) {
							entity.addProperty("setAddress", extractAddress(child), elem);
						}
					}

				}
				if (tagName.equals("relation")) {
					String relationMethod = "", relationName = elem.getAttribute("name");
					Boolean inverted = false;
					Class<? extends OxpEntity> relationClass = null;
					if (relationName.equals("occupies")) {
						relationMethod = "addOccupiedPlace";
						relationClass = Place.class;
					} else if (relationName.equals("primary")) {
						relationMethod = "setPrimaryPlace";
						relationClass = Place.class;
					} else if (relationName.equals("controls")) {
						relationMethod = "setParent";
						relationClass = Unit.class;
						inverted = true;
					} else if (relationName.equals("supplies")) {
						// Gaboto thinks it's so clever, dropping the 's'
						relationMethod = "addSupplie";
						relationClass = SpatialThing.class;
					} else if (relationName.equals("upstreamOf")) {
						relationMethod = "setDownstreamOf";
						relationClass = Measure.class;
						inverted = true;
					} else if (relationName.equals("contains")) {
						relationMethod = "setParent";
						relationClass = Place.class;
						inverted = true;
					}
					
					if (!relationMethod.equals("")) {
						entity.addRelation(
								relationMethod,
								gaboto.getConfig().getNSData()+elem.getAttribute("passive").substring(1),
								elem, relationClass, inverted
						);
						
						if (elem.getAttribute("type").equals("primary")) {
							entity.addRelation(
									"setPrimaryPlace",
									gaboto.getConfig().getNSData()+elem.getAttribute("passive").substring(1),
									elem, Place.class
							);
						}
					}
				} else if (tagName.equals("place") || tagName.equals("org") || tagName.equals("object")) {
					loadChildEntity(elem, filename);
					entity.addRelation(
							"setParent",
							gaboto.getConfig().getNSData()+elem.getAttribute("oxpID"),
							elem, tagName.equals("place") ? Place.class : Unit.class, true
					);
				} else if (tagName.equals("note")) {
					NodeList figures = element.getElementsByTagName("figure");
					for (int j=0; j < figures.getLength(); j++) {
						if (!(figures.item(j) instanceof Element))
							continue;
						String imageURI = loadImage((Element) figures.item(j), filename);
						if (((Element) figures.item(j)).getAttribute("type").equals("logo"))
							entity.addRelation("setLogo", imageURI, element, Image.class);
						else
							entity.addRelation("addImage", imageURI, element, Image.class);
					}
				}
			}

		}		
	}
	
	private void loadChildEntity(Element elem, String filename) {
		loadEntity(
				gaboto.getConfig().getNSData()+elem.getAttribute("oxpID"),
				elem,
				new ImmutableTimeInstant(elem.getAttribute("inferredFrom")),
				new ImmutableTimeInstant(elem.getAttribute("inferredTo")),
				filename
		);
	}

	private String loadImage(Element figure, String filename) {
		String uri = "", width="", height="", description=null;
		try {
			Element graphic = (Element) figure.getElementsByTagName("graphic").item(0);
			if (graphic.getAttribute("url").startsWith("http://") || graphic.getAttribute("url").startsWith("https://"))
				uri = graphic.getAttribute("url");
			else
				uri = gaboto.getConfig().getImagesPrefix()+graphic.getAttribute("url");
			width = graphic.getAttribute("width");
			height = graphic.getAttribute("height");
		} catch (IndexOutOfBoundsException e) {
			warningHandler.addWarning(filename, "'figure' element must have a 'graphic' child element.");
		}
		try {
			Element figDesc = (Element) figure.getElementsByTagName("figDesc").item(0);
			description = figDesc.getTextContent();
		} catch (NullPointerException e) {
			//warningHandler.addWarning(filename, "'figure' element must have a 'graphic' child element.");
		}
		
		SegmentedOxpEntity image = new SegmentedOxpEntity(
				gaboto, warningHandler, uri, TimeSpan.BIG_BANG,
				TimeSpan.DOOMS_DAY, "figure", filename);
		
		for (String dcType : figure.getAttribute("type").split(" "))
			if (dcType.length() > 0)
				image.addProperty("addDcType", dcType, TimeSpan.BIG_BANG, TimeSpan.DOOMS_DAY);
		
		for (String corresp : figure.getAttribute("corresp").split(" ")) {
			if (corresp.length() == 0)
				continue;
			else if (corresp.length() != 9 || !corresp.startsWith("#"))
				warningHandler.addWarning(filename, "Invalid corresp attribute for figure.");
			else
				image.addRelation("addImage", gaboto.getConfig().getNSData()+corresp.substring(1), TimeSpan.BIG_BANG, TimeSpan.DOOMS_DAY, Image.class, true);
		}
		image.addType("Image", TimeSpan.BIG_BANG, TimeSpan.DOOMS_DAY);
		image.addProperty("setWidth", width, TimeSpan.BIG_BANG, TimeSpan.DOOMS_DAY);
		image.addProperty("setHeight", height, TimeSpan.BIG_BANG, TimeSpan.DOOMS_DAY);
		if (description != null)
			image.addProperty("setDescription", description, TimeSpan.BIG_BANG, TimeSpan.DOOMS_DAY);
		
		entitiesByURI.put(uri, image);
		return uri;
	}

	public void parseDates(Element element, TimeInstant lower, TimeInstant upper) {
		String from = element.getAttribute("from");
		String to = element.getAttribute("to");
		
		if (!from.equals("")) {
			lower = latest(new ImmutableTimeInstant(from), lower);
		}
		if (!to.equals("")) {
			upper = earliest(new ImmutableTimeInstant(to), upper);
		}
		element.setAttribute("inferredFrom", lower.toString());
		element.setAttribute("inferredTo", upper.toString());
		
		NodeList nodes = element.getChildNodes();
		for (int i = 0; i < nodes.getLength(); i++)
			if (nodes.item(i) instanceof Element)
				parseDates((Element) nodes.item(i), lower, upper);
		
	}
	
	public static TimeInstant earliest(TimeInstant t1, TimeInstant t2) {
		if (t1.compareTo(t2) == -1)
			return t1;
		else
			return t2;
	}
	
	public static TimeInstant latest(TimeInstant t1, TimeInstant t2) {
		if (t1.compareTo(t2) == 1)
			return t1;
		else
			return t2;
	}
	
	private Address extractAddress(Element elem) {

		String postCode = "";
		Address address = new Address();
		LinkedList<String> addrLines = new LinkedList<String>();

		NodeList addressChildren = elem.getChildNodes();
		for (int j = 0; j < addressChildren.getLength(); j++){
			if (! (addressChildren.item(j) instanceof Element))
				continue;
			Element addressPart = (Element) addressChildren.item(j);

			if (addressPart.getNodeName().equals("addrLine"))
				addrLines.add(addressPart.getTextContent());
			else if (addressPart.getNodeName().equals("postCode"))
				postCode += addressPart.getTextContent();
			else
				throw new RuntimeException("Unrecognized element:" + addressPart);
		}
		
		if (addrLines.size() == 1) {
			address.setStreetAddress(addrLines.getFirst());
		} else if (addrLines.size() == 2) {
			address.setStreetAddress(addrLines.getFirst());
			address.setLocality(addrLines.getLast());
		} else if (addrLines.size() >= 3) {
			address.setExtendedAddress(addrLines.getFirst());
			String add = addrLines.get(1);
			for (int i = 2; i < addrLines.size() - 1; i++)
				add += ", " + addrLines.get(i);
			address.setStreetAddress(add);
			address.setLocality(addrLines.getLast());
		}

		address.setPostCode(postCode);
		return address;
	}
	
	public WarningHandler getWarningHandler() {
		return warningHandler;
	}
	
	public String getWebsite(Element elem, String filename, boolean requireUnique) {
		NodeList ns = elem.getElementsByTagName("desc");
		ns = ((Element) ns.item(0)).getElementsByTagName("ptr");
		Element e = (Element) ns.item(0);
		String url = e.getAttribute("target");
		
		return url;
	}
	
	public Tel getTelephoneNumber(Element elem) {
		NodeList ns = elem.getElementsByTagName("desc");
		String value = ((Element) ns.item(0)).getTextContent();
		
		Tel tel;
		if (elem.getAttribute("type").equals("telephone"))
			tel = new Voice();
		else
			tel = new Fax();
		
		tel.setValue(value);
		return tel;
		
	}
	
	public static void main(String[] args) {
		String filename = args[0];
		File directory = new File(filename);
		if(! directory.exists())
			throw new RuntimeException("Argument one needs to be a file");

		//Gaboto gaboto = GabotoFactory.getPersistentGaboto();
		Gaboto gaboto = GabotoFactory.getEmptyInMemoryGaboto();
		SeparatedTEIImporter importer = new SeparatedTEIImporter(gaboto);
		importer.loadDirectory(directory);
		
		WarningHandler warningHandler = importer.getWarningHandler();
		if (warningHandler.hasWarnings()) {
			warningHandler.printWarnings(System.err);
			System.exit(1);
		} else {
			gaboto.persistToDisk(args[1]);
			System.exit(0);
		}
	}

	public class WarningHandler {
		private TreeMap<String,Vector<String>> warnings = new TreeMap<String,Vector<String>>();
		private String filename;

		public void addWarning(String warning) {
			addWarning(filename, warning);
		}

		public void addWarning(String filename, String warning) {
			if (filename == null)
				filename = "";
			if (!warnings.containsKey(filename))
				warnings.put(filename,new Vector<String>());
			warnings.get(filename).add(warning);
		}

		public boolean hasWarnings() {
			return (warnings.size() > 0);
		}

		public void setFilename(String filename) {
			this.filename = filename;
		}

		public void printWarnings(PrintStream out) {
			for (String filename : warnings.keySet()) {
				if (filename.equals(""))
					continue;
				out.println("In file '"+filename+"':");
				for (String warning : warnings.get(filename))
					out.println("    "+warning);
			}
			if (warnings.containsKey("")) {
				out.println("Non-localised warnings:");
				for (String warning : warnings.get(""))
					out.println("    "+warning);					
			}
		}
	}

	public boolean hasWarnings() {
		return warningHandler.hasWarnings();
	}

}

