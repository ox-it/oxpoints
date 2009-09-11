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

import static org.junit.Assert.assertTrue;

import java.util.List;

import net.sf.gaboto.vocabulary.DCVocab;

import org.junit.BeforeClass;
import org.junit.Test;

import uk.ac.ox.oucs.oxpoints.gaboto.entities.Building;
import uk.ac.ox.oucs.oxpoints.gaboto.entities.Unit;

import com.hp.hpl.jena.datatypes.xsd.XSDDatatype;
import com.hp.hpl.jena.graph.Node;
import com.hp.hpl.jena.graph.Triple;

public class TestGabotoEntityUtils {

	
	@BeforeClass
	public static void setUp() throws Exception {
	}
	
	@Test
	public void testEntityToTriples()  {
		Unit u = new Unit();
		u.setUri("http://exampleuri.co.uk/lala");
		
		Building b = new Building();
		b.setUri("http://exampleuri.co.uk");
		b.setName("Abcdef");
		List<Triple> triples = b.getTriplesFor();
		
		assertTrue(triples.contains(new Triple(
			Node.createURI(b.getUri()),
			Node.createURI(DCVocab.title_URI),
			Node.createLiteral(b.getName(), null, XSDDatatype.XSDstring)
		)));
	}
}
