package uk.ac.ox.oucs.oxpoints.gaboto.entities;


import com.hp.hpl.jena.rdf.model.Literal;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.Statement;
import com.hp.hpl.jena.rdf.model.StmtIterator;

import java.lang.reflect.Method;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import net.sf.gaboto.GabotoRuntimeException;
import net.sf.gaboto.GabotoSnapshot;

import net.sf.gaboto.node.GabotoEntity;

import net.sf.gaboto.node.annotation.BagComplexProperty;
import net.sf.gaboto.node.annotation.BagLiteralProperty;
import net.sf.gaboto.node.annotation.BagURIProperty;
import net.sf.gaboto.node.annotation.ComplexProperty;
import net.sf.gaboto.node.annotation.PassiveProperty;
import net.sf.gaboto.node.annotation.SimpleLiteralProperty;
import net.sf.gaboto.node.annotation.SimpleURIProperty;
import net.sf.gaboto.node.annotation.StaticProperty;

import net.sf.gaboto.node.pool.EntityExistsCallback;
import net.sf.gaboto.node.pool.EntityPool;
import net.sf.gaboto.node.pool.PassiveEntitiesRequest;

import uk.ac.ox.oucs.oxpoints.gaboto.OxpointsGabotoOntologyLookup;

import uk.ac.ox.oucs.oxpoints.gaboto.beans.Address;
import uk.ac.ox.oucs.oxpoints.gaboto.beans.SpaceConfiguration;

import uk.ac.ox.oucs.oxpoints.gaboto.entities.SpatialThing;


/**
 * Gaboto generated Entity.
 * @see net.sf.gaboto.generation.GabotoGenerator
 */
public class Place extends SpatialThing {
  protected String oBNCode;
  protected Address address;
  protected Place reception;
  protected Float floor;
  protected String mapLabel;
  protected Collection<String> osmId;
  protected Collection<OnlineAccount> onlineAccount;
  protected Collection<SpaceConfiguration> spaceConfiguration;
  protected Collection<SpaceConfiguration> primarySpaceConfiguration;
  private Collection<Agent> occupiedBy;


