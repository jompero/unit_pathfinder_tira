package test_package;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import graph.Graph;
import pathfinder.Dijkstra;

class Dijkstra_test {
	Dijkstra d = new Dijkstra();

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
		ArrayList<int[]> result = d.search(g, start, end);
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
	void test_small_right() {
		// Test setup
		int[][] matrix = { 	{ 1, 5, 5 }, 
							{ 1, 5, 5 }, 
							{ 1, 1, 1 } };
		Graph g = new Graph(matrix);
		int[][] path = { { 0, 0 }, { 1, 1 }, { 2, 2 } };

		// Do test
		ArrayList<int[]> ans = d.search(g, path[0], path[2]);

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
		ArrayList<int[]> ans = d.search(g, path[0], path[2]);

		// Check result
		for (int i = 0; i < ans.size(); i++) {
			Assertions.assertArrayEquals(path[i], ans.get(i), "test small right");
		}
	}
}
