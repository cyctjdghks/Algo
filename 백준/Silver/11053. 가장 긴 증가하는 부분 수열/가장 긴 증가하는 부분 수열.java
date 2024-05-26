import java.util.Scanner;

public class Main {

	static int N;
	static int[] A;
	static int[] dp;
	static int res;
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		res = 0;
		N = sc.nextInt();
		A = new int[N];
		dp = new int[N];
		for(int i = 0; i < N; i++) {
			A[i] = sc.nextInt();
		}
		
		dp[0] = 1;
		
		for(int i = 1; i < N; i++) {
			dp[i] = 1;
			for(int j = 0; j < i; j++) {
				if(A[j] < A[i] && dp[i] <= dp[j]) {
					dp[i] = dp[j] + 1;
				}
			}
		}
		
		for(int i = 0; i < N; i++) {
			res = Math.max(res, dp[i]);
		}
		
		System.out.println(res);
	}

}
