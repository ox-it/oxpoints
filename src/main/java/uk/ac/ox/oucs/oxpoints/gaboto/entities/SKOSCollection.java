package uk.ac.ox.oucs.oxpoints.gaboto.entities;


import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.Statement;
import com.hp.hpl.jena.rdf.model.StmtIterator;

import java.lang.reflect.Method;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import net.sf.gaboto.GabotoRuntimeException;
import net.sf.gaboto.GabotoSnapshot;

import net.sf.gaboto.node.GabotoEntity;

import net.sf.gaboto.node.annotation.BagURIProperty;
import net.sf.gaboto.node.annotation.IndirectProperty;
import net.sf.gaboto.node.annotation.StaticProperty;

import net.sf.gaboto.node.pool.EntityExistsCallback;
import net.sf.gaboto.node.pool.EntityPool;

import uk.ac.ox.oucs.oxpoints.gaboto.entities.OxpEntity;


/**
 * Gaboto generated Entity.
 * @see net.sf.gaboto.generation.GabotoGenerator
 */
public class SKOSCollection extends OxpEntity {
  protected Collection<OxpEntity> member;


  private static Map<String, List<Method>> indirectPropertyLookupTable;
  static{
    indirectPropertyLookupTable = new HashMap<String, List<Method>>();
    List<Method> list;

    try {
      list = new ArrayList<Method>();
      list.add(SKOSCollection.class.getMethod("getMember", (Class<?>[])null));
      indirectPropertyLookupTable.put("http://www.w3.org/2004/02/skos/core#member", list);

    } catch (Exception e) {
      throw new GabotoRuntimeException(e);
    }
  }

  @Override
  public String getType(){
    return "http://www.w3.org/2004/02/skos/core#Collection";
  }

  @IndirectProperty({"http://www.w3.org/2004/02/skos/core#member"})
  @BagURIProperty("http://www.w3.org/2004/02/skos/core#member")
  public Collection<OxpEntity> getMember(){
    if(! this.isDirectReferencesResolved())
      this.resolveDirectReferences();
    return this.member;
  }

  @BagURIProperty("http://www.w3.org/2004/02/skos/core#member")
  public void setMember(Collection<OxpEntity> member){
    if( member != null ){
      for( GabotoEntity _entity : member)
        this.removeMissingReference( _entity.getUri() );
    }

    this.member = member;
  }

  public void addMember(OxpEntity member){
    if( member != null )
      this.removeMissingReference( member.getUri() );
    if(this.member == null )
      this.member = new HashSet<OxpEntity>();
    this.member.add(member);
  }






  @StaticProperty("http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#allMembers")
  public Collection<OxpEntity> getAllMembers() {
	  Collection<OxpEntity> members = new HashSet<OxpEntity>();
	  if (getMember() == null)
		  return members;
	  for (OxpEntity member : getMember()) {
		  if (member instanceof SKOSCollection)
			  members.addAll(((SKOSCollection) member).getAllMembers());
		  else
			  members.add(member);
	  }
	  return members;
  }

  public void loadFromSnapshot(Resource res, GabotoSnapshot snapshot, EntityPool pool) {
    super.loadFromSnapshot(res, snapshot, pool);
    Statement stmt;

    // Load BAG_URI_PROPERTY member
    {
        StmtIterator stmts = res.listProperties(snapshot.getProperty("http://www.w3.org/2004/02/skos/core#member"));
        while (stmts.hasNext()) {
            RDFNode node = stmts.next().getObject();
            if(! node.isResource())
              throw new IllegalArgumentException("node should be a resource");

            Resource missingReference = (Resource)node;
            EntityExistsCallback callback = new EntityExistsCallback(){
              public void entityExists(EntityPool p, GabotoEntity entity) {
                addMember((OxpEntity) entity);
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