package dsaProblems.trie;

import java.util.HashMap;
import java.util.Map;

public class Trie {

	class Node {
		char data;
		Map<Character, Node> map;
		boolean isTerminal;

		public Node(char ch) {
			data = ch;
			isTerminal = false;
		}
	}

	Node root;

	public Trie() {
		root = new Node('*');
		root.map = new HashMap<>();
	}

	public void insert(String word) {
		Node temp = root;
		for (char c : word.toCharArray()) {
			if (!root.map.containsKey(c)) {
				Node n = new Node(c);
				root.map.put(c, n);
			} else {
				temp = root.map.get(c);
			}
		}
		temp.isTerminal = true;

	}

	public boolean search(String word) {
		Node temp = root;
		for (char c : word.toCharArray()) {
			if (!root.map.containsKey(c)) {
				return false;
			} else {
				temp = root.map.get(c);
			}
		}
		return temp.isTerminal;
	}

}
