import java.util.Arrays;
import java.util.Scanner;

// 10. SWEA 3234-D4 준환이의 양팔저울
public class Solution {

	static int N;
	static int[] weight;
	static boolean[] visit;
	static int[] output;
	static int sum = 0;
	static int count;

	public static void main(String[] args) {
		// N개의 서로 다른 무게를 가진 무게 추와 양팔저울
		// 순서는 총 N! 가지
		// 왼쪽에 올릴 것인지 오른쪽에 올릴 것인지를 정해야 해서 총 2^N * N!가지의 경우
		// 무게 추를 올릴 때 오른쪽 위에 올라가 있는 무게의 총합이 왼쪽에 올라가 있는 무게의 총합보다 더 커져서는 안 된다.
		// 테스트케이스 T
		// 추의 개수 N
		// N개의 추
		// 오른쪽이 왼쪽보다 커지지 않는 경우의 수
		// 순열로 줄 세운 뒤 양쪽의 무게 비교

		int T;

		Scanner sc = new Scanner(System.in);

		T = sc.nextInt();

		for (int tc = 1; tc <= T; tc++) {
			N = sc.nextInt();
			weight = new int[N];
			visit = new boolean[N];
			output = new int[N];
			sum = 0;
			count = 0;
			for (int i = 0; i < N; i++) {
				weight[i] = sc.nextInt();
			}
//			// 배열확인
//			System.out.println(Arrays.toString(weight));

			perm(0);

			System.out.println("#" + tc + " " + count);

		}
	}

	public static void perm(int depth) {
		if (depth == N) {
//			System.out.println(Arrays.toString(output));
			dfs(0, 0, 0);
			return;
		}
		for (int i = 0; i < N; i++) {
			if (!visit[i]) {
				visit[i] = true;
				output[depth] = weight[i];
				perm(depth + 1);
				visit[i] = false;
			}
		}
	}

	public static void dfs(int depth, int left, int right) {
		if (depth == N) {
			count++;
			return;
		}
		sum = left + output[depth];
		dfs(depth + 1, sum, right);
		sum = right + output[depth];
		if (sum <= left) {
			dfs(depth + 1, left, sum);
		}
	}
}
