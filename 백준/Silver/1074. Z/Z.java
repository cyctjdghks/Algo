
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		// 시간 제한 0.5초
		// 입력값 N과 상관없이 r행 c열의 값은 항상 같다.
		// => 행렬 만드는데 시간 쓸 필요가 x
		// r, c를 찾아야됨.
		// 2 - 4 - 8 - 16 순으로 2의 n승만큼 구역이 정해져잇음
		// len = 2^N
		// r과 c를 입력 받으면 len과 비교해서(len / 2) 가장 큰 구역에서 어느 구역인지 찾기
		// z 순대로 0 1 2 3 구역
		// A = (len/2 * len/2)
		// 0번째 구역이면 (0, 0) +0
		// 1번째 구역이면 (len/2, 0) +A
		// 2번째 구역이면 (0, len/2) +2A
		// 3번째 구역이면 (len/2, len/2)+3A
		// 다음 탐색할 N은 N/2
		// 1이 될떄까지 반복
		// 해당 값 출력
		// 반복
		
		int N;
		int r, c;
		int len;
		int A;
		
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		r = sc.nextInt();
		c = sc.nextInt();
		A = 0;
		len = (int) Math.pow(2, N);
		search(len, r, c, A);
	}
	
	public static void search(int len, int r, int c, int A) {
		if(len == 1) {
			System.out.println(A);
			return;
		}
		
		if(r < (len/2) && (c < len/2)) { // 0 번구역
			A += 0;
			search(len/2, r, c, A);
		} else if (r < (len/2) && c >= (len/2)) { // 1번 구역
			A += 1 * (len/2 * len/2);
			search(len/2, r, c - len/2, A);
		} else if (r >= (len/2) && c < (len/2)) { // 2번 구역
			A += 2 * (len/2 * len/2);
			search(len/2, r - len/2, c, A);
		} else if(r >= (len/2) && c >= (len/2)) { // 3번 구역
			A += 3 * (len/2 * len/2);
			search(len/2, r - len/2, c - len/2, A);
		}
	}

}
