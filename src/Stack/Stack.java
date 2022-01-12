package Stack;

import java.util.Arrays;
import java.util.Comparator;
import java.util.EmptyStackException;

//출처: stranger's lab

import interface_form.StackInterface;


public class Stack<E> implements StackInterface<E>, Cloneable {

	////////////////////////////
	// field
	////////////////////////////
	private static final int DEFAULT_CAPACITY = 10; // 최소 용적 크기
	private static final Object[] EMPTY_ARRAY = {}; // 빈 배열
	
	private Object[] array; // 요소를 담을 배열
	private int size; // 요소 개수

	////////////////////////////
	// constructor
	////////////////////////////
	// 생성자 (초기 공간 할당 X)
	public Stack() {
		this.array = EMPTY_ARRAY;
		this.size = 0;
	}
	
	// 생성자 (초기 공간 할당 O)
	public Stack(int capacity) {
		this.array = new Object[capacity];
		this.size = 0;
	}

	////////////////////////////
	// method
	////////////////////////////
	
	/*
	 * 용적은 외부에서 마음대로 접근하면 데이터의 손상을 야기할 수 있기 때문에
	 * private로 접근을 제한!
	 */
	
	private void resize() {
		
		// <1> 빈 배열일 경우(capacity is 0)
		if(Arrays.equals(array, EMPTY_ARRAY)) {
			array = new Object[DEFAULT_CAPACITY];
			return;
		}
		
		int arrayCapacity = array.length; // 현재 용적 크기
		
		// <2> 용적이 가득 찰 경우
		if(size == arrayCapacity) {
			int newSize = arrayCapacity * 2;
			
			array = Arrays.copyOf(array, newSize);
			return;
		}
		
		// <3> 용적의 절반 미만으로 요소가 차지하고 있을 경우
		if(size < (arrayCapacity / 2)) {
			int newCapacity = (arrayCapacity / 2);
			
			array = Arrays.copyOf(array, Math.max(DEFAULT_CAPACITY, newCapacity));
			return;
		}
	}

	@Override
	public E push(E item) {
		
		if(size == array.length) {
			resize();
		}
		
		array[size] = item;
		size++;
		
		return item;
	}

	@Override
	public E pop() {
		
		if(size == 0) {
			throw new EmptyStackException();
		}
		
		@SuppressWarnings("unchecked")
		E obj = (E) array[size - 1];
		
		array[size - 1] = null;
		size--;
		resize();
		return obj;
	}

	@SuppressWarnings("unchecked")
	@Override
	public E peek() {
		
		if(size == 0) {
			throw new EmptyStackException();
		}
		
		return (E) array[size - 1];
	}

	@Override
	public int search(Object value) {
		
		for(int idx = size - 1; idx >= 0; idx--) {
			
			if(array[idx].equals(value)) {
				return size - idx;
			}
		}
		
		return -1;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public void clear() {
		
		for(int i = 0; i < size; i++) {
			array[i] = null;
		}
		
		size = 0; 
		resize();
	}

	@Override
	public boolean empty() {
		return size == 0;
	}
	
	/******************************************
	 * 부가목록 - clone, toArray, sort 메소드
	 ******************************************/
	
	@Override
	public Object clone() throws CloneNotSupportedException {
		
		Stack<?> cloneStack = (Stack<?>) super.clone();
		
		cloneStack.array = new Object[size];
		
		System.arraycopy(array, 0, cloneStack, 0, size);
		return cloneStack;
	}

	public Object[] toArray() {
		return Arrays.copyOf(array, size);
	}
	
	@SuppressWarnings("unchecked")
	public <T> T[] toArray(T[] a) {
		if(a.length < size) {
			return (T[]) Arrays.copyOf(array, size, a.getClass());
			// 상위타입에 대해서도 담을 수 있도록 하기 위해 copyOf 메소드에서
			// Class라는 파라미터를 마지막에 넣어준다. 
		}
		
		System.arraycopy(array, 0, a, 0, size);
		
		return a;
	}
	
	public void sort() {
		sort(null);
	}
	
	@SuppressWarnings("unchecked")
	public void sort(Comparator<? super E> c) {
		Arrays.sort((E[]) array, 0, size, c);
	}
}
