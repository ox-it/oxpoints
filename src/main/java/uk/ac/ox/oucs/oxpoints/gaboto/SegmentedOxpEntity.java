package uk.ac.ox.oucs.oxpoints.gaboto;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.w3c.dom.Element;

import net.sf.gaboto.EntityAlreadyExistsException;
import net.sf.gaboto.Gaboto;
import net.sf.gaboto.GabotoRuntimeException;
import net.sf.gaboto.time.ImmutableTimeInstant;
import net.sf.gaboto.time.TimeInstant;
import net.sf.gaboto.time.TimeSpan;

import uk.ac.ox.oucs.oxpoints.gaboto.entities.Building;
import uk.ac.ox.oucs.oxpoints.gaboto.entities.Carpark;
import uk.ac.ox.oucs.oxpoints.gaboto.entities.College;
import uk.ac.ox.oucs.oxpoints.gaboto.entities.Department;
import uk.ac.ox.oucs.oxpoints.gaboto.entities.Division;
import uk.ac.ox.oucs.oxpoints.gaboto.entities.DrainCover;
import uk.ac.ox.oucs.oxpoints.gaboto.entities.Faculty;
import uk.ac.ox.oucs.oxpoints.gaboto.entities.Group;
import uk.ac.ox.oucs.oxpoints.gaboto.entities.Library;
import uk.ac.ox.oucs.oxpoints.gaboto.entities.Museum;
import uk.ac.ox.oucs.oxpoints.gaboto.entities.OxpEntity;
import uk.ac.ox.oucs.oxpoints.gaboto.entities.Place;
import uk.ac.ox.oucs.oxpoints.gaboto.entities.Room;
import uk.ac.ox.oucs.oxpoints.gaboto.entities.SubLibrary;
import uk.ac.ox.oucs.oxpoints.gaboto.entities.WAP;

public class SegmentedOxpEntity {
	private String oxpID;
	private String type;
	private String uri;
	private Gaboto gaboto;
	private Class<? extends OxpEntity> entityClass;
	private OxpEntity proxy;
	
	private TimeInstant from;
	private TimeInstant to;
	
	private static final Map<String,Class<? extends OxpEntity>> typeMap = getTypeMap();
	private static final Set<Relation> relations = new HashSet<Relation>();
	private final Set<Property> properties = new HashSet<Property>();
	
	public SegmentedOxpEntity(Gaboto gaboto, String type, String oxpID, Element element) {
		this(
				gaboto, type, oxpID,
				new ImmutableTimeInstant(element.getAttribute("inferredFrom")),
				new ImmutableTimeInstant(element.getAttribute("inferredTo")));
	}

	public SegmentedOxpEntity(Gaboto gaboto, String type, String oxpID, TimeInstant from, TimeInstant to) {
		this.type = type;
		this.oxpID = oxpID;
		this.uri = gaboto.getConfig().getNSData()+oxpID;
		this.gaboto = gaboto;
		
		this.from = from;
		this.to = to;
		
		entityClass = typeMap.get(type);
		System.out.println("NEW "+oxpID);
		
		try {
			proxy = entityClass.newInstance();
			proxy.setUri(uri);
		} catch (InstantiationException e) {
			throw new GabotoRuntimeException();
		} catch (IllegalAccessException e) {
			throw new GabotoRuntimeException();
		}
	}
	
	public String getType() {
		return type;
	}
	public Class<? extends OxpEntity> getEntityClass() {
		return entityClass;
	}
	
	public boolean instanceOf(Class<?> c) {
		return c.isAssignableFrom(getEntityClass());
	}
	
	public void addProperty(String property, Element element) {
		addProperty(
				property,
				element.getTextContent(),
				new ImmutableTimeInstant(element.getAttribute("inferredFrom")),
				new ImmutableTimeInstant(element.getAttribute("inferredTo"))
				);
				
	}
	
