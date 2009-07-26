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

import net.sf.gaboto.model.GabotoSnapshot;

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
import uk.ac.ox.oucs.oxpoints.gaboto.beans.Location;

import uk.ac.ox.oucs.oxpoints.gaboto.entities.OxpEntity;


/**
 * Gaboto generated Entity.
 * @see net.sf.gaboto.generation.GabotoGenerator
 */
public class Place extends OxpEntity {
  private String name;
  private String oUCSCode;
  private String oBNCode;
  private Place parent;
  private Location location;
  private Address address;
  private Website homepage;
  private Collection<Place> containedPlaces;


  private static Map<String, List<Method>> indirectPropertyLookupTable;
  static{
    indirectPropertyLookupTable = new HashMap<String, List<Method>>();
    List<Method> list;

    try {
      list = new ArrayList<Method>();
      list.add(Place.class.getMethod("getParent", (Class<?>[])null));
      indirectPropertyLookupTable.put("http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#hasLocation", list);

    } catch (Exception e) {
      throw new GabotoRuntimeException(e);
    }
  }

  @Override
  public String getType(){
    return "http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#Place";
  }

  @SimpleLiteralProperty(
    value = "http://purl.org/dc/elements/1.1/title",
    datatypeType = "javaprimitive",
    javaType = "String"
  )
  public String getName(){
    return this.name;
  }

  @SimpleLiteralProperty(
    value = "http://purl.org/dc/elements/1.1/title",
    datatypeType = "javaprimitive",
    javaType = "String"
  )
  public void setName(String name){
    this.name = name;
  }

  @SimpleLiteralProperty(
    value = "http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#hasOUCSCode",
    datatypeType = "javaprimitive",
    javaType = "String"
  )
  public String getOUCSCode(){
    return this.oUCSCode;
  }

  @SimpleLiteralProperty(
    value = "http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#hasOUCSCode",
    datatypeType = "javaprimitive",
    javaType = "String"
  )
  public void setOUCSCode(String oUCSCode){
    this.oUCSCode = oUCSCode;
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
  @IndirectProperty({"http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#hasLocation"})
  @SimpleURIProperty("http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#physicallyContainedWithin")
  public Place getParent(){
    if(! this.isDirectReferencesResolved())
      this.resolveDirectReferences();
    return this.parent;
  }

  @SimpleURIProperty("http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#physicallyContainedWithin")
  public void setParent(Place parent){
    if( parent != null )
      this.removeMissingReference( parent.getUri() );
    this.parent = parent;
  }

  @ComplexProperty("http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#hasLocation")
  public Location getLocation(){
    return this.location;
  }

  @ComplexProperty("http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#hasLocation")
  public void setLocation(Location location){
    this.location = location;
  }

  @ComplexProperty("http://nwalsh.com/rdf/vCard#adr")
  public Address getAddress(){
    return this.address;
  }

  @ComplexProperty("http://nwalsh.com/rdf/vCard#adr")
  public void setAddress(Address address){
    this.address = address;
  }

  @SimpleURIProperty("http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#hasHomepage")
  public Website getHomepage(){
    if(! this.isDirectReferencesResolved())
      this.resolveDirectReferences();
    return this.homepage;
  }

  @SimpleURIProperty("http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#hasHomepage")
  public void setHomepage(Website homepage){
    if( homepage != null )
      this.removeMissingReference( homepage.getUri() );
    this.homepage = homepage;
  }

  @PassiveProperty(
    uri = "http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#physicallyContainedWithin",
    entity = "Place"
  )
  public Collection<Place> getContainedPlaces(){
    if(! isPassiveEntitiesLoaded() )
      loadPassiveEntities();
    return this.containedPlaces;
  }

  @PassiveProperty(
    uri = "http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#physicallyContainedWithin",
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







  public Collection<PassiveEntitiesRequest> getPassiveEntitiesRequest(){
    Collection<PassiveEntitiesRequest> requests = super.getPassiveEntitiesRequest();
    if(requests == null)
      requests = new HashSet<PassiveEntitiesRequest>();
    requests.add(new PassiveEntitiesRequest(){
      public String getType() {
        return "http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#Place";
      }

      public String getUri() {
        return "http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#physicallyContainedWithin";
      }

      public int getCollectionType() {
        return EntityPool.PASSIVE_PROPERTY_COLLECTION_TYPE_NONE;
      }

      public void passiveEntityLoaded(GabotoEntity entity) {
        addContainedPlace((Place)entity);
      }
    });
    return requests;
  }


  public void loadFromSnapshot(Resource res, GabotoSnapshot snapshot, EntityPool pool) {
    super.loadFromSnapshot(res, snapshot, pool);
    Statement stmt;

    // Load SIMPLE_LITERAL_PROPERTY name
    stmt = res.getProperty(snapshot.getProperty("http://purl.org/dc/elements/1.1/title"));
    if(stmt != null && stmt.getObject().isLiteral())
      this.setName(((Literal)stmt.getObject()).getString());

    // Load SIMPLE_LITERAL_PROPERTY oUCSCode
    stmt = res.getProperty(snapshot.getProperty("http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#hasOUCSCode"));
    if(stmt != null && stmt.getObject().isLiteral())
      this.setOUCSCode(((Literal)stmt.getObject()).getString());

    // Load SIMPLE_LITERAL_PROPERTY oBNCode
    stmt = res.getProperty(snapshot.getProperty("http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#hasOBNCode"));
    if(stmt != null && stmt.getObject().isLiteral())
      this.setOBNCode(((Literal)stmt.getObject()).getString());

    // Load SIMPLE_URI_PROPERTY parent
    stmt = res.getProperty(snapshot.getProperty("http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#physicallyContainedWithin"));
    if(stmt != null && stmt.getObject().isResource()){
      Resource missingReference = (Resource)stmt.getObject();
      EntityExistsCallback callback = new EntityExistsCallback(){
        public void entityExists(EntityPool p, GabotoEntity entity) {
          setParent((Place)entity);
        }
      };
      this.addMissingReference(missingReference, callback);
    }

    // Load SIMPLE_COMPLEX_PROPERTY location
    stmt = res.getProperty(snapshot.getProperty("http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#hasLocation"));
    if(stmt != null && stmt.getObject().isAnon()){
      Location bean = new Location();
      bean.loadFromResource((Resource)stmt.getObject(), snapshot, pool);
      setLocation(bean);
    }

    // Load SIMPLE_COMPLEX_PROPERTY address
    stmt = res.getProperty(snapshot.getProperty("http://nwalsh.com/rdf/vCard#adr"));
    if(stmt != null && stmt.getObject().isAnon()){
      Address bean = new Address();
      bean.loadFromResource((Resource)stmt.getObject(), snapshot, pool);
      setAddress(bean);
    }

    // Load SIMPLE_URI_PROPERTY homepage
    stmt = res.getProperty(snapshot.getProperty("http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#hasHomepage"));
    if(stmt != null && stmt.getObject().isResource()){
      Resource missingReference = (Resource)stmt.getObject();
      EntityExistsCallback callback = new EntityExistsCallback(){
        public void entityExists(EntityPool p, GabotoEntity entity) {
          setHomepage((Website)entity);
        }
      };
      this.addMissingReference(missingReference, callback);
    }

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