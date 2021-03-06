package test_package;

import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsIterableContainingInAnyOrder.*;

import org.junit.jupiter.api.Test;

import graph.Graph;
import mycollections.MyArrayList;

import java.util.Arrays;

class Graph_test {
	
	@Test
	void test_matrix() {
		int[][] matrix = { 	{ 1, 1, 1 }, 
							{ 1, 1, 1 }, 
							{ 1, 1, 1 } };
		Graph g = new Graph(matrix);	
		int[][] gMatrix = g.getMatrix();
		if (!Arrays.equals(matrix, gMatrix)) fail("something went wrong with graph init");
	}
	
	@Test
	void test_distance_short1() {
		int[] a = { 0, 0 };
		int[] b = { 1, 0 };
		double result = Graph.distance(a, b);
		assertEquals(1, result);
	}
	
	@Test
	void test_distance_short2() {
		int[] a = { 0, 0 };
		int[] b = { 1, 1 };
		double expected = Math.sqrt(2);
		double result = Graph.distance(a, b);
		assertEquals(expected, result);
	}
	
	@Test
	void test_distance_negative1() {
		int[] a = { 1, 1 };
		int[] b = { 0, 0 };
		double expected = Math.sqrt(2);
		double result = Graph.distance(a, b);
		assertEquals(expected, result);
	}
	
	@Test
	void test_distance_negative2() {
		int[] a = { 1, 0 };
		int[] b = { 0, 0 };
		double result = Graph.distance(a, b);
		assertEquals(1, result);
	}
	
	@Test
	void test_neighbors1() {
		int[][] matrix = { 	{ 1, 1, 1 }, 
							{ 1, 1, 1 }, 
							{ 1, 1, 1 } };
		Graph g = new Graph(matrix);	
		MyArrayList<int[]> expected = new MyArrayList<>();
		int[][] neighbors = { { 0, 0 }, { 1, 0 }, { 2, 0 }, { 0, 1 }, { 0, 2 }, { 1, 2 }, { 2, 1 }, { 2, 2 } };
		for (int i = 0; i < neighbors.length; i++) {
			expected.add(neighbors[i]);
		}
		MyArrayList<int[]> result = g.neighbors(1, 1);
		assertThat(result, containsInAnyOrder(neighbors));
	}
	
	@Test
	void test_neighbors2() {
		int[][] matrix = { 	{ 1, 1, 1 }, 
							{ 1, 1, 1 }, 
							{ 1, 1, 1 } };
		Graph g = new Graph(matrix);	
		MyArrayList<int[]> expected = new MyArrayList<>();
		int[][] neighbors = { { 1, 0 }, { 0, 1 }, { 1, 1 } };
		for (int i = 0; i < neighbors.length; i++) {
			expected.add(neighbors[i]);
		}
		MyArrayList<int[]> result = g.neighbors(0, 0);

		assertThat(result, containsInAnyOrder(neighbors));
	}

	@Test
	void test_normalizeddir_up() {
		int[] a = { 1, 0 };
		int[] b = { 0, 0 };
		int[] expected = { -1, 0 };
		if (!Arrays.equals(expected, Graph.getNormalizedDir(a, b))) fail("unit vector up wrong");
	}
	
	@Test
	void test_normalizeddir_down() {
		int[] a = { 0, 0 };
		int[] b = { 1, 0 };
		int[] expected = { 1, 0 };
		if (!Arrays.equals(expected, Graph.getNormalizedDir(a, b))) fail("unit vector down wrong");
	}

	@Test
	void test_normalizeddir_left() {
		int[] a = { 1, 1 };
		int[] b = { 1, 0 };
		int[] expected = { 0, -1 };
		if (!Arrays.equals(expected, Graph.getNormalizedDir(a, b))) fail("unit vector left wrong");
	}
	
	@Test
	void test_normalizeddir_right() {
		int[] a = { 1, 0 };
		int[] b = { 1, 1 };
		int[] expected = { 0, 1 };
		if (!Arrays.equals(expected, Graph.getNormalizedDir(a, b))) fail("unit vector right wrong");
	}
	
	@Test
	void test_normalizeddir_upright() {
		int[] a = { 1, 0 };
		int[] b = { 0, 1 };
		int[] expected = { -1, 1 };
		if (!Arrays.equals(expected, Graph.getNormalizedDir(a, b))) fail("unit vector upright wrong");
	}
	
	// TODO: Null neighbor test
}
