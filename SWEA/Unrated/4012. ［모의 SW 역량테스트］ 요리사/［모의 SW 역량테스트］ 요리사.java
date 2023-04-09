import java.util.Arrays;
import java.util.Scanner;

// 7. SWEA 4012-[모의 SW 역량테스트] 요리사
public class Solution {

	static int N;
	static int[][] flavor;
	static boolean[] visit;
	static int[] A;
	static int min;

	public static void main(String[] args) {
		// 총 테스트 케이스 개수 T
		// N개의 식재료
		// A음식과 B음식 맛의 차이가 최소
		// 각 음식은 N/2 개씩 재료 사용
		// Sij + Sji = a
		// Sji + Sij = b
		// 맛의 차이 = |a-b|
		// 맛의 차이가 최소가 될 때 맛의 차이 출력
		// N 개 중에 순서 상관없이 N/2 개 뽑기 => 조합

		int T;

		Scanner sc = new Scanner(System.in);

		T = sc.nextInt();

		for (int tc = 1; tc <= T; tc++) {
			min = 9999_9999;
			
			N = sc.nextInt();

			flavor = new int[N][N];
			visit = new boolean[N];
			A = new int[N / 2];

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					flavor[i][j] = sc.nextInt();
				}
			}

//			// 배열 확인
//			for(int i = 0; i < N; i++) {
//				for(int j = 0; j < N; j++) {
//					System.out.print(flavor[i][j] + " ");
//				}
//				System.out.println();
//			}
//			System.out.println();

			Combination(0, N, N / 2);
			
			System.out.println("#" + tc + " " + min);
		}
	}

	public static void Combination(int depth, int n, int r) {
		if (r == 0) {
//			System.out.println(Arrays.toString(A));

			cooking();
			return;
		}

		if (depth == n) {
			return;
		}

		visit[depth] = true;
		A[r - 1] = depth + 1;
		Combination(depth + 1, n, r - 1);
		visit[depth] = false;
		Combination(depth + 1, n, r);
	}

	public static void cooking() {
		int a = 0; // a 요리의 값
		int b = 0; // b 요리의 값
		int diff = 0; // 맛의 차이

		for (int i = 0; i < N - 1; i++) {
			for (int j = i + 1; j < N; j++) {
				if (visit[i] == true && visit[j] == true) {
					a += flavor[i][j];
					a += flavor[j][i];
				} else if (visit[i] == false && visit[j] == false) {
					b += flavor[i][j];
					b += flavor[j][i];
				}
			}
		}
		diff = Math.abs(a - b);
		
		min = Math.min(min, diff);
	}

}