  private static Map<String, List<Method>> indirectPropertyLookupTable;
  static{
    indirectPropertyLookupTable = new HashMap<String, List<Method>>();
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

  @ComplexProperty("http://www.w3.org/2006/vcard/ns#adr")
  public Address getAddress(){
    return this.address;
  }

  @ComplexProperty("http://www.w3.org/2006/vcard/ns#adr")
  public void setAddress(Address address){
    this.address = address;
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

  @SimpleLiteralProperty(
    value = "http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#mapLabel",
    datatypeType = "javaprimitive",
    javaType = "String"
  )
  public String getMapLabel(){
    return this.mapLabel;
  }

  @SimpleLiteralProperty(
    value = "http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#mapLabel",
    datatypeType = "javaprimitive",
    javaType = "String"
  )
  public void setMapLabel(String mapLabel){
    this.mapLabel = mapLabel;
  }

  @BagLiteralProperty(
    value = "http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#hasOSMIdentifier",
    datatypeType = "javaprimitive",
    javaType = "String"
  )
  public Collection<String> getOsmId(){
    return this.osmId;
  }

  @BagLiteralProperty(
    value = "http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#hasOSMIdentifier",
    datatypeType = "javaprimitive",
    javaType = "String"
  )
  public void setOsmId(Collection<String> osmId){
    this.osmId = osmId;
  }

  public void addOsmId(String osmIdP){
    if(this.osmId == null)
      setOsmId( new HashSet<String>() );
    this.osmId.add(osmIdP);
  }

  @BagURIProperty("http://xmlns.com/foaf/0.1/account")
  public Collection<OnlineAccount> getOnlineAccount(){
    if(! this.isDirectReferencesResolved())
      this.resolveDirectReferences();
    return this.onlineAccount;
  }

  @BagURIProperty("http://xmlns.com/foaf/0.1/account")
  public void setOnlineAccount(Collection<OnlineAccount> onlineAccount){
    if( onlineAccount != null ){
      for( GabotoEntity _entity : onlineAccount)
        this.removeMissingReference( _entity.getUri() );
    }

    this.onlineAccount = onlineAccount;
  }

  public void addOnlineAccount(OnlineAccount onlineAccount){
    if( onlineAccount != null )
      this.removeMissingReference( onlineAccount.getUri() );
    if(this.onlineAccount == null )
      this.onlineAccount = new HashSet<OnlineAccount>();
    this.onlineAccount.add(onlineAccount);
  }

  @BagComplexProperty("http://purl.org/openorg/space-configuration/spaceConfiguration")
  public Collection<SpaceConfiguration> getSpaceConfiguration(){
    return this.spaceConfiguration;
  }

  @BagComplexProperty("http://purl.org/openorg/space-configuration/spaceConfiguration")
  public void setSpaceConfiguration(Collection<SpaceConfiguration> spaceConfiguration){
    this.spaceConfiguration = spaceConfiguration;
  }

  public void addSpaceConfiguration(SpaceConfiguration spaceConfigurationP){
    if(this.spaceConfiguration == null)
      setSpaceConfiguration( new HashSet<SpaceConfiguration>() );
    this.spaceConfiguration.add(spaceConfigurationP);
  }

  @BagComplexProperty("http://purl.org/openorg/space-configuration/primarySpaceConfiguration")
  public Collection<SpaceConfiguration> getPrimarySpaceConfiguration(){
    return this.primarySpaceConfiguration;
  }

  @BagComplexProperty("http://purl.org/openorg/space-configuration/primarySpaceConfiguration")
  public void setPrimarySpaceConfiguration(Collection<SpaceConfiguration> primarySpaceConfiguration){
    this.primarySpaceConfiguration = primarySpaceConfiguration;
  }

  public void addPrimarySpaceConfiguration(SpaceConfiguration primarySpaceConfigurationP){
    if(this.primarySpaceConfiguration == null)
      setPrimarySpaceConfiguration( new HashSet<SpaceConfiguration>() );
    this.primarySpaceConfiguration.add(primarySpaceConfigurationP);
  }

  @PassiveProperty(
    uri = "http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#occupies",
    entity = "Agent"
  )
  public Collection<Agent> getOccupiedBy(){
    if(! isPassiveEntitiesLoaded() )
      loadPassiveEntities();
    return this.occupiedBy;
  }

  @PassiveProperty(
    uri = "http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#occupies",
    entity = "Agent"
  )
  private void setOccupiedBy(Collection<Agent> occupiedBy){
    this.occupiedBy = occupiedBy;
  }

  private void addOccupiedBy(Agent occupiedByP){
    if(this.occupiedBy == null)
      setOccupiedBy( new HashSet<Agent>() );
    this.occupiedBy.add(occupiedByP);
  }






	@StaticProperty("http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#fullyQualifiedTitle")
	public String getFullyQualifiedTitle() {
		if (getName() == null || getName().equals(""))
			return null;
			
		Place parent = getParent();
		String title = getName();
		while (parent != null) {
			if (parent.getName() != null && !parent.getName().equals(""))
				title += ", " + parent.getName();
			parent = parent.getParent();
		}
		return title;
	}
	

  public Collection<PassiveEntitiesRequest> getPassiveEntitiesRequest(){
    Collection<PassiveEntitiesRequest> requests = super.getPassiveEntitiesRequest();
    if(requests == null)
      requests = new HashSet<PassiveEntitiesRequest>();
    requests.add(new PassiveEntitiesRequest(){
      public String getType() {
        return "http://xmlns.com/foaf/0.1/Agent";
      }

      public String getUri() {
        return "http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#occupies";
      }

      public int getCollectionType() {
        return EntityPool.PASSIVE_PROPERTY_COLLECTION_TYPE_BAG;
      }

      public void passiveEntityLoaded(GabotoEntity entity) {
        addOccupiedBy((Agent)entity);
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

    // Load SIMPLE_COMPLEX_PROPERTY address
    stmt = res.getProperty(snapshot.getProperty("http://www.w3.org/2006/vcard/ns#adr"));
    if(stmt != null && stmt.getObject().isAnon()){
      Address bean = new Address();
      bean.loadFromResource((Resource)stmt.getObject(), snapshot, pool);
      setAddress(bean);
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

    // Load SIMPLE_LITERAL_PROPERTY floor
    stmt = res.getProperty(snapshot.getProperty("http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#floor"));
    if(stmt != null && stmt.getObject().isLiteral())
      this.setFloor(((Literal)stmt.getObject()).getFloat());

    // Load SIMPLE_LITERAL_PROPERTY mapLabel
    stmt = res.getProperty(snapshot.getProperty("http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#mapLabel"));
    if(stmt != null && stmt.getObject().isLiteral())
      this.setMapLabel(((Literal)stmt.getObject()).getString());

    // Load BAG_LITERAL_PROPERTY osmId
    {
        StmtIterator stmts = res.listProperties(snapshot.getProperty("http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#hasOSMIdentifier"));
        while (stmts.hasNext()) {
            RDFNode node = stmts.next().getObject();
            if(! node.isLiteral())
              throw new IllegalArgumentException("node should be a literal");

            addOsmId(((Literal)node).getString());
        }
    }

    // Load BAG_URI_PROPERTY onlineAccount
    {
        StmtIterator stmts = res.listProperties(snapshot.getProperty("http://xmlns.com/foaf/0.1/account"));
        while (stmts.hasNext()) {
            RDFNode node = stmts.next().getObject();
            if(! node.isResource())
              throw new IllegalArgumentException("node should be a resource");

            Resource missingReference = (Resource)node;
            EntityExistsCallback callback = new EntityExistsCallback(){
              public void entityExists(EntityPool p, GabotoEntity entity) {
                addOnlineAccount((OnlineAccount) entity);
            }
        };
        this.addMissingReference(missingReference, callback);
      }
    }

    // Load BAG_COMPLEX_PROPERTY spaceConfiguration
    {
        StmtIterator stmts = res.listProperties(snapshot.getProperty("http://purl.org/openorg/space-configuration/spaceConfiguration"));
        while (stmts.hasNext()) {
            RDFNode node = stmts.next().getObject();
            if(! node.isAnon())
              throw new IllegalArgumentException("node should be a blank node");

            Resource anon_res = (Resource) node;
            String type = anon_res.getProperty(snapshot.getProperty("http://www.w3.org/1999/02/22-rdf-syntax-ns#type")).getObject().toString();
            SpaceConfiguration prop;
            try {
                prop = (SpaceConfiguration) (new OxpointsGabotoOntologyLookup()).getBeanClassFor(type).newInstance();
            } catch (InstantiationException e) {
                throw new GabotoRuntimeException();
            } catch (IllegalAccessException e) {
                throw new GabotoRuntimeException();
            }
            prop.loadFromResource(anon_res, snapshot, pool);
            addSpaceConfiguration(prop);
        }

    }

    // Load BAG_COMPLEX_PROPERTY primarySpaceConfiguration
    {
        StmtIterator stmts = res.listProperties(snapshot.getProperty("http://purl.org/openorg/space-configuration/primarySpaceConfiguration"));
        while (stmts.hasNext()) {
            RDFNode node = stmts.next().getObject();
            if(! node.isAnon())
              throw new IllegalArgumentException("node should be a blank node");

            Resource anon_res = (Resource) node;
            String type = anon_res.getProperty(snapshot.getProperty("http://www.w3.org/1999/02/22-rdf-syntax-ns#type")).getObject().toString();
            SpaceConfiguration prop;
            try {
                prop = (SpaceConfiguration) (new OxpointsGabotoOntologyLookup()).getBeanClassFor(type).newInstance();
            } catch (InstantiationException e) {
                throw new GabotoRuntimeException();
            } catch (IllegalAccessException e) {
                throw new GabotoRuntimeException();
            }
            prop.loadFromResource(anon_res, snapshot, pool);
            addPrimarySpaceConfiguration(prop);
        }

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