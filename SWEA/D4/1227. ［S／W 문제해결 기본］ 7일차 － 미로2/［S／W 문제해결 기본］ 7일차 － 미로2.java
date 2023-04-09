import java.util.Scanner;

public class Solution {

	static int length = 100;
	static int[][] maze = new int[length][length];
	static boolean[][] visit = new boolean[length][length];
	static int start_r, start_c;
	static int end_r, end_c;
	static int result;
	static int[] dx = {0, 1, 0, -1};
	static int[] dy = {1, 0, -1, 0};
	// 우 하 좌 상
	
	public static void main(String[] args) {
		// 10개의 테스트 케이스
		// 1은 벽 0은 길 2는 출발 3은 도착점
		// 도달 가능 1 불가능 0
		// 100 * 100 행렬
		// 깊이우선 탐색으로 탐색 or 사방위 탐색으로 0으로 된 지점만 이동
		
		int T = 10;
		int test;
		String line;
		
		
		Scanner sc = new Scanner(System.in);
		
		for(int tc = 1; tc <= 10; tc++) {
			start_r = 0;
			start_c = 0;
			end_r = 0;
			end_c = 0;
			result = 0;
			
			test = sc.nextInt();
			sc.nextLine();
			for(int i = 0; i < length; i++) {
				line = sc.nextLine();
				for(int j = 0; j < length; j++) {
					maze[i][j] = Integer.parseInt(String.valueOf(line.charAt(j)));
				}
			}
			
			for(int i = 0; i < length; i++) {
				for(int j = 0; j < length; j++) {
					if(maze[i][j] == 2) {
						start_r = i;
						start_c = j;
					}
					if(maze[i][j] == 3) {
						end_r = i;
						end_c = j;
					}
				}
			}
			
			dfs(start_r, start_c);
			
			System.out.println("#" + test + " " +result);
		}
	}
	
	public static void dfs(int r, int c) {
		if(r == end_r && c == end_c) { // 도착지점 찾으면 종료
			result = 1;
			return;
		}
		// 왔으니 방문탐색 기록
		visit[r][c] = true;
		for(int i = 0; i < 4; i++) { // 갈 수 있는 방향(자식)은 4개
			int nextX = r + dx[i];
			int nextY = c + dy[i];
			
			if(isInbound(nextX, nextY) && visit[nextX][nextY] == false &&
					(maze[nextX][nextY] == 0 || maze[nextX][nextY] == 3)) {
				dfs(nextX, nextY);
			}
		}
		visit[r][c] = false;
		
		// 더이상 갈 곳 없으면 종료
		return;
	}

	public static boolean isInbound(int r, int c) {
		return r >= 0 && c >= 0 && r < length && c < length;
	}
}
