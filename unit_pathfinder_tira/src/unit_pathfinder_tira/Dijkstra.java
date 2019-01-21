package unit_pathfinder_tira;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * @author danijompero
 *
 */
public class Dijkstra {

	
	/**
	 * Dijkstra's shortest path search algorithm.
	 * @param g Graph from which path is searched from
	 * @param start The starting point
	 * @param end The ending point
	 * @return 
	 */
	public ArrayList<int[]> search(Graph g, int[] start, int[] end) {
		int size = g.matrix.length;
		if (size == 0 || g.matrix[0].length == 0) return null;
		
		int[][] visited = new int[size][size];
		PriorityQueue<Node> queue = new PriorityQueue<>();
		queue.add(new Node(start, g.getWeight(start), null));
		
		while(!queue.isEmpty()) {
			Node n = queue.poll();
			if (Arrays.equals(n.xy, end)) {
				return n.path();
			}
			if (visited[n.xy[0]][n.xy[1]] > 0) {
				continue;
			}
			visited[n.xy[0]][n.xy[1]] = 1;
			
			for (int[] neighbor : g.neighbors(n.xy)) {
				queue.add(new Node(neighbor, n.weight + g.getWeight(neighbor), n));
			}
		}
		
		return null;
	}
}
