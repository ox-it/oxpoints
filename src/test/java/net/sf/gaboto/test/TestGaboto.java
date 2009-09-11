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

import static org.junit.Assert.*;

import net.sf.gaboto.Gaboto;
import net.sf.gaboto.GabotoFactory;

import org.junit.Test;

import com.hp.hpl.jena.rdf.model.Model;

/**
 * @author timp
 * @since 26 Aug 2009
 *
 */
public class TestGaboto {

  /**
   * Test method for {@link net.sf.gaboto.Gaboto#Gaboto(com.hp.hpl.jena.rdf.model.Model, de.fuberlin.wiwiss.ng4j.NamedGraphSet)}.
   */
  @Test
  public void testGabotoModelNamedGraphSet() {
    
  }

  /**
   * Test method for {@link net.sf.gaboto.Gaboto#Gaboto(com.hp.hpl.jena.rdf.model.Model, de.fuberlin.wiwiss.ng4j.NamedGraphSet, net.sf.gaboto.time.TimeDimensionIndexer)}.
   */
  @Test
  public void testGabotoModelNamedGraphSetTimeDimensionIndexer() {
    
  }

  /**
   * Test method for {@link net.sf.gaboto.Gaboto#getTimeDimensionIndexer()}.
   */
  @Test
  public void testGetTimeDimensionIndexer() {
    
  }

  /**
   * Test method for {@link net.sf.gaboto.Gaboto#setTimeDimensionIndexer(net.sf.gaboto.time.TimeDimensionIndexer)}.
   */
  @Test
  public void testSetTimeDimensionIndexer() {
    
  }

  /**
   * Test method for {@link net.sf.gaboto.Gaboto#recreateTimeDimensionIndex()}.
   */
  @Test
  public void testRecreateTimeDimensionIndex() {
    
  }

  /**
   * Test method for {@link net.sf.gaboto.Gaboto#attachUpdateListener(net.sf.gaboto.event.UpdateListener)}.
   */
  @Test
  public void testAttachUpdateListener() {
    
  }

  /**
   * Test method for {@link net.sf.gaboto.Gaboto#detachUpdateListener(net.sf.gaboto.event.UpdateListener)}.
   */
  @Test
  public void testDetachUpdateListener() {
    
  }

  /**
   * Test method for {@link net.sf.gaboto.Gaboto#generateID()}.
   */
  @Test
  public void testGenerateID() {
    
  }

  /**
   * Test method for {@link net.sf.gaboto.Gaboto#getSnapshot(net.sf.gaboto.time.TimeInstant)}.
   */
  @Test
  public void testGetSnapshotTimeInstant() {
    
  }

  /**
   * Test method for {@link net.sf.gaboto.Gaboto#getSnapshot(de.fuberlin.wiwiss.ng4j.NamedGraph)}.
   */
  @Test
  public void testGetSnapshotNamedGraph() {
    
  }

  /**
   * Test method for {@link net.sf.gaboto.Gaboto#getSnapshot(java.lang.String)}.
   */
  @Test
  public void testGetSnapshotString() {
    
  }

  /**
   * Test method for {@link net.sf.gaboto.Gaboto#getSnapshot(java.util.Collection)}.
   */
  @Test
  public void testGetSnapshotCollectionOfString() {
    
  }

  /**
   * Test method for {@link net.sf.gaboto.Gaboto#add(net.sf.gaboto.node.GabotoTimeBasedEntity)}.
   */
  @Test
  public void testAddGabotoTimeBasedEntity() {
    
  }

  /**
   * Test method for {@link net.sf.gaboto.Gaboto#change(net.sf.gaboto.node.GabotoEntity)}.
   */
  @Test
  public void testChangeGabotoEntity() {
    
  }

  /**
   * Test method for {@link net.sf.gaboto.Gaboto#change(net.sf.gaboto.node.GabotoTimeBasedEntity)}.
   */
  @Test
  public void testChangeGabotoTimeBasedEntity() {
    
  }

  /**
   * Test method for {@link net.sf.gaboto.Gaboto#add(net.sf.gaboto.node.GabotoEntity)}.
   */
  @Test
  public void testAddGabotoEntity() {
    
  }

  /**
   * Test method for {@link net.sf.gaboto.Gaboto#purge(net.sf.gaboto.node.GabotoEntity)}.
   */
  @Test
  public void testPurgeGabotoEntity() {
    
  }

