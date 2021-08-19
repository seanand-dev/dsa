package dsaProblems.graphds;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;

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
		int n = graph.length;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (graph[i][j] != -1) {
					traverseDfs(graph, i, j, n);
				}
			}
		}

	}

	public void traverseDfs(int[][] graph, int i, int j, int n) {
		if (i < 0 || i > n - 1 || j > n - 1 || j < 0 || graph[i][j] == -1) {
			return;
		}
		int[] rows = { -1, 1, 0, 0 };
		int[] cols = { 0, 0, -1, 1 };
		System.out.println(graph[i][j]);
		graph[i][j] = -1;
		for (int index = 0; index < 4; index++) {
			int row = i + rows[index];
			int col = j + cols[index];
			traverseDfs(graph, row, col, n);
		}
	}

	public boolean containsCycle(char[][] grid) {
		int n = grid.length;
		boolean[][] visited = new boolean[n][grid[0].length];
		boolean hasCycle = false;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				if (!visited[i][j] && isCycle(grid, i, j, visited, grid[i][j])) {
					return true;
				}
			}
		}
		return false;
	}

	boolean isCycle(char[][] grid, int i, int j, boolean[][] visited, char target) {

		if (i < 0 || j < 0 || i >= grid.length || j >= grid[0].length || grid[i][j] != target) {
			return false;
		}
		if (visited[i][j]) {
			return true;
		}
		visited[i][j] = true;
		grid[i][j] = '#';
		int[] rows = { -1, 1, 0, 0 };
		int[] cols = { 0, 0, -1, 1 };
		boolean hasCycle = false;
		for (int index = 0; index < 4; index++) {
			int rowIndex = i + rows[index];
			int colIndex = j + cols[index];
			hasCycle = hasCycle || isCycle(grid, rowIndex, colIndex, visited, target);
		}
		grid[i][j] = target;
		return hasCycle;
	}

	public int[] findOrder(int numCourses, int[][] prerequisites) {
		if (numCourses == 0)
			return null;
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
			for (int i = 0; i < prerequisites.length; i++) {
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

		Node(char c) {
			colorIndex = c - 'a';
		}

		public void start() {
			colors[colorIndex]++;
		}

		// This is the key. When visiting a node, no matter which path leads to this
		// node,
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
		List<List<Integer>> adj = new ArrayList<>();
		int[] visited = new int[n + 1];
		for (int i = 0; i <= n; i++) {
			adj.add(i, new ArrayList<>());
		}
		for (int i = 0; i < dislikes.length; i++) {
			adj.get(dislikes[i][1]).add(dislikes[i][0]);
			adj.get(dislikes[i][0]).add(dislikes[i][1]);
		}

		for (int i = 1; i <= n; ++i) {
			if (visited[i] == 0 && !dfs(adj, i, visited, 1)) {
				return false;
			}
		}
		return true;
	}

	boolean dfs(List<List<Integer>> adj, int i, int[] visited, int color) {
		visited[i] = color;
		for (int index : adj.get(i)) {
			if (visited[index] == 0 && !dfs(adj, index, visited, -color)) {
				return false;
			} else if (visited[index] == color) {
				return false;
			}
		}
		return true;
	}

	public boolean isBipartite(int[][] graph) {
		int[] colors = new int[graph.length];
		for (int i = 0; i < graph.length; i++) {
			if (colors[i] == 0 && !isValid(graph, i, 1, colors)) {
				return false;
			}
		}
		return true;
	}

	boolean isValid(int[][] graph, int i, int color, int[] colors) {
		if (colors[i] != 0) {
			return colors[i] == color;
		}
		colors[i] = color;
		for (int n : graph[i]) {
			if (!isValid(graph, n, -color, colors)) {
				return false;

			}
		}
		return true;
	}

	public boolean canFinish(int numCourses, int[][] prerequisites) {

		int[] visited = new int[numCourses];
		List<List<Integer>> adj = new ArrayList<>();
		for (int i = 0; i < numCourses; i++)
			adj.add(new ArrayList<>());

		for (int i = 0; i < prerequisites.length; i++) {
			int secondCourse = prerequisites[i][1];
			int firstCourse = prerequisites[i][0];
			adj.get(secondCourse).add(firstCourse);
		}

		for (int i = 0; i < numCourses; ++i) {
			if (visited[i] == 0) {
				if (isCyclic(i, adj, visited))
					return false;
			}
		}
		return true;

	}

	boolean isCyclic(int i, List<List<Integer>> adj, int[] visited) {
		if (visited[i] == 2) {
			return true;
		}
		visited[i] = 2;
		for (int n : adj.get(i)) {
			if (visited[n] != 1 && isCyclic(n, adj, visited)) {
				return true;
			}
		}
		visited[i] = 1;
		return false;
	}

	public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
		List<List<Integer>> res = new ArrayList<>();
		List<Integer> path = new ArrayList<>();

		path.add(0);
		dfsSearch(graph, 0, res, path);

		return res;
	}

	private void dfsSearch(int[][] graph, int node, List<List<Integer>> res, List<Integer> path) {
		if (node == graph.length - 1) {
			res.add(new ArrayList<Integer>(path));
			return;
		}

		for (int nextNode : graph[node]) {
			path.add(nextNode);
			dfsSearch(graph, nextNode, res, path);
			path.remove(path.size() - 1);
		}
	}

	public List<String> findItinerary(List<List<String>> tickets) {
		Map<String, PriorityQueue<String>> mapEdges = new HashMap<>();

		for (List<String> ticket : tickets) {
			PriorityQueue<String> q = mapEdges.getOrDefault(ticket.get(0),
					new PriorityQueue<String>((s1, s2) -> s1.compareTo(s2)));
			q.offer(ticket.get(1));
			mapEdges.put(ticket.get(0), q);
		}

		Stack<String> s = new Stack<>();
		s.push("JFK"); // Starting point is JFK

		List<String> finalIternary = new ArrayList<>();
		while (!s.isEmpty()) {

			String source = s.peek();
			PriorityQueue<String> q = mapEdges.get(source);

			if (q == null || q.isEmpty()) {
				finalIternary.add(s.pop());
			} else {
				s.push(q.poll());
			}
		}

		Collections.reverse(finalIternary);
		return finalIternary;
	}

	int[][] dirs = new int[][] { { 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 } };

	public int getFood(char[][] grid) {

		int m = grid.length;
		int n = grid[0].length;

		Queue<int[]> q = new LinkedList<>();
		q.add(findStart(grid));

		boolean[][] visited = new boolean[m][n];

		int step = 0;
		while (!q.isEmpty()) {
			int len = q.size();
			for (int i = 0; i < len; i++) {
				int[] pos = q.poll();

				int x = pos[0];
				int y = pos[1];

				if (grid[x][y] == '#')
					return step;

				for (int[] dir : dirs) {
					int newX = x + dir[0];
					int newY = y + dir[1];

					if (isValid(grid, newX, newY) && !visited[newX][newY]) {
						visited[newX][newY] = true;
						q.offer(new int[] { newX, newY });
					}
				}
			}
			step++;
		}

		return -1;
	}

	private int[] findStart(char[][] grid) {
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[0].length; j++) {
				if (grid[i][j] == '*') {
					return new int[] { i, j };
				}
			}
		}
		throw new RuntimeException();
	}

	private boolean isValid(char[][] grid, int i, int j) {
		return i >= 0 && i < grid.length && j >= 0 && j < grid[0].length && grid[i][j] != 'X';
	}

	int size;
	int[] parent;

	int find(int i) {
		if (parent[i] == i) {
			return i;
		}
		return parent[i] = find(parent[i]);
	}

	void union(int x, int y) {
		int s1 = find(x);
		int s2 = find(y);
		if (s1 != s2) {
			parent[s1] = s2;
			size--;
		}
	}

	public int minimumCost(int n, int[][] connections) {
		parent = new int[n + 1];
		size = n;
		for (int i = 1; i <= n; i++) {
			parent[i] = i;
		}
		int res = 0;
		Arrays.sort(connections, (a, b) -> (a[2] - b[2]));
		for (int[] con : connections) {
			if (find(con[0]) != find(con[1])) {
				union(con[0], con[1]);
				res += con[2];
			}
		}

		return size == 1 ? res : -1;

	}

}
