import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		int N;
		int min;
		
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		min = 0;
		
		int[] dp = new int[N+1];
		dp[1] = 1;
		
		for (int i = 2; i <= N; i++) {
            min = Integer.MAX_VALUE;
            
            // i에서 i보다 작은 제곱수에서 뺀 dp[i - j * j] 중
            // 최소를 택한다.
            for (int j = 1; j * j <= i; j++) {
                int temp = i - j * j;
                min = Math.min(min, dp[temp]);
            }
 
            dp[i] = min + 1; // 그리고 1을 더해준다.
        }
		
		
		System.out.println(dp[N]);
	}

}
