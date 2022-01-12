/*
 * 배열로 구현한 Deque와의 차이점
 * - 이중 연결 리스트의 노드를 사용
 * (
 */

package Deque;

import interface_form.Queue;

public class LinkedListDeque<E> implements Queue<E> {

	///////////////////
	// field
	///////////////////
	private Node<E> head;
	private Node<E> tail;
	private int size;
	
	
	///////////////////
	// constructor
	///////////////////
	public LinkedListDeque() {
		head = null;
		tail = null;
		size = 0;
	}
	
	
	///////////////////
	// method
	///////////////////
	
	/*
	 * [offer 계열 메소드 구현]
	 * - 가장 마지막 부분에 추가: offer(E item), offerLast(E item)
	 * - 가장 앞의 부분에 추가: offerFirst(E item)
	 * 
	 * + 양방향 연결리스트의 경우, 전방 삽입부터 구현하는 것이 편리(직관적이기 때문)
	 */
	
	public boolean offerFirst(E value) {
		
		Node<E> newNode = new Node<E>(value);
		newNode.next = head;
		
		/*
		 * head가 null이 아닌 경우에만 기존 head노드의 prev 변수가 새 노드를 가리키도록 한다.
		 * 이유는 기존 head노드가 없는 경우(null)는 데이터가 
		 * 아무 것도 없던 상태였으므로 head.prev를 하면 잘못된 참조가 된다. 
		 */
		
		if(head != null) {
			head.prev = newNode;
		}
		
		head = newNode;
		size++;
		
		/*
		 * 다음에 가리킬 노드가 없는 경우(= 데이터가 새 노드밖에 없는 경우 = 이전의 head가 null인 경우)
		 * 데이터가 한개(새 노드)밖에 없으므로 새 노드느느 처음 시작 노드이자
		 * 마지막 노드다. 즉 tail = head다. 
		 */
		
		if(head.next == null) {
			tail = head;
		}
		
		return true;
	}
	
	@Override
	public boolean offer(E value) {
		return offerLast(value);
	}

	public boolean offerLast(E value) {
		
		// 데이터가 없을 경우 offerFirst()로 인자를 넘겨줌
		if(size == 0) {
			return offerFirst(value);
		}
		
		Node<E> newNode = new Node<E>(value);
		
		tail.next = newNode; 
		newNode.prev = tail;
		tail = newNode;
		size++;
		
		return true;
	}
	
	/*
	 * [poll 계열 메소드 구현]
	 * - 전방 삭제: poll(), pollFirst(), remove(), removeFirst()
	 */
	
	@Override
	public E poll() {
		return pollFirst();
	}
	
	public E pollFirst() {
		if(size == 0) {
			return null;
		}
		
		
	}

	@Override
	public E peek() {
		// TODO Auto-generated method stub
		return null;
	}

}
