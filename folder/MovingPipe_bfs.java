import java.util.Scanner;

//BOJ 17070 파이프 옮기기 1 
public class MovingPipe_bfs {

	static int N;
	static int[][] map;
	static int res = 0;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		map = new int[N + 1][N + 1];
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				map[i][j] = sc.nextInt();
			}
		}

		dfs(1, 2, 0);

		System.out.println(res);
	}

	public static void dfs(int x, int y, int dir) {
		if (x == N && y == N) {
			res++;
			return;
		}
		if (dir == 0) { // 가로 : -> , \
			// 오른쪽
			if (y + 1 <= N && map[x][y + 1] == 0) {
				dfs(x, y + 1, 0);
			}
			// 대각선 아래
			if (x + 1 <= N && y + 1 <= N && map[x + 1][y + 1] == 0 && map[x][y + 1] == 0 && map[x + 1][y] == 0) {
				dfs(x + 1, y + 1, 2);
			}
		} else if (dir == 1) { // 세로 : | \
			// 아래
			if (x + 1 <= N && map[x + 1][y] == 0) {
				dfs(x + 1, y, 1);
			}
			// 대각선 아래
			if (x + 1 <= N && y + 1 <= N && map[x + 1][y + 1] == 0 && map[x][y + 1] == 0 && map[x + 1][y] == 0) {
				dfs(x + 1, y + 1, 2);
			}
		} else if (dir == 2) { // 대각선 : -> | \
			// 오른쪽
			if (y + 1 <= N && map[x][y + 1] == 0) {
				dfs(x, y + 1, 0);
			}
			// 아래
			if (x + 1 <= N && map[x + 1][y] == 0) {
				dfs(x + 1, y, 1);
			}
			// 대각선 아래
			if (x + 1 <= N && y + 1 <= N && map[x + 1][y + 1] == 0 && map[x][y + 1] == 0 && map[x + 1][y] == 0) {
				dfs(x + 1, y + 1, 2);
			}
		}
	}

}
