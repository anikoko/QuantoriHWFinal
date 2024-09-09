package peptides;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


public class Peptides4 {

	public static final int DEFAULT_PEPTIDE_SIZE = 8;
	
	static void mergeSort(long[] array) {
		int middle = array.length / 2;
		long[] left;
		long[] right;
		
		if (array.length > 1) {
			middle = array.length / 2;
			
			
			left = Arrays.copyOfRange(array, 0, middle);
			right = Arrays.copyOfRange(array, middle, array.length);
			
			mergeSort(left);
			mergeSort(right);
			
			int i = 0;
			int j = 0;
			int k = 0;
			
			while (i < left.length && j < right.length) {
				if (left[i] < right[j]) {
					array[k] = left[i];
					i++;
				} else {
					array[k] = right[j];
					j++;
				}
				k++;
			}
			
			while (i < left.length) {
				array[k] = left[i];
				i++;
				k++;
			}
			
			while (j < right.length) {
				array[k] = right[j];
				j++;
				k++;
			}
			
			
		}
		
	}
	
	static int binarySearch(long[] array, long x) {
		int low = 0;
		int high = array.length - 1;
		while(low <= high) {
			int mid = low + (high - low) / 2;
			if (array[mid] == x) {
				return mid;
			}
			if (array[mid] < x) {
				low = mid+1;
			} else {
				high = mid-1;
			}
		}
		
		return -1;
	}
	

	private String protein;
	private int peptideSize;
	public HashMap<String, List<Integer>> kmers = new LinkedHashMap<>();

	
	long[] peptides;

	public Peptides4(int peptideSize, String protein, List<String> peptidesList) {
		this.peptideSize = peptideSize;
		this.protein = protein;
		this.peptides = mapPeptidesToLongArray(peptidesList);
	
	}

	private long[] mapPeptidesToLongArray(List<String> peptidesList) {
		peptides = new long[peptidesList.size()];
		for (int i = 0; i < peptides.length; i++) {
			Long value = stringToLong(peptidesList.get(i));
			peptides[i] = value;
		}
		mergeSort(peptides);
		return peptides;
	}
	
	void findPositionsInProtein() {
		for (int i = 0; i < protein.length() - peptideSize + 1; i++) {
			String kmer = protein.substring(i, i + peptideSize);
			Long kmerLong = stringToLong(kmer);
			int index = binarySearch(peptides, kmerLong);
			if(index != -1) {
				List<Integer> positions = kmers.get(kmer);
				if (positions == null) {
					positions = new ArrayList<>();
					kmers.put(kmer, positions);
				}
				positions.add(i);
			}
		}
	}
	
	public long stringToLong(String str) {
		long result = 0;
//		int length = Math.min(str.length(), 8);

        for (int i = 0; i < str.length(); i++) {
   
            long charValue = str.charAt(i);

           
            result = (result << 8);
            result |= charValue;
        }

        return result;
	}
	

	public List<Integer> search(String peptide) {
		return kmers.getOrDefault(peptide, List.of());
	}

}
