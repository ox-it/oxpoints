package uk.ac.ox.oucs.oxpoints.gaboto;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
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
import uk.ac.ox.oucs.oxpoints.gaboto.beans.Location;
import uk.ac.ox.oucs.oxpoints.gaboto.entities.Building;
import uk.ac.ox.oucs.oxpoints.gaboto.entities.Carpark;
import uk.ac.ox.oucs.oxpoints.gaboto.entities.College;
import uk.ac.ox.oucs.oxpoints.gaboto.entities.Department;
import uk.ac.ox.oucs.oxpoints.gaboto.entities.Division;
import uk.ac.ox.oucs.oxpoints.gaboto.entities.DrainCover;
import uk.ac.ox.oucs.oxpoints.gaboto.entities.Faculty;
import uk.ac.ox.oucs.oxpoints.gaboto.entities.Group;
import uk.ac.ox.oucs.oxpoints.gaboto.entities.Museum;
import uk.ac.ox.oucs.oxpoints.gaboto.entities.OxpEntity;
import uk.ac.ox.oucs.oxpoints.gaboto.entities.Room;
import uk.ac.ox.oucs.oxpoints.gaboto.entities.SubLibrary;
import uk.ac.ox.oucs.oxpoints.gaboto.entities.Unit;
import uk.ac.ox.oucs.oxpoints.gaboto.entities.Place;
import uk.ac.ox.oucs.oxpoints.gaboto.entities.Library;
import uk.ac.ox.oucs.oxpoints.gaboto.entities.WAP;

public class SeparatedTEIImporter {

	private Gaboto gaboto;

	public final static String XML_NS = "http://www.w3.org/XML/1998/namespace";
	private Logger logger = Logger.getLogger("uk.ac.ox.oucs.oxpoints.importer");
	
		
	private Map<String, SegmentedOxpEntity> oxpointsIdToEntityLookup = new HashMap<String, SegmentedOxpEntity>();
	
	public SeparatedTEIImporter(Gaboto gaboto) {
		this.gaboto = gaboto;
	}
	
	public void loadDirectory(File directory) {
		assert directory.isDirectory();
		
		for (File file : directory.listFiles()) {
			System.out.println("Loading " + file);
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
		
		SegmentedOxpEntity.constrainRelations(oxpointsIdToEntityLookup);
		
		for (SegmentedOxpEntity entity : oxpointsIdToEntityLookup.values()) {
			entity.addToGaboto(oxpointsIdToEntityLookup);
		}
	}
	
	public void loadFile(File file) throws IOException, SAXException, ParserConfigurationException {
		Vector<Element> elements = new Vector<Element>();
		Document document = XMLUtils.readInputFileIntoJAXPDoc(file);
		String oxpID, type;
		
		Element element = document.getDocumentElement();

		oxpID = element.getAttribute("oxpID");
		type = element.getAttribute("type");

		if (element.getTagName().equals("placeList")) {
			NodeList nodes = element.getChildNodes();
			for (int i = 0; i < nodes.getLength(); i++) {
				elements.add((Element) nodes.item(i));
			}
		} else {
			elements.add(element);
		}
		
		// The oxpID of the entity described by this file must match the filename
		assert (file.getName().equals(oxpID+".xml"));

		for (Element elem : elements) {
			parseDates(elem, TimeSpan.BIG_BANG, TimeSpan.DOOMS_DAY);
		}

		loadEntity(type, oxpID, elements);
	}
	
	public void loadEntity(String type, String oxpID, Vector<Element> elements) {
		loadEntity(type, oxpID, elements, TimeSpan.BIG_BANG, TimeSpan.DOOMS_DAY);
	}
	
	public void loadEntity(String type, String oxpID, Element element, TimeInstant lower, TimeInstant upper) {
		Vector<Element> elements = new Vector<Element>();
		elements.add(element);
		loadEntity(type, oxpID, elements, lower, upper);
	}
	
	public void loadEntity(String type, String oxpID, Vector<Element> elements, TimeInstant lower, TimeInstant upper) {
		if (elements.size() > 1)
			throw new AssertionError("We can't yet handle discontinuous entities.");
		
		SegmentedOxpEntity entity = new SegmentedOxpEntity(gaboto, type, oxpID, elements.get(0));
		oxpointsIdToEntityLookup.put(oxpID, entity);

		for (Element element : elements) {
			NodeList nodes = element.getChildNodes();
			for (int i = 0; i < nodes.getLength(); i++) {
				if (!(nodes.item(i) instanceof Element))
					continue;
				Element elem = (Element) nodes.item(i);
				String tagName = elem.getTagName();
				String elemType = elem.getAttribute("type");


				if (tagName.equals("placeName")) {
					if (entity.instanceOf(Unit.class) || entity.instanceOf(Place.class))
						entity.addProperty("setName", elem);

				} else if (tagName.equals("idno")) {
					if (elemType.equals("oucs") && entity.instanceOf(Unit.class))
						entity.addProperty("setOUCSCode", elem);
					else if (elemType.equals("obn") && entity.instanceOf(Place.class))
						entity.addProperty("setOBNCode", elem);
					else if (elemType.equals("olis") && entity.instanceOf(Library.class))
						entity.addProperty("setOLISCode", elem);

				} else if (tagName.equals("location")) {
					NodeList children = elem.getChildNodes();

					for (int k=0; k<children.getLength(); k++) {
						if (!(children.item(k) instanceof Element))
							continue;
						Element child = (Element) children.item(k);
						if (elemType.equals("point") && child.getTagName().equals("geo") && entity.instanceOf(Place.class)) {
							Location loc = new Location();
							loc.setPos(child.getTextContent());
							entity.addProperty("setLocation", loc, elem);
						} else if (elemType.equals("address") && child.getTagName().equals("address")) {
							if (entity.instanceOf(Place.class) || entity.instanceOf(Unit.class))
								entity.addProperty("setAddress", extractAddress(child), elem);
						}
					}

				}

				if (tagName.equals("relation")) {
					String relationMethod, relationType = elem.getAttribute("type");
					Class<? extends OxpEntity> relationClass;
					if (relationType.equals("occupies")) {
						relationMethod = "addOccupiedBuilding";
						relationClass = Building.class;
					} else
						return;
					entity.addRelation(
							relationMethod,
							elem.getAttribute("passive"),
							elem, relationClass
					);
				} else if (tagName.equals("place")) {
					loadChildEntity(elem, type);
					entity.addRelation(
							"setParent",
							elem.getAttribute("oxpID"),
							elem, Place.class, true
					);
				}
			}

		}		
	}
	
	private void loadChildEntity(Element elem, String parentType) {
		String type = elem.getAttribute("type");

		if (!((parentType.equals("building") && type.equals("room")) || (parentType.equals("library") && type.equals("sublibrary"))))
			return;
			
		loadEntity(
				type,
				elem.getAttribute("oxpID"),
				elem,
				new ImmutableTimeInstant(elem.getAttribute("inferredFrom")),
				new ImmutableTimeInstant(elem.getAttribute("inferredTo"))
		);
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
	
	public static void main(String[] args) {
		String filename = args[0];
		File directory = new File(filename);
		System.out.println(filename);
		if(! directory.exists())
			throw new RuntimeException("Argument one needs to be a file");

		//Gaboto gaboto = GabotoFactory.getPersistentGaboto();
		Gaboto gaboto = GabotoFactory.getEmptyInMemoryGaboto();
		SeparatedTEIImporter importer = new SeparatedTEIImporter(gaboto);
		importer.loadDirectory(directory);
		gaboto.persistToDisk("/home/alex/gaboto_data");
		System.out.println("done");
	}
	

}
