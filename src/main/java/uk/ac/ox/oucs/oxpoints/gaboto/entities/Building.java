package uk.ac.ox.oucs.oxpoints.gaboto.entities;


import java.lang.reflect.Method;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;


import net.sf.gaboto.node.GabotoEntity;
import net.sf.gaboto.node.annotation.PassiveProperty;
import net.sf.gaboto.node.pool.EntityPool;
import net.sf.gaboto.node.pool.PassiveEntitiesRequest;



import uk.ac.ox.oucs.oxpoints.gaboto.entities.Place;


/**
 * Gaboto generated Entity.
 * @see net.sf.gaboto.generation.GabotoGenerator
 */
public class Building extends Place {
  private Collection<Unit> occupiedBy;


  private static Map<String, List<Method>> indirectPropertyLookupTable;
  static{
    indirectPropertyLookupTable = new HashMap<String, List<Method>>();
  }

  @Override
  public String getType(){
    return "http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#Building";
  }

  @PassiveProperty(
    uri = "http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#occupies",
    entity = "Unit"
  )
  public Collection<Unit> getOccupiedBy(){
    if(! isPassiveEntitiesLoaded() )
      loadPassiveEntities();
    return this.occupiedBy;
  }

  @PassiveProperty(
    uri = "http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#occupies",
    entity = "Unit"
  )
  private void setOccupiedBy(Collection<Unit> occupiedBy){
    this.occupiedBy = occupiedBy;
  }

  private void addOccupiedBy(Unit occupiedByP){
    if(this.occupiedBy == null)
      setOccupiedBy( new HashSet<Unit>() );
    this.occupiedBy.add(occupiedByP);
  }







  public Collection<PassiveEntitiesRequest> getPassiveEntitiesRequest(){
    Collection<PassiveEntitiesRequest> requests = super.getPassiveEntitiesRequest();
    if(requests == null)
      requests = new HashSet<PassiveEntitiesRequest>();
    requests.add(new PassiveEntitiesRequest(){
      public String getType() {
        return "http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#Unit";
      }

      public String getUri() {
        return "http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#occupies";
      }

      public int getCollectionType() {
        return EntityPool.PASSIVE_PROPERTY_COLLECTION_TYPE_BAG;
      }

      public void passiveEntityLoaded(GabotoEntity entity) {
        addOccupiedBy((Unit)entity);
      }
    });
    return requests;
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