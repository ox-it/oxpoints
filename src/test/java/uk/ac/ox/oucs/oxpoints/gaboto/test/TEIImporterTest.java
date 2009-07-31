package uk.ac.ox.oucs.oxpoints.gaboto.test;


import java.io.File;

import net.sf.gaboto.Gaboto;
import net.sf.gaboto.GabotoConfiguration;
import net.sf.gaboto.GabotoFactory;

import org.junit.Test;

import uk.ac.ox.oucs.oxpoints.gaboto.TEIImporter;

public class TEIImporterTest {

  @Test
  public void testRun() {
    
    File file = new File("uk/ac/ox/oucs/oxpoints/gabaoto/test/OUCS.xml");
    GabotoFactory.init(GabotoConfiguration.fromConfigFile());
    Gaboto gab = GabotoFactory.getPersistentGaboto();
    new TEIImporter(gab, file).run();
  }

}
