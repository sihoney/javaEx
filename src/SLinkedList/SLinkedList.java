package SLinkedList;

import interface_form.List;

public class SLinkedList<E> implements List<E>{

	//////////////////////
	// FIELD
	//////////////////////
	private Node<E> head;
	private Node<E> tail;
	private int size;
	
	/////////////////////
	// CONSTRUCTOR
	/////////////////////
	public SLinkedList() {
		this.head = null;
		this.tail = null;
		this.size = 0;
	}
	
	//////////////////////
	// METHOD
	//////////////////////
	/*    특정 위치의 노드를 반환하는 메소드     */
	private Node<E> search(int index) {
		
		/*  범위 밖(잘못된 위치)일 경우 예외 던지기  */
		if(index < 0 || index >= size) {
			throw new IndexOutOfBoundsException();
		}
		
		Node<E> x = head; 
		
		for(int i = 0; i < index; i++) {
			x = x.next;
		}
		
		return x;
	}
	
	public void addFirst(E value) {
		
		Node<E> newNode = new Node<E>(value);
		newNode.next = head; // 새 노드의 다음 노드로 head 노드를 연결
		head = newNode; // head가 가리키는 노드를 새 노드로 변경
		size++;
		
		/*
		 * 다음에 가리킬 노드가 없는 경우(= 데이터가 새 노드밖에 없는 경우)
		 * 데이터가 한 개 밖에 없으므로 새 노드는 시작노드이자 마지막 노드
		 * 즉 tail = head
		 */
		
		if(head.next == null) {
			tail = head;
		}
	}
	
	@Override
	public boolean add(E value) {
		addLast(value);
		return true;
	}
	
	public void addLast(E value) {
		
		Node<E> newNode = new Node<E>(value);
		
		if(size == 0) {
			addFirst(value);
			return;
		}
		
		tail.next = newNode;
		tail = newNode;
		size++;
	}

	@Override
	public void add(int index, E value) {
		
		// 잘못된 인덱스를 참조할 경우 예외 발생
		if(index > size || index < 0) {
			throw new IndexOutOfBoundsException();
		}
		
		// 추가하려는 index가 가장 앞에 추가하려는 경우 --> addFirst
		if(index == 0) {
			addFirst(value);
			return;
		}
		
		// 추가하려는 index가 마지막 위치일 경우 --> addLast
		if(index == size) {
			addLast(value);
			return;
		}
		
		// 추가하려는 위치의 이전 노드
		Node<E> prev_Node = search(index - 1);
		
		// 추가하려는 위치의 노드
		Node<E> next_Node = prev_Node.next;
		
		// 추가하려는 노드
		Node<E> newNode = new Node<E>(value);
		
		prev_Node.next = null;
		prev_Node.next = newNode;
		newNode.next = next_Node;
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
