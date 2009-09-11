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
import net.sf.gaboto.node.pool.EntityPool;
import net.sf.gaboto.node.pool.EntityPoolConfiguration;
import net.sf.gaboto.query.GabotoQuery;
import net.sf.gaboto.time.TimeInstant;
import net.sf.gaboto.util.PerformanceAverager;

import org.junit.BeforeClass;
import org.junit.Test;

import uk.ac.ox.oucs.oxpoints.CollegesNearEntity;
import uk.ac.ox.oucs.oxpoints.OxpointsFactory;

public class TestPoolCreationPerformance {

	private static int RUNS = 5;

	@BeforeClass
	public static void setUp() throws Exception {
	}
	
	@Test
	public void testSimplePoolCreation() {
    //Gaboto oxp = GabotoFactory.getInMemoryGaboto();
    Gaboto oxp = OxpointsFactory.getOxpointsFromXML();

		PerformanceAverager perf = new PerformanceAverager("Simple Pool Creation");
		for(int i = 0; i < RUNS; i++){
			perf.start("creation");
			
			GabotoSnapshot snap = oxp.getSnapshot(TimeInstant.now());
			EntityPoolConfiguration config = new EntityPoolConfiguration(snap);
			EntityPool.createFrom(config);
			
			perf.stop();
		}
		
    System.out.println(perf);
	}
	
	@Test
	public void testSimplePoolCreationWithAllPassive() {
    //Gaboto oxp = GabotoFactory.getInMemoryGaboto();
    Gaboto oxp = OxpointsFactory.getOxpointsFromXML();

		PerformanceAverager perf = new PerformanceAverager("Simple Pool Creation with passive creation");
		for(int i = 0; i < RUNS; i++){
			perf.start("creation");
			
			GabotoSnapshot snap = oxp.getSnapshot(TimeInstant.now());
			EntityPoolConfiguration config = new EntityPoolConfiguration(snap);
			config.setCreatePassiveEntities(true);
			EntityPool.createFrom(config);
			
			perf.stop();
		}
		
		System.out.println(perf);
	}
	
	@Test
	public void testQuery1() throws Exception{
    //Gaboto oxp = GabotoFactory.getInMemoryGaboto();
    Gaboto oxp = OxpointsFactory.getOxpointsFromXML();

		CollegesNearEntity query = new CollegesNearEntity(oxp, "Somerville College", 10, TimeInstant.now());
		query.prepare();
		
		PerformanceAverager perf = new PerformanceAverager("Test query 1");
		for(int i = 0; i < RUNS; i++){
			perf.start("creation");
			
			query.execute(GabotoQuery.FORMAT_ENTITY_POOL);
			
			perf.stop();
		}
		
		System.out.println(perf);
	}
}
