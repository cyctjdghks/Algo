import java.util.Arrays;
import java.util.Scanner;

public class Solution {

	static int N;
	static int[][] map;
	static boolean[] v; // 디저트 종류는 1 ~ 100
	static int res;
	static int[] dx = { 1, 1, -1, -1 };
	static int[] dy = { 1, -1, -1, 1 };
	// 우측하단 좌측하단 좌측상단 우측상단

	public static void main(String[] args) {
		// 4*4 멥일때
		// 스타트 지점을 0,1 0,2 1,1 1,2 에서만 시작지점을 잡으면 되지 않나???
		// => j == 0 일때와 j == N-1 을 제외하고
		// => i >= N-2 일때 제외하는게 맞지 않나 싶지만?
		// 이렇게 가지치면 틀림
		
		int T;

		Scanner sc = new Scanner(System.in);

		T = sc.nextInt();

		for (int tc = 1; tc <= T; tc++) {
			N = sc.nextInt();
			map = new int[N][N];
			res = -1;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					map[i][j] = sc.nextInt();
				}
			}

//			print();

			for (int i = 0; i < N-2; i++) {
				for (int j = 1; j < N-1; j++) {
//					System.out.println(i + " " + j);
					v = new boolean[100 + 1];
					dfs(i, j, i, j, 0, 0);
				}
			}

			System.out.println("#" + tc + " " + res);
		}
	}

	private static void dfs(int stx, int sty, int x, int y, int dir, int cnt) {
		v[map[x][y]] = true;
		cnt += 1;

		// 방법 1. 방향 유지하여 그대로 쭉감
		if (isInBound(x + dx[dir], y + dy[dir])) {
			if(x + dx[dir] == stx && y + dy[dir] == sty) {
				res = Math.max(res, cnt);
//				System.out.println(dir);
//				System.out.println(Arrays.toString(v));
				return;
			}

			if (!v[map[x + dx[dir]][y + dy[dir]]]) {
				dfs(stx, sty, x + dx[dir], y + dy[dir], dir, cnt);
				v[map[x + dx[dir]][y + dy[dir]]] = false;
			}
		}

		// 방법 2. 방향을 꺾어서 감
		if (dir < 3) {
			dir += 1;
			if (isInBound(x + dx[dir], y + dy[dir])) {
				if(x + dx[dir] == stx && y + dy[dir] == sty) {
					res = Math.max(res, cnt);
//					System.out.println(dir);
//					System.out.println(Arrays.toString(v));
					return;
				}

				if (!v[map[x + dx[dir]][y + dy[dir]]]) {
					dfs(stx, sty, x + dx[dir], y + dy[dir], dir, cnt);
					v[map[x + dx[dir]][y + dy[dir]]] = false;
				}
			}
		}
	}

	public static boolean isInBound(int x, int y) {
		return x >= 0 && x < N && y >= 0 && y < N;
	}

	private static void print() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}

}
