package dsaProblems.graphds;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class GraphHelper {

	public void bfs(LinkedList<Integer> adj[], int s, int V) {

		boolean[] visited = new boolean[V];
		Queue<Integer> q = new LinkedList<>();
		q.add(s);
		while (!q.isEmpty()) {

			int row = q.poll();
			System.out.println(row + " ");
			for (int n : adj[row]) {
				if (!visited[n]) {
					visited[n] = true;
					q.add(n);
				}
			}
		}

	}
	
	public void dfs(int[][] graph) {
		int n=graph.length;
		for(int i=0;i<n;i++) {
			for(int j=0;j<n;j++) {
				if(graph[i][j]!=-1) {
					traverseDfs(graph,i,j,n);
				}
			}
		}
		
	}
	
	public void traverseDfs(int[][] graph,int i,int j,int n) {
		if(i<0 || i>n-1 || j>n-1 || j<0 || graph[i][j]==-1) {
			return;
		}
		int[] rows= {-1,1,0,0};
		int[] cols= {0,0,-1,1};
		System.out.println(graph[i][j]);
		graph[i][j]=-1;
		for(int index=0;index<4;index++) {
			int row=i+rows[index];
			int col=j+cols[index];
			traverseDfs(graph,row,col,n);
		}
	}
	
	public boolean detectCycle() {
		return false;
	}
	
	   public int[] findOrder(int numCourses, int[][] prerequisites) {
	       if (numCourses == 0) return null;
	    // Convert graph presentation from edges to indegree of adjacent list.
	    int indegree[] = new int[numCourses], order[] = new int[numCourses], index = 0;
	    for (int i = 0; i < prerequisites.length; i++) // Indegree - how many prerequisites are needed.
	        indegree[prerequisites[i][0]]++;    

	    Queue<Integer> queue = new LinkedList<Integer>();
	    for (int i = 0; i < numCourses; i++) 
	        if (indegree[i] == 0) {
	            // Add the course to the order because it has no prerequisites.
	            order[index++] = i;
	            queue.offer(i);
	        }

	    // How many courses don't need prerequisites. 
	    while (!queue.isEmpty()) {
	        int prerequisite = queue.poll(); // Already finished this prerequisite course.
	        for (int i = 0; i < prerequisites.length; i++)  {
	            if (prerequisites[i][1] == prerequisite) {
	                indegree[prerequisites[i][0]]--; 
	                if (indegree[prerequisites[i][0]] == 0) {
	                    // If indegree is zero, then add the course to the order.
	                    order[index++] = prerequisites[i][0];
	                    queue.offer(prerequisites[i][0]);
	                }
	            } 
	        }
	    }

	    return (index == numCourses) ? order : new int[0];
	    }

}
