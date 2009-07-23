/**
 * Copyright 2009 University of Oxford
 *
 * Written by Arno Mittelbach for the Erewhon Project
 *
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without 
 * modification, are permitted provided that the following conditions are met:
 *
 *  - Redistributions of source code must retain the above copyright notice, 
 *    this list of conditions and the following disclaimer.
 *  - Redistributions in binary form must reproduce the above copyright notice, 
 *    this list of conditions and the following disclaimer in the documentation 
 *    and/or other materials provided with the distribution.
 *  - Neither the name of the University of Oxford nor the names of its 
 *    contributors may be used to endorse or promote products derived from this 
 *    software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" 
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE 
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE 
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE 
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR 
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF 
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS 
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN 
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) 
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE 
 * POSSIBILITY OF SUCH DAMAGE.
 */
package uk.ac.ox.oucs.oxpoints.gaboto;

import java.io.File;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.oucs.gaboto.GabotoConfiguration;
import org.oucs.gaboto.GabotoFactory;
import org.oucs.gaboto.GabotoRuntimeException;
import org.oucs.gaboto.model.Gaboto;
import org.oucs.gaboto.nodes.GabotoEntity;
import org.oucs.gaboto.timedim.TimeInstant;
import org.oucs.gaboto.timedim.TimeSpan;
import org.oucs.gaboto.util.XMLUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;

import uk.ac.ox.oucs.oxpoints.gaboto.beans.Address;
import uk.ac.ox.oucs.oxpoints.gaboto.beans.Location;
import uk.ac.ox.oucs.oxpoints.gaboto.entities.Building;
import uk.ac.ox.oucs.oxpoints.gaboto.entities.Carpark;
import uk.ac.ox.oucs.oxpoints.gaboto.entities.College;
import uk.ac.ox.oucs.oxpoints.gaboto.entities.Department;
import uk.ac.ox.oucs.oxpoints.gaboto.entities.Image;
import uk.ac.ox.oucs.oxpoints.gaboto.entities.Library;
import uk.ac.ox.oucs.oxpoints.gaboto.entities.Museum;
import uk.ac.ox.oucs.oxpoints.gaboto.entities.OxpEntity;
import uk.ac.ox.oucs.oxpoints.gaboto.entities.Room;
import uk.ac.ox.oucs.oxpoints.gaboto.entities.Unit;
import uk.ac.ox.oucs.oxpoints.gaboto.entities.Website;

/**
 * 
 * @author Arno Mittelbach
 *
 */
public class TEIImporter {
	/**
   * @since  6 July 2009
   *
   */
  public class ElementRuntimeException extends RuntimeException {

    /**
     * Constructor.
     * @param message
     */
    public ElementRuntimeException(String message) {
    }

    private static final long serialVersionUID = -6439480177218879551L;
    private String message;
    public ElementRuntimeException(Element el, String mess) {
      super();
      NamedNodeMap nnm = el.getAttributes();
      int len = nnm.getLength();
      String atts = "";
      for (int i = 0; i < len; i++ )
        atts += nnm.item(i) + ", ";
      
      this.message = mess + ": " + atts;  
     }
    /**
     * {@inheritDoc}
     * @see java.lang.Throwable#getMessage()
     */
    @Override
    public String getMessage() {
      return message;
    }

  }



  private Document document;
	private Gaboto gaboto;

	public final static String XML_NS = "http://www.w3.org/XML/1998/namespace";
	
	private Set<OxpEntity> entities = new HashSet<OxpEntity>();
	private Map<String, OxpEntity> entityLookup = new HashMap<String, OxpEntity>();
	
	
	public TEIImporter(Gaboto gaboto, File file) {
		try {
      this.document = XMLUtils.readInputFileIntoJAXPDoc(file);
    } catch (Exception e) {
      throw new GabotoRuntimeException(e);
    }
		this.gaboto = gaboto;
	}
	
