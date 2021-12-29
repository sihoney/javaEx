/*
 * 자바에서 linkedList로 구현한 Queue가 가장 대중적으로 쓰인다.
 */

package Queue;

import java.util.NoSuchElementException;

import interface_form.Queue;

public class LinkedListQueue<E> implements Queue<E> {

	private Node<E> head;
	private Node<E> tail;
	private int size;
	
	public LinkedListQueue() {
		this.head = null;
		this.tail = null;
		this.size = 0;
	}
	
	@Override
	public boolean offer(E value) {
		Node<E> newNode = new Node<E>(value);
		
		// 비어있을 경우
		if(size == 0) {
			head = newNode;
		}
		
		// 그 외의 경우 마지막 노드의 다음 노드가 새 노드를 가리키도록 한다.
		else {
			tail.next = newNode;
		}
		/*
		 * tail이 가리키는 노드를 새 노드로 바꿔준다.
		 */
		tail = newNode;
		size++;
		
		return true;
	}

	@Override
	public E poll() {
		
		// 삭제할 요소가 없을 경우
		if(size == 0) {
			return null;
		}
		
		// 삭제될 요소의 데이터를 반환하기 위한 임시 변수
		E element = head.data;
		
		// head 노드의 다음노드
		Node<E> nextNode = head.next;
		
		// head의 모든 데이터들을 삭제
		head.data = null;
		head.next = null;
				
		// head 가 가리키는 노드를 삭제된 head 노드의 다음 노드를 가리키도록 변경
		head = nextNode;
		size--;
		
		return element;
	}
	
	public E remove() {
		
		E element = poll();
		
		if(element == null) {
			throw new NoSuchElementException();
		}	
		
		return element;
	}

	@Override
	public E peek() {
		
		//요소가 없을 경우 null 반환
		if(size == 0) {
			return null;
		}
		
		return head.data;
	}
	
	public E element() {
		
		E element = peek();
		
		if(element == null) {
			throw new NoSuchElementException();
		}
		
		return element;
	}
	
	public int size() {
		return size;
	}

	public boolean isEmpty() {
		return size == 0;
	}
	
	public boolean contains(Object value) {

		for(Node<E> x = head; x != null; x = x.next) {
			if(value.equals(x.data)) {
				return true;
			}
		}
		
		return false;
	}
	
	public void clear() {
		
		for(Node<E> x = head; x != null;) {
			
			Node<E> next = x.next;
			x.data = null;
			x.next = null;
			x = next;
		}
		
		size = 0;
		head = tail = null;
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
			// Array.newInstance(컴포넌트 타입, 생성할 크기)
			a = (T[]) java.lang.reflect.Array.newInstance(a.getClass().getComponentType(), size);
		}
		
		int i = 0;
		// 얕은 복사를 위한 s 배열
		Object[] result = a;
		for(Node<E> x = head; x != null; x = x.next) {
			result[i++] = x.data;
		}
		/*
		 * 
		 * 
		 * 굳이 얕은 복사를 통한 Object[] result 를 만드는 이유는 무엇일까?
		 * 
		 * 
		 */
		return a;
	}
	
	@Override 
	public Object clone() {
		
		// super.clone() 은 CloneNotSupportedException 예외를 처리해주어야 한다.
		try {
			@SuppressWarnings("unchecked")
			LinkedListQueue<E> clone = (LinkedListQueue<E>) super.clone(); // 새로운 큐 객체 생성
			clone.head = null;
			clone.tail = null;
			clone.size = 0;
			
			// 내부까지 복사되는 것이 아니기 때문에 내부 데이터들응ㄹ 모두 복사해준다.
			for(Node<E> x = head; x != null; x = x.next) {
				clone.offer(x.data);
			}
			return clone;
 		} catch(CloneNotSupportedException e) {
 			throw new Error(e); // 예외처리는 여러분들이 자유롭게 구성
 		}
	}
	
}
