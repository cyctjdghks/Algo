import java.util.Arrays;
import java.util.Scanner;

public class Main {
    static int K, N;
    static int[] map;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        K = sc.nextInt();
        N = sc.nextInt();
        map = new int[K];
        for (int i = 0; i < K; i++) {
            map[i] = sc.nextInt();
        }

        Arrays.sort(map);

        long max = map[K - 1];
        long min = 1;
        long count, half;

        while (min <= max) {
            count = 0;
            half = (min + max) / 2;

            for (int i = 0; i < K; i++) {
                count += map[i] / half;
            }

            if (count < N) {
                max = half - 1;
            } else {
                min = half + 1;
            }
        }

        System.out.println(max);
    }
}
