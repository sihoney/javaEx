package SLinkedList;

import java.util.Arrays;
import java.util.Comparator;
import java.util.NoSuchElementException;

import interface_form.List;

public class SLinkedList<E> implements List<E>, Cloneable{

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

	// 가장 앞에 있는 요소 제거 (head가 가리키는 요소만 삭제)
	public E remove() {
		
		Node<E> headNode = head;
		
		if(headNode == null) {
			throw new NoSuchElementException();
		}
		
		E element = headNode.data;
		
		Node<E> nextNode = head.next;
		
		head.data = null;
		head.next = null;
		
		head = nextNode;
		size--;
		
		/*
		 * 삭제된 요소가 유일한 요소였을 경우
		 * --> 그 요소는 head이자 tail
		 * 유일한 요소가 삭제되면 tail도 가리킬 요소가 없다.
		 * size가 0일 경우 tail도 null로 변환
		 */
		
		if(size == 0) {
			tail = null;
		}
		
		return element;
	}
	
	@Override
	public E remove(int index) {
		
		// 삭제하려는 노드가 첫 번째 원소일 경우
		if(index == 0) {
			return remove();
		}
		
		// 잘못된 범위에 대한 예외
		if(index < 0 || index >= size) {
			throw new IndexOutOfBoundsException();
		}
		
		Node<E> prevNode = search(index - 1);
		Node<E> removedNode = prevNode.next;
		Node<E> nextNode = removedNode.next;
		
		E element = removedNode.data;
		
		prevNode.next = nextNode;
		
		removedNode.next = null;
		removedNode.data = null;
		size--;
		
		return element;
	}
	
	public boolean remove(Object value) {
		
		Node<E> prevNode = head;
		Node<E> x = head; // removedNode
		
		for(; x != null; x = x.next) {
			
			if(value.equals(x.data)) {
				break;
			}
			prevNode = x;
		}
		
		// 일치하는 요소가 없을 경우 false 반환
		if(x == null) {
			return false;
		}
		
		// 만약 삭제하려는 노드가 head라면 기존 remove()를 사용
		if(x.equals(head)) {
			remove();
			return true;
		}
		
		else {
			// 이전 노드의 링크를 삭제하려는 노드의 다음 노드로 연결
			prevNode.next = x.next;
			
			x.data = null;
			x.next = null;
			size--;
			return true;
		}
	}

	@Override
	public E get(int index) {
		return search(index).data;
	}

	@Override
	public void set(int index, E value) {
		
		Node<E> replaceNode = search(index);
		replaceNode.data = null;
		replaceNode.data = value;
	}

	@Override
	public boolean contains(Object value) {
		return indexOf(value) >= 0;
	}

	@Override
	public int indexOf(Object value) {
		
		int index = 0;
		
		for(Node<E> x = head; x != null; x = x.next) {
			if(value.equals(x.data)) {
				return index;
			}
			
			index++;
		}
		
		return -1;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public void clear() {
		
		for(Node<E> x = head; x != null;) {
			Node<E> nextNode = x.next;
			x.data = null;
			x.next = null;
			x = nextNode;
		}
		
		head = tail = null;
		size = 0;
	}

	/*
	 * [clone, toArray, sort]
	 * clone: 기존에 있는 객체를 파괴하지 않고 요소들이 동일한 객체를 새로 하나 만드는 것
	 * toArray: 리스트를 출력할 때 for-each문을 쓰기 위해 사용된다. 
	 * 			(for-each문을 구현하고 싶다면 iterable을 구현해주어야 하지만, 내용이 길어지는 관계로 생략)
	 * sort: 리스트 정렬
	 */
	
	/*
	 * [clone]
	 * 깊은 복사를 하기 위해 필요한 것이 바로 clone()
	 * Object에 있는 메소드지만 접근제어자가 protected로 되어있어 사용자 클래스의 경우
	 * Cloneable 인터페이스를 implement 해야한다.
	 */
	
	public Object clone() throws CloneNotSupportedException {
		
		@SuppressWarnings("unchecked")
		SLinkedList<? super E> clone = (SLinkedList<? super E>) super.clone(); 
		
		clone.head = null;
		clone.tail = null;
		clone.size = 0;
		
		for(Node<E> x = head; x != null; x = x.next) {
			clone.addLast(x.data);
		}
		
		return clone;
	}
	
	/*
	 * [toArray]
	 * ArrayList에서는 내부에서 데이터를 Object[] 배열에 담았기 때문에 데이터 복사가 쉬웠으나
	 * SLinkedList는 노드라는 객체에 데이터를 담고 있는 연결리스트이기 때문에
	 * 노드 자체가 Wrapper 클래스나 사용자가 만든 데이터를 갖을 수가 없다.
	 * 한마디로 노드의 data 변수가 객체 타입 변수인 것이지 노드 자체가 객체 타입을 갖지 못한다.
	 * 그래서 Arrays.copyOf()나 System.arraycopy를 쓰기 어렵다.
	 */
	
	public Object[] toArray() {
		
		Object[] array = new Object[size];
		int idx = 0;
		
		for(Node<E> x = head; x != null; x = x.next) {
			array[idx++] = (E) x.data;                            // 왜 E로 변환해주지??
		}
		
		return array;
	}
	
	@SuppressWarnings("unchecked")
	public <T> T[] toArray(T[] a) {
		
		if(a.length < size) {
			// Array.newInstance(컴포넌트 타입, 생성할 크기)
			a = (T[]) java.lang.reflect.Array.newInstance(a.getClass().getComponentType(), size);
		}
		
		int i = 0;
		Object[] result = a;
		for(Node<E> x = head; x != null; x = x.next) {
			result[i++] = x.data;
		}
		
		return a;
	}
	
	/*
	 * ArrayList 같은 경우, 자체에서 Object[] 배열을 사용하고 있기 때문에 Arrays.sort() 해주면 끝
	 * 
	 * LinkedList 같은 객체로 연결괸 리스트들은 다른 방법으로 정렬해야 함
	 * 객체배열의 경우 Collections.sort()를 사용하게 되는데, 해당 메소드도 Arrays.sort() 사용
	 * 
	 * 래퍼 클래스라면 Comparator을 구현해주지 않아도 되지만, 
	 * 사용자 클래스의 경우 사용자가 따로 해당 객체에 Comparable를 구현해주거나 Comparator를 구현해주어 
	 * 파라미터로 넘겨주어야 한다.
	 */
	
	public void sort() {
		
		/*
		 * Comparator를 넘겨주지 않는 경우
		 * 해당 객체의 Comparable에 구현된 정렬방식을 사용 (구현되지 않았으면 에러가 발생)
		 * 만약 구현되어 있을 경우 null로 파라미터를 넘기면
		 * Arrays.sort()가 객체의 compareTo 메소드에 정의된방식대로 정렬
		 */
		
		sort(null);
	}
	
	@SuppressWarnings("unchecked")
	public void sort(Comparator<? super E> c) {
		
		Object[] a = this.toArray();
		Arrays.sort(a, (Comparator) c);
		
		int i = 0;
		for(Node<E> x = head; x != null; x = x.next, i++) {
			x.data = (E) a[i];
		}
	}
}
