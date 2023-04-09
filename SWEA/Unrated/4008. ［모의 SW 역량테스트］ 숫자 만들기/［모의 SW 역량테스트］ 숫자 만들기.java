import java.util.Arrays;
import java.util.Scanner;

public class Solution {

	static int N;
	static int[] opers;
	// + - * /
	static int[] nums;
	static int[] output;
	static int max, min;
	static int res;
	
	public static void main(String[] args) {
		// 중복 순열
		
		int T;
		
		Scanner sc = new Scanner(System.in);
		
		T = sc.nextInt();
		
		for(int tc = 1; tc <= T; tc++) {
			
			N = sc.nextInt();
			opers = new int[4];
			nums = new int[N];
			output = new int[N-1];
			for(int i = 0; i < 4; i++) {
				opers[i] = sc.nextInt();
			}
			for(int i = 0; i < N; i++) {
				nums[i] = sc.nextInt();
			}
			max = Integer.MIN_VALUE;
			min = Integer.MAX_VALUE;
			res = 0;
			
			perm(N-1, 0);
			
			res = max - min;
			System.out.println("#" + tc + " " + res);
		}
	}

	private static void perm(int n, int depth) {
		if(depth == n) {
//			System.out.println(Arrays.toString(output));
			calcul();
			return;
		}
		
		for(int i = 0; i < 4; i++) {
			if(opers[i] > 0) {
				opers[i]--;
				output[depth] = i;
				perm(n, depth+1);
				opers[i]++;
			}
		}
	}

	private static void calcul() {
		int num = nums[0];
		for(int i = 0; i < N-1; i++) {
			switch(output[i]) {
			case 0:
				num += nums[i+1];
				break;
			case 1:
				num -= nums[i+1];
				break;
			case 2:
				num *= nums[i+1];
				break;
			case 3:
				num /= nums[i+1];
				break;
			}
		}
		
		max = Math.max(max, num);
		min = Math.min(min, num);
	}

}
