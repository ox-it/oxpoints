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
import net.sf.gaboto.node.annotation.BagResourceProperty;
import net.sf.gaboto.node.annotation.BagURIProperty;
import net.sf.gaboto.node.annotation.SimpleLiteralProperty;
import net.sf.gaboto.node.annotation.UnstoredProperty;

import net.sf.gaboto.node.pool.EntityExistsCallback;
import net.sf.gaboto.node.pool.EntityPool;


/**
 * Gaboto generated Entity.
 * @see net.sf.gaboto.generation.GabotoGenerator
 */
abstract public class OxpEntity extends GabotoEntity {
  protected String name;
  protected Collection<Image> images;
  protected Collection<String> sameAss;
  protected String description;
  protected Collection<String> dcType;


  private static Map<String, List<Method>> indirectPropertyLookupTable;
  static{
    indirectPropertyLookupTable = new HashMap<String, List<Method>>();
  }

  @Override
  public String getType(){
    return "http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#OxpEntity";
  }

  @UnstoredProperty({"http://www.w3.org/2000/01/rdf-schema#label"})
  @SimpleLiteralProperty(
    value = "http://purl.org/dc/elements/1.1/title",
    datatypeType = "javaprimitive",
    javaType = "String"
  )
  public String getName(){
    return this.name;
  }

  @SimpleLiteralProperty(
    value = "http://purl.org/dc/elements/1.1/title",
    datatypeType = "javaprimitive",
    javaType = "String"
  )
  public void setName(String name){
    this.name = name;
  }

  @BagURIProperty("http://xmlns.com/foaf/0.1/depiction")
  public Collection<Image> getImages(){
    if(! this.isDirectReferencesResolved())
      this.resolveDirectReferences();
    return this.images;
  }

  @BagURIProperty("http://xmlns.com/foaf/0.1/depiction")
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

  @BagResourceProperty("http://www.w3.org/2002/07/owl#sameAs")
  public Collection<String> getSameAss(){
    return this.sameAss;
  }

  @BagResourceProperty("http://www.w3.org/2002/07/owl#sameAs")
  public void setSameAss(Collection<String> sameAss){
    this.sameAss = sameAss;
  }

  public void addSameAs(String sameAsP){
    if(this.sameAss == null)
      setSameAss( new HashSet<String>() );
    this.sameAss.add(sameAsP);
  }

  @SimpleLiteralProperty(
    value = "http://purl.org/dc/elements/1.1/description",
    datatypeType = "javaprimitive",
    javaType = "String"
  )
  public String getDescription(){
    return this.description;
  }

  @SimpleLiteralProperty(
    value = "http://purl.org/dc/elements/1.1/description",
    datatypeType = "javaprimitive",
    javaType = "String"
  )
  public void setDescription(String description){
    this.description = description;
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







  public void loadFromSnapshot(Resource res, GabotoSnapshot snapshot, EntityPool pool) {
    super.loadFromSnapshot(res, snapshot, pool);
    Statement stmt;

    // Load SIMPLE_LITERAL_PROPERTY name
    stmt = res.getProperty(snapshot.getProperty("http://purl.org/dc/elements/1.1/title"));
    if(stmt != null && stmt.getObject().isLiteral())
      this.setName(((Literal)stmt.getObject()).getString());

    // Load BAG_URI_PROPERTY images
    {
        StmtIterator stmts = res.listProperties(snapshot.getProperty("http://xmlns.com/foaf/0.1/depiction"));
        while (stmts.hasNext()) {
            RDFNode node = stmts.next().getObject();
            if(! node.isResource())
              throw new IllegalArgumentException("node should be a resource");

            Resource missingReference = (Resource)node;
            EntityExistsCallback callback = new EntityExistsCallback(){
              public void entityExists(EntityPool p, GabotoEntity entity) {
                addImage((Image) entity);
            }
        };
        this.addMissingReference(missingReference, callback);
      }
    }

    // Load BAG_RESOURCE_PROPERTY sameAss
    {
        StmtIterator stmts = res.listProperties(snapshot.getProperty("http://www.w3.org/2002/07/owl#sameAs"));
        while (stmts.hasNext()) {
            RDFNode node = stmts.next().getObject();
            if(node.isLiteral()){
                this.addSameAs(((Literal)node).getLexicalForm());
            }
        }
    }

    // Load SIMPLE_LITERAL_PROPERTY description
    stmt = res.getProperty(snapshot.getProperty("http://purl.org/dc/elements/1.1/description"));
    if(stmt != null && stmt.getObject().isLiteral())
      this.setDescription(((Literal)stmt.getObject()).getString());

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