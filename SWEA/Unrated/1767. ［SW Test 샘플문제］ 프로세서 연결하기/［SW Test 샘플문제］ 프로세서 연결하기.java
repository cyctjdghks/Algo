import java.util.ArrayList;
import java.util.Scanner;

public class Solution {

	static int N;
	static int[][] map;
	static ArrayList<Data> cores;
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };
	// 상 하 좌 우
	static int core;
	static int res;

	public static void main(String[] args) {

		int T;

		Scanner sc = new Scanner(System.in);

		T = sc.nextInt();

		for (int tc = 1; tc <= T; tc++) {
			N = sc.nextInt();
			map = new int[N][N];
			cores = new ArrayList<Data>();
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					map[i][j] = sc.nextInt();
					if (map[i][j] == 1) {
						if (i == 0 || i == N - 1 || j == 0 || j == N - 1)
							continue;
						cores.add(new Data(i, j));
					}
				}
			}
			core = 0;
			res = Integer.MAX_VALUE;

//			print();

			dfs(0, 0);

			System.out.println("#" + tc + " " + res);
		}
	}

	// dfs 수행
	private static void dfs(int index, int c) {
		if (index == cores.size()) { // 끝까지 다 그렸으면 갯수 세보기
			if (core < c) {
				core = c;
				res = count();
			} else if (core == c) {
				res = Math.min(res, count());
			}

			return;
		}

		for (int d = 0; d < 4; d++) {
			if (isPossible(cores.get(index).x, cores.get(index).y, d)) {
				fill(cores.get(index).x, cores.get(index).y, d, 2);
//				print();
				dfs(index + 1, c + 1);
				fill(cores.get(index).x, cores.get(index).y, d, 0);
			}
		}
		dfs(index + 1, c);

	}

	private static void fill(int x, int y, int d, int num) {
		int nx, ny;
		nx = x + dx[d];
		ny = y + dy[d];

		while (isInBound(nx, ny)) {
			map[nx][ny] = num;

			nx += dx[d];
			ny += dy[d];
		}
	}

	// 가능한가 체크
	private static boolean isPossible(int x, int y, int d) {

		int nx, ny;
		nx = x + dx[d];
		ny = y + dy[d];

		while (isInBound(nx, ny)) {
			if (map[nx][ny] != 0) {
				return false;
			}

			nx += dx[d];
			ny += dy[d];
		}

		return true;
	}

	public static boolean isInBound(int x, int y) {
		return x >= 0 && x < N && y >= 0 && y < N;
	}

	public static int count() {
		int cnt = 0;

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] == 2) {
					cnt++;
				}
			}
		}
		return cnt;
	}

	public static void print() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}

	static class Data {
		int x, y;

		public Data(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}
}
