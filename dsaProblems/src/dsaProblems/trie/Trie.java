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
			map = new HashMap<>();
			isTerminal = false;
		}
	}

	Node root;

	public Trie() {
		this.root = new Node('*');
		
	}

	public void insert(String word) {
		Node temp = root;
		for (char c : word.toCharArray()) {
			if (!temp.map.containsKey(c)) {
				Node n = new Node(c);
				temp.map.put(c, n);
			} 
				temp = temp.map.get(c);
			
		}
		temp.isTerminal = true;

	}

	public boolean search(String word) {
		Node temp = root;
		for (char c : word.toCharArray()) {
			if (!temp.map.containsKey(c)) {
				return false;
			} else {
				temp = temp.map.get(c);
			}
		}
		return temp.isTerminal;
	}

}
