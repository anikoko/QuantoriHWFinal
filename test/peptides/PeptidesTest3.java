package peptides;

import static org.junit.Assert.*;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

public class PeptidesTest3 {

	private Peptides3 peptides3;
	private String peptide;
	private String protein;

	@Before
	public void setup() {
		peptide = "RNLKDGHI";
		protein = "ABERNLKDGHIHWEPOGCVNWOORNLKDGHIMXVNXMCWERY";
		var library = List.of(peptide, "ORNLKDGH", "ABCDEFGH");
		peptides3 = new Peptides3(Peptides3.DEFAULT_PEPTIDE_SIZE, protein, library);
	}
	
	@Test
	public void test() {
		peptides3.findPositionsInProtein();
		
		List<Integer> positions = peptides3.search(peptide);
		assertTrue(positions.contains(3));
		assertTrue(positions.contains(23));
	}
	
	
	@Test
	public void testSpecificKMerExistsAtTwoPositions() throws Exception {
		peptides3.findPositionsInProtein();
		assertEquals(List.of(3, 23), peptides3.peptides.get(peptides3.stringToLong(peptide)));
	}
	
	@Test
	public void testFindPostionsInProteinListIsNull() throws Exception {
		var library = List.of("HELLO", "BELLO");
		Long valueOfHello = Long.parseLong("HELLO", 36);
		Long valueOfBello = Long.parseLong("BELLO", 36);
		peptides3 = new Peptides3(Peptides3.DEFAULT_PEPTIDE_SIZE, protein, library);
		peptides3.findPositionsInProtein();
		assertNotNull(peptides3.peptides);
		assertNull(peptides3.peptides.get(valueOfHello));
		assertNull(peptides3.peptides.get(valueOfBello));
	}

	
	@Test
	public void testFindPostionsInProtein() throws Exception {
		peptides3.findPositionsInProtein();
		assertNotNull(peptides3);
		assertEquals(2, peptides3.peptides.get(peptides3.stringToLong(peptide)).size());
	}
	
	@Test
	public void testAlphabetInit() throws Exception {
		assertEquals("ABCDEFGHIJKLMNOPQRSTUVWXYZ", new String(Benchmark1.ALPHABET));
	}
	
}









