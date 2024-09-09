package assenbley;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Ignore;
import org.junit.jupiter.api.Test;

import assembly.DeBruijnGraph;
import assembly.EulerianPath;

class TestEulerianPath {
	
	@Test
	void testReconstructGenome() {
		DeBruijnGraph g = new DeBruijnGraph("AAABBBBA", 3);
		EulerianPath path = new EulerianPath(g);
		String reconstructedGenome = path.reconstructGenome();
		assertTrue(reconstructedGenome.equals("AAABBBBA"));
		DeBruijnGraph g2 = new DeBruijnGraph("GATCACA", DeBruijnGraph.K_MER_LENGTH);
		EulerianPath path2 = new EulerianPath(g2);
		String reconstructedGenome2 = path2.reconstructGenome();
		assertTrue(reconstructedGenome2.equals("GATCACA"));

	}

	@Test
	void testReconstructLargeGenome() {
		String genome = "GATCACAGGTCTATCACCCTATTAACCACTCACGGGAGCTCTCCATGCATTTGGTATTTTCGTCTGGGGGGTGTGCACGCGATAGCATTGCGAGACGCTGGAGCCGGAGCACCCTATGTCGCAGTATCTGTCTTTGATTCCTGCCTCATTCTATTATTTATCGCACCTACGTTCAATATTACAGGCGAACATACCTACTAAAGTGTGTTAATTAATTAATGCTTGTAGGACATAATAATAACAATTGAAT";
		DeBruijnGraph g = new DeBruijnGraph(genome, DeBruijnGraph.K_MER_LENGTH);
		EulerianPath path = new EulerianPath(g);
		String reconstructedGenome = path.reconstructGenome();
		assertTrue(reconstructedGenome.equals(genome));		
	}
}
