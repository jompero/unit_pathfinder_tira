package test_package;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import graph.Node;
import mycollections.MyArrayList;

class Node_test {
	int[] valid = { 0, 0 };
	Node parent = new Node(valid, 0, null);

	@Test
	void test_illegalArgument() {
		int[] xy = { 0 };
		IllegalArgumentException thrown =
	        assertThrows(IllegalArgumentException.class,
	           () -> new Node(xy, 1, parent),
	           "illegal argument exception not thrown");
	}
	
	@Test
	void test_nullParent() {
		assertEquals(null, parent.getParent());
	}
	
	@Test
	void test_xy() {
		assertEquals(valid, parent.getXY());
	}
	
	@Test
	void test_weight() {
		assertEquals(0, parent.getWeight());
	}
	
	@Test
	void test_path1() {
		MyArrayList<int[]> result = parent.path();
		if (result.size() < 1 || result.size() > 1) fail("path wrong");
		Assertions.assertArrayEquals(valid, result.get(0));
	}
}
