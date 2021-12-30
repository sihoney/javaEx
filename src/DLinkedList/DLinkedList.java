package DLinkedList;

import interface_form.List;

public class DLinkedList<E> implements List<E> {

	////////////////////////////
	// field
	////////////////////////////	
	private Node<E> head;
	private Node<E> tail;
	private int size;
	
	
	////////////////////////////
	// constructor
	////////////////////////////
	public DLinkedList() {
		this.head = null;
		this.tail = null;
		this.size = 0;
	}
	
	
	////////////////////////////
	// method
	////////////////////////////
	private Node<E> search(int index) {
		
		
	}
	
	@Override
	public boolean add(E value) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void add(int index, E value) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public E remove(int index) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public E get(int index) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void set(int index, E value) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean contains(Object value) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int indexOf(Object value) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}

}
