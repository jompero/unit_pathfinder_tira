package mycollections;

import java.util.Iterator;

public class MyArrayList<T> extends MyCollection<T> implements Iterable<T>{

	@Override
	public void add(T object) {
		size++;
		if (size > list.length) {
			resize();
		}
		list[size-1] = object;
	}

	@SuppressWarnings("unchecked")
	@Override
	public T get(int index) {
		if (index < 0 || index >= size) return null;
		return (T) list[index];
	}
	
	@Override
	public void addAll(MyArrayList<T> items) {
		if (items.size >= list.length) resize(list.length - size + items.size + 20);
		for (T t : items) {
			add(t);
		}
	}
	
	@Override
	public Iterator<T> iterator() {
		return new MyIterator();
	}
	
    private class MyIterator implements Iterator<T> {
        private int position = 0;
 
        public boolean hasNext() {
            if (position < size)
                return true;
            else
                return false;
        }
 
        public T next() {
            if (this.hasNext())
                return get(position++);
            else
                return null;
        }
 
        @Override
        public void remove() {
 
        }
    }
}
