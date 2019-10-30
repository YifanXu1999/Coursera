package DirectedGraph;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * Directed Graph, assuming the format of the input file is correct.
 * No need of exception handling
 * @author Yifan Xu
 *
 */
public class Graph {
	private int numOfVertices;
	private int numOfEdges;
	private List<HashSet<Integer>>  adjLists;
	
	/**
	 *  Create an empty graph with V vertices;
	 * @param V number of vertices
	 */
	public Graph(int V) {
		numOfVertices = V;
		initAdjLists();
	}
	
	/**
	 * Create a graph from input stream
	 * @param in input stream of the target file
	 * @throws IOException 
	 */
	public Graph (InputStream in) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(in));
		// Read the first line (numOfVertices)numOfVertices = Integer.parseInt(reader.readLine());
		numOfVertices = Integer.parseInt(reader.readLine());
		initAdjLists();
		while(reader.ready()) {
			// The String representation of edges is like "a-b"
			String [] edge = reader.readLine().split("-");
			// Update the adjacency lists
			addEdge(Integer.parseInt(edge[1]), Integer.parseInt(edge[0]));
			numOfEdges++;
		}
	}
	
	/**
	 * Insert edge to the graph
	 * @param v
	 * @param w
	 */
	public void addEdge(int v, int w) {
		adjLists.get(v).add(w);
	}
	
	/**
	 * Return the adjacency list of the integer
	 * @param v
	 * @return the adj list of v
	 */
	public Iterable<Integer> adj(int v) {
		return adjLists.get(v);
	}
	
	/**
	 * 
	 * @return the number of vertices
	 */
	public int V() {
		return numOfVertices;
	}
	
	/**
	 * 
	 * @return the number of edges
	 */
	public int E() {
		return numOfEdges;
	}
	
	private void initAdjLists() {
		adjLists = new ArrayList<HashSet<Integer>>();
		for(int i = 0; i < numOfVertices; i++) {
			adjLists.add(new HashSet<Integer>());
		}
	}
	/**
	 * @return the String representation of the adjacency list
	 */
	public String toString() {
		String ret = "";
		for(int i = 0; i < numOfVertices; i++) {
			ret += i + ": ";
			for(int w: adj(i)) {
				ret += w + "; ";
			}
			ret += "\n";
		}
		return ret;
	}
	
	public static void main(String [] args) throws FileNotFoundException, IOException {
		Graph g = new Graph(new FileInputStream("graph.txt"));
		System.out.print(g.toString());
	}
	
}
