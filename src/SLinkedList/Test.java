package SLinkedList;

import java.util.Comparator;

public class Test {
	public static void main(String[] args) {
		
		SLinkedList<Student> list = new SLinkedList<>();
		
		list.add(new Student("김자바", 82));
		list.add(new Student("이시플", 72));
		list.add(new Student("조시샵", 98));
		list.add(new Student("파이손", 51));
		
		//list.sort(customComp);
		list.sort();
		
		for(int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}
	}
	
	/*
	// 사용자 설정 comparator 
	static Comparator<Student> customComp = new Comparator<Student>() {
		@Override
		public int compare(Student o1, Student o2) {
			return o2.score - o1.score;
		}
	};
	*/
}
