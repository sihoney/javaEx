package Deque;

import java.util.Comparator;

public class Test2 {
	public static void main(String[] args) {
		 
		LinkedListDeque<Student> dq = new LinkedListDeque<>();
		
		dq.offer(new Student("Chen" , 89));
		dq.offer(new Student("Malli" , 65));
		dq.offer(new Student("Tomas" , 53));
		dq.offer(new Student("Cris" , 79));
		dq.offer(new Student("Julia" , 93));
		
		dq.sort(customComp);
		
		for(Object a : dq.toArray()) {
			System.out.println(a);
		}
	}
	
	static Comparator<Student> customComp = new Comparator<Student>() {
		public int compare(Student o1, Student o2) {
			return o2.score - o1.score; 
		}
	};
}
