package UndirectedGraph;

import java.util.LinkedList;
import java.util.Queue;

public class BreadthFirst {

	private boolean [] marked;
	private int [] edgeTo;
	private int [] dist;
	private int currDepth;
	private int prevParent;
	public BreadthFirst(Graph G, int v) {
		marked = new boolean[G.V()];
		edgeTo = new int [G.V()];
		dist = new int [G.V()];
		for(int i = 0; i < G.V(); i++) {
			edgeTo[i] = - 1;
			dist[i] = - 1;
		}
		prevParent = 0;
		currDepth = 0;
		bfs(G, v);
	}
	
	/**
	 * Time O(V + E), each vertex and edge is visted at most once
	 * Space O (V), queue cant be longger than the number of vertices
	 * @param G
	 * @param v
	 */
	private void bfs(Graph G, int v) {
		// Init the queue and put v into it
		Queue<Integer> que = new LinkedList<>();
		que.add(v);
		// While the queue is not empty
		while(que.size() > 0) {
			// Poll the vertex
			int curr = que.poll();
			// Mark the vertex to be true
			marked[curr] = true;
			if(prevParent != edgeTo[curr]) currDepth++;
			// Add the unvisited vertices to be queue, and update their status
			for(int nei : G.adj(curr)) {
				if(!marked[nei]) {
					edgeTo[nei] = curr;
					dist[nei] = currDepth;
					que.add(nei);
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
	
	public int [] getDist() {
		return dist;
	}
	
}
