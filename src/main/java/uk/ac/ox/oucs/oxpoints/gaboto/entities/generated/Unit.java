package uk.ac.ox.oucs.oxpoints.gaboto.entities.generated;


import com.hp.hpl.jena.rdf.model.Bag;
import com.hp.hpl.jena.rdf.model.Literal;
import com.hp.hpl.jena.rdf.model.NodeIterator;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.Statement;

import java.lang.reflect.Method;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;


import org.oucs.gaboto.entities.GabotoEntity;

import org.oucs.gaboto.entities.pool.EntityExistsCallback;
import org.oucs.gaboto.entities.pool.GabotoEntityPool;
import org.oucs.gaboto.entities.pool.PassiveEntitiesRequest;

import org.oucs.gaboto.entities.utils.BagURIProperty;
import org.oucs.gaboto.entities.utils.ComplexProperty;
import org.oucs.gaboto.entities.utils.IndirectProperty;
import org.oucs.gaboto.entities.utils.PassiveProperty;
import org.oucs.gaboto.entities.utils.SimpleLiteralProperty;
import org.oucs.gaboto.entities.utils.SimpleURIProperty;
import org.oucs.gaboto.entities.utils.StaticProperty;
import org.oucs.gaboto.entities.utils.UnstoredProperty;

import org.oucs.gaboto.exceptions.GabotoRuntimeException;

import org.oucs.gaboto.model.GabotoSnapshot;

import uk.ac.ox.oucs.oxpoints.gaboto.beans.generated.Address;
import uk.ac.ox.oucs.oxpoints.gaboto.entities.generated.OxpEntity;


/**
 * Gaboto generated Entity.
 * @see net.sf.gaboto.generation.GabotoGenerator#generateEntity.
 */
public class Unit extends OxpEntity {
  private Address address;
  private Website homepage;
  private String oUCSCode;
  private Website itHomepage;
  private String name;
  private Collection<Building> occupiedBuildings;
  private Place primaryPlace;
  private Unit subsetOf;
  private Website weblearn;
  private Collection<Unit> hasSubsets;


  private static Map<String, List<Method>> indirectPropertyLookupTable;
  static{
    indirectPropertyLookupTable = new HashMap<String, List<Method>>();
    List<Method> list;

    try{
      list = new ArrayList<Method>();
      list.add(Unit.class.getMethod("getPrimaryPlace", (Class<?>[])null));
      list.add(Unit.class.getMethod("getSubsetOf", (Class<?>[])null));
      list.add(Unit.class.getMethod("getOccupiedBuildings", (Class<?>[])null));
      indirectPropertyLookupTable.put("http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#hasLocation", list);

    } catch (Exception e) {
      throw new GabotoRuntimeException(e);
    }
  }

