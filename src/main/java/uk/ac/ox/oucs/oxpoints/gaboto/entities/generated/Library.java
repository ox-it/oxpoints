package uk.ac.ox.oucs.oxpoints.gaboto.entities.generated;


import com.hp.hpl.jena.rdf.model.Literal;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.Statement;

import java.lang.reflect.Method;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.oucs.gaboto.entities.GabotoEntity;

import org.oucs.gaboto.entities.pool.EntityExistsCallback;
import org.oucs.gaboto.entities.pool.GabotoEntityPool;

import org.oucs.gaboto.entities.utils.SimpleLiteralProperty;
import org.oucs.gaboto.entities.utils.SimpleURIProperty;

import org.oucs.gaboto.model.GabotoSnapshot;

import uk.ac.ox.oucs.oxpoints.gaboto.entities.generated.Unit;


/**
 * Gaboto generated Entity.
 * @see net.sf.gaboto.generation.GabotoGenerator#generateEntity.
 */
public class Library extends Unit {
  private String oLISCode;
  private Website libraryHomepage;


  private static Map<String, List<Method>> indirectPropertyLookupTable;
  static{
    indirectPropertyLookupTable = new HashMap<String, List<Method>>();
    List<Method> list;

  }

  @Override
  public String getType(){
    return "http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#Library";
  }

  @SimpleLiteralProperty(
    value = "http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#hasOLISCode",
    datatypeType = "javaprimitive",
    javaType = "String"
  )
  public String getOLISCode(){
    return this.oLISCode;
  }

  @SimpleLiteralProperty(
    value = "http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#hasOLISCode",
    datatypeType = "javaprimitive",
    javaType = "String"
  )
  public void setOLISCode(String oLISCode){
    this.oLISCode = oLISCode;
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







  public void loadFromSnapshot(Resource res, GabotoSnapshot snapshot, GabotoEntityPool pool) {
    super.loadFromSnapshot(res, snapshot, pool);
    Statement stmt;

    // Load SIMPLE_LITERAL_PROPERTY oLISCode
    stmt = res.getProperty(snapshot.getProperty("http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#hasOLISCode"));
    if(stmt != null && stmt.getObject().isLiteral())
      this.setOLISCode(((Literal)stmt.getObject()).getString());

    // Load SIMPLE_URI_PROPERTY libraryHomepage
    stmt = res.getProperty(snapshot.getProperty("http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#hasLibraryHomepage"));
    if(stmt != null && stmt.getObject().isResource()){
      Resource missingReference = (Resource)stmt.getObject();
      EntityExistsCallback callback = new EntityExistsCallback(){
        public void entityExists(GabotoEntityPool p, GabotoEntity entity) {
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