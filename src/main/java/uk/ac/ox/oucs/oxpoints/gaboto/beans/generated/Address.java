package uk.ac.ox.oucs.oxpoints.gaboto.beans.generated;


import com.hp.hpl.jena.rdf.model.Literal;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.Statement;

import org.oucs.gaboto.beans.GabotoBean;
import org.oucs.gaboto.entities.pool.GabotoEntityPool;

import org.oucs.gaboto.entities.utils.SimpleLiteralProperty;

import org.oucs.gaboto.model.GabotoSnapshot;


/**
 * Gaboto generated bean.
 * @see net.sf.gaboto.generation.GabotoGenerator#generateBean.
 */
public class Address extends GabotoBean {
  private String postCode;
  private String streetAddress;

  @Override
  public String getType(){
    return "http://nwalsh.com/rdf/vCard#Address";
  }

  @SimpleLiteralProperty(
    value = "http://nwalsh.com/rdf/vCard#postal_code",
    datatypeType = "javaprimitive",
    javaType = "String"
  )
  public String getPostCode(){
    return this.postCode;
  }

  @SimpleLiteralProperty(
    value = "http://nwalsh.com/rdf/vCard#postal_code",
    datatypeType = "javaprimitive",
    javaType = "String"
  )
  public void setPostCode(String postCode){
    this.postCode = postCode;
  }

  @SimpleLiteralProperty(
    value = "http://nwalsh.com/rdf/vCard#street_address",
    datatypeType = "javaprimitive",
    javaType = "String"
  )
  public String getStreetAddress(){
    return this.streetAddress;
  }

  @SimpleLiteralProperty(
    value = "http://nwalsh.com/rdf/vCard#street_address",
    datatypeType = "javaprimitive",
    javaType = "String"
  )
  public void setStreetAddress(String streetAddress){
    this.streetAddress = streetAddress;
  }





  public void loadFromResource(Resource res, GabotoSnapshot snapshot, GabotoEntityPool pool) {
    super.loadFromResource(res, snapshot, pool);
    Statement stmt;

    // Load SIMPLE_LITERAL_PROPERTY postCode
    stmt = res.getProperty(snapshot.getProperty("http://nwalsh.com/rdf/vCard#postal_code"));
    if(stmt != null && stmt.getObject().isLiteral())
      this.setPostCode(((Literal)stmt.getObject()).getString());

    // Load SIMPLE_LITERAL_PROPERTY streetAddress
    stmt = res.getProperty(snapshot.getProperty("http://nwalsh.com/rdf/vCard#street_address"));
    if(stmt != null && stmt.getObject().isLiteral())
      this.setStreetAddress(((Literal)stmt.getObject()).getString());

  }
  public String toString() {
    return "[" + this.postCode + ", " + this.streetAddress + "]";
  }
}