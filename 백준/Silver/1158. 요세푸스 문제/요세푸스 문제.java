import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		// N 명의 사람
		// 방법 1
		// input 이 0일때 input += K 번째 사람마다 죽음
		// 만약 K가 N을 넘어가면 input-N
		// input의 위치 사람 죽이고 넘어가기
		// 만약 죽어있다면 패스하고 다음사람
		// => 이미 죽은 사람도 포함시켜 +K 하기떄문에오류
		// 방법 2
		// queue 활용
		// 1 ~ N까지 queue에 저장
		// K-1 만큼 add(poll())
		// K번째는 poll() 하면서 출력
		// size 가 0이 될떄까지 반복 or N번 반복
		
		int N, K;
		Queue<Integer> queue = new LinkedList<>();
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		K = sc.nextInt();
		
		// 초기화
		for(int i = 1; i <= N; i++) {
			queue.add(i);
		}
		
		// 실행 & 출력
		System.out.print("<");
		for(int i = 0; i < N; i++) {
			for(int k = 0; k < K - 1; k++) {
				queue.add(queue.poll());
			}
			if(i == N-1) {
				System.out.print(queue.poll());
			} else {
			System.out.print(queue.poll() + ", ");
			}
		}
		System.out.print(">");
	}

}
