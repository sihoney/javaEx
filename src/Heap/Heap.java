package Heap;

import java.util.Arrays;
import java.util.Comparator;
import java.util.NoSuchElementException;

public class Heap<E> {
	
	private final Comparator<? super E> comparator;
	private static final int DEFAULT_CAPACITY = 10; 
	
	private int size;
	private Object[] array;
	
	public Heap() {
		this(null);
	}
	
	public Heap(Comparator<? super E> comparator) {
		this.array = new Object[DEFAULT_CAPACITY];
		this.size = 0;
		this.comparator = comparator;
	}
	
	public Heap(int capacity) {
		this(capacity, null);
	}
	
	public Heap(int capacity, Comparator<? super E> comparator) {
		this.array = new Object[capacity];
		this.size = 0;
		this.comparator = comparator;
	}

	private int getParent(int index) {
		return index / 2;
		
	}
	
	private int getLeftChild(int index) {
		return index * 2;
	}
	
	private int getRightChild(int index) {
		return index * 2 + 1;
	}
	
	/*
	 * [resize 메소드 구현]
	 */
	
	private void resize(int newCapacity) {
		
		Object[] newArray = new Object[newCapacity];
		
		for(int i = 0; i <= size; i++) {
			newArray[i] = array[i];
		}
		
		this.array = null;
		this.array = newArray;
	}
	
	/*
	 * [add 메소드 구현]
	 */
	
	public void add(E value) {
		
		if(size + 1 == array.length) {
			resize(array.length * 2);
		}
		
		siftUp(size + 1, value);
		size++;
	}
	
	private void siftUp(int idx, E target) {
		
		if(comparator != null) {
			siftUpComparator(idx, target, comparator);
		}
		else {
			siftUpComparable(idx, target);
		}
	}
	
	// Comparator을 이용한 sift-up
	@SuppressWarnings("unchecked")
	private void siftUpComparator(int idx, E target, Comparator<? super E> comp) {
		
		// root 노드보다 클 때까지만 탐색한다.
		while(idx > 1) {
			int parent = getParent(idx);
			Object parentVal = array[parent];
			
			// 타겟 노드 값이 부모노드보다 크면 반복문 종료
			if(comp.compare(target, (E) parentVal) >= 0) {
				break;
			}
			
			/*
			 * 부모노드가 타겟노드보다 크므로
			 * 현재 삽입 될 위치에 부모노드 값으로 교체해주고
			 * 타겟 노드의 위치를 부모노드의 위치로 변경해준다.
			 */
			array[idx] = parentVal;
			idx = parent;
		}
		
		// 최족적으로 삽입될 위치에 타겟 노드 값을 지정
		array[idx] = target;
 	}
	
	// 삽입할 객체의 Comparable을 이용한 siftUp
	@SuppressWarnings("unchecked")
	private void siftUpComparable(int idx, E target) {
		
		// 타겟노드가 비교될 수 있도록 한 변수를 만든다.
		Comparable<? super E> comp = (Comparable<? super E>) target;
		
		while(idx > 1) {
			int parent = getParent(idx);
			Object parentVal = array[parent];
			
			if(comp.compareTo((E) parentVal) >= 0) {
				break;
			}
			
			array[idx] = parentVal;
			idx = parent;
		}
		
		array[idx] = comp;
	}
	
	/*
	 * [remove 메소드 구현]
	 */
	@SuppressWarnings("unchecked")
	public E remove() {
		
		if(array[1] == null) {
			throw new NoSuchElementException();
		}
		
		E result = (E) array[1]; // 삭제된 요소를 반환하기 위한 임시 변수
		E target = (E) array[size]; 
		array[size] = null;
		
		// 삭제할 노드의 인덱스와 이후 재배치 할 타겟 노드를 넘겨준다.
		siftDown(1, target); 
		
		return result;
	}
	
	private void siftDown(int idx, E target) {
		
		if(comparator != null) {
			siftDownComparator(idx, target, comparator);
		} 
		else {
			siftDownComparable(idx, target);
		}
	}
	
	// Comparator을 이용한 sift-down
	@SuppressWarnings("unchecked")
	private void siftDownComparator(int idx, E target, Comparator<? super E> comp) {
		
		array[idx] = null; // 삭제할 인덱스의 노드를 삭제
		size--;
		
		int parent = idx; // 삭제노드부터 시작할 부모를 가리키는 변수
		int child; // 교환 될 자식을 가리키는 변수
		
		// 왼쪽 자식 노드의 인덱스가 요소의 개수보다 작을 때 까지 반복
		while((child = getLeftChild(parent)) <= size) {
			
			int right = getRightChild(parent); // 오른쪽 자식 인덱스
			
			Object childVal = array[child]; // 왼쪽 자식의 값(교환 될 값)
			
			if(right <= size && comp.compare((E) childVal, (E) array[right]) > 0) {
				
				child = right;
				childVal = array[child];
			}
			
			// 재배치 할 노드가 자식 노드보다 작을 경우 반복문을 종료한다.
			if(comp.compare(target, (E) childVal) <= 0) {
				break;
			}
			
			array[parent] = childVal;
			parent = child;
		}
		
		array[parent] = target;
		
		/*
		 * 용적의 사이즈가 최소 용적보다는 크면서 요소의 개수가 전체 용적의 1/4일 경우
		 * 용적을 반으로 줄임(단, 최소용적보단 커야함)
		 */
		if(array.length > DEFAULT_CAPACITY && size < array.length / 4) {
			resize(Math.max(DEFAULT_CAPACITY, array.length / 2));
		}
	}
	
	// Comparable을 이용한 sift-down
	@SuppressWarnings("unchecked")
	private void siftDownComparable(int idx, E target) {
		
		Comparable<? super E> comp = (Comparable<? super E>) target;
		
		array[idx] = null;
		size--;
		
		int parent = idx;
		int child;
		
		while((child = getLeftChild(parent)) <= size) {
			
			int right = getRightChild(parent);
			
			Object childVal = array[child];
			
			if(right <= size && ((Comparable<? super E>) childVal).compareTo((E) array[right]) > 0) {
				child = right;
				childVal = array[child];
			}
			
			if(comp.compareTo((E) childVal) <= 0) {
				break;
			}
			
			array[parent] = childVal;
			parent = child;
		}
		
		array[parent] = comp;
		
		if(array.length > DEFAULT_CAPACITY && size < array.length / 4) {
			resize(Math.max(DEFAULT_CAPACITY, array.length / 2));
		}
	}
	
	/*
	 * [size, peek, isEmpty, toArray]
	 */
	public int size() {
		return this.size;
	}
	
	@SuppressWarnings("unchecked")
	public E peek() {
		if(array[1] == null) {
			throw new NoSuchElementException();
		}
		
		return (E) array[1];
	}
	
	public boolean isEmpty() {
		return size == 0;
	}
	
	public Object[] toArray() {
		return Arrays.copyOf(array, size + 1);
	}
}
