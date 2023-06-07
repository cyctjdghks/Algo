import java.util.Arrays;
import java.util.Scanner;

public class Main {
    static int N;
    static int[] map;
    static int[] dp;
    static int res;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        map = new int[N];

        for (int i = 0; i < N; i++) {
            map[i] = sc.nextInt();
        }

        dp = new int[N];

        dp[0] = map[0];
        res = dp[0];
        for (int i = 1; i < N; i++) {
            dp[i] = Math.max(dp[i - 1] + map[i], map[i]);
        }

        for (int i = 0; i < N; i++) {
            res = Math.max(res, dp[i]);
        }

        System.out.println(res);
    }
}
