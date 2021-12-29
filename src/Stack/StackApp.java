package Stack;

import java.util.Comparator;

public class StackApp {
	public static void main(String[] args) {
		
		// sort
		
		Stack<Student> stack = new Stack<>();
		
		stack.push(new Student("java kim", 92));
		stack.push(new Student("cple lee", 72));
		stack.push(new Student("joshy shap", 98));
		stack.push(new Student("phy thon", 51));
		
		//stack.sort(customComp);
		stack.sort();
		
		for(Object a : stack.toArray()) {
			System.out.println(a);
		}

	}
	/*
	// 사용자 설정 comparator(비교기)
	static Comparator<Student> customComp = new Comparator<Student>() {
		@Override
		public int compare(Student o1, Student o2) {
			return o2.score - o1.score;
		}
	};
	*/
}
