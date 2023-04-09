
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
 
public class Solution {
	
    static int MOD;
    static long[] fact;
    static long res;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
 
        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
 
            long N = Long.parseLong(st.nextToken());
            long R = Long.parseLong(st.nextToken());
            MOD = Integer.parseInt(st.nextToken());
            
            fact = new long[1000001];
            res = 1;
            
            set();
            
            while (N > 0 || R > 0) {
                int n = (int) (N % MOD);
                int r = (int) (R % MOD);
 
                if(r>n) {
                    res = 0 ;
                    break;
                }
                 
                res = (res * fact[n]) % MOD;
                for (int i = 0; i < MOD - 2; i++) {
                    res = (res * fact[n - r] * fact[r]) % MOD;
                }
 
                N /= MOD;
                R /= MOD;
            }
             
            res %= MOD;
            
            System.out.println("#" + tc + " " + res);
        }
 
    }
    
    public static void set() {
        fact[0] = 1;
        for (int i = 1; i <= MOD; i++) {
            fact[i] = (i * fact[i - 1]) % MOD;
        }
    }
}