	public void addProperty(String property, Object value, Element element) {
		addProperty(
				property,
				value,
				new ImmutableTimeInstant(element.getAttribute("inferredFrom")),
				new ImmutableTimeInstant(element.getAttribute("inferredTo"))
				);
	}
	
	public void addProperty(String property, Object value) {
		addProperty(property, value, TimeSpan.BIG_BANG, TimeSpan.DOOMS_DAY);
	}
	public void addRelation(String property, String passiveOxpID, Class<? extends OxpEntity> argumentClass) {
		addRelation(property, passiveOxpID, TimeSpan.BIG_BANG, TimeSpan.DOOMS_DAY, argumentClass, false);
	}
	
	public void addRelation(String property, String passiveOxpID, Element element, Class<? extends OxpEntity> argumentClass) {
		addRelation(property, passiveOxpID, 
				new ImmutableTimeInstant(element.getAttribute("inferredFrom")),
				new ImmutableTimeInstant(element.getAttribute("inferredTo")), argumentClass, false);
	}
	
	public void addRelation(String property, String passiveOxpID, Element element, Class<? extends OxpEntity> argumentClass, boolean inverted) {
		addRelation(property, passiveOxpID, 
				new ImmutableTimeInstant(element.getAttribute("inferredFrom")),
				new ImmutableTimeInstant(element.getAttribute("inferredTo")), argumentClass, inverted);
	}
	
	public void addProperty(String property, Object value, TimeInstant start, TimeInstant end) {
		System.out.println("PRO "+property+", "+value.toString()+", "+start.toString()+", "+end.toString());
		
		properties.add(new Property(property, value, start, end));
	}
	
	public void addRelation(String property, String passiveOxpID, TimeInstant start, TimeInstant end, Class<? extends OxpEntity>argumentClass, boolean inverted) {
		System.out.println("REL "+property+", "+passiveOxpID+", "+start.toString()+", "+end.toString());
		
		if (inverted)
			relations.add(new Relation(passiveOxpID, oxpID, property, start, end, argumentClass));
		else
			relations.add(new Relation(oxpID, passiveOxpID, property, start, end, argumentClass));
		
	}
	
	public OxpEntity getProxy() {
		return proxy;
	}
	
	public static void constrainRelations(Map<String,SegmentedOxpEntity> entityMapping) {
		Set<Relation> relationsToRemove = new HashSet<Relation>();
		
		for (Relation relation : relations) {
			SegmentedOxpEntity active = entityMapping.get(relation.active);
			SegmentedOxpEntity passive = entityMapping.get(relation.passive);
			
			TimeInstant from = SeparatedTEIImporter.latest(active.from, passive.from);
			relation.from = SeparatedTEIImporter.latest(relation.from, from);

			TimeInstant to = SeparatedTEIImporter.latest(active.to, passive.to);
			relation.to = SeparatedTEIImporter.latest(relation.to, to);
			
			if (relation.from.compareTo(relation.to) != -1)
				relationsToRemove.add(relation);
		}
		
		for (Relation relation : relationsToRemove)
			relations.remove(relation);
	}
	
