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

import net.sf.gaboto.query.GabotoQuery;
import net.sf.gaboto.query.SPARQLConstructQuery;
import net.sf.gaboto.time.TimeInstant;
import net.sf.gaboto.util.GabotoPredefinedQueries;
import net.sf.gaboto.vocabulary.OxPointsVocab;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class TestSimpleConstructSPARQLQuery {

	@BeforeClass
	public static void setUp() throws Exception {
	}

	@AfterClass
  public static void tearDown() { 
  }
	
	@Test
	public void testQuery() throws Exception{
		String query = GabotoPredefinedQueries.getStandardPrefixes();
		query += "PREFIX oxp: <" + OxPointsVocab.NS + ">\n";
		query += "CONSTRUCT { ?a ?b ?c. } WHERE {\n" +
		     "?a rdf:type oxp:College . \n" +
		     "?a dc:title ?title . \n" +
		     "FILTER regex(?title, \"^b\", \"i\") . \n" +
		     "?a ?b ?c . \n" +
		     "}";

		SPARQLConstructQuery sparqlQuery = new SPARQLConstructQuery(TimeInstant.now(), query);
		String result = (String) sparqlQuery.execute(GabotoQuery.FORMAT_RDF_XML);
		System.err.println(result);
	}
}
