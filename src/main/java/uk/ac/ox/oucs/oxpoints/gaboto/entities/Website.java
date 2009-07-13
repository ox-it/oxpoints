package uk.ac.ox.oucs.oxpoints.gaboto.entities;


import java.lang.reflect.Method;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.oucs.gaboto.entities.GabotoEntity;

import org.oucs.gaboto.entities.pool.GabotoEntityPool;
import org.oucs.gaboto.entities.pool.PassiveEntitiesRequest;

import org.oucs.gaboto.entities.utils.PassiveProperty;

import uk.ac.ox.oucs.oxpoints.gaboto.entities.OxpEntity;


/**
 * Gaboto generated Entity.
 * @see net.sf.gaboto.generation.GabotoGenerator
 */
public class Website extends OxpEntity {
  private Collection<OxpEntity> isWeblearnIn;
  private Collection<OxpEntity> isITHomepageIn;
  private Collection<OxpEntity> isHomepageIn;
  private Collection<OxpEntity> isLibraryHomepageIn;


  private static Map<String, List<Method>> indirectPropertyLookupTable;
  static{
    indirectPropertyLookupTable = new HashMap<String, List<Method>>();
  }

  @Override
  public String getType(){
    return "http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#Website";
  }

  @PassiveProperty(
    uri = "http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#hasWeblearn",
    entity = "OxpEntity"
  )
  public Collection<OxpEntity> getIsWeblearnIn(){
    if(! isPassiveEntitiesLoaded() )
      loadPassiveEntities();
    return this.isWeblearnIn;
  }

  @PassiveProperty(
    uri = "http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#hasWeblearn",
    entity = "OxpEntity"
  )
  private void setIsWeblearnIn(Collection<OxpEntity> isWeblearnIn){
    this.isWeblearnIn = isWeblearnIn;
  }

  private void addIsWeblearnIn(OxpEntity isWeblearnInP){
    if(this.isWeblearnIn == null)
      setIsWeblearnIn( new HashSet<OxpEntity>() );
    this.isWeblearnIn.add(isWeblearnInP);
  }

  @PassiveProperty(
    uri = "http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#hasITHomepage",
    entity = "OxpEntity"
  )
  public Collection<OxpEntity> getIsITHomepageIn(){
    if(! isPassiveEntitiesLoaded() )
      loadPassiveEntities();
    return this.isITHomepageIn;
  }

  @PassiveProperty(
    uri = "http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#hasITHomepage",
    entity = "OxpEntity"
  )
  private void setIsITHomepageIn(Collection<OxpEntity> isITHomepageIn){
    this.isITHomepageIn = isITHomepageIn;
  }

  private void addIsITHomepageIn(OxpEntity isITHomepageInP){
    if(this.isITHomepageIn == null)
      setIsITHomepageIn( new HashSet<OxpEntity>() );
    this.isITHomepageIn.add(isITHomepageInP);
  }

  @PassiveProperty(
    uri = "http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#hasHomepage",
    entity = "OxpEntity"
  )
  public Collection<OxpEntity> getIsHomepageIn(){
    if(! isPassiveEntitiesLoaded() )
      loadPassiveEntities();
    return this.isHomepageIn;
  }

  @PassiveProperty(
    uri = "http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#hasHomepage",
    entity = "OxpEntity"
  )
  private void setIsHomepageIn(Collection<OxpEntity> isHomepageIn){
    this.isHomepageIn = isHomepageIn;
  }

  private void addIsHomepageIn(OxpEntity isHomepageInP){
    if(this.isHomepageIn == null)
      setIsHomepageIn( new HashSet<OxpEntity>() );
    this.isHomepageIn.add(isHomepageInP);
  }

  @PassiveProperty(
    uri = "http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#hasLibraryHomepage",
    entity = "OxpEntity"
  )
  public Collection<OxpEntity> getIsLibraryHomepageIn(){
    if(! isPassiveEntitiesLoaded() )
      loadPassiveEntities();
    return this.isLibraryHomepageIn;
  }

  @PassiveProperty(
    uri = "http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#hasLibraryHomepage",
    entity = "OxpEntity"
  )
  private void setIsLibraryHomepageIn(Collection<OxpEntity> isLibraryHomepageIn){
    this.isLibraryHomepageIn = isLibraryHomepageIn;
  }

  private void addIsLibraryHomepageIn(OxpEntity isLibraryHomepageInP){
    if(this.isLibraryHomepageIn == null)
      setIsLibraryHomepageIn( new HashSet<OxpEntity>() );
    this.isLibraryHomepageIn.add(isLibraryHomepageInP);
  }







  public Collection<PassiveEntitiesRequest> getPassiveEntitiesRequest(){
    Collection<PassiveEntitiesRequest> requests = super.getPassiveEntitiesRequest();

    requests.add(new PassiveEntitiesRequest(){
      public String getType() {
        return "http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#OxpEntity";
      }

      public String getUri() {
        return "http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#hasWeblearn";
      }

      public int getCollectionType() {
        return GabotoEntityPool.PASSIVE_PROPERTY_COLLECTION_TYPE_NONE;
      }

      public void passiveEntityLoaded(GabotoEntity entity) {
        addIsWeblearnIn((OxpEntity)entity);
      }
    });
    requests.add(new PassiveEntitiesRequest(){
      public String getType() {
        return "http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#OxpEntity";
      }

      public String getUri() {
        return "http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#hasITHomepage";
      }

      public int getCollectionType() {
        return GabotoEntityPool.PASSIVE_PROPERTY_COLLECTION_TYPE_NONE;
      }

      public void passiveEntityLoaded(GabotoEntity entity) {
        addIsITHomepageIn((OxpEntity)entity);
      }
    });
    requests.add(new PassiveEntitiesRequest(){
      public String getType() {
        return "http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#OxpEntity";
      }

      public String getUri() {
        return "http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#hasHomepage";
      }

      public int getCollectionType() {
        return GabotoEntityPool.PASSIVE_PROPERTY_COLLECTION_TYPE_NONE;
      }

      public void passiveEntityLoaded(GabotoEntity entity) {
        addIsHomepageIn((OxpEntity)entity);
      }
    });
    requests.add(new PassiveEntitiesRequest(){
      public String getType() {
        return "http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#OxpEntity";
      }

      public String getUri() {
        return "http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#hasLibraryHomepage";
      }

      public int getCollectionType() {
        return GabotoEntityPool.PASSIVE_PROPERTY_COLLECTION_TYPE_NONE;
      }

      public void passiveEntityLoaded(GabotoEntity entity) {
        addIsLibraryHomepageIn((OxpEntity)entity);
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