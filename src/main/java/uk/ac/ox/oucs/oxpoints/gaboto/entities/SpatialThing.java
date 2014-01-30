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

import net.sf.gaboto.node.annotation.IndirectProperty;
import net.sf.gaboto.node.annotation.PassiveProperty;
import net.sf.gaboto.node.annotation.SimpleLiteralProperty;
import net.sf.gaboto.node.annotation.SimpleURIProperty;
import net.sf.gaboto.node.annotation.UnstoredProperty;

import net.sf.gaboto.node.pool.EntityExistsCallback;
import net.sf.gaboto.node.pool.EntityPool;
import net.sf.gaboto.node.pool.PassiveEntitiesRequest;

import uk.ac.ox.oucs.oxpoints.gaboto.entities.OxpEntity;


/**
 * Gaboto generated Entity.
 * @see net.sf.gaboto.generation.GabotoGenerator
 */
public class SpatialThing extends OxpEntity {
  protected Place containedBy;
  protected Place parent;
  protected Place reception;
  protected Float longitude;
  protected Float latitude;
  private Collection<Place> containedPlaces;


  private static Map<String, List<Method>> indirectPropertyLookupTable;
  static{
    indirectPropertyLookupTable = new HashMap<String, List<Method>>();
    List<Method> list;

    try {
      list = new ArrayList<Method>();
      list.add(SpatialThing.class.getMethod("getContainedBy", (Class<?>[])null));
      indirectPropertyLookupTable.put("http://www.w3.org/2003/01/geo/wgs84_pos#long", list);

      list = new ArrayList<Method>();
      list.add(SpatialThing.class.getMethod("getContainedBy", (Class<?>[])null));
      indirectPropertyLookupTable.put("http://www.w3.org/2003/01/geo/wgs84_pos#lat", list);

    } catch (Exception e) {
      throw new GabotoRuntimeException(e);
    }
  }

  @Override
  public String getType(){
    return "http://www.w3.org/2003/01/geo/wgs84_pos#SpatialThing";
  }

  @UnstoredProperty({"http://ns.ox.ac.uk/namespace/gaboto/kml/2009/03/owl#parent"})
  @IndirectProperty({"http://www.w3.org/2003/01/geo/wgs84_pos#long","http://www.w3.org/2003/01/geo/wgs84_pos#lat"})
  @SimpleURIProperty("http://data.ordnancesurvey.co.uk/ontology/spatialrelations/within")
  public Place getContainedBy(){
    if(! this.isDirectReferencesResolved())
      this.resolveDirectReferences();
    return this.containedBy;
  }

  @SimpleURIProperty("http://data.ordnancesurvey.co.uk/ontology/spatialrelations/within")
  public void setContainedBy(Place containedBy){
    if( containedBy != null )
      this.removeMissingReference( containedBy.getUri() );
    this.containedBy = containedBy;
  }

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

  @SimpleURIProperty("http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#reception")
  public Place getReception(){
    if(! this.isDirectReferencesResolved())
      this.resolveDirectReferences();
    return this.reception;
  }

  @SimpleURIProperty("http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#reception")
  public void setReception(Place reception){
    if( reception != null )
      this.removeMissingReference( reception.getUri() );
    this.reception = reception;
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
    return requests;
  }


  public void loadFromSnapshot(Resource res, GabotoSnapshot snapshot, EntityPool pool) {
    super.loadFromSnapshot(res, snapshot, pool);
    Statement stmt;

    // Load SIMPLE_URI_PROPERTY containedBy
    stmt = res.getProperty(snapshot.getProperty("http://data.ordnancesurvey.co.uk/ontology/spatialrelations/within"));
    if(stmt != null && stmt.getObject().isResource()){
      Resource missingReference = (Resource)stmt.getObject();
      EntityExistsCallback callback = new EntityExistsCallback(){
        public void entityExists(EntityPool p, GabotoEntity entity) {
          setContainedBy((Place)entity);
        }
      };
      this.addMissingReference(missingReference, callback);
    }

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

    // Load SIMPLE_URI_PROPERTY reception
    stmt = res.getProperty(snapshot.getProperty("http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#reception"));
    if(stmt != null && stmt.getObject().isResource()){
      Resource missingReference = (Resource)stmt.getObject();
      EntityExistsCallback callback = new EntityExistsCallback(){
        public void entityExists(EntityPool p, GabotoEntity entity) {
          setReception((Place)entity);
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