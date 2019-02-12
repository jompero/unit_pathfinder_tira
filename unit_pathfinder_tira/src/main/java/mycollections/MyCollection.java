package mycollections;

abstract public class MyCollection<T> {
	protected int size = 0;
	
	/**
	 * MyList is my implementation of an abstract collection that will be extended to form a priority queue.
	 */
	public MyCollection() {
		
	}

	abstract public void add(T object);
	
	abstract public boolean isEmpty();
}
