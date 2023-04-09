import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Solution {
	
	static int MOD = 1234567891;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			String[] line = br.readLine().split(" ");
			int n = Integer.parseInt(line[0]);// 10
			int r = Integer.parseInt(line[1]);// 2
			
			//FACTORIAL 미리 구해 놓기 N!
			long[] facto = new long[n + 1];//1! ~ 10!
			facto[1] = 1; //1! = 1			
			for (int i = 2; i <= n; i++) {
				facto[i] = (facto[i - 1] * i) % MOD;
			}
			
			long bottom = (facto[r] * facto[n-r]) % MOD;// 1/a에서 a에 해당하는 값
			bottom = pow(bottom, MOD -2);			
			
			System.out.println("#" + tc + " " + (facto[n] * bottom) % MOD );
		}
	}

	private static long pow(long a, int b) {//a의 b승
		if(b == 0)
			return 1;
		
		else if(b == 1)
			return a;
		
		if(b % 2 == 0) {//b가 짝수인 경우
			long tmp = pow(a, b/2);
			return (tmp * tmp) % MOD;
		}
		
		long tmp = pow(a, b-1) % MOD;//pow(2,5) ==> pow(2,4)
		return (tmp * a) % MOD;
	}
}