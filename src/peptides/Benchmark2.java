package peptides;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Random;

public class Benchmark2 {
	
	private static final int PROTEIN_SIZE = 10_000;
	private static final int LIBRARY_SIZE = 100_000;
	static final byte[] ALPHABET = new byte[26];
	
	static {
		for (byte c = 'A'; c <= 'Z'; c++) {
			ALPHABET[c-'A'] = c;
		}
	}

	public static void main(String[] args) {
		
		System.out.println("Benchmark 2");
		
		System.out.println("generating data...");
		
		String protein = generateProtein(PROTEIN_SIZE);
		HashMap<String, List<Integer>> library = generatePeptides(LIBRARY_SIZE);
		Peptides2 peptides = new Peptides2(Peptides1.DEFAULT_PEPTIDE_SIZE, protein, library);
		
		System.out.println("searching peptides...");
		long start = System.currentTimeMillis();
		peptides.findPositionsInProtein();
		long stop = System.currentTimeMillis();
		
		System.out.println("Elapsed: " + (stop - start));
		
		
	}

	static String generateProtein(int proteinSize) {
		Random r = new Random();
		var data = new byte[proteinSize];
		for (int i = 0; i < proteinSize; i++) {
			data[i] = ALPHABET[r.nextInt(ALPHABET.length)];
		}
		return new String(data);
	}

	private static HashMap<String, List<Integer>> generatePeptides(int librarySize) {
		var peptides = new LinkedHashMap<String, List<Integer>>();
		for (int i = 0; i < librarySize; i++) {
			var peptide = generateProtein(PROTEIN_SIZE);
			peptides.put(peptide, null);
		}
		return peptides;
	}


}
