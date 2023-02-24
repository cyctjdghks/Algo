import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Solution {

	static int N;
	static int[][] map;
	static int stx, sty; // A, B;
	static int endx, endy; // C, D;
	static int[][] v;
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };
	// 상 하 좌 우
	static int res;

	public static void main(String[] args) {
		// 섬과 같은 장애물은 지도에서 1로 표시, 소용돌이 같은 장애물은 2로 표시
		// 소용돌이는 생성되고 2초동안 유지되다가 1초동안 잠잠해진다.
		// 예를들어, 0초에 생성된 소용돌이는 0초, 1초까지 유지되고 2초에 사라지게된다.
		// 또한 3초, 4초에는 생성되고 5초에 사라진다.
		// (단 ,한번 통과한 소용돌이 위에서는 머물러 있을 수 있다 )

		int T;

		Scanner sc = new Scanner(System.in);

		T = sc.nextInt();

		for (int tc = 1; tc <= T; tc++) {
			N = sc.nextInt();
			map = new int[N][N];
			v = new int[N][N];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					map[i][j] = sc.nextInt();
					v[i][j] = 9999_9999;
				}
			}
			stx = sc.nextInt();
			sty = sc.nextInt();
			endx = sc.nextInt();
			endy = sc.nextInt();
			res = -1;

//			print();

			bfs();

//			print();
			
			System.out.println("#" + tc + " " + res);
		}
	}

	private static void bfs() {
		Queue<Data> q = new LinkedList<Data>();
		q.offer(new Data(stx, sty, 0));
		v[stx][sty] = 0;

		while (!q.isEmpty()) {
			Data data = q.poll();
			if (data.x == endx && data.y == endy) {
				res = data.time;
			}

			for (int d = 0; d < 4; d++) {
				int nx = data.x + dx[d];
				int ny = data.y + dy[d];

				if (isInBound(nx, ny)) {
					if (map[nx][ny] == 1) {
						continue;
					}

					if (map[nx][ny] == 0 && data.time + 1 < v[nx][ny]) {
						q.offer(new Data(nx, ny, data.time + 1));
						v[nx][ny] = data.time + 1;
					}

					if (map[nx][ny] == 2) {
						int sw = data.time % 3;
						switch (sw) {
						case 0:
							if (data.time + 1 < v[nx][ny]) {
								q.offer(new Data(nx, ny, data.time + 3));
								v[nx][ny] = data.time + 3;
							}
							break;
						case 1:
							if (data.time + 2 < v[nx][ny]) {
								q.offer(new Data(nx, ny, data.time + 2));
								v[nx][ny] = data.time + 2;
							}
							break;
						case 2:
							if (data.time + 1 < v[nx][ny]) {
								q.offer(new Data(nx, ny, data.time + 1));
								v[nx][ny] = data.time + 1;
							}
							break;
						default:
							break;
						}
					}
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

	static class Data {
		int x, y;
		int time;

		public Data(int x, int y, int time) {
			this.x = x;
			this.y = y;
			this.time = time;
		}
	}

}
