package net.sf.gaboto.test;

import java.io.File;

import net.sf.gaboto.Gaboto;
import net.sf.gaboto.GabotoFactory;
import net.sf.gaboto.GabotoSnapshot;
import net.sf.gaboto.time.TimeInstant;
import net.sf.gaboto.vocabulary.OxPointsVocab;

import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.vocabulary.RDF;

import uk.ac.ox.oucs.oxpoints.gaboto.SeparatedTEIImporter;
import uk.ac.ox.oucs.oxpoints.gaboto.entities.College;

public class TestSeparatedTEIImporter {
	Gaboto gaboto = null;
	SeparatedTEIImporter importer = null;
	
	@Before
	public void setUp() {
		gaboto = GabotoFactory.getEmptyInMemoryGaboto();
		importer = new SeparatedTEIImporter(gaboto);
		importer.loadDirectory(new File("src/test/data/individual/"));
	}
	
	@Test
	public void testWhole() throws Exception {
		Assert.assertTrue(!importer.hasWarnings());
		gaboto.persistToDisk("target/");
		Utils.assertFileContentsStringEqual("target/graphs.rdf", "src/test/data/reference/separated_graphs.rdf");
	}
	
	@Test
	public void testSEH() throws Exception {
		GabotoSnapshot snapshot;
		Resource seh;
		
		snapshot = gaboto.getSnapshot(new TimeInstant("1930"));
		seh = snapshot.getResource(gaboto.getConfig().getNSData()+"23232449");
		Assert.assertTrue(seh.hasProperty(RDF.type, OxPointsVocab.Hall));

		snapshot = gaboto.getSnapshot(new TimeInstant("2000"));
		seh = snapshot.getResource(gaboto.getConfig().getNSData()+"23232449");
		Assert.assertTrue(seh.hasProperty(RDF.type, OxPointsVocab.College));
	}
}
