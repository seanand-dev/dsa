package dsaProblems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

import dsaProblems.arrayds.ArrayDS;
import dsaProblems.graphds.GraphHelper;
import dsaProblems.tree.Tree;
import dsaProblems.trie.Trie;

public class TestMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		GraphHelper hg=new GraphHelper();
		//LinkedList<Integer> adj[]= new LinkedList[3];
		ArrayList<Integer> list1=new ArrayList<>(Arrays.asList(0,0,0));
		ArrayList<Integer> list2=new ArrayList<>(Arrays.asList(0,1,0));
		ArrayList<Integer> list3=new ArrayList<>(Arrays.asList(1,1,1));

//		adj[0]=list1;
//		adj[1]=list2;
//		adj[2]=list3;
		List<List<Integer>> adjL=new ArrayList<>(Arrays.asList(list1,list2,list3));

	//	hg.bfs(adj, 0, 3);
		int[][] arr=new int[3][3];
		for(int i=0;i<3;i++) {
			for(int j=0;j<3;j++) {
				arr[i][j]=i+j;
			}
		}
		
		int[] a1= {2,1,5};
		int[] a2= {3,3,7};
//		int[] a3= {2,2};
//		int[] a4= {3,4};
//		int[] a5= {1,1};
		int[][] grid=new int[2][2];
		grid[0]=a1;
		grid[1]=a2;
//		grid[2]=a3;
//		grid[3]=a4;
//		grid[4]=a5;
		ArrayDS ads=new ArrayDS();
		ads.generateParenthesis(3);
		Stack<Integer> st=new Stack<>();
			Tree tree=new Tree();
			//tree.splitBST(new int[] {4,2,6,1,3,5,7},2);
		Trie trie=new Trie();
		String[] words= {"oath","pea","eat","rain"};
//		for(String word:words) {
//			trie.insert(word);
//		}
		//trie.findWords(words);
	//	System.out.println(trie.search("dog"));
	}
	
	
	
	

}
