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
public class SpaceConfiguration extends GabotoBean {
  protected int capacity;
  protected String comment;

  @Override
  public String getType(){
    return "http://purl.org/openorg/space-configuration/SpaceConfiguration";
  }

  @SimpleLiteralProperty(
    value = "http://purl.org/openorg/space-configuration/capacity",
    datatypeType = "javaprimitive",
    javaType = "int"
  )
  public int getCapacity(){
    return this.capacity;
  }

  @SimpleLiteralProperty(
    value = "http://purl.org/openorg/space-configuration/capacity",
    datatypeType = "javaprimitive",
    javaType = "int"
  )
  public void setCapacity(int capacity){
    this.capacity = capacity;
  }

  @SimpleLiteralProperty(
    value = "http://www.w3.org/2000/01/rdf-schema#comment",
    datatypeType = "javaprimitive",
    javaType = "String"
  )
  public String getComment(){
    return this.comment;
  }

  @SimpleLiteralProperty(
    value = "http://www.w3.org/2000/01/rdf-schema#comment",
    datatypeType = "javaprimitive",
    javaType = "String"
  )
  public void setComment(String comment){
    this.comment = comment;
  }





  public void loadFromResource(Resource res, GabotoSnapshot snapshot, EntityPool pool) {
    super.loadFromResource(res, snapshot, pool);
    Statement stmt;

    // Load SIMPLE_LITERAL_PROPERTY capacity
    stmt = res.getProperty(snapshot.getProperty("http://purl.org/openorg/space-configuration/capacity"));
    if(stmt != null && stmt.getObject().isLiteral())
      this.setCapacity(((Literal)stmt.getObject()).getInt());

    // Load SIMPLE_LITERAL_PROPERTY comment
    stmt = res.getProperty(snapshot.getProperty("http://www.w3.org/2000/01/rdf-schema#comment"));
    if(stmt != null && stmt.getObject().isLiteral())
      this.setComment(((Literal)stmt.getObject()).getString());

  }
  public String toString() {
    return this.capacity + ", " + this.comment ;
  }
}