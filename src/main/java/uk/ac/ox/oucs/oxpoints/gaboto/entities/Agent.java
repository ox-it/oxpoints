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

import net.sf.gaboto.node.annotation.BagComplexProperty;
import net.sf.gaboto.node.annotation.BagURIProperty;
import net.sf.gaboto.node.annotation.IndirectProperty;
import net.sf.gaboto.node.annotation.PassiveProperty;
import net.sf.gaboto.node.annotation.ResourceProperty;
import net.sf.gaboto.node.annotation.SimpleLiteralProperty;
import net.sf.gaboto.node.annotation.SimpleURIProperty;
import net.sf.gaboto.node.annotation.UnstoredProperty;

import net.sf.gaboto.node.pool.EntityExistsCallback;
import net.sf.gaboto.node.pool.EntityPool;
import net.sf.gaboto.node.pool.PassiveEntitiesRequest;

import uk.ac.ox.oucs.oxpoints.gaboto.OxpointsGabotoOntologyLookup;

import uk.ac.ox.oucs.oxpoints.gaboto.beans.OnlineAccount;
import uk.ac.ox.oucs.oxpoints.gaboto.beans.Tel;

import uk.ac.ox.oucs.oxpoints.gaboto.entities.OxpEntity;


/**
 * Gaboto generated Entity.
 * @see net.sf.gaboto.generation.GabotoGenerator
 */
public class Agent extends OxpEntity {
  protected Collection<Tel> telephoneNumbers;
  protected Collection<OnlineAccount> onlineAccount;
  protected Collection<Place> site;
  protected Collection<Place> occupiedPlaces;
  protected Place primarySite;
  protected Place primaryPlace;
  protected Organization parent;
  protected Organization subOrganizationOf;
  protected String weblearn;
  protected Image logo;
  protected String oLISAlephCode;
  private Collection<Organization> hasChildren;
  private Collection<Organization> subOrganizations;


  private static Map<String, List<Method>> indirectPropertyLookupTable;
  static{
    indirectPropertyLookupTable = new HashMap<String, List<Method>>();
    List<Method> list;

    try {
      list = new ArrayList<Method>();
      list.add(Agent.class.getMethod("getPrimarySite", (Class<?>[])null));
      list.add(Agent.class.getMethod("getSubOrganizationOf", (Class<?>[])null));
      list.add(Agent.class.getMethod("getSite", (Class<?>[])null));
      indirectPropertyLookupTable.put("http://www.w3.org/2003/01/geo/wgs84_pos#long", list);

      list = new ArrayList<Method>();
      list.add(Agent.class.getMethod("getPrimarySite", (Class<?>[])null));
      list.add(Agent.class.getMethod("getSubOrganizationOf", (Class<?>[])null));
      list.add(Agent.class.getMethod("getSite", (Class<?>[])null));
      indirectPropertyLookupTable.put("http://www.w3.org/2003/01/geo/wgs84_pos#lat", list);

    } catch (Exception e) {
      throw new GabotoRuntimeException(e);
    }
  }

  @Override
  public String getType(){
    return "http://xmlns.com/foaf/0.1/Agent";
  }

  @BagComplexProperty("http://www.w3.org/2006/vcard/ns#tel")
  public Collection<Tel> getTelephoneNumbers(){
    return this.telephoneNumbers;
  }

  @BagComplexProperty("http://www.w3.org/2006/vcard/ns#tel")
  public void setTelephoneNumbers(Collection<Tel> telephoneNumbers){
    this.telephoneNumbers = telephoneNumbers;
  }

  public void addTelephoneNumber(Tel telephoneNumberP){
    if(this.telephoneNumbers == null)
      setTelephoneNumbers( new HashSet<Tel>() );
    this.telephoneNumbers.add(telephoneNumberP);
  }

  @BagComplexProperty("http://xmlns.com/foaf/0.1/account")
  public Collection<OnlineAccount> getOnlineAccount(){
    return this.onlineAccount;
  }

