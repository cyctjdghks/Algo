import java.io.IOException;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static int max = Integer.MIN_VALUE;
	static int[][] map;
	static boolean[][] v;

	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };
	// 상 하 좌 우

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		map = new int[N][M];
		v = new boolean[N][M];

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				map[i][j] = sc.nextInt();
			}
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				v[i][j] = true;
				dfs(i, j, 1, map[i][j]);
				v[i][j] = false;

				combi(0, 0, i, j, map[i][j]); // 인접한 4칸 중 3칸 고르기(ㅗ,ㅓ,ㅏ,ㅜ 모양)
			}
		}

		System.out.println(max);

	}

	private static void combi(int cnt, int start, int x, int y, int sum) {
		if (cnt == 3) {
			max = Math.max(max, sum);
			return;
		}

		for (int d = start; d < 4; d++) {
			int nx = x + dx[d];
			int ny = y + dy[d];

			if (isInBound(nx, ny)) {
				combi(cnt + 1, d + 1, x, y, sum + map[nx][ny]);
			}
		}
	}

	private static void dfs(int x, int y, int cnt, int sum) {

		if (cnt == 4) {
			max = Math.max(max, sum);
			return;
		}

		for (int d = 0; d < 4; d++) {
			int nx = x + dx[d];
			int ny = y + dy[d];

			if (isInBound(nx, ny) && !v[nx][ny]) {
				v[nx][ny] = true;
				dfs(nx, ny, cnt + 1, sum + map[nx][ny]);
				v[nx][ny] = false;
			}
		}
	}

	public static boolean isInBound(int x, int y) {
		return x >= 0 && x < N && y >= 0 && y < M;
	}
}