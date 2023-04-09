import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Solution {

	static int N, M;
	static char[][] map;
	static Queue<Data> q_soo;
	static Queue<Data> q_devil;
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	// 상 하 좌 우
	static int res;
	
	public static void main(String[] args) {
		// 수연이의 위치는 ‘S’, 여신의 공간은 ‘D’, 돌의 위치는 ‘X’, 악마는 ‘*’
		// ‘.’는 평범한 지역으로, 수연이가 이동할 수도 있으며 “악마의 손아귀” 스킬이 확장
		// S -> D
		
		int T;
		
		Scanner sc = new Scanner(System.in);
		
		T = sc.nextInt();
		
		for(int tc = 1; tc <= T; tc++) {
			N = sc.nextInt();
			M = sc.nextInt();
			map = new char[N][M];
			q_soo = new LinkedList<Data>();
			q_devil = new LinkedList<Data>();
			for(int i = 0; i < N; i++) {
				String str = sc.next();
				for(int j = 0; j < M; j++) {
					map[i][j] = str.charAt(j);
					if(map[i][j] == 'S') {
						q_soo.offer(new Data(i, j));
					}
					if(map[i][j] == '*') {
						q_devil.offer(new Data(i, j));
					}
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
			
			System.out.print("#" + tc + " ");
			System.out.println(bfs() ? res : "GAME OVER");
		}
	}

	private static boolean bfs() {
		int time = 0;
		while(!q_soo.isEmpty()) {
			Data data;
			int nx, ny;
			int size;
			time++;
			
			size = q_devil.size();
			for(int i = 0; i < size; i++) {
				data = q_devil.poll();
				
				for(int d = 0; d < 4; d++) {
					nx = data.x + dx[d];
					ny = data.y + dy[d];
					
					if(isInBound(nx, ny)) {
						if(map[nx][ny] == 'S' || map[nx][ny] == '.') {
							q_devil.offer(new Data(nx, ny));
							map[nx][ny] = '*';
						}
						
					}
					
				}
			}
			
			size = q_soo.size();
			for(int i = 0; i < size; i++) {
				data = q_soo.poll();
				
				for(int d = 0; d < 4; d++) {
					nx = data.x + dx[d];
					ny = data.y + dy[d];
					
					if(isInBound(nx, ny)) {
						if(map[nx][ny] == 'D') {
							res = time;
							return true;
						}
						if(map[nx][ny] == '.') {
							q_soo.offer(new Data(nx, ny));
							map[nx][ny] = 'S';
						}
						
					}
					
				}
			}
			
			
		}
		
		return false;
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
