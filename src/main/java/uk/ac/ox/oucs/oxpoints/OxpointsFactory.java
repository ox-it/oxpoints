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

package uk.ac.ox.oucs.oxpoints;

import java.io.File;

import net.sf.gaboto.Gaboto;
import net.sf.gaboto.GabotoFactory;
import uk.ac.ox.oucs.oxpoints.gaboto.TEIImporter;

/**
 * @author timp
 * @since 11 September 2009
 *
 */
public final class OxpointsFactory {
  
  public static String filename = "src/test/data/oxpoints_plus.xml"; 

  public static Gaboto getOxpointsFromXML() {
    return getOxpointsFromXML(filename);
  }
  public static Gaboto getOxpointsFromXML(String filenameIn) { 
    System.err.println("Reading oxp from " + filenameIn);
    File file = new File(filenameIn);
    if(! file.exists()) {
      file = new File("examples/oxpoints/" + filename);      
      if(! file.exists()) {
        throw new RuntimeException ("Cannot open file " + filenameIn);
      }
    }
    Gaboto oxp = null;
    synchronized (oxp) { 
      oxp = GabotoFactory.getEmptyInMemoryGaboto();
      new TEIImporter(oxp, file).run();
    }
    return oxp;
    
  }


}
