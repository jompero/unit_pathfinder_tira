package pathfinder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

import graph.Graph;
import graph.Node;

public class AStar {
	// Benchmarking data
	double totalWeight = 0;
	int nodesInPath = 0;
	int visitedNodes = 0;

	/**
	 * A* shortest path search algorithm.
	 * 
	 * @param g
	 *            Graph from which path is searched from
	 * @param start
	 *            The starting point
	 * @param end
	 *            The ending point
	 * @return
	 */
	public ArrayList<int[]> search(Graph g, int[] start, int[] end) {
		// Ensure there is a matrix
		int height = g.getHeight();
		int width = g.getWidth();
		if (height == 0 || width == 0)
			return null;

		// Return end if same as start
		if (Arrays.equals(start, end)) {
			ArrayList<int[]> result = new ArrayList<>();
			result.add(end);
			return result;
		}

		// Create visited matrix where the value indicates true weight from start
		double[][] visited = new double[height][width];
		visitedNodes = 0;

		// The actual search algorithm where nodes are evaluated based on the estimated
		// distance to end
		PriorityQueue<Node> queue = new PriorityQueue<>();
		queue.add(new Node(start, 0, null));
		visited[start[0]][start[1]] = 1;
		while (!queue.isEmpty()) {
			// Poll next node from queue and check if we've reached the end
			// else continue search
			Node n = queue.poll();
			int[] xy = n.getXY();
			
			if (Arrays.equals(xy, end)) {
				ArrayList<int[]> path = n.path();
				nodesInPath = path.size();
				totalWeight = visited[end[0]][end[1]] - 1;
				logBenchmark();
				return path;
			}

			// Check neighbors of polled node and calculate estimated distance to end
			// and add to queue
			for (int[] neighbor : g.neighbors(xy)) {
				double newWeight = visited[xy[0]][xy[1]] + Graph.distance(xy, neighbor);
				double oldWeight = visited[neighbor[0]][neighbor[1]];
				if (oldWeight == 0 || oldWeight > newWeight) {
					// Add new weight in the weight map
					visited[neighbor[0]][neighbor[1]] = newWeight;
					visitedNodes++;
					
					// Add Euclidean distance as heuristic value
					int hWeight = (int) (newWeight + Graph.distance(neighbor, end));
					queue.add(new Node(neighbor, hWeight, n));
				}

			}
		}

		return null;
	}

	private void logBenchmark() {
		System.out.println(
				String.format("A* shortest path found a path for %s. \n" + "Nodes visited: %s \n" + "Nodes in path: %s",
						totalWeight, visitedNodes, nodesInPath));
	}
}
