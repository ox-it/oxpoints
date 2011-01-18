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
import net.sf.gaboto.node.annotation.SimpleURIProperty;

import net.sf.gaboto.node.pool.EntityExistsCallback;
import net.sf.gaboto.node.pool.EntityPool;

import uk.ac.ox.oucs.oxpoints.gaboto.entities.OxpEntity;


/**
 * Gaboto generated Entity.
 * @see net.sf.gaboto.generation.GabotoGenerator
 */
public class Measure extends OxpEntity {
  protected String measures;
  protected Collection<SpatialThing> supplies;
  protected Measure downstreamOf;
  protected String measureIdentifier;


  private static Map<String, List<Method>> indirectPropertyLookupTable;
  static{
    indirectPropertyLookupTable = new HashMap<String, List<Method>>();
  }

  @Override
  public String getType(){
    return "http://purl.org/meter/Measure";
  }

  @ResourceProperty("http://purl.org/meter/measures")
  public String getMeasures(){
    return this.measures;
  }

  @ResourceProperty("http://purl.org/meter/measures")
  public void setMeasures(String measures){
    this.measures = measures;
  }

  @BagURIProperty("http://purl.org/meter/supplies")
  public Collection<SpatialThing> getSupplies(){
    if(! this.isDirectReferencesResolved())
      this.resolveDirectReferences();
    return this.supplies;
  }

  @BagURIProperty("http://purl.org/meter/supplies")
  public void setSupplies(Collection<SpatialThing> supplies){
    if( supplies != null ){
      for( GabotoEntity _entity : supplies)
        this.removeMissingReference( _entity.getUri() );
    }

    this.supplies = supplies;
  }

  public void addSupplie(SpatialThing supplie){
    if( supplie != null )
      this.removeMissingReference( supplie.getUri() );
    if(this.supplies == null )
      this.supplies = new HashSet<SpatialThing>();
    this.supplies.add(supplie);
  }

  @SimpleURIProperty("http://purl.org/meter/downstreamOf")
  public Measure getDownstreamOf(){
    if(! this.isDirectReferencesResolved())
      this.resolveDirectReferences();
    return this.downstreamOf;
  }

  @SimpleURIProperty("http://purl.org/meter/downstreamOf")
  public void setDownstreamOf(Measure downstreamOf){
    if( downstreamOf != null )
      this.removeMissingReference( downstreamOf.getUri() );
    this.downstreamOf = downstreamOf;
  }

  @SimpleLiteralProperty(
    value = "http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#measureIdentifier",
    datatypeType = "javaprimitive",
    javaType = "String"
  )
  public String getMeasureIdentifier(){
    return this.measureIdentifier;
  }

  @SimpleLiteralProperty(
    value = "http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#measureIdentifier",
    datatypeType = "javaprimitive",
    javaType = "String"
  )
  public void setMeasureIdentifier(String measureIdentifier){
    this.measureIdentifier = measureIdentifier;
  }







  public void loadFromSnapshot(Resource res, GabotoSnapshot snapshot, EntityPool pool) {
    super.loadFromSnapshot(res, snapshot, pool);
    Statement stmt;

    // Load SIMPLE_RESOURCE_PROPERTY measures
    stmt = res.getProperty(snapshot.getProperty("http://purl.org/meter/measures"));
    if(stmt != null && stmt.getObject().isURIResource()){
      this.setMeasures(((Resource) stmt.getObject()).getURI());
    }

    // Load BAG_URI_PROPERTY supplies
    {
        StmtIterator stmts = res.listProperties(snapshot.getProperty("http://purl.org/meter/supplies"));
        while (stmts.hasNext()) {
            RDFNode node = stmts.next().getObject();
            if(! node.isResource())
              throw new IllegalArgumentException("node should be a resource");

            Resource missingReference = (Resource)node;
            EntityExistsCallback callback = new EntityExistsCallback(){
              public void entityExists(EntityPool p, GabotoEntity entity) {
                addSupplie((SpatialThing) entity);
            }
        };
        this.addMissingReference(missingReference, callback);
      }
    }

    // Load SIMPLE_URI_PROPERTY downstreamOf
    stmt = res.getProperty(snapshot.getProperty("http://purl.org/meter/downstreamOf"));
    if(stmt != null && stmt.getObject().isResource()){
      Resource missingReference = (Resource)stmt.getObject();
      EntityExistsCallback callback = new EntityExistsCallback(){
        public void entityExists(EntityPool p, GabotoEntity entity) {
          setDownstreamOf((Measure)entity);
        }
      };
      this.addMissingReference(missingReference, callback);
    }

    // Load SIMPLE_LITERAL_PROPERTY measureIdentifier
    stmt = res.getProperty(snapshot.getProperty("http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#measureIdentifier"));
    if(stmt != null && stmt.getObject().isLiteral())
      this.setMeasureIdentifier(((Literal)stmt.getObject()).getString());

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