  @BagComplexProperty("http://xmlns.com/foaf/0.1/account")
  public void setOnlineAccount(Collection<OnlineAccount> onlineAccount){
    this.onlineAccount = onlineAccount;
  }

  public void addOnlineAccount(OnlineAccount onlineAccountP){
    if(this.onlineAccount == null)
      setOnlineAccount( new HashSet<OnlineAccount>() );
    this.onlineAccount.add(onlineAccountP);
  }

  @IndirectProperty({"http://www.w3.org/2003/01/geo/wgs84_pos#long","http://www.w3.org/2003/01/geo/wgs84_pos#lat"})
  @BagURIProperty("http://www.w3.org/ns/org#hasSite")
  public Collection<Place> getSite(){
    if(! this.isDirectReferencesResolved())
      this.resolveDirectReferences();
    return this.site;
  }

  @BagURIProperty("http://www.w3.org/ns/org#hasSite")
  public void setSite(Collection<Place> site){
    if( site != null ){
      for( GabotoEntity _entity : site)
        this.removeMissingReference( _entity.getUri() );
    }

    this.site = site;
  }

  public void addSite(Place site){
    if( site != null )
      this.removeMissingReference( site.getUri() );
    if(this.site == null )
      this.site = new HashSet<Place>();
    this.site.add(site);
  }

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
  @SimpleURIProperty("http://www.w3.org/ns/org#hasPrimarySite")
  public Place getPrimarySite(){
    if(! this.isDirectReferencesResolved())
      this.resolveDirectReferences();
    return this.primarySite;
  }

  @SimpleURIProperty("http://www.w3.org/ns/org#hasPrimarySite")
  public void setPrimarySite(Place primarySite){
    if( primarySite != null )
      this.removeMissingReference( primarySite.getUri() );
    this.primarySite = primarySite;
  }

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

  @SimpleURIProperty("http://purl.org/dc/terms/isPartOf")
  public Organization getParent(){
    if(! this.isDirectReferencesResolved())
      this.resolveDirectReferences();
    return this.parent;
  }

  @SimpleURIProperty("http://purl.org/dc/terms/isPartOf")
  public void setParent(Organization parent){
    if( parent != null )
      this.removeMissingReference( parent.getUri() );
    this.parent = parent;
  }

  @UnstoredProperty({"http://ns.ox.ac.uk/namespace/gaboto/kml/2009/03/owl#parent"})
  @IndirectProperty({"http://www.w3.org/2003/01/geo/wgs84_pos#long","http://www.w3.org/2003/01/geo/wgs84_pos#lat"})
  @SimpleURIProperty("http://www.w3.org/ns/org#subOrganizationOf")
  public Organization getSubOrganizationOf(){
    if(! this.isDirectReferencesResolved())
      this.resolveDirectReferences();
    return this.subOrganizationOf;
  }

  @SimpleURIProperty("http://www.w3.org/ns/org#subOrganizationOf")
  public void setSubOrganizationOf(Organization subOrganizationOf){
    if( subOrganizationOf != null )
      this.removeMissingReference( subOrganizationOf.getUri() );
    this.subOrganizationOf = subOrganizationOf;
  }

  @ResourceProperty("http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#hasWeblearn")
  public String getWeblearn(){
    return this.weblearn;
  }

  @ResourceProperty("http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#hasWeblearn")
  public void setWeblearn(String weblearn){
    this.weblearn = weblearn;
  }

  @SimpleURIProperty("http://xmlns.com/foaf/0.1/logo")
  public Image getLogo(){
    if(! this.isDirectReferencesResolved())
      this.resolveDirectReferences();
    return this.logo;
  }

  @SimpleURIProperty("http://xmlns.com/foaf/0.1/logo")
  public void setLogo(Image logo){
    if( logo != null )
      this.removeMissingReference( logo.getUri() );
    this.logo = logo;
  }

  @SimpleLiteralProperty(
    value = "http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#hasOLISAlephCode",
    datatypeType = "javaprimitive",
    javaType = "String"
  )
  public String getOLISAlephCode(){
    return this.oLISAlephCode;
  }

