import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Solution {

	static int N, M, R, C, L;
	static int[][] map;
	static boolean[][] v;
	static int res;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
	// 상 우 하 좌
	static int[][] block_hole = {
			{},
			{1,1,1,1},
			{1,0,1,0},
			{0,1,0,1},
			{1,1,0,0},
			{0,1,1,0},
			{0,0,1,1},
			{1,0,0,1}};

	public static void main(String[] args) {
		// 숫자 1 ~ 7은 해당 위치의 터널 구조물 타입을 의미하며 숫자 0 은 터널이 없는 장소

		int T;

		Scanner sc = new Scanner(System.in);

		T = sc.nextInt();

		for (int tc = 1; tc <= T; tc++) {

			N = sc.nextInt();
			M = sc.nextInt();
			R = sc.nextInt();
			C = sc.nextInt();
			L = sc.nextInt();
			map = new int[N][M];
			v = new boolean[N][M];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					map[i][j] = sc.nextInt();
				}
			}
			res = 0;

//			// 멥 확인
//			for(int i = 0; i < N; i++) {
//				for(int j = 0; j < M; j++) {
//					System.out.print(map[i][j] + " ");
//				}
//				System.out.println();
//			}

			bfs();

			System.out.println("#" + tc + " " + res);
		}
	}

	private static void bfs() {
		Queue<Data> q = new LinkedList<Data>();

		q.offer(new Data(R, C, 1));
		v[R][C] = true;
		res++;

		while (!q.isEmpty()) {
			Data data = q.poll();
			int nx, ny;
			
			int block = map[data.x][data.y];
			
			for(int d = 0; d < 4; d++) {
				if(block_hole[block][d] != 1) continue;
				
				nx = data.x + dx[d];
				ny = data.y + dy[d];
				
				if(isInBound(nx, ny) && !v[nx][ny] && map[nx][ny] > 0
						&& data.time < L && block_hole[map[nx][ny]][(d+2)%4]==1) {
					q.offer(new Data(nx, ny, data.time + 1));
					v[nx][ny] = true;
					res++;
				}
			}
		}
	}
	
	public static boolean isInBound(int x, int y) {
		return x >= 0 && x < N && y >= 0 && y < M;
	}

	static class Data {
		int x, y;
		int time;

		public Data(int x, int y, int time) {
			super();
			this.x = x;
			this.y = y;
			this.time = time;
		}
	}

}
