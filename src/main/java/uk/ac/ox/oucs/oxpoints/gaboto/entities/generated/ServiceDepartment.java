package uk.ac.ox.oucs.oxpoints.gaboto.entities.generated;


import java.lang.reflect.Method;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import uk.ac.ox.oucs.oxpoints.gaboto.entities.generated.Unit;


/**
 * Gaboto generated Entity.
 * @see net.sf.gaboto.generation.GabotoGenerator#generateEntity.
 */
public class ServiceDepartment extends Unit {


  private static Map<String, List<Method>> indirectPropertyLookupTable;
  static{
    indirectPropertyLookupTable = new HashMap<String, List<Method>>();
    List<Method> list;

  }

  @Override
  public String getType(){
    return "http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#ServiceDepartment";
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