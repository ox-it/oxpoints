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
import static org.junit.Assert.fail;

import net.sf.gaboto.time.TimeInstant;
import net.sf.gaboto.time.TimeSpan;

import org.junit.Test;

public class TestTimeSpan {

  @Test
  public void testEqual() {
    TimeSpan ts0 = new TimeSpan(100, 3, 10, 200, 6, 8);
    TimeSpan ts1 = new TimeSpan(100, 3, 10, 200, 6, 8);

    assertEquals(ts0, ts1);

    TimeSpan ts2 = new TimeSpan(100, 3, null, 200, 6, null);
    TimeSpan ts3 = new TimeSpan(100, 3, null, 200, 6, null);

    assertEquals(ts2, ts3);
    assertTrue(!ts2.equals(ts0));

    ts2.setStartDay(10);
    ts2.setDurationDay(8);
    assertEquals(ts2, ts0);

    TimeSpan ts4 = new TimeSpan(88, 3, 10, 200, 6, 8);
    TimeSpan ts5 = new TimeSpan(100, 3, 11, 200, 6, 8);

    assertTrue(!ts0.equals(ts4));
    assertTrue(!ts0.equals(ts5));
  }

  @Test
  public void testGetBegin() {
    TimeSpan ts1 = new TimeSpan(100, 3, 10, 200, 6, 8);
    TimeSpan ts2 = new TimeSpan(100, null, null);

    TimeInstant ti1 = new TimeInstant(100, 3, 10);
    TimeInstant ti2 = new TimeInstant(100, null, null);

    assertEquals(ts1.getBegin(), ti1);
    assertEquals(ts2.getBegin(), ti2);
  }

  @Test
  public void testContainsInstantsNoOverflow() {
    TimeSpan ts0_test = new TimeSpan(100, 3, 10, 200, 6, 8);

    TimeInstant ti0_contained = new TimeInstant(200, 8, 10);
    assertTrue(ts0_test.contains(ti0_contained));

    TimeInstant ti1_notcontained = new TimeInstant(100, 3, 9);
    assertTrue(!ts0_test.contains(ti1_notcontained));

    TimeInstant ti2_notcontained = new TimeInstant(300, 9, 19);
    assertTrue(!ts0_test.contains(ti2_notcontained));

    TimeInstant ti3_notcontained = new TimeInstant(600, 1, 1);
    assertTrue(!ts0_test.contains(ti3_notcontained));

    TimeInstant ti4_notcontained = new TimeInstant(99, 1, 1);
    assertTrue(!ts0_test.contains(ti4_notcontained));

    TimeInstant ti5_contained = new TimeInstant(100, 3, 10);
    assertTrue(ts0_test.contains(ti5_contained));

    TimeInstant ti6_contained = new TimeInstant(300, null, null);
    assertTrue(ts0_test.contains(ti6_contained));

    TimeSpan ts1_test = new TimeSpan(100, null, null, 200, null, null);

    TimeInstant ti7_contained = new TimeInstant(200, 3, 18);
    assertTrue(ts1_test.contains(ti7_contained));

    TimeInstant ti8_contained = new TimeInstant(300, 3, 18);
    assertTrue(ts1_test.contains(ti8_contained));

    TimeSpan ts2_test = new TimeSpan(100, 3, 10);
    assertTrue(ts2_test.contains(ti0_contained));
  }

  @Test
  public void testNegativeMonth() {
    try {
      new TimeInstant(101, -1, 1);
      fail("Should have bombed");
    } catch (IllegalArgumentException e) {
      e = null;
    }

  }

  @Test
  public void testContainsInstantsOverflow() {
    TimeSpan ts0_test = new TimeSpan(100, 7, 28, 0, 6, 3);

    TimeInstant ti0_contained = new TimeInstant(101, 1, 1);
    assertTrue(ts0_test.contains(ti0_contained));

    TimeInstant ti1_contained = new TimeInstant(101, 2, 1);
    assertTrue(ts0_test.contains(ti1_contained));

    TimeInstant ti1_notcontained = new TimeInstant(101, 3, 1);
    assertTrue(ts0_test + " contains " + ti1_notcontained, !ts0_test.contains(ti1_notcontained));

    TimeSpan ts1_test = new TimeSpan(100, 8, 28, 0, 5, 4);

    TimeInstant ti2_contained = new TimeInstant(101, 1, 1);
    assertTrue(ts1_test.contains(ti2_contained));

  }

