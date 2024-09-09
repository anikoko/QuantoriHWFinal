package assenbley;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.jupiter.api.Test;

import assembly.DeBruijnGraph;

class TestDeBruijnGraph2 {
	
	private DeBruijnGraph DBgraph;
	
	@Test
	void testPrefixAndSuffix() {
		String genome = "GATCACA";
		this.DBgraph = new DeBruijnGraph(genome, DeBruijnGraph.K_MER_LENGTH);
		String prefix = DBgraph.prefix("GATC");
		String suffix = DBgraph.suffix("GATC");
		System.out.println(prefix);
		assertTrue(prefix.equals("GAT"));
		assertTrue(suffix.equals("ATC"));
	}
	
	@Test
	void testCreateKmers() {
		String genome = "GATCACA";
		this.DBgraph = new DeBruijnGraph(genome, DeBruijnGraph.K_MER_LENGTH);
		
	}
	
	@Test
	void testCreationOfGraph() {
		String genome = "GATCACA";
		this.DBgraph = new DeBruijnGraph(genome, DeBruijnGraph.K_MER_LENGTH);
		Map<String, List<String>> adjList = DBgraph.graph.getAdjacencyList();
		

	}

}
