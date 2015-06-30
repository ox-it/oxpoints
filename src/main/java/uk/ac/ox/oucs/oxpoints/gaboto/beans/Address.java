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
public class Address extends GabotoBean {
  protected String streetAddress;
  protected String extendedAddress;
  protected String locality;
  protected String postCode;
  protected String country;

  @Override
  public String getType(){
    return "http://www.w3.org/2006/vcard/ns#Address";
  }

  @SimpleLiteralProperty(
    value = "http://www.w3.org/2006/vcard/ns#street-address",
    datatypeType = "javaprimitive",
    javaType = "String"
  )
  public String getStreetAddress(){
    return this.streetAddress;
  }

  @SimpleLiteralProperty(
    value = "http://www.w3.org/2006/vcard/ns#street-address",
    datatypeType = "javaprimitive",
    javaType = "String"
  )
  public void setStreetAddress(String streetAddress){
    this.streetAddress = streetAddress;
  }

  @SimpleLiteralProperty(
    value = "http://www.w3.org/2006/vcard/ns#extended-address",
    datatypeType = "javaprimitive",
    javaType = "String"
  )
  public String getExtendedAddress(){
    return this.extendedAddress;
  }

  @SimpleLiteralProperty(
    value = "http://www.w3.org/2006/vcard/ns#extended-address",
    datatypeType = "javaprimitive",
    javaType = "String"
  )
  public void setExtendedAddress(String extendedAddress){
    this.extendedAddress = extendedAddress;
  }

  @SimpleLiteralProperty(
    value = "http://www.w3.org/2006/vcard/ns#locality",
    datatypeType = "javaprimitive",
    javaType = "String"
  )
  public String getLocality(){
    return this.locality;
  }

  @SimpleLiteralProperty(
    value = "http://www.w3.org/2006/vcard/ns#locality",
    datatypeType = "javaprimitive",
    javaType = "String"
  )
  public void setLocality(String locality){
    this.locality = locality;
  }

  @SimpleLiteralProperty(
    value = "http://www.w3.org/2006/vcard/ns#postal-code",
    datatypeType = "javaprimitive",
    javaType = "String"
  )
  public String getPostCode(){
    return this.postCode;
  }

  @SimpleLiteralProperty(
    value = "http://www.w3.org/2006/vcard/ns#postal-code",
    datatypeType = "javaprimitive",
    javaType = "String"
  )
  public void setPostCode(String postCode){
    this.postCode = postCode;
  }

  @SimpleLiteralProperty(
    value = "http://www.w3.org/2006/vcard/ns#country-name",
    datatypeType = "javaprimitive",
    javaType = "String"
  )
  public String getCountry(){
    return this.country;
  }

  @SimpleLiteralProperty(
    value = "http://www.w3.org/2006/vcard/ns#country-name",
    datatypeType = "javaprimitive",
    javaType = "String"
  )
  public void setCountry(String country){
    this.country = country;
  }





  public void loadFromResource(Resource res, GabotoSnapshot snapshot, EntityPool pool) {
    super.loadFromResource(res, snapshot, pool);
    Statement stmt;

    // Load SIMPLE_LITERAL_PROPERTY streetAddress
    stmt = res.getProperty(snapshot.getProperty("http://www.w3.org/2006/vcard/ns#street-address"));
    if(stmt != null && stmt.getObject().isLiteral())
      this.setStreetAddress(((Literal)stmt.getObject()).getString());

    // Load SIMPLE_LITERAL_PROPERTY extendedAddress
    stmt = res.getProperty(snapshot.getProperty("http://www.w3.org/2006/vcard/ns#extended-address"));
    if(stmt != null && stmt.getObject().isLiteral())
      this.setExtendedAddress(((Literal)stmt.getObject()).getString());

    // Load SIMPLE_LITERAL_PROPERTY locality
    stmt = res.getProperty(snapshot.getProperty("http://www.w3.org/2006/vcard/ns#locality"));
    if(stmt != null && stmt.getObject().isLiteral())
      this.setLocality(((Literal)stmt.getObject()).getString());

    // Load SIMPLE_LITERAL_PROPERTY postCode
    stmt = res.getProperty(snapshot.getProperty("http://www.w3.org/2006/vcard/ns#postal-code"));
    if(stmt != null && stmt.getObject().isLiteral())
      this.setPostCode(((Literal)stmt.getObject()).getString());

    // Load SIMPLE_LITERAL_PROPERTY country
    stmt = res.getProperty(snapshot.getProperty("http://www.w3.org/2006/vcard/ns#country-name"));
    if(stmt != null && stmt.getObject().isLiteral())
      this.setCountry(((Literal)stmt.getObject()).getString());

  }
  public String toString() {
    return this.streetAddress + ", " + this.extendedAddress + ", " + this.locality + ", " + this.postCode + ", " + this.country ;
  }
}