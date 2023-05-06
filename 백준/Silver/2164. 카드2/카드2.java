import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		// N 장의 카드
		// 1 ~ N까지의 번호가 붙어있다
		// 1번이 제일 위에
		// 제일 위에 카드를 바닥에 버림
		// 그 다음 제일 위에 카드를 아래로 옮김
		
		int N;
		Queue<Integer> cards = new LinkedList<>();
		
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		
		// 첫번째 값이 가장 위에 있는 카드(숫자)
		for(int i = 1; i <= N; i++) {
			cards.add(i);
		}
		
		// 마지막 값 1개가 남을때까지 반복
		while(cards.size() > 1) {
			cards.poll(); // 제일 위에 값 삭제
			cards.add(cards.poll()); // 제일 위에 값을 삭제하면서 그 값을 제일 뒤에 add
		}
		
		System.out.println(cards.peek());
	}

}
