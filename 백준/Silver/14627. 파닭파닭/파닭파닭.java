import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // S <= C

        Scanner sc = new Scanner(System.in);

        int S = sc.nextInt(); // 퍄의 갯수
        int C = sc.nextInt(); // 파닭의 갯수

        int[] greenOnion = new int[S]; // 파의 길이

        for (int i = 0; i < S; i++) {
            greenOnion[i] = sc.nextInt();
        }

        long left = 1;
        long right = 1000000000;
        long res = 0;
        while (left <= right) {
            long mid = (left + right) / 2;

            int cnt = 0; // 현재 파 길이로 만들 수 있는 파닭 수
            for (int i = 0; i < S; i++) {
                cnt += greenOnion[i] / mid;
            }

            if (cnt >= C) {
                res = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        long ans = 0;
        for (int i = 0; i < S; i++) {
            ans += greenOnion[i];
        }

        System.out.println(ans - (res * C));

    }
}
