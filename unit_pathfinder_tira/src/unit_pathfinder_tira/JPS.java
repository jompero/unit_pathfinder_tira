package unit_pathfinder_tira;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

public class JPS {
	// Benchmarking data
	int totalWeight = 0;
	int nodesInPath = 0;
	int visitedNodes = 0;

	/**
	 * Jump-point search (JPS) shortest path search algorithm.
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

					int hWeight = newWeight;
					queue.add(new Node(neighbor, hWeight, n));
				}

			}
		}

		return null;
	}
	
	/**
	 * Method that returns the interesting Nodes that the Jump method found.
	 * @param g Graph
	 * @param start Starting Node
	 * @param end Ending coordinate
	 * @return
	 */
	private ArrayList<Node> successors(Graph g, Node start, int[] end) {
		ArrayList<Node> successors = new ArrayList<>();
		ArrayList<int[]> neighbors = prune(g.neighbors(start.xy));
		for (int[] n : neighbors) {
			Node s = jump(start, n, g);
			if (s != null) {
				successors.add(s);
			}
		}
		return successors;
	}
	
//	Require: x: initial node, ~d: direction, s: start, g: goal
//	1: n ← step(x, ~d)
//	2: if n is an obstacle or is outside the grid then
//	3: return null
//	4: if n = g then
//	5: return n
//	6: if ∃ n
//	0 ∈ neighbours(n) s.t. n
//	0
//	is forced then
//	7: return n
//	8: if ~d is diagonal then
//	9: for all i ∈ {1, 2} do
//	10: if jump(n, ~di
//	, s, g) is not null then
//	11: return n
//	12: return jump(n, ~d, s, g)

	/**
	 * Method will go though Nodes in a direction and returns first successor Node.
	 * A node is interesting if end can be reached from it or it reaches behind an obstacle.
	 * @param start Starting Node
	 * @param towards Coordinate towards which the method will move.
	 * @param g Graph
	 * @return Successor Node 
	 */
	private Node jump(Node start, int[] towards, Graph g) {
		int[] dir = {towards[0]-start.xy[0], towards[1]-start.xy[1]};
		
	return null;
}

	private ArrayList<int[]> prune(ArrayList<int[]> neighbors) {
	// TODO Auto-generated method stub
	return null;
}

	private void logBenchmark() {
		System.out.println(
				String.format("A* shortest path found a path for %s. \n" + "Nodes visited: %s \n" + "Nodes in path: %s",
						totalWeight, visitedNodes, nodesInPath));
	}
}
