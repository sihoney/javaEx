/*
 * Deque (Double-ended queue)
 *  - 양방향 연결리스트(Doubly LinkedList)와 유사한 메커니즘
 *  - 양쪽 방향으로 삽입/삭제가 이루어 질 수 있도록 구현한 것
 *  - 장점: Stack처럼 사용할 수도 있고 Queue처럼 사용할 수도 있는 자료구조
 */

package Deque;

import java.util.Arrays;
import java.util.Comparator;
import java.util.NoSuchElementException;

import interface_form.Queue;

public class ArrayDeque<E> implements Queue<E>, Cloneable{

	/////////////////////
	// field
	/////////////////////
	private static final int DEFAULT_CAPACITY = 64;
	
	private Object[] array;
	private int size;
	
	private int front;
	private int rear;
	
	
	/////////////////////
	// constructor
	/////////////////////
	public ArrayDeque() {
		this.array = new Object[DEFAULT_CAPACITY];
		this.size = 0;
		this.front = 0;
		this.rear = 0;
	}
	
	public ArrayDeque(int capacity) {
		this.array = new Object[capacity];
		this.size = 0;
		this.front = 0;
		this.rear = 0;
	}
	
	
	/////////////////////
	// method
	/////////////////////
	private void resize(int newCapacity) {
		int arrayCapacity = array.length; // 현재 용적 크기
		
		Object[] newArray = new Object[newCapacity]; // 용적을 변경한 배열
		
		for(int i = 1, j = front + 1; i <= size; i++, j++) {
			newArray[i] = array[j % arrayCapacity];
		}
		
		this.array = null;
		this.array = newArray;
		
		front = 0;
		rear = size;
	}
	
	/*
	 * offer(), offerLast()
	 * offerFirst()
	 */
	
	@Override
	public boolean offer(E item) {
		return offerLast(item);
	}
	
	public boolean offerLast(E item) {
		
		if((rear + 1) % array.length == front) { // 용적이 가득 찼을 경우
			resize(array.length * 2); // 용적을 두 배 늘려준다.
		}
		
		rear = (rear + 1) % array.length;
		
		array[rear] = item;
		size++;
		
		return true;
	}
	
	public boolean offerFirst(E item) {
		// 용적이 가득 찼을 경우
		if((front - 1 + array.length) % array.length == rear) {
			resize(array.length * 2);
		}
		
		array[front] = item;
		front = (front - 1 + array.length) % array.length;
		size++;
		
		return true;
	}
	
	/*
	 * poll(), pollFirst()
	 * remove(), removeFirst()
	 * pollLast(), removeLast()
	 */

	@Override
	public E poll() {
		return pollFirst();
	}
	
	public E pollFirst() {
		if(size == 0) { // 삭제할 요소가 없을 경우 null 반환
			return null;
		}
		
		front = (front + 1) % array.length; // front를 한 칸 옮긴다.
		
		@SuppressWarnings("unchecked")
		E item = (E) array[front]; // 반환할 데이터 임시 저장
		
		array[front] = null; // 해당 front의 데이터 삭제
		size--;
		
		// 용적이 최소 크기(64)보다 크고 요소 개수가 1/4 미만인 경우
		if(array.length > DEFAULT_CAPACITY && size < (array.length / 4)) {
			
			// 아무리 작아도 최소용적 미만으로 줄이지는 않도록 한다.
			resize(Math.max(DEFAULT_CAPACITY, array.length / 2));
		}
		
		return item;
	}
	
	public E remove() {
		return removeFirst();
	}
	
	public E removeFirst() {
		E item = pollFirst(); 
		
		if(item == null) {
			throw new NoSuchElementException();
		}
		
		return item;
	}
	
	public E pollLast() {
		if(size == 0) { // 삭제할 요소가 없을 경우 null 반환
			return null;
		}
		
		@SuppressWarnings("unchecked")
		E item = (E) array[rear]; // 반환할 데이터 임시 저장
		
		array[rear] = null; // 해당 rear의 데이터 삭제
		
		rear = (rear - 1 + array.length) % array.length; // front를 한 칸 옮긴다.
		size--;
		
		// 용적이 최소 크기보다 크고 요소 개수가 1/4 미만일 경우
		if(array.length > DEFAULT_CAPACITY && size < (array.length / 4)) {
			
			// 아무리 작아도 최소용적 미만으로 줄이지는 않도록 한다. 
			resize(Math.max(DEFAULT_CAPACITY, array.length / 2));
		}
		
		return item;
	}
	
	public E removeLast() {
		E item = pollLast();
		
		if(item == null) {
			throw new NoSuchElementException();
		}
		
		return item;
	}

	/*
	 * peek(), peekFirst()
	 * peekLast()
	 * element(), getFirst()
	 * getLast()
	 */
	
	@Override
	public E peek() {
		return peekFirst();
	}
	
	public E peekFirst() {
		// 요소가 없을 경우 null 반환
		if(size == 0) {
			return null;
		}
		
		@SuppressWarnings("unchecked")
		E item = (E) array[(front + 1) % array.length];
		return item;
	}
	
