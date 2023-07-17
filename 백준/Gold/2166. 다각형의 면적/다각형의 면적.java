import java.util.Scanner;

public class Main {
    static int N;
    static long[] x;
    static long[] y;
    static long sumA, sumB;
    static String res;

    public static void main(String[] args) {
        // 입력
        Scanner sc = new Scanner(System.in);

        sumA = 0;
        sumB = 0;
        N = sc.nextInt();
        x = new long[N + 1];
        y = new long[N + 1];
        for (int i = 0; i < N; i++) {
            x[i] = sc.nextInt();
            y[i] = sc.nextInt();
        }
        x[N] = x[0];
        y[N] = y[0];

        // 로직
        // 신발끈 공식
        // https://ko.wikipedia.org/wiki/%EC%8B%A0%EB%B0%9C%EB%81%88_%EA%B3%B5%EC%8B%9D
        for (int i = 0; i < N; i++) {
            sumA += x[i] * y[i + 1];
            sumB += x[i + 1] * y[i];
        }

//        String area = String.format("%.1f", (Math.abs(sum_a - sum_b) / 2.0));
        res = String.format("%.1f", (Math.abs(sumA - sumB) / 2.0));

        // 출력
        System.out.println(res);
    }


}
