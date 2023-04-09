import java.util.Scanner;

public class Solution {

	static int N;
	static int[][] dp;
	static int res;
	
	public static void main(String[] args) {
		
		int T;
		
		Scanner sc = new Scanner(System.in);
		
		T = sc.nextInt();
		
		for(int tc = 1; tc <= T; tc++) {
			res = Integer.MAX_VALUE;
			N = sc.nextInt();
			dp = new int[N][N];
			
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					dp[i][j] = sc.nextInt();
					if(i != j && dp[i][j] == 0) {// 아주 큰 비용을 할당
						dp[i][j] = 9999999;
					}
				}
			}
			
			// 프로이드 와샬
			for(int k = 0; k < N; k++) {
				for(int i = 0; i < N; i++) {
					if(i == k) {
						continue;
					}
					for(int j = 0; j < N; j++) {
						if(i == j) { // 출발지 도착지 무시
							continue;
						}
						dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k][j]);
					}
				}
			}
			
			for(int i = 0; i < N; i++) {
				int sum = 0;
				for(int j = 0; j < N; j++) {
					sum += dp[i][j];
				}
				res = Math.min(res, sum);
			}
			System.out.println("#" + tc + " " + res);
		}
	}

}
