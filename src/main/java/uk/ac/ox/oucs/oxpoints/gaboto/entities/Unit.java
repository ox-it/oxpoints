package uk.ac.ox.oucs.oxpoints.gaboto.entities;


import com.hp.hpl.jena.rdf.model.Literal;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.Statement;
import com.hp.hpl.jena.rdf.model.StmtIterator;

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

import net.sf.gaboto.node.annotation.BagURIProperty;
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
public class Unit extends OxpEntity {
  private String oUCSCode;
  private Address address;
  private Website homepage;
  private Website itHomepage;
  private Collection<Place> occupiedPlaces;
  private Place primaryPlace;
  private Unit parent;
  private Website weblearn;
  private Collection<Unit> hasChildren;


  private static Map<String, List<Method>> indirectPropertyLookupTable;
  static{
    indirectPropertyLookupTable = new HashMap<String, List<Method>>();
    List<Method> list;

    try {
      list = new ArrayList<Method>();
      list.add(Unit.class.getMethod("getPrimaryPlace", (Class<?>[])null));
      list.add(Unit.class.getMethod("getParent", (Class<?>[])null));
      list.add(Unit.class.getMethod("getOccupiedPlaces", (Class<?>[])null));
      indirectPropertyLookupTable.put("http://www.w3.org/2003/01/geo/wgs84_pos#long", list);

      list = new ArrayList<Method>();
      list.add(Unit.class.getMethod("getPrimaryPlace", (Class<?>[])null));
      list.add(Unit.class.getMethod("getParent", (Class<?>[])null));
      list.add(Unit.class.getMethod("getOccupiedPlaces", (Class<?>[])null));
      indirectPropertyLookupTable.put("http://www.w3.org/2003/01/geo/wgs84_pos#lat", list);

    } catch (Exception e) {
      throw new GabotoRuntimeException(e);
    }
  }