  /**
   * Test method for {@link net.sf.gaboto.Gaboto#purge(net.sf.gaboto.node.GabotoTimeBasedEntity)}.
   */
  @Test
  public void testPurgeGabotoTimeBasedEntity() {
    
  }

  /**
   * Test method for {@link net.sf.gaboto.Gaboto#purge(java.lang.String)}.
   */
  @Test
  public void testPurgeString() {
    
  }

  /**
   * Test method for {@link net.sf.gaboto.Gaboto#remove(net.sf.gaboto.node.GabotoEntity)}.
   */
  @Test
  public void testRemoveGabotoEntity() {
    
  }

  /**
   * Test method for {@link net.sf.gaboto.Gaboto#remove(net.sf.gaboto.node.GabotoEntity, net.sf.gaboto.time.TimeSpan)}.
   */
  @Test
  public void testRemoveGabotoEntityTimeSpan() {
    
  }

  /**
   * Test method for {@link net.sf.gaboto.Gaboto#add(com.hp.hpl.jena.graph.Triple)}.
   */
  @Test
  public void testAddTriple() {
    
  }

  /**
   * Test method for {@link net.sf.gaboto.Gaboto#remove(com.hp.hpl.jena.graph.Triple)}.
   */
  @Test
  public void testRemoveTriple() {
    
  }

  /**
   * Test method for {@link net.sf.gaboto.Gaboto#add(net.sf.gaboto.time.TimeSpan, com.hp.hpl.jena.graph.Triple)}.
   */
  @Test
  public void testAddTimeSpanTriple() {
    
  }

  /**
   * Test method for {@link net.sf.gaboto.Gaboto#remove(net.sf.gaboto.time.TimeSpan, com.hp.hpl.jena.graph.Triple)}.
   */
  @Test
  public void testRemoveTimeSpanTriple() {
    
  }

  /**
   * Test method for {@link net.sf.gaboto.Gaboto#remove(de.fuberlin.wiwiss.ng4j.Quad)}.
   */
  @Test
  public void testRemoveQuad() {
    
  }

  /**
   * Test method for {@link net.sf.gaboto.Gaboto#getNamedGraphSet()}.
   */
  @Test
  public void testGetNamedGraphSet() {
    
  }

  /**
   * Test method for {@link net.sf.gaboto.Gaboto#containsGraph(net.sf.gaboto.time.TimeSpan)}.
   */
  @Test
  public void testContainsGraphTimeSpan() {
    
  }

  /**
   * Test method for {@link net.sf.gaboto.Gaboto#containsGraph(java.lang.String)}.
   */
  @Test
  public void testContainsGraphString() {
    
  }

  /**
   * Test method for {@link net.sf.gaboto.Gaboto#getGraph(java.lang.String)}.
   */
  @Test
  public void testGetGraphString() {
    
  }

  /**
   * Test method for {@link net.sf.gaboto.Gaboto#getGraph(net.sf.gaboto.time.TimeSpan)}.
   */
  @Test
  public void testGetGraphTimeSpan() {
    
  }

  /**
   * Test method for {@link net.sf.gaboto.Gaboto#getGlobalKnowledgeGraph()}.
   */
  @Test
  public void testGetGlobalKnowledgeGraph() {
    
  }

  /**
   * Test method for {@link net.sf.gaboto.Gaboto#getContextDescriptionGraph()}.
   */
  @Test
  public void testGetContextDescriptionGraph() {
    
  }

  /**
   * Test method for {@link net.sf.gaboto.Gaboto#getJenaModelViewOnNamedGraphSet()}.
   */
  @Test
  public void testGetJenaModelViewOnNamedGraphSet() {
    
  }

  /**
   * Test method for {@link net.sf.gaboto.Gaboto#containsEntity(net.sf.gaboto.node.GabotoEntity)}.
   */
  @Test
  public void testContainsEntityGabotoEntity() {
    
  }

  /**
   * Test method for {@link net.sf.gaboto.Gaboto#containsEntity(java.lang.String)}.
   */
  @Test
  public void testContainsEntityString() {
    
  }

  /**
   * Test method for {@link net.sf.gaboto.Gaboto#getEntity(java.lang.String, net.sf.gaboto.time.TimeInstant)}.
   */
  @Test
  public void testGetEntity() {
    
  }

