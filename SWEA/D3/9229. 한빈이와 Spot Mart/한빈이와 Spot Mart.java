import java.util.Scanner;

public class Solution {

	public static void main(String[] args) {
		
		int TC;
		int N, M;
		int[] arr;
		int sum, max;
		
		Scanner sc = new Scanner(System.in);
		
		TC = sc.nextInt();
		
		for(int tc = 1; tc <= TC; tc++) {
			sum = 0;
			max = 0;
			
			N = sc.nextInt();
			M = sc.nextInt();
			
			arr = new int[N];
			
			for(int i = 0; i < N; i++) {
				arr[i] = sc.nextInt();
			}
			
			for(int i = 0; i < N; i++) {
				for(int j = i+1; j < N; j++) {
					sum = arr[i] + arr[j];
					
					if(sum <= M && sum > max) {
						max = sum;
					}
				}
			}
			
			if(max == 0) {
				max = -1;
			}
			
			System.out.println("#" + tc + " " + max);
		}
	}

}
