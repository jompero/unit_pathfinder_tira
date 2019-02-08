package pathfinder;

import java.util.ArrayList;

import graph.Graph;

public abstract class Pathfinder {
	// Benchmarking data
	double totalWeight = 0;
	int nodesInPath = 0;
	int visitedNodes = 0;
	long time = 0;
	
	abstract public ArrayList<int[]> search(Graph g, int[] start, int[] end);
	
	public double getTotalWeight() {
		return totalWeight;
	}
	
	public int getNodesInPath() {
		return nodesInPath;
	}
	
	public int getVisitedNodes() {
		return visitedNodes;
	}
	
	public long getTime() {
		return time;
	}
}
