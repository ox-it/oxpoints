package uk.ac.ox.oucs.oxpoints.gaboto.entities;


import com.hp.hpl.jena.rdf.model.Bag;
import com.hp.hpl.jena.rdf.model.NodeIterator;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.Statement;

import java.lang.reflect.Method;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;


import org.oucs.gaboto.entities.annotations.BagURIProperty;
import org.oucs.gaboto.entities.pool.EntityExistsCallback;
import org.oucs.gaboto.entities.pool.GabotoEntityPool;


import org.oucs.gaboto.model.GabotoSnapshot;
import org.oucs.gaboto.nodes.GabotoEntity;


/**
 * Gaboto generated Entity.
 * @see net.sf.gaboto.generation.GabotoGenerator
 */
abstract public class OxpEntity extends GabotoEntity {
  private Collection<Image> images;


  private static Map<String, List<Method>> indirectPropertyLookupTable;
  static{
    indirectPropertyLookupTable = new HashMap<String, List<Method>>();
  }

  @Override
  public String getType(){
    return "http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#OxpEntity";
  }

  @BagURIProperty("http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#inImage")
  public Collection<Image> getImages(){
    if(! this.isDirectReferencesResolved())
      this.resolveDirectReferences();
    return this.images;
  }

  @BagURIProperty("http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#inImage")
  public void setImages(Collection<Image> images){
    if( images != null ){
      for( GabotoEntity _entity : images)
        this.removeMissingReference( _entity.getUri() );
    }

    this.images = images;
  }

  public void addImage(Image image){
    if( image != null )
      this.removeMissingReference( image.getUri() );
    if(this.images == null )
      this.images = new HashSet<Image>();
    this.images.add(image);
  }







  public void loadFromSnapshot(Resource res, GabotoSnapshot snapshot, GabotoEntityPool pool) {
    super.loadFromSnapshot(res, snapshot, pool);
    Statement stmt;

    // Load BAG_URI_PROPERTY images
    stmt = res.getProperty(snapshot.getProperty("http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#inImage"));
    if(stmt != null && stmt.getObject().isResource() && null != stmt.getBag()){
      Bag bag = stmt.getBag();
      NodeIterator nodeIt = bag.iterator();
      while(nodeIt.hasNext()){
        RDFNode node = nodeIt.nextNode();
        if(! node.isResource())
          throw new IllegalArgumentException("node should be a resource");

        Resource missingReference = (Resource)node;
        EntityExistsCallback callback = new EntityExistsCallback(){
          public void entityExists(GabotoEntityPool p, GabotoEntity entity) {
            addImage((Image) entity);
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