package peptides;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Peptides2 {

	public static final int DEFAULT_PEPTIDE_SIZE = 8;

	private String protein;
	private int peptideSize;

	HashMap<String, List<Integer>> peptides;

	public Peptides2(int peptideSize, String protein, HashMap<String, List<Integer>> peptides) {
		this.peptideSize = peptideSize;
		this.protein = protein;
		this.peptides = peptides;
	}

	void findPositionsInProtein() {
		for (int i = 0; i < protein.length() - peptideSize + 1; i++) {
			String kmer = protein.substring(i, i + peptideSize);
			if(isPeptide(kmer)) {
				List<Integer> positions =  peptides.get(kmer);
				if (positions == null) {
					positions = new ArrayList<>();
					peptides.put(kmer, positions);
				}
				positions.add(i);
			}
		}
//		System.out.println(peptides);
	}

	public boolean isPeptide(String kmer) {
		return peptides.containsKey(kmer);
	}
	
	public List<Integer> search(String peptide) {
		return peptides.getOrDefault(peptide, List.of());
	}

}
