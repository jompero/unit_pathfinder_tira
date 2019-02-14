package test_package;

import static java.time.Duration.ofMillis;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.Random;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import graph.Graph;
import mycollections.MyArrayList;
import pathfinder.Dijkstra;

class Dijkstra_test {
	Dijkstra d = new Dijkstra();

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
	void invalid_argument_test() {
		int[][] matrix = { { 1, 1 }, { 1, 1 } };
		Graph g = new Graph(matrix);
		int[] start = { 2, 0 };
		int[] end = { 0, 0 };

		assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
			d.search(g, start, end);
		});
	}

	@Test
	void small_test() {
		int[][] matrix = { { 1, 1 }, { 1, 1 } };
		Graph g = new Graph(matrix);
		int[] start = { 0, 0 };
		int[] end = { 0, 1 };
		MyArrayList<int[]> result = d.search(g, start, end);
		MyArrayList<int[]> expected = new MyArrayList<>();
		expected.add(start);
		expected.add(end);

		if (result.size() != 2) {
			fail("incorrect path returned");
		} else {
			for (int i = 0; i < 2; i++) {
				if (!Arrays.equals(result.get(i), expected.get(i))) {
					fail("incorrect path returned");
				}
			}
		}
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
		MyArrayList<int[]> ans = d.search(g, path[0], path[2]);

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
		MyArrayList<int[]> ans = d.search(g, path[0], path[2]);

		// Check result
		for (int i = 0; i < ans.size(); i++) {
			Assertions.assertArrayEquals(path[i], ans.get(i), "test small right");
		}
	}
	
	@Test
	void test_large1() {
		Graph g = graphGenerator(200, 200);
		int[][] path = { { 0, 0 }, { 999, 999 } };
		assertTimeoutPreemptively(ofMillis(1000), () -> {
        	MyArrayList<int[]> ans = d.search(g, path[0], path[1]);
        });
	}
	
	@Test
	void test_large2() {
		Graph g = graphGenerator(500, 500);
    	int[][] path = { { 499, 0 }, { 0, 499 } };
		assertTimeoutPreemptively(ofMillis(2000), () -> {
        	MyArrayList<int[]> ans = d.search(g, path[0], path[1]);
        });
	}
	
	@Test
	void test_large3() {
		Graph g = graphGenerator(500, 500);
    	int[][] path = { { 498, 1 }, { 0, 498 } };
		assertTimeoutPreemptively(ofMillis(2000), () -> {
        	MyArrayList<int[]> ans = d.search(g, path[0], path[1]);
        });
	}
}
