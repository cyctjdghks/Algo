import java.util.Scanner;

public class Main {
    static char[] str1, str2;
    static int[][] dp;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 각 문자열을 입력 받는다.
        str1 = sc.next().toCharArray();
        str2 = sc.next().toCharArray();

        dp = new int[str1.length+1][str2.length+1];

        LCS();

        System.out.println(dp[str1.length][str2.length]);
    }

    public static void LCS() {
        // B의 모든 문자열을 A문자열과 비교
        for (int i = 1; i <= str1.length; i++) {
            for (int j = 1; j <= str2.length; j++) {
                // 만일 두 문자가 같은 경우
                if(str1[i - 1] == str2[j - 1]) {
                    // 대각선의 값을 참고하여 LCS의 값을 + 1한다.
                    dp[i][j] =  dp[i - 1][j - 1] + 1;
                // 두 문자가 다른 경우
                } else {
                    // 각 문자열의 이전 문자 중 최대 LCS값을 선택.
                    dp[i][j] = Math.max(dp[i][j - 1],dp[i - 1][j]);
                }
            }
        }
    }
}
