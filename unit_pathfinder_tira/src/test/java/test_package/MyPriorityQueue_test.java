package test_package;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Random;

import org.junit.jupiter.api.Test;

import mycollections.MyPriorityQueue;

class MyPriorityQueue_test {

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
}
