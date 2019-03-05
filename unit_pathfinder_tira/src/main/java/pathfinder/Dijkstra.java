package pathfinder;

import java.util.Arrays;

import graph.Graph;
import graph.Node;
import mycollections.MyArrayList;
import mycollections.MyPriorityQueue;

/**
 * Dijkstra is a greedy shortest path algorithm that expands to all frontiers in order shortest path so far. 
 * Essentially it is a breadth first that sorts visited Nodes with a PriorityQueue.
 * @author danijompero
 *
 */
public class Dijkstra extends Pathfinder {
	boolean[][] visited;
	
	/**
	 * Dijkstra's shortest path search algorithm.
	 * 
	 * @param g 		Graph from which path is searched from
	 * @param start 	The starting point
	 * @param end 	The ending point
	 * @return		Shortest path between start and end
	 */
	public MyArrayList<int[]> search(Graph g, int[] start, int[] end) {
		// Log start time
		time = System.currentTimeMillis();
		
		// Ensure there is a matrix
		if (matrixCheck(g)) return null;
		
		// Return null if one of the coordinates is wall
		if (weightCheck(g, start, end)) {
			resetBenchmark();
			return null;
		}
		
		// Return end if same as start
		path = duplicateCheck(start, end);
		if (path != null) return path;

		// Create visited matrix where the value indicates true weight from start
		boolean[][] visited = new boolean[g.getHeight()][g.getWidth()];
		visitedList = new MyArrayList<>();

		// The actual search algorithm where nodes are evaluated based on the distance
		// from start
		MyPriorityQueue<Node> queue = new MyPriorityQueue<>();
		queue.add(new Node(start, 0, null));
		while (!queue.isEmpty()) {
			// Poll next node from queue and check if we reached the end
			Node n = queue.poll();
			
			int[] xy = n.getXY();
			if (Arrays.equals(xy, end)) {
				path = n.path();
				nodesInPath = path.size();
				totalWeight = n.getWeight();
				time = System.currentTimeMillis() - time;
				return path;
			}
			// Also if visited
			if (visited[xy[0]][xy[1]]) {
				continue;
			}
			// Mark visited
			visited[xy[0]][xy[1]] = true;
			visitedList.add(xy);

			// Add polled node's neighbors to queue
			for (int[] neighbor : g.neighbors(xy)) {
				if (visited[neighbor[0]][neighbor[1]]) {
					continue;
				}
				// We already know that the neighbors are a unit away so we can use the Euclidean distance as weight
				double weight = Graph.distance(xy, neighbor);
				queue.add(new Node(neighbor, n.getWeight() + weight, n));
			}
		}

		totalWeight = 0;
		nodesInPath = 0;
		return null;
	}
}
