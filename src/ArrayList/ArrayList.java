package ArrayList;

import java.util.Arrays;
import interface_form.List;

public class ArrayList<E> implements List<E>, Cloneable{

	////////////////////
	// FIELD
	////////////////////
	
	private static final int DEFAULT_CAPACITY = 10; // 최소 용적 크기
	private static final Object[] EMPTY_ARRAY = {}; // 빈 배열
	
	private int size; // 요소 개수
	
	Object[] array; // 요소를 담을 배열
	
	////////////////////
	// CONSTRUCTOR
	////////////////////
	
	// 생성자 - 1
	public ArrayList() {
		this.array = EMPTY_ARRAY;
		this.size = 0;
	}
	
	// 생성자 - 2
	public ArrayList(int capacity) {
		this.array = new Object[capacity];
		this.size = 0;
	}
	
	////////////////////
	// METHOD 
	////////////////////
	
	private void resize() {
		
		int array_capacity = array.length;
		
		// if array's capacity is 0
		if(Arrays.equals(array, EMPTY_ARRAY)) {
			array = new Object[DEFAULT_CAPACITY];
			return;
		}
		
		// 용량이 꽉 찰 경우
		if(size == array_capacity) {
			int new_capacity = array_capacity * 2;
			
			// copy
			array = Arrays.copyOf(array, new_capacity);
			return; 
		}
		
		// 용적의 절반 미만으로 요소가 차지하고 있을 경우
		if(size < (array_capacity / 2)) {
			int new_capacity = array_capacity / 2;
			
			// copy
			array = Arrays.copyOf(array, Math.max(new_capacity, DEFAULT_CAPACITY));
			return;
		}
	}
	
	@Override
	public boolean add(E value) {
		addLast(value);
		return true;
	}

	public void addLast(E value) {
		
		// 꽉 차있는 상태라면 용적 재할당
		if(size == array.length) {
			resize();
		}
		
		array[size] = value;
		size++;
	}
	
	// 중간 삽입
	@Override
	public void add(int index, E value) {
		
		if(index > size || index < 0) { // 영역을 벗어날 경우 예외 발생
			throw new IndexOutOfBoundsException();
		}
		
		if(index == size) {
			addLast(value);
		}
		else {
			
			if(size == array.length) { // 꽉 차 있다면 용적 재할당
				resize();
			}
			
			// index 기준 후자에 있는 모든 요소들 한 칸씩 뒤로 밀기
			for(int i = size; i > index; i--) {
				array[i] = array[i - 1];
			}
			
			array[index] = value;
			size++;
		}
		
	}
	
	public void addFirst(E value) {
		add(0, value);
	}

	@Override
	public E remove(int index) {
		
		if(index >= size || index < 0) {
			throw new IndexOutOfBoundsException();
		}
		
		@SuppressWarnings("unchecked")
		E element = (E) array[index]; // 삭제될 요소를 반환하기 위해 임시도로 담아둠
		array[index] = null;
		
		// 삭제한 요소 뒤에 있는 모든 요소들을 한 칸 씩 당겨옴
		for(int i = index; i < size; i++) {
			array[i] = array[i + 1];
			array[i + 1] = null;
		}
		
		size--;
		resize();
		return element;
	}
	
	public boolean remove(Object value) {
		
		// 삭제하고자 하는 요소의 인덱스 찾기
		int index = indexOf(value);
		
		// -1이라면 array에 요소가 없다는 의미이므로 false 반환
		if(index == -1) {
			return false;
		}
		
		// index 위치에 있는 요소를 삭제
		remove(index);
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public E get(int index) {
		
		if(index >= size || index < 0) {
			throw new IndexOutOfBoundsException();
		}
		
		// Object 타입에서 E 타입으로 캐스팅 후 반환
		return (E) array[index];
	}

	@Override
	public void set(int index, E value) {
		
		if(index >= size || index < 0) {
			throw new IndexOutOfBoundsException();
		}
		else {
			array[index] = value;
		}
	}

	@Override
	public boolean contains(Object value) {
		
		if(indexOf(value) >= 0) {
			return true;
		} 
		else {
			return false;
		}
	}

	@Override
	public int indexOf(Object value) {

		// value와 같은 객체(요소 값)일 경우 i(위치) 반환
		for(int i = 0; i < size; i++) {
			if(array[i].equals(value)) {
				return i;
			}
		}
		
		// 일치하는 것이 없을 경우 -1을 반환
		return -1;
	}
	
	public int lastIndexOf(Object value) {
		for(int i = size - 1; i >= 0; i--) {
			if(array[i].equals(value)) {
				return i;
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
		
		for(int i = 0; i < size; i++) {
			array[i] = null;
		}
		
		size = 0;
		resize();
	}
	
	/*
	 * 중효한 부분은 아니지만 조금 더 많은 기능을 원할 경우 추가해주면 좋은 메소드들 - clone, toArray
	 * 
	 * clone: 기존에 있던 객체를 파괴하지 않고 요소들이 동일한 객체를 새로 하나 만드는 것
	 * toArray: 리스트를 출력할 때 for-each문을 쓰기 위해
	 * 			(원래 iterable을 implement하여 구현해주면 되지만 컬렉션 공부 취지와 맞지 않기 때문에)
	 */
	
	@Override
	public Object clone() throws CloneNotSupportedException {
		 
		// 새로운 객체 생성
		ArrayList<?> cloneList = (ArrayList<?>)super.clone();
		
		// 새로운 "객체의 배열도 생성"해주어야 함 (객체는 얕은 복사가 되기 때문)
		cloneList.array = new Object[size];
		
		// 배열의 "값을 복사"함
		System.arraycopy(array, 0, cloneList.array, 0, size);
		
		return cloneList;
	}
	
	/*
	 * System.arraycopy(src, srcPos, dest, descPos, length)
	 * 
	 * Object src: 복사하고자 하는 소스(원본)
	 * int srcPos: 원본 소스에서 어느 부분부터 읽어올지 위치 설정
	 * Object dest: 복사할 소스(복사하려는 대상)
	 * int descPos: 어느 부분부터 쓸 것인지 시작 위치 설정
	 * int length: 원본에서 복사본으로 얼마큼 읽어올지 
	 */
	
	public Object[] toArray() {
		return Arrays.copyOf(array, size);
	}
	
	@SuppressWarnings("unchecked")
	public <T> T[] toArray(T[] a) {
		if(a.length < size) {
			return (T[]) Arrays.copyOf(array, size, a.getClass());
		}
		
		System.arraycopy(array, 0, a, 0, size);
		return a;
	}
}
