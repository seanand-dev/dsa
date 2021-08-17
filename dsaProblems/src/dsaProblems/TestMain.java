package dsaProblems;

import java.util.Arrays;
import java.util.LinkedList;

import dsaProblems.graphds.GraphHelper;

public class TestMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		GraphHelper hg=new GraphHelper();
		LinkedList<Integer> adj[]= new LinkedList[3];
		LinkedList<Integer> list1=new LinkedList<>(Arrays.asList(1,2));
		LinkedList<Integer> list2=new LinkedList<>(Arrays.asList(2));
		LinkedList<Integer> list3=new LinkedList<>(Arrays.asList(0,2));

		adj[0]=list1;
		adj[1]=list2;
		adj[2]=list3;
	//	hg.bfs(adj, 0, 3);
		int[][] arr=new int[3][3];
		for(int i=0;i<3;i++) {
			for(int j=0;j<3;j++) {
				arr[i][j]=i+j;
			}
		}
		hg.dfs(arr);
		
	}

}
