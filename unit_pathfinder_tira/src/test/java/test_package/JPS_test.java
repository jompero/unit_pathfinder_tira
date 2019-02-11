package test_package;

import static org.junit.jupiter.api.Assertions.*;
import static java.time.Duration.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import graph.Graph;
import pathfinder.JPS;

class JPS_test {

	JPS j = new JPS();
	
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
	void test_small_start() {
		int[][] matrix = { { 1, 1 }, { 1, 1 } };
		Graph g = new Graph(matrix);
		int[] start = { 0, 0 };
		int[] end = { 0, 0 };
		ArrayList<int[]> result = j.search(g, start, end);
		ArrayList<int[]> expected = new ArrayList<>();
		expected.add(start);

		if (result.size() != 1) {
			fail("incorrect path returned");
		} else {
			for (int i = 0; i < 1; i++) {
				if (!Arrays.equals(result.get(i), expected.get(i))) {
					fail("incorrect path returned");
				}
			}
		}
	}
	
	@Test
	void test_null() {
		// Test setup
		int[][] matrix = { 	{ 0, 0, 0, 0, 0, 0 }, 
							{ 0, 1, 1, 1, 1, 0 }, 
							{ 0, 1, 1, 1, 1, 0 }, 
							{ 0, 1, 1, 0, 0, 0 }, 
							{ 0, 1, 1, 0, 1, 0 }, 
							{ 0, 0, 0, 0, 0, 0 } };
		Graph g = new Graph(matrix);
		int[][] path = { { 1, 1 }, { 2, 2 }, { 3, 3 }, { 4, 3 } };

		// Do test
		JPS j = new JPS();
		ArrayList<int[]> ans = j.search(g, path[0], path[3]);

		// Check result
		assertEquals(null, ans, "test null");
	}
	
	@Test
	void test_small0() {
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
		int[][] path = { { 1, 1 }, { 3, 3 }, { 4, 3 } };

		// Do test
		JPS j = new JPS();
		ArrayList<int[]> ans = j.search(g, path[0], path[2]);

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
		int[][] path = { { 1, 1 }, { 3, 1 }, { 4, 2 }, { 4, 3 }, { 3, 4 }, { 2, 3 }, { 1, 3 } };

		// Do test
		JPS j = new JPS();
		ArrayList<int[]> ans = j.search(g, path[0], path[6]);

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
		int[][] path = { { 5, 0 }, { 4, 1 }, { 4, 2 }, { 2, 4 }, { 1, 4 } };

		// Do test
		JPS j = new JPS();
		ArrayList<int[]> ans = j.search(g, path[0], path[4]);

		// Check result
		for (int i = 0; i < ans.size(); i++) {
			Assertions.assertArrayEquals(path[i], ans.get(i), "test small 4");
		}
	}
	
	@Test
	void test_small5() {
		// Test setup
		int[][] matrix = { 	{ 1, 1, 1, 1, 1, 1 }, 
							{ 1, 1, 1, 1, 1, 1 }, 
							{ 1, 1, 1, 1, 1, 1 }, 
							{ 1, 1, 1, 1, 1, 1 }, 
							{ 1, 1, 1, 1, 1, 1 }, 
							{ 1, 1, 1, 1, 1, 1 } };
		Graph g = new Graph(matrix);
		int[][] path = { { 5, 3 }, { 0, 3 } };

		// Do test
		JPS j = new JPS();
		ArrayList<int[]> ans = j.search(g, path[0], path[1]);

		// Check result
		for (int i = 0; i < ans.size(); i++) {
			Assertions.assertArrayEquals(path[i], ans.get(i), "test small 5");
		}
	}
	
	@Test
	void test_small6() {
		// Test setup
		int[][] matrix = { 	{ 1, 1, 1, 1, 1, 1 }, 
							{ 1, 1, 1, 1, 1, 1 }, 
							{ 1, 1, 1, 1, 1, 1 }, 
							{ 1, 1, 1, 1, 1, 1 }, 
							{ 1, 1, 1, 1, 1, 1 }, 
							{ 1, 1, 1, 1, 1, 1 } };
		Graph g = new Graph(matrix);
		int[][] path = { { 5, 3 }, { 4, 4 }, { 0, 4 } };

		// Do test
		JPS j = new JPS();
		ArrayList<int[]> ans = j.search(g, path[0], path[2]);

		// Check result
		for (int i = 0; i < ans.size(); i++) {
			Assertions.assertArrayEquals(path[i], ans.get(i), "test small 6");
		}
	}
	
	@Test
	void test_small7() {
		// Test setup
		int[][] matrix = { 	{ 1, 1, 1, 1, 1, 1 }, 
							{ 1, 1, 1, 1, 1, 1 }, 
							{ 1, 1, 1, 1, 1, 1 }, 
							{ 1, 1, 1, 1, 1, 1 }, 
							{ 1, 1, 1, 1, 1, 1 }, 
							{ 1, 1, 1, 1, 1, 1 } };
		Graph g = new Graph(matrix);
		int[][] path = { { 0, 4 }, { 1, 3 }, { 5, 3 } };

		// Do test
		JPS j = new JPS();
		ArrayList<int[]> ans = j.search(g, path[0], path[2]);

		// Check result
		for (int i = 0; i < ans.size(); i++) {
			Assertions.assertArrayEquals(path[i], ans.get(i), "test small 7");
		}
	}
	