	public void run(){
		NodeList listPlaces = document.getElementsByTagName("listPlace");
		
		// take care of entity creation
		for(int i = 0; i < listPlaces.getLength();i++){
			NodeList places = listPlaces.item(i).getChildNodes();
			for(int j = 0; j < places.getLength(); j++){
				if(places.item(j) instanceof Element)
					processElement((Element)places.item(j), false);
			}
		}
		// take care of relations
		for(int i = 0; i < listPlaces.getLength();i++){
			NodeList places = listPlaces.item(i).getChildNodes();
			for(int j = 0; j < places.getLength(); j++){
				if(places.item(j) instanceof Element)
					processElement((Element)places.item(j), true);
			}
		}
		
		// process figures
		NodeList figureList = document.getElementsByTagName("figure");
		for(int i = 0; i < figureList.getLength(); i++){
			if(! (figureList.item(i) instanceof Element))
				continue;
			processFigure((Element)figureList.item(i));
				
		}
		
		// add entities
		for(GabotoEntity e : entities){
			try {
				gaboto.add(e);
			} catch (org.oucs.gaboto.model.EntityAlreadyExistsException e1) {
				throw new RuntimeException(e.getUri() + " has already been added to the system."); 
			} 
		}
	}
	
	

	private void processElement(Element el, boolean relations) {
		// create object
		String name = el.getNodeName();
		if(name.equals("place") && !relations){
      String type = el.getAttribute("type");
      if (type == null) {
				throw new ElementRuntimeException(el, "No type defined for place");
			}
      if(type.equals("college") || type.equals("ex-college")){
        processUnit(el, new College());
      } else if(type.equals("unit")){
        processUnit(el, new Unit());
      } else if(type.equals("library")){
        processLibrary(el);
      } else if(type.equals("museum")){
        processUnit(el, new Museum());
      } else if(type.equals("department")){
        processUnit(el, new Department());
      } else if(type.equals("uas")){
        processUnit(el, new Department());
      } else if(type.equals("poi")){
        processUnit(el, new Unit());
      } else if(type.equals("building")){
        processBuilding(el);
      } else if(type.equals("carpark")){
        processCarpark(el);
      } else {
        throw new RuntimeException("Unknown place type: " + type);
      }

		} else if(name.equals("relation") && relations){
			String relName = el.getAttribute("name");
			try{
				if(relName.equals("occupies")){
					processOccupies(el);
				} else if(relName.equals("controls")){
					processControls(el);
				} else {
					throw new RuntimeException("Unknown relation: " + relName);
				}
			}catch(NullPointerException e){
				throw new RuntimeException("No name defined for relation", e);
			}
		}
	}
	

	private void processFigure(Element figureEl) {
		// try to find corresponding entity
		if(! figureEl.hasAttribute("corresp")){
			throw new RuntimeException("Ambiguous figure element");
		}
			
		String id = figureEl.getAttribute("corresp");
		
		try{
			OxpEntity entity = entityLookup.get(id.substring(1));
			
			// try to find a graphic element
			NodeList graphics = figureEl.getElementsByTagName("graphic");
			if(graphics.getLength() < 1){
				throw new RuntimeException("Empty figure element for: " + id);
			}
			
			Element graphic = (Element) graphics.item(0);
			
			Image img = new Image();
			img.setTimeSpan(entity.getTimeSpan());

			String uri = graphic.getAttribute("url");
			if (!uri.startsWith("http"))
			  uri = "http://www.oucs.ox.ac.uk/oxpoints/" + uri;
			img.setUri(uri);
			
			img.setWidth(graphic.getAttribute("width"));
			img.setHeight(graphic.getAttribute("height"));
			
			entity.addImage(img);
			
			// add figure
			entities.add(img);
			
		} catch(NullPointerException e){
			throw new RuntimeException("Could not load entity from id: " + id );
		}
	}
	
