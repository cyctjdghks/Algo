import java.util.Scanner;

// BOJ 1463 1로 만들기
public class MakeIt1 {

	static int res = Integer.MAX_VALUE;
	static int[] dp;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int X = sc.nextInt();
		dp = new int[X+1];
		res = dfs(X);
		System.out.println(res);
	}

	static int dfs(int num) {
		// 종료조건
		if (num == 1) {
			return 0;
		}
		if(dp[num] != 0) {
			return dp[num];
		}
		// 실행
		// 1을 빼보고
//		int min = dfs(num - 1) + 1;
		dp[num] = dfs(num-1)+1;
		// 2로 나누어보고
		if (num % 2 == 0) {
//			min = Math.min(min, dfs(num / 2) + 1);
			dp[num] = Math.min(dp[num], dfs(num/2) + 1);
		}
		// 3으로 나누어보고
		if (num % 3 == 0) {
//			min = Math.min(min, dfs(num / 3) + 1);
			dp[num] = Math.min(dp[num], dfs(num/3) + 1);
		}

//		return min;
		return dp[num];
	}

}
