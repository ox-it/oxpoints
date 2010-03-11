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

import uk.ac.ox.oucs.oxpoints.gaboto.entities.OxpEntity;
import uk.ac.ox.oucs.oxpoints.gaboto.entities.Place;
import uk.ac.ox.oucs.oxpoints.gaboto.entities.Unit;

public class SegmentedOxpEntity {
	private String uri;
	private String tagName;
	private Gaboto gaboto;
	
	private TimeInstant from;
	private TimeInstant to;
	private String filename;
	
	private SeparatedTEIImporter.WarningHandler warningHandler;

	private static final Set<Relation> relations = new HashSet<Relation>();
	private static final Map<String,Set<Relation>> relationsByURI = new HashMap<String,Set<Relation>>();
	private final Set<Property> properties = new HashSet<Property>();
	private final Set<TypeSpan> types = new HashSet<TypeSpan>();
	
	public SegmentedOxpEntity(Gaboto gaboto, SeparatedTEIImporter.WarningHandler warningHandler, String oxpID, Element element, String filename) {
		this(
				gaboto, warningHandler, oxpID,
				new ImmutableTimeInstant(element.getAttribute("inferredFrom")),
				new ImmutableTimeInstant(element.getAttribute("inferredTo")),
				element.getTagName(),
				filename);
	}

	public SegmentedOxpEntity(Gaboto gaboto, SeparatedTEIImporter.WarningHandler warningHandler, String uri, TimeInstant from, TimeInstant to, String tagName, String filename) {
		this.gaboto = gaboto;
		
		this.uri = uri;
		
		this.from = from;
		this.to = to;
		
		this.warningHandler = warningHandler;
		
		if (!tagName.equals("org") && !tagName.equals("place") && !tagName.equals("figure"))
			warningHandler.addWarning(filename, "Root element must be either 'place', 'org' or 'figure', not '"+tagName+"'.");
		this.tagName = tagName;
		this.filename = filename;
		
		//System.out.println("NEW "+oxpID);

	}
	
	public void addType(Element element) {
		addType(
			element.getElementsByTagName("desc").item(0).getTextContent(),
			new ImmutableTimeInstant(element.getAttribute("inferredFrom")),
			new ImmutableTimeInstant(element.getAttribute("inferredTo")));
	}
	
	public void addType(String type, TimeInstant start, TimeInstant end) {
		try {
			TypeSpan typeSpan = new TypeSpan(type, start, end);
			if (tagName.equals("place") && !Place.class.isAssignableFrom(typeSpan.entityClass))
				warningHandler.addWarning(filename, "Type '"+typeSpan.typeName+"' not applicable for 'place' element.");
			else if (tagName.equals("org") && !Unit.class.isAssignableFrom(typeSpan.entityClass))
				warningHandler.addWarning(filename, "Type '"+typeSpan.typeName+"' not applicable for 'org' element.");

			types.add(typeSpan);
		} catch (ClassNotFoundException e) {
			warningHandler.addWarning(filename, "Invalid type for entity "+uri+": "+type);
		}
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
		//System.out.println("PRO "+property+", "+value.toString()+", "+start.toString()+", "+end.toString());
		
		properties.add(new Property(property, value, start, end));
	}
	
	public void addRelation(String property, String passiveUri, TimeInstant start, TimeInstant end, Class<? extends OxpEntity>argumentClass, boolean inverted) {
		Relation relation;
		
		if (inverted)
			relation = new Relation(passiveUri, uri, property, start, end, argumentClass);
		else
			relation = new Relation(uri, passiveUri, property, start, end, argumentClass);
		
		relations.add(relation);
		String activeURI = inverted ? passiveUri : uri;
		if (!relationsByURI.containsKey(activeURI))
			relationsByURI.put(activeURI, new HashSet<Relation>());
		relationsByURI.get(activeURI).add(relation);
		
	}
	
	public static void constrainRelations(Map<String,SegmentedOxpEntity> entityMapping, SeparatedTEIImporter.WarningHandler warningHandler) {
		Set<Relation> relationsToRemove = new HashSet<Relation>();
		
		for (Relation relation : relations) {
			SegmentedOxpEntity active = entityMapping.get(relation.active);
			SegmentedOxpEntity passive = entityMapping.get(relation.passive);
			
			if (active == null) {
				warningHandler.addWarning(null, "Entity "+relation.active+" referenced in relation by "+relation.passive+" but is not defined.");
				relationsToRemove.add(relation);
			} else if (passive == null) {
				warningHandler.addWarning(null, "Entity "+relation.passive+" referenced in relation by "+relation.active+" but is not defined.");
				relationsToRemove.add(relation);
			} else {
				TimeInstant from = SeparatedTEIImporter.latest(active.from, passive.from);
				relation.from = SeparatedTEIImporter.latest(relation.from, from);

				TimeInstant to = SeparatedTEIImporter.earliest(active.to, passive.to);
				relation.to = SeparatedTEIImporter.earliest(relation.to, to);

				if (relation.from.compareTo(relation.to) != -1)
					relationsToRemove.add(relation);
			}
		}
		
		for (Relation relation : relationsToRemove) {
			warningHandler.addWarning(null, "Relation between "+relation.active+" and "+relation.passive+" precluded by lack of overlap.");
			relations.remove(relation);
		}
	}
	
