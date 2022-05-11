package york.eecs.source;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class UndirectedGraphAlgorithms<T extends Comparable<T>> implements GraphAlgorithms<T> {

	/**
	 * Please implement BFS algorithm as described on the handout
	 * 
	 * @param g:           a graph
	 * @param initial:     the starting vertex of the path
	 * @param destination: the destination vertex of the path
	 * @return the path from initial to destination in the form of an ArrayList of
	 *         vertices, with initial as the first element, and destination as the
	 *         last element of the ArrayList
	 */
	public List<T> findBFSpath(Graph<T> g, T initial, T destination) {

		// TODO : implement BFS path search

		Queue<T> open = new LinkedList<T>();
		List<String> visited = new ArrayList<String>();
		List<String> path = new ArrayList<String>();
		HashMap<T, T> parent = new HashMap<T, T>();
		List<String> path_result = new ArrayList<String>();

		// add current node to the queue first
		open.add(initial);

		while (!open.isEmpty()) {

			// current is the first element of the queue
			T current = open.poll();
			// add the first element to the queue
			visited.add((String) current);

			if (current == destination) {
				path.add((String) current);
				break;
			}

			for (T child : g.getAdjacent(current)) {
				if (!visited.contains(child)) {
					open.add(child);
					parent.put(child, current);

				}
			}	
		}
		
		T start = destination;

		for(int i =0; i < parent.keySet().size(); i++){
			
			if(start != initial && parent.get(start)!=null) {
				path.add((String) parent.get(start));
				start = parent.get(start);
			}
			//
				else {
					break;
				}
		

		}
		//in place reversal
		for (int i = 0, j = path.size() - 1; i < j; i++) {
			path.add(i, path.remove(j));
		}
		
		//if no path exists.
		
		return (List<T>) path;
		

		
	}

}
