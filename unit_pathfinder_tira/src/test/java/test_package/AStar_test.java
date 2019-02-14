package test_package;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import graph.Graph;
import mycollections.MyArrayList;
import pathfinder.AStar;

import static java.time.Duration.ofMillis;
import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;

import java.util.Random;

class AStar_test {
	
	AStar a = new AStar();

	Graph graphGenerator(int x, int y) {
		int[][] matrix = new int[x][y];
		
		Random r = new Random(2019);
		for (int i = 0; i < x; i++) {
			for (int j = 0; j < y; j++) {
				matrix[i][j] = 1;
			}
		}
		for (int i = 0; i < x/2; i++)  {
			int rX = r.nextInt(x - 2) + 1;
			int rY = r.nextInt(x - 2) + 1;
			matrix[rX][rY] = 0;
		}
		Graph g = new Graph(matrix);
		return g;
	}
	
	@Test
	void test_small_right() {
		// Test setup
		int[][] matrix = { 	{ 1, 5, 5 }, 
							{ 1, 5, 5 }, 
							{ 1, 1, 1 } };
		Graph g = new Graph(matrix);
		int[][] path = { { 0, 0 }, { 1, 1 }, { 2, 2 } };

		// Do test
		MyArrayList<int[]> ans = a.search(g, path[0], path[2]);

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
		MyArrayList<int[]> ans = a.search(g, path[0], path[2]);

		// Check result
		for (int i = 0; i < ans.size(); i++) {
			Assertions.assertArrayEquals(path[i], ans.get(i), "test small right");
		}
	}
	
	@Test
	void test_large1() {
		Graph g = graphGenerator(200, 200);
		assertTimeoutPreemptively(ofMillis(1000), () -> {
        	int[][] path = { { 0, 0 }, { 999, 999 } };
        	MyArrayList<int[]> ans = a.search(g, path[0], path[1]);
        });
	}
	
	@Test
	void test_large2() {
		Graph g = graphGenerator(1000, 1000);
		assertTimeoutPreemptively(ofMillis(1000), () -> {
        	int[][] path = { { 999, 0 }, { 0, 999 } };
        	MyArrayList<int[]> ans = a.search(g, path[0], path[1]);
        });
	}
	
	@Test
	void test_large3() {
		Graph g = graphGenerator(1000, 1000);
		assertTimeoutPreemptively(ofMillis(1000), () -> {
        	int[][] path = { { 999, 1 }, { 0, 998 } };
        	MyArrayList<int[]> ans = a.search(g, path[0], path[1]);
        });
	}
}
