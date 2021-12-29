package SLinkedList;

public class Node<E> {
	
	//////////// 
	// FIELD 
	////////////
	
	E data;
	Node<E> next;
	
	//////////////////
	// CONSTRUCTOR
	//////////////////
	
	Node(E data) {
		this.data = data;
		this.next = null;
	}
}
