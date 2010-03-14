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
package net.sf.gaboto.entities.pool.test;

import net.sf.gaboto.Gaboto;
import net.sf.gaboto.GabotoSnapshot;
import net.sf.gaboto.node.GabotoEntity;
import net.sf.gaboto.node.pool.EntityPool;
import net.sf.gaboto.node.pool.EntityPoolConfiguration;
import net.sf.gaboto.node.pool.filter.EntityFilter;
import net.sf.gaboto.node.pool.filter.PropertyEqualsFilter;
import net.sf.gaboto.node.pool.filter.PropertyExistsFilter;
import net.sf.gaboto.time.TimeInstant;
import net.sf.gaboto.vocabulary.OxPointsVocab;

import org.junit.BeforeClass;
import org.junit.Test;

import uk.ac.ox.oucs.oxpoints.OxpointsFactory;
import uk.ac.ox.oucs.oxpoints.gaboto.entities.Building;
import uk.ac.ox.oucs.oxpoints.gaboto.entities.College;
import uk.ac.ox.oucs.oxpoints.gaboto.entities.Place;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ResIterator;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.Statement;
import com.hp.hpl.jena.rdf.model.StmtIterator;
import com.hp.hpl.jena.vocabulary.DC_11;
import com.hp.hpl.jena.vocabulary.RDF;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;


public class TestEntityPool {
  static Gaboto oxp = null;

  @BeforeClass
  public static void setUp() throws Exception {
    oxp = OxpointsFactory.getOxpointsFromXML();
  }

  @Test
  public void testEntityPoolCreation()  {

    GabotoSnapshot snap = oxp.getSnapshot(new TimeInstant(2000, 1, 1));

    Model m = snap.getModel();

    // count buildings
    int nrOfBuildings = 0;
    ResIterator it = m.listResourcesWithProperty(RDF.type,
        OxPointsVocab.Building);
    while (it.hasNext()) {
      nrOfBuildings++;
      it.next();
    }

    // count colleges
    int nrOfColleges = 0;
    it = m.listResourcesWithProperty(RDF.type, OxPointsVocab.College);
    while (it.hasNext()) {
      nrOfColleges++;
      it.next();
    }

    // create entitypool
    EntityPool pool = EntityPool
        .createFrom(new EntityPoolConfiguration(snap));

    assertEquals(nrOfBuildings, pool.getEntities(new Building()).size());
    assertEquals(nrOfColleges, pool.getEntities(new College()).size());
  }

  /**
   * For a long while m2 was one bigger than m1.
   * Now they are the same, as one would hope.
   * I suspect that under some circumstances a default graph is added but not used.
   * Then it came back again. 
   */
  // FIXME What does this even test?
  @Test
  public void testModelSizeEquality()  {

    // as long as there is no data for the future in the system, this should
    // hold
    Model m1 = oxp.getSnapshot(TimeInstant.now()).getModel();
    EntityPool pool = EntityPool.createFrom(new EntityPoolConfiguration(oxp, m1));
    Model m2 = pool.createJenaModel();

    StmtIterator it = m1.listStatements();
    while (it.hasNext()) {
      Statement stmt = it.nextStatement();
      if (!m2.contains(stmt))
        System.out.println("Statement not in m2: " + stmt);
      //else 
      //  System.out.println("Statement in m2: " + stmt);
    }

     assertTrue(m1.size() + " not equals to " + m2.size(), Math.abs(m1.size()-m2.size()) == 0);
  }

  @Test
  public void testEntityAddReferencedEntities() {

    GabotoSnapshot snap = oxp.getSnapshot(new TimeInstant(2000, 1, 1));

    EntityPoolConfiguration config = new EntityPoolConfiguration(
        snap);
    config.setAddReferencedEntitiesToPool(false);
    config.addAcceptedType(OxPointsVocab.College_URI);

    EntityPool pool = EntityPool.createFrom(config);

    for (GabotoEntity e : pool.getEntities())
      assertTrue(e instanceof College);

    config = new EntityPoolConfiguration(snap);
    config.addAcceptedType(OxPointsVocab.Building_URI);
    config.setAddReferencedEntitiesToPool(false);
    pool = EntityPool.createFrom(config);

    for (GabotoEntity e : pool.getEntities())
      assertTrue(e instanceof Building);

    config = new EntityPoolConfiguration(snap);
    config.addAcceptedType(OxPointsVocab.College_URI);
    config.setAddReferencedEntitiesToPool(true);

    pool = EntityPool.createFrom(config);

    // Access some properties
    for (College c : pool.getEntities(new College())) {
      System.err.println(c.getPrimaryPlace());
    }

    boolean foundCollege = false;
    boolean foundBuilding = false;
    for (GabotoEntity e : pool.getEntities()) {
      if (e instanceof Building)
        foundBuilding = true;
      if (e instanceof College)
        foundCollege = true;
    }

    assertTrue(foundCollege);
    assertTrue(foundBuilding);
  }

