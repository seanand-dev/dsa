package dsaProblems.graphds;

import java.util.ArrayList;
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
	   
	   static class Node {
		    // Stores the max frequency of color for this node.
			// There may be multiple paths leading to this node, for each color,
			// only the one with max count is needed here.
			public int[] colors = new int[26];
			public int colorIndex;

			Node (char c) {
				colorIndex = c - 'a';
			}

			public void start() {
				colors[colorIndex]++;
			}

			// This is the key. When visiting a node, no matter which path leads to this node,
			// we only need to store the max color frequency.
			// Of course, you need to add 1 for this node's color.
			public void visit(int[] pre) {
				for (int i = 0; i < pre.length; ++i) {
					colors[i] = Math.max(pre[i] + (i == colorIndex ? 1 : 0), colors[i]);
				}
			}

			public int maxColor() {
				int max = 0;
				for (int c : colors) {
					max = Math.max(max, c);
				}
				return max;
			}
		}

		public int largestPathValue(String colors, int[][] edges) {
			char[] colorMap = colors.toCharArray();
			int nodeSize = colorMap.length;

			int[] indegrees = new int[nodeSize];
			List<Integer>[] outdegrees = new ArrayList[nodeSize];
			for (int i = 0; i < nodeSize; ++i) {
				outdegrees[i] = new ArrayList<>();
			}
			for (int[] edge : edges) {
				int from = edge[0];
				int to = edge[1];
				indegrees[to]++;
				outdegrees[from].add(to);
			}

			Node[] nodes = new Node[nodeSize];
			for (int i = 0; i < nodeSize; ++i) {
				nodes[i] = new Node(colorMap[i]);
			}
			int count = 0;
			Queue<Integer> visiting = new LinkedList<>();
			for (int i = 0; i < nodeSize; ++i) {
				if (indegrees[i] == 0) {
					visiting.offer(i);
					++count;
					nodes[i].start();
				}
			}

			int ans = 0;
			while (!visiting.isEmpty()) {
				int label = visiting.poll();
				if (outdegrees[label].isEmpty()) {
					ans = Math.max(ans, nodes[label].maxColor());
				}
				for (int next : outdegrees[label]) {
					nodes[next].visit(nodes[label].colors);
					if (--indegrees[next] == 0) {
						visiting.offer(next);
						++count;
					}
				}
			}

			return count == nodeSize ? ans : -1;
		} 
		
		   public boolean possibleBipartition(int n, int[][] dislikes) {
		        List<List<Integer>> adj=new ArrayList<>();
		        int[] visited=new int[n+1];
		        for(int i=0;i<=n;i++){
		            adj.add(i,new ArrayList<>());
		        }
		          for(int i=0;i<dislikes.length;i++){
		            adj.get(dislikes[i][1]).add(dislikes[i][0]);
		            adj.get(dislikes[i][0]).add(dislikes[i][1]);
		        }
		        
		        
		        
		        for(int i=1;i<=n;++i){
		            if(visited[i]==0 && !dfs(adj,i,visited,1)){
		                return false;
		            }
		        }
		        return true;
		    }
		    
		    boolean dfs(List<List<Integer>> adj,int i,int[] visited,int color){
		        visited[i]=color;
		        for(int index:adj.get(i)){
		            if(visited[index]==0 && !dfs(adj,index,visited,-color)){
		                return false;
		            }
		            else if(visited[index]==color){
		                return false;
		            }
		        }
		        return true;
		    }
		    
	    

}