  @Override
  public String getType(){
    return "http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#Unit";
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

  @SimpleURIProperty("http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#hasITHomepage")
  public Website getItHomepage(){
    if(! this.isDirectReferencesResolved())
      this.resolveDirectReferences();
    return this.itHomepage;
  }

  @SimpleURIProperty("http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#hasITHomepage")
  public void setItHomepage(Website itHomepage){
    if( itHomepage != null )
      this.removeMissingReference( itHomepage.getUri() );
    this.itHomepage = itHomepage;
  }

  @IndirectProperty({"http://www.w3.org/2003/01/geo/wgs84_pos#long","http://www.w3.org/2003/01/geo/wgs84_pos#lat"})
  @BagURIProperty("http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#occupies")
  public Collection<Place> getOccupiedPlaces(){
    if(! this.isDirectReferencesResolved())
      this.resolveDirectReferences();
    return this.occupiedPlaces;
  }

  @BagURIProperty("http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#occupies")
  public void setOccupiedPlaces(Collection<Place> occupiedPlaces){
    if( occupiedPlaces != null ){
      for( GabotoEntity _entity : occupiedPlaces)
        this.removeMissingReference( _entity.getUri() );
    }

    this.occupiedPlaces = occupiedPlaces;
  }

  public void addOccupiedPlace(Place occupiedPlace){
    if( occupiedPlace != null )
      this.removeMissingReference( occupiedPlace.getUri() );
    if(this.occupiedPlaces == null )
      this.occupiedPlaces = new HashSet<Place>();
    this.occupiedPlaces.add(occupiedPlace);
  }

  @IndirectProperty({"http://www.w3.org/2003/01/geo/wgs84_pos#long","http://www.w3.org/2003/01/geo/wgs84_pos#lat"})
  @SimpleURIProperty("http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#primaryPlace")
  public Place getPrimaryPlace(){
    if(! this.isDirectReferencesResolved())
      this.resolveDirectReferences();
    return this.primaryPlace;
  }

  @SimpleURIProperty("http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#primaryPlace")
  public void setPrimaryPlace(Place primaryPlace){
    if( primaryPlace != null )
      this.removeMissingReference( primaryPlace.getUri() );
    this.primaryPlace = primaryPlace;
  }

  @UnstoredProperty({"http://ns.ox.ac.uk/namespace/gaboto/kml/2009/03/owl#parent"})
  @IndirectProperty({"http://www.w3.org/2003/01/geo/wgs84_pos#long","http://www.w3.org/2003/01/geo/wgs84_pos#lat"})
  @SimpleURIProperty("http://purl.org/dc/terms/isPartOf")
  public Unit getParent(){
    if(! this.isDirectReferencesResolved())
      this.resolveDirectReferences();
    return this.parent;
  }

  @SimpleURIProperty("http://purl.org/dc/terms/isPartOf")
  public void setParent(Unit parent){
    if( parent != null )
      this.removeMissingReference( parent.getUri() );
    this.parent = parent;
  }

  @SimpleURIProperty("http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#hasWeblearn")
  public Website getWeblearn(){
    if(! this.isDirectReferencesResolved())
      this.resolveDirectReferences();
    return this.weblearn;
  }

  @SimpleURIProperty("http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#hasWeblearn")
  public void setWeblearn(Website weblearn){
    if( weblearn != null )
      this.removeMissingReference( weblearn.getUri() );
    this.weblearn = weblearn;
  }

  @PassiveProperty(
    uri = "http://purl.org/dc/terms/isPartOf",
    entity = "Unit"
  )
  public Collection<Unit> getHasChildren(){
    if(! isPassiveEntitiesLoaded() )
      loadPassiveEntities();
    return this.hasChildren;
  }

  @PassiveProperty(
    uri = "http://purl.org/dc/terms/isPartOf",
    entity = "Unit"
  )
  private void setHasChildren(Collection<Unit> hasChildren){
    this.hasChildren = hasChildren;
  }

  private void addHasChildren(Unit hasChildrenP){
    if(this.hasChildren == null)
      setHasChildren( new HashSet<Unit>() );
    this.hasChildren.add(hasChildrenP);
  }





  public Object getLongitude(){
    return this.getPropertyValue("http://www.w3.org/2003/01/geo/wgs84_pos#long", false, true);
  }

  public Object getLatitude(){
    return this.getPropertyValue("http://www.w3.org/2003/01/geo/wgs84_pos#lat", false, true);
  }



  public Collection<PassiveEntitiesRequest> getPassiveEntitiesRequest(){
    Collection<PassiveEntitiesRequest> requests = super.getPassiveEntitiesRequest();
    if(requests == null)
      requests = new HashSet<PassiveEntitiesRequest>();
    requests.add(new PassiveEntitiesRequest(){
      public String getType() {
        return "http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#Unit";
      }

      public String getUri() {
        return "http://purl.org/dc/terms/isPartOf";
      }

      public int getCollectionType() {
        return EntityPool.PASSIVE_PROPERTY_COLLECTION_TYPE_NONE;
      }

      public void passiveEntityLoaded(GabotoEntity entity) {
        addHasChildren((Unit)entity);
      }
    });
    return requests;
  }


  public void loadFromSnapshot(Resource res, GabotoSnapshot snapshot, EntityPool pool) {
    super.loadFromSnapshot(res, snapshot, pool);
    Statement stmt;

    // Load SIMPLE_LITERAL_PROPERTY oUCSCode
    stmt = res.getProperty(snapshot.getProperty("http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#hasOUCSCode"));
    if(stmt != null && stmt.getObject().isLiteral())
      this.setOUCSCode(((Literal)stmt.getObject()).getString());

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

    // Load SIMPLE_URI_PROPERTY itHomepage
    stmt = res.getProperty(snapshot.getProperty("http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#hasITHomepage"));
    if(stmt != null && stmt.getObject().isResource()){
      Resource missingReference = (Resource)stmt.getObject();
      EntityExistsCallback callback = new EntityExistsCallback(){
        public void entityExists(EntityPool p, GabotoEntity entity) {
          setItHomepage((Website)entity);
        }
      };
      this.addMissingReference(missingReference, callback);
    }

    // Load BAG_URI_PROPERTY occupiedPlaces
    {
        StmtIterator stmts = res.listProperties(snapshot.getProperty("http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#occupies"));
        while (stmts.hasNext()) {
            RDFNode node = stmts.next().getObject();
            if(! node.isResource())
              throw new IllegalArgumentException("node should be a resource");

            Resource missingReference = (Resource)node;
            EntityExistsCallback callback = new EntityExistsCallback(){
              public void entityExists(EntityPool p, GabotoEntity entity) {
                addOccupiedPlace((Place) entity);
            }
        };
        this.addMissingReference(missingReference, callback);
      }
    }

    // Load SIMPLE_URI_PROPERTY primaryPlace
    stmt = res.getProperty(snapshot.getProperty("http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#primaryPlace"));
    if(stmt != null && stmt.getObject().isResource()){
      Resource missingReference = (Resource)stmt.getObject();
      EntityExistsCallback callback = new EntityExistsCallback(){
        public void entityExists(EntityPool p, GabotoEntity entity) {
          setPrimaryPlace((Place)entity);
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
          setParent((Unit)entity);
        }
      };
      this.addMissingReference(missingReference, callback);
    }

    // Load SIMPLE_URI_PROPERTY weblearn
    stmt = res.getProperty(snapshot.getProperty("http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#hasWeblearn"));
    if(stmt != null && stmt.getObject().isResource()){
      Resource missingReference = (Resource)stmt.getObject();
      EntityExistsCallback callback = new EntityExistsCallback(){
        public void entityExists(EntityPool p, GabotoEntity entity) {
          setWeblearn((Website)entity);
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