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
import net.sf.gaboto.node.annotation.PassiveProperty;
import net.sf.gaboto.node.annotation.ResourceProperty;
import net.sf.gaboto.node.annotation.SimpleLiteralProperty;
import net.sf.gaboto.node.annotation.SimpleURIProperty;
import net.sf.gaboto.node.annotation.StaticProperty;
import net.sf.gaboto.node.annotation.UnstoredProperty;

import net.sf.gaboto.node.pool.EntityExistsCallback;
import net.sf.gaboto.node.pool.EntityPool;
import net.sf.gaboto.node.pool.PassiveEntitiesRequest;


/**
 * Gaboto generated Entity.
 * @see net.sf.gaboto.generation.GabotoGenerator
 */
public class OxpEntity extends GabotoEntity {
  protected String name;
  protected Collection<Image> images;
  protected Image img;
  protected Collection<String> sameAss;
  protected String description;
  protected String homepage;
  protected Collection<String> dcType;
  protected String prefLabel;
  protected Collection<String> altLabels;
  protected Collection<String> hiddenLabels;
  protected String sortLabel;
  private Collection<SKOSCollection> memberOf;


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

  @SimpleURIProperty("http://xmlns.com/foaf/0.1/img")
  public Image getImg(){
    if(! this.isDirectReferencesResolved())
      this.resolveDirectReferences();
    return this.img;
  }

  @SimpleURIProperty("http://xmlns.com/foaf/0.1/img")
  public void setImg(Image img){
    if( img != null )
      this.removeMissingReference( img.getUri() );
    this.img = img;
  }

  @BagResourceProperty("http://www.w3.org/2004/02/skos/core#exactMatch")
  public Collection<String> getSameAss(){
    return this.sameAss;
  }

  @BagResourceProperty("http://www.w3.org/2004/02/skos/core#exactMatch")
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

  @ResourceProperty("http://xmlns.com/foaf/0.1/homepage")
  public String getHomepage(){
    return this.homepage;
  }

  @ResourceProperty("http://xmlns.com/foaf/0.1/homepage")
  public void setHomepage(String homepage){
    this.homepage = homepage;
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
    value = "http://www.w3.org/2004/02/skos/core#prefLabel",
    datatypeType = "javaprimitive",
    javaType = "String"
  )
  public String getPrefLabel(){
    return this.prefLabel;
  }

  @SimpleLiteralProperty(
    value = "http://www.w3.org/2004/02/skos/core#prefLabel",
    datatypeType = "javaprimitive",
    javaType = "String"
  )
  public void setPrefLabel(String prefLabel){
    this.prefLabel = prefLabel;
  }

  @BagLiteralProperty(
    value = "http://www.w3.org/2004/02/skos/core#altLabel",
    datatypeType = "javaprimitive",
    javaType = "String"
  )
  public Collection<String> getAltLabels(){
    return this.altLabels;
  }

  @BagLiteralProperty(
    value = "http://www.w3.org/2004/02/skos/core#altLabel",
    datatypeType = "javaprimitive",
    javaType = "String"
  )
  public void setAltLabels(Collection<String> altLabels){
    this.altLabels = altLabels;
  }

  public void addAltLabel(String altLabelP){
    if(this.altLabels == null)
      setAltLabels( new HashSet<String>() );
    this.altLabels.add(altLabelP);
  }

  @BagLiteralProperty(
    value = "http://www.w3.org/2004/02/skos/core#hiddenLabel",
    datatypeType = "javaprimitive",
    javaType = "String"
  )
  public Collection<String> getHiddenLabels(){
    return this.hiddenLabels;
  }

  @BagLiteralProperty(
    value = "http://www.w3.org/2004/02/skos/core#hiddenLabel",
    datatypeType = "javaprimitive",
    javaType = "String"
  )
  public void setHiddenLabels(Collection<String> hiddenLabels){
    this.hiddenLabels = hiddenLabels;
  }

  public void addHiddenLabel(String hiddenLabelP){
    if(this.hiddenLabels == null)
      setHiddenLabels( new HashSet<String>() );
    this.hiddenLabels.add(hiddenLabelP);
  }

  @SimpleLiteralProperty(
    value = "http://open.vocab.org/terms/sortLabel",
    datatypeType = "javaprimitive",
    javaType = "String"
  )
  public String getSortLabel(){
    return this.sortLabel;
  }

  @SimpleLiteralProperty(
    value = "http://open.vocab.org/terms/sortLabel",
    datatypeType = "javaprimitive",
    javaType = "String"
  )
  public void setSortLabel(String sortLabel){
    this.sortLabel = sortLabel;
  }

  @PassiveProperty(
    uri = "http://www.w3.org/2004/02/skos/core#member",
    entity = "SKOSCollection"
  )
  public Collection<SKOSCollection> getMemberOf(){
    if(! isPassiveEntitiesLoaded() )
      loadPassiveEntities();
    return this.memberOf;
  }

