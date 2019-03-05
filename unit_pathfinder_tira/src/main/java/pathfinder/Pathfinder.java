package pathfinder;

import java.util.Arrays;

import graph.Graph;
import mycollections.MyArrayList;

/**
 * Pathdinder is an abstract class that provides a skeleton for all implemented pathfinding algorithms.
 * @author danijompero
 *
 */
public abstract class Pathfinder {
	// Benchmarking data
	double totalWeight = 0;
	int nodesInPath = 0;
	long time = 0;
	MyArrayList<int[]> visitedList;
	MyArrayList<int[]> path;
	
	abstract public MyArrayList<int[]> search(Graph g, int[] start, int[] end);
	
	public double getTotalWeight() {
		return totalWeight;
	}
	
	public int getNodesInPath() {
		return nodesInPath;
	}
	
	public int getVisitedNodes() {
		if (visitedList == null) return 0;
		return visitedList.size();
	}
	
	public long getTime() {
		return time;
	}
	
	public MyArrayList<int[]> getVisited() {
		return visitedList;
	}
	
	protected boolean weightCheck(Graph g, int[] start, int[] end) {
		if (g.getWeight(start) == 0 || g.getWeight(end) == 0) return true;
		return false;
	}
	
	protected MyArrayList<int[]> duplicateCheck(int[] start, int[] end) {
		if (Arrays.equals(start, end)) {
			MyArrayList<int[]> result = new MyArrayList<>();
			result.add(end);
			visitedList = new MyArrayList<>();
			visitedList.add(end);
			nodesInPath = 1;
			time = System.currentTimeMillis() - time;
			return result;
		}
		return null;
	}
	
	protected boolean matrixCheck(Graph g) {
		int height = g.getHeight();
		int width = g.getWidth();
		if (height == 0 || width == 0) return true;
		return false;
	}
	
	protected void resetBenchmark() {
		totalWeight = 0;
		nodesInPath = 0;
		time = 0;
		visitedList = null;
	}
}