  @Test
  public void testEntityFilter()  {
    GabotoSnapshot snap = oxp.getSnapshot(new TimeInstant(2000, 1, 1));

    EntityPoolConfiguration config = new EntityPoolConfiguration(
        snap);
    config.addEntityFilter(new EntityFilter() {

      @Override
      public Class<? extends GabotoEntity> appliesTo() {
        return College.class;
      }

      @Override
      public boolean filterEntity(GabotoEntity entity) {
        return false;
      }
    });

    EntityPool pool = EntityPool.createFrom(config);

    assertTrue(pool.getEntities(new College()).isEmpty());

    config = new EntityPoolConfiguration(snap);
    config.addEntityFilter(new EntityFilter() {

      @Override
      public boolean filterEntity(GabotoEntity entity) {
        if (!(entity instanceof College))
          return false;
        return true;
      }
    });

    pool = EntityPool.createFrom(config);

    for (GabotoEntity e : pool.getEntities())
      assertTrue(e instanceof College);
  }

  @Test
  public void testEntityFilter2()  {
    GabotoSnapshot snap = oxp.getSnapshot(TimeInstant.now());

    EntityPoolConfiguration config = new EntityPoolConfiguration(
        snap);
    config.addEntityFilter(new EntityFilter() {

      @Override
      public boolean filterEntity(GabotoEntity entity) {
        if (!(entity instanceof College))
          return false;
        return true;
      }
    });

    config.addEntityFilter(new EntityFilter() {

      @Override
      public Class<? extends GabotoEntity> appliesTo() {
        return College.class;
      }

      @Override
      public boolean filterEntity(GabotoEntity entity) {
        College col = (College) entity;

        // reject if no primary place is set
        if (col.getPrimaryPlace() == null)
          return false;
        System.err.println(col.getPrimaryPlace());
        // load location
        Place place = col.getPrimaryPlace();

        // reject if no location is set
        if (place.getLatitude() == null || place.getLongitude() == null)
          return false;

        double lat_oucs = 51.760010;
        double long_oucs = -1.260350;

        double lat_diff = place.getLatitude() - lat_oucs;
        double long_diff = place.getLongitude() - long_oucs;
        double distance = Math
            .sqrt(lat_diff * lat_diff + long_diff * long_diff);

        // if distance is small enough, allow entity to pass.
        if (distance < 0.002)
          return true;

        // reject by default
        return false;

      }
    });

    EntityPool.createFrom(config);
  }

  @Test
  public void testEntityTypeFilter() {
    GabotoSnapshot snap = oxp.getSnapshot(TimeInstant.now());

    EntityPoolConfiguration config = new EntityPoolConfiguration(
        snap);
    config.addAcceptedType(OxPointsVocab.College_URI);
    EntityPool pool = EntityPool.createFrom(config);
    for (GabotoEntity e : pool.getEntities())
      assertEquals(OxPointsVocab.College_URI, e.getType());

    config = new EntityPoolConfiguration(snap);
    config.addUnacceptedType(OxPointsVocab.College_URI);
    pool = EntityPool.createFrom(config);

    boolean bCollege = false;
    boolean bBuilding = false;
    for (GabotoEntity e : pool.getEntities()) {
      if (OxPointsVocab.College_URI.equals(e.getType()))
        bCollege = true;
      if (OxPointsVocab.Building_URI.equals(e.getType()))
        bBuilding = true;
    }

    assertTrue(bBuilding);
    assertTrue(!bCollege);
  }

  @Test
  public void testResourceFilter() throws Exception {
    GabotoSnapshot snap = oxp.getSnapshot(TimeInstant.now());

    EntityPoolConfiguration config = new EntityPoolConfiguration(
        snap);
    config.addAcceptedType(OxPointsVocab.College_URI);
    config.addResourceFilter(new PropertyExistsFilter(DC_11.title));
    EntityPool pool = EntityPool.createFrom(config);
    assertTrue("We have an empty pool; size: " + pool.getSize(),
        pool.getSize() > 0);

    config = new EntityPoolConfiguration(snap);
    config.addAcceptedType(OxPointsVocab.College_URI);
    config.addResourceFilter(new PropertyEqualsFilter(DC_11.title,
        "Somerville College"));
    pool = EntityPool.createFrom(config);
    assertTrue("Pool size is greater than 1:" + pool.getSize(),
        pool.getSize() == 1);

    Resource col = snap.getResource(pool.getEntities().toArray(
        new GabotoEntity[1])[0].getUri());
    config = new EntityPoolConfiguration(snap);
    config.addAcceptedType(OxPointsVocab.College_URI);
    config.addResource(col);
    config.addResourceFilter(new PropertyEqualsFilter(DC_11.title,
        "Somerville College"));
    pool = EntityPool.createFrom(config);
    assertTrue(pool.getSize() == 1);

  }
}
