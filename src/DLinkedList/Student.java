package DLinkedList;

public class Student implements Comparable<Student>{
	
	String name;
	int score;
	
	public Student(String name, int score) {
		super();
		this.name = name;
		this.score = score;
	}

	@Override
	public String toString() {
		return "Student [name=" + name + ", score=" + score + "]";
	}
	
	public int compareTo(Student o) {
		return o.score -this.score;
	}
	
}
