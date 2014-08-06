/**
 * @author clack008@gmail.com
 */

package week1;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class LengthComparator implements Comparator<String> {

	@Override
	public int compare(String o1, String o2) {
		return o1.length() - o2.length();
	}

	public static void main(String[] args) {
		List<String> s = Arrays.asList("aaa", "b", "cd");
		// Should return {b, cd , aaa}
		Collections.sort(s, new LengthComparator());
		for (String elem : s) {
			System.out.println(elem);
		}
	}
}
