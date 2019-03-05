package graph;

import mycollections.MyArrayList;

/**
 * My implementation of a graph. It uses an Integer matrix and contains various manipulations methods.
 * @author danijompero
 *
 */
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

	/**
	 * Get the weight of a given coordinate.
	 * @param v		Coordinate in int[] form were [0] is X and [1] is Y.
	 * @return		Weight of the given coordinate
	 */
	public int getWeight(int[] v) {
		return getWeight(v[0], v[1]);
	}
	
	/**
	 * Get the weight of a given coordinate.
	 * @param x		X coordinate
	 * @param y		Y coordinate
	 * @return		Weight of the given coordinate
	 */
	public int getWeight(int x, int y) {
		if (x < 0 || x >= height || y < 0 || y >= width) return 0;
		if (matrix[x][y] > 0) return 1;
		return 0;
	}

	/**
	 * Return neighbors of given coordinate. Walls will be ignored.
	 * @param xy		Coordinates of node to be tested
	 * @return 			Coordinates of neighboring nodes
	 */
	public MyArrayList<int[]> neighbors(int[] xy) {
		return neighbors(xy[0], xy[1]);
	}
	
	/**
	 * Return neighbors of given coordinate. Walls will be ignored.
	 * @param x 		X coordinate of node to be tested
	 * @param y 		Y coordinate of node to be tested
	 * @return			Coordinates of neighboring nodes
	 */
	public MyArrayList<int[]> neighbors(int x, int y) {
		MyArrayList<int[]> neighbors = new MyArrayList<>();
		if (y > 0) {
			if (getWeight(x, y - 1) > 0) neighbors.add(coordinate(x, y - 1));
			if (x > 0) {
				if (getWeight(x - 1, y - 1) > 0) neighbors.add(coordinate(x - 1, y - 1));
			}
			if (x < height - 1) {
				if (getWeight(x + 1, y - 1) > 0) neighbors.add(coordinate(x + 1, y - 1));
			}
		}
		if (y < width - 1) {
			if (getWeight(x, y + 1) > 0) neighbors.add(coordinate(x, y + 1));
			if (x > 0) {
				if (getWeight(x - 1, y + 1) > 0) neighbors.add(coordinate(x - 1, y + 1));
			}
			if (x < height - 1) {
				if (getWeight(x + 1, y + 1) > 0) neighbors.add(coordinate(x + 1, y + 1));
			}
		}
		if (x > 0) {
			if (getWeight(x - 1, y) > 0) neighbors.add(coordinate(x - 1, y));
		}
		if (x < height - 1) {
			if (getWeight(x + 1, y) > 0) neighbors.add(coordinate(x + 1, y));
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
		int[] dir = {b[0] - a[0], b[1] - a[1]};
		if (dir[0] != 0) dir[0] = dir[0]/Math.abs(dir[0]);
		if (dir[1] != 0) dir[1] = dir[1]/Math.abs(dir[1]);
		return dir;
	}
	
	/**
	 * Calculates the offset coordinate. Mainly implemented to improve readability of jump and prune methods.
	 * @param origin	Coordinate from which to offset
	 * @param offsetX	Offset value X
	 * @param offsetY	Offset value Y
	 * @return			Return offset coordinate
	 */
	public static int[] offset(int[] origin, int offsetX, int offsetY) {
		int X = origin[0] + offsetX;
		int Y = origin[1] + offsetY;
		int[] offset = { X, Y };
		return offset;
	}
}