	public void addToGaboto(Map<String,SegmentedOxpEntity> entityMapping) {
		Set<TimeInstant> instants = new TreeSet<TimeInstant> ();
		Map<TimeInstant,Integer> instantOffsets = new HashMap<TimeInstant,Integer>();
		
		for (Property property : properties) {
			instants.add(property.from);
			instants.add(property.to);
		}
		
		for (TypeSpan typeSpan : types) {
			instants.add(typeSpan.from);
			instants.add(typeSpan.to);
		}
		
		if (relationsByURI.containsKey(uri))
			for (Relation relation : relationsByURI.get(uri)) {
				instants.add(relation.from);
				instants.add(relation.to);
			}
		
		
		OxpEntity[] entities = new OxpEntity[instants.size()];
		TimeInstant[] instantArray = instants.toArray(new TimeInstant[instants.size()]); 
		TypeSpan[] typeArray = new TypeSpan[instants.size()]; 
		
		for (int i=0; i<instantArray.length; i++) {
			TimeInstant instant = instantArray[i];
			instantOffsets.put(instant, i);
		}
		
		for (TypeSpan typeSpan : types) {
			for (int i=instantOffsets.get(typeSpan.from); i<instantOffsets.get(typeSpan.to); i++)
				typeArray[i] = typeSpan;
		}
		
		
		
		for (int i=0; i<instantArray.length-1; i++) {
			try {
				entities[i] = typeArray[i].entityClass.newInstance();

			} catch (NullPointerException e) {
				warningHandler.addWarning(filename, "Entity "+uri+" has periods of its existence without a type.");
				return;
			} catch (InstantiationException e) {
				throw new GabotoRuntimeException();
			} catch (IllegalAccessException e) {
				throw new GabotoRuntimeException();
			}
			entities[i].setTimeSpan(TimeSpan.createFromInstants(instantArray[i].clone(), instantArray[i+1].clone()));
			entities[i].setUri(uri);
		}
		
		for (Property property : properties) {
			Class<? extends OxpEntity> et=null;
			try {
				for (int i=instantOffsets.get(property.from); i<instantOffsets.get(property.to); i++) {
					et = typeArray[i].entityClass;
					Method m = typeArray[i].entityClass.getMethod(property.name, property.value.getClass());
					m.invoke(entities[i], property.value);
				}
			} catch (SecurityException e) {
				throw new GabotoRuntimeException();
			} catch (NoSuchMethodException e) {
				warningHandler.addWarning(filename, "Cannot call "+property.name+" on entity of type "+et.getName());
			} catch (IllegalArgumentException e) {
				throw new GabotoRuntimeException();
			} catch (IllegalAccessException e) {
				throw new GabotoRuntimeException();
			} catch (InvocationTargetException e) {
				throw new GabotoRuntimeException();
			}
		}
		
		if (relationsByURI.containsKey(uri))
			for (Relation relation : relationsByURI.get(uri)) {
				OxpEntity proxy_;
				Class<? extends OxpEntity> et=null;
				try {
					proxy_ = relation.argumentClass.newInstance();
					proxy_.setUri(gaboto.getConfig().getNSData()+relation.passive);
					for (int i=instantOffsets.get(relation.from); i<instantOffsets.get(relation.to); i++) {
						et = typeArray[i].entityClass;
						Method m = typeArray[i].entityClass.getMethod(relation.name, relation.argumentClass); //proxy.getClass());
						m.invoke(entities[i], proxy_);
					}
				} catch (InstantiationException e) {
					throw new GabotoRuntimeException();
				} catch (SecurityException e) {
					throw new GabotoRuntimeException();
				} catch (NoSuchMethodException e) {
					warningHandler.addWarning(filename, "Cannot call "+relation.name+" on entity of type "+et.getName());
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
	
	public String getTagName() {
		return tagName;
	}
	
	private class Relation {
		String active;
		String passive;
		String name;
		TimeInstant from;
		TimeInstant to;
		Class<? extends OxpEntity> argumentClass;
		
		public Relation(String active, String passive, String name, TimeInstant from, TimeInstant to, Class<? extends OxpEntity> argumentClass) {
			//System.out.println("REL "+name+", "+active+", "+passive+", "+from.toString()+", "+to.toString());
			if (active.equals(""))
				throw new AssertionError();
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
	
	private class TypeSpan {
		Class <? extends OxpEntity> entityClass;
		TimeInstant from;
		TimeInstant to;
		OxpEntity proxy;
		String typeName;
		
		@SuppressWarnings("unchecked")
		public TypeSpan(String name, TimeInstant from, TimeInstant to) throws ClassNotFoundException{
			this.entityClass = (Class<? extends OxpEntity>) Class.forName("uk.ac.ox.oucs.oxpoints.gaboto.entities."+name);
			this.typeName = name;
			try {
				this.proxy = this.entityClass.newInstance();
				this.proxy.setUri(uri);
			} catch (InstantiationException e) {
				throw new GabotoRuntimeException();
			} catch (IllegalAccessException e) {
				throw new GabotoRuntimeException();
			}
			
			this.from = from;
			this.to = to;
			
			
		}
		
	}
	
}
