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
import net.sf.gaboto.node.annotation.SimpleLiteralProperty;
import net.sf.gaboto.node.annotation.SimpleURIProperty;

import net.sf.gaboto.node.pool.EntityExistsCallback;
import net.sf.gaboto.node.pool.EntityPool;

import uk.ac.ox.oucs.oxpoints.gaboto.entities.OxpEntity;


/**
 * Gaboto generated Entity.
 * @see net.sf.gaboto.generation.GabotoGenerator
 */
public class Access extends OxpEntity {
  private Place accessFrom;
  private Place accessTo;
  private Portal usingPortal;
  private Collection<String> accessRights;
  private String incline;


  private static Map<String, List<Method>> indirectPropertyLookupTable;
  static{
    indirectPropertyLookupTable = new HashMap<String, List<Method>>();
  }

  @Override
  public String getType(){
    return "http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#Access";
  }

  @SimpleURIProperty("http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#accessFrom")
  public Place getAccessFrom(){
    if(! this.isDirectReferencesResolved())
      this.resolveDirectReferences();
    return this.accessFrom;
  }

  @SimpleURIProperty("http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#accessFrom")
  public void setAccessFrom(Place accessFrom){
    if( accessFrom != null )
      this.removeMissingReference( accessFrom.getUri() );
    this.accessFrom = accessFrom;
  }

  @SimpleURIProperty("http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#accessTo")
  public Place getAccessTo(){
    if(! this.isDirectReferencesResolved())
      this.resolveDirectReferences();
    return this.accessTo;
  }

  @SimpleURIProperty("http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#accessTo")
  public void setAccessTo(Place accessTo){
    if( accessTo != null )
      this.removeMissingReference( accessTo.getUri() );
    this.accessTo = accessTo;
  }

  @SimpleURIProperty("http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#usingPortal")
  public Portal getUsingPortal(){
    if(! this.isDirectReferencesResolved())
      this.resolveDirectReferences();
    return this.usingPortal;
  }

  @SimpleURIProperty("http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#usingPortal")
  public void setUsingPortal(Portal usingPortal){
    if( usingPortal != null )
      this.removeMissingReference( usingPortal.getUri() );
    this.usingPortal = usingPortal;
  }

  @BagLiteralProperty(
    value = "http://purl.org/dc/elements/1.1/accessRights",
    datatypeType = "javaprimitive",
    javaType = "String"
  )
  public Collection<String> getAccessRights(){
    return this.accessRights;
  }

  @BagLiteralProperty(
    value = "http://purl.org/dc/elements/1.1/accessRights",
    datatypeType = "javaprimitive",
    javaType = "String"
  )
  public void setAccessRights(Collection<String> accessRights){
    this.accessRights = accessRights;
  }

  public void addAccessRight(String accessRightP){
    if(this.accessRights == null)
      setAccessRights( new HashSet<String>() );
    this.accessRights.add(accessRightP);
  }

  @SimpleLiteralProperty(
    value = "http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#incline",
    datatypeType = "javaprimitive",
    javaType = "String"
  )
  public String getIncline(){
    return this.incline;
  }

  @SimpleLiteralProperty(
    value = "http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#incline",
    datatypeType = "javaprimitive",
    javaType = "String"
  )
  public void setIncline(String incline){
    this.incline = incline;
  }







  public void loadFromSnapshot(Resource res, GabotoSnapshot snapshot, EntityPool pool) {
    super.loadFromSnapshot(res, snapshot, pool);
    Statement stmt;

    // Load SIMPLE_URI_PROPERTY accessFrom
    stmt = res.getProperty(snapshot.getProperty("http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#accessFrom"));
    if(stmt != null && stmt.getObject().isResource()){
      Resource missingReference = (Resource)stmt.getObject();
      EntityExistsCallback callback = new EntityExistsCallback(){
        public void entityExists(EntityPool p, GabotoEntity entity) {
          setAccessFrom((Place)entity);
        }
      };
      this.addMissingReference(missingReference, callback);
    }

    // Load SIMPLE_URI_PROPERTY accessTo
    stmt = res.getProperty(snapshot.getProperty("http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#accessTo"));
    if(stmt != null && stmt.getObject().isResource()){
      Resource missingReference = (Resource)stmt.getObject();
      EntityExistsCallback callback = new EntityExistsCallback(){
        public void entityExists(EntityPool p, GabotoEntity entity) {
          setAccessTo((Place)entity);
        }
      };
      this.addMissingReference(missingReference, callback);
    }

    // Load SIMPLE_URI_PROPERTY usingPortal
    stmt = res.getProperty(snapshot.getProperty("http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#usingPortal"));
    if(stmt != null && stmt.getObject().isResource()){
      Resource missingReference = (Resource)stmt.getObject();
      EntityExistsCallback callback = new EntityExistsCallback(){
        public void entityExists(EntityPool p, GabotoEntity entity) {
          setUsingPortal((Portal)entity);
        }
      };
      this.addMissingReference(missingReference, callback);
    }

    // Load BAG_LITERAL_PROPERTY accessRights
    {
        StmtIterator stmts = res.listProperties(snapshot.getProperty("http://purl.org/dc/elements/1.1/accessRights"));
        while (stmts.hasNext()) {
            RDFNode node = stmts.next().getObject();
            if(! node.isLiteral())
              throw new IllegalArgumentException("node should be a literal");

            addAccessRight(((Literal)node).getString());
        }
    }

    // Load SIMPLE_LITERAL_PROPERTY incline
    stmt = res.getProperty(snapshot.getProperty("http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#incline"));
    if(stmt != null && stmt.getObject().isLiteral())
      this.setIncline(((Literal)stmt.getObject()).getString());

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