package unit_pathfinder_tira;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

public class AStar {
	// Benchmarking data
	int totalWeight = 0;
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
		int[][] visited = new int[height][width];
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
			if (Arrays.equals(n.xy, end)) {
				ArrayList<int[]> path = n.path();
				nodesInPath = path.size();
				totalWeight = visited[end[0]][end[1]] - 1;
				logBenchmark();
				return path;
			}

			// Check neighbors of polled node and calculate estimated distance to end
			// and add to queue
			for (int[] neighbor : g.neighbors(n.xy)) {
				int newWeight = visited[n.xy[0]][n.xy[1]] + g.getWeight(neighbor);
				int oldWeight = visited[neighbor[0]][neighbor[1]];
				if (oldWeight == 0 || oldWeight > newWeight) {
					visited[neighbor[0]][neighbor[1]] = newWeight;
					visitedNodes++;

					int hWeight = newWeight + heuristic(neighbor, end);
					queue.add(new Node(neighbor, hWeight, n));
				}

			}
		}

		return null;
	}

	/**
	 * Heuristic for A* algorithm; Manhattan distance.
	 * 
	 * @param a
	 *            Coordinates of starting point
	 * @param b
	 *            Coordinates of ending point
	 * @return Manhattan distance between a and b.
	 */
	private int heuristic(int[] a, int[] b) {
		return (Math.abs(a[0] - b[0]) + Math.abs(a[1] - b[1])) * 10;
	}

	private void logBenchmark() {
		System.out.println(
				String.format("A* shortest path found a path for %s. \n" + "Nodes visited: %s \n" + "Nodes in path: %s",
						totalWeight, visitedNodes, nodesInPath));
	}
}
