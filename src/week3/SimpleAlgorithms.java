/**
 * @author clack008@gmail.com
 */

/*
 - найти максимальный элемент массива (в массиве только по-
 ложительные целые числа)
 - написать функцию разворота строки (abcd -> dcba)
 - на вход функции подается строка, посчитать частоту вхожде-
 ния каждой буквы в строке и вернуть Map<String, Integer> -
 список букв и их частот
 */
package week3;

import java.util.Map;
import java.util.TreeMap;

public class SimpleAlgorithms {

	public static <T extends Comparable<T>> T maxElem(T[] arr) {
		T max = arr[0];
		for (T elem : arr) {
			if (elem.compareTo(max) == 1)
				max = elem;
		}
		return max;
	}

	public static String strReverse(String source) {
		StringBuilder reversed = new StringBuilder();
		for (int i = source.length() - 1; i >= 0; i--) {
			reversed.append(source.charAt(i));
		}
		return reversed.toString();
	}

	public static Map<String, Integer> freqCounter(String source) {
		Map<String, Integer> frequency = new TreeMap<>();
		char[] chars = source.toCharArray();
		for (int i = 0; i < chars.length; i++) {
			String character = String.valueOf(chars[i]);
			if (frequency.containsKey(character)) {
				frequency.put(character, frequency.get(character) + 1);
				continue;
			}
			frequency.put(character, 1);
		}
		return frequency;
	}

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		System.out
				.println(maxElem(new Comparable[] { 5, 6, 3485000, 40000, 6 }));
		System.out
				.println(freqCounter("cccccddddaaaaaaaaaaaaaaaaaaaaaaaaaaaa"));
	}
}
