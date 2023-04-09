import java.util.Arrays;
import java.util.Scanner;

// 5. SWEA 5215. 햄버거 다이어트
public class Solution {

	static int[][] flavor;
	static int[][] output;
	static boolean[] visit;
	static int count, limit;
	static int max;
	static int f_sum, c_sum;

	public static void main(String[] args) {
		// 햄버거의 맛은 최대한 유지하면서 정해진 칼로리를 넘지 않는 햄버거
		// 테스트 케이스 T
		// 재료 수 N 제한 칼로리 L
		// 맛에대한 점수와 칼로리
		// 제한 칼로리 이하 조합 중 가장 점수가 높은 햄버거의 점수
		// => 순서 상관 X
		// => 조합

		int T;

		Scanner sc = new Scanner(System.in);

		T = sc.nextInt();

		for (int tc = 1; tc <= T; tc++) {
			count = sc.nextInt();
			limit = sc.nextInt();

			flavor = new int[2][count];
			output = new int[2][count];
			visit = new boolean[count];
			max = 0;

			for (int i = 0; i < count; i++) {
				flavor[0][i] = sc.nextInt(); // 맛
				flavor[1][i] = sc.nextInt(); // 칼로리
			}

			for (int i = 1; i <= count; i++) {
				Combimation(0, count, i);
			}
			
			System.out.println("#" + tc + " " + max);
		}
	}

	public static void Combimation(int index, int count, int r) {
		if (r == 0) {
			f_sum = 0;
			c_sum = 0;

			for (int i = 0; i < output[0].length; i++) {
				f_sum += output[0][i];
				c_sum += output[1][i];
			}

			if (c_sum <= limit) {
				max = Math.max(max, f_sum);
			}
			return;
		}

		if (index == count) {
			return;
		}

		visit[index] = true;
		output[0][r - 1] = flavor[0][index];
		output[1][r - 1] = flavor[1][index];
		Combimation(index + 1, count, r - 1);
		visit[index] = false;
		Combimation(index + 1, count, r);
	}

}
