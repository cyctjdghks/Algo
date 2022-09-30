import java.util.Scanner;

// BOJ 17070 파이프 옮기기 1 
public class MovingPipe_dp {

	public static void main(String[] args) {
		// n * n 집
		// 처음에 파이프는 (1, 1)와 (1, 2)를 차지하고 있고, 방향은 가로
		// 가로 > 가로, 대각
		// 세로 > 세로, 대각
		// 대각 > 가로, 세로, 대각
		// 0 : 가로
		// 1 : 세로
		// 2 : 대각
//		x y 0 => x y-1 ~ x y 0 => x y-2 0 + x-1 y-2 2
//		x y 1 => x-1 y ~ x y 1 => x-2 y 1 + x-2 y-1 2
//		x y 2 => x-1 y-1 ~ x y 2 => x-1 y-2 0 + x-2 y-1 1 + x-2 y-2 2 
		
		int N;
		int[][] map;
		int[][][] dp;
		int res;
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		map = new int[N+1][N+1];
		dp = new int[N+1][N+1][3];
		for(int i = 1; i <= N; i++) {
			for(int j = 1; j <= N; j++) {
				map[i][j] = sc.nextInt();
			}
		}
		
		dp[1][2][0] = 1;
		for(int i = 1; i <= N; i++) {
			for(int j = 2; j <= N; j++) {
				if(map[i][j] == 1) continue;
				
				dp[i][j][0] += dp[i][j-1][0] + dp[i][j-1][2];
				dp[i][j][1] += dp[i-1][j][1] + dp[i-1][j][2];
				
				if(map[i-1][j] == 1 || map[i][j-1] == 1) continue;
				dp[i][j][2] += dp[i-1][j-1][0] + dp[i-1][j-1][1]
						+ dp[i-1][j-1][2];
			}
		}
		
		res = dp[N][N][0] + dp[N][N][1] + dp[N][N][2];
		
		System.out.println(res);
		
	}

}
