package Deque;

public class Student implements Comparable<Student> {
	String name;
	int score;
	
	Student(String name, int score){
		this.name = name;
		this.score = score;
	}
	
	public String toString() {
		return "이름 : " + name + "\t성적 : " + score;
	}
	
	@Override
	public int compareTo(Student o) {
		return o.score - this.score;
	}
}
