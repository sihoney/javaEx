/*
 * stack은 vector를 상속받는다. vector은 arrayList와 구조가 유사(동기화 지원 유무의 차이)
 * 우리가 앞서 구현했던 arrayList를 상속하여 써보자!
 * 
 * size() 메소드는 부모 클래스와 이를이 같으므로 따로 구현하지 않아도 된다.
 * 
 * arrayList에 구현되어 있는 메소드들을 활용하면 끝난다!
 */

package Stack;

import java.util.EmptyStackException;

import ArrayList.ArrayList;
import interface_form.StackInterface;

public class StackExtendArrayList<E> extends ArrayList<E> implements StackInterface<E> {

	public StackExtendArrayList() {	// 초기 용적 할당 X
		super();
	}

	public StackExtendArrayList(int capacity) {	// 초기 용적 할당 O
		super(capacity);
	}
	
	@Override
	public E push(E item) {
		addLast(item);
		return item;
	}

	@Override
	public E pop() {
		int length = size();
		E obj = remove(length - 1);
		
		return obj;
	}

	@Override
	public E peek() {
		
		int length = size();
		if(length == 0) {
			throw new EmptyStackException();
		}
		
		E obj = get(length - 1);
		
		return obj;
	}

	@Override
	public int search(Object value) {
		
		int idx = lastIndexOf(value);
		
		if(idx >= 0) {
			return size() - idx;
		}
		return -1;
	}

	@Override
	public boolean empty() {
		return size() == 0;
	}
	
}