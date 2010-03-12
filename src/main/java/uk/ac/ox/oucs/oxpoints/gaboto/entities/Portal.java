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
  private Collection<String> format;
  private String accesses;


  private static Map<String, List<Method>> indirectPropertyLookupTable;
  static{
    indirectPropertyLookupTable = new HashMap<String, List<Method>>();
  }

  @Override
  public String getType(){
    return "http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#Portal";
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
    value = "http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#accesses",
    datatypeType = "javaprimitive",
    javaType = "String"
  )
  public String getAccesses(){
    return this.accesses;
  }

  @SimpleLiteralProperty(
    value = "http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#accesses",
    datatypeType = "javaprimitive",
    javaType = "String"
  )
  public void setAccesses(String accesses){
    this.accesses = accesses;
  }







  public void loadFromSnapshot(Resource res, GabotoSnapshot snapshot, EntityPool pool) {
    super.loadFromSnapshot(res, snapshot, pool);
    Statement stmt;

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

    // Load SIMPLE_LITERAL_PROPERTY accesses
    stmt = res.getProperty(snapshot.getProperty("http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#accesses"));
    if(stmt != null && stmt.getObject().isLiteral())
      this.setAccesses(((Literal)stmt.getObject()).getString());

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