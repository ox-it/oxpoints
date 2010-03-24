package uk.ac.ox.oucs.oxpoints.gaboto.beans;


import com.hp.hpl.jena.rdf.model.Literal;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.Statement;

import net.sf.gaboto.GabotoSnapshot;

import net.sf.gaboto.node.GabotoBean;

import net.sf.gaboto.node.annotation.SimpleLiteralProperty;

import net.sf.gaboto.node.pool.EntityPool;


/**
 * Gaboto generated bean.
 * @see net.sf.gaboto.generation.GabotoGenerator
 */
public class Tel extends GabotoBean {
  protected String value;

  @Override
  public String getType(){
    return "http://www.w3.org/2006/vcard/ns#Tel";
  }

  @SimpleLiteralProperty(
    value = "http://www.w3.org/1999/02/22-rdf-syntax-ns#value",
    datatypeType = "javaprimitive",
    javaType = "String"
  )
  public String getValue(){
    return this.value;
  }

  @SimpleLiteralProperty(
    value = "http://www.w3.org/1999/02/22-rdf-syntax-ns#value",
    datatypeType = "javaprimitive",
    javaType = "String"
  )
  public void setValue(String value){
    this.value = value;
  }





  public void loadFromResource(Resource res, GabotoSnapshot snapshot, EntityPool pool) {
    super.loadFromResource(res, snapshot, pool);
    Statement stmt;

    // Load SIMPLE_LITERAL_PROPERTY value
    stmt = res.getProperty(snapshot.getProperty("http://www.w3.org/1999/02/22-rdf-syntax-ns#value"));
    if(stmt != null && stmt.getObject().isLiteral())
      this.setValue(((Literal)stmt.getObject()).getString());

  }
  public String toString() {
    return this.value ;
  }
}