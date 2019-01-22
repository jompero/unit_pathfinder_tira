package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import unit_pathfinder_tira.Dijkstra;
import unit_pathfinder_tira.Graph;

class Dijkstra_test {
	Dijkstra d = new Dijkstra();
	
	@Test
	void invalid_argument_test() {
		int[][] matrix = {{1, 1}, {1, 1}};
		Graph g = new Graph(matrix);
		int[] start = {0, 0};
		int[] end = {0, 2};

	    assertThrows(NullPointerException.class, () -> {
	    		d.search(g, start, end);
	    });
	}
	
	@Test
	void small_test() {
		int[][] matrix = {{1, 1}, {1, 1}};
		Graph g = new Graph(matrix);
		int[] start = {0, 0};
		int[] end = {0, 1};
		ArrayList<int[]> result = d.search(g, start, end);
		ArrayList<int[]> expected = new ArrayList<>();
		expected.add(start);
		expected.add(end);
		
		boolean exp = true;
		boolean test = true;
		if (result.size() != 2) {
			test = false;
		} else {
			for (int i = 0; i < 2; i++) {
				if (!Arrays.equals(result.get(i), expected.get(i))) {
					test = false;
				}
			}
		}
		assertEquals(exp, test, "test small graph");
	}
}
