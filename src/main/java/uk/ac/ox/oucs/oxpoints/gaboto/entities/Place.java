package uk.ac.ox.oucs.oxpoints.gaboto.entities;


import com.hp.hpl.jena.rdf.model.Literal;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.Statement;

import java.lang.reflect.Method;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import net.sf.gaboto.GabotoRuntimeException;
import net.sf.gaboto.GabotoSnapshot;

import net.sf.gaboto.node.GabotoEntity;

import net.sf.gaboto.node.annotation.ComplexProperty;
import net.sf.gaboto.node.annotation.IndirectProperty;
import net.sf.gaboto.node.annotation.PassiveProperty;
import net.sf.gaboto.node.annotation.SimpleLiteralProperty;
import net.sf.gaboto.node.annotation.SimpleURIProperty;
import net.sf.gaboto.node.annotation.UnstoredProperty;

import net.sf.gaboto.node.pool.EntityExistsCallback;
import net.sf.gaboto.node.pool.EntityPool;
import net.sf.gaboto.node.pool.PassiveEntitiesRequest;

import uk.ac.ox.oucs.oxpoints.gaboto.beans.Address;

import uk.ac.ox.oucs.oxpoints.gaboto.entities.OxpEntity;


/**
 * Gaboto generated Entity.
 * @see net.sf.gaboto.generation.GabotoGenerator
 */
public class Place extends OxpEntity {
  private String oBNCode;
  private Place parent;
  private Float longitude;
  private Float latitude;
  private Address address;
  private Website homepage;
  private Float floor;
  private Collection<Place> containedPlaces;
  private String osmId;
  private Collection<Unit> occupiedBy;


  private static Map<String, List<Method>> indirectPropertyLookupTable;
  static{
    indirectPropertyLookupTable = new HashMap<String, List<Method>>();
    List<Method> list;

    try {
      list = new ArrayList<Method>();
      list.add(Place.class.getMethod("getParent", (Class<?>[])null));
      indirectPropertyLookupTable.put("http://www.w3.org/2003/01/geo/wgs84_pos#long", list);

      list = new ArrayList<Method>();
      list.add(Place.class.getMethod("getParent", (Class<?>[])null));
      indirectPropertyLookupTable.put("http://www.w3.org/2003/01/geo/wgs84_pos#lat", list);

    } catch (Exception e) {
      throw new GabotoRuntimeException(e);
    }
  }

  @Override
  public String getType(){
    return "http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#Place";
  }

  @SimpleLiteralProperty(
    value = "http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#hasOBNCode",
    datatypeType = "javaprimitive",
    javaType = "String"
  )
  public String getOBNCode(){
    return this.oBNCode;
  }

  @SimpleLiteralProperty(
    value = "http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#hasOBNCode",
    datatypeType = "javaprimitive",
    javaType = "String"
  )
  public void setOBNCode(String oBNCode){
    this.oBNCode = oBNCode;
  }

  @UnstoredProperty({"http://ns.ox.ac.uk/namespace/gaboto/kml/2009/03/owl#parent"})
  @IndirectProperty({"http://www.w3.org/2003/01/geo/wgs84_pos#long","http://www.w3.org/2003/01/geo/wgs84_pos#lat"})
  @SimpleURIProperty("http://purl.org/dc/terms/isPartOf")
  public Place getParent(){
    if(! this.isDirectReferencesResolved())
      this.resolveDirectReferences();
    return this.parent;
  }

  @SimpleURIProperty("http://purl.org/dc/terms/isPartOf")
  public void setParent(Place parent){
    if( parent != null )
      this.removeMissingReference( parent.getUri() );
    this.parent = parent;
  }

  @SimpleLiteralProperty(
    value = "http://www.w3.org/2003/01/geo/wgs84_pos#long",
    datatypeType = "javaprimitive",
    javaType = "Float"
  )
  public Float getLongitude(){
    return this.longitude;
  }

  @SimpleLiteralProperty(
    value = "http://www.w3.org/2003/01/geo/wgs84_pos#long",
    datatypeType = "javaprimitive",
    javaType = "Float"
  )
  public void setLongitude(Float longitude){
    this.longitude = longitude;
  }