  private void processControls(Element relation){
    String activeID = relation.getAttribute("active");
    String passiveID = relation.getAttribute("passive");
    
    Unit active = (Unit) entityLookup.get(activeID.substring(1));
    if(active == null)
      throw new RuntimeException("Could not load active entity from id: " + activeID );
    Unit passive = (Unit) entityLookup.get(passiveID.substring(1));
    if(passive == null )
      throw new RuntimeException("Could not load passive entity from id: " + passiveID );
    passive.setSubsetOf(active);
  }
  
  
	private void processOccupies(Element relation){
		String type = relation.getAttribute("type");
		String activeID = relation.getAttribute("active");
		String passiveID = relation.getAttribute("passive");
		
    Unit u = (Unit) entityLookup.get(activeID.substring(1));
    if (u == null)
      throw new RuntimeException("Could not load entity from id: " + activeID);
    
    Building b = (Building) entityLookup.get(passiveID.substring(1));
    if (b == null)
      throw new RuntimeException("Could not load entity from id: " + passiveID);
	
    if(type.equals("geo primary")){
      u.setPrimaryPlace(b);
    } 
    // If this is not a primary, but it has no other 
    if(u.getPrimaryPlace() == null)
      u.setPrimaryPlace(b);
			
    u.addOccupiedBuilding(b);
	}
	

	private void processCarpark(Element el) {
		Carpark cp = new Carpark();

    String id = el.getAttribute("oxpID");
    if (id == null)
      throw new NullPointerException();
    cp.setUri(gaboto.getConfig().getNSData() + id);
		
    String code = el.getAttribute("oucsCode");
    cp.setOUCSCode(code);
    String obn = el.getAttribute("obnCode");
    cp.setOBNCode(obn);
    
		cp.setName(findName(el));
		cp.setLocation(findLocation(el));
		
		// label
		NodeList nl = el.getElementsByTagName("label");
		if(nl.getLength() > 0){
			Element label = (Element) nl.item(0);
			String labelContent = label.getTextContent();
			labelContent = labelContent.replaceAll("[a-zA-Z\\s]", "");
			try{
				Integer size = new Integer(Integer.parseInt(labelContent));
				cp.setCapacity(size);
			} catch(NumberFormatException e){
				throw new RuntimeException("Could not ascertain carpark size.");
			}
		}
		
		// add unit
		entities.add(cp);
		if(el.hasAttributeNS(XML_NS, "id"))
			entityLookup.put(el.getAttributeNS(XML_NS, "id"), cp);
	}
	
	/**
	 * 
	 * @param buildingEl
	 */
	private void processBuilding(Element buildingEl){
		getBuilding(buildingEl, null);
		
	}
	

	private void processLibrary(Element libraryEl) {
		Library lib = new Library();
		
		processUnit(libraryEl, lib);
		
		// olis code
		String code = libraryEl.getAttribute("olisCode");
		lib.setOLISCode(code);

		lib.setLibraryHomepage(findLibWebsite(libraryEl, lib.getTimeSpan()));
    if(lib.getOUCSCode() != null)
      entityLookup.put(lib.getOUCSCode(), lib);
	}
	
	/**
	 * Processes a unit and adds it to the dataset.
	 * 
	 * @param unitEl
	 */
	private void processUnit(Element unitEl, Unit unit) {
		_processUnit(unit, unitEl);
		
		// add unit
		entities.add(unit);
		
		
    if(unit.getOUCSCode() != null)
      entityLookup.put(unit.getOUCSCode(), unit);
	}
	
	
	
