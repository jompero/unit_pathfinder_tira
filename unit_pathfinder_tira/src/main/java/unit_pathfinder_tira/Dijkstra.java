package unit_pathfinder_tira;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * @author danijompero
 *
 */
public class Dijkstra {
	// Benchmarking data
	double totalWeight = 0;
	int nodesInPath = 0;
	int visitedNodes = 0;

	/**
	 * Dijkstra's shortest path search algorithm.
	 * 
	 * @param g 		Graph from which path is searched from
	 * @param start 	The starting point
	 * @param end 	The ending point
	 * @return		Shortest path between start and end
	 */
	public ArrayList<int[]> search(Graph g, int[] start, int[] end) {
		int height = g.matrix.length;
		int width = g.matrix[0].length;
		if (height == 0 || width == 0)
			return null;

		// Return end if same as start
		if (Arrays.equals(start, end)) {
			ArrayList<int[]> result = new ArrayList<>();
			result.add(end);
			return result;
		}

		// Create visited matrix where the value indicates true weight from start
		boolean[][] visited = new boolean[height][width];
		visitedNodes = 0;

		// The actual search algorithm where nodes are evaluated based on the distance
		// from start
		PriorityQueue<Node> queue = new PriorityQueue<>();
		queue.add(new Node(start, 0, null));
		while (!queue.isEmpty()) {
			// Poll next node from queue and check if we reached the end
			Node n = queue.poll();
			if (Arrays.equals(n.xy, end)) {
				ArrayList<int[]> path = n.path();
				nodesInPath = path.size();
				totalWeight = n.weight;
				logBenchmark();
				return path;
			}
			// Also if visited
			if (visited[n.xy[0]][n.xy[1]]) {
				continue;
			}
			// Mark visited
			visited[n.xy[0]][n.xy[1]] = true;
			visitedNodes++;

			// Add polled node's neighbors to queue
			for (int[] neighbor : g.neighbors(n.xy)) {
				// We already know that the neighbors are a unit away so we can use the Euclidean distance as weight
				double weight = Graph.distance(n.xy, neighbor);
				queue.add(new Node(neighbor, n.weight + weight, n));
			}
		}

		return null;
	}

	private void logBenchmark() {
		System.out.println(String.format(
				"Dijkstra's shortest path found a path for %s. \n" + "Nodes visited: %s \n" + "Nodes in path: %s",
				totalWeight, visitedNodes, nodesInPath));
	}
}
