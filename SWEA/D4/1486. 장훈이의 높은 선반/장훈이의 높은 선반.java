import java.util.Arrays;
import java.util.Scanner;

public class Solution {
	
	static int N, B;
	static int[] Hi;
	static int res;
	static int[] output;
	static boolean[] v;

	public static void main(String[] args) {
		// N 개중에 r 개를 뽑아 B와 비교
		// 뽑은 것들의 합 중에 B이상 중에 최소값
		// 순서 상관X => 조합
		
		int T;
		
		Scanner sc = new Scanner(System.in);
		
		T = sc.nextInt();
		
		for(int tc = 1; tc <= T; tc++) {
			N = sc.nextInt();
			B = sc.nextInt();
			Hi = new int[N];
			for(int i = 0; i < N; i++) {
				Hi[i] = sc.nextInt();
			}
			// 점원은 최대 20명, 각 점원의 키는 최대 10,000
			res = 20 * 10000;
			
//			print();
			
			for(int i = 1; i <= N; i++) {
				output = new int[i];
				v = new boolean[N];
				comb(N, i, 0, 0);
			}
			
			res = res - B;
			
			System.out.println("#" + tc + " " + res);
		}
	}

	private static void comb(int n, int r, int depth, int start) {
		if(r == depth) {
//			System.out.println(Arrays.toString(output));
			int sum = output_sum();
			if(sum >= B) {
				res = Math.min(res, sum);
			}
			return;
		}
		
		for(int i = start; i < n; i++) {
			if(!v[i]) {
				v[i] = true;
				output[depth] = Hi[i];
				comb(n, r, depth+1, i);
				v[i] = false;
			}
		}
	}

	private static int output_sum() {
		int sum = 0;
		int len = output.length;
		for(int i = 0; i < len; i++) {
			sum += output[i]; 
		}
		
		return sum;
	}

	private static void print() {
		System.out.println(Arrays.toString(Hi));
	}

}
