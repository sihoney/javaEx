package interface_form;

public interface List<E> {
	
	boolean add(E value);
	
	void add(int index, E value);
	
	E remove(int index);
	
	E get(int index);
	
	void set(int index, E value);
	
	boolean contains(Object value);
	
	int indexOf(Object value);
	
	int size();
	
	boolean isEmpty();
	
	public void clear();
}