	@Test
	void test_small8() {
		// Test setup
		int[][] matrix = { 	{ 1, 1, 0, 1, 1, 1 }, 
							{ 1, 1, 0, 1, 1, 1 }, 
							{ 1, 1, 0, 1, 1, 1 }, 
							{ 1, 1, 1, 1, 1, 1 }, 
							{ 1, 1, 0, 1, 1, 1 }, 
							{ 1, 1, 0, 1, 1, 1 } };
		Graph g = new Graph(matrix);
		int[][] path = { { 3, 0 }, { 3, 2 }, { 3, 5 } };

		// Do test
		JPS j = new JPS();
		ArrayList<int[]> ans = j.search(g, path[0], path[2]);

		// Check result
		for (int i = 0; i < ans.size(); i++) {
			Assertions.assertArrayEquals(path[i], ans.get(i), "test small 8");
		}
	}
	
	@Test
	void test_small9() {
		// Test setup
		int[][] matrix = { 	{ 1, 1, 1, 1, 1, 1 }, 
							{ 1, 1, 1, 1, 1, 1 }, 
							{ 0, 0, 1, 0, 0, 0 }, 
							{ 1, 1, 1, 1, 1, 1 }, 
							{ 1, 1, 1, 1, 1, 1 }, 
							{ 1, 1, 1, 1, 1, 1 } };
		Graph g = new Graph(matrix);
		int[][] path = { { 5, 2 }, { 2, 2 }, { 0, 2 } };

		// Do test
		JPS j = new JPS();
		ArrayList<int[]> ans = j.search(g, path[0], path[2]);

		// Check result
		for (int i = 0; i < ans.size(); i++) {
			Assertions.assertArrayEquals(path[i], ans.get(i), "test small 9");
		}
	}
	
	@Test
	void test_large1() {
		Graph g = graphGenerator(200, 200);
		int[][] path = { { 0, 0 }, { 199, 199 } };
		assertTimeoutPreemptively(ofMillis(1000), () -> {
        	ArrayList<int[]> ans = j.search(g, path[0], path[1]);
        });
	}
	
	@Test
	void test_large2() {
		Graph g = graphGenerator(1000, 1000);
    	int[][] path = { { 999, 0 }, { 0, 999 } };
		assertTimeoutPreemptively(ofMillis(1000), () -> {
        	ArrayList<int[]> ans = j.search(g, path[0], path[1]);
        });
	}
	
	@Test
	void test_large3() {
		Graph g = graphGenerator(1000, 1000);
    	int[][] path = { { 999, 1 }, { 0, 998 } };
		assertTimeoutPreemptively(ofMillis(1000), () -> {
        	ArrayList<int[]> ans = j.search(g, path[0], path[1]);
        });
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
		ArrayList<int[]> ans = j.search(g, path[0], path[3]);

		// Check result
		for (int i = 0; i < ans.size(); i++) {
			Assertions.assertArrayEquals(path[i], ans.get(i), "test small left");
		}
	}
	
	@Test
	void test_small_diag1() {
		// Test setup
		int[][] matrix = { 	{ 1, 5, 5 }, 
							{ 1, 5, 5 }, 
							{ 1, 1, 1 } };
		Graph g = new Graph(matrix);
		int[][] path = { { 2, 2 }, { 0, 0 } };

		// Do test
		ArrayList<int[]> ans = j.search(g, path[0], path[1]);

		// Check result
		for (int i = 0; i < ans.size(); i++) {
			Assertions.assertArrayEquals(path[i], ans.get(i));
		}
	}
		
		@Test
		void test_small_diag2() {
			// Test setup
			int[][] matrix = { 	{ 0, 0, 5 }, 
								{ 0, 5, 0 }, 
								{ 0, 1, 1 } };
			Graph g = new Graph(matrix);
			int[][] path = { { 2, 2 }, { 1, 1 }, { 0, 2 } };

			// Do test
			ArrayList<int[]> ans = j.search(g, path[0], path[2]);

			// Check result
			for (int i = 0; i < ans.size(); i++) {
				Assertions.assertArrayEquals(path[i], ans.get(i));
			}
	}
		
	@Test
	void test_diag3() {
		// Test setup
		int[][] matrix = { 	{ 1, 1, 1, 1, 1, 1 }, 
							{ 1, 1, 1, 1, 1, 1 }, 
							{ 1, 1, 1, 1, 1, 1 }, 
							{ 0, 0, 1, 0, 0, 1 }, 
							{ 1, 1, 0, 1, 0, 1 }, 
							{ 1, 1, 1, 1, 0, 1 } };
		Graph g = new Graph(matrix);
		int[][] path = { { 5, 0 }, { 3, 2 }, { 2, 3 }, { 2, 4 } };

		// Do test
		JPS j = new JPS();
		ArrayList<int[]> ans = j.search(g, path[0], path[3]);

		// Check result
		for (int i = 0; i < ans.size(); i++) {
			Assertions.assertArrayEquals(path[i], ans.get(i));
		}
	}
}