  @SimpleLiteralProperty(
    value = "http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#hasOLISAlephCode",
    datatypeType = "javaprimitive",
    javaType = "String"
  )
  public void setOLISAlephCode(String oLISAlephCode){
    this.oLISAlephCode = oLISAlephCode;
  }

  @PassiveProperty(
    uri = "http://purl.org/dc/terms/isPartOf",
    entity = "Organization"
  )
  public Collection<Organization> getHasChildren(){
    if(! isPassiveEntitiesLoaded() )
      loadPassiveEntities();
    return this.hasChildren;
  }

  @PassiveProperty(
    uri = "http://purl.org/dc/terms/isPartOf",
    entity = "Organization"
  )
  private void setHasChildren(Collection<Organization> hasChildren){
    this.hasChildren = hasChildren;
  }

  private void addHasChildren(Organization hasChildrenP){
    if(this.hasChildren == null)
      setHasChildren( new HashSet<Organization>() );
    this.hasChildren.add(hasChildrenP);
  }

  @PassiveProperty(
    uri = "http://www.w3.org/ns/org#subOrganizationOf",
    entity = "Organization"
  )
  public Collection<Organization> getSubOrganizations(){
    if(! isPassiveEntitiesLoaded() )
      loadPassiveEntities();
    return this.subOrganizations;
  }

  @PassiveProperty(
    uri = "http://www.w3.org/ns/org#subOrganizationOf",
    entity = "Organization"
  )
  private void setSubOrganizations(Collection<Organization> subOrganizations){
    this.subOrganizations = subOrganizations;
  }

  private void addSubOrganization(Organization subOrganizationP){
    if(this.subOrganizations == null)
      setSubOrganizations( new HashSet<Organization>() );
    this.subOrganizations.add(subOrganizationP);
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
        return "http://www.w3.org/ns/org#Organization";
      }

      public String getUri() {
        return "http://purl.org/dc/terms/isPartOf";
      }

      public int getCollectionType() {
        return EntityPool.PASSIVE_PROPERTY_COLLECTION_TYPE_NONE;
      }

