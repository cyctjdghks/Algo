import java.util.Scanner;

public class Main {
	static int[][] Field;
	static boolean[][] visit;
	static int M, N;
	static int worm;
	static int[] dx = {0, -1, 0, 1};
	static int[] dy = {1, 0, -1, 0};
	// dxdy 0 - 오른쪽
	// dxdy 1 - 위
	// dxdy 2 - 왼쪽
	// dxdy 3 - 아래

	public static void main(String[] args) {
		// 첫 줄에는 테이트케이스 T
		// 배추밭의 가로길이 M, 세로길이 M
		// 심어져있는 배추의 갯수
		// 배추의 x와 y 좌표
		// 너비 우선 탐색(BFS), 깊이 우선 탐색(DFS)
		// BFS - 재귀적으로 동작x, 방문여부 확인, 큐(Queue) 사용
		// DFS - 자기 자신을 호출하는 순환 알고리즘의 형태

		int T; // 테스트케이스 횟수
		int count; // 입력 받을 배추 갯수
		int x, y; // 심을 배추의 x,y 좌표

		Scanner sc = new Scanner(System.in);
		T = sc.nextInt();

		for (int test_case = 1; test_case <= T; test_case++) {
			worm = 0;

			M = sc.nextInt();
			N = sc.nextInt();
			count = sc.nextInt();

			Field = new int[N][M];
			visit = new boolean[N][M];
			// Field 배열 입력값들은 1로 초기화
			for (int i = 0; i < count; i++) {
				y = sc.nextInt();
				x = sc.nextInt();
				Field[x][y] = 1;
			}

			// Field 배열 확인
//			for(int i = 0; i < N; i ++) {
//				for(int j = 0; j < M; j++) {
//					System.out.print(Field[i][j] + " ");
//				}
//				System.out.println();
//			}

			// 탐색
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (Field[i][j] == 1 && !visit[i][j]) {
						dfs(i,j);
						worm++;
					}
				}
			}

			System.out.println(worm);
		}
	}
	static void dfs(int x, int y) {
		visit[x][y] = true;

		for (int i = 0; i < 4; i++) {
			int cx = x + dx[i];
			int cy = y + dy[i];

			if (cx >= 0 && cy >= 0 && cx < N && cy < M) {
				if (Field[cx][cy] == 1 && !visit[cx][cy]) {
					dfs(cx, cy);
				}
			}
		}
	}
}