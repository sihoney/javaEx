package DLinkedList;

import java.util.Comparator;

public class Test {
	public static void main(String[] args) throws CloneNotSupportedException {
		
		DLinkedList<Student> list = new DLinkedList<>();
		 
		list.add(new Student("김자바", 92));
		list.add(new Student("이시플", 72));
		list.add(new Student("조시샵", 98));
		list.add(new Student("파이손", 51));
		
		//list.sort(customComp);
		list.sort();
		
		for(int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}
		
		/*
		DLinkedList<Integer> original = new DLinkedList<>();
		original.add(10);	// original에 10추가 
		 
		DLinkedList<Integer> copy = original;
		@SuppressWarnings("unchecked")
		DLinkedList<Integer> clone = (DLinkedList<Integer>) original.clone();
		 
		copy.add(20);	// copy에 20추가
		clone.add(30);	// clone에 30추가
		 
		System.out.println("original list");
		for(int i = 0; i < original.size(); i++) {
			System.out.println("index " + i + " data = " + original.get(i));
		}
		 
		System.out.println("\ncopy list");
		for(int i = 0; i < copy.size(); i++) {
			System.out.println("index " + i + " data = " + copy.get(i));
		}
		 
		System.out.println("\nclone list");
		for(int i = 0; i < clone.size(); i++) {
			System.out.println("index " + i + " data = " + clone.get(i));
		}
		 
		System.out.println("\noriginal list reference : " + original);
		System.out.println("copy list reference : " + copy);
		System.out.println("clone list reference : " + clone);
		*/
	}
	
	static Comparator<Student> customComp = new Comparator<Student>() {
		
		public int compare(Student o1, Student o2) {
			return o2.score - o1.score;
		}
	};
}
