/*
 * 자바에서 제공하고 있는 LinkedList 또한 양방향 연결리스트로 기본 메커니즘은 DLinkedList와 거의 같다.
 * 이중 연결리스트의 경우 단일 연결리스트와 마찬가지로 삽입, 삭제 과정에서 '링크'만 끊어주면 되기 때문에 효율적
 * 다만, 연결리스트는 모든 자료를 인덱스가 아닌 head로부터 연결되어 관리하기 때문에 색인 능력은 떻어짐
 */

package DLinkedList;

import java.util.Arrays;
import java.util.Comparator;
import java.util.NoSuchElementException;

import interface_form.List;

public class DLinkedList<E> implements List<E>, Cloneable {

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

	// 가장 앞에 있는 요소 제거 (즉, 헤드가 가리키는 요소만 제거하면 된다)
	public E remove() {
		
		Node<E> headNode = head;
		
		if(headNode == null) {
			throw new NoSuchElementException();
		}
		
		E element = headNode.data;
		
		Node<E> nextNode = head.next;
		
		head.data = null;
		head.next = null;
		
		/*
		 * head의 다음 노드(=nextNode)가 null이 아닐 경우메만
		 * prev 변수를 null로 업데이트 해주어야 한다.
		 * 이유는 nextNode가 없는 경우(=null)는 데이터가 
		 * 아무것도 없던 상태였으므로 nextNode.prev를 하면 잘못된 참조가 된다.
		 */
		
		if(nextNode != null) {
			nextNode.prev = null;
		}
		
		head = nextNode;
		size--;
		
		/*
		 * 삭제된 요소가 리스트의 유일한 요소였을 경우
		 * 그 요소는 head이자 tail이였으므로
		 * 삭제되면서 tail도 가리킬 요소가 없기 떄문에
		 * size가 0일 경우 tail도 null로 변환
		 */
		
		if(size == 0) {
			tail = null;
		}
		
		return element;
	}
	
	@Override
	public E remove(int index) {
		
		if(index < 0 || index >= size) {
			throw new IndexOutOfBoundsException();
		}
		
		if(index == 0) {
			E element = head.data;
			remove();
			return element;
		}
		
		Node<E> prevNode = search(index - 1);
		Node<E> removedNode = prevNode.next;
		Node<E> nextNode = removedNode.next;
		
		E element = removedNode.data;
		
		prevNode.next = null;
		removedNode.next = null;
		removedNode.prev = null;
		removedNode.data = null;
		
		if(nextNode != null) {
			nextNode.prev = null;
			
			nextNode.prev = prevNode;
			prevNode.next = nextNode;
		}
		
		/*
		 * nextNode가 null이라는 것은 마지막 노드를 삭제했다는 의미이므로
		 * prevNode가 tail이 된다 (연결 해줄 것이 없음)
		 */
		
		else {
			tail = prevNode;
		}
		
		size--;
		
		return element;
	}

	public boolean remove(Object value) {
		
		Node<E> prevNode = head;
		Node<E> x = head;
		
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
		
		// 삭제하려는 노드가 head일 경우 remove()로 삭제
		if(x.equals(head)) {
			remove();
			return true;
		}
		
		// remove(int index)와 같은 메커니즘으로 삭제
		else {
			Node<E> nextNode = x.next;
			
			prevNode.next = null;
			x.data = null;
			x.next = null;
			x.prev = null;
			
			if(nextNode != null) {
				nextNode.prev = null;
				
				nextNode.prev = prevNode;
				prevNode.next = nextNode;
			} 
			else {
				tail = prevNode;
			}
			
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
	public int indexOf(Object o) { // 정방향 탐색
		int index = 0;
		
		for(Node<E> x = head; x != null; x = x.next) {
			if(o.equals(x.data)) {
				return index;
			}
			index++;
		}
		
		return -1;
	}

	public int lastIndexOf(Object o) { // 역방향 탐색
		int index = size;
		
		for(Node<E> x = tail; x != null; x = x.prev) {
			index--;
			
			if(o.equals(x.data)) {
				return index;
			}
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
			x.prev = null;
			x = nextNode;
		}

		head = tail = null;
		size = 0;
	}
	
	////////////////////////////////////////////
	//<부가목록> [clone, toArray, sort]
	////////////////////////////////////////////
	
	public Object clone() throws CloneNotSupportedException {
		
		@SuppressWarnings("unchecked")
		DLinkedList<? super E> clone = (DLinkedList<? super E>) super.clone();
		
		clone.head = null;
		clone.tail = null;
		clone.size = 0;
		
		for(Node<E> x = head; x != null; x = x.next) {
			clone.addLast(x.data);
		}
		
		return clone;
	}
	
	public Object[] toArray() {
		
		Object[] array = new Object[size];
		int idx = 0;
		
		for(Node<E> x = head; x != null; x = x.next) {
			array[idx++] = (E) x.data;
		}
		
		return array;
	}
	
	@SuppressWarnings("unchecked")
	public <T> T[] toArray(T[] a) {
		if(a.length < size) {
			a = (T[]) java.lang.reflect.Array.newInstance(a.getClass().getComponentType(), size);
		}
		
		int i = 0;
		Object[] result = a;
		for(Node<E> x = head; x != null; x = x.next) {
			result[i++] = x.data;
		}
		
		return a;
	}
	
	public void sort() {
		sort(null);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void sort(Comparator<? super E> c) {
		Object[] a = this.toArray();
		Arrays.sort(a, (Comparator) c);
		
		int i = 0;
		for(Node<E> x = head; x != null; x = x.next, i++) {
			x.data = (E) a[i];
		}
	}
}
