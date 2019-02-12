package pathfinder;

import java.util.ArrayList;
import java.util.Arrays;

import graph.Graph;
import graph.Node;
import mycollections.MyPriorityQueue;

public class JPS extends Pathfinder {
	double[][] visited;
	
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

		// Reset visitedNodes for benchmarking and create visited matrix where the value indicates true weight from start
		visited = new double[height][width];
		visitedList = new ArrayList<>();

		// The actual search algorithm where nodes are evaluated based on the estimated
		// distance to end
		MyPriorityQueue<Node> queue = new MyPriorityQueue<>();
		queue.add(new Node(start, Graph.distance(start, end), null));
		
		while (!queue.isEmpty()) {
			// Poll next node from queue and check if we've reached the end
			// else continue search
			Node n = queue.poll();
			int[] xy = n.getXY();
			
			if (Arrays.equals(xy, end)) {
				ArrayList<int[]> path = n.path();
				nodesInPath = path.size();
				totalWeight = n.getWeight();
				time = System.currentTimeMillis() - time;
				return path;
			}

			// Check neighbors of polled node and add all successors to the queue
			queue.addAll(successors(g, n, end));
		}

		return null;
	}
	
	/**
	 * Method that returns the interesting Nodes that the Jump method found.
	 * @param g 		Graph
	 * @param start 	Starting Node
	 * @param end 		Ending coordinate
	 * @return Successors (or "interesting" Nodes) to the starting node
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
	 * Returns the viable neighbors JPS can jump towards.
	 * @param neighbors
	 * @return
	 */
	private ArrayList<int[]> prune(Graph g, Node start) {
		ArrayList<int[]> neighbors = new ArrayList<>();
		Node parent = start.getParent();
		if (parent != null) {
			// If start node has a parent, we can get direction and thus ignore some of the surrounding nodes
			int[] parentXY = parent.getXY();
			int[] startXY = start.getXY();
			// For easier reading:
			int x = startXY[0];
			int y = startXY[1];
			
			// Get normalized direction parent -> start
			int dir[] = Graph.getNormalizedDir(parentXY, startXY);
			int dirX = dir[0];
			int dirY = dir[1];
			
			// If direction is diagonal
			if (dirX != 0 && dirY != 0) {
                if (g.getWeight(x, y + dirY) > 0) neighbors.add(Graph.coordinate(x, y + dirY));
                if (g.getWeight(x + dirX, y) > 0) neighbors.add(Graph.coordinate(x + dirX, y));
                if (g.getWeight(x + dirX, y + dirY) > 0) neighbors.add(Graph.coordinate(x + dirX, y + dirY));
                // If perpendicularly blocked, add forced nodes
                if (g.getWeight(x, y - dirY) == 0) neighbors.add(Graph.coordinate(x + dirX, y - dirY));
                if (g.getWeight(x - dirX, y) == 0) neighbors.add(Graph.coordinate(x - dirX, y + dirY));
            
            // If direction is vertical
			} else if (dirX == 0) {
                if (g.getWeight(x, y + dirY) > 0) neighbors.add(Graph.coordinate(x, y + dirY));
                // If perpendicularly blocked, add forced nodes
                if (g.getWeight(x + 1, y) == 0) neighbors.add(Graph.coordinate(x + 1, y + dirY));
                if (g.getWeight(x - 1, y) == 0) neighbors.add(Graph.coordinate(x - 1, y + dirY));
			
            // If direction is horizontal
			} else {
                if (g.getWeight(x + dirX, y) > 0) neighbors.add(Graph.coordinate(x + dirX, y));
                // If perpendicularly blocked, add forced nodes
                if (g.getWeight(x, y + 1) == 0) neighbors.add(Graph.coordinate(x + dirX, y + 1));
                if (g.getWeight(x, y - 1) == 0) neighbors.add(Graph.coordinate(x + dirX, y - 1));
			}
			
		} else {
			// If no parent, just return all neighbors
			return g.neighbors(start.getXY());
		}
		return neighbors;
	}
	
	/**
	 * Method will jump Nodes in a direction and returns first successor Node.
	 * A node is a successor if end can be reached from it or it has a forced neighbor.
	 * @param start 	Starting Node
	 * @param towards 	Coordinate towards which the method will move.
	 * @param g 		Graph
	 * @return Successor Node 
	 */
	private Node jump(Node start, int[] towards, int[] end, Graph g) {
		// Determine dir based on 'start -> towards' 
		int[] dir = Graph.getNormalizedDir(start.getXY(), towards);

		// If wall, return null
		if (g.getWeight(towards) == 0) return null;
		
		// If goal, return new Node of 'towards'
		if (Arrays.equals(towards, end)) return newStart(start, towards, end);
		
		// If forced node is found in neighbors, return new Node of 'towards'
		if (dir[0] != 0 && dir[1] != 0) {
			// Check diagonally
            if ((g.getWeight(towards[0] - dir[0], towards[1] + dir[1]) != 0 && g.getWeight(towards[0] - dir[0], towards[1]) == 0) ||
        			(g.getWeight(towards[0] + dir[0], towards[1] - dir[1]) != 0 && g.getWeight(towards[0], towards[1] - dir[1]) == 0)) {
            	return newStart(start, towards, end);
            }
            // Jump horizontally and vertically
            int[] newTowardsXYLeft = {towards[0] + dir[0], towards[1] };
            int[] newTowardsXYRight = {towards[0], towards[1] + dir[1] };
            Node nextStart = newStart(start, towards, end);
            if (jump(nextStart, newTowardsXYLeft, end, g) != null ||
            		jump(nextStart, newTowardsXYRight, end, g) != null) {
            	return nextStart;
            }
		} else {
            if (dir[0] != 0) {
            	// Check horizontally
                if ((g.getWeight(towards[0] + dir[0], towards[1] + 1) != 0 && g.getWeight(towards[0], towards[1] + 1) == 0) ||
                		(g.getWeight(towards[0] + dir[0], towards[1] - 1) != 0 && g.getWeight(towards[0], towards[1] - 1) == 0)) {
                	return newStart(start, towards, end);
                }
            } else {
            	// Check vertically
                if ((g.getWeight(towards[0] + 1, towards[1] + dir[1]) != 0 && g.getWeight(towards[0] + 1, towards[1]) == 0) ||
                		(g.getWeight(towards[0] - 1, towards[1] + dir[1]) != 0 && g.getWeight(towards[0] - 1, towards[1]) == 0)) {
                	return newStart(start, towards, end);
                }
            }
		}
		
		// If no forced Nodes found, move towards the goal
		int[] nextTowards = { towards[0] + dir[0], towards[1] + dir[1] };
		return jump(start, nextTowards, end, g);
	}
	
	Node newStart(Node start, int[] towards, int[] end) {
		int[] startXY = start.getXY();
		double startWeight = visited[startXY[0]][startXY[1]];
		double endWeight = startWeight + Graph.distance(startXY, towards);
		double heuristicWeight = endWeight + Graph.distance(towards, end);
		
		visited[towards[0]][towards[1]] = endWeight;
		visitedList.add(towards);
		Node nextStart = new Node(towards, heuristicWeight, start);
		return nextStart;
	}
}
