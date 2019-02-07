package test_package;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import graph.Graph;
import pathfinder.JPS;

class JPS_test {

	JPS j = new JPS();
	
	@Test
	void small_test() {
		int[][] matrix = { { 1, 1 }, { 1, 1 } };
		Graph g = new Graph(matrix);
		int[] start = { 0, 0 };
		int[] end = { 0, 1 };
		ArrayList<int[]> result = j.search(g, start, end);
		ArrayList<int[]> expected = new ArrayList<>();
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
	void test_small1() {
		// Test setup
		int[][] matrix = { 	{ 0, 0, 0, 0, 0, 0 }, 
							{ 0, 1, 1, 1, 1, 0 }, 
							{ 0, 1, 1, 1, 1, 0 }, 
							{ 0, 1, 1, 1, 1, 0 }, 
							{ 0, 1, 1, 1, 1, 0 }, 
							{ 0, 0, 0, 0, 0, 0 } };
		Graph g = new Graph(matrix);
		int[][] path = { { 1, 1 }, { 2, 2 }, { 3, 3 }, { 4, 3 } };

		// Do test
		JPS j = new JPS();
		ArrayList<int[]> ans = j.search(g, path[0], path[3]);

		// Check result
		for (int i = 0; i < ans.size(); i++) {
			Assertions.assertArrayEquals(path[i], ans.get(i), "test small 1");
		}
	}
	
	@Test
	void test_small2() {
		// Test setup
		int[][] matrix = { 	{ 0, 0, 0, 0, 0, 0 }, 
							{ 0, 1, 0, 1, 0, 0 }, 
							{ 0, 1, 0, 1, 1, 0 }, 
							{ 0, 1, 0, 0, 1, 0 }, 
							{ 0, 1, 1, 1, 1, 0 }, 
							{ 0, 0, 0, 0, 0, 0 } };
		Graph g = new Graph(matrix);
		int[][] path = { { 1, 1 }, { 2, 1 }, { 3, 1 }, { 4, 2 }, { 4, 3 }, { 3, 4 }, { 2, 3 }, { 1, 3 } };

		// Do test
		JPS j = new JPS();
		ArrayList<int[]> ans = j.search(g, path[0], path[7]);

		// Check result
		for (int i = 0; i < ans.size(); i++) {
			Assertions.assertArrayEquals(path[i], ans.get(i), "test small 2");
		}
	}

	@Test
	void test_small3() {
		// Test setup
		int[][] matrix = { 	{ 0, 0, 0, 0, 0, 0 }, 
							{ 0, 1, 0, 0, 0, 0 }, 
							{ 0, 1, 0, 1, 0, 0 }, 
							{ 0, 1, 1, 0, 1, 0 }, 
							{ 0, 1, 0, 1, 0, 0 }, 
							{ 0, 0, 0, 0, 0, 0 } };
		Graph g = new Graph(matrix);
		int[][] path = { { 3, 4 }, { 2, 3 }, { 3, 2 }, { 2, 1 }, { 1, 1 } };

		// Do test
		JPS j = new JPS();
		ArrayList<int[]> ans = j.search(g, path[0], path[4]);

		// Check result
		for (int i = 0; i < ans.size(); i++) {
			Assertions.assertArrayEquals(path[i], ans.get(i), "test small 3");
		}
	}
	
	@Test
	void test_small4() {
		// Test setup
		int[][] matrix = { 	{ 1, 1, 0, 1, 1, 1 }, 
							{ 1, 1, 0, 1, 1, 1 }, 
							{ 1, 0, 0, 1, 1, 1 }, 
							{ 0, 0, 0, 1, 1, 1 }, 
							{ 1, 1, 1, 1, 1, 1 }, 
							{ 1, 1, 1, 1, 1, 1 } };
		Graph g = new Graph(matrix);
		int[][] path = { { 5, 0 }, { 4, 1 }, { 4, 2 }, { 3, 3 }, { 2, 4 }, { 1, 4 } };

		// Do test
		JPS j = new JPS();
		ArrayList<int[]> ans = j.search(g, path[0], path[5]);

		// Check result
		for (int i = 0; i < ans.size(); i++) {
			Assertions.assertArrayEquals(path[i], ans.get(i), "test small 4");
		}
	}
	
	@Test
	void test_small_right() {
		// Test setup
		int[][] matrix = { 	{ 1, 5, 5 }, 
							{ 1, 0, 5 }, 
							{ 1, 0, 1 } };
		Graph g = new Graph(matrix);
		int[][] path = { { 0, 0 }, { 0, 1 }, { 1, 2 }, { 2, 2 } };

		// Do test
		JPS j = new JPS();
		ArrayList<int[]> ans = j.search(g, path[0], path[3]);

		// Check result
		for (int i = 0; i < ans.size(); i++) {
			Assertions.assertArrayEquals(path[i], ans.get(i), "test small right");
		}
	}
	
	@Test
	void test_small_left() {
		// Test setup
		int[][] matrix = { 	{ 1, 5, 5 }, 
							{ 1, 0, 0 }, 
							{ 1, 1, 1 } };
		Graph g = new Graph(matrix);
		int[][] path = { { 0, 0 }, { 1, 0 }, { 2, 1 }, { 2, 2 } };

		// Do test
		JPS j = new JPS();
		ArrayList<int[]> ans = j.search(g, path[0], path[3]);

		// Check result
		for (int i = 0; i < ans.size(); i++) {
			Assertions.assertArrayEquals(path[i], ans.get(i), "test small left");
		}
	}
	
	@Test
	void test_small_diag() {
		// Test setup
		int[][] matrix = { 	{ 1, 5, 5 }, 
							{ 1, 5, 5 }, 
							{ 1, 1, 1 } };
		Graph g = new Graph(matrix);
		int[][] path = { { 2, 2 }, { 1, 1 }, { 0, 0 } };

		// Do test
		JPS j = new JPS();
		ArrayList<int[]> ans = j.search(g, path[0], path[2]);

		// Check result
		for (int i = 0; i < ans.size(); i++) {
			Assertions.assertArrayEquals(path[i], ans.get(i), "test small right reverse");
		}
	}
}
