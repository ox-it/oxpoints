package uk.ac.ox.oucs.oxpoints.gaboto;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.Vector;
import java.util.logging.Logger;

import javax.xml.parsers.ParserConfigurationException;

import net.sf.gaboto.EntityAlreadyExistsException;
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
import uk.ac.ox.oucs.oxpoints.gaboto.beans.Location;
import uk.ac.ox.oucs.oxpoints.gaboto.entities.Building;
import uk.ac.ox.oucs.oxpoints.gaboto.entities.Image;
import uk.ac.ox.oucs.oxpoints.gaboto.entities.OxpEntity;
import uk.ac.ox.oucs.oxpoints.gaboto.entities.Place;
import uk.ac.ox.oucs.oxpoints.gaboto.entities.Website;

public class SeparatedTEIImporter {

	private Gaboto gaboto;
	private WarningHandler warningHandler;

	public final static String XML_NS = "http://www.w3.org/XML/1998/namespace";
	private Logger logger = Logger.getLogger("uk.ac.ox.oucs.oxpoints.importer");

	private Map<String,Website> websites = new HashMap<String,Website>();
	
		
	private Map<String, SegmentedOxpEntity> entitiesByURI = new HashMap<String, SegmentedOxpEntity>();
	
	public SeparatedTEIImporter(Gaboto gaboto) {
		this.gaboto = gaboto;
		this.warningHandler = new WarningHandler();
	}
	
	public void loadDirectory(File directory) {
		assert directory.isDirectory();
		
		for (File file : directory.listFiles()) {
			try {
				if (file.toString().endsWith(".xml"))
					loadFile(file);
			} catch (IOException e) {
				logger.warning("Could not read from file '" + file.getAbsolutePath() + "'");
			} catch (SAXException e) {
				logger.warning("SAX exception parsing '" + file.getAbsolutePath() + "'");
			} catch (ParserConfigurationException e) {
				throw new GabotoRuntimeException();
			}
		}
		
		SegmentedOxpEntity.constrainRelations(entitiesByURI, warningHandler);
		
		for (SegmentedOxpEntity entity : entitiesByURI.values()) {
			entity.addToGaboto(entitiesByURI);
		}
	}
	
