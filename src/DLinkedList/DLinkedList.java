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
		
		// 범위 밖(잘못된 위치)일 경우 예외 던지기
		if(index < 0 || index >= size) {
			throw new IndexOutOfBoundsException();
		}
		
		// 뒤에서부터 검색
		if(index > size / 2) {
			
			Node<E> x = tail;
			for(int i = size - 1; i > index; i--) {
				x = x.prev;
			}
			
			return x;
		}
		
		// 앞에서부터 검색
		else {
			
			Node<E> x = head;
			for(int i = 0; i < index; i++) {
				x = x.next;
			}
			
			return x;
		}
	}
	
	public void addFirst(E value) {
		
		Node<E> newNode = new Node<E>(value);
		newNode.next = head;
		
		/*
		 * head가 null이 아닐 경우에만 기존 head노드의 prev변수가 새 노드를 가리키도록 한다.
		 * 이유는 기존 head노드가 없는 경우(null)는 데이터가 아무 것도 없던 상태였으므로 head.prev를 하면 
		 * 잘못된 참조가 된다.
		 */
		
		if(head != null) {
			head.prev = newNode;
		}
		
		head = newNode;
		size++;
		
		/*
		 * 다음에 가리킬 노드가 없는 경우(데이터가 새 노드밖에 없는 경우)
		 * 데이터가 한 개밖에 없으므로 새 노드는 처음 시작노드이자 마지막 노드다.
		 * 즉 tail = head다.
		 */
		
		if(head.next == null) {
			tail = head;
		}
	}
	
	// add()의 기본 값은 addLast()라고 했다. 구현 자체는 addLast를 중점적으로 구현하면 되는 것이다.
	// 그리고 여거서 addFirst()를 구현한 이유가 나오는데, size가 0일 경우
	// 결국 처음으로 데이터를 추가한다는 뜻이기 때문에 간단하데 addFirst()를 호출하면 된다.
	
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
		newNode.prev = tail;
		tail = newNode;
		size++;
	}
	
	@Override
	public void add(int index, E value) {
		
		// 잘못된 인덱스를 참조할 경우 예외 발생
		if(index > size || index < 0) {
			throw new IndexOutOfBoundsException();
		}
		
		if(index == 0) {
			addFirst(value);
			return;
		}
		
		if(index == size) {
			addLast(value);
			return;
		}
		
		Node<E> prev_Node = search(index - 1);
		
		Node<E> next_Node = prev_Node.next;
		
		Node<E> newNode = new Node<E>(value);
		
		// 링크 끊기
		prev_Node.next = null;
		next_Node.prev = null;
		
		// 링크 연결하기
		prev_Node.next = newNode;
		
		newNode.prev = prev_Node;
		newNode.next = next_Node;
		
		next_Node.prev = newNode;
		size++;
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
