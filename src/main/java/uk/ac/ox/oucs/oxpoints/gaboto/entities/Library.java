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
import net.sf.gaboto.node.annotation.SimpleURIProperty;

import net.sf.gaboto.node.pool.EntityExistsCallback;
import net.sf.gaboto.node.pool.EntityPool;

import uk.ac.ox.oucs.oxpoints.gaboto.entities.Unit;


/**
 * Gaboto generated Entity.
 * @see net.sf.gaboto.generation.GabotoGenerator
 */
public class Library extends Unit {
  protected Collection<String> oLISCode;
  protected Website libraryHomepage;


  private static Map<String, List<Method>> indirectPropertyLookupTable;
  static{
    indirectPropertyLookupTable = new HashMap<String, List<Method>>();
  }

  @Override
  public String getType(){
    return "http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#Library";
  }

  @BagLiteralProperty(
    value = "http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#hasOLISCode",
    datatypeType = "javaprimitive",
    javaType = "String"
  )
  public Collection<String> getOLISCode(){
    return this.oLISCode;
  }

  @BagLiteralProperty(
    value = "http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#hasOLISCode",
    datatypeType = "javaprimitive",
    javaType = "String"
  )
  public void setOLISCode(Collection<String> oLISCode){
    this.oLISCode = oLISCode;
  }

  public void addOLISCode(String oLISCodeP){
    if(this.oLISCode == null)
      setOLISCode( new HashSet<String>() );
    this.oLISCode.add(oLISCodeP);
  }

  @SimpleURIProperty("http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#hasLibraryHomepage")
  public Website getLibraryHomepage(){
    if(! this.isDirectReferencesResolved())
      this.resolveDirectReferences();
    return this.libraryHomepage;
  }

  @SimpleURIProperty("http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#hasLibraryHomepage")
  public void setLibraryHomepage(Website libraryHomepage){
    if( libraryHomepage != null )
      this.removeMissingReference( libraryHomepage.getUri() );
    this.libraryHomepage = libraryHomepage;
  }







  public void loadFromSnapshot(Resource res, GabotoSnapshot snapshot, EntityPool pool) {
    super.loadFromSnapshot(res, snapshot, pool);
    Statement stmt;

    // Load BAG_LITERAL_PROPERTY oLISCode
    {
        StmtIterator stmts = res.listProperties(snapshot.getProperty("http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#hasOLISCode"));
        while (stmts.hasNext()) {
            RDFNode node = stmts.next().getObject();
            if(! node.isLiteral())
              throw new IllegalArgumentException("node should be a literal");

            addOLISCode(((Literal)node).getString());
        }
    }

    // Load SIMPLE_URI_PROPERTY libraryHomepage
    stmt = res.getProperty(snapshot.getProperty("http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#hasLibraryHomepage"));
    if(stmt != null && stmt.getObject().isResource()){
      Resource missingReference = (Resource)stmt.getObject();
      EntityExistsCallback callback = new EntityExistsCallback(){
        public void entityExists(EntityPool p, GabotoEntity entity) {
          setLibraryHomepage((Website)entity);
        }
      };
      this.addMissingReference(missingReference, callback);
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