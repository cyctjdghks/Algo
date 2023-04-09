import java.util.Arrays;
import java.util.Scanner;

class Solution {
	
	static int[][] farm;
	public static void main(String args[]) throws Exception {
		Scanner sc = new Scanner(System.in);
		int T;
		T = sc.nextInt();
		
		int N;
		String str;
		int sum; // 수익
		int x; // 수확 범위 조건
		
		for (int test_case = 1; test_case <= T; test_case++) {
			sum = 0;
			x = 0;
			
			N = sc.nextInt();
			farm = new int[N][N];
			for(int i = 0; i < N; i ++) {
				str = sc.next();
				for(int j = 0; j < N; j ++) {
					farm[i][j] = Character.getNumericValue(str.charAt(j));
				}
			}
			
			for(int i = 0; i < N; i++) {
				// 마름모 조건부
				for(int j = N/2 - x; j <= N/2 + x; j++) {
					sum += farm[i][j];
				}
				if(i < N/2) {
					x++;
				} else {
					x--;
				}
			}
			System.out.println("#" + test_case + " " + sum);
		}
	}
}