      public void passiveEntityLoaded(GabotoEntity entity) {
        addHasChildren((Organization)entity);
      }
    });
    requests.add(new PassiveEntitiesRequest(){
      public String getType() {
        return "http://www.w3.org/ns/org#Organization";
      }

      public String getUri() {
        return "http://www.w3.org/ns/org#subOrganizationOf";
      }

      public int getCollectionType() {
        return EntityPool.PASSIVE_PROPERTY_COLLECTION_TYPE_NONE;
      }

      public void passiveEntityLoaded(GabotoEntity entity) {
        addSubOrganization((Organization)entity);
      }
    });
    return requests;
  }


  public void loadFromSnapshot(Resource res, GabotoSnapshot snapshot, EntityPool pool) {
    super.loadFromSnapshot(res, snapshot, pool);
    Statement stmt;

    // Load BAG_COMPLEX_PROPERTY telephoneNumbers
    {
        StmtIterator stmts = res.listProperties(snapshot.getProperty("http://www.w3.org/2006/vcard/ns#tel"));
        while (stmts.hasNext()) {
            RDFNode node = stmts.next().getObject();
            if(! node.isAnon())
              throw new IllegalArgumentException("node should be a blank node");

            Resource anon_res = (Resource) node;
            String type = anon_res.getProperty(snapshot.getProperty("http://www.w3.org/1999/02/22-rdf-syntax-ns#type")).getObject().toString();
            Tel prop;
            try {
                prop = (Tel) (new OxpointsGabotoOntologyLookup()).getBeanClassFor(type).newInstance();
            } catch (InstantiationException e) {
                throw new GabotoRuntimeException();
            } catch (IllegalAccessException e) {
                throw new GabotoRuntimeException();
            }
            prop.loadFromResource(anon_res, snapshot, pool);
            addTelephoneNumber(prop);
        }

    }

    // Load BAG_COMPLEX_PROPERTY onlineAccount
    {
        StmtIterator stmts = res.listProperties(snapshot.getProperty("http://xmlns.com/foaf/0.1/account"));
        while (stmts.hasNext()) {
            RDFNode node = stmts.next().getObject();
            if(! node.isAnon())
              throw new IllegalArgumentException("node should be a blank node");

            Resource anon_res = (Resource) node;
            String type = anon_res.getProperty(snapshot.getProperty("http://www.w3.org/1999/02/22-rdf-syntax-ns#type")).getObject().toString();
            OnlineAccount prop;
            try {
                prop = (OnlineAccount) (new OxpointsGabotoOntologyLookup()).getBeanClassFor(type).newInstance();
            } catch (InstantiationException e) {
                throw new GabotoRuntimeException();
            } catch (IllegalAccessException e) {
                throw new GabotoRuntimeException();
            }
            prop.loadFromResource(anon_res, snapshot, pool);
            addOnlineAccount(prop);
        }

    }

    // Load BAG_URI_PROPERTY site
    {
        StmtIterator stmts = res.listProperties(snapshot.getProperty("http://www.w3.org/ns/org#hasSite"));
        while (stmts.hasNext()) {
            RDFNode node = stmts.next().getObject();
            if(! node.isResource())
              throw new IllegalArgumentException("node should be a resource");

            Resource missingReference = (Resource)node;
            EntityExistsCallback callback = new EntityExistsCallback(){
              public void entityExists(EntityPool p, GabotoEntity entity) {
                addSite((Place) entity);
            }
        };
        this.addMissingReference(missingReference, callback);
      }
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

    // Load SIMPLE_URI_PROPERTY primarySite
    stmt = res.getProperty(snapshot.getProperty("http://www.w3.org/ns/org#hasPrimarySite"));
    if(stmt != null && stmt.getObject().isResource()){
      Resource missingReference = (Resource)stmt.getObject();
      EntityExistsCallback callback = new EntityExistsCallback(){
        public void entityExists(EntityPool p, GabotoEntity entity) {
          setPrimarySite((Place)entity);
        }
      };
      this.addMissingReference(missingReference, callback);
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
          setParent((Organization)entity);
        }
      };
      this.addMissingReference(missingReference, callback);
    }

    // Load SIMPLE_URI_PROPERTY subOrganizationOf
    stmt = res.getProperty(snapshot.getProperty("http://www.w3.org/ns/org#subOrganizationOf"));
    if(stmt != null && stmt.getObject().isResource()){
      Resource missingReference = (Resource)stmt.getObject();
      EntityExistsCallback callback = new EntityExistsCallback(){
        public void entityExists(EntityPool p, GabotoEntity entity) {
          setSubOrganizationOf((Organization)entity);
        }
      };
      this.addMissingReference(missingReference, callback);
    }

    // Load SIMPLE_RESOURCE_PROPERTY weblearn
    stmt = res.getProperty(snapshot.getProperty("http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#hasWeblearn"));
    if(stmt != null && stmt.getObject().isURIResource()){
      this.setWeblearn(((Resource) stmt.getObject()).getURI());
    }

    // Load SIMPLE_URI_PROPERTY logo
    stmt = res.getProperty(snapshot.getProperty("http://xmlns.com/foaf/0.1/logo"));
    if(stmt != null && stmt.getObject().isResource()){
      Resource missingReference = (Resource)stmt.getObject();
      EntityExistsCallback callback = new EntityExistsCallback(){
        public void entityExists(EntityPool p, GabotoEntity entity) {
          setLogo((Image)entity);
        }
      };
      this.addMissingReference(missingReference, callback);
    }

    // Load SIMPLE_LITERAL_PROPERTY oLISAlephCode
    stmt = res.getProperty(snapshot.getProperty("http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#hasOLISAlephCode"));
    if(stmt != null && stmt.getObject().isLiteral())
      this.setOLISAlephCode(((Literal)stmt.getObject()).getString());

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