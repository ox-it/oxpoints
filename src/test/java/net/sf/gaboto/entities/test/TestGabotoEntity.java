/**
 * Copyright 2009 University of Oxford
 *
 * Written by Arno Mittelbach for the Erewhon Project
 *
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without 
 * modification, are permitted provided that the following conditions are met:
 *
 *  - Redistributions of source code must retain the above copyright notice, 
 *    this list of conditions and the following disclaimer.
 *  - Redistributions in binary form must reproduce the above copyright notice, 
 *    this list of conditions and the following disclaimer in the documentation 
 *    and/or other materials provided with the distribution.
 *  - Neither the name of the University of Oxford nor the names of its 
 *    contributors may be used to endorse or promote products derived from this 
 *    software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" 
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE 
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE 
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE 
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR 
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF 
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS 
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN 
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) 
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE 
 * POSSIBILITY OF SUCH DAMAGE.
 */
package net.sf.gaboto.entities.test;


import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.Map.Entry;

import net.sf.gaboto.Gaboto;
import net.sf.gaboto.GabotoFactory;
import net.sf.gaboto.GabotoSnapshot;
import net.sf.gaboto.SPARQLQuerySolutionProcessor;
import net.sf.gaboto.node.GabotoEntity;
import net.sf.gaboto.node.pool.EntityPool;
import net.sf.gaboto.time.TimeInstant;
import net.sf.gaboto.time.TimeSpan;
import net.sf.gaboto.util.GabotoPredefinedQueries;
import net.sf.gaboto.vocabulary.OxPointsVocab;

import org.junit.BeforeClass;
import org.junit.Test;

import uk.ac.ox.oucs.oxpoints.OxpointsFactory;
import uk.ac.ox.oucs.oxpoints.gaboto.beans.Location;
import uk.ac.ox.oucs.oxpoints.gaboto.entities.Building;
import uk.ac.ox.oucs.oxpoints.gaboto.entities.Carpark;
import uk.ac.ox.oucs.oxpoints.gaboto.entities.OxpEntity;
import uk.ac.ox.oucs.oxpoints.gaboto.entities.Unit;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.rdf.model.Property;

public class TestGabotoEntity  {

  static Gaboto oxp = null;

  @BeforeClass
	public  static void setUp() throws Exception {
    //oxp = GabotoFactory.getPersistentGaboto();
    oxp = GabotoFactory.getInMemoryGaboto();
    //oxp = GabotoFactory.getEmptyInMemoryGaboto();
	}
	
	@Test
	public void testGetPropertyValue(){
		
		Building b = new Building();
		b.setTimeSpan(new TimeSpan(1900,null,null));
		b.setName("Test Building");
		String[] loc = "50.12312414234 0.12312432".split(" ");
		b.setLongitude(Float.valueOf(loc[0]));
		b.setLatitude(Float.valueOf(loc[1]));
		
		Building b2 = new Building();
		b2.setTimeSpan(new TimeSpan(1900,null,null));
		b2.setName("Test Building 2");
		String[] loc2 = "57.1239812 21.123987".split(" ");
		b.setLongitude(Float.valueOf(loc2[0]));
		b.setLatitude(Float.valueOf(loc2[1]));
		
		Unit u = new Unit();
		u.setUri(oxp.generateIdUri());
		u.setTimeSpan(new TimeSpan(1900,null,null));
		u.setName("Test Unit");
		u.setPrimaryPlace(b);
		
		Unit u2 = new Unit();
		u2.setUri(oxp.generateIdUri());
		u2.setTimeSpan(new TimeSpan(1900,null,null));
		u2.setName("Test Unit");
		u2.setPrimaryPlace(b2);
		
		Unit u3 = new Unit();
		u3.setUri(oxp.generateIdUri());
		u3.setTimeSpan(new TimeSpan(1900,null,null));
		u3.setName("Test Unit");
		u3.setSubsetOf(u2);
		
		
		assertEquals(loc, u.getPropertyValue(OxPointsVocab.hasLocation_URI));
		assertEquals(loc2, u3.getPropertyValue(OxPointsVocab.hasLocation_URI));
	}
		
	
	@Test
	public void testGetPropertyValue2() throws Exception{
		oxp = GabotoFactory.getPersistentGaboto();
		Gaboto oxp_mem = GabotoFactory.getInMemoryGaboto();
		
		Building b = new Building();
		b.setUri(oxp.generateIdUri());
		b.setTimeSpan(new TimeSpan(1900,null,null));
		b.setName("Test Building");
		String[] loc = "50.12312414234 0.12312432".split(" ");
		b.setLongitude(Float.valueOf(loc[0]));
		b.setLatitude(Float.valueOf(loc[1]));
		
		Unit u = new Unit();
		u.setUri(oxp.generateIdUri());
		u.setTimeSpan(new TimeSpan(1900,null,null));
		u.setName("Test Unit");
		u.addOccupiedBuilding(b);
		
		// add to data store
		oxp.add(b);
		oxp.add(u);
		
		// create pool for passive properties
		EntityPool pool = new EntityPool(oxp_mem, oxp_mem.getSnapshot(TimeInstant.now()));
		pool.addEntity(b);
		pool.addEntity(u);
		
		assertEquals(loc, u.getPropertyValue(OxPointsVocab.hasLocation_URI));
	}
	