	/**
	 * do the actual processing work
	 * 
	 * @param unit
	 * @param unitEl
	 */
	private void _processUnit(Unit unit, Element unitEl){
    // get ID
    String id = unitEl.getAttribute("oxpID");
    if (id == null)
      throw new NullPointerException();
    unit.setUri(gaboto.getConfig().getNSData() + id);
    
    // get name
    unit.setName(findName(unitEl));
    
    // oucs code
    String code = unitEl.getAttribute("oucsCode");
    unit.setOUCSCode(code);
    
		// do we have a foundation date
		TimeSpan ts = null;
		TimeInstant start = null, end = null;
		NodeList events = unitEl.getChildNodes();
		for(int i = 0; i < events.getLength(); i++){
			if(events.item(i).getNodeName().equals("event")){
				if(! (events.item(i) instanceof Element))
					continue;
				
				Element event = (Element)events.item(i);
				
				// find out type
				if(event.hasAttribute("type") && event.getAttribute("type").equals("founded")){
					try{
						start = new TimeInstant(Integer.parseInt(event.getAttribute("when")), null, null);
					}catch(NumberFormatException e){
						throw new RuntimeException("Could not parse date: " + event.getAttribute("when")  + " for " + unit.getName() );
					}
				} else if(event.hasAttribute("type") && event.getAttribute("type").equals("ended")){
					try{
						end = new TimeInstant(Integer.parseInt(event.getAttribute("when")), null, null);
					}catch(NumberFormatException e){
						throw new RuntimeException("Could not parse date: " + event.getAttribute("when")  + " for " + unit.getName() );
					}
				}
			}
		}
		// have we found something
		if(null != start && null != end)
			ts = TimeSpan.createFromInstants(start, end);
		else if(null != start)
			ts = new TimeSpan(start.getStartYear(), start.getStartMonth(), start.getStartDay());
		
		
		// find Website
		unit.setHomepage(findHomepage(unitEl, ts));
		
		unit.setItHomepage(findITWebsite(unitEl, ts));
		
    unit.setWeblearn(findWeblearn(unitEl, ts));
		
		// get address
    unit.setAddress(findAddress(unitEl));
		
		// add buildings
		getBuildings(unit, unitEl, ts);
		
		unit.setTimeSpan(ts);
	}

	

	private Collection<Building> getBuildings(Unit unit, Element unitEl, TimeSpan ts) {
		Set<Building> buildings = new HashSet<Building>();
		
		NodeList places = unitEl.getElementsByTagName("place");
		for(int i = 0; i < places.getLength(); i++){
			if(! (places.item(i) instanceof Element))
				continue;
			
			Element place = (Element) places.item(i);
			
			// if building
			if(! place.hasAttribute("type") || ! place.getAttribute("type").equals("building"))
				continue;
			
			Building building = getBuilding(place, ts);
			
			// occupants
			unit.addOccupiedBuilding(building);
			
			// is it the primary building
			if(place.hasAttribute("subtype") && place.getAttribute("subtype").equals("primary"))
				unit.setPrimaryPlace(building);
			
			buildings.add(building);
		}
		
		return buildings;
	}

	private Room getRoom(Building building, Element roomEl) {
    Room room = new Room();
    String id = roomEl.getAttribute("oxpID");
    if (id == null)
      throw new NullPointerException();
    room.setUri(gaboto.getConfig().getNSData() + id);
    
    // get name
    room.setName(findName(roomEl));
    
    // oucs code
    String code = roomEl.getAttribute("oucsCode");
    room.setOUCSCode(code);
    
    String obn = roomEl.getAttribute("obnCode");
    room.setOBNCode(obn);

		
		// building
		room.setParent(building);
		
		// timespan 
		room.setTimeSpan(building.getTimeSpan());
		
		// name?
		room.setName(findName(roomEl));
		
		entities.add(room);
    if(room.getOUCSCode() != null)
      entityLookup.put(room.getOUCSCode(), room);
		
		return room;
	}

	private Building getBuilding(Element buildingEl, TimeSpan ts) {
		Building building = new Building();

    String id = buildingEl.getAttribute("oxpID");
    if (id == null)
      throw new NullPointerException();
    building.setUri(gaboto.getConfig().getNSData() + id);
		// get uri
		
    String code = buildingEl.getAttribute("oucsCode");
    building.setOUCSCode(code);
    
    String obn = buildingEl.getAttribute("obnCode");
    building.setOBNCode(obn);
    
    // time span
		building.setTimeSpan(ts);
		
		// get name
		building.setName(findName(buildingEl));
		
		// find Website
    building.setHomepage(findHomepage(buildingEl, building.getTimeSpan()));

		// location
		building.setLocation(findLocation(buildingEl));
		
		// rooms
		NodeList rooms = buildingEl.getElementsByTagName("place");
		for(int j = 0; j < rooms.getLength(); j++){
			if(! (rooms.item(j) instanceof Element))
				continue;
			
			Element roomEl = (Element) rooms.item(j);
			getRoom(building, roomEl);
		}	
		
		entities.add(building);
		
    if(building.getOUCSCode() != null)
      entityLookup.put(building.getOUCSCode(), building);
		return building;
	}

