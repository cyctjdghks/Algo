import java.util.Scanner;

// BOJ 11726 2×n 타일링
public class Tiling2n {

	public static void main(String[] args) {
		// 2*n 크기의 직사각형을 1*2, 2*1 타일로 채우는 방법
		
		int n;
		int[] dp;
		int res = 0;
		
		Scanner sc = new Scanner(System.in);
		
		n = sc.nextInt();
		dp = new int[1000+1];
		
		dp[1] = 1;
		dp[2] = dp[1] + 1;
//		dp[3] = dp[1] + =
//				dp[2] + l
		for(int i = 3; i <= n; i++) {
			dp[i] = (dp[i-1] + dp[i-2]) % 10007;
		}
		res = dp[n];
		res = res % 10007;
		System.out.println(res);
	}

}