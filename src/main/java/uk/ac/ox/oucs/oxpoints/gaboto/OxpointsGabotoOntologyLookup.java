package uk.ac.ox.oucs.oxpoints.gaboto;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import net.sf.gaboto.node.GabotoEntity;
import net.sf.gaboto.node.GabotoBean;
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
  private static Map<String,Class<? extends GabotoBean>> beanClassLookupClass;
  private static Map<Class<? extends GabotoEntity>, String> classToURILookup;
  private static Collection<String> entityClassNames;
  private static Set<String> entityTypes;

  private static Map<String,String> nameToURILookup;
  static{
    entityClassLookupNames = new HashMap<String,String>();

    entityClassLookupNames.put("http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#OpenSpace", "OpenSpace");
    entityClassLookupNames.put("http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#Department", "Department");
    entityClassLookupNames.put("http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#StudentGroup", "StudentGroup");
    entityClassLookupNames.put("http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#ServiceDepartment", "ServiceDepartment");
    entityClassLookupNames.put("http://xmlns.com/foaf/0.1/Image", "Image");
    entityClassLookupNames.put("http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#Space", "Space");
    entityClassLookupNames.put("http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#OxpEntity", "OxpEntity");
    entityClassLookupNames.put("http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#Outside", "Outside");
    entityClassLookupNames.put("http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#DrainCover", "DrainCover");
    entityClassLookupNames.put("http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#Museum", "Museum");
    entityClassLookupNames.put("http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#Group", "Group");
    entityClassLookupNames.put("http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#Quadrangle", "Quadrangle");
    entityClassLookupNames.put("http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#SubLibrary", "SubLibrary");
    entityClassLookupNames.put("http://www.w3.org/ns/org#Organization", "Organization");
    entityClassLookupNames.put("http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#Unit", "Unit");
    entityClassLookupNames.put("http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#Library", "Library");
    entityClassLookupNames.put("http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#Site", "Site");
    entityClassLookupNames.put("http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#College", "College");
    entityClassLookupNames.put("http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#Carpark", "Carpark");
    entityClassLookupNames.put("http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#Faculty", "Faculty");
    entityClassLookupNames.put("http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#Website", "Website");
    entityClassLookupNames.put("http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#University", "University");
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
      entityClassLookupClass.put("http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#OpenSpace", (Class<?  extends GabotoEntity>) Class.forName("uk.ac.ox.oucs.oxpoints.gaboto.entities.OpenSpace"));
      entityClassLookupClass.put("http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#Department", (Class<?  extends GabotoEntity>) Class.forName("uk.ac.ox.oucs.oxpoints.gaboto.entities.Department"));
      entityClassLookupClass.put("http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#StudentGroup", (Class<?  extends GabotoEntity>) Class.forName("uk.ac.ox.oucs.oxpoints.gaboto.entities.StudentGroup"));
      entityClassLookupClass.put("http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#ServiceDepartment", (Class<?  extends GabotoEntity>) Class.forName("uk.ac.ox.oucs.oxpoints.gaboto.entities.ServiceDepartment"));
      entityClassLookupClass.put("http://xmlns.com/foaf/0.1/Image", (Class<?  extends GabotoEntity>) Class.forName("uk.ac.ox.oucs.oxpoints.gaboto.entities.Image"));
      entityClassLookupClass.put("http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#Space", (Class<?  extends GabotoEntity>) Class.forName("uk.ac.ox.oucs.oxpoints.gaboto.entities.Space"));
      entityClassLookupClass.put("http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#OxpEntity", (Class<?  extends GabotoEntity>) Class.forName("uk.ac.ox.oucs.oxpoints.gaboto.entities.OxpEntity"));
      entityClassLookupClass.put("http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#Outside", (Class<?  extends GabotoEntity>) Class.forName("uk.ac.ox.oucs.oxpoints.gaboto.entities.Outside"));
      entityClassLookupClass.put("http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#DrainCover", (Class<?  extends GabotoEntity>) Class.forName("uk.ac.ox.oucs.oxpoints.gaboto.entities.DrainCover"));
      entityClassLookupClass.put("http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#Museum", (Class<?  extends GabotoEntity>) Class.forName("uk.ac.ox.oucs.oxpoints.gaboto.entities.Museum"));
      entityClassLookupClass.put("http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#Group", (Class<?  extends GabotoEntity>) Class.forName("uk.ac.ox.oucs.oxpoints.gaboto.entities.Group"));
      entityClassLookupClass.put("http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#Quadrangle", (Class<?  extends GabotoEntity>) Class.forName("uk.ac.ox.oucs.oxpoints.gaboto.entities.Quadrangle"));
      entityClassLookupClass.put("http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#SubLibrary", (Class<?  extends GabotoEntity>) Class.forName("uk.ac.ox.oucs.oxpoints.gaboto.entities.SubLibrary"));
      entityClassLookupClass.put("http://www.w3.org/ns/org#Organization", (Class<?  extends GabotoEntity>) Class.forName("uk.ac.ox.oucs.oxpoints.gaboto.entities.Organization"));
      entityClassLookupClass.put("http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#Unit", (Class<?  extends GabotoEntity>) Class.forName("uk.ac.ox.oucs.oxpoints.gaboto.entities.Unit"));
      entityClassLookupClass.put("http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#Library", (Class<?  extends GabotoEntity>) Class.forName("uk.ac.ox.oucs.oxpoints.gaboto.entities.Library"));
      entityClassLookupClass.put("http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#Site", (Class<?  extends GabotoEntity>) Class.forName("uk.ac.ox.oucs.oxpoints.gaboto.entities.Site"));
      entityClassLookupClass.put("http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#College", (Class<?  extends GabotoEntity>) Class.forName("uk.ac.ox.oucs.oxpoints.gaboto.entities.College"));
      entityClassLookupClass.put("http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#Carpark", (Class<?  extends GabotoEntity>) Class.forName("uk.ac.ox.oucs.oxpoints.gaboto.entities.Carpark"));
      entityClassLookupClass.put("http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#Faculty", (Class<?  extends GabotoEntity>) Class.forName("uk.ac.ox.oucs.oxpoints.gaboto.entities.Faculty"));
      entityClassLookupClass.put("http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#Website", (Class<?  extends GabotoEntity>) Class.forName("uk.ac.ox.oucs.oxpoints.gaboto.entities.Website"));
      entityClassLookupClass.put("http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#University", (Class<?  extends GabotoEntity>) Class.forName("uk.ac.ox.oucs.oxpoints.gaboto.entities.University"));
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
    beanClassLookupClass = new HashMap<String,Class<? extends GabotoBean>>();

    try {
      beanClassLookupClass.put("http://xmlns.com/foaf/0.1/OnlineAccount", (Class<?  extends GabotoBean>) Class.forName("uk.ac.ox.oucs.oxpoints.gaboto.beans.OnlineAccount"));
      beanClassLookupClass.put("http://www.w3.org/2006/vcard/ns#Tel", (Class<?  extends GabotoBean>) Class.forName("uk.ac.ox.oucs.oxpoints.gaboto.beans.Tel"));
      beanClassLookupClass.put("http://www.w3.org/2006/vcard/ns#Voice", (Class<?  extends GabotoBean>) Class.forName("uk.ac.ox.oucs.oxpoints.gaboto.beans.Voice"));
      beanClassLookupClass.put("http://www.w3.org/2006/vcard/ns#Address", (Class<?  extends GabotoBean>) Class.forName("uk.ac.ox.oucs.oxpoints.gaboto.beans.Address"));
      beanClassLookupClass.put("http://www.w3.org/2006/vcard/ns#Fax", (Class<?  extends GabotoBean>) Class.forName("uk.ac.ox.oucs.oxpoints.gaboto.beans.Fax"));
    } catch (ClassNotFoundException e) {
      throw new GabotoRuntimeException(e);
    }
  }

  static{
    classToURILookup = new HashMap<Class<? extends GabotoEntity>, String>();

    try {
      classToURILookup.put((Class<?  extends GabotoEntity>) Class.forName("uk.ac.ox.oucs.oxpoints.gaboto.entities.OpenSpace"), "http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#OpenSpace");
      classToURILookup.put((Class<?  extends GabotoEntity>) Class.forName("uk.ac.ox.oucs.oxpoints.gaboto.entities.Department"), "http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#Department");
      classToURILookup.put((Class<?  extends GabotoEntity>) Class.forName("uk.ac.ox.oucs.oxpoints.gaboto.entities.StudentGroup"), "http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#StudentGroup");
      classToURILookup.put((Class<?  extends GabotoEntity>) Class.forName("uk.ac.ox.oucs.oxpoints.gaboto.entities.ServiceDepartment"), "http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#ServiceDepartment");
      classToURILookup.put((Class<?  extends GabotoEntity>) Class.forName("uk.ac.ox.oucs.oxpoints.gaboto.entities.Image"), "http://xmlns.com/foaf/0.1/Image");
      classToURILookup.put((Class<?  extends GabotoEntity>) Class.forName("uk.ac.ox.oucs.oxpoints.gaboto.entities.Space"), "http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#Space");
      classToURILookup.put((Class<?  extends GabotoEntity>) Class.forName("uk.ac.ox.oucs.oxpoints.gaboto.entities.OxpEntity"), "http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#OxpEntity");
      classToURILookup.put((Class<?  extends GabotoEntity>) Class.forName("uk.ac.ox.oucs.oxpoints.gaboto.entities.Outside"), "http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#Outside");
      classToURILookup.put((Class<?  extends GabotoEntity>) Class.forName("uk.ac.ox.oucs.oxpoints.gaboto.entities.DrainCover"), "http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#DrainCover");
      classToURILookup.put((Class<?  extends GabotoEntity>) Class.forName("uk.ac.ox.oucs.oxpoints.gaboto.entities.Museum"), "http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#Museum");
      classToURILookup.put((Class<?  extends GabotoEntity>) Class.forName("uk.ac.ox.oucs.oxpoints.gaboto.entities.Group"), "http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#Group");
      classToURILookup.put((Class<?  extends GabotoEntity>) Class.forName("uk.ac.ox.oucs.oxpoints.gaboto.entities.Quadrangle"), "http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#Quadrangle");
      classToURILookup.put((Class<?  extends GabotoEntity>) Class.forName("uk.ac.ox.oucs.oxpoints.gaboto.entities.SubLibrary"), "http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#SubLibrary");
      classToURILookup.put((Class<?  extends GabotoEntity>) Class.forName("uk.ac.ox.oucs.oxpoints.gaboto.entities.Organization"), "http://www.w3.org/ns/org#Organization");
      classToURILookup.put((Class<?  extends GabotoEntity>) Class.forName("uk.ac.ox.oucs.oxpoints.gaboto.entities.Unit"), "http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#Unit");
      classToURILookup.put((Class<?  extends GabotoEntity>) Class.forName("uk.ac.ox.oucs.oxpoints.gaboto.entities.Library"), "http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#Library");
      classToURILookup.put((Class<?  extends GabotoEntity>) Class.forName("uk.ac.ox.oucs.oxpoints.gaboto.entities.Site"), "http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#Site");
      classToURILookup.put((Class<?  extends GabotoEntity>) Class.forName("uk.ac.ox.oucs.oxpoints.gaboto.entities.College"), "http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#College");
      classToURILookup.put((Class<?  extends GabotoEntity>) Class.forName("uk.ac.ox.oucs.oxpoints.gaboto.entities.Carpark"), "http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#Carpark");
      classToURILookup.put((Class<?  extends GabotoEntity>) Class.forName("uk.ac.ox.oucs.oxpoints.gaboto.entities.Faculty"), "http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#Faculty");
      classToURILookup.put((Class<?  extends GabotoEntity>) Class.forName("uk.ac.ox.oucs.oxpoints.gaboto.entities.Website"), "http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#Website");
      classToURILookup.put((Class<?  extends GabotoEntity>) Class.forName("uk.ac.ox.oucs.oxpoints.gaboto.entities.University"), "http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#University");
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

  static {
    nameToURILookup = new HashMap<String,String>();
    nameToURILookup.put("OpenSpace", "http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#OpenSpace");
    nameToURILookup.put("Department", "http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#Department");
    nameToURILookup.put("StudentGroup", "http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#StudentGroup");
    nameToURILookup.put("ServiceDepartment", "http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#ServiceDepartment");
    nameToURILookup.put("Image", "http://xmlns.com/foaf/0.1/Image");
    nameToURILookup.put("Space", "http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#Space");
    nameToURILookup.put("OxpEntity", "http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#OxpEntity");
    nameToURILookup.put("Outside", "http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#Outside");
    nameToURILookup.put("DrainCover", "http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#DrainCover");
    nameToURILookup.put("Museum", "http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#Museum");
    nameToURILookup.put("Group", "http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#Group");
    nameToURILookup.put("Quadrangle", "http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#Quadrangle");
    nameToURILookup.put("SubLibrary", "http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#SubLibrary");
    nameToURILookup.put("Organization", "http://www.w3.org/ns/org#Organization");
    nameToURILookup.put("Unit", "http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#Unit");
    nameToURILookup.put("Library", "http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#Library");
    nameToURILookup.put("Site", "http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#Site");
    nameToURILookup.put("College", "http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#College");
    nameToURILookup.put("Carpark", "http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#Carpark");
    nameToURILookup.put("Faculty", "http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#Faculty");
    nameToURILookup.put("Website", "http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#Website");
    nameToURILookup.put("University", "http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#University");
    nameToURILookup.put("Building", "http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#Building");
    nameToURILookup.put("Hall", "http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#Hall");
    nameToURILookup.put("Place", "http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#Place");
    nameToURILookup.put("Division", "http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#Division");
    nameToURILookup.put("WAP", "http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#WAP");
    nameToURILookup.put("Room", "http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#Room");
  }

  static{
    entityTypes = new HashSet<String>();

    entityTypes.add("http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#OpenSpace");
    entityTypes.add("http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#Department");
    entityTypes.add("http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#StudentGroup");
    entityTypes.add("http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#ServiceDepartment");
    entityTypes.add("http://xmlns.com/foaf/0.1/Image");
    entityTypes.add("http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#Space");
    entityTypes.add("http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#OxpEntity");
    entityTypes.add("http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#Outside");
    entityTypes.add("http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#DrainCover");
    entityTypes.add("http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#Museum");
    entityTypes.add("http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#Group");
    entityTypes.add("http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#Quadrangle");
    entityTypes.add("http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#SubLibrary");
    entityTypes.add("http://www.w3.org/ns/org#Organization");
    entityTypes.add("http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#Unit");
    entityTypes.add("http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#Library");
    entityTypes.add("http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#Site");
    entityTypes.add("http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#College");
    entityTypes.add("http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#Carpark");
    entityTypes.add("http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#Faculty");
    entityTypes.add("http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#Website");
    entityTypes.add("http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#University");
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
    entityClassNames.add("Image");
    entityClassNames.add("Place");
    entityClassNames.add("Outside");
    entityClassNames.add("Building");
    entityClassNames.add("Carpark");
    entityClassNames.add("DrainCover");
    entityClassNames.add("Room");
    entityClassNames.add("Site");
    entityClassNames.add("Space");
    entityClassNames.add("WAP");
    entityClassNames.add("OpenSpace");
    entityClassNames.add("Quadrangle");
    entityClassNames.add("Organization");
    entityClassNames.add("Unit");
    entityClassNames.add("College");
    entityClassNames.add("University");
    entityClassNames.add("Hall");
    entityClassNames.add("Department");
    entityClassNames.add("Division");
    entityClassNames.add("Faculty");
    entityClassNames.add("Group");
    entityClassNames.add("Library");
    entityClassNames.add("SubLibrary");
    entityClassNames.add("Museum");
    entityClassNames.add("ServiceDepartment");
    entityClassNames.add("StudentGroup");
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

  public Class<? extends GabotoBean> getBeanClassFor(String typeURI){
    return beanClassLookupClass.get(typeURI);
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

  public String getURIForName(String name){
    return nameToURILookup.get(name);
  }

}