  @SimpleLiteralProperty(
    value = "http://www.w3.org/2003/01/geo/wgs84_pos#lat",
    datatypeType = "javaprimitive",
    javaType = "Float"
  )
  public Float getLatitude(){
    return this.latitude;
  }

  @SimpleLiteralProperty(
    value = "http://www.w3.org/2003/01/geo/wgs84_pos#lat",
    datatypeType = "javaprimitive",
    javaType = "Float"
  )
  public void setLatitude(Float latitude){
    this.latitude = latitude;
  }

  @ComplexProperty("http://nwalsh.com/rdf/vCard#adr")
  public Address getAddress(){
    return this.address;
  }

  @ComplexProperty("http://nwalsh.com/rdf/vCard#adr")
  public void setAddress(Address address){
    this.address = address;
  }

  @SimpleURIProperty("http://xmlns.com/foaf/0.1/homepage")
  public Website getHomepage(){
    if(! this.isDirectReferencesResolved())
      this.resolveDirectReferences();
    return this.homepage;
  }

  @SimpleURIProperty("http://xmlns.com/foaf/0.1/homepage")
  public void setHomepage(Website homepage){
    if( homepage != null )
      this.removeMissingReference( homepage.getUri() );
    this.homepage = homepage;
  }

  @SimpleLiteralProperty(
    value = "http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#floor",
    datatypeType = "javaprimitive",
    javaType = "Float"
  )
  public Float getFloor(){
    return this.floor;
  }

  @SimpleLiteralProperty(
    value = "http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#floor",
    datatypeType = "javaprimitive",
    javaType = "Float"
  )
  public void setFloor(Float floor){
    this.floor = floor;
  }

  @PassiveProperty(
    uri = "http://purl.org/dc/terms/isPartOf",
    entity = "Place"
  )
  public Collection<Place> getContainedPlaces(){
    if(! isPassiveEntitiesLoaded() )
      loadPassiveEntities();
    return this.containedPlaces;
  }

  @PassiveProperty(
    uri = "http://purl.org/dc/terms/isPartOf",
    entity = "Place"
  )
  private void setContainedPlaces(Collection<Place> containedPlaces){
    this.containedPlaces = containedPlaces;
  }

  private void addContainedPlace(Place containedPlaceP){
    if(this.containedPlaces == null)
      setContainedPlaces( new HashSet<Place>() );
    this.containedPlaces.add(containedPlaceP);
  }

  @SimpleLiteralProperty(
    value = "http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#hasOSMIdentifier",
    datatypeType = "javaprimitive",
    javaType = "String"
  )
  public String getOsmId(){
    return this.osmId;
  }

  @SimpleLiteralProperty(
    value = "http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#hasOSMIdentifier",
    datatypeType = "javaprimitive",
    javaType = "String"
  )
  public void setOsmId(String osmId){
    this.osmId = osmId;
  }

  @PassiveProperty(
    uri = "http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#occupies",
    entity = "Unit"
  )
  public Collection<Unit> getOccupiedBy(){
    if(! isPassiveEntitiesLoaded() )
      loadPassiveEntities();
    return this.occupiedBy;
  }

  @PassiveProperty(
    uri = "http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#occupies",
    entity = "Unit"
  )
  private void setOccupiedBy(Collection<Unit> occupiedBy){
    this.occupiedBy = occupiedBy;
  }

  private void addOccupiedBy(Unit occupiedByP){
    if(this.occupiedBy == null)
      setOccupiedBy( new HashSet<Unit>() );
    this.occupiedBy.add(occupiedByP);
  }







