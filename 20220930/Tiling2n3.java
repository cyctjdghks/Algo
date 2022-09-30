import java.util.Scanner;

// BOJ 14852 타일 채우기3
public class Tiling2n3 {

	public static void main(String[] args) {
		// 2*n 크기의 직사각형을 1*2, 2*1, 1*1 타일로 채우는 방법
		
		int n;
		long[][] dp;
		long res = 0;
		
		Scanner sc = new Scanner(System.in);
		
		n = sc.nextInt();
		dp = new long[1000000+1][2];
		
		dp[0][0] = 0;
//		dp[1] = :
//				l
		dp[1][0] = 2;
//		dp[2] = dp[0] + ㅛ
//				dp[0] + ㅠ
//				dp[0] + =
//				dp[1] + :
//				dp[1] + l
		dp[2][0] = dp[1][0]*2 + 3;
		dp[2][1] = 1;
//		dp[3] = dp[0] + .ㅡ
//					  + ㅡ.
//				dp[0] + ㅡ.
//					  + .ㅡ
//				dp[1] + ㅛ
//				dp[1] + ㅠ
//				dp[1] + =
//				dp[2] + :
//				dp[2] + l
//		d[i-1]*2 + d[i-2] + sum(d[i-3] ~ d[0])*2
		for(int i = 3; i <= n; i++) {
			dp[i][1] = (dp[i-1][1] + dp[i-3][0]) % 1000000007;
			dp[i][0] = (dp[i-1][0]*2 + dp[i-2][0]*3 + dp[i][1]*2) % 1000000007;
		}
		res = dp[n][0];
		res = res % 1000000007;
		
		System.out.println(res);
	}

}
