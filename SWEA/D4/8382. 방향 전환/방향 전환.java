import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

// 4. SWEA 8382 방향 전환(A형 보충)
public class Solution {

	static int x1, y1;
	static int x2, y2;
	static int res;
	static boolean[][][] v;
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };

	public static void main(String[] args) {
		// x1 y1 -> x2 y2
		// 가로 이동했으면 세로이동
		// 세로이동 했으면 가로이동
		// 첫 이동은 어떤 이동 이어도 상관X
		// 테스트 케이스 T

		int T;
		int count;

		Scanner sc = new Scanner(System.in);

		T = sc.nextInt();

		for (int tc = 1; tc <= T; tc++) {
			x1 = sc.nextInt() + 100;
			y1 = sc.nextInt() + 100;
			x2 = sc.nextInt() + 100;
			y2 = sc.nextInt() + 100;
			res = 0;
			v = new boolean[201][201][2];

			bfs();

			System.out.println("#" + tc + " " + res);
		}
	}

	static void bfs() {
		// 큐 만들어서 처음것 집어넣고 방문체크하기
		Queue<Data> q = new LinkedList();
		q.offer(new Data(x1, y1, 0, 0)); // 가로이동
		q.offer(new Data(x1, y1, 0, 1)); // 세로이동
		v[x1][y1][0] = true;
		v[x1][y1][1] = true;
		// 큐가 빌때 까지
		Data cur;
		while (!q.isEmpty()) {
			// 무조건 첫번째것 꺼내기
			cur = q.poll();
			// 할일하기
			if (cur.x == x2 && cur.y == y2) {
				res = cur.cnt;
				return;
			}
			// 현재 진행되던 것에 따른 4방위 탐색
			int nx, ny;
			switch (cur.dir) {
			case 0: // 가로 이동
				for(int d = 2; d < 4; d++) {
					nx = cur.x + dx[d];
					ny = cur.y + dy[d];
					// 배열범위 넘어갔지
					if(nx < 0 || nx >= 201 || ny < 0 || ny >= 201) {
						continue;
					}
					// 이미 방문했니
					if(v[nx][ny][1]) {
						continue;
					}
					// 로직 구현했니
					// 큐에 삽입 방문체크
					q.offer(new Data(nx, ny, cur.cnt + 1, 1)); // 세로이동
					v[nx][ny][1] = true;
				}
				break;
			case 1: // 세로 이동
				for(int d = 0; d < 2; d++) {
					nx = cur.x + dx[d];
					ny = cur.y + dy[d];
					// 배열범위 넘어갔지
					if(nx < 0 || nx >= 201 || ny < 0 || ny >= 201) {
						continue;
					}
					// 이미 방문했니
					if(v[nx][ny][0]) {
						continue;
					}
					// 로직 구현했니
					// 큐에 삽입 방문체크
					q.offer(new Data(nx, ny, cur.cnt + 1, 0)); // 가로이동
					v[nx][ny][0] = true;
				}
				break;
			default:
				break;
			}
		}
	}

	static class Data {
		int x, y;
		int cnt;
		int dir; // 가로(0) 세로(1)

		public Data(int x, int y, int cnt, int dir) {
			this.x = x;
			this.y = y;
			this.cnt = cnt;
			this.dir = dir;
		}

	}

}
