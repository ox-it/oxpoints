package uk.ac.ox.oucs.oxpoints.gaboto.entities;


import com.hp.hpl.jena.rdf.model.Literal;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.Statement;

import java.lang.reflect.Method;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.gaboto.GabotoSnapshot;

import net.sf.gaboto.node.annotation.ResourceProperty;
import net.sf.gaboto.node.annotation.SimpleLiteralProperty;

import net.sf.gaboto.node.pool.EntityPool;

import uk.ac.ox.oucs.oxpoints.gaboto.entities.OxpEntity;


/**
 * Gaboto generated Entity.
 * @see net.sf.gaboto.generation.GabotoGenerator
 */
public class OnlineAccount extends OxpEntity {
  protected String accountServiceHomepage;
  protected String accountName;
  protected String accountProfilePage;


  private static Map<String, List<Method>> indirectPropertyLookupTable;
  static{
    indirectPropertyLookupTable = new HashMap<String, List<Method>>();
  }

  @Override
  public String getType(){
    return "http://xmlns.com/foaf/0.1/OnlineAccount";
  }

  @ResourceProperty("http://xmlns.com/foaf/0.1/accountServiceHomepage")
  public String getAccountServiceHomepage(){
    return this.accountServiceHomepage;
  }

  @ResourceProperty("http://xmlns.com/foaf/0.1/accountServiceHomepage")
  public void setAccountServiceHomepage(String accountServiceHomepage){
    this.accountServiceHomepage = accountServiceHomepage;
  }

  @SimpleLiteralProperty(
    value = "http://xmlns.com/foaf/0.1/accountName",
    datatypeType = "javaprimitive",
    javaType = "String"
  )
  public String getAccountName(){
    return this.accountName;
  }

  @SimpleLiteralProperty(
    value = "http://xmlns.com/foaf/0.1/accountName",
    datatypeType = "javaprimitive",
    javaType = "String"
  )
  public void setAccountName(String accountName){
    this.accountName = accountName;
  }

  @ResourceProperty("http://xmlns.com/foaf/0.1/accountProfilePage")
  public String getAccountProfilePage(){
    return this.accountProfilePage;
  }

  @ResourceProperty("http://xmlns.com/foaf/0.1/accountProfilePage")
  public void setAccountProfilePage(String accountProfilePage){
    this.accountProfilePage = accountProfilePage;
  }







  public void loadFromSnapshot(Resource res, GabotoSnapshot snapshot, EntityPool pool) {
    super.loadFromSnapshot(res, snapshot, pool);
    Statement stmt;

    // Load SIMPLE_RESOURCE_PROPERTY accountServiceHomepage
    stmt = res.getProperty(snapshot.getProperty("http://xmlns.com/foaf/0.1/accountServiceHomepage"));
    if(stmt != null && stmt.getObject().isURIResource()){
      this.setAccountServiceHomepage(((Resource) stmt.getObject()).getURI());
    }

    // Load SIMPLE_LITERAL_PROPERTY accountName
    stmt = res.getProperty(snapshot.getProperty("http://xmlns.com/foaf/0.1/accountName"));
    if(stmt != null && stmt.getObject().isLiteral())
      this.setAccountName(((Literal)stmt.getObject()).getString());

    // Load SIMPLE_RESOURCE_PROPERTY accountProfilePage
    stmt = res.getProperty(snapshot.getProperty("http://xmlns.com/foaf/0.1/accountProfilePage"));
    if(stmt != null && stmt.getObject().isURIResource()){
      this.setAccountProfilePage(((Resource) stmt.getObject()).getURI());
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