package mycollections;

import java.util.ArrayList;

public class MyPriorityQueue<T extends Comparable<T>> extends MyCollection<T> {
	Object[] list = new Object[20];
	int depth = 0;
	
	@Override
	public boolean isEmpty() {
		if (size == 0) return true;
		return false;
	}

	@Override
	public void add(T object) {
		size++;
		if (size >= list.length) {
			resize();
		}
		list[size] = object;
		rotateUp(size);
	}
	
	@SuppressWarnings("unchecked")
	public T poll() {
		T polled = (T) list[1];
		list[1] = list[size];
		size--;
		rotateDown(1);
		return polled;
	}
	
	@SuppressWarnings("unchecked")
	private void rotateUp(int i) {
		if (i <= 1) return;
		T child = (T) list[i];
		T parent = (T) list[i/2];
		
		// Compare with parent and swap if smaller
		if (child.compareTo(parent) < 0) {
			list[i] = parent;
			list[i/2] = child;
			rotateUp(i/2);
		}
	}
	
	@SuppressWarnings("unchecked")
	private void rotateDown(int i) {
		if (i >= size) return;
		
		T parent = (T) list[i];
		T leftChild = null;
		if (i*2 <= size) leftChild = (T) list[i*2];
		T rightChild = null;
		if (i*2+1 <= size) rightChild = (T) list[i*2+1];
		
		// Compare with left and right children
		// If larger than one of them, swap with it
		// If larger than both, swap with smaller
		if (leftChild != null && parent.compareTo(leftChild) > 0) {
			if (rightChild != null && rightChild.compareTo(leftChild) < 0) {
				list[i] = rightChild;
				list[i*2+1] = parent;
				rotateDown(i*2+1);
			} else {
				list[i] = leftChild;
				list[i*2] = parent;
				rotateDown(i*2);
			}
		} else if (rightChild != null && parent.compareTo(rightChild) > 0) {
			list[i] = rightChild;
			list[i*2+1] = parent;
			rotateDown(i*2+1);
		}
	}
	
	private void resize() {
		Object[] newList = new Object[size + 20];
		System.arraycopy(list, 0, newList, 0, list.length);
		list = newList;
	}

	public void addAll(ArrayList<T> successors) {
		for (T t : successors) {
			add(t);
		}
	}
}
