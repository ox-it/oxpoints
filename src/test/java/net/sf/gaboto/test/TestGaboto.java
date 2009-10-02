/**
 * Copyright 2009 University of Oxford
 *
 * Written by Tim Pizey for the Erewhon Project
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

import java.io.File;
import java.io.FileInputStream;

import net.sf.gaboto.Gaboto;
import net.sf.gaboto.GabotoFactory;

import org.junit.Test;

/**
 * @author timp
 * @since 23 Sep 2009
 *
 */
public class TestGaboto {

  
  
  /**
   * Test method for {@link net.sf.gaboto.Gaboto#read(java.io.InputStream)}.
   */
  @Test
  public void testReadInputStream() throws Exception {
    Gaboto g = GabotoFactory.getEmptyInMemoryGaboto();
    assertEquals(0, g.getJenaModelViewOnNamedGraphSet().size());
    File graphs = new File(Utils.rooted("src/test/data/test1"), Gaboto.GRAPH_FILE_NAME);
    FileInputStream graphsFileInputStream; 
    graphsFileInputStream = new FileInputStream(graphs);
    
    g.read(graphsFileInputStream);
    g.persistToDisk(Utils.rooted("src/test/data/test1"));
    
    assertEquals(21,g.getJenaModelViewOnNamedGraphSet().size());
  }

  @Test
  public void testPersistEmpty() throws Exception { 
    Gaboto g = GabotoFactory.getEmptyInMemoryGaboto();
    g.persistToDisk(Utils.rooted("src/test/data/empty"));
    
  }
  /**
   * Test method for {@link net.sf.gaboto.Gaboto#read(java.lang.String)}.
   */
  @Test
  public void testReadString() throws Exception {
    Gaboto g = GabotoFactory.getGaboto(Utils.rooted("src/test/data/empty"));
    assertEquals(0, g.getJenaModelViewOnNamedGraphSet().size());
    File graphs = new File(Utils.rooted("src/test/data/test1"), "graphs.rdf");
    FileInputStream graphsFileInputStream; 
    graphsFileInputStream = new FileInputStream(graphs);
    
    StringBuffer contents = new StringBuffer();
    int c = 0;
    while ( c != -1){ 
      c = graphsFileInputStream.read();
      contents.append((char)c);
    }
    g.read(contents.toString());
    g.persistToDisk(Utils.rooted("src/test/data/test1"));
    assertEquals(21,g.getJenaModelViewOnNamedGraphSet().size());
  }

  /**
   * Test method for {@link net.sf.gaboto.Gaboto#read(java.io.InputStream, java.lang.String, java.io.InputStream, java.lang.String)}.
   */
  @Test
  public void testReadInputStreamStringInputStreamString() throws Exception {
    Gaboto g = GabotoFactory.getEmptyInMemoryGaboto();
    assertEquals(0,g.getJenaModelViewOnNamedGraphSet().size());
    File graphs = new File(Utils.rooted("src/test/data/test1"), Gaboto.GRAPH_FILE_NAME);
    FileInputStream graphsFileInputStream; 
    graphsFileInputStream = new FileInputStream(graphs); 
    
    g.read(graphsFileInputStream);
    assertEquals(21,g.getJenaModelViewOnNamedGraphSet().size());
  }
  

}
