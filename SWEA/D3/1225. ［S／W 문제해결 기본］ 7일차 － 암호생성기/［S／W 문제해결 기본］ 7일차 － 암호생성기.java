import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Solution {
	// queue 문제
	public static void main(String[] args) {
		// 첫줄에는 테스트 케이스의 번호
		// 8개의 데이터

		int T = 10;
		int num;
		Queue<Integer> queue = new LinkedList<>();

		Scanner sc = new Scanner(System.in);

		for (int tc = 1; tc <= T; tc++) {
			queue.clear();
			
			num = sc.nextInt();
			for (int i = 0; i < 8; i++) {
				queue.add(sc.nextInt());
			}

			loop :
			while (true) {
				for (int i = 1; i <= 5; i++) {
					if((queue.peek() - i) <= 0 ) {
						queue.poll();
						queue.add(0);
						break loop;
					}else {
						queue.add(queue.poll() - i);
					}
				}
			}
			
			System.out.print("#" + num);
			for(int i = 0; i < 8; i++) {
			System.out.print(" " + queue.poll());
			}
			System.out.println();
		}
	}

}
