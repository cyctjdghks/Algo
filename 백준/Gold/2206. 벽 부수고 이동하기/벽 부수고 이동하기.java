import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {

	static int N, M;
	static int[][] map;
	static boolean[][][] v;
	static int res;
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };

	public static void main(String[] args) {
		// N * M 행렬
		// 0은 이동 가능 1은 벽
		// 1개까지는 부술 수 있음
		// 0,0 > N-1, M-1 까지 최단경로

		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		M = sc.nextInt();
		map = new int[N][M];
		v = new boolean[N][M][2];
		res = 0;

		String str;
		for (int i = 0; i < N; i++) {
			str = sc.next();
			for (int j = 0; j < M; j++) {
				map[i][j] = Character.getNumericValue(str.charAt(j));
			}
		}

//		// 배열 확인
//		for (int i = 0; i < N; i++) {
//			for (int j = 0; j < M; j++) {
//				System.out.print(map[i][j] + " ");
//			}
//			System.out.println();
//		}

		bfs();

		if (v[N - 1][M - 1][0] == true || v[N - 1][M - 1][1] == true) {
			System.out.println(res);
		} else {
			System.out.println(-1);
		}
	}

	public static void bfs() {
		Queue<Data> q = new LinkedList<>();
		q.offer(new Data(0, 0, 1, 0));
		v[0][0][0] = true;
		v[0][0][1] = true;

		Data cur;
		while (!q.isEmpty()) {
			cur = q.poll();
			if (cur.r == N - 1 && cur.c == M - 1) {
				res = cur.cnt;
				return;
			}

			int nx, ny;
			switch (cur.wall) {
			case 0: // 벽 부수기 전
				for (int i = 0; i < 4; i++) {
					nx = cur.r + dx[i];
					ny = cur.c + dy[i];

					if (isInBound(nx, ny)) {
						if (v[nx][ny][0] == false) {
							if (map[nx][ny] == 0) {
								q.offer(new Data(nx, ny, cur.cnt + 1, 0));
								v[nx][ny][0] = true;
								v[nx][ny][1] = true;
							} else if (map[nx][ny] == 1) {
								q.offer(new Data(nx, ny, cur.cnt + 1, 1));
								v[nx][ny][1] = true;
							}
						}

					}
				}
				break;
			case 1: // 벽 부순 후
				for (int i = 0; i < 4; i++) {
					nx = cur.r + dx[i];
					ny = cur.c + dy[i];

					if (isInBound(nx, ny) && v[nx][ny][1] == false && map[nx][ny] == 0) {
						q.offer(new Data(nx, ny, cur.cnt + 1, 1));
						v[nx][ny][1] = true;
					}
				}
				break;
			default:
				break;
			}
		}
	}

	public static boolean isInBound(int r, int c) {
		return r >= 0 && r < N && c >= 0 && c < M;
	}

	static class Data {
		int r, c;
		int cnt;
		int wall;

		public Data(int r, int c, int cnt, int wall) {
			this.r = r;
			this.c = c;
			this.cnt = cnt;
			this.wall = wall;
		}
	}
}
