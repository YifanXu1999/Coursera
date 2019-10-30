package UndirectedGraph;

public class CC {
	
	private boolean [] marked;
	private int [] id;
	private int count;
	
	/**
	 * Time O(V+E), each vertex and edge is visited at most once
	 * Space O(V)
	 * @param G
	 */
	public CC(Graph G) {
		marked = new boolean[G.V()];
		id = new int[G.V()];
		count = 0;
		for(int i = 0; i < G.V(); i++) {
			id[i] = 0;
		}
		// Loop thru all vertices, do DFS, if vertex is not marked
		for(int i = 0; i < G.V(); i++) {
			if(marked[i] == false) {
				DepthFirst dfs = new DepthFirst(G, i);
				boolean[] marker = dfs.getMarker();
				for(int j = 0; j < G.V(); j++) {
					if(marker[j]) {
						marked[j] = true;
						id[j] = count;
					}
				}
				count++;
			}
		}
	}
	
	public int getCount() {
		return count;
	}
	
	public  int[] getIds() {
		return id;
	}
	
}
