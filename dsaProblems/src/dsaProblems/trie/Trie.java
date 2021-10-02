package dsaProblems.trie;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Trie {

	class Node {
		char data;
		Map<Character, Node> map;
		boolean isTerminal;
		String word="";
		Node[] children=new Node[26];

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
	List<String> result;
	char[][] b;
	public List<String> findWords(char[][] board, String[] words) {
        this.b=board;
        result=new ArrayList<>();
        Trie trie=new Trie();
        trie.buildTree(words);
        Node temp = root;
        for(int i=0;i<board.length;i++){
            for(int j=0;j<board[i].length;j++){
                dfs(i,j,temp);
             }
        }
        Collections.sort(result);
        return result;
}
    void dfs(int r,int c,Node trie){
        if(r<0 || c<0 || r==b.length || c==b[r].length || b[r][c]=='#'){
            return;
        }
        char curr=b[r][c];
        Node nxt=trie.children[curr-'a'];
        if(nxt==null){
            return;
        } if(!nxt.word.equals("")){
            result.add(nxt.word);
            nxt.word="";
        }
        b[r][c]='#';
        dfs(r+1,c,trie);
        dfs(r-1,c,trie);
        dfs(r,c+1,trie);
        dfs(r,c-1,trie);
        b[r][c]=curr;
    }
    
    public void buildTree(String[] words){
        Trie curr=this;
       
        for(String word:words){
        	 Node temp=root;
            for(char c:word.toCharArray()){
                 if(temp.children[c-'a']==null){
                	 Node n = new Node(c);
                     n.children[c-'a']=n;
                 }
                temp=temp.children[c-'a'];
            }
            temp.word=word;
           
        }
        
    }
    
 

}