  public Collection<PassiveEntitiesRequest> getPassiveEntitiesRequest(){
    Collection<PassiveEntitiesRequest> requests = super.getPassiveEntitiesRequest();
    if(requests == null)
      requests = new HashSet<PassiveEntitiesRequest>();
    requests.add(new PassiveEntitiesRequest(){
      public String getType() {
        return "http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#Place";
      }

      public String getUri() {
        return "http://purl.org/dc/terms/isPartOf";
      }

      public int getCollectionType() {
        return EntityPool.PASSIVE_PROPERTY_COLLECTION_TYPE_NONE;
      }

      public void passiveEntityLoaded(GabotoEntity entity) {
        addContainedPlace((Place)entity);
      }
    });
    requests.add(new PassiveEntitiesRequest(){
      public String getType() {
        return "http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#Unit";
      }

      public String getUri() {
        return "http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#occupies";
      }

      public int getCollectionType() {
        return EntityPool.PASSIVE_PROPERTY_COLLECTION_TYPE_BAG;
      }

      public void passiveEntityLoaded(GabotoEntity entity) {
        addOccupiedBy((Unit)entity);
      }
    });
    return requests;
  }


  public void loadFromSnapshot(Resource res, GabotoSnapshot snapshot, EntityPool pool) {
    super.loadFromSnapshot(res, snapshot, pool);
    Statement stmt;

    // Load SIMPLE_LITERAL_PROPERTY oBNCode
    stmt = res.getProperty(snapshot.getProperty("http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#hasOBNCode"));
    if(stmt != null && stmt.getObject().isLiteral())
      this.setOBNCode(((Literal)stmt.getObject()).getString());

    // Load SIMPLE_URI_PROPERTY parent
    stmt = res.getProperty(snapshot.getProperty("http://purl.org/dc/terms/isPartOf"));
    if(stmt != null && stmt.getObject().isResource()){
      Resource missingReference = (Resource)stmt.getObject();
      EntityExistsCallback callback = new EntityExistsCallback(){
        public void entityExists(EntityPool p, GabotoEntity entity) {
          setParent((Place)entity);
        }
      };
      this.addMissingReference(missingReference, callback);
    }

    // Load SIMPLE_LITERAL_PROPERTY longitude
    stmt = res.getProperty(snapshot.getProperty("http://www.w3.org/2003/01/geo/wgs84_pos#long"));
    if(stmt != null && stmt.getObject().isLiteral())
      this.setLongitude(((Literal)stmt.getObject()).getFloat());

    // Load SIMPLE_LITERAL_PROPERTY latitude
    stmt = res.getProperty(snapshot.getProperty("http://www.w3.org/2003/01/geo/wgs84_pos#lat"));
    if(stmt != null && stmt.getObject().isLiteral())
      this.setLatitude(((Literal)stmt.getObject()).getFloat());

    // Load SIMPLE_COMPLEX_PROPERTY address
    stmt = res.getProperty(snapshot.getProperty("http://nwalsh.com/rdf/vCard#adr"));
    if(stmt != null && stmt.getObject().isAnon()){
      Address bean = new Address();
      bean.loadFromResource((Resource)stmt.getObject(), snapshot, pool);
      setAddress(bean);
    }

    // Load SIMPLE_URI_PROPERTY homepage
    stmt = res.getProperty(snapshot.getProperty("http://xmlns.com/foaf/0.1/homepage"));
    if(stmt != null && stmt.getObject().isResource()){
      Resource missingReference = (Resource)stmt.getObject();
      EntityExistsCallback callback = new EntityExistsCallback(){
        public void entityExists(EntityPool p, GabotoEntity entity) {
          setHomepage((Website)entity);
        }
      };
      this.addMissingReference(missingReference, callback);
    }

    // Load SIMPLE_LITERAL_PROPERTY floor
    stmt = res.getProperty(snapshot.getProperty("http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#floor"));
    if(stmt != null && stmt.getObject().isLiteral())
      this.setFloor(((Literal)stmt.getObject()).getFloat());

    // Load SIMPLE_LITERAL_PROPERTY osmId
    stmt = res.getProperty(snapshot.getProperty("http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#hasOSMIdentifier"));
    if(stmt != null && stmt.getObject().isLiteral())
      this.setOsmId(((Literal)stmt.getObject()).getString());

  }
  protected List<Method> getIndirectMethodsForProperty(String propertyURI){
    List<Method> list = super.getIndirectMethodsForProperty(propertyURI);
    if(list == null)
      return indirectPropertyLookupTable.get(propertyURI);
    
    else{
      List<Method> tmp = indirectPropertyLookupTable.get(propertyURI);
      if(tmp != null)
        list.addAll(tmp);
    }
    return list;
  }

}