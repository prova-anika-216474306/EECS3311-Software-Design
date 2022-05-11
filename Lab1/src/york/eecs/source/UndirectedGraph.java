package york.eecs.source;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class UndirectedGraph<T extends Comparable<T>> extends Graph<T> {
	
	/**
	 * we use map to maintain a graph, the key in the map is a vertex; 
	 * The value of the key is a set of edge between this vertex and other vertices 
	 */
	private Map<T, Set<T>> graph; 
	
	/**
	 *  This is the constructor.
	 *  Please do not change it.
	 */
	public UndirectedGraph() {
		this.graph = new HashMap<>();
	}
	
	/**
	 * @return true if graph is nonempty, false otherwise.
	 */
	public boolean isEmpty() {
		// TODO: Complete this method
		// Hint: An empty graph contains zero vertices
		if(this.graph.isEmpty()) {
			return false;
		}
		return true; // this line needs to be rewritten 
	}

	/**
	 * @return the size (i.e. number of vertices) of this graph
	 */
	public int getSize() {
		// TODO: compute the size
		return this.graph.size(); // this line needs to be rewritten
	}
	
	/**
	 * Add a new vertex to the graph. A new vertex points
	 * to an empty set of adjacent vertices.
	 * 
	 * @param vertex: an object that is a new vertex in the graph
	 */
	public void addVertex(T vertex) throws VertexExistsException {
		// TODO: Complete this method  
		// Hints: If the vertex already exists, throw and exception
		//        Else, add a new pair to the graph hashmap:
		//        the vertex is the key, the value is an empty
		//        set of vertices.
		if(this.graph.containsKey(vertex)) {
		throw new VertexExistsException("vertex already exists!");
		}
		this.graph.put(vertex, new HashSet<T>());
	}
	
	public List<T> getAdjacent(T vertex) {
		return new ArrayList<>(graph.get(vertex));
	}
	
	
	@Override
	public List<T> getVertices() {
		return graph.keySet().stream()
                .collect(Collectors.toList());
	}

	/**
	 * @param fromVertex one of vertices of this edge
	 * @param toVertex the other vertex of this egde
	 */
	public void addEdge(T fromVertex, T toVertex) {
		// TODO: Complete this method
		// Hint: Recall, both vertices already exist. Also,
		//       our graphs are not oriented, hence both edges
		//       need to be added.
	this.graph.get(toVertex).add(fromVertex);
	this.graph.get(fromVertex).add(toVertex);
	
	}

	@Override
	public String toString() {
		// TODO: Override toString() method
		String s = "";
		s += "Graph:\n";
		
		for (T key : graph.keySet()) {
			s += "Vertex: " + key + " & Adjacent Vertices: " + graph.get(key) + "\n";
		}
        return s; // this line needs to change
	}


}
