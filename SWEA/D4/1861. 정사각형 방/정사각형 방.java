import java.util.Scanner;

// 7. SWEA 1861-D4 정사각형방
public class Solution {

	static int[][] Arr;
	static int N;
	static int start, max;
	static int location;
	static int dx[] = { -1, 1, 0, 0 };
	static int dy[] = { 0, 0, -1, 1 };
	// 좌 우 상 하

	public static void main(String[] args) {
		// 테스트 케이스의 수 T
		// 정수 N
		// N개의 A[i, j]
		// A[i, j] 는 모두 서로 다른 수
		// 상하좌우로 이동 가능
		// 이동하려는 방의 숫자가 현재 방에 적힌 숫자보다 정확히 1 더 클 때 이동
		// 처음에 출발해야 하는 방 번호와
		// 최대 몇 개의 방을 이동할 수 있는지 출력
		// 사방위 탐색

		int T;

		Scanner sc = new Scanner(System.in);

		T = sc.nextInt();

		for (int tc = 1; tc <= T; tc++) {
			start = 0;
			max = 0;
			location = 0;

			N = sc.nextInt();

			Arr = new int[N][N];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					Arr[i][j] = sc.nextInt();
				}
			}

			setsearch();

			System.out.println("#" + tc + " " + start + " " + max);
		}
	}

	public static void setsearch() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				location = Arr[i][j];
				search(i, j, 1);
			}
		}
	}
	
	public static void search(int r, int c, int depth) {
		for (int i = 0; i < 4; i++) {
			int nextX = r + dy[i];
			int nextY = c + dx[i];
			
			if (isInBound(nextX, nextY)) continue;
			if (Arr[nextX][nextY] == Arr[r][c] + 1) {
				search(nextX, nextY, depth + 1);
			}
		}
		
		if(depth > max) {
			max = depth;
			start = location;
		}
		if (depth == max) start = Math.min(location, start);
	}
	
	public static boolean isInBound(int x, int y) {
		return x < 0 || x >= N || y < 0 || y >= N;
	}
}
