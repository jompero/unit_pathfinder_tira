package graph;

import java.util.ArrayList;

public class Graph {
	final int[][] matrix;
	final int width, height;

	public Graph(int[][] matrix) {
		this.matrix = matrix;
		height = matrix.length;
		width = matrix[0].length;
	}
	
	public int[][] getMatrix() {
		return matrix;
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}

	public int getWeight(int[] v) {
		return getWeight(v[0], v[1]);
	}
	
	public int getWeight(int x, int y) {
		if (x < 0 || x >= height || y < 0 || y >= width) return 0;
		if (matrix[x][y] > 0) return 1;
		return 0;
	}

	/**
	 * @param xy		Coordinates of node to be tested
	 * @return 		Coordinates of neighboring nodes
	 */
	public ArrayList<int[]> neighbors(int[] xy) {
		return neighbors(xy[0], xy[1]);
	}
	
	/**
	 * @param x 		X coordinate of node to be tested
	 * @param y 		Y coordinate of node to be tested
	 * @return		Coordinates of neighboring nodes
	 */
	public ArrayList<int[]> neighbors(int x, int y) {
		ArrayList<int[]> neighbors = new ArrayList<>();
		if (y > 0) {
			if (getWeight(x, y - 1) > 0)
				neighbors.add(coordinate(x, y - 1));
			if (x > 0) {
				if (getWeight(x - 1, y - 1) > 0)
					neighbors.add(coordinate(x - 1, y - 1));
			}
			if (x < height - 1) {
				if (getWeight(x + 1, y - 1) > 0)
					neighbors.add(coordinate(x + 1, y - 1));
			}
		}
		if (y < width - 1) {
			if (getWeight(x, y + 1) > 0)
				neighbors.add(coordinate(x, y + 1));
			if (x > 0) {
				if (getWeight(x - 1, y + 1) > 0)
					neighbors.add(coordinate(x - 1, y + 1));
			}
			if (x < height - 1) {
				if (getWeight(x + 1, y + 1) > 0)
					neighbors.add(coordinate(x + 1, y + 1));
			}
		}
		if (x > 0) {
			if (getWeight(x - 1, y) > 0)
				neighbors.add(coordinate(x - 1, y));
		}
		if (x < height - 1) {
			if (getWeight(x + 1, y) > 0)
				neighbors.add(coordinate(x + 1, y));
		}
		return neighbors;
	}
	
	/**
	 * Creates an integer array tuple of given x and y
	 * @param x		X coordinate
	 * @param y		Y Coordinate
	 * @return		{ x, y }
	 */
	public static int[] coordinate(int x, int y) {
		int[] c = { x, y };
		return c;
	}

	/**
	 * Returns the Euclidean distance between the given coordinates.
	 * @param a		Start coordinates of node to be tested
	 * @param b		End coordinates of node to be tested
	 * @return		Euclidean distance as Double
	 */
	public static double distance(int[] a, int[] b) {
		// Return Euclidean distance between the two points
		double xDist = (b[0] - a[0]);
		double yDist = (b[1] - a[1]);
		return Math.sqrt(xDist*xDist + yDist*yDist);
	}

	/**
	 * Get normalized direction between the given coordinates (unit vector).
	 * @param a		Start coordinates of node to be tested
	 * @param b		End coordinates of node to be tested
	 * @return		Unit vector as integer array
	 */
	public static int[] getNormalizedDir(int[] a, int[] b) {
		if (a == null || b == null) return null;
		int[] dir = {b[0] - a[0], b[1] - a[1]};
		if (dir[0] != 0) dir[0] = dir[0]/Math.abs(dir[0]);
		if (dir[1] != 0) dir[1] = dir[1]/Math.abs(dir[1]);
		return dir;
	}
}