import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Solution {

	static ArrayList<Integer>[] list;
	static boolean visit[];
	static int max;

	public static void main(String[] args) {
		// 데이터 길이와 시작점
		// from - to 순서로 데이터 입력
		// 비상 연락망이 퍼졌을때 가장 큰 수
		// 연락 인원 최대 100명
		// 중간 중간에 비어있는 번호 존재
		// 문제에는 적혀있지 않지만 tc 는 10
		// 최대 깊이에서의 가장 큰 숫자

		int T = 10;
		int data_length, start;
		int from, to;

		Scanner sc = new Scanner(System.in);

		for (int tc = 1; tc <= T; tc++) {
			list = new ArrayList[100 + 1];
			visit = new boolean[100 + 1];
			for (int i = 0; i <= 100; i++) {
				list[i] = new ArrayList<Integer>();
			}
			max = 0;

			data_length = sc.nextInt();
			start = sc.nextInt();

			for (int i = 0; i < data_length / 2; i++) {
				from = sc.nextInt();
				to = sc.nextInt();

				list[from].add(to);
			}

			bfs(start);

			System.out.println("#" + tc + " " + max);
		}
	}

	static void bfs(int start) {
		Queue<Integer> queue = new LinkedList<>();

		queue.offer(start);
		visit[start] = true;
		int depth = 0;

		while (!queue.isEmpty()) {
			int size = queue.size();
			max = 0;
//			System.out.println("현제의 depth : " + depth);
			for (int i = 0; i < size; i++) {
				int cur = queue.poll();
				max = Math.max(max, cur);
//				System.out.println("현제 위치 : " + cur);
				for (Integer idx : list[cur]) {
					if (visit[idx]) {
						continue;
					}
					queue.offer(idx);
					visit[idx] = true;
				}
			}
//			System.out.println(max);
			depth++;
		}
	}

}
