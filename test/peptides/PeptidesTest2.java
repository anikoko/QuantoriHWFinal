package peptides;

import static org.junit.Assert.*;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

public class PeptidesTest2 {

	private Peptides2 peptides2;
	private String peptide;
	private String protein;

	@Before
	public void setup() {
		peptide = "RNLKDGHI";
		protein = "ABERNLKDGHIHWEPOGCVNWOORNLKDGHIMXVNXMCWERY";
		var library = new LinkedHashMap<String, List<Integer>>();
		library.put(peptide, null);
		library.put("ORNLKDGH", null);
		library.put("ABCDEFGH", null);
		peptides2 = new Peptides2(Peptides2.DEFAULT_PEPTIDE_SIZE, protein, library);
	}
	
	@Test
	public void test() {
		peptides2.findPositionsInProtein();
		List<Integer> positions = peptides2.search(peptide);
		assertTrue(positions.contains(3));
		assertTrue(positions.contains(23));
	}
	
	@Test
	public void testSpecificKMerExistsAtTwoPositions() throws Exception {
		peptides2.findPositionsInProtein();
		assertEquals(List.of(3, 23), peptides2.peptides.get(peptide));
	}
	
	@Test
	public void testFindPostionsInProteinListIsNull() throws Exception {
		var library = new LinkedHashMap<String, List<Integer>>();
		library.put("HELLO", null);
		library.put("BELLO", null);
		peptides2 = new Peptides2(Peptides2.DEFAULT_PEPTIDE_SIZE, protein, library);
		peptides2.findPositionsInProtein();
		assertNotNull(peptides2.peptides);
		assertNull(peptides2.peptides.get("HELLO"));
		assertNull(peptides2.peptides.get("BELLO"));
	}

	@Test
	public void testFindPostionsInProtein() throws Exception {
		peptides2.findPositionsInProtein();
		assertNotNull(peptides2);
		assertEquals(2, peptides2.peptides.get(peptide).size());
	}
	
	@Test
	public void testAlphabetInit() throws Exception {
		assertEquals("ABCDEFGHIJKLMNOPQRSTUVWXYZ", new String(Benchmark1.ALPHABET));
	}
	
}