	@Test
	public void testTypedLiteral1() throws Exception{
		
		String uri1 = oxp.generateIdUri();
		Carpark cp1 = new Carpark();
		cp1.setUri(uri1);
		cp1.setName("small carpark");
		cp1.setCapacity(30);
		
		final String uri2 = oxp.generateIdUri();
		Carpark cp2 = new Carpark();
		cp2.setUri(uri2);
		cp2.setName("big carpark");
		cp2.setCapacity(80);
		
		// add carparks
		oxp.add(cp1);
		oxp.add(cp2);
		
		// snapshot
		GabotoSnapshot snap = oxp.getSnapshot(TimeInstant.now());

		// ask for small
		String query = GabotoPredefinedQueries.getStandardPrefixes();
		query += "SELECT ?cp WHERE { \n";
		query += "?cp a oxp:Carpark .\n";
		query += "?cp oxp:capacity ?capacity .\n";
		query += "FILTER (?capacity > 50) .\n";
		query += "}";
		
		final Collection<String> foundURIs = new HashSet<String>();
		snap.execSPARQLSelect(query, new SPARQLQuerySolutionProcessor(){

			public void processSolution(QuerySolution solution) {
				foundURIs.add(solution.getResource("cp").getURI());
			}

			public boolean stopProcessing() {
				return false;
			}
			
		});
		
		assertTrue(foundURIs.contains(uri2));
	}
	
  @Test
  public void testLoad() { 
    oxp = OxpointsFactory.getOxpointsFromXML();
  }
	
	@SuppressWarnings("unchecked")
  @Test
  public void testPassiveProperties() { 
    oxp = OxpointsFactory.getOxpointsFromXML();
    
    GabotoSnapshot nowSnap = oxp.getSnapshot(TimeInstant.now());
    
    EntityPool pool = new EntityPool(oxp, nowSnap);
    GabotoEntity passiveParticipant = nowSnap.loadEntity("http://m.ox.ac.uk/oxpoints/id/23232562");
    Property prop = OxPointsVocab.MODEL.getObjectProperty("http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#occupies");
    Set<Entry<String, Object>> passiveProperties = passiveParticipant.getAllPassiveProperties().entrySet(); 
    for (Entry<String, Object> entry : passiveProperties) {
      if (entry.getKey().equals(prop.getURI())) {
        if (entry.getValue() != null) {
          if (entry.getValue() instanceof HashSet) { 
            HashSet<Object> them = (HashSet<Object>)entry.getValue(); 
            for (Object e : them) { 
              if (e instanceof GabotoEntity) {
                System.err.println("Adding set member :" + e);
                pool.add((GabotoEntity)e);
              }
            }
          } else if (entry.getValue() instanceof GabotoEntity) { 
            System.err.println("Adding individual :" + entry.getKey());
            pool.add((GabotoEntity)entry.getValue());            
          } else { 
            System.err.println("Ignoring:" + entry.getKey());
          }
        } else { 
          System.err.println("Ignoring:" + entry.getKey());
        }
      } else { 
        System.err.println("Ignoring:" + entry.getKey());
      }
    }
    assertEquals(3, pool.size());
  }
  @SuppressWarnings("unchecked")
  @Test
  public void testPassivePropertiesTheLongWay() { 
    oxp = OxpointsFactory.getOxpointsFromXML();
    
    GabotoSnapshot nowSnap = oxp.getSnapshot(TimeInstant.now());
    
    EntityPool pool = new EntityPool(oxp, nowSnap);
    GabotoEntity passiveParticipant = nowSnap.loadEntity("http://m.ox.ac.uk/oxpoints/id/23232562");
    Property prop = OxPointsVocab.MODEL.getObjectProperty("http://ns.ox.ac.uk/namespace/oxpoints/2009/02/owl#occupies");
    
    EntityPool allEntitiesWithProp = nowSnap.loadEntitiesWithProperty(prop);
    for(GabotoEntity e : allEntitiesWithProp.getEntities()) {
      if (e.getPropertyValue(prop) != null) {
        if (e.getPropertyValue(prop) instanceof HashSet) { 
          for (Object ent : (HashSet)e.getPropertyValue(prop)) { 
            if (((OxpEntity)ent).getUri().equals(passiveParticipant.getUri())) { 
              System.err.println("FOUND" + ((OxpEntity)ent).getUri());
              System.err.println("Adding" + e);
              pool.add(e);
            }
          }
        } else if (e.getPropertyValue(prop) instanceof OxpEntity) 
          if (((OxpEntity)e.getPropertyValue(prop)).getUri().equals(passiveParticipant.getUri())) { 
            System.err.println(((OxpEntity)e.getPropertyValue(prop)).getUri());
            System.err.println("FOUND" + ((OxpEntity)e).getUri());
            pool.add(e);
            System.err.println("Adding" + e);
          }
      }
    }
    assertEquals(3, pool.size());
  }
}
