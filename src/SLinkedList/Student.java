package SLinkedList;

public class Student implements Comparable<Student>{

	//////////////
	// field
	//////////////
	String name;
	int score;

	//////////////
	// constructor
	//////////////
	public Student(String name, int score) {
		super();
		this.name = name;
		this.score = score;
	}
	
	//////////////
	// method
	//////////////
	@Override
	public String toString() {
		return "Student [name=" + name + ", score=" + score + "]";
	}

	@Override
	public int compareTo(Student o) {
		return o.score - this.score;
	}
	

}
