package uk.ac.ox.oucs.oxpoints.gaboto;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import net.sf.gaboto.node.GabotoEntity;
import net.sf.gaboto.GabotoRuntimeException;

import net.sf.gaboto.OntologyLookup;



/**
 * Gaboto generated ontology lookup utility.
 * @see net.sf.gaboto.generation.GabotoGenerator
 */
@SuppressWarnings("unchecked")
public class OxpointsGabotoOntologyLookup implements OntologyLookup {
  private static Map<String,String> entityClassLookupNames;
  private static Map<String,Class<? extends GabotoEntity>> entityClassLookupClass;
  private static Map<Class<? extends GabotoEntity>, String> classToURILookup;
  private static Collection<String> entityClassNames;
  private static Set<String> entityTypes;

  static{
    entityClassLookupNames = new HashMap<String,String>();

    entityClassLookupNames.put("http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#Department", "Department");
    entityClassLookupNames.put("http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#Portal", "Portal");
    entityClassLookupNames.put("http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#ServiceDepartment", "ServiceDepartment");
    entityClassLookupNames.put("http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#OxpEntity", "OxpEntity");
    entityClassLookupNames.put("http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#Outside", "Outside");
    entityClassLookupNames.put("http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#DrainCover", "DrainCover");
    entityClassLookupNames.put("http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#Museum", "Museum");
    entityClassLookupNames.put("http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#Group", "Group");
    entityClassLookupNames.put("http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#SubLibrary", "SubLibrary");
    entityClassLookupNames.put("http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#Access", "Access");
    entityClassLookupNames.put("http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#Entrance", "Entrance");
    entityClassLookupNames.put("http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#Unit", "Unit");
    entityClassLookupNames.put("http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#Library", "Library");
    entityClassLookupNames.put("http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#Site", "Site");
    entityClassLookupNames.put("http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#College", "College");
    entityClassLookupNames.put("http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#Carpark", "Carpark");
    entityClassLookupNames.put("http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#Faculty", "Faculty");
    entityClassLookupNames.put("http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#Website", "Website");
    entityClassLookupNames.put("http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#Image", "Image");
    entityClassLookupNames.put("http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#Building", "Building");
    entityClassLookupNames.put("http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#Hall", "Hall");
    entityClassLookupNames.put("http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#Place", "Place");
    entityClassLookupNames.put("http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#Division", "Division");
    entityClassLookupNames.put("http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#WAP", "WAP");
    entityClassLookupNames.put("http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#Room", "Room");
  }

  static{
    entityClassLookupClass = new HashMap<String,Class<? extends GabotoEntity>>();

    try {
      entityClassLookupClass.put("http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#Department", (Class<?  extends GabotoEntity>) Class.forName("uk.ac.ox.oucs.oxpoints.gaboto.entities.Department"));
      entityClassLookupClass.put("http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#Portal", (Class<?  extends GabotoEntity>) Class.forName("uk.ac.ox.oucs.oxpoints.gaboto.entities.Portal"));
      entityClassLookupClass.put("http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#ServiceDepartment", (Class<?  extends GabotoEntity>) Class.forName("uk.ac.ox.oucs.oxpoints.gaboto.entities.ServiceDepartment"));
      entityClassLookupClass.put("http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#OxpEntity", (Class<?  extends GabotoEntity>) Class.forName("uk.ac.ox.oucs.oxpoints.gaboto.entities.OxpEntity"));
      entityClassLookupClass.put("http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#Outside", (Class<?  extends GabotoEntity>) Class.forName("uk.ac.ox.oucs.oxpoints.gaboto.entities.Outside"));
      entityClassLookupClass.put("http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#DrainCover", (Class<?  extends GabotoEntity>) Class.forName("uk.ac.ox.oucs.oxpoints.gaboto.entities.DrainCover"));
      entityClassLookupClass.put("http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#Museum", (Class<?  extends GabotoEntity>) Class.forName("uk.ac.ox.oucs.oxpoints.gaboto.entities.Museum"));
      entityClassLookupClass.put("http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#Group", (Class<?  extends GabotoEntity>) Class.forName("uk.ac.ox.oucs.oxpoints.gaboto.entities.Group"));
      entityClassLookupClass.put("http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#SubLibrary", (Class<?  extends GabotoEntity>) Class.forName("uk.ac.ox.oucs.oxpoints.gaboto.entities.SubLibrary"));
      entityClassLookupClass.put("http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#Access", (Class<?  extends GabotoEntity>) Class.forName("uk.ac.ox.oucs.oxpoints.gaboto.entities.Access"));
      entityClassLookupClass.put("http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#Entrance", (Class<?  extends GabotoEntity>) Class.forName("uk.ac.ox.oucs.oxpoints.gaboto.entities.Entrance"));
      entityClassLookupClass.put("http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#Unit", (Class<?  extends GabotoEntity>) Class.forName("uk.ac.ox.oucs.oxpoints.gaboto.entities.Unit"));
      entityClassLookupClass.put("http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#Library", (Class<?  extends GabotoEntity>) Class.forName("uk.ac.ox.oucs.oxpoints.gaboto.entities.Library"));
      entityClassLookupClass.put("http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#Site", (Class<?  extends GabotoEntity>) Class.forName("uk.ac.ox.oucs.oxpoints.gaboto.entities.Site"));
      entityClassLookupClass.put("http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#College", (Class<?  extends GabotoEntity>) Class.forName("uk.ac.ox.oucs.oxpoints.gaboto.entities.College"));
      entityClassLookupClass.put("http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#Carpark", (Class<?  extends GabotoEntity>) Class.forName("uk.ac.ox.oucs.oxpoints.gaboto.entities.Carpark"));
      entityClassLookupClass.put("http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#Faculty", (Class<?  extends GabotoEntity>) Class.forName("uk.ac.ox.oucs.oxpoints.gaboto.entities.Faculty"));
      entityClassLookupClass.put("http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#Website", (Class<?  extends GabotoEntity>) Class.forName("uk.ac.ox.oucs.oxpoints.gaboto.entities.Website"));
      entityClassLookupClass.put("http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#Image", (Class<?  extends GabotoEntity>) Class.forName("uk.ac.ox.oucs.oxpoints.gaboto.entities.Image"));
      entityClassLookupClass.put("http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#Building", (Class<?  extends GabotoEntity>) Class.forName("uk.ac.ox.oucs.oxpoints.gaboto.entities.Building"));
      entityClassLookupClass.put("http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#Hall", (Class<?  extends GabotoEntity>) Class.forName("uk.ac.ox.oucs.oxpoints.gaboto.entities.Hall"));
      entityClassLookupClass.put("http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#Place", (Class<?  extends GabotoEntity>) Class.forName("uk.ac.ox.oucs.oxpoints.gaboto.entities.Place"));
      entityClassLookupClass.put("http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#Division", (Class<?  extends GabotoEntity>) Class.forName("uk.ac.ox.oucs.oxpoints.gaboto.entities.Division"));
      entityClassLookupClass.put("http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#WAP", (Class<?  extends GabotoEntity>) Class.forName("uk.ac.ox.oucs.oxpoints.gaboto.entities.WAP"));
      entityClassLookupClass.put("http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#Room", (Class<?  extends GabotoEntity>) Class.forName("uk.ac.ox.oucs.oxpoints.gaboto.entities.Room"));
    } catch (ClassNotFoundException e) {
      throw new GabotoRuntimeException(e);
    }
  }

