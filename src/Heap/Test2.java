package Heap;

import java.util.Comparator;

public class Test2 {
	public static void main(String[] args) {
		
		Heap<Student> heap1 = new Heap<Student>();
		Heap<Student> heap2 = new Heap<Student>(comparator);
 
		
		heap1.add(new Student("김자바", 40));
		heap2.add(new Student("김자바", 40));
		
		heap1.add(new Student("이씨프", 27));
		heap2.add(new Student("이씨프", 27));
		
		heap1.add(new Student("조파이", 48));
		heap2.add(new Student("조파이", 48));
		
		heap1.add(new Student("김자바", 18));
		heap2.add(new Student("김자바", 18));
		
		heap1.add(new Student("상스윕", 32));
		heap2.add(new Student("상스윕", 32));
		
		heap1.add(new Student("양씨샵", 27));
		heap2.add(new Student("양씨샵", 27));
		
		
		System.out.println("[Heap 1] : 이름순(같을 경우 나이 오름차순)");
		while(!heap1.isEmpty()) {
			System.out.println(heap1.remove());
		}
		System.out.println();
		
		System.out.println("[Heap 2] : 나이 내림차순(같을 경우 이름순)");
		while(!heap2.isEmpty()) {
			System.out.println(heap2.remove());
		}
		System.out.println();
	}
	
	private static Comparator<Student> comparator = new Comparator<Student>() {
		@Override
		public int compare(Student o1, Student o2) {
			// 나이가 같다면 이름순
			if(o1.age == o2.age) {
				return o1.name.compareTo(o2.name);
			}
			
			return o2.age - o1.age;	// 나이 내림차순
		}
	};
}
