package pathfinder;

import graph.Graph;
import mycollections.MyArrayList;

public abstract class Pathfinder {
	// Benchmarking data
	double totalWeight = 0;
	int nodesInPath = 0;
	long time = 0;
	MyArrayList<int[]> visitedList;
	
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
}
