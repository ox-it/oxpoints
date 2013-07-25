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

import net.sf.gaboto.GabotoSnapshot;

import net.sf.gaboto.node.GabotoEntity;

import net.sf.gaboto.node.annotation.BagURIProperty;
import net.sf.gaboto.node.annotation.ResourceProperty;
import net.sf.gaboto.node.annotation.SimpleLiteralProperty;

import net.sf.gaboto.node.pool.EntityExistsCallback;
import net.sf.gaboto.node.pool.EntityPool;

import uk.ac.ox.oucs.oxpoints.gaboto.entities.Agent;


/**
 * Gaboto generated Entity.
 * @see net.sf.gaboto.generation.GabotoGenerator
 */
public class Organization extends Agent {
  protected String oUCSCode;
  protected String financeCode;
  protected String divisionCode;
  protected String departmentCode;
  protected String itHomepage;
  protected String weblearn;
  protected String libraryDataId;
  protected Collection<OrganizationalCollaboration> organizationalMemberOf;


  private static Map<String, List<Method>> indirectPropertyLookupTable;
  static{
    indirectPropertyLookupTable = new HashMap<String, List<Method>>();
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

  @SimpleLiteralProperty(
    value = "http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#hasDivisionCode",
    datatypeType = "javaprimitive",
    javaType = "String"
  )
  public String getDivisionCode(){
    return this.divisionCode;
  }

  @SimpleLiteralProperty(
    value = "http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#hasDivisionCode",
    datatypeType = "javaprimitive",
    javaType = "String"
  )
  public void setDivisionCode(String divisionCode){
    this.divisionCode = divisionCode;
  }

  @SimpleLiteralProperty(
    value = "http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#hasDepartmentCode",
    datatypeType = "javaprimitive",
    javaType = "String"
  )
  public String getDepartmentCode(){
    return this.departmentCode;
  }

  @SimpleLiteralProperty(
    value = "http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#hasDepartmentCode",
    datatypeType = "javaprimitive",
    javaType = "String"
  )
  public void setDepartmentCode(String departmentCode){
    this.departmentCode = departmentCode;
  }

  @ResourceProperty("http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#hasITHomepage")
  public String getItHomepage(){
    return this.itHomepage;
  }

  @ResourceProperty("http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#hasITHomepage")
  public void setItHomepage(String itHomepage){
    this.itHomepage = itHomepage;
  }

  @ResourceProperty("http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#hasWeblearn")
  public String getWeblearn(){
    return this.weblearn;
  }

  @ResourceProperty("http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#hasWeblearn")
  public void setWeblearn(String weblearn){
    this.weblearn = weblearn;
  }

  @ResourceProperty("http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#hasLibraryDataId")
  public String getLibraryDataId(){
    return this.libraryDataId;
  }

  @ResourceProperty("http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#hasLibraryDataId")
  public void setLibraryDataId(String libraryDataId){
    this.libraryDataId = libraryDataId;
  }

  @BagURIProperty("http://www.w3.org/ns/org#memberOf")
  public Collection<OrganizationalCollaboration> getOrganizationalMemberOf(){
    if(! this.isDirectReferencesResolved())
      this.resolveDirectReferences();
    return this.organizationalMemberOf;
  }

  @BagURIProperty("http://www.w3.org/ns/org#memberOf")
  public void setOrganizationalMemberOf(Collection<OrganizationalCollaboration> organizationalMemberOf){
    if( organizationalMemberOf != null ){
      for( GabotoEntity _entity : organizationalMemberOf)
        this.removeMissingReference( _entity.getUri() );
    }

    this.organizationalMemberOf = organizationalMemberOf;
  }

  public void addOrganizationalMemberOf(OrganizationalCollaboration organizationalMemberOf){
    if( organizationalMemberOf != null )
      this.removeMissingReference( organizationalMemberOf.getUri() );
    if(this.organizationalMemberOf == null )
      this.organizationalMemberOf = new HashSet<OrganizationalCollaboration>();
    this.organizationalMemberOf.add(organizationalMemberOf);
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

    // Load SIMPLE_LITERAL_PROPERTY divisionCode
    stmt = res.getProperty(snapshot.getProperty("http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#hasDivisionCode"));
    if(stmt != null && stmt.getObject().isLiteral())
      this.setDivisionCode(((Literal)stmt.getObject()).getString());

    // Load SIMPLE_LITERAL_PROPERTY departmentCode
    stmt = res.getProperty(snapshot.getProperty("http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#hasDepartmentCode"));
    if(stmt != null && stmt.getObject().isLiteral())
      this.setDepartmentCode(((Literal)stmt.getObject()).getString());

    // Load SIMPLE_RESOURCE_PROPERTY itHomepage
    stmt = res.getProperty(snapshot.getProperty("http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#hasITHomepage"));
    if(stmt != null && stmt.getObject().isURIResource()){
      this.setItHomepage(((Resource) stmt.getObject()).getURI());
    }

    // Load SIMPLE_RESOURCE_PROPERTY weblearn
    stmt = res.getProperty(snapshot.getProperty("http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#hasWeblearn"));
    if(stmt != null && stmt.getObject().isURIResource()){
      this.setWeblearn(((Resource) stmt.getObject()).getURI());
    }

    // Load SIMPLE_RESOURCE_PROPERTY libraryDataId
    stmt = res.getProperty(snapshot.getProperty("http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#hasLibraryDataId"));
    if(stmt != null && stmt.getObject().isURIResource()){
      this.setLibraryDataId(((Resource) stmt.getObject()).getURI());
    }

    // Load BAG_URI_PROPERTY organizationalMemberOf
    {
        StmtIterator stmts = res.listProperties(snapshot.getProperty("http://www.w3.org/ns/org#memberOf"));
        while (stmts.hasNext()) {
            RDFNode node = stmts.next().getObject();
            if(! node.isResource())
              throw new IllegalArgumentException("node should be a resource");

            Resource missingReference = (Resource)node;
            EntityExistsCallback callback = new EntityExistsCallback(){
              public void entityExists(EntityPool p, GabotoEntity entity) {
                addOrganizationalMemberOf((OrganizationalCollaboration) entity);
            }
        };
        this.addMissingReference(missingReference, callback);
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