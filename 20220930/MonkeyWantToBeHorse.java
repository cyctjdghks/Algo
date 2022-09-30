import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

// BOJ 1600 말이 되고픈 원숭이
public class MonkeyWantToBeHorse {

	static int K;
	static int W, H;
	static int[][] map;
	static int[] dx = {0, 0, -1, 1};
	static int[] dy = {-1, 1, 0, 0};
	// 상 하 좌 우
	static int[] hdx = {-2, -1, 1, 2, 2, 1, -1, -2};
	static int[] hdy = {-1, -2, -2, -1, 1, 2, 2, 1};
	// 말 이동
	static boolean[][][] v;
	static int res = -1;
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		K = sc.nextInt();
		W = sc.nextInt();
		H = sc.nextInt();
		map = new int[H][W];
		v = new boolean[H][W][K+1];
		for(int i = 0; i < H; i++) {
			for(int j = 0; j < W; j++) {
				map[i][j] = sc.nextInt();
			}
		}
		
//		// 맵 확인
//		for(int i = 0; i < H; i++) {
//			for(int j = 0; j < W; j++) {
//				System.out.print(map[i][j] + " ");
//			}
//			System.out.println();
//		}
		
		bfs();
		
		System.out.println(res);
		
	}
	
	public static void bfs() {
		// bfs, 큐, data, 방문체크
		// 관리할 큐 생성
		Queue<Data> q = new LinkedList<Data>();
		// 첫번째(모든 시작값)을 큐에 삽입하고 방문체크한다.
		q.offer(new Data(0, 0, 0, 0));
		// 방문체크
		v[0][0][0] = true;
		
		Data cur;
		int nx, ny;
		// 큐가 빌때까지 계속 작업
		while(!q.isEmpty()) {
			// 큐에서 한개 뽑기
			cur = q.poll();
			// 할일 하기 => 현재 좌표가 목표좌표인가 판단
			if(cur.x == H-1 && cur.y == W-1) {
				res = cur.cnt;
				break;
			}
			// 탐색
			// 원숭이 탐색
			for(int i = 0; i < 4; i++) {
				nx = cur.x + dx[i];
				ny = cur.y + dy[i];
				// 범위체크
				if(isInBound(nx, ny)) {
					//방문체크
					if(v[nx][ny][cur.k]) continue;
					// 장애물 체크
					if(map[nx][ny] == 1) continue;
					
					// 큐에 새로운 값 삽입하고 방문처리한다.
					q.offer(new Data(nx, ny, cur.cnt+1, cur.k));
					// 방문체크
					v[nx][ny][cur.k] = true;
				}
			}
			// 말 탐색
			if(cur.k < K) {
				for(int i = 0; i < 8; i++) {
					nx = cur.x + hdx[i];
					ny = cur.y + hdy[i];
					// 범위 체크
					if(isInBound(nx, ny)) {
						//방문체크
						if(v[nx][ny][cur.k+1]) continue;
						// 장애물 체크
						if(map[nx][ny] == 1) continue;
					
						// 큐에 새로운 값 삽입하고 방문처리한다.
						q.offer(new Data(nx, ny, cur.cnt+1, cur.k+1));
						// 방문체크
						v[nx][ny][cur.k+1] = true;
					}
				}
			}
		}
		
	}
	
	public static boolean isInBound(int x, int y) {
		return x >= 0 && x < H && y >= 0 && y < W;
	}
	
	public static class Data {
		int x, y;
		int cnt;
		int k;
		
		public Data(int x, int y, int cnt, int k) {
			super();
			this.x = x;
			this.y = y;
			this.cnt = cnt;
			this.k = k;
		}

		@Override
		public String toString() {
			return "Data [x=" + x + ", y=" + y + ", cnt=" + cnt + ", k=" + k + "]";
		}
		
	}

}
