package uk.ac.ox.oucs.oxpoints.gaboto.entities;


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

import net.sf.gaboto.node.pool.EntityExistsCallback;
import net.sf.gaboto.node.pool.EntityPool;

import uk.ac.ox.oucs.oxpoints.gaboto.entities.Object_;


/**
 * Gaboto generated Entity.
 * @see net.sf.gaboto.generation.GabotoGenerator
 */
public class Meter extends Object_ {
  protected Collection<Measure> measure;


  private static Map<String, List<Method>> indirectPropertyLookupTable;
  static{
    indirectPropertyLookupTable = new HashMap<String, List<Method>>();
  }

  @Override
  public String getType(){
    return "http://purl.org/meter/Meter";
  }

  @BagURIProperty("http://purl.org/meter/measure")
  public Collection<Measure> getMeasure(){
    if(! this.isDirectReferencesResolved())
      this.resolveDirectReferences();
    return this.measure;
  }

  @BagURIProperty("http://purl.org/meter/measure")
  public void setMeasure(Collection<Measure> measure){
    if( measure != null ){
      for( GabotoEntity _entity : measure)
        this.removeMissingReference( _entity.getUri() );
    }

    this.measure = measure;
  }

  public void addMeasure(Measure measure){
    if( measure != null )
      this.removeMissingReference( measure.getUri() );
    if(this.measure == null )
      this.measure = new HashSet<Measure>();
    this.measure.add(measure);
  }







  public void loadFromSnapshot(Resource res, GabotoSnapshot snapshot, EntityPool pool) {
    super.loadFromSnapshot(res, snapshot, pool);
    Statement stmt;

    // Load BAG_URI_PROPERTY measure
    {
        StmtIterator stmts = res.listProperties(snapshot.getProperty("http://purl.org/meter/measure"));
        while (stmts.hasNext()) {
            RDFNode node = stmts.next().getObject();
            if(! node.isResource())
              throw new IllegalArgumentException("node should be a resource");

            Resource missingReference = (Resource)node;
            EntityExistsCallback callback = new EntityExistsCallback(){
              public void entityExists(EntityPool p, GabotoEntity entity) {
                addMeasure((Measure) entity);
            }
        };
        this.addMissingReference(missingReference, callback);
      }
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