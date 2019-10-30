package DirectedGraph;

import java.util.Stack;

public class DepthFirst {
	
	private boolean [] marked;
	private int [] edgeTo; // Record the parent vertex of vertex i
	private int s; // The start vertex
	
	public DepthFirst(Graph G, int v) {
		// Initialize fields
		marked = new boolean[G.V()];
		edgeTo = new int [G.V()];
		for(int i = 0; i < G.V(); i++) {
			edgeTo[i] = - 1;
		}
		s = v;
		DFS2(G, v);
		
	}
	
	/**
	 * Recursive way of DFS
	 * 
	 * Time O(V + E): we only visited each vertex once and each edge at most once.
	 * Space O(V): There will be V recursive calls
	 * @param G
	 * @param v
	 */
	private void DFS1 (Graph G, int v) {
		// marked[v] become true;
		marked[v] = true;
		// Loop through the neighbors of v, and recurse through the unmarked neighbors
		for(int nei : G.adj(v)) {
			if(!marked[nei]) {
				DFS1(G, nei);
				edgeTo[nei] = v;
			}
		}
 	}
	
	/**
	 * Non-recursive way of DFS by using stack
	 * 
	 * Time O(V+E): same reason
	 * Space O(V): the stack consumes a space of O(V)
	 */
	private void DFS2 (Graph G,int v) {
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
			for(int nei : G.adj(curr)) {
				if(!marked[nei]) {
					stk.add(nei);
					edgeTo[nei] = curr;
				}
			}
		}
	}
	
	public boolean [] getMarker() {
		return marked;
	}
	
	public int [] getEdgeTo() {
		return edgeTo;
	}
}