	public void addToGaboto(Map<String,SegmentedOxpEntity> entityMapping) {
		Set<TimeInstant> instants = new TreeSet<TimeInstant> ();
		Map<TimeInstant,Integer> instantOffsets = new HashMap<TimeInstant,Integer>();
		
		for (Property property : properties) {
			instants.add(property.from);
			instants.add(property.to);
		}
		
		for (Relation relation : relations) {
			if (relation.active != oxpID)
				continue;
				
			instants.add(relation.from);
			instants.add(relation.to);
		}
		
		OxpEntity[] entities = new OxpEntity[instants.size()];
		TimeInstant[] instantArray = instants.toArray(new TimeInstant[instants.size()]); 
		
		for (int i=0; i<instantArray.length; i++) {
			TimeInstant instant = instantArray[i];
			instantOffsets.put(instant, i);
			if (i==instantArray.length-1)
				break;
			try {
				entities[i] = entityClass.newInstance();
			} catch (InstantiationException e) {
				throw new GabotoRuntimeException();
			} catch (IllegalAccessException e) {
				throw new GabotoRuntimeException();
			}
			entities[i].setTimeSpan(TimeSpan.createFromInstants(instant.clone(), instantArray[i+1].clone()));
			entities[i].setUri(uri);
		}
		
		for (Property property : properties) {
			try {
				Method m = entityClass.getMethod(property.name, property.value.getClass());
				for (int i=instantOffsets.get(property.from); i<instantOffsets.get(property.to); i++)
					m.invoke(entities[i], property.value);
			} catch (SecurityException e) {
				throw new GabotoRuntimeException();
			} catch (NoSuchMethodException e) {
				throw new GabotoRuntimeException();
			} catch (IllegalArgumentException e) {
				throw new GabotoRuntimeException();
			} catch (IllegalAccessException e) {
				throw new GabotoRuntimeException();
			} catch (InvocationTargetException e) {
				throw new GabotoRuntimeException();
			}
		}
		
		for (Relation relation : relations) {
			if (relation.active != oxpID)
				continue;
			
			try {
				OxpEntity proxy = entityMapping.get(relation.passive).getProxy();
				Method[] methods = entityClass.getMethods();
				Method m = entityClass.getMethod(relation.name, (relation.name.equals("addOccupiedBuilding")) ? Building.class : Place.class); //proxy.getClass());
				for (int i=instantOffsets.get(relation.from); i<instantOffsets.get(relation.to); i++)
					m.invoke(entities[i], proxy);
			} catch (SecurityException e) {
				throw new GabotoRuntimeException();
			} catch (NoSuchMethodException e) {
				throw new GabotoRuntimeException();
			} catch (IllegalArgumentException e) {
				throw new GabotoRuntimeException();
			} catch (IllegalAccessException e) {
				throw new GabotoRuntimeException();
			} catch (InvocationTargetException e) {
				throw new GabotoRuntimeException();
			}
		}
		
		for (int i=0; i<entities.length-1; i++) {
			OxpEntity entity = entities[i];
			try {
				gaboto.add(entity, true, true);
			} catch (EntityAlreadyExistsException e) {
				throw new GabotoRuntimeException();
			}
		}
	}
	
	private static  Map<String,Class<? extends OxpEntity>> getTypeMap() {
		Map<String,Class<? extends OxpEntity>> map = new HashMap<String,Class<? extends OxpEntity>>();
		
		map.put("college", College.class);
		map.put("department", Department.class);
		map.put("faculty", Faculty.class);
		map.put("room", Room.class);
		map.put("division", Division.class);
		map.put("drain_cover", DrainCover.class);
		map.put("building", Building.class);
		map.put("library", Library.class);
		map.put("sublibrary", SubLibrary.class);
		map.put("carpark", Carpark.class);
		map.put("wap", WAP.class);
		map.put("museum", Museum.class);
		map.put("group", Group.class);
		
		return map;
	}
	
	private class Relation {
		String active;
		String passive;
		String name;
		TimeInstant from;
		TimeInstant to;
		Class<? extends OxpEntity> argumentClass;
		
		public Relation(String active, String passive, String name, TimeInstant from, TimeInstant to, Class<? extends OxpEntity> argumentClass) {
			this.active = active;
			this.passive = passive;
			this.name = name;
			this.from = from;
			this.to = to;
			this.argumentClass = argumentClass;
		}
	}
	
	private class Property {
		String name;
		Object value;
		TimeInstant from;
		TimeInstant to;
		
		public Property(String name, Object value, TimeInstant from, TimeInstant to) {
			this.name = name;
			this.value = value;
			this.from = from;
			this.to = to;
		}
	}
	
}
