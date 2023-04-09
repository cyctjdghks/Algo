import java.util.Scanner;

public class Solution {

	public static void main(String[] args) {
		// N * N 배열
		// M * M 크기의 파리채
		// 첫 줄에는 테스트 케이스의 개수 T
		// N 과 M
		// N * N 의 배열

		int T = 0;
		int N = 0;
		int M = 0;
		int[][] flies;
		int max;
		int sum;

		Scanner sc = new Scanner(System.in);

		T = sc.nextInt();
		
		for (int test_case = 1; test_case <= T; test_case++) {
			max = 0;
			sum = 0;
			N = sc.nextInt();
			M = sc.nextInt();
			flies = new int[N+1][N+1];
			
			// 누적합을 배열에 저장
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= N; j++) {
					flies[i][j] = sc.nextInt() + flies[i][j-1] + flies[i-1][j] - flies[i-1][j-1];
				}
			}
			
			// 배열 확인
//			for (int i = 0; i < flies.length; i++) {
//				for (int j = 0; j < flies.length; j++) {
//					System.out.print(flies[i][j] + " ");
//				}
//				System.out.println();
//			}
			
			// 알고리즘 수행 - 누적합
			for (int i = 1; i <= N - M + 1; i++) {
				for (int j = 1; j <= N - M + 1; j++) { // (i,j) ~ (i+M, j+M) 부분의 합
					sum = flies[i+M-1][j+M-1] - flies[i+M-1][j-1] - flies[i-1][j+M-1] + flies[i-1][j-1];
					
					if(max < sum) {
						max = sum;
					}
				}
			}

			System.out.println("#" + test_case + " " + max);
		}
	}

}
