package dsaProblems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import dsaProblems.arrayds.ArrayDS;
import dsaProblems.graphds.GraphHelper;

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
		
		int[] a1= {0,1};
		int[] a2= {1,2};
		int[] a3= {3,4};
		int[] a4= {2,1};
		int[][] grid=new int[3][3];
		grid[0]=a1;
		grid[1]=a2;
		grid[2]=a3;
		//grid[3]=a4;
		hg.countComponents(5,grid);
				
		
	}

}
