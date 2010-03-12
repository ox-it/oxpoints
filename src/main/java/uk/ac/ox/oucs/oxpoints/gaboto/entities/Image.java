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

import net.sf.gaboto.node.annotation.BagLiteralProperty;
import net.sf.gaboto.node.annotation.PassiveProperty;
import net.sf.gaboto.node.annotation.SimpleLiteralProperty;

import net.sf.gaboto.node.pool.EntityPool;
import net.sf.gaboto.node.pool.PassiveEntitiesRequest;

import uk.ac.ox.oucs.oxpoints.gaboto.entities.OxpEntity;


/**
 * Gaboto generated Entity.
 * @see net.sf.gaboto.generation.GabotoGenerator
 */
public class Image extends OxpEntity {
  private String width;
  private String height;
  private String title;
  private Collection<String> dcType;
  private String date;
  private Float latitude;
  private Float longitude;
  private Collection<OxpEntity> imageContents;


  private static Map<String, List<Method>> indirectPropertyLookupTable;
  static{
    indirectPropertyLookupTable = new HashMap<String, List<Method>>();
  }

  @Override
  public String getType(){
    return "http://xmlns.com/foaf/0.1/Image";
  }

  @SimpleLiteralProperty(
    value = "http://www.w3.org/2003/12/exif/ns#width",
    datatypeType = "javaprimitive",
    javaType = "String"
  )
  public String getWidth(){
    return this.width;
  }

  @SimpleLiteralProperty(
    value = "http://www.w3.org/2003/12/exif/ns#width",
    datatypeType = "javaprimitive",
    javaType = "String"
  )
  public void setWidth(String width){
    this.width = width;
  }

  @SimpleLiteralProperty(
    value = "http://www.w3.org/2003/12/exif/ns#height",
    datatypeType = "javaprimitive",
    javaType = "String"
  )
  public String getHeight(){
    return this.height;
  }

  @SimpleLiteralProperty(
    value = "http://www.w3.org/2003/12/exif/ns#height",
    datatypeType = "javaprimitive",
    javaType = "String"
  )
  public void setHeight(String height){
    this.height = height;
  }

  @SimpleLiteralProperty(
    value = "http://purl.org/dc/elements/1.1/title",
    datatypeType = "javaprimitive",
    javaType = "String"
  )
  public String getTitle(){
    return this.title;
  }

  @SimpleLiteralProperty(
    value = "http://purl.org/dc/elements/1.1/title",
    datatypeType = "javaprimitive",
    javaType = "String"
  )
  public void setTitle(String title){
    this.title = title;
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

  @SimpleLiteralProperty(
    value = "http://purl.org/dc/elements/1.1/date",
    datatypeType = "javaprimitive",
    javaType = "String"
  )
  public String getDate(){
    return this.date;
  }

  @SimpleLiteralProperty(
    value = "http://purl.org/dc/elements/1.1/date",
    datatypeType = "javaprimitive",
    javaType = "String"
  )
  public void setDate(String date){
    this.date = date;
  }

  @SimpleLiteralProperty(
    value = "http://www.opengis.net/gml/lat",
    datatypeType = "javaprimitive",
    javaType = "Float"
  )
  public Float getLatitude(){
    return this.latitude;
  }

  @SimpleLiteralProperty(
    value = "http://www.opengis.net/gml/lat",
    datatypeType = "javaprimitive",
    javaType = "Float"
  )
  public void setLatitude(Float latitude){
    this.latitude = latitude;
  }

  @SimpleLiteralProperty(
    value = "http://www.opengis.net/gml/lon",
    datatypeType = "javaprimitive",
    javaType = "Float"
  )
  public Float getLongitude(){
    return this.longitude;
  }

  @SimpleLiteralProperty(
    value = "http://www.opengis.net/gml/lon",
    datatypeType = "javaprimitive",
    javaType = "Float"
  )
  public void setLongitude(Float longitude){
    this.longitude = longitude;
  }

  @PassiveProperty(
    uri = "http://xmlns.com/foaf/0.1/depiction",
    entity = "OxpEntity"
  )
  public Collection<OxpEntity> getImageContents(){
    if(! isPassiveEntitiesLoaded() )
      loadPassiveEntities();
    return this.imageContents;
  }

  @PassiveProperty(
    uri = "http://xmlns.com/foaf/0.1/depiction",
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
        return "http://xmlns.com/foaf/0.1/depiction";
      }

      public int getCollectionType() {
        return EntityPool.PASSIVE_PROPERTY_COLLECTION_TYPE_BAG;
      }

      public void passiveEntityLoaded(GabotoEntity entity) {
        addImageContent((OxpEntity)entity);
      }
    });
    return requests;
  }


  public void loadFromSnapshot(Resource res, GabotoSnapshot snapshot, EntityPool pool) {
    super.loadFromSnapshot(res, snapshot, pool);
    Statement stmt;

    // Load SIMPLE_LITERAL_PROPERTY width
    stmt = res.getProperty(snapshot.getProperty("http://www.w3.org/2003/12/exif/ns#width"));
    if(stmt != null && stmt.getObject().isLiteral())
      this.setWidth(((Literal)stmt.getObject()).getString());

    // Load SIMPLE_LITERAL_PROPERTY height
    stmt = res.getProperty(snapshot.getProperty("http://www.w3.org/2003/12/exif/ns#height"));
    if(stmt != null && stmt.getObject().isLiteral())
      this.setHeight(((Literal)stmt.getObject()).getString());

    // Load SIMPLE_LITERAL_PROPERTY title
    stmt = res.getProperty(snapshot.getProperty("http://purl.org/dc/elements/1.1/title"));
    if(stmt != null && stmt.getObject().isLiteral())
      this.setTitle(((Literal)stmt.getObject()).getString());

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

    // Load SIMPLE_LITERAL_PROPERTY date
    stmt = res.getProperty(snapshot.getProperty("http://purl.org/dc/elements/1.1/date"));
    if(stmt != null && stmt.getObject().isLiteral())
      this.setDate(((Literal)stmt.getObject()).getString());

    // Load SIMPLE_LITERAL_PROPERTY latitude
    stmt = res.getProperty(snapshot.getProperty("http://www.opengis.net/gml/lat"));
    if(stmt != null && stmt.getObject().isLiteral())
      this.setLatitude(((Literal)stmt.getObject()).getFloat());

    // Load SIMPLE_LITERAL_PROPERTY longitude
    stmt = res.getProperty(snapshot.getProperty("http://www.opengis.net/gml/lon"));
    if(stmt != null && stmt.getObject().isLiteral())
      this.setLongitude(((Literal)stmt.getObject()).getFloat());

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