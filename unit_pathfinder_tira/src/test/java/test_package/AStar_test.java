package test_package;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import graph.Graph;
import pathfinder.AStar;

import java.util.ArrayList;

class AStar_test {

	@Test
	void test_small_right() {
		// Test setup
		int[][] matrix = { 	{ 1, 5, 5 }, 
							{ 1, 5, 5 }, 
							{ 1, 1, 1 } };
		Graph g = new Graph(matrix);
		int[][] path = { { 0, 0 }, { 1, 1 }, { 2, 2 } };

		// Do test
		AStar a = new AStar();
		ArrayList<int[]> ans = a.search(g, path[0], path[2]);

		// Check result
		for (int i = 0; i < ans.size(); i++) {
			Assertions.assertArrayEquals(path[i], ans.get(i), "test small right");
		}
	}

	@Test
	void test_small_left() {
		// Test setup
		int[][] matrix = { 	{ 1, 1, 1 }, 
							{ 5, 5, 1 }, 
							{ 5, 5, 1 } };
		Graph g = new Graph(matrix);
		int[][] path = { { 0, 0 }, { 1, 1 }, { 2, 2 } };

		// Do test
		AStar a = new AStar();
		ArrayList<int[]> ans = a.search(g, path[0], path[2]);

		// Check result
		for (int i = 0; i < ans.size(); i++) {
			Assertions.assertArrayEquals(path[i], ans.get(i), "test small right");
		}
	}
}
