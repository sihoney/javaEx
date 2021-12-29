/*
 * Queue는 자바로 구현할 경우 LinkedList로 구현하는게 휠씬 편하다.
 * 기본적인 Queue에 대한 개념을 잡고 다른 언어로도 구현하게 될 수도 있기에 
 * 기본적으로 배열을 사용한 방식으로 먼저 포스팅했다. 
 */

package Queue;

import java.util.Arrays;
import java.util.Comparator;
import java.util.NoSuchElementException;

import interface_form.Queue;

public class ArrayQueue<E> implements Queue<E> {
	
	private static final int DEFAULT_CAPACITY = 64; // 최소 기본 용적 크기
	
	private Object[] array; // 요소를 담을 배열
	private int size; // 요소 개수
	
	private int front; // 시작 인텍스를 가리키는 변수(빈 공간을 유의)
	private int rear; // 마지막 요소의 인덱스를 가라키는 변수
	
	// 생성자 1
	public ArrayQueue() {
		this.array = new Object[DEFAULT_CAPACITY];
		this.size = 0;
		this.front = 0;
		this.rear = 0;
	}
	
	// 생성자 2
	public ArrayQueue(int capacity) {
		this.array = new Object[capacity];
		this.size = 0;
		this.front = 0;
		this.rear = 0;
	}
	
	public void resize(int newCapacity) {
		
		int arrayCapacity = array.length; // 현재 용적 크기
		
		Object[] newArray = new Object[newCapacity]; // 용적을 변경한 배열
		
		/*
		 *  i = new array index
		 *  j = original array
		 *  index 요소 개수(size)만큼 새 배열에 값 복사
		 */
		
		for(int i = 0, j = front + 1; i <= size; i++, j++) {
			newArray[i] = array[j % arrayCapacity];
		}
		
		this.array = null;
		this.array = newArray; // 새 배열을 기존 array의 배열로 덮어씌움
		
		front = 0;
		rear = size; 
	}
	
	@Override
	public boolean offer(E item) {
		
		// 용적이 가득 찼을 경우
		if((rear + 1) % array.length == front) {
			resize(array.length * 2); // 용적을 두 배 늘려준다. 
		}
		
		rear = (rear + 1) % array.length; // rear을 rear의 다음 위치로 갱신
		
		array[rear] = item;
		size++; 
		
		return true;
	}

	@Override
	public E poll() {
		
		if(size == 0) { // 삭제할 요소가 없을 경우 null 반환
			return null;
		}
		
		front = (front + 1) % array.length; // front를 한 칸 옮긴다. 
		
		@SuppressWarnings("unchecked")
		E item = (E) array[front]; // 반환할 데이터 임시 저장
		
		array[front] = null; // 해당 front의 데이터 삭제
		size--; 
		
		// 용적이 최소 크기(64)보다 크고 요소 개수가 1/4 미만일 경우
		if(array.length > DEFAULT_CAPACITY && size < (array.length / 4)) {
			
			// 아무리 작아도 최소 용적 미만으로 줄이지는 않도록 한다. 
			resize(Math.max(DEFAULT_CAPACITY, array.length / 2));
		}
		
		return item;
	}
	
	public E remove() {
		
		E item = poll();
		
		if(item == null) {
			throw new NoSuchElementException();
		}
		
		return item;
	}

	@Override
	public E peek() {
		
		// 요소가 없을 경우 null 반환
		if(size == 0) {
			return null;
		}
		
		@SuppressWarnings("unchecked")
		E item = (E) array[(front + 1) % array.length];
		return item;
	}
	
	public E element() {
		
		E item = peek();
		
		if(item == null) {
			throw new NoSuchElementException();
		}
		
		return item;
	}
	
	public int size() {
		return size;
	}
	
	public boolean isEmpty() {
		return size == 0;
	}
	
	public boolean contains(Object value) {
		
		int start = (front + 1) % array.length;
		
		/*
		 * i : 요소 개수만큼만 반복한다.
		 * idx : 원소 위치로, 매 회(idx + 1) % array.length; 의 위치로 갱신
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
	
	public Object[] toArray() {
		return toArray(new Object[size]);
	}
	
	@SuppressWarnings("unchecked")
	public <T> T[] toArray(T[] a) {
		
		final T[] res;
		
		// 들어오는 배열의 길이가 큐의 요소보다 작은 경우
		if(a.length < size) {
			/*
			 * front가 rear보다 앞에 있을 경우(또는 요소가 없을 경우 f==r)
			 */
			if(front <= rear) {
				return (T[]) Arrays.copyOfRange(array, front + 1, rear + 1, a.getClass());
			}
			
			/*
			 * front가 rear보다 뒤에 있을 경우
			 */
			res = (T[]) Arrays.copyOfRange(array, 0, size, a.getClass());
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
		 * front가 rear보다 앞에 있을 경우 (또는 요소가 없을 경우 f == r)
		 */
		if(front <= rear) {
			System.arraycopy(array, front + 1, a, 0, size);
		}
		
		/*
		 * front가 rear보다 뒤에 있을 경우
		 */
		else {
			
			int rearlength = array.length - 1 - front; // 뒷 부분의 요소 개수
			
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
			ArrayQueue<E> clone = (ArrayQueue<E>) super.clone(); // 새로운 큐 객체 생성
			
			clone.array = Arrays.copyOf(array, array.length);
			
			return clone;
			
		} catch (CloneNotSupportedException e) {
			throw new Error(e); 
		}
	}
	
	public void sort() {
		/*
		 * comparator를 넘겨주지 않는 경우 해당 객체의 comparable에 구현된
		 * 정렬 방식을 사용
		 * 
		 * 만약 구현되어 있지 않으면 cannot be case to class java.lang.Comparable
		 * 에러가 발생
		 * 
		 * 만약 구현되어있을 경우 null로 파라미터를 넘기면
		 * Arrays.sort()가 객체의 compareTo 메소드에 정의된 방식대로 정렬
		 */
		sort(null);
	}
	
	@SuppressWarnings("unchecked")
	public void sort(Comparator<? super E> c) {
		
		// null 접근 방지를 위해 toArray로 요소만 있는 배열을 얻어 이를 정렬한 뒤 덮어씌운다.
		Object[] res = toArray();
		
		Arrays.sort((E[]) res, 0, size, c);
		
		clear();
		/*
		 * 정렬된 원소를 다시 array에 0부터 차례대로 채운다.
		 * 이 때 front = 0 인덱스는 비워야 하므로 사실상 1번째 인덱스부터 채워야 한다.
		 */
		
		System.arraycopy(res, 0, array, 1, res.length); // res 배열을 array에 복사
		
		this.rear  = this.size = res.length;
	}
}
