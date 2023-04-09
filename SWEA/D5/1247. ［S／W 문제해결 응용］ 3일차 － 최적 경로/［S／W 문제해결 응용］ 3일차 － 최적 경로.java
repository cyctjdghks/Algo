import java.util.Arrays;
import java.util.Scanner;

// 5. SWEA 1247-[S/W 문제해결 응용] 3일차 - 최적 경로
public class Solution {

	static int N;
	static int startX, startY;
	static int endX, endY;
	static int[][] client;
	static int[] output;
	static boolean[] visit;
	static int dis, dis_x, dis_y;
	static int min;

	public static void main(String[] args) {
		// 테스트 케이스 수 T
		// 테스트 케이스 하나씩
		// 고객의 수 N
		// 회사의 좌표 - start
		// 집의 좌표 - end
		// N명의 고객의 좌표
		// 좌표의 값은 0 ~ 100 => arr[101][101]
		// 좌표간의 거리는 |x1-x2| + |y1-y2|
		// 모든 경우의 수
		// 순서는 상관 있음 => 순열

		int T;

		Scanner sc = new Scanner(System.in);

		T = sc.nextInt();

		for (int tc = 1; tc <= T; tc++) {
			N = sc.nextInt();
			startX = sc.nextInt();
			startY = sc.nextInt();
			endX = sc.nextInt();
			endY = sc.nextInt();
			client = new int[N][2];
			output = new int[N];
			visit = new boolean[N];
			min = 9999_9999;

			for (int i = 0; i < N; i++) {
				client[i][0] = sc.nextInt();
				client[i][1] = sc.nextInt();
			}

//			// 배열 확인
//			for(int i = 0; i < N; i++) {
//				for(int j = 0; j < 2; j++) {
//					System.out.print(client[i][j] + " ");
//				}
//				System.out.println();
//			}

			// N에 대한 순열 찾기
			perm(N, 0);
			
			System.out.println("#" + tc + " " + min);
		}
	}

	// 순열로 거리 구할 순서 정하기
	public static void perm(int n, int depth) {
		if (depth == n) {
			distance();
			return;
		}

		for (int i = 0; i < N; i++) {
			if (!visit[i]) {
				visit[i] = true;
				output[depth] = i;
				perm(n, depth + 1);
				visit[i] = false;
			}
		}
	}

	// 거리 구하기
	public static void distance() {
		dis_x = Math.abs(startX - client[output[0]][0]);
		dis_y = Math.abs(startY - client[output[0]][1]);
		dis = dis_x + dis_y;
		for(int i = 0; i < N-1; i++) {
			dis_x = Math.abs(client[output[i]][0] - client[output[i+1]][0]);
			dis_y = Math.abs(client[output[i]][1] - client[output[i+1]][1]);
			dis += dis_x + dis_y;
		}
		dis_x = Math.abs(client[output[N-1]][0] - endX);
		dis_y = Math.abs(client[output[N-1]][1] - endY);
		dis += dis_x + dis_y;
		
		min = Math.min(min, dis);
	}
}
