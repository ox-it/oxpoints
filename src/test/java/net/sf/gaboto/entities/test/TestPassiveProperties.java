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

import net.sf.gaboto.Gaboto;
import net.sf.gaboto.GabotoSnapshot;
import net.sf.gaboto.node.GabotoEntity;
import net.sf.gaboto.node.pool.EntityPool;
import net.sf.gaboto.node.pool.EntityPoolConfiguration;
import net.sf.gaboto.time.TimeInstant;
import net.sf.gaboto.vocabulary.OxPointsVocab;

import org.junit.BeforeClass;
import org.junit.Test;

import uk.ac.ox.oucs.oxpoints.OxpointsFactory;
import uk.ac.ox.oucs.oxpoints.gaboto.entities.College;
import uk.ac.ox.oucs.oxpoints.gaboto.entities.Website;
import static org.junit.Assert.assertTrue;

public class TestPassiveProperties  {


	@BeforeClass
	public static void setUp() throws Exception {
	}
	
	@Test
	public void testClassicPropertyLoading() {
    Gaboto oxp = OxpointsFactory.getOxpointsFromXML();
		
		GabotoSnapshot snapshot = oxp.getSnapshot(TimeInstant.now());
		EntityPoolConfiguration config = new EntityPoolConfiguration(snapshot);
		config.addAcceptedType(OxPointsVocab.Website_URI);
		EntityPool pool = EntityPool.createFrom(config);
		
		boolean foundPassive = false;
    for(GabotoEntity e : pool.getEntities()){
			Website web = (Website) e;
			
			if(web.getIsHomepageIn() != null)
				foundPassive = true;
		}
		
		assertTrue(foundPassive);
	}
	
	@Test
	public void testClassicPropertyLoading2() {
    Gaboto oxp = OxpointsFactory.getOxpointsFromXML();
		
		GabotoSnapshot snapshot = oxp.getSnapshot(TimeInstant.now());
		EntityPoolConfiguration config = new EntityPoolConfiguration(snapshot);
		config.addAcceptedType(OxPointsVocab.College_URI);
		EntityPool pool = EntityPool.createFrom(config);
		
		boolean foundPassive = false;
		for(GabotoEntity e : pool.getEntities()){
			College col = (College) e;
			
			if(col.getOccupiedPlaces() != null)
				foundPassive = true;
		}
		
		assertTrue(foundPassive);
	}

}