  @Test
  public void testContainsTimeSpans() {
    TimeSpan ts_test = new TimeSpan(100, 3, 10, 200, 6, 8);

    TimeSpan ts1_contained = new TimeSpan(100, 3, 10, 1, 1, 1);
    assertTrue(ts_test.contains(ts1_contained));

    TimeSpan ts2_contained = new TimeSpan(100, 3, 10, 100, null, null);
    assertTrue(ts_test.contains(ts2_contained));

    TimeSpan ts3_contained = new TimeSpan(200, 4, 1, 1, 1, 1);
    assertTrue(ts_test.contains(ts3_contained));

    TimeSpan ts4_notcontained = new TimeSpan(100, 3, 10);
    assertTrue(!ts_test.contains(ts4_notcontained));

    TimeSpan ts5_notcontained = new TimeSpan(99, 3, 10, 1, 1, 1);
    assertTrue(!ts_test.contains(ts5_notcontained));

    TimeSpan ts6_notcontained = new TimeSpan(100, 3, 10, 200, 6, 9);
    assertTrue(!ts_test.contains(ts6_notcontained));

    TimeSpan ts7_notcontained = new TimeSpan(100, 3, 10, 200, 7, 8);
    assertTrue(!ts_test.contains(ts7_notcontained));

    TimeSpan ts8_notcontained = new TimeSpan(100, 3, 10, 201, 6, 8);
    assertTrue(!ts_test.contains(ts8_notcontained));

    TimeSpan ts9_notcontained = new TimeSpan(300, 9, 18, null, null, 1);
    assertTrue(!ts_test.contains(ts9_notcontained));

    TimeSpan ts_test2 = new TimeSpan(500, 9, 15, 100, 3, 20);

    assertTrue(ts_test2.contains(new TimeInstant(500, null, null)));
    assertTrue(ts_test2.contains(new TimeInstant(601, 1, 4)));
  }

  @Test
  public void testOverlap() {
    TimeSpan ts1 = new TimeSpan(100, 3, 12, 300, 4, 20);

    TimeSpan ts2 = new TimeSpan(0, 1, 1, 100, 2, 12);
    TimeSpan ts3 = new TimeSpan(0, 1, 1, 200, 2, 12);
    TimeSpan ts4 = new TimeSpan(300, 2, 10, 400, 1, 0);
    TimeSpan ts5 = new TimeSpan(200, null, null);

    assertTrue(ts1.overlaps(ts2));
    assertTrue(ts2.overlaps(ts1));

    assertTrue(ts1.overlaps(ts3));
    assertTrue(ts3.overlaps(ts1));

    assertTrue(ts1.overlaps(ts4));
    assertTrue(ts4.overlaps(ts1));

    assertTrue(ts1.overlaps(ts5));
    assertTrue(ts5.overlaps(ts1));

    TimeSpan ts6 = new TimeSpan(0, 1, 1, 100, 2, 10);
    TimeSpan ts7 = new TimeSpan(400, 10, 1);

    assertTrue(ts1 + " overlaps " + ts6, !ts1.overlaps(ts6));
    assertTrue(!ts6.overlaps(ts1));

    assertTrue(!ts1.overlaps(ts7));
    assertTrue(!ts7.overlaps(ts1));
  }

  @Test
  public void brokenTestCreateByInstants() {
    // FIXME Not the way to test things: think of corner cases
    // since we are working with random time instants .. give it some extra
    // tries to fail.
    for (int i = 0; i < 2000; i++) {
      TimeInstant ti1 = TimeUtils.getRandomTimeinstant();
      TimeInstant ti2 = TimeUtils.getRandomTimeinstant();
      TimeInstant early, late;

      if (ti1.equals(ti2)) {
        i--;
        continue;
      }

      TimeSpan ts = null;

      if (ti1.compareTo(ti2) < 0) {
        early = ti1;
        late = ti2;
      } else {
        early = ti2;
        late = ti1;
      }

      ts = TimeSpan.createFromInstants(early, late);

      System.out.println(early);
      System.out.println(ts.getBegin());
      System.out.println(late);
      System.out.println(ts.getEnd());
      System.out.println();

      assertTrue(ts.getEnd() + " not the same as " + early, ts.getBegin().canUnify(early));
      if (!early.canUnify(late))
        assertTrue(ts.getEnd() + " not the same as " + late, ts.getEnd().canUnify(late));
    }
    
  }

  @Test
  public void testTimespan() {
    TimeInstant ti1 = new TimeInstant(2008, 7, 4);
    System.err.println(ti1);
    TimeInstant ti2 = new TimeInstant(2008, 7, 4);
    TimeInstant ti3 = new TimeInstant(2009, 7, 4);
    System.err.println(ti2);
    TimeSpan ts = TimeSpan.createFromInstants(ti1, ti2);
    System.err.println(ts);
    System.err.println(ts.getEnd());
    assertTrue(ts.getEnd() + " not the same as " + ti3, ts.getEnd().canUnify(ti3));

  }

  @Test
  public void testSomething() {
    TimeSpan ts = new TimeSpan(1913, 1, 2, 23, 5, 27);

    assertTrue(ts.getBegin().equals(new TimeInstant(1913, 1, 2)));
    assertTrue(ts.getEnd().equals(new TimeInstant(1936, 6, 29)));

    TimeInstant early = new TimeInstant(884, 5, 27);
    TimeInstant late = new TimeInstant(1281, 6, 1);

    ts = TimeSpan.createFromInstants(early, late);

    assertTrue(ts.getBegin().canUnify(early));
    assertTrue(ts.getEnd().canUnify(late));
  }
}
