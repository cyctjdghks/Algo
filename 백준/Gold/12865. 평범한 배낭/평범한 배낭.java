import java.util.Scanner;

public class Main {

	static int N, K;
	static int[] W;
	static int[] V;
	static Integer[][] dp;

	public static void main(String[] args) {
		// N개의 물건
		// 각 물건은 무게 W와 가치 V
		// 최대 K만큼의 무게만
		// 물건들의 가치의 최댓값
		// dp 문제

		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		K = sc.nextInt();

		W = new int[N];
		V = new int[N];
		dp = new Integer[N][K + 1];

		for (int i = 0; i < N; i++) {
			W[i] = sc.nextInt();
			V[i] = sc.nextInt();
		}

		System.out.println(knapsack(N - 1, K));
	}

	private static int knapsack(int i, int k) {
		// i가 0미만, 즉 범위 밖이 된다면
		if (i < 0)
			return 0;

		// 탐색하지 않은 위치라면?
		if (dp[i][k] == null) {

			// 현재 물건(i)을 추가로 못담는 경우 (이전 i값 탐색)
			if (W[i] > k) {
				dp[i][k] = knapsack(i - 1, k);
			}
			// 현재 물건(i)을 담을 수 있는 경우
			else {
				// 이전 i값과 이전 i값에 대한 k-W[i]의 값 + 현재 가치(V[i])중 큰 값을 저장
				dp[i][k] = Math.max(knapsack(i - 1, k), knapsack(i - 1, k - W[i]) + V[i]);
			}
		}
		return dp[i][k];
	}

}
