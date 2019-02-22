package pathfinder;

import java.util.Arrays;

import graph.Graph;
import graph.Node;
import mycollections.MyArrayList;
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

		// Reset visitedNodes for benchmarking and create visited matrix where the value indicates true weight from start
		visited = new double[g.getHeight()][g.getWidth()];
		visitedList = new MyArrayList<>();

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
				path = n.path();
				nodesInPath = path.size();
				totalWeight = n.getWeight();
				time = System.currentTimeMillis() - time;
				return path;
			}

			// Check neighbors of polled node and add all successors to the queue
			queue.addAll(successors(g, n, end));
		}
		
		totalWeight = 0;
		nodesInPath = 0;
		return null;
	}
	
	/**
	 * Method that returns the interesting Nodes that the Jump method found.
	 * @param g 		Graph
	 * @param start 	Starting Node
	 * @param end 		Ending coordinate
	 * @return Successors (or "interesting" Nodes) to the starting node
	 */
	private MyArrayList<Node> successors(Graph g, Node start, int[] end) {
		MyArrayList<Node> successors = new MyArrayList<>();
		MyArrayList<int[]> neighbors = prune(g, start);
		for (int[] n : neighbors) {
			Node s = null;
			s = jump(start, n, end, g);
			if (s != null) successors.add(s);
		}
		return successors;
	}

	/**
	 * Returns the viable neighbors JPS can jump towards.
	 * @param neighbors
	 * @return
	 */
	private MyArrayList<int[]> prune(Graph g, Node start) {				
		MyArrayList<int[]> neighbors = new MyArrayList<>();
		Node parent = start.getParent();
		if (parent != null) {
			// If start node has a parent, we can get direction and thus ignore some of the surrounding nodes
			int[] parentXY = parent.getXY();
			int[] startXY = start.getXY();
			
			// Get normalized direction parent -> start
			int dir[] = Graph.getNormalizedDir(parentXY, startXY);
			int dirX = dir[0];
			int dirY = dir[1];
			
			// If direction is diagonal
			if (dirX != 0 && dirY != 0) {
				/*
				 * startXY is current, if dir is { -1, 1 } then below diagram represents the following coordinates
				 * left		| fLeft		| forwards
				 * bLeft	| startXY	| fRight
				 * ignore	| bRight	| right
				 */
				int[] forward = Graph.offset(startXY, dirX, dirY);
				int[] fLeft = Graph.offset(startXY, dirX, 0);
				int[] fRight = Graph.offset(startXY, 0, dirY);
				int[] left = Graph.offset(startXY, dirX, -dirY);
				int[] right = Graph.offset(startXY, -dirX, dirY);
				int[] bLeft = Graph.offset(startXY, 0, -dirY);
				int[] bRight = Graph.offset(startXY, -dirX, 0);
				
                if (g.getWeight(fRight) > 0) neighbors.add(fRight);
                if (g.getWeight(fLeft) > 0) neighbors.add(fLeft);
                if (g.getWeight(forward) > 0) neighbors.add(forward);
                // If perpendicularly (right and left) blocked, add forced nodes
                // Basically if bLeft is blocked, add left and if bRight is blocked add right
                if (g.getWeight(bLeft) == 0 && g.getWeight(left) > 0) neighbors.add(left);
                if (g.getWeight(bRight) == 0 && g.getWeight(right) > 0) neighbors.add(right);
            
            // If direction is vertical
			} else if (dirX == 0) {
				/*
				 * startXY is current, if dir is { 0, 1 } then below diagram represents the following coordinates
				 * ignore	| left		| fLeft
				 * ignore	| startXY	| forward
				 * ignore	| right		| fRight
				 */
				int[] forward = Graph.offset(startXY, 0, dirY);
				int[] left = Graph.offset(startXY, -1, 0);
				int[] right = Graph.offset(startXY, 1, 0);
				int[] fLeft = Graph.offset(startXY, -1, dirY);
				int[] fRight = Graph.offset(startXY, 1, dirY);
				
                if (g.getWeight(forward) > 0) neighbors.add(forward);
                // If perpendicularly blocked, add forced nodes
                // Basically if left is blocked, add fLeft and if right is blocked add fRight
                if (g.getWeight(right) == 0 && g.getWeight(fRight) > 0) neighbors.add(fRight);
                if (g.getWeight(left) == 0 && g.getWeight(fLeft) > 0) neighbors.add(fLeft);
			
            // If direction is horizontal
			} else {
				/*
				 * startXY is current, if dir is { 1, 0 } then below diagram represents the following coordinates
				 * ignore	| ignore	| ignore
				 * right	| startXY	| left
				 * fRight	| forward	| fLeft
				 */
				int[] forward = Graph.offset(startXY, dirX, 0);
				int[] left = Graph.offset(startXY, 0, 1);
				int[] right = Graph.offset(startXY, 0, -1);
				int[] fLeft = Graph.offset(startXY, dirX, 1);
				int[] fRight = Graph.offset(startXY, dirX, -1);
				
                if (g.getWeight(forward) > 0) neighbors.add(forward);
                // If perpendicularly blocked, add forced nodes
                // Basically if left is blocked, add fLeft and if right is blocked add fRight
                if (g.getWeight(left) == 0 && g.getWeight(fLeft) > 0) neighbors.add(fLeft);
                if (g.getWeight(right) == 0 && g.getWeight(fRight) > 0) neighbors.add(fRight);
			}
			
		} else {
			// If no parent (i.e. we are at start), just return all neighbors
			return g.neighbors(start.getXY());
		}
		return neighbors;
	}
	
	/**
	 * Method will jump Nodes in a direction and returns first successor Node.
	 * A node is a successor if end can be reached from it or it has a forced neighbor.
	 * A forced neighbor is a coordinate that would be normally ignored but is blocked for the neighboring path 
	 * that otherwise would have gotten there as fast or faster.
	 * @param start 	Starting Node
	 * @param towards 	Coordinate towards which the method will move.
	 * @param g 		Graph
	 * @return Successor Node 
	 */
	private Node jump(Node start, int[] towards, int[] end, Graph g) {
		// TODO: Add visited check somewhere relevant
		// if (visited[n[0]][n[1]] == 0) 
		
		// Determine dir based on 'start -> towards' 
		int[] dir = Graph.getNormalizedDir(start.getXY(), towards);
		int dirX = dir[0];
		int dirY = dir[1];

		// If wall, return null
		if (g.getWeight(towards) == 0) return null;
		
		// If goal, return new Node of 'towards'
		if (Arrays.equals(towards, end)) return newStart(start, towards, end);
		
		// If forced node is found in neighbors, return new Node of 'towards'
		if (dir[0] != 0 && dir[1] != 0) {
			// Check diagonally
            if ((g.getWeight(Graph.offset(towards, -dirX, dirY)) != 0 && g.getWeight(Graph.offset(towards, -dirX, 0)) == 0) ||
        			(g.getWeight(Graph.offset(towards, dirX, -dirY)) != 0 && g.getWeight(Graph.offset(towards, 0, -dirY)) == 0)) {
            	return newStart(start, towards, end);
            }
            // Jump horizontally and vertically
            int[] newTowardsXYLeft = Graph.offset(towards, dirX, 0);
            int[] newTowardsXYRight = Graph.offset(towards, 0, dirY);
            Node nextStart = newStart(start, towards, end);
            if (jump(nextStart, newTowardsXYLeft, end, g) != null ||
            		jump(nextStart, newTowardsXYRight, end, g) != null) {
            	return nextStart;
            }
		} else {
            if (dir[0] != 0) {
            	// Check horizontally
                if ((g.getWeight(Graph.offset(towards, dirX, 1)) != 0 && g.getWeight(Graph.offset(towards, 0, 1)) == 0) ||
                		(g.getWeight(Graph.offset(towards, dirX, -1)) != 0 && g.getWeight(Graph.offset(towards, 0, -1)) == 0)) {
                	return newStart(start, towards, end);
                }
            } else {
            	// Check vertically
                if ((g.getWeight(Graph.offset(towards, 1, dirY)) != 0 && g.getWeight(Graph.offset(towards, 1, 0)) == 0) ||
                		(g.getWeight(Graph.offset(towards, -1, dirY)) != 0 && g.getWeight(Graph.offset(towards, -1, 0)) == 0)) {
                	return newStart(start, towards, end);
                }
            }
		}
		
		// If no forced Nodes found, move towards the goal
		int[] nextTowards = Graph.offset(towards, dirX, dirY);
		return jump(start, nextTowards, end, g);
	}
	
	/**
	 * Creates a new starting for a new jump. As the same Node is used in many places, 
	 * this could have been run once early but sometimes is not needed so it is created as late as possible.
	 * @param start		Jump origin
	 * @param towards	Coordinate to be checked
	 * @param end		The goal
	 * @return
	 */
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
