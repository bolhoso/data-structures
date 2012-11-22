#include <stdio.h>
#include <stdlib.h>
#include <malloc.h>
#include <time.h>

inline void xchange (int *arr, int i, int j) {
	int aux = arr[i];
	arr[i] = arr[j];
	arr[j] = aux;
}

int partition (int *arr, int lo, int hi) {
	int i = lo, j = hi+1;

	while (1) {
		while (arr[++i] < arr[lo])
			if (i == hi) break;

		while (arr[lo] < arr[--j] )
			if (j == lo) break;

		// If the indexes didn't crossed, then I xchange them
		if (i >= j)
			break;

		xchange (arr, i, j);
	} 

	xchange (arr, lo, j);
	return j;
}

void myqsort_rec (int *arr, int lo, int hi) {
	if (lo > hi) {
		return;
	}

	int pivot = partition (arr, lo, hi);
	myqsort_rec (arr, lo, pivot - 1);
	myqsort_rec (arr, pivot+1, hi);
}

void myqsort (int *arr, int size) {
	myqsort_rec (arr, 0, size - 1);
}

int select_kth (int *a, int size, int k) {
	int lo = 0, hi = size - 1;
	k--;
	int j;
	do {
		j = partition (a, lo, hi);

		if      (j < k) lo = j + 1; // j is before the k-th, so ignore the lower part
		else if (j > k) hi = j - 1; // j is after the k-th, so ignore the higher part
	} while (j != k);

	return j;
}

void print (int *arr, int len) {
	int i;
	for (i = 0; i < 15; i++) {
		printf ("%c", arr[i]);
		if (i < 14) {
			printf (", ");
		}
	}
	putchar ('\n');
}

int is_sorted (int *arr, int len) {
	while (--len > 0) {
		if (arr[len] < arr[len-1]) 
			return 0;
	}

	return 1;
}

/*int main () {
	int arr[] = { 'K', 'R', 'A', 'T', 'E', 'L', 'E', 'P', 'U', 'I', 'M', 'Q', 'C', 'X', 'O', 'S' };
	int arr2[] = { 'K', 'R', 'A', 'T', 'E', 'L', 'E', 'P', 'U', 'I', 'M', 'Q', 'C', 'X', 'O', 'S' };

	myqsort (arr, 16);
	printf ("Original: "); print (arr2, 16);
	printf ("Sorted  : "); print (arr, 16);

	int kth = select_kth (arr2, 16, 5);
	printf ("The 5rd is arr[%d] = %c\n", kth, arr2[kth]);
	
	return 0;
h}
*/

int main (int argc, char *argv[]) {
	int size = atoi (argv[1]);
	int *arr = malloc (sizeof (int) * size);


	int i;
	srand(time(NULL));
	for (i = 0; i < size; i++) {
		arr[i] = rand() % size;
	}

	myqsort (arr, size);
	if (is_sorted (arr, size)) {
		printf ("OK\n");
	}
}
