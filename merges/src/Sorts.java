public class Sorts {
	@SuppressWarnings({ "rawtypes", "unchecked" }) 
	private static void mergeSort (Comparable item[], Comparable aux[], int start, int end) {
		if (start >= end) {
			return;
		}
		
		int middle = start + (end - start) / 2;
		mergeSort (item, aux, start, middle);
		mergeSort (item, aux, middle + 1, start);
		
		merge (item, aux, start, middle, end);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void merge (Comparable items[], Comparable aux[], int start, int middle, int end) {
		for (int k = start; k <= end; k++) {
			aux[k] = items[k];
		}
		
		int i = start, j = middle + 1;
		for (int k = start; k <= end; k++) {
			if      (i > middle)                   items[k] = aux[j++];
			else if (j > end)                      items[k] = aux[i++];
			else if (aux[j].compareTo(aux[i]) < 0) items[k] = aux[j++];
			else                                   items[k] = aux[i++];
		}
	}
	
	public static void mergeSort (Comparable item[]) {
		Comparable aux[] = new Comparable [item.length];
		mergeSort (item, aux, 0, item.length - 1);
	}
	
	public static void main (String args[]) {
		Integer array[] = {9, 8, 7, 6, 5, 4, 3, 2, 1},
				aux[] = new Integer[array.length];

		mergeSort (array);
		for (Integer i : array) {
			System.out.printf("%d, ", i);
		}
		System.out.println();
	}
}