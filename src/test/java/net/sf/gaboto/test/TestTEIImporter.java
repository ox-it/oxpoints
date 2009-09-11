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

import java.io.File;
import java.io.FileOutputStream;



import net.sf.gaboto.Gaboto;
import net.sf.gaboto.query.GabotoQuery;
import net.sf.gaboto.query.ListOfTypedEntities;
import net.sf.gaboto.time.TimeInstant;
import net.sf.gaboto.vocabulary.OxPointsVocab;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import uk.ac.ox.oucs.oxpoints.OxpointsFactory;

public class TestTEIImporter {

  public static Gaboto oxp = null;
  
  @Before
  public void setUp() throws Exception {
    oxp = OxpointsFactory.getOxpointsFromXML();    
  }

  @After
  public void tearDown() throws Exception {
    oxp = null;
  }

  @Test
  public void testTypedEntitiesOutputToKML() throws Exception { 
    GabotoQuery query = new ListOfTypedEntities(oxp, OxPointsVocab.Unit_URI, TimeInstant.now() );
    
    Utils.assertXmlEqual((String)query.execute(GabotoQuery.FORMAT_KML), "UnitsKML.kml");    
  }
  @Test
  public void testTypedEntitiesOutputToRDF() throws Exception { 
    GabotoQuery query = new ListOfTypedEntities(oxp, OxPointsVocab.Unit_URI, TimeInstant.now() );
    Utils.assertXmlEqual((String)query.execute(GabotoQuery.FORMAT_RDF_XML), "UnitsRDF.xml");    
  }
  @Test
  public void testAllToRdf() throws Exception { 
    File graphsFile = new File(Utils.actualOutputDir, "graphs.rdf");
    FileOutputStream actualOutputStream = new FileOutputStream(graphsFile);
    oxp.write(actualOutputStream);
    actualOutputStream.close();
    
    File contextFile = new File(Utils.actualOutputDir, "cdg.rdf");
    FileOutputStream contextOutputStream = new FileOutputStream(contextFile);
    oxp.writeCDG(contextOutputStream);
    contextOutputStream.close();
    
    
  }
  @Test
  public void testAllToTEI() throws Exception { 
    //GabotoQuery query = new AllEntities(oxp);
    
    // Fails as not yet implemented
   // assertXmlEqual((String)query.execute(GabotoQuery.FORMAT_TEI_XML), "all.xml");    
  }


}