  @Override
  public String getType(){
    return "http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#Unit";
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

  @IndirectProperty({"http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#hasLocation"})
  @BagURIProperty("http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#occupies")
  public Collection<Building> getOccupiedBuildings(){
    if(! this.isDirectReferencesResolved())
      this.resolveDirectReferences();
    return this.occupiedBuildings;
  }

  @BagURIProperty("http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#occupies")
  public void setOccupiedBuildings(Collection<Building> occupiedBuildings){
    if( occupiedBuildings != null ){
      for( GabotoEntity _entity : occupiedBuildings)
        this.removeMissingReference( _entity.getUri() );
    }

    this.occupiedBuildings = occupiedBuildings;
  }

  public void addOccupiedBuilding(Building occupiedBuilding){
    if( occupiedBuilding != null )
      this.removeMissingReference( occupiedBuilding.getUri() );
    if(this.occupiedBuildings == null )
      this.occupiedBuildings = new HashSet<Building>();
    this.occupiedBuildings.add(occupiedBuilding);
  }

  @IndirectProperty({"http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#hasLocation"})
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
  @IndirectProperty({"http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#hasLocation"})
  @SimpleURIProperty("http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#subsetOf")
  public Unit getSubsetOf(){
    if(! this.isDirectReferencesResolved())
      this.resolveDirectReferences();
    return this.subsetOf;
  }

  @SimpleURIProperty("http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#subsetOf")
  public void setSubsetOf(Unit subsetOf){
    if( subsetOf != null )
      this.removeMissingReference( subsetOf.getUri() );
    this.subsetOf = subsetOf;
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
    uri = "http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#subsetOf",
    entity = "Unit"
  )
  public Collection<Unit> getHasSubsets(){
    if(! isPassiveEntitiesLoaded() )
      loadPassiveEntities();
    return this.hasSubsets;
  }

  @PassiveProperty(
    uri = "http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#subsetOf",
    entity = "Unit"
  )
  private void setHasSubsets(Collection<Unit> hasSubsets){
    this.hasSubsets = hasSubsets;
  }

  private void addHasSubset(Unit hasSubsetP){
    if(this.hasSubsets == null)
      setHasSubsets( new HashSet<Unit>() );
    this.hasSubsets.add(hasSubsetP);
  }





  public Object getLocation(){
    return this.getPropertyValue("http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#hasLocation", false, true);
  }


                            
    @StaticProperty("http://purl.org/dc/elements/1.1/description")
    public String getDescription(){
        String description = "";
        
        if(getHomepage() != null)
            description += "Website: <a href=\"" + getHomepage().getUri() + "\">" + getHomepage().getUri() + "</a><br/>";
        if(getImages() != null && getImages().size() > 0){
        	Image img = getImages().iterator().next();
        	description += "<img src=\"" + img.getUri() + "\" width=\"" + img.getWidth() + "\" height=\"" + img.getHeight() + "\"/>";
        }
        
        return description;
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
        return "http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#subsetOf";
      }

      public int getCollectionType() {
        return GabotoEntityPool.PASSIVE_PROPERTY_COLLECTION_TYPE_NONE;
      }

      public void passiveEntityLoaded(GabotoEntity entity) {
        addHasSubset((Unit)entity);
      }
    });
    return requests;
  }


  public void loadFromSnapshot(Resource res, GabotoSnapshot snapshot, GabotoEntityPool pool) {
    super.loadFromSnapshot(res, snapshot, pool);
    Statement stmt;

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
        public void entityExists(GabotoEntityPool p, GabotoEntity entity) {
          setHomepage((Website)entity);
        }
      };
      this.addMissingReference(missingReference, callback);
    }

    // Load SIMPLE_LITERAL_PROPERTY oUCSCode
    stmt = res.getProperty(snapshot.getProperty("http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#hasOUCSCode"));
    if(stmt != null && stmt.getObject().isLiteral())
      this.setOUCSCode(((Literal)stmt.getObject()).getString());

    // Load SIMPLE_URI_PROPERTY itHomepage
    stmt = res.getProperty(snapshot.getProperty("http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#hasITHomepage"));
    if(stmt != null && stmt.getObject().isResource()){
      Resource missingReference = (Resource)stmt.getObject();
      EntityExistsCallback callback = new EntityExistsCallback(){
        public void entityExists(GabotoEntityPool p, GabotoEntity entity) {
          setItHomepage((Website)entity);
        }
      };
      this.addMissingReference(missingReference, callback);
    }

    // Load SIMPLE_LITERAL_PROPERTY name
    stmt = res.getProperty(snapshot.getProperty("http://purl.org/dc/elements/1.1/title"));
    if(stmt != null && stmt.getObject().isLiteral())
      this.setName(((Literal)stmt.getObject()).getString());

    // Load BAG_URI_PROPERTY occupiedBuildings
    stmt = res.getProperty(snapshot.getProperty("http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#occupies"));
    if(stmt != null && stmt.getObject().isResource() && null != stmt.getBag()){
      Bag bag = stmt.getBag();
      NodeIterator nodeIt = bag.iterator();
      while(nodeIt.hasNext()){
        RDFNode node = nodeIt.nextNode();
        if(! node.isResource())
          throw new IllegalArgumentException("node should be a resource");

        Resource missingReference = (Resource)node;
        EntityExistsCallback callback = new EntityExistsCallback(){
          public void entityExists(GabotoEntityPool p, GabotoEntity entity) {
            addOccupiedBuilding((Building) entity);
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
        public void entityExists(GabotoEntityPool p, GabotoEntity entity) {
          setPrimaryPlace((Place)entity);
        }
      };
      this.addMissingReference(missingReference, callback);
    }

    // Load SIMPLE_URI_PROPERTY subsetOf
    stmt = res.getProperty(snapshot.getProperty("http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#subsetOf"));
    if(stmt != null && stmt.getObject().isResource()){
      Resource missingReference = (Resource)stmt.getObject();
      EntityExistsCallback callback = new EntityExistsCallback(){
        public void entityExists(GabotoEntityPool p, GabotoEntity entity) {
          setSubsetOf((Unit)entity);
        }
      };
      this.addMissingReference(missingReference, callback);
    }

    // Load SIMPLE_URI_PROPERTY weblearn
    stmt = res.getProperty(snapshot.getProperty("http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#hasWeblearn"));
    if(stmt != null && stmt.getObject().isResource()){
      Resource missingReference = (Resource)stmt.getObject();
      EntityExistsCallback callback = new EntityExistsCallback(){
        public void entityExists(GabotoEntityPool p, GabotoEntity entity) {
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