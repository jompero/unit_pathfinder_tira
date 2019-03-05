package pathfinder;

import java.util.Arrays;

import graph.Graph;
import graph.Node;
import mycollections.MyArrayList;
import mycollections.MyPriorityQueue;

/**
 * A* is a shortest path algorithm that extends Dijkstra's with heuristic values. 
 * It's aim is to beam towards the target with a heuristic function, however doing so 
 * it may occasionally trade optimal path for speed.
 * @author danijompero
 *
 */
public class AStar extends Pathfinder {
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
		double[][] visited = new double[g.getHeight()][g.getWidth()];
		visitedList = new MyArrayList<>();

		// The actual search algorithm where nodes are evaluated based on the estimated
		// distance to end
		MyPriorityQueue<Node> queue = new MyPriorityQueue<>();
		queue.add(new Node(start, 0, null));
		visited[start[0]][start[1]] = 1;
		while (!queue.isEmpty()) {
			// Poll next node from queue and check if we've reached the end
			// else continue search
			Node n = queue.poll();
			int[] xy = n.getXY();
			
			if (Arrays.equals(xy, end)) {
				path = n.path();
				nodesInPath = path.size();
				totalWeight = visited[end[0]][end[1]] - 1;
				time = System.currentTimeMillis() - time;
				return path;
			}

			// Check neighbors of polled node and calculate estimated distance to end
			// and add to queue
			for (int[] neighbor : g.neighbors(xy)) {
				if (visited[neighbor[0]][neighbor[1]] > 0) continue;
				
				double newWeight = visited[xy[0]][xy[1]] + Graph.distance(xy, neighbor);
				double oldWeight = visited[neighbor[0]][neighbor[1]];
				if (oldWeight == 0 || oldWeight > newWeight) {
					// Add new weight in the weight map
					visited[neighbor[0]][neighbor[1]] = newWeight;
					visitedList.add(xy);
					
					// Add Euclidean distance as heuristic value
					int hWeight = (int) (newWeight + Graph.distance(neighbor, end));
					queue.add(new Node(neighbor, hWeight, n));
				}

			}
		}
		
		totalWeight = 0;
		nodesInPath = 0;
		return null;
	}
}
