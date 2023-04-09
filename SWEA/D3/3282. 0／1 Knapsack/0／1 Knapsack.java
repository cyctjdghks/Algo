import java.util.Scanner;

// 3282. 0/1 Knapsack
public class Solution {

	public static void main(String[] args) {
		// 물건 N
		// 가방 K
		// 각각 물건은 부피 Vi 가치 Ci
		// 가치의 합 최대화
		// 테스트케이스 T
		int T;
		int N, K;
		int[][] products;
		int[][] dp;
		
		Scanner sc = new Scanner(System.in);
		
		T = sc.nextInt();
		
		for(int tc = 1; tc<=T; tc++) {
			N = sc.nextInt();
			K = sc.nextInt();
			products = new int[N+1][2];
			dp = new int[N+1][K+1];
			for(int i = 1; i <= N; i++) {
				products[i][0] = sc.nextInt(); // 부피
				products[i][1] = sc.nextInt(); // 가치
			}
			
			for(int i = 1; i<= N; i++) {
				for(int j = 1; j <= K; j++) {
					if(products[i][0] > j) {
						dp[i][j] = dp[i-1][j];
					} else {
						dp[i][j] = Math.max(dp[i-1][j-products[i][0]] + products[i][1], 
								dp[i-1][j]);
					}
				}
			}
			
			System.out.println("#" + tc + " " + dp[N][K]);
		}
	}

}
