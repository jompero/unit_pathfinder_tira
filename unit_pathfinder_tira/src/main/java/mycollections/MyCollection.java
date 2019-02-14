package mycollections;

/**
 * MyList is my implementation of an abstract collection that will be extended for a priority queue and an array list.
 * @author danijompero
 *
 * @param <T>
 */
abstract public class MyCollection<T> {
	protected Object[] list = new Object[10];;
	protected int size = 0;

	abstract public void add(T object);
	
	abstract public T get(int index);
	
	protected void resize() {
		Object[] newList = new Object[list.length + 20];
		System.arraycopy(list, 0, newList, 0, list.length);
		list = newList;
	}
	
	protected void resize(int amount) {
		Object[] newList = new Object[list.length + amount];
		System.arraycopy(list, 0, newList, 0, list.length);
		list = newList;
	}
	
	public boolean isEmpty() {
		if (size == 0) return true;
		return false;
	}
	
	public int size() {
		return size;
	}
	
	abstract public void addAll(MyArrayList<T> items);
}
