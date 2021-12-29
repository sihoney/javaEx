package Stack;

public class Student implements Comparable<Student> {

	String name;
	public int score;
	
	public Student(String name, int score) {
		this.name = name;
		this.score = score;
	}
	
	public String toString() {
		return "name: " + name + "\tscore: " + score;
	}
	
	public int compareTo(Student o) {
		return o.score - this.score;
	}
}
