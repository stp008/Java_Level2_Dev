/**
 * @author clack008@gmail.com
 */

package week5;

import java.util.Arrays;

public class SimpleAlgorithms {

	public static void main(String[] args) {

		int[] arr = { 9, 8, 5, 4, 3, 6, 7, 2, 1 };
		// System.out.println(binarySearch(5, arr));
		int[] arr1 = countingSort(arr, 1, 10);

		for (int elem : arr) {
			System.out.println(elem);
		}

		System.out.println();

		for (int elem : arr1) {
			System.out.println(elem);
		}

	}

	public static <T extends Comparable<T>> int binarySearch(T key, T[] arr) {
		int low = 0;
		int hi = arr.length - 1;

		while (hi >= low) {
			int mid = low + (hi - low) / 2;
			int result = key.compareTo((T) arr[mid]);

			if (result > 0)
				low = mid + 1;
			if (result < 0)
				hi = mid - 1;
			if (result == 0)
				return mid;
		}

		return -1;
	}

	public static int[] countingSort(int[] array, int min, int max) {
		int[] arr = Arrays.copyOf(array, array.length);
		int[] count = new int[max - min + 1];
		for (int elem : arr) {
			count[elem - min]++;
		}
		int z = 0;
		for (int i = min; i <= max; i++) {
			while (count[i - min] > 0) {
				arr[z] = i;
				z++;
				count[i - min]--;
			}
		}

		return arr;
	}

}