  static{
    classToURILookup = new HashMap<Class<? extends GabotoEntity>, String>();

    try {
      classToURILookup.put((Class<?  extends GabotoEntity>) Class.forName("uk.ac.ox.oucs.oxpoints.gaboto.entities.Department"), "http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#Department");
      classToURILookup.put((Class<?  extends GabotoEntity>) Class.forName("uk.ac.ox.oucs.oxpoints.gaboto.entities.Portal"), "http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#Portal");
      classToURILookup.put((Class<?  extends GabotoEntity>) Class.forName("uk.ac.ox.oucs.oxpoints.gaboto.entities.ServiceDepartment"), "http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#ServiceDepartment");
      classToURILookup.put((Class<?  extends GabotoEntity>) Class.forName("uk.ac.ox.oucs.oxpoints.gaboto.entities.OxpEntity"), "http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#OxpEntity");
      classToURILookup.put((Class<?  extends GabotoEntity>) Class.forName("uk.ac.ox.oucs.oxpoints.gaboto.entities.Outside"), "http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#Outside");
      classToURILookup.put((Class<?  extends GabotoEntity>) Class.forName("uk.ac.ox.oucs.oxpoints.gaboto.entities.DrainCover"), "http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#DrainCover");
      classToURILookup.put((Class<?  extends GabotoEntity>) Class.forName("uk.ac.ox.oucs.oxpoints.gaboto.entities.Museum"), "http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#Museum");
      classToURILookup.put((Class<?  extends GabotoEntity>) Class.forName("uk.ac.ox.oucs.oxpoints.gaboto.entities.Group"), "http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#Group");
      classToURILookup.put((Class<?  extends GabotoEntity>) Class.forName("uk.ac.ox.oucs.oxpoints.gaboto.entities.SubLibrary"), "http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#SubLibrary");
      classToURILookup.put((Class<?  extends GabotoEntity>) Class.forName("uk.ac.ox.oucs.oxpoints.gaboto.entities.Access"), "http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#Access");
      classToURILookup.put((Class<?  extends GabotoEntity>) Class.forName("uk.ac.ox.oucs.oxpoints.gaboto.entities.Entrance"), "http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#Entrance");
      classToURILookup.put((Class<?  extends GabotoEntity>) Class.forName("uk.ac.ox.oucs.oxpoints.gaboto.entities.Unit"), "http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#Unit");
      classToURILookup.put((Class<?  extends GabotoEntity>) Class.forName("uk.ac.ox.oucs.oxpoints.gaboto.entities.Library"), "http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#Library");
      classToURILookup.put((Class<?  extends GabotoEntity>) Class.forName("uk.ac.ox.oucs.oxpoints.gaboto.entities.Site"), "http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#Site");
      classToURILookup.put((Class<?  extends GabotoEntity>) Class.forName("uk.ac.ox.oucs.oxpoints.gaboto.entities.College"), "http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#College");
      classToURILookup.put((Class<?  extends GabotoEntity>) Class.forName("uk.ac.ox.oucs.oxpoints.gaboto.entities.Carpark"), "http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#Carpark");
      classToURILookup.put((Class<?  extends GabotoEntity>) Class.forName("uk.ac.ox.oucs.oxpoints.gaboto.entities.Faculty"), "http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#Faculty");
      classToURILookup.put((Class<?  extends GabotoEntity>) Class.forName("uk.ac.ox.oucs.oxpoints.gaboto.entities.Website"), "http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#Website");
      classToURILookup.put((Class<?  extends GabotoEntity>) Class.forName("uk.ac.ox.oucs.oxpoints.gaboto.entities.Image"), "http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#Image");
      classToURILookup.put((Class<?  extends GabotoEntity>) Class.forName("uk.ac.ox.oucs.oxpoints.gaboto.entities.Building"), "http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#Building");
      classToURILookup.put((Class<?  extends GabotoEntity>) Class.forName("uk.ac.ox.oucs.oxpoints.gaboto.entities.Hall"), "http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#Hall");
      classToURILookup.put((Class<?  extends GabotoEntity>) Class.forName("uk.ac.ox.oucs.oxpoints.gaboto.entities.Place"), "http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#Place");
      classToURILookup.put((Class<?  extends GabotoEntity>) Class.forName("uk.ac.ox.oucs.oxpoints.gaboto.entities.Division"), "http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#Division");
      classToURILookup.put((Class<?  extends GabotoEntity>) Class.forName("uk.ac.ox.oucs.oxpoints.gaboto.entities.WAP"), "http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#WAP");
      classToURILookup.put((Class<?  extends GabotoEntity>) Class.forName("uk.ac.ox.oucs.oxpoints.gaboto.entities.Room"), "http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#Room");
    } catch (ClassNotFoundException e) {
      throw new GabotoRuntimeException(e);
    }
  }

