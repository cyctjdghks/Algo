import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

// 3. BOJ 14502 연구소
// BOJ 14502. 연구소
public class Main {

	static int N, M;
	static int[][] map;
	static int[][] map_copy;
	static ArrayList<Data> safe_zone;
	static ArrayList<Data> virus_zone;
	static boolean[] list_v;
	static int[] comb;
	static int max;
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	// 상 하 좌 우

	public static void main(String[] args) {
		// N*M 연구소
		// 바이러스는 상하좌우
		// 벽은 3개
		// 0은 빈 칸, 1은 벽, 2는 바이러스가 있는 곳
		// 순서 상관 X 벽을 세울 3곳 찾기 => 조합

		Scanner sc = new Scanner(System.in);

		// 입력
		N = sc.nextInt();
		M = sc.nextInt();
		map = new int[N][M];
		map_copy = new int[N][M];
		safe_zone = new ArrayList<Data>();
		virus_zone = new ArrayList<Data>();
		comb = new int[3];
		max = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				map[i][j] = sc.nextInt();

				if (map[i][j] == 0) {
					safe_zone.add(new Data(i, j));
				}
				if (map[i][j] == 2) {
					virus_zone.add(new Data(i, j));
				}
			}
		}
		list_v = new boolean[safe_zone.size()];

//		// 멥 확인
//		for(int i = 0; i < N; i++) {
//			for(int j = 0; j < M; j++) {
//				System.out.print(map[i][j] + " ");
//			}
//			System.out.println();
//		}
		
		// 벽 세울 3곳 찾기 - 조합
		comb(0, safe_zone.size(), 3);
		
		System.out.println(max);
	}

	public static void comb(int start, int n, int r) {
		if (r == 0) {
//			System.out.println(Arrays.toString(comb));
			// 찾았으면 해당 구역들 1로 채우기
			fill();
			return;
		}

		for (int i = start; i < n; i++) {
			list_v[i] = true;
			comb[2-(r-1)] = i;
			// 2 > 0
			// 1 > 1
			// 0 > 2
			comb(i + 1, n, r - 1);
			list_v[i] = false;
		}
	}
	
	public static void fill() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				map_copy[i][j] = map[i][j];
			}
		}
		
		for(int i = 0; i < 3; i++) {
			Data data = safe_zone.get(comb[i]);
			
			map_copy[data.x][data.y] = 1;
		}
		
		bfs();
	}
	
	public static void bfs() {
		Queue<Data> q = new LinkedList<Data>();
		for(int i = 0; i < virus_zone.size(); i++) {
			q.offer(virus_zone.get(i));
		}
		
		// 바이러스 구역 2로 채우기
		while(!q.isEmpty()) {
			Data data = q.poll();
			
			for(int d = 0; d < 4; d++) {
				int nx = data.x + dx[d];
				int ny = data.y + dy[d];
				
				if(isInBound(nx, ny)) {
					if(map_copy[nx][ny] == 0) {
						map_copy[nx][ny] = 2;
						q.offer(new Data(nx, ny));
					}
				}
			}
		}
		
		int count = 0;
		// 다 채워진 연구소 안전구역 합 구하기
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(map_copy[i][j] == 0) {
					count++;
				}
			}
		}
		
		max = Math.max(max, count);
	}
	
	public static boolean isInBound(int x, int y) {
		return x >= 0 && x < N && y >= 0 && y < M;
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