	public E peekLast() {
		// 요소가 없을 경우 null 반환
		if(size == 0) {
			return null;
		}
		
		@SuppressWarnings("unchecked")
		E item = (E) array[(rear)];
		return item;
	}
	
	public E element() {
		return getFirst();
	}
	
	public E getFirst() {
		E item = peek();
		
		if(item == null) {
			throw new NoSuchElementException();
		}
		
		return item;
	}
	
	public E getLast() {
		E item = peekLast();
		
		if(item == null) {
			throw new NoSuchElementException();
		}
		return item;
	}
	
	/*
	 * size()
	 * isEmpty()
	 * contains()
	 * clear()
	 */
	
	public int size() {
		return size;
	}
	
	public boolean isEmpty() {
		return size == 0;
	}
	
	public boolean contains(Object value) {
		
		int start = (front + 1) % array.length;
		
		/*
		 * i : 요소 개수만큼만 반복
		 * idx : 원소 위치로, 매 회 (idx + 1) % array.length;의 위치로 갱신
		 */
		
		for(int i = 0, idx = start; i < size; i++, idx = (idx + 1) % array.length) {
			
			if(array[idx].equals(value)) {
				return true;
			}
		}
		
		return false;
	}
	
	public void clear() {
		
		for(int i = 0; i < array.length; i++) {
			array[i] = null;
		}
		
		front = rear = size = 0;
	}
	
	/*
	 * <부가목록>
	 * toArray()
	 * clone()
	 * sort()
	 */
	
	public Object[] toArray() {
		return toArray(new Object[size]);
	}
	
	@SuppressWarnings("unchecked")
	public <T> T[] toArray(T[] a) {
		
		final T[] res;
		
		// 들어오는 배열의 길이가 덱의 요소 개수보다 작은 경우
		if(a.length < size) { 
			
			/*
			 * front가 rear보다 뒤에 있을 경우 (또는 요소가 없을 경우 f==r)
			 *  ______________________
			 *  |  |  |  |  |  |  |  |
			 *  ˉˉˉˉˉˉˉˉˉˉˉˉˉˉˉˉˉˉˉˉˉˉ
			 *    	f        r
			 */
			if(front <= rear) {
				return (T[]) Arrays.copyOfRange(array,  front + 1, rear + 1, a.getClass());
			}
			
		 	/*
			 * front가 rear보다 앞에 있을 경우
			 *  ______________________
			 *  |  |  |  |  |  |  |  |
			 *  ˉˉˉˉˉˉˉˉˉˉˉˉˉˉˉˉˉˉˉˉˉˉ
			 *    	r        f
		  	 */
			res= (T[]) Arrays.copyOfRange(array, 0, size, a.getClass());
			int rearlength = array.length - 1 - front; // 뒷 부분의 요소 개수
			
			// 뒷 부분 복사
			if(rearlength > 0) {
				System.arraycopy(array, front + 1, res, 0, rearlength);
			}
			
			// 앞 부분 복사
			System.arraycopy(array, 0, res, rearlength, rear + 1);
			
			return res;
		}
		
		/*
		 * front가 rear보다 뒤에 있을 경우 (또는 요소가 없을 경우 f==r)
		 *  ______________________
		 *  |  |  |  |  |  |  |  |
		 *  ˉˉˉˉˉˉˉˉˉˉˉˉˉˉˉˉˉˉˉˉˉˉ
		 *    	f        r
		 */
		if(front <= rear) {
			System.arraycopy(array, front + 1, a, 0, size);
		}
		
		/*
		 * front가 rear보다 앞에 있을 경우
		 *  ______________________
		 *  |  |  |  |  |  |  |  |
		 *  ˉˉˉˉˉˉˉˉˉˉˉˉˉˉˉˉˉˉˉˉˉˉ
		 *    	r        f
		 */
		else {
			int rearlength = array.length - 1 - front; // 뒷 부분 요소 개수
			
			// 뒷 부분 복사
			if(rearlength > 0) {
				System.arraycopy(array, front + 1, a, 0, rearlength);
			}
			
			// 앞 부분 복사
			System.arraycopy(array, 0, a, rearlength, rear + 1);
		}
		
		return a;
	}
	
	@Override
	public Object clone() {
		
		try {
			
			@SuppressWarnings("unchecked")
			ArrayDeque<E> clone = (ArrayDeque<E>) super.clone();
			
			clone.array = Arrays.copyOf(array, array.length);
			return clone;
			
		} catch (CloneNotSupportedException e) {
			throw new Error(e);
		} 
	}
	
	public void sort() {
		sort(null);
	}
	
	@SuppressWarnings("unchecked")
	public void sort(Comparator<? super E> c) {
		
		// "null 접근 방지"를 위해 toArray로 "요소만 있는 배열"을 얻어 이를 정렬한 뒤 덮어씌운다.
		Object[] res = toArray();
		
		Arrays.sort((E[]) res, 0, size, c);
		
		clear();
		
		/*
		 * 정렬된 원소를 다시 array에 0부터 차례대로 채운다.
		 * 이 때 front = 0 인덱스는 비워야 하므로 사실상 1번째 인덱스부터 채워야 한다. 
		 */
		System.arraycopy(res, 0, array, 1, res.length);
		
		this.rear = this.size = res.length;
	}
}
