package test_package;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import mycollections.MyArrayList;
import mycollections.MyPriorityQueue;

class MyPriorityQueue_test {
	MyArrayList<Integer> smallList = integerList(1000);
	MyArrayList<Integer> bigList = integerList(100000);
	
	MyArrayList<Integer> integerList(int items) {
		MyArrayList<Integer> list = new MyArrayList<>();
		
		for (int i = items; i > 0; i--) {
			int current = (Integer) i;
			list.add(current);
		}
		
		return list;
    }

	@Test
	void test_pollempty() {
		MyPriorityQueue<Integer> queue = new MyPriorityQueue<>();
		assertEquals(null, queue.poll());
		assertEquals(null, queue.poll());
		assertEquals(null, queue.poll());
	}
	
	@Test
	void test_small1() {
		MyPriorityQueue<Integer> queue = new MyPriorityQueue<>();
		assertEquals(queue.isEmpty(), true);
		queue.add(4);
		queue.add(3);
		queue.add(1);
		queue.add(4);
		assertEquals(queue.isEmpty(), false);
		assertEquals((Integer) 1, queue.poll());
		assertEquals((Integer) 3, queue.poll());
		assertEquals((Integer) 4, queue.poll());
		assertEquals((Integer) 4, queue.poll());
		assertEquals(true, queue.isEmpty());
	}

	@Test
	void test_large1() {
		MyPriorityQueue<Integer> queue = new MyPriorityQueue<>();
		for (int i = 0; i < 1000; i++) {
			queue.add(i+1);
		}
		assertEquals((Integer) 1, queue.poll());
	}
	
	@Test
	void test_large2() {
		MyPriorityQueue<Integer> queue = new MyPriorityQueue<>();
		for (int i = 1000; i > 0; i--) {
			queue.add(i);
		}
		assertEquals((Integer) 1, queue.poll());
	}
	
	@Test
	void test_get() {
		MyPriorityQueue<Integer> queue = new MyPriorityQueue<>();
		for (int i = 1000; i > 0; i--) {
			queue.add(i);
		}
		assertTrue(queue.get(2) != 1);
		assertEquals(null, queue.get(0));
		assertEquals(null, queue.get(1001));
	}
	
	@Test
	void test_addAll1() {
		MyPriorityQueue<Integer> queue = new MyPriorityQueue<>();
		queue.addAll(smallList);
		assertEquals((Integer) 1, queue.poll());
	}
	
	@Test
	void test_addAll2() {
		MyPriorityQueue<Integer> queue = new MyPriorityQueue<>();
		queue.addAll(bigList);
		assertEquals((Integer) 1, queue.poll());
	}
}
