package uk.ac.ox.oucs.oxpoints.gaboto;

import java.io.File;
import java.io.IOException;
import java.util.Date;
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
import net.sf.gaboto.GabotoRuntimeException;
import net.sf.gaboto.time.TimeInstant;
import net.sf.gaboto.time.TimeSpan;
import net.sf.gaboto.util.XMLUtils;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import uk.ac.ox.oucs.oxpoints.gaboto.TEIImporter.ElementRuntimeException;
import uk.ac.ox.oucs.oxpoints.gaboto.beans.Address;
import uk.ac.ox.oucs.oxpoints.gaboto.beans.Location;
import uk.ac.ox.oucs.oxpoints.gaboto.entities.College;
import uk.ac.ox.oucs.oxpoints.gaboto.entities.Building;
import uk.ac.ox.oucs.oxpoints.gaboto.entities.Department;
import uk.ac.ox.oucs.oxpoints.gaboto.entities.OxpEntity;
import uk.ac.ox.oucs.oxpoints.gaboto.entities.Unit;
import uk.ac.ox.oucs.oxpoints.gaboto.entities.Place;
import uk.ac.ox.oucs.oxpoints.gaboto.entities.Library;

public class SeparatedTEIImporter {

	private Gaboto gaboto;

	public final static String XML_NS = "http://www.w3.org/XML/1998/namespace";
	private Logger logger = Logger.getLogger("uk.ac.ox.oucs.oxpoints.importer");
	
		
	private Set<OxpEntity> entities = new HashSet<OxpEntity>();
	private Map<String, OxpEntity> oxpointsIdToEntityLookup = new HashMap<String, OxpEntity>();
	
	private Set<Vector<String>> relations = new HashSet<Vector<String>>();
	
	public void loadDirectory(File directory) {
		assert directory.isDirectory();
		
		for (File file : directory.listFiles()) {
			try {
				loadFile(file);
			} catch (IOException e) {
				logger.warning("Could not read from file '" + file.getAbsolutePath() + "'");
			} catch (SAXException e) {
				logger.warning("SAX exception parsing '" + file.getAbsolutePath() + "'");
			} catch (ParserConfigurationException e) {
				throw new GabotoRuntimeException();
			}
		}
	}
	
	public void loadFile(File file) throws IOException, SAXException, ParserConfigurationException {
		Vector<Element> elements = new Vector<Element>();
		Element element = (Element) XMLUtils.readInputFileIntoJAXPDoc(file);
		String oxpID, type;

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
		
		if (type.equals("college")) {
			loadEntity(College.class, oxpID, elements);
		} else if (type.equals("department")) {
			loadEntity(Department.class, oxpID, elements);
		}
	}
	
	public void loadEntity(Class<? extends OxpEntity> entityClass, String oxpID, Vector<Element> elements) {
		loadEntity(entityClass, oxpID, elements, TimeSpan.BIG_BANG, TimeSpan.DOOMS_DAY);
	}
	
	public void loadEntity(Class<? extends OxpEntity> entityClass, String oxpID, Vector<Element> elements, TimeInstant lower, TimeInstant upper) {
		SortedSet<TimeInstant> instants = new TreeSet<TimeInstant>();
		NodeList nodes;
		Element elem;

		for (Element element : elements) {
			parseDates(element, instants, lower, upper);
		}
		
		OxpEntity[] entities = new OxpEntity[instants.size()];
		
		
		Map<String,Integer> instantOffsets= new HashMap<String,Integer>();
		
		int i = 0;
		for (TimeInstant instant : instants) {
			instantOffsets.put(instant.toString(), i);
			if (instant.equals(TimeSpan.DOOMS_DAY))
				break;
			
			try {
				entities[i] =  entityClass.newInstance();
			} catch (InstantiationException e) {
				throw new GabotoRuntimeException();
			} catch (IllegalAccessException e) {
				throw new GabotoRuntimeException();
			}
			
			this.entities.add(entities[i]);
			entities[i].setUri(gaboto.getConfig().getNSData() + oxpID);
			
			i++;
		}
		
		for (Element element : elements) {
			nodes = element.getChildNodes();
			for (i = 0; i < nodes.getLength(); i++) {
				if (!(nodes.item(i) instanceof Element))
					continue;
				elem = (Element) nodes.item(i);
				String tagName = elem.getTagName();
				OxpEntity entity = entities[i];
				String elemType = elem.getAttribute("type");
				
				for (int j=instantOffsets.get(elem.getAttribute("from")); j<instantOffsets.get(elem.getAttribute("to")); j++) {
					
					if (tagName.equals("placeName")) {
						if (entity instanceof Unit) 
							((Unit) entity).setName(elem.getTextContent());
						else if (entity instanceof Place)
							((Place) entity).setName(elem.getTextContent());
						
					} else if (tagName.equals("idno")) {
						if (elemType != null && elemType.equals("oucs") && entity instanceof Unit)
							((Unit) entity).setOUCSCode(elem.getTextContent());
						else if (elemType != null && elemType.equals("obn") && entity instanceof Place)
							((Place) entity).setOBNCode(elem.getTextContent());
						else if (elemType != null && elemType.equals("olis") && entity instanceof Library)
							((Library) entity).setOLISCode(elem.getTextContent());
						
					} else if (tagName.equals("location")) {
						
						Element child = (Element) elem.getFirstChild();
						if (elemType != null && elemType.equals("point") && child.getTagName().equals("geo") && entity instanceof Place) {
							Location loc = new Location();
							loc.setPos(child.getTextContent());
							((Place) entity).setLocation(loc);
						} else if (elemType != null && elemType.equals("address") && child.getTagName().equals("address")) {
							if (entity instanceof Place)
								((Place) entity).setAddress(extractAddress(child));
							if (entity instanceof Unit)
								((Unit) entity).setAddress(extractAddress(child));
						}
					}
				}
				
				if (tagName.equals("relation")) {
					Vector<String> relation = new Vector<String>();
					relation.add(elem.getAttribute("from"));
					relation.add(elem.getAttribute("to"));
					relation.add(oxpID);
					relation.add(elem.getAttribute("passive"));
					relation.add(elem.getAttribute("type"));
					relations.add(relation);
				}
			}
		}
		
		
	}
	
	public void parseDates(Element element, Set<TimeInstant> instants, TimeInstant lower, TimeInstant upper) {
		String from = element.getAttribute("from");
		String to = element.getAttribute("to");
		
		if (from != null) {
			lower = latest(new TimeInstant(from), lower);
			instants.add(lower);
			element.setAttribute("from", lower.toString());
		}
		if (to != null) {
			upper = earliest(new TimeInstant(from), upper);
			instants.add(upper);
			element.setAttribute("to", lower.toString());
		}
		
		NodeList nodes = element.getChildNodes();
		for (int i = 0; i < nodes.getLength(); i++)
			parseDates((Element) nodes.item(i), instants, lower, upper);
		
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
}
