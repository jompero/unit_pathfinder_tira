package test_package;

import static java.time.Duration.ofMillis;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import mycollections.MyArrayList;

class MyArrayList_test {
	MyArrayList<int[]> smallList = integerMatrixList(1000);
	MyArrayList<int[]> bigList = integerMatrixList(100000);
	
	MyArrayList<Integer> integerList(int items) {
		MyArrayList<Integer> list = new MyArrayList<>();
		
		for (int i = 0; i < items; i++) {
			int current = i+1;
			list.add(current);
		}
		
		return list;
    }
	
	MyArrayList<int[]> integerMatrixList(int items) {
		MyArrayList<int[]> list = new MyArrayList<>();
		
		for (int i = 0; i < items; i++) {
			int[] current = { i+1, i+2 };
			list.add(current);
		}
		
		return list;
    }

	@Test
	void test_add1() {
		MyArrayList<Integer> list = integerList(20);
		int listSize = list.size();
		assertEquals(20, listSize);
		int lastNum = list.get(listSize - 1);
		assertEquals(20, lastNum);
	}
	
	@Test
	void test_add2() {
		MyArrayList<Integer> list = integerList(100);
		int listSize = list.size();
		assertEquals(100, listSize);
		int lastNum = list.get(listSize - 1);
		assertEquals(100, lastNum);
	}
	
	@Test
	void test_add3() {
		assertTimeoutPreemptively(ofMillis(1000), () -> {
			MyArrayList<Integer> list = integerList(1000);
        });
	}
	
	@Test
	void test_addAll1() {
		MyArrayList<int[]> list = new MyArrayList<>();
		list.addAll(smallList);
		int listSize = list.size();
		assertEquals(1000, listSize);
	}
	
	@Test
	void test_addAll2() {
		MyArrayList<int[]> list = new MyArrayList<>();
		list.addAll(bigList);
		int listSize = list.size();
		assertEquals(100000, listSize);
	}
	
	@Test
	void test_forloop1() {
		MyArrayList<Integer> list = integerList(100);
		for (int i : list) {
			assertTrue(i > 0);
		}
	}
	
	@Test
	void test_forloop2() {
		MyArrayList<Integer> list = integerList(10000);
		for (int i : list) {
			assertTrue(i > 0);
		}
	}
	
	@Test
	void test_isEmpty1() {
		MyArrayList<Integer> list = integerList(100);
		assertTrue(!list.isEmpty());
	}
	
	@Test
	void test_isEmpty2() {
		MyArrayList<Integer> list = new MyArrayList<>();
		assertTrue(list.isEmpty());
	}
}
