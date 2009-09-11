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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Collection;
import java.util.Iterator;

import net.sf.gaboto.EntityAlreadyExistsException;
import net.sf.gaboto.Gaboto;
import net.sf.gaboto.GabotoFactory;
import net.sf.gaboto.node.GabotoEntity;
import net.sf.gaboto.node.GabotoTimeBasedEntity;
import net.sf.gaboto.test.TimeUtils;
import net.sf.gaboto.time.TimeInstant;
import net.sf.gaboto.time.TimeSpan;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import uk.ac.ox.oucs.oxpoints.gaboto.entities.Building;
import uk.ac.ox.oucs.oxpoints.gaboto.entities.Unit;

import com.hp.hpl.jena.graph.Node;
import com.hp.hpl.jena.vocabulary.DC_11;

public class TestGaboto {
	
	@BeforeClass
	public static void setUp() throws Exception {
	}

	@AfterClass
	public static void tearDown() { 
	}
	
	@Test (expected=EntityAlreadyExistsException.class)
	public void testAddDuplicate() throws Exception{
		Gaboto oxp = GabotoFactory.getInMemoryGaboto();
		
		Unit u = new Unit();
		u.setUri(TimeUtils.generateRandomURI());
		
		oxp.add(u);
		oxp.add(u);
	}
	
	
	@Test
	public void testAddRemove() throws Exception{
		Gaboto oxp = GabotoFactory.getPersistentGaboto();
		Gaboto oxp_m = GabotoFactory.getInMemoryGaboto();
		
		Unit u = new Unit();
		u.setUri(TimeUtils.generateRandomURI());
		
		Building b = new Building();
		b.setUri(TimeUtils.generateRandomURI());
		b.setName("Abcdef");
		
		// add entities
		oxp.add(u);
		oxp.add(b);
		
		// test if entities were added
		assertTrue(oxp_m.containsEntity(u));
		assertTrue(oxp_m.containsEntity(b));
		
		// remove entities
		oxp.remove(u);
		oxp.remove(b);
		
		// test if entities were removed
		assertTrue(! oxp_m.containsEntity(u));
		assertTrue(! oxp_m.containsEntity(b));
	}
	
	@Test
	public void testLoadEntity() throws Exception{
		Gaboto oxp = GabotoFactory.getPersistentGaboto();
		Gaboto oxp_m = GabotoFactory.getInMemoryGaboto();
		
		String uri = TimeUtils.generateRandomURI();
		Building b = new Building();
		b.setUri(uri);
		b.setTimeSpan(new TimeSpan(500,1,1,200,10,10));
		b.setName("Abcdef");
		
		oxp.add(b);
		
		Building b_loaded = (Building) oxp_m.getEntity(uri, new TimeInstant(600,1,1));
		assertNotNull("Should have found something", b_loaded);
		assertEquals(b_loaded.getName(), b.getName());
		assertEquals(b_loaded.getTimeSpan(), b.getTimeSpan());
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void testAddRemove2() throws Exception{
		Gaboto oxp = GabotoFactory.getPersistentGaboto();
		
		Unit u = new Unit();
		u.setUri(TimeUtils.generateRandomURI());
		
		// add entity
		oxp.add(u);
		
		Iterator it = oxp.getNamedGraphSet().findQuads(Node.ANY, Node.createURI(u.getUri()), Node.ANY, Node.ANY);
		assertTrue(it.hasNext());
		
		// remove entity
		
		oxp.remove(u);
		
		it = oxp.getNamedGraphSet().findQuads(Node.ANY, Node.createURI(u.getUri()), Node.ANY, Node.ANY);
		assertTrue(! it.hasNext());
	}
	
	@Test
	public void testGetEntityURIs() throws Exception {
		Gaboto oxp = GabotoFactory.getInMemoryGaboto();
		
		Collection<String> uris = oxp.getEntityURIsFor(DC_11.title);
		assertTrue(uris.size() > 0);
		int counter = 0;
		for(String u : uris){
		  if (counter++>30) continue;
			GabotoTimeBasedEntity tb = oxp.getEntityOverTime(u);
			Iterator<GabotoEntity> it = tb.iterator();
			while(it.hasNext()){
				GabotoEntity entity = it.next();
				
				Object titleO = entity.getPropertyValue(DC_11.title);
				if(! (titleO instanceof String))
					continue;
				
				String title = (String) titleO;
				assertTrue(oxp.getEntityURIsFor(DC_11.title, title).contains(u));
				
				// time based
				Collection<GabotoTimeBasedEntity> tbEntities = oxp.loadEntitiesOverTimeWithProperty(DC_11.title, title);
				assertTrue(tbEntities.size() > 0);
			}
		}
			
	}
}