  @PassiveProperty(
    uri = "http://www.w3.org/2004/02/skos/core#member",
    entity = "SKOSCollection"
  )
  private void setMemberOf(Collection<SKOSCollection> memberOf){
    this.memberOf = memberOf;
  }

  private void addMemberOf(SKOSCollection memberOfP){
    if(this.memberOf == null)
      setMemberOf( new HashSet<SKOSCollection>() );
    this.memberOf.add(memberOfP);
  }






  @StaticProperty("http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#allCollections")
  public Collection<SKOSCollection> getAllCollections() {
	  Collection<SKOSCollection> collections = new HashSet<SKOSCollection>();
	  if (getMemberOf() == null)
		  return collections;
	  for (SKOSCollection collection : getMemberOf()) {
		  collections.add(collection);
		  if (collection instanceof SKOSCollection)
			  collections.addAll(((SKOSCollection) collection).getAllCollections());
	  }
	  return collections;
  }

  public Collection<PassiveEntitiesRequest> getPassiveEntitiesRequest(){
    Collection<PassiveEntitiesRequest> requests = super.getPassiveEntitiesRequest();
    if(requests == null)
      requests = new HashSet<PassiveEntitiesRequest>();
    requests.add(new PassiveEntitiesRequest(){
      public String getType() {
        return "http://www.w3.org/2004/02/skos/core#Collection";
      }

      public String getUri() {
        return "http://www.w3.org/2004/02/skos/core#member";
      }

      public int getCollectionType() {
        return EntityPool.PASSIVE_PROPERTY_COLLECTION_TYPE_BAG;
      }

      public void passiveEntityLoaded(GabotoEntity entity) {
        addMemberOf((SKOSCollection)entity);
      }
    });
    return requests;
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

    // Load SIMPLE_URI_PROPERTY img
    stmt = res.getProperty(snapshot.getProperty("http://xmlns.com/foaf/0.1/img"));
    if(stmt != null && stmt.getObject().isResource()){
      Resource missingReference = (Resource)stmt.getObject();
      EntityExistsCallback callback = new EntityExistsCallback(){
        public void entityExists(EntityPool p, GabotoEntity entity) {
          setImg((Image)entity);
        }
      };
      this.addMissingReference(missingReference, callback);
    }

    // Load BAG_RESOURCE_PROPERTY sameAss
    {
        StmtIterator stmts = res.listProperties(snapshot.getProperty("http://www.w3.org/2004/02/skos/core#exactMatch"));
        while (stmts.hasNext()) {
            RDFNode node = stmts.next().getObject();
            if(node.isURIResource()){
                this.addSameAs(((Resource) node).getURI());
            }
        }
    }

    // Load SIMPLE_LITERAL_PROPERTY description
    stmt = res.getProperty(snapshot.getProperty("http://purl.org/dc/elements/1.1/description"));
    if(stmt != null && stmt.getObject().isLiteral())
      this.setDescription(((Literal)stmt.getObject()).getString());

    // Load SIMPLE_RESOURCE_PROPERTY homepage
    stmt = res.getProperty(snapshot.getProperty("http://xmlns.com/foaf/0.1/homepage"));
    if(stmt != null && stmt.getObject().isURIResource()){
      this.setHomepage(((Resource) stmt.getObject()).getURI());
    }

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

    // Load SIMPLE_LITERAL_PROPERTY prefLabel
    stmt = res.getProperty(snapshot.getProperty("http://www.w3.org/2004/02/skos/core#prefLabel"));
    if(stmt != null && stmt.getObject().isLiteral())
      this.setPrefLabel(((Literal)stmt.getObject()).getString());

    // Load BAG_LITERAL_PROPERTY altLabels
    {
        StmtIterator stmts = res.listProperties(snapshot.getProperty("http://www.w3.org/2004/02/skos/core#altLabel"));
        while (stmts.hasNext()) {
            RDFNode node = stmts.next().getObject();
            if(! node.isLiteral())
              throw new IllegalArgumentException("node should be a literal");

            addAltLabel(((Literal)node).getString());
        }
    }

    // Load BAG_LITERAL_PROPERTY hiddenLabels
    {
        StmtIterator stmts = res.listProperties(snapshot.getProperty("http://www.w3.org/2004/02/skos/core#hiddenLabel"));
        while (stmts.hasNext()) {
            RDFNode node = stmts.next().getObject();
            if(! node.isLiteral())
              throw new IllegalArgumentException("node should be a literal");

            addHiddenLabel(((Literal)node).getString());
        }
    }

    // Load SIMPLE_LITERAL_PROPERTY sortLabel
    stmt = res.getProperty(snapshot.getProperty("http://open.vocab.org/terms/sortLabel"));
    if(stmt != null && stmt.getObject().isLiteral())
      this.setSortLabel(((Literal)stmt.getObject()).getString());

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