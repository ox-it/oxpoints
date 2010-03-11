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

import net.sf.gaboto.node.annotation.BagLiteralProperty;
import net.sf.gaboto.node.annotation.SimpleLiteralProperty;

import net.sf.gaboto.node.pool.EntityPool;

import uk.ac.ox.oucs.oxpoints.gaboto.entities.Place;


/**
 * Gaboto generated Entity.
 * @see net.sf.gaboto.generation.GabotoGenerator
 */
public class Portal extends Place {
  private Collection<String> dcType;
  private Collection<String> format;
  private String extent;
  private Integer steps;


  private static Map<String, List<Method>> indirectPropertyLookupTable;
  static{
    indirectPropertyLookupTable = new HashMap<String, List<Method>>();
  }

  @Override
  public String getType(){
    return "http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#Portal";
  }

  @BagLiteralProperty(
    value = "http://purl.org/dc/elements/1.1/type",
    datatypeType = "javaprimitive",
    javaType = "String"
  )
  public Collection<String> getDcType(){
    return this.dcType;
  }

  @BagLiteralProperty(
    value = "http://purl.org/dc/elements/1.1/type",
    datatypeType = "javaprimitive",
    javaType = "String"
  )
  public void setDcType(Collection<String> dcType){
    this.dcType = dcType;
  }

  public void addDcType(String dcTypeP){
    if(this.dcType == null)
      setDcType( new HashSet<String>() );
    this.dcType.add(dcTypeP);
  }

  @BagLiteralProperty(
    value = "http://purl.org/dc/elements/1.1/format",
    datatypeType = "javaprimitive",
    javaType = "String"
  )
  public Collection<String> getFormat(){
    return this.format;
  }

  @BagLiteralProperty(
    value = "http://purl.org/dc/elements/1.1/format",
    datatypeType = "javaprimitive",
    javaType = "String"
  )
  public void setFormat(Collection<String> format){
    this.format = format;
  }

  public void addFormat(String formatP){
    if(this.format == null)
      setFormat( new HashSet<String>() );
    this.format.add(formatP);
  }

  @SimpleLiteralProperty(
    value = "http://purl.org/dc/elements/1.1/extent",
    datatypeType = "javaprimitive",
    javaType = "String"
  )
  public String getExtent(){
    return this.extent;
  }

  @SimpleLiteralProperty(
    value = "http://purl.org/dc/elements/1.1/extent",
    datatypeType = "javaprimitive",
    javaType = "String"
  )
  public void setExtent(String extent){
    this.extent = extent;
  }

  @SimpleLiteralProperty(
    value = "http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#steps",
    datatypeType = "javaprimitive",
    javaType = "Integer"
  )
  public Integer getSteps(){
    return this.steps;
  }

  @SimpleLiteralProperty(
    value = "http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#steps",
    datatypeType = "javaprimitive",
    javaType = "Integer"
  )
  public void setSteps(Integer steps){
    this.steps = steps;
  }







  public void loadFromSnapshot(Resource res, GabotoSnapshot snapshot, EntityPool pool) {
    super.loadFromSnapshot(res, snapshot, pool);
    Statement stmt;

    // Load BAG_LITERAL_PROPERTY dcType
    {
        StmtIterator stmts = res.listProperties(snapshot.getProperty("http://purl.org/dc/elements/1.1/type"));
        while (stmts.hasNext()) {
            RDFNode node = stmts.next().getObject();
            if(! node.isLiteral())
              throw new IllegalArgumentException("node should be a literal");

            addDcType(((Literal)node).getString());
        }
    }

    // Load BAG_LITERAL_PROPERTY format
    {
        StmtIterator stmts = res.listProperties(snapshot.getProperty("http://purl.org/dc/elements/1.1/format"));
        while (stmts.hasNext()) {
            RDFNode node = stmts.next().getObject();
            if(! node.isLiteral())
              throw new IllegalArgumentException("node should be a literal");

            addFormat(((Literal)node).getString());
        }
    }

    // Load SIMPLE_LITERAL_PROPERTY extent
    stmt = res.getProperty(snapshot.getProperty("http://purl.org/dc/elements/1.1/extent"));
    if(stmt != null && stmt.getObject().isLiteral())
      this.setExtent(((Literal)stmt.getObject()).getString());

    // Load SIMPLE_LITERAL_PROPERTY steps
    stmt = res.getProperty(snapshot.getProperty("http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#steps"));
    if(stmt != null && stmt.getObject().isLiteral())
      this.setSteps(((Literal)stmt.getObject()).getInt());

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