package assembly;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class DeBruijnGraph {
	
	public static final int K_MER_LENGTH = 4;
	
	public Graph graph  = new Graph();
	String genome;
	List<String> kmers  = new ArrayList<String>();
	int kMerLength;
	
	
	public DeBruijnGraph(String genome, int kMerLength) {
		this.genome = genome;
		this.kMerLength = kMerLength;
		createGraph();
	}
	
	public String prefix(String kmer) {
		return kmer.substring(0, kMerLength-1); 
	}
	
	public String suffix(String kmer) {
		return kmer.substring(1, kMerLength); 
	}
	
	public void createKMers() {
		for (int i = 0; i < genome.length() - kMerLength + 1; i++) {
			String kmer = genome.substring(i, i + kMerLength);
			kmers.add(kmer);
		}
	}
	
	public void createGraph() {
		createKMers();
		for (String kmer : kmers) {
			String prefix = prefix(kmer);
			String suffix = suffix(kmer);
			graph.addVertex(prefix);
			graph.addEdge(prefix, suffix);
		}

	}

}