	private Website findHomepage(Element el, TimeSpan ts){
		return findWebsite(el, ts, "url");
	}
	
	private Website findITWebsite(Element el, TimeSpan ts){
		return findWebsite(el, ts, "iturl");
	}
	
	private Website findLibWebsite(Element el, TimeSpan ts){
		return findWebsite(el, ts, "liburl");
	}
	
	private Website findWeblearn(Element el, TimeSpan ts){
		return findWebsite(el, ts, "weblearn");
	}
	

	private Website findWebsite(Element el, TimeSpan ts, String type){
		NodeList traits = el.getChildNodes();
		for(int i = 0; i < traits.getLength(); i++){
			if(traits.item(i).getNodeName().equals("trait")){
				if(! (traits.item(i) instanceof Element))
					continue;
				
				Element trait = (Element) traits.item(i);
				if(!trait.hasAttribute("type") || !trait.getAttribute("type").equals(type))
					continue;
				
				// find ptr
				NodeList ptrs = trait.getElementsByTagName("ptr");
				if(ptrs.getLength() > 0){
					Website hp = new Website();
					String uri = ((Element)ptrs.item(0)).getAttribute("target");
          if (uri == null)
            throw new ElementRuntimeException(el, "URI Null");
          if (uri.trim().equals(""))
            throw new ElementRuntimeException(el, "URI empty");
					hp.setUri(uri);
					hp.setTimeSpan(ts);
					
					entities.add(hp);
					
					return hp;
				} else{
					throw new RuntimeException("Missed pointer for " + type + ".");
				}
			}
		}
		
		return null;
	}

	private Address findAddress(Element el) {
		NodeList locations = el.getChildNodes();
		for(int i = 0; i < locations.getLength(); i++){
			if(locations.item(i).getNodeName().equals("location")){
				if(! (locations.item(i) instanceof Element))
					continue;
				
				Element location = (Element)locations.item(i);
				if(! location.hasAttribute("type") || ! location.getAttribute("type").equals("address"))
					continue;
				
				// get address element
				Element addressEl = (Element)location.getElementsByTagName("address").item(0);
				if (addressEl == null)
				  throw new ElementRuntimeException(el, "Expected address missing");
				Address address = new Address();
			
				NodeList addressChildren = addressEl.getChildNodes();
				for(int j = 0; j < addressChildren.getLength(); j++){
					if(! (addressChildren.item(j) instanceof Element))
						continue;
					
					Element addressPart = (Element) addressChildren.item(j);
					
					if(addressPart.getNodeName().equals("addrLine"))
						address.setStreetAddress(addressPart.getTextContent());
					else if(addressPart.getNodeName().equals("postCode"))
						address.setPostCode(addressPart.getTextContent());
				}
				
				return address;
			}
		}
		
		return null;
	}
	

	
	private Location findLocation(Element el) {
		NodeList children = el.getChildNodes();
		for(int i = 0; i < children.getLength(); i++){
			if(children.item(i).getNodeName().equals("location")){
				if(! (children.item(i) instanceof Element))
					continue;
			
				Element location = (Element) children.item(i);
				NodeList geos = location.getElementsByTagName("geo");
				if(geos.getLength() > 0){
					String geo = geos.item(0).getTextContent();
					
					Location loc = new Location();
					loc.setPos(geo);
					
					return loc;
				}
			}
		}

		return null;
	}

	
	private String findName(Element el) {
		NodeList placeNames = el.getChildNodes();
		for(int i = 0; i < placeNames.getLength(); i++){
			if(placeNames.item(i).getNodeName().equals("placeName")){
				return placeNames.item(i).getTextContent();
			}
		}
		return null;
	}



  /**
   * 
   * @param args
   */
  public static void main(String[] args) {
    String filename = args[0];
    File file = new File(filename);
    if(! file.exists())
      throw new RuntimeException("Argument one needs to be a file");
    
    GabotoFactory.init(GabotoConfiguration.fromConfigFile());
    Gaboto gab = GabotoFactory.getPersistentGaboto();
    new TEIImporter(gab, file).run();
  }

}
