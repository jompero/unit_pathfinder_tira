package graph;

import mycollections.MyArrayList;

/**
 * A node is an expert coordinate which knows its parent and can hold a weight value.
 * @author danijompero
 *
 */
public class Node implements Comparable<Node> {
	final int[] xy;
	double weight;
	Node parent;

	public Node(int[] xy, double weight, Node parent) {
		if (xy.length > 2 || xy.length < 2) {
			throw new IllegalArgumentException();
		}
		this.xy = xy;
		this.weight = weight;
		this.parent = parent;
	}
	
	public int[] getXY() {
		return xy;
	}

	public double getWeight() {
		return weight;
	}
	
	/**
	 * Recursively go through parents and return the path to the Node with no parents.
	 * @return	Path to the Node with no parent in reverse order.
	 */
	public MyArrayList<int[]> path() {
		MyArrayList<int[]> path = new MyArrayList<>();
		if (parent != null) {
			path.addAll(parent.path());
		}
		path.add(xy);
		return path;
	}

	@Override
	public int compareTo(Node o) {
		if (weight == o.weight) return 0;
		if (weight > o.weight) {
			return 1;
		} else {
			return -1;
		}
	}

	public Node getParent() {
		return parent;
	}
}