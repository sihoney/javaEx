package interface_form;

public interface Set<E> {
	
	boolean add(E e);
	
	boolean remove(Object o);
	
	boolean contains(Object o);
	
	boolean equals(Object o);
	
	boolean isEmpty();
	
	void clear();
}
