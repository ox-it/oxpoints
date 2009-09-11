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
package net.sf.gaboto.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import net.sf.gaboto.time.TimeInstant;

import org.junit.BeforeClass;
import org.junit.Test;

public class TestTimeInstant {
	
	@BeforeClass
	public static void setUp() throws Exception {
	}

	@Test (expected=IllegalStateException.class)
	public void testDurationMethodInvocation(){
		TimeInstant ti = TimeUtils.getRandomTimeinstant();
		ti.setDurationDay(10);
	}

	@Test
	public void testEquals(){
		TimeInstant ts0 = new TimeInstant(200,8,10);
		TimeInstant ts1 = new TimeInstant(200,8,10);
		TimeInstant ts2 = new TimeInstant(200,8,11);
		
		assertTrue(ts0.equals(ts1));
		assertTrue(ts1.equals(ts0));
		assertTrue(! ts0.equals(ts2));
		assertTrue(! ts2.equals(ts0));
	}
	
	@Test
	public void testCompare(){
		TimeInstant ts0 = new TimeInstant(200,8,10);
		TimeInstant ts1 = new TimeInstant(200,8,10);
		TimeInstant ts2 = new TimeInstant(200,8,11);
		TimeInstant ts3 = new TimeInstant(200,9,10);
		TimeInstant ts4 = new TimeInstant(201,8,10);
		
		assertTrue(ts0.compareTo(ts1) == 0);
		
		assertTrue(ts0.compareTo(ts2) < 0);
		assertTrue(ts2.compareTo(ts0) > 0);
		
		assertTrue(ts0.compareTo(ts3) < 0);
		assertTrue(ts3.compareTo(ts0) > 0);
		
		assertTrue(ts0.compareTo(ts4) < 0);
		assertTrue(ts4.compareTo(ts0) > 0);
		
		TimeInstant ts5 = new TimeInstant(201,8,null);
		TimeInstant ts6 = new TimeInstant(201,8,null);
		TimeInstant ts7 = new TimeInstant(201,null,null);
		assertEquals(ts5, ts6);
		assertTrue(! ts6.equals(ts7));
		
	}
}