	public void loadFile(File file) throws IOException, SAXException, ParserConfigurationException {
		warningHandler.setFilename(file.getName());
		Vector<Element> elements = new Vector<Element>();
		Document document = XMLUtils.readInputFileIntoJAXPDoc(file);
		String oxpID, uri;
		
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
			
		
		SegmentedOxpEntity entity = new SegmentedOxpEntity(gaboto, warningHandler, uri, elements.get(0), filename);
		entitiesByURI.put(uri, entity);

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
					elem.getElementsByTagName("desc");
					entity.addType(elem);
					
				} else if (tagName.equals("trait") && elem.getAttribute("type").equals("url")) {
					entity.addProperty("setHomepage", getWebsite(elem), from, to);
				} else if (tagName.equals("trait") && elem.getAttribute("type").equals("iturl")) {
					entity.addProperty("setItHomepage", getWebsite(elem), from, to);
				} else if (tagName.equals("trait") && elem.getAttribute("type").equals("liburl")) {
					entity.addProperty("setLibraryHomepage", getWebsite(elem), from, to);
				} else if (tagName.equals("trait") && elem.getAttribute("type").equals("weblearn")) {
					entity.addProperty("setWeblearn", getWebsite(elem), from, to);

				} else if (tagName.equals("placeName")) {
					entity.addProperty("setName", elem);

				} else if (tagName.equals("idno")) {
					if (elemType.equals("oucs"))
						entity.addProperty("setOUCSCode", elem);
					else if (elemType.equals("obn"))
						entity.addProperty("setOBNCode", elem);
					else if (elemType.equals("olis"))
						entity.addProperty("setOLISCode", elem);
					else if (elemType.equals("osm"))
						entity.addProperty("setOsmId", elem);

				} else if (tagName.equals("location")) {
					NodeList children = elem.getChildNodes();

					for (int k=0; k<children.getLength(); k++) {
						if (!(children.item(k) instanceof Element))
							continue;
						Element child = (Element) children.item(k);
						if (child.getTagName().equals("geo")) {
							Location loc = new Location();
							loc.setPos(child.getTextContent());
							entity.addProperty("setLocation", loc, elem);
						} else if (child.getTagName().equals("address")) {
							entity.addProperty("setAddress", extractAddress(child), elem);
						}
					}

				}
				if (tagName.equals("relation")) {
					String relationMethod, relationName = elem.getAttribute("name");
					Class<? extends OxpEntity> relationClass;
					if (relationName.equals("occupies")) {
						relationMethod = "addOccupiedBuilding";
						relationClass = Building.class;
					
						entity.addRelation(
								relationMethod,
								gaboto.getConfig().getNSData()+elem.getAttribute("passive").substring(1),
								elem, relationClass
						);
						
						if (elem.getAttribute("type").equals("geo primary")) {
							entity.addRelation(
									"setPrimaryPlace",
									gaboto.getConfig().getNSData()+elem.getAttribute("passive").substring(1),
									elem, Place.class
							);
						}
					}
				} else if (tagName.equals("place") || tagName.equals("org")) {
					loadChildEntity(elem, filename);
					entity.addRelation(
							"setParent",
							gaboto.getConfig().getNSData()+elem.getAttribute("oxpID"),
							elem, Place.class, true
					);
				} else if (tagName.equals("note")) {
					NodeList figures = element.getElementsByTagName("figure");
					for (int j=0; j < figures.getLength(); j++) {
						if (!(figures.item(j) instanceof Element))
							continue;
						String imageURI = loadImage((Element) figures.item(j), filename);
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
		String uri = "", width="", height="", description="", type="";
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
		} catch (IndexOutOfBoundsException e) {
			warningHandler.addWarning(filename, "'figure' element must have a 'graphic' child element.");
		}
		if (figure.hasAttribute("type"))
			type = figure.getAttribute("type");
		else
			type = "unknown";
		
		SegmentedOxpEntity image = new SegmentedOxpEntity(
				gaboto, warningHandler, uri, TimeSpan.BIG_BANG,
				TimeSpan.DOOMS_DAY, "figure", filename);
		
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
		image.addProperty("setDescription", description, TimeSpan.BIG_BANG, TimeSpan.DOOMS_DAY);
		image.addProperty("setDcType", type, TimeSpan.BIG_BANG, TimeSpan.DOOMS_DAY);

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

		String add = "";
		String postCode = "";
		Address address = new Address();

		NodeList addressChildren = elem.getChildNodes();
		for (int j = 0; j < addressChildren.getLength(); j++){
			if (! (addressChildren.item(j) instanceof Element))
				continue;

			Element addressPart = (Element) addressChildren.item(j);

			if (addressPart.getNodeName().equals("addrLine")) {
				if (add.length() > 0 )
					add += ", ";
				add += addressPart.getTextContent();
			} else if (addressPart.getNodeName().equals("postCode"))
				postCode += addressPart.getTextContent();
			else
				throw new RuntimeException("Unrecognized element:" + addressPart);
		}

		address.setStreetAddress(add);
		address.setPostCode(postCode);
		return address;
	}
	
	public WarningHandler getWarningHandler() {
		return warningHandler;
	}
	
	public Website getWebsite(Element elem) {
		NodeList ns = elem.getElementsByTagName("desc");
		ns = ((Element) ns.item(0)).getElementsByTagName("ptr");
		Element e = (Element) ns.item(0);
		String url = e.getAttribute("target");
		
		if (websites.containsKey(url)) {
			return websites.get(url);
		} else {
			Website website = new Website();
			website.setUri(url);
			websites.put(url, website);
			try {
				gaboto.add(website);
			} catch (EntityAlreadyExistsException e1) {
				throw new GabotoRuntimeException();
			}
			return website;
		}
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

