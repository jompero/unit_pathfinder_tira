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
	 * Jump-point search (JPS) shortest path search algorithm. Not fully implemented yet.
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
		
		while (!queue.isEmpty()) {
			// Poll next node from queue and check if we've reached the end
			// else continue search
			Node n = queue.poll();
			visited[start[0]][start[1]] = 1;
			
			// TODO: Ensure benchmarking data is calculated correctly
			if (Arrays.equals(n.xy, end)) {
				ArrayList<int[]> path = n.path();
				nodesInPath = path.size();
				totalWeight = visited[end[0]][end[1]] - 1;
				logBenchmark();
				return path;
			}

			// Check neighbors of polled node and add all successors to the queue
			queue.addAll(successors(g, n, end));
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
		ArrayList<int[]> neighbors = prune(g, start);
		for (int[] n : neighbors) {
			Node s = jump(start, n, end, g);
			if (s != null) {
				successors.add(s);
			}
		}
		return successors;
	}
	
	/**
	 * Method will jump Nodes in a direction and returns first successor Node.
	 * A node is a successor if end can be reached from it or it has a forced neighbor.
	 * @param start Starting Node
	 * @param towards Coordinate towards which the method will move.
	 * @param g Graph
	 * @return Successor Node 
	 */
	private Node jump(Node start, int[] towards, int[] end, Graph g) {
		// Determine dir based on 'start -> towards' 
		// TODO I think this returns an incorrect array. Also maybe I could delegate this to Graph instead?
		int[] dir = {towards[0]-start.xy[0], towards[1]-start.xy[1]};
		
		// If wall, return null
		if (g.getWeight(start.xy) == 0) return null;
		
		// If goal, return new Node from 'towards'
		if (Arrays.equals(start.xy, end)) return new Node(towards, Graph.distance(start.xy, towards), start);
		
		// If forced node is found in neighbor, return new Node from 'towards'
		// Diagonal check
		if (dir[0] != 0 && dir[1] != 0) {
            if ((g.getWeight(towards[0] - dir[0], towards[1] + dir[1]) != 0 && g.getWeight(towards[0] - dir[0], towards[1]) == 0) ||
        			(g.getWeight(towards[0] + dir[0], towards[1] - dir[1]) != 0 && g.getWeight(towards[0], towards[1] - dir[1]) == 0)) {
            	return new Node(towards, Graph.distance(start.xy, towards), start);
            }
        // Horizontal check
		} else {
            if (dir[0] != 0) {
                if ((g.getWeight(towards[0] + dir[0], towards[1] + 1) != 0 && g.getWeight(towards[0], towards[1] + 1) == 0) ||
                		(g.getWeight(towards[0] + dir[0], towards[1] - 1) != 0 && g.getWeight(towards[0], towards[1]) == 0)) {
                		return new Node(towards, Graph.distance(start.xy, towards), start);
                }
            } else {
                if ((g.getWeight(towards[0] + 1, towards[1] + dir[1]) != 0 && g.getWeight(towards[0] + 1, towards[1]) == 0) ||
                		(g.getWeight(towards[0] - 1, towards[1] + dir[1]) != 0 && g.getWeight(towards[0] - 1, towards[1]) == 0)) {
                		return new Node(towards, Graph.distance(start.xy, towards), start);
                }
            }
		}
		return null;
	}

	/**
	 * Returns the 
	 * @param neighbors
	 * @return
	 */
	private ArrayList<int[]> prune(Graph g, Node start) {
		ArrayList<int[]> neighbors = new ArrayList<>();
		Node parent = start.parent;
		if (parent != null) {
			int x = start.xy[0];
			int y = start.xy[1];
			
			// Get normalized direction from parent
			int dir[] = Graph.getNormalizedDir(parent.xy, start.xy);
			int dirX = dir[0];
			int dirY = dir[1];
			
			// If direction is diagonal
			if (dirX > 0 && dirY > 0) {
                if (g.getWeight(x, y + dirY) > 0) neighbors.add(Graph.coordinate(x, y + dirY));
                if (g.getWeight(x + dirX, y) > 0) neighbors.add(Graph.coordinate(x + dirX, y));
                if (g.getWeight(x + dirX, y + dirY) > 0) neighbors.add(Graph.coordinate(x + dirX, y + dirY));
                // If perpendicularly blocked, add forced nodes
                if (g.getWeight(x, y - dirY) == 0) neighbors.add(Graph.coordinate(x + dirX, y - dirY));
                if (g.getWeight(x + dirX, y) == 0) neighbors.add(Graph.coordinate(x = dirY, y + dirX));
            
            // If direction is horizontal
			} else if (dirX == 0) {
                if (g.getWeight(x, y + dirY) > 0) neighbors.add(Graph.coordinate(x, y + dirY));
                // If perpendicularly blocked, add forced nodes
                if (g.getWeight(x + 1, y) == 0) neighbors.add(Graph.coordinate(x + 1, y + dirY));
                if (g.getWeight(x - 1, y) == 0) neighbors.add(Graph.coordinate(x - 1, y + dirY));
			
            // If direction is vertical
			} else {
                if (g.getWeight(x + dirX, y) > 0) neighbors.add(Graph.coordinate(x + dirX, y));
                // If perpendicularly blocked, add forced nodes
                if (g.getWeight(x, y + 1) == 0) neighbors.add(Graph.coordinate(x + dirX, y + 1));
                if (g.getWeight(x - 1, y) == 0) neighbors.add(Graph.coordinate(x + dirX, y - 1));
			}
			
		} else {
			return g.neighbors(start.xy);
		}
		return null;
	}

	private void logBenchmark() {
		System.out.println(
				String.format("A* shortest path found a path for %s. \n" + "Nodes visited: %s \n" + "Nodes in path: %s",
						totalWeight, visitedNodes, nodesInPath));
	}
}
