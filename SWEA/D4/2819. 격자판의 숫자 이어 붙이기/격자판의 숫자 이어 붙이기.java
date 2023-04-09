import java.util.HashSet;
import java.util.Scanner;

// 2. SWEA 2819 격자판의 숫자 이어붙이기(보충수업)
public class Solution {

	static int T;
	static int[][] map;
	static int[] dx = { 0, 0, -1, 1 };
	static int[] dy = { -1, 1, 0, 0 };
	// 상 하 좌 우
	static HashSet<String> list;

	public static void main(String[] args) {
		// 테스트 케이스 T
		// 격자 정보
		// 임의의 위치에서 시작해서 네방향으로 인접한 격자로 총 6번 이동
		// 만들 수 있는 서로 다른 일곱 자리 수들의 개수
		// 한번 거쳤던 격자칸을 다시 거쳐도 됨

		Scanner sc = new Scanner(System.in);

		T = sc.nextInt();

		for (int tc = 1; tc <= T; tc++) {
			map = new int[4][4];
			list = new HashSet<>();

			for (int i = 0; i < 4; i++) {
				for (int j = 0; j < 4; j++) {
					map[i][j] = sc.nextInt();
				}
			}

			for (int i = 0; i < 4; i++) {
				for (int j = 0; j < 4; j++) {
					dfs(i, j, 0, "" + map[i][j]); // i, j 좌표에서 찾기
				}
			}

			System.out.println("#" + tc + " " + list.size());
		}
	}

	public static void dfs(int r, int c, int depth, String output) {
		if (depth == 6) {
			list.add(output);
			return;
		}

		for (int i = 0; i < 4; i++) {
			int nextX = r + dx[i];
			int nextY = c + dy[i];

			if (isInBound(nextX, nextY)) {
				dfs(nextX, nextY, depth + 1, output + map[nextX][nextY]);
			}
		}
	}

	public static boolean isInBound(int r, int c) {
		return r >= 0 && r < 4 && c >= 0 && c < 4;
	}

}
