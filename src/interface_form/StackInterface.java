// 출처: stranger's lab
/*
 * [stack의 활용]
 * 1. 페이지 뒤로 가기
 * 2. 실행 취소
 * 3. 수식 괄호 검사
 */

package interface_form;

public interface StackInterface<E> {

	// 스택의 맨 위에 요소를 추가
	E push(E item);
	
	// 스택 맨 위에 있는 요소를 제거하고 제거 된 요소를 반환
	E pop();
	
	// 스택의 맨 위에 있는 요소를 제거하지 않고 반환
	E peek();
	
	// 스택의 상반부터 특정 요소가 몇 번째 위치에 있는지를 반환
	// 중복되는 원소가 있을 경우 가장 위에 있는 요소의 위치를 반환
	int search(Object value);
	
	int size();
	
	void clear();
	
	boolean empty();
}
