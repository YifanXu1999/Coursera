package UndirectedGraph;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import java.util.Stack;

public class GraphProblems {
	
	
	/**
	 * Find the path from path i to j.
	 *
	 */
	private static class PathTo {
		
		/**
		 * For convenience, just do the depth-first search through the
		 * connected component that connects i, and trace back from the
		 * edgeTo[j]
		 * 
		 * Time O(2V+E): O(V+E) for DepthFirst Search, O(V) for trace back to i
		 * Space O(V): O(V) from DFS and O(V) from the stack
		 * @param g
		 * @param i
		 * @param j
		 * @return
		 */
		public static  Stack<Integer> getPathTo (Graph g, int i, int j) {
			DepthFirst dfs = new DepthFirst(g, i);
			// Get the edgeTo
			int [] edgeTo = dfs.getEdgeTo();
			// Init the stack
			Stack<Integer> stk = new Stack<>();
			// While edgeTo[j] != -1
			while(edgeTo[j] != -1) {
				// Add edgeTo[j] to stack, and update j
				stk.add(edgeTo[j]);
				j = edgeTo[j];
			}
			return stk;
		}
	}
	
	/**
	 * Cycle Detection
	 */
	private static class CycleDetection {
		
		/**
		 * Detect if the component started with v has a cycle
		 * General idea: do dfs, return true if the any of non-parent neighbors of the current vertex
		 * have been marked
		 * 
		 * Similar time and space complexity like depth first search.
		 * 
		 * Note: we can also do it with breadth first
		 * @param g
		 * @param v
		 * @return
		 */
		public static boolean detectCycle(Graph g, int v) {
			// Init the stack, and put the first vertex into it.
			Stack<Integer> stk = new Stack<>();
			boolean [] marked = new boolean[g.V()];
			int [] parent = new int [g.V()];
			for(int i = 0; i < g.V(); i++) {
				parent[i] = -1;
			}
			stk.add(v);
			// While the stack is not empty
			while (stk.size() > 0) {
				// Pop the vertex
				int curr = stk.pop();
				// Mark it to be true
				marked[curr] = true;
				// Put the unvisited neighbors into the stack
				for(int nei : g.adj(curr)) {
					if(!marked[nei]) {
						stk.add(nei);
						parent[nei] = curr;
					} else {
						if(nei != parent[curr]) {
							return true;
						}
					}
				}
			}
			return false;
		}
	}
	
	/**
	 * Bipartite (each edge is connected by a "black" and "white" vertex)
	 */
	private static class Bipartite {
		
		/**
		 * Check if the cc that v is in is a bipartite.
		 * Bipartite is well known for being used in Dating app. (male vertex and female vertex)
		 * Use DFS
		 * 
		 * @param g
		 * @param v
		 * @return
		 */
		public static boolean checkBipartite(Graph g, int v) {
			// Need to have a array to store the symbolic color of the vertex
			boolean color [] = new boolean [g.V()];
			boolean marked [] = new boolean [g.V()];
			// Init the stack, and put the first vertex into it.
			Stack<Integer> stk = new Stack<>();
			stk.add(v);
			// While the stack is not empty
			while (stk.size() > 0) {
				// Pop the vertex
				int curr = stk.pop();
				// Mark it to be true
				marked[curr] = true;
				// Put the unvisited neighbors into the stack
				for(int nei : g.adj(curr)) {
					if(!marked[nei]) {
						stk.add(nei);
						// Color the neighbor in an opposite color
						color[nei] = !color[curr];
					} else {
						// The neighbor has the same color, return false.
						if(color[nei] == color[curr]) {
							return false;
						}
					}
				}
			}
			return true;
		}
	}
	
	/**
	 * Euler Cycle (the cc that each edge can be traversed once, and the last edge end with the start vertex
	 */
	private static class EulerCycle {
		
		/**
		 * This method prints the paths of an Euler cycle with the assumption that
		 * the cc that has v is a Euler cycle.
		 * @param g
		 * @param v
		 * @return
		 */
		private static boolean printEulerCyclePaths(Graph g, int v) {
			//  DFS stack
			Stack<Integer> stk = new Stack<>(); 
			// curr pointer
			int curr = v;
			stk.add(v);
			// For the linked list 
			// While the stk is not empty or g.adj(curr) has unvisited neighbors
			while(! stk.isEmpty() || ! g.adj(curr).isEmpty()) {
				// Print out the vertex if v doesnt have unvisited neighbors
				if(g.adj(curr).size() == 0)	{
					System.out.print(curr + "--");
					curr = stk.pop();
				} else {
					int nei = g.adj(curr).iterator().next();
					g.adj(curr).remove(nei);
					g.adj(nei).remove(curr);
					stk.add(curr);
					curr = nei;
					
				}
	
				
			}
					
			return false;
		}
		
	}
	public static void main(String [] args) throws FileNotFoundException, IOException {
		Graph g = new Graph(new FileInputStream("graph.txt"));
		BreadthFirst bfs = new BreadthFirst(g, 0);
		CC cc = new CC(g);
		for(int i = 0; i < g.V(); i++) {
			//System.out.println(cc.getIds()[i]);
		}
		for(int i = 0; i < g.V(); i++) {
			//System.out.println(bfs.getDist()[i]);
		}
		System.out.println(PathTo.getPathTo(g, 0, 6));
		System.out.println(CycleDetection.detectCycle(g, 10));
		System.out.println(Bipartite.checkBipartite(g, 10));
		Graph g2 = new Graph(new FileInputStream("graph3"));
		EulerCycle.printEulerCyclePaths(g2, 0);
	}
}
