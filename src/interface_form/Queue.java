package interface_form;

public interface Queue<E> {

	/*
	 * offer()은 add()와 비슷한 역할이고
	 * poll()의 경우 remove()와 비슷한 역할이며
	 * peek()은 element()와 비슷한 역할이다.
	 * 
	 * 다만 차이점은 add(), remove(), element()는 내부적으로 예외를 처리하고 있다.
	 * 하지만 offer(), peek(), poll()의 경우 예외를 던지는 것이 아닌 특별한 값을 던지는데,
	 * 
	 * 일반적으로 null 또는 false를 던진다고 생각하면 된다.
	 */
	
	boolean offer(E e);
	
	E poll();
	
	E peek();
}
