import java.util.Scanner;

public class Solution {

	static int R, C;
	static int[][] map;
	static boolean[] v;
	static int max;
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };
	// 상 하 좌 우

	public static void main(String[] args) {
		// R * C 행렬
		// 1행1열로 시작
		// 1행1열에서 출발
		// 알파벳은 섬의 명물
		// 처음 볼때는 무료로
		// 4방향 탐색
		// 명물을 최대한 많이 보되, 요금을 지급x
		// 같은 알파벳을 두번 이상 보지 않는 방법 중 알파벳의 최대 개수
		// 테스트 케이스 T

		int T;
		String str;

		Scanner sc = new Scanner(System.in);

		T = sc.nextInt();

		for (int tc = 1; tc <= T; tc++) {
			R = sc.nextInt();
			C = sc.nextInt();
			map = new int[R][C];
			v = new boolean[26]; // 알파벳 탐색 여부
			max = 0;

			for (int i = 0; i < R; i++) {
				str = sc.next();
				for (int j = 0; j < C; j++) {
					map[i][j] = str.charAt(j) - 65; // 대문자 A 는 65, 소문자 a 는 97
				}
			}

//			// 배열 확인
//			for(int i = 0; i < R;i++) {
//				for(int j = 0; j < C; j++) {
//					System.out.print(map[i][j] + " ");
//				}
//				System.out.println();
//			}
//			System.out.println();

			dfs(0, 0, 1); // 0, 0 에서 시작
			
			System.out.println("#" + tc + " " + max);
		}
	}

	public static void dfs(int r, int c, int depth) {
		max = Math.max(max, depth);
		
		v[map[r][c]] = true;
		for(int i = 0; i < 4; i++) {
			int nextX = r + dx[i];
			int nextY = c + dy[i];
			
			if(isInBound(nextX, nextY) && v[map[nextX][nextY]] == false) {
				dfs(nextX, nextY, depth + 1);
			}
		}
		v[map[r][c]] = false;
	}

	public static boolean isInBound(int r, int c) {
		return r >= 0 && r < R && c >= 0 && c < C;
	}

}
