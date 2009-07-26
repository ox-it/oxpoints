package uk.ac.ox.oucs.oxpoints.gaboto.beans;


import com.hp.hpl.jena.rdf.model.Literal;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.Statement;

import net.sf.gaboto.model.GabotoSnapshot;

import net.sf.gaboto.node.GabotoBean;

import net.sf.gaboto.node.annotation.SimpleLiteralProperty;

import net.sf.gaboto.node.pool.EntityPool;


/**
 * Gaboto generated bean.
 * @see net.sf.gaboto.generation.GabotoGenerator
 */
public class Location extends GabotoBean {
  private String pos;

  @Override
  public String getType(){
    return "http://www.opengis.net/gml/Point";
  }

  @SimpleLiteralProperty(
    value = "http://www.opengis.net/gml/pos",
    datatypeType = "javaprimitive",
    javaType = "String"
  )
  public String getPos(){
    return this.pos;
  }

  @SimpleLiteralProperty(
    value = "http://www.opengis.net/gml/pos",
    datatypeType = "javaprimitive",
    javaType = "String"
  )
  public void setPos(String pos){
    this.pos = pos;
  }




                    
  public Double getLatitude() {
    String p = getPos();
    if (p == null) 
      return null;
    try {
      return Double.valueOf(p.split(" ")[1]);
    } catch (NumberFormatException e) {
      return null;
    }
  }
                     
                
                    
  public Double getLongitude() {
    String p = getPos();
    if (p == null) 
      return null;
    try {
      return Double.valueOf(p.split(" ")[0]);
    } catch (NumberFormatException e) {
      return null;
    }
  }
                    
                

  public void loadFromResource(Resource res, GabotoSnapshot snapshot, EntityPool pool) {
    super.loadFromResource(res, snapshot, pool);
    Statement stmt;

    // Load SIMPLE_LITERAL_PROPERTY pos
    stmt = res.getProperty(snapshot.getProperty("http://www.opengis.net/gml/pos"));
    if(stmt != null && stmt.getObject().isLiteral())
      this.setPos(((Literal)stmt.getObject()).getString());

  }
  public String toString() {
    return this.pos ;
  }
}