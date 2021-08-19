package dsaProblems.arrayds;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ArrayDS {

	public List<Integer> returnDuplicates() {
		List<Integer> list = new ArrayList<>();
		list.add(0);
		list.add(0);
		list.add(1);
		list.add(2);
		list.add(1);
		Collections.sort(list);
		List<Integer> l2 = new ArrayList<>();
		for (int i = 0; i < list.size() - 1; i++) {
			if (list.get(i) == list.get(i + 1)) {
				l2.add(list.get(i));
			}
		}
		return l2;
	}

	String replaceVowels(String s) {
		StringBuilder sb = new StringBuilder();
		List<Character> vowels = new ArrayList<>(Arrays.asList('a', 'e', 'i', 'o', 'u'));

		for (char c : s.toCharArray()) {
			if (!vowels.contains(c)) {
				sb.append(String.valueOf(c));
			}
		}
		return sb.toString();
	}
}
