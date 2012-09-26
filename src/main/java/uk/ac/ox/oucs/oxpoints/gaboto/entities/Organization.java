package uk.ac.ox.oucs.oxpoints.gaboto.entities;


import com.hp.hpl.jena.rdf.model.Literal;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.Statement;

import java.lang.reflect.Method;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.gaboto.GabotoSnapshot;

import net.sf.gaboto.node.GabotoEntity;

import net.sf.gaboto.node.annotation.ResourceProperty;
import net.sf.gaboto.node.annotation.SimpleLiteralProperty;

import net.sf.gaboto.node.pool.EntityPool;

import uk.ac.ox.oucs.oxpoints.gaboto.entities.Agent;


/**
 * Gaboto generated Entity.
 * @see net.sf.gaboto.generation.GabotoGenerator
 */
public class Organization extends Agent {
  protected String oUCSCode;
  protected String financeCode;
  protected String itHomepage;
  protected String weblearn;


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