  /**
   * Test method for {@link net.sf.gaboto.Gaboto#getEntityOverTime(java.lang.String)}.
   */
  @Test
  public void testGetEntityOverTime() {
    
  }

  /**
   * Test method for {@link net.sf.gaboto.Gaboto#containsResource(com.hp.hpl.jena.rdf.model.Resource)}.
   */
  @Test
  public void testContainsResourceResource() {
    
  }

  /**
   * Test method for {@link net.sf.gaboto.Gaboto#containsResource(java.lang.String)}.
   */
  @Test
  public void testContainsResourceString() {
    
  }

  /**
   * Test method for {@link net.sf.gaboto.Gaboto#getTypeOf(java.lang.String)}.
   */
  @Test
  public void testGetTypeOf() {
    
  }

  /**
   * Test method for {@link net.sf.gaboto.Gaboto#getEntitysLifetime(java.lang.String)}.
   */
  @Test
  public void testGetEntitysLifetime() {
    
  }

  /**
   * Test method for {@link net.sf.gaboto.Gaboto#getEntityURIsFor(com.hp.hpl.jena.rdf.model.Property)}.
   */
  @Test
  public void testGetEntityURIsForProperty() {
    
  }

  /**
   * Test method for {@link net.sf.gaboto.Gaboto#getEntityURIsFor(com.hp.hpl.jena.rdf.model.Property, java.lang.String)}.
   */
  @Test
  public void testGetEntityURIsForPropertyString() {
    
  }

  /**
   * Test method for {@link net.sf.gaboto.Gaboto#getEntityURIsFor(com.hp.hpl.jena.rdf.model.Property, com.hp.hpl.jena.graph.Node)}.
   */
  @Test
  public void testGetEntityURIsForPropertyNode() {
    
  }

  /**
   * Test method for {@link net.sf.gaboto.Gaboto#loadEntitiesOverTimeWithProperty(com.hp.hpl.jena.rdf.model.Property)}.
   */
  @Test
  public void testLoadEntitiesOverTimeWithPropertyProperty() {
    
  }

  /**
   * Test method for {@link net.sf.gaboto.Gaboto#loadEntitiesOverTimeWithProperty(com.hp.hpl.jena.rdf.model.Property, java.lang.String)}.
   */
  @Test
  public void testLoadEntitiesOverTimeWithPropertyPropertyString() {
    
  }

  /**
   * Test method for {@link net.sf.gaboto.Gaboto#loadEntitiesOverTimeWithProperty(com.hp.hpl.jena.rdf.model.Property, com.hp.hpl.jena.graph.Node)}.
   */
  @Test
  public void testLoadEntitiesOverTimeWithPropertyPropertyNode() {
    
  }

  /**
   * Test method for {@link net.sf.gaboto.Gaboto#write(java.io.OutputStream)}.
   */
  @Test
  public void testWriteOutputStream() {
    
  }

  /**
   * Test method for {@link net.sf.gaboto.Gaboto#write(java.io.OutputStream, java.lang.String)}.
   */
  @Test
  public void testWriteOutputStreamString() {
    
  }

  /**
   * Test method for {@link net.sf.gaboto.Gaboto#writeCDG(java.io.OutputStream)}.
   */
  @Test
  public void testWriteCDGOutputStream() {
    
  }

  /**
   * Test method for {@link net.sf.gaboto.Gaboto#writeCDG(java.io.OutputStream, java.lang.String)}.
   */
  @Test
  public void testWriteCDGOutputStreamString() {
    
  }

  /**
   * Test method for {@link net.sf.gaboto.Gaboto#read(java.io.InputStream, java.io.InputStream)}.
   */
  @Test
  public void testReadInputStreamInputStream() {
    
  }

  /**
   * Test method for {@link net.sf.gaboto.Gaboto#read(java.io.InputStream, java.lang.String, java.io.InputStream, java.lang.String)}.
   */
  @Test
  public void testReadInputStreamStringInputStreamString() {
    
  }

  /**
   * Test method for {@link net.sf.gaboto.Gaboto#getOntologyLookup()}.
   */
  @Test
  public void testGetOntologyLookup() {
    
  }

  /**
   * Test method for {@link net.sf.gaboto.Gaboto#getConfig()}.
   */
  @Test
  public void testGetConfig() {
    
  }


}
