package Heap;

public class Student implements Comparable<Student> {
	String name;
	int age;
	
	Student(String name, int age){
		this.name = name;
		this.age = age;
	}
	
	public String toString() {
		return "이름 : " + name + "\t나이 : " + age;
	}
	
	@Override
	public int compareTo(Student o) {
		
		if(this.name.compareTo(o.name) == 0) {
			return this.age - o.age;
		}
		
		return this.name.compareTo(o.name);
	}
}
