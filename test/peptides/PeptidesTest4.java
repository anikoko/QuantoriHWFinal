package peptides;

import static org.junit.Assert.*;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

public class PeptidesTest4 {

	private Peptides4 peptides4;
	private String peptide;
	private String protein;

	@Before
	public void setup() {
		peptide = "RNLKDGHI";
		protein = "ABERNLKDGHIHWEPOGCVNWOORNLKDGHIMXVNXMCWERY";
		var library = List.of(peptide, "ORNLKDGH", "ABCDEFGH");
		peptides4 = new Peptides4(Peptides4.DEFAULT_PEPTIDE_SIZE, protein, library);
	}
	
	
	@Test
	public void test() {
		peptides4.findPositionsInProtein();
		
		List<Integer> positions = peptides4.search(peptide);
		assertTrue(positions.contains(3));
		assertTrue(positions.contains(23));
	}
	
	@Test
	public void testSpecificKMerExistsAtTwoPositions() throws Exception {
		peptides4.findPositionsInProtein();
		assertEquals(List.of(3, 23), peptides4.kmers.get(peptide));
	}
	
	@Test
	public void testFindPostionsInProteinListIsNull() throws Exception {
		var library = List.of("HELLO", "BELLO");
		peptides4 = new Peptides4(Peptides4.DEFAULT_PEPTIDE_SIZE, protein, library);
		peptides4.findPositionsInProtein();
		assertNotNull(peptides4.peptides);
		assertNull(peptides4.kmers.get("Hello"));
		assertNull(peptides4.kmers.get("Bello"));
	}

	@Test
	public void testFindPostionsInProtein() throws Exception {
		peptides4.findPositionsInProtein();
		assertNotNull(peptides4);
		assertEquals(2, peptides4.kmers.get(peptide).size());
	}
	
	@Test
	public void testAlphabetInit() throws Exception {
		assertEquals("ABCDEFGHIJKLMNOPQRSTUVWXYZ", new String(Benchmark1.ALPHABET));
	}
	
}









