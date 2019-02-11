package pathfinder;

import java.util.ArrayList;

import graph.Graph;

public abstract class Pathfinder {
	// Benchmarking data
	double totalWeight = 0;
	int nodesInPath = 0;
	long time = 0;
	ArrayList<int[]> visitedList;
	
	abstract public ArrayList<int[]> search(Graph g, int[] start, int[] end);
	
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
	
	public ArrayList<int[]> getVisited() {
		return visitedList;
	}
}
