package dsaProblems.trie;

import java.util.HashMap;
import java.util.Map;

public class SuffixTrie {

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

	public SuffixTrie() {
		this.root = new Node('*');
		
	}

	private void insertHelper(String word) {
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
	
	public void insert(String word) {
		for(int i=0;word.charAt(i)!='\0';i++) {
			insertHelper(word.substring(i));
		}
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
