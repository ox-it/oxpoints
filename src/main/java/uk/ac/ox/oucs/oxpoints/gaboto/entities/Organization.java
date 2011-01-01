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
import net.sf.gaboto.node.annotation.ComplexProperty;
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

import uk.ac.ox.oucs.oxpoints.gaboto.beans.Address;
import uk.ac.ox.oucs.oxpoints.gaboto.beans.OnlineAccount;
import uk.ac.ox.oucs.oxpoints.gaboto.beans.Tel;

import uk.ac.ox.oucs.oxpoints.gaboto.entities.OxpEntity;


/**
 * Gaboto generated Entity.
 * @see net.sf.gaboto.generation.GabotoGenerator
 */
public class Organization extends OxpEntity {
  protected String oUCSCode;
  protected String financeCode;
  protected Address address;
  protected Collection<Tel> telephoneNumbers;
  protected String homepage;
  protected String itHomepage;
  protected Collection<OnlineAccount> onlineAccount;
  protected Collection<Place> occupiedPlaces;
  protected Place primaryPlace;
  protected Unit parent;
  protected String weblearn;
  protected Image logo;
  private Collection<Unit> hasChildren;


  private static Map<String, List<Method>> indirectPropertyLookupTable;
  static{
    indirectPropertyLookupTable = new HashMap<String, List<Method>>();
    List<Method> list;

    try {
      list = new ArrayList<Method>();
      list.add(Organization.class.getMethod("getPrimaryPlace", (Class<?>[])null));
      list.add(Organization.class.getMethod("getParent", (Class<?>[])null));
      list.add(Organization.class.getMethod("getOccupiedPlaces", (Class<?>[])null));
      indirectPropertyLookupTable.put("http://www.w3.org/2003/01/geo/wgs84_pos#long", list);

      list = new ArrayList<Method>();
      list.add(Organization.class.getMethod("getPrimaryPlace", (Class<?>[])null));
      list.add(Organization.class.getMethod("getParent", (Class<?>[])null));
      list.add(Organization.class.getMethod("getOccupiedPlaces", (Class<?>[])null));
      indirectPropertyLookupTable.put("http://www.w3.org/2003/01/geo/wgs84_pos#lat", list);

    } catch (Exception e) {
      throw new GabotoRuntimeException(e);
    }
  }

  @Override
  public String getType(){
    return "http://www.w3.org/ns/org#Organization";
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
    value = "http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#hasFinanceCode",
    datatypeType = "javaprimitive",
    javaType = "String"
  )
  public String getFinanceCode(){
    return this.financeCode;
  }

  @SimpleLiteralProperty(
    value = "http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#hasFinanceCode",
    datatypeType = "javaprimitive",
    javaType = "String"
  )
  public void setFinanceCode(String financeCode){
    this.financeCode = financeCode;
  }

  @ComplexProperty("http://www.w3.org/2006/vcard/ns#adr")
  public Address getAddress(){
    return this.address;
  }

  @ComplexProperty("http://www.w3.org/2006/vcard/ns#adr")
  public void setAddress(Address address){
    this.address = address;
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

  @ResourceProperty("http://xmlns.com/foaf/0.1/homepage")
  public String getHomepage(){
    return this.homepage;
  }

  @ResourceProperty("http://xmlns.com/foaf/0.1/homepage")
  public void setHomepage(String homepage){
    this.homepage = homepage;
  }

  @ResourceProperty("http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#hasITHomepage")
  public String getItHomepage(){
    return this.itHomepage;
  }

  @ResourceProperty("http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#hasITHomepage")
  public void setItHomepage(String itHomepage){
    this.itHomepage = itHomepage;
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

    // Load SIMPLE_LITERAL_PROPERTY financeCode
    stmt = res.getProperty(snapshot.getProperty("http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#hasFinanceCode"));
    if(stmt != null && stmt.getObject().isLiteral())
      this.setFinanceCode(((Literal)stmt.getObject()).getString());

    // Load SIMPLE_COMPLEX_PROPERTY address
    stmt = res.getProperty(snapshot.getProperty("http://www.w3.org/2006/vcard/ns#adr"));
    if(stmt != null && stmt.getObject().isAnon()){
      Address bean = new Address();
      bean.loadFromResource((Resource)stmt.getObject(), snapshot, pool);
      setAddress(bean);
    }

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

    // Load SIMPLE_RESOURCE_PROPERTY homepage
    stmt = res.getProperty(snapshot.getProperty("http://xmlns.com/foaf/0.1/homepage"));
    if(stmt != null && stmt.getObject().isLiteral()){
      this.setHomepage(((Literal)stmt.getObject()).getLexicalForm());
    }

    // Load SIMPLE_RESOURCE_PROPERTY itHomepage
    stmt = res.getProperty(snapshot.getProperty("http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#hasITHomepage"));
    if(stmt != null && stmt.getObject().isLiteral()){
      this.setItHomepage(((Literal)stmt.getObject()).getLexicalForm());
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

    // Load SIMPLE_RESOURCE_PROPERTY weblearn
    stmt = res.getProperty(snapshot.getProperty("http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#hasWeblearn"));
    if(stmt != null && stmt.getObject().isLiteral()){
      this.setWeblearn(((Literal)stmt.getObject()).getLexicalForm());
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