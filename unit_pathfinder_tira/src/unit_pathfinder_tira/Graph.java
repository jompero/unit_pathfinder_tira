package unit_pathfinder_tira;

import java.util.ArrayList;

public class Graph {
	int[][] matrix;
	int width, height;

	public Graph(int[][] matrix) {
		this.matrix = matrix;
		height = matrix.length;
		width = matrix[0].length;
	}

	public int getWeight(int[] v) {
		return matrix[v[0]][v[1]];
	}

	/**
	 * @param xy
	 *            Coordinates of node to be tested
	 * @return Coordinates of neighboring nodes
	 */
	public ArrayList<int[]> neighbors(int[] xy) {
		ArrayList<int[]> neighbors = new ArrayList<>();
		if (xy[1] > 0) {
			int[] left = { xy[0], xy[1] - 1 };
			if (getWeight(left) > 0)
				neighbors.add(left);
		}
		if (xy[1] < width - 1) {
			int[] right = { xy[0], xy[1] + 1 };
			if (getWeight(right) > 0)
				neighbors.add(right);
		}
		if (xy[0] > 0) {
			int[] up = { xy[0] - 1, xy[1] };
			if (getWeight(up) > 0)
				neighbors.add(up);
		}
		if (xy[0] < height - 1) {
			int[] down = { xy[0] + 1, xy[1] };
			if (getWeight(down) > 0)
				neighbors.add(down);
		}
		return neighbors;
	}
}

class Node implements Comparable<Node> {
	int[] xy;
	int weight;
	Node parent;

	public Node(int[] xy, int weight, Node parent) {
		if (xy.length > 2 || xy.length < 2) {
			throw new IllegalArgumentException();
		}
		this.xy = xy;
		this.weight = weight;
		this.parent = parent;
	}

	public ArrayList<int[]> path() {
		ArrayList<int[]> path = new ArrayList<>();
		if (parent != null) {
			path.addAll(parent.path());
		}
		path.add(xy);
		return path;
	}

	@Override
	public int compareTo(Node o) {
		return weight - o.weight;
	}
}