  static{
    entityTypes = new HashSet<String>();

    entityTypes.add("http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#Department");
    entityTypes.add("http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#Portal");
    entityTypes.add("http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#ServiceDepartment");
    entityTypes.add("http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#OxpEntity");
    entityTypes.add("http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#Outside");
    entityTypes.add("http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#DrainCover");
    entityTypes.add("http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#Museum");
    entityTypes.add("http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#Group");
    entityTypes.add("http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#SubLibrary");
    entityTypes.add("http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#Access");
    entityTypes.add("http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#Entrance");
    entityTypes.add("http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#Unit");
    entityTypes.add("http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#Library");
    entityTypes.add("http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#Site");
    entityTypes.add("http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#College");
    entityTypes.add("http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#Carpark");
    entityTypes.add("http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#Faculty");
    entityTypes.add("http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#Website");
    entityTypes.add("http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#Image");
    entityTypes.add("http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#Building");
    entityTypes.add("http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#Hall");
    entityTypes.add("http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#Place");
    entityTypes.add("http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#Division");
    entityTypes.add("http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#WAP");
    entityTypes.add("http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#Room");
  }

  static{
    entityClassNames = new HashSet<String>();

    entityClassNames.add("OxpEntity");
    entityClassNames.add("Access");
    entityClassNames.add("Image");
    entityClassNames.add("Place");
    entityClassNames.add("Outside");
    entityClassNames.add("Portal");
    entityClassNames.add("Building");
    entityClassNames.add("Carpark");
    entityClassNames.add("DrainCover");
    entityClassNames.add("Entrance");
    entityClassNames.add("Room");
    entityClassNames.add("Site");
    entityClassNames.add("WAP");
    entityClassNames.add("Unit");
    entityClassNames.add("College");
    entityClassNames.add("Hall");
    entityClassNames.add("Department");
    entityClassNames.add("Division");
    entityClassNames.add("Faculty");
    entityClassNames.add("Group");
    entityClassNames.add("Library");
    entityClassNames.add("SubLibrary");
    entityClassNames.add("Museum");
    entityClassNames.add("ServiceDepartment");
    entityClassNames.add("Website");
  }

  public Set<String> getRegisteredClassesAsURIs(){
    return entityTypes;
  }

  public Collection<String> getRegisteredEntityClassesAsClassNames(){
    return entityClassNames;
  }

  public Class<? extends GabotoEntity> getEntityClassFor(String typeURI){
    return entityClassLookupClass.get(typeURI);
  }

  public String getLocalName(String typeURI){
    return entityClassLookupNames.get(typeURI);
  }

  public boolean isValidName(String name) {
    return entityClassNames.contains(name);
  }

  public String getTypeURIForEntityClass(Class<? extends GabotoEntity> clazz){
    return classToURILookup.get(clazz);
  }

}
