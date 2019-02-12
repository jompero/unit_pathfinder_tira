package pathfinder;

import java.util.ArrayList;
import java.util.Arrays;

import graph.Graph;
import graph.Node;
import mycollections.MyPriorityQueue;

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
	public ArrayList<int[]> search(Graph g, int[] start, int[] end) {
		// Log start time
		time = System.currentTimeMillis();
		
		// Ensure there is a matrix
		int height = g.getHeight();
		int width = g.getWidth();
		if (height == 0 || width == 0)
			return null;

		// Return end if same as start
		if (Arrays.equals(start, end)) {
			ArrayList<int[]> result = new ArrayList<>();
			result.add(end);
			nodesInPath = 1;
			time = System.currentTimeMillis() - time;
			return result;
		}

		// Create visited matrix where the value indicates true weight from start
		boolean[][] visited = new boolean[height][width];
		visitedList = new ArrayList<>();

		// The actual search algorithm where nodes are evaluated based on the distance
		// from start
		MyPriorityQueue<Node> queue = new MyPriorityQueue<>();
		queue.add(new Node(start, 0, null));
		while (!queue.isEmpty()) {
			// Poll next node from queue and check if we reached the end
			Node n = queue.poll();
			
			int[] xy = n.getXY();
			if (Arrays.equals(xy, end)) {
				ArrayList<int[]> path = n.path();
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
				// We already know that the neighbors are a unit away so we can use the Euclidean distance as weight
				double weight = Graph.distance(xy, neighbor);
				queue.add(new Node(neighbor, n.getWeight() + weight, n));
			}
		}

		return null;
	}
}
