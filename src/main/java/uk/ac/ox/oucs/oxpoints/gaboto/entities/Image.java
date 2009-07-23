package uk.ac.ox.oucs.oxpoints.gaboto.entities;


import com.hp.hpl.jena.rdf.model.Literal;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.Statement;

import java.lang.reflect.Method;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;


import org.oucs.gaboto.entities.annotations.PassiveProperty;
import org.oucs.gaboto.entities.annotations.SimpleLiteralProperty;
import org.oucs.gaboto.entities.pool.GabotoEntityPool;
import org.oucs.gaboto.entities.pool.PassiveEntitiesRequest;


import org.oucs.gaboto.model.GabotoSnapshot;
import org.oucs.gaboto.nodes.GabotoEntity;

import uk.ac.ox.oucs.oxpoints.gaboto.entities.OxpEntity;


/**
 * Gaboto generated Entity.
 * @see net.sf.gaboto.generation.GabotoGenerator
 */
public class Image extends OxpEntity {
  private String width;
  private String height;
  private Collection<OxpEntity> imageContents;


  private static Map<String, List<Method>> indirectPropertyLookupTable;
  static{
    indirectPropertyLookupTable = new HashMap<String, List<Method>>();
  }

  @Override
  public String getType(){
    return "http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#Image";
  }

  @SimpleLiteralProperty(
    value = "http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#width",
    datatypeType = "javaprimitive",
    javaType = "String"
  )
  public String getWidth(){
    return this.width;
  }

  @SimpleLiteralProperty(
    value = "http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#width",
    datatypeType = "javaprimitive",
    javaType = "String"
  )
  public void setWidth(String width){
    this.width = width;
  }

  @SimpleLiteralProperty(
    value = "http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#height",
    datatypeType = "javaprimitive",
    javaType = "String"
  )
  public String getHeight(){
    return this.height;
  }

  @SimpleLiteralProperty(
    value = "http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#height",
    datatypeType = "javaprimitive",
    javaType = "String"
  )
  public void setHeight(String height){
    this.height = height;
  }

  @PassiveProperty(
    uri = "http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#inImage",
    entity = "OxpEntity"
  )
  public Collection<OxpEntity> getImageContents(){
    if(! isPassiveEntitiesLoaded() )
      loadPassiveEntities();
    return this.imageContents;
  }

  @PassiveProperty(
    uri = "http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#inImage",
    entity = "OxpEntity"
  )
  private void setImageContents(Collection<OxpEntity> imageContents){
    this.imageContents = imageContents;
  }

  private void addImageContent(OxpEntity imageContentP){
    if(this.imageContents == null)
      setImageContents( new HashSet<OxpEntity>() );
    this.imageContents.add(imageContentP);
  }







  public Collection<PassiveEntitiesRequest> getPassiveEntitiesRequest(){
    Collection<PassiveEntitiesRequest> requests = super.getPassiveEntitiesRequest();
    if(requests == null)
      requests = new HashSet<PassiveEntitiesRequest>();
    requests.add(new PassiveEntitiesRequest(){
      public String getType() {
        return "http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#OxpEntity";
      }

      public String getUri() {
        return "http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#inImage";
      }

      public int getCollectionType() {
        return GabotoEntityPool.PASSIVE_PROPERTY_COLLECTION_TYPE_BAG;
      }

      public void passiveEntityLoaded(GabotoEntity entity) {
        addImageContent((OxpEntity)entity);
      }
    });
    return requests;
  }


  public void loadFromSnapshot(Resource res, GabotoSnapshot snapshot, GabotoEntityPool pool) {
    super.loadFromSnapshot(res, snapshot, pool);
    Statement stmt;

    // Load SIMPLE_LITERAL_PROPERTY width
    stmt = res.getProperty(snapshot.getProperty("http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#width"));
    if(stmt != null && stmt.getObject().isLiteral())
      this.setWidth(((Literal)stmt.getObject()).getString());

    // Load SIMPLE_LITERAL_PROPERTY height
    stmt = res.getProperty(snapshot.getProperty("http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#height"));
    if(stmt != null && stmt.getObject().isLiteral())
      this.setHeight(((Literal)stmt.getObject()).getString());

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