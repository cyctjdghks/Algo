import java.util.ArrayList;
import java.util.Scanner;

public class Solution {

	static int N, K;
	static int[][] map;
	static boolean[][] v;
	static int start_val;
	static ArrayList<Data> list;
	static int res;
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	
	public static void main(String[] args) {
		int T;
		
		Scanner sc = new Scanner(System.in);
		
		T = sc.nextInt();
		
		for(int tc = 1; tc <= T; tc++) {
			start_val = 0;
			N = sc.nextInt();
			K = sc.nextInt();
			map = new int[N][N];
			list = new ArrayList<Data>();
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					map[i][j] = sc.nextInt();
					start_val = Math.max(start_val, map[i][j]);
				}
			}
			res = 0;
			
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					if(map[i][j] == start_val) {
						list.add(new Data(i, j));
					}
				}
			}
//			print();
			
			for(int i = 0; i < list.size(); i++) {
				v = new boolean[N][N];
				dfs(list.get(i).x, list.get(i).y, map[list.get(i).x][list.get(i).y], 1, false);				
			}
			
			System.out.println("#" + tc + " " + res);
		}
	}

	private static void dfs(int x, int y, int val, int len, boolean isUsed) {
		v[x][y] = true;
		if(res < len) {
			res = len;
		}
		
		for(int d = 0; d < 4; d++) {
			int nx = x + dx[d];
			int ny = y + dy[d];
			
			if(isInBound(nx, ny) && !v[nx][ny]) {
				if(map[nx][ny] < val) {
					dfs(nx, ny, map[nx][ny], len+1, isUsed);
				} else {
					if(!isUsed && map[nx][ny] - K < val) {
						dfs(nx, ny, val-1, len+1, true);
					}
				}
			}
		}
		
		v[x][y] = false;
	}
	
	public static boolean isInBound(int x, int y) {
		return x >= 0 && x < N && y >= 0 && y < N;
	}

	public static void print() {
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}
	
	static class Data{
		int x, y;

		public Data(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}
}
