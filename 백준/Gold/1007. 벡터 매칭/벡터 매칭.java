import java.util.Scanner;

public class Main {
    static int T;
    static int N;
    static int[][] arr;
    static boolean[] v;
    static double res;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        T = sc.nextInt();

        for (int tc = 1; tc <= T; tc++) {
            N = sc.nextInt();
            arr = new int[N][2];
            v = new boolean[N];
            int sumx = 0;
            int sumy = 0;

            for (int i = 0; i < N; i++) {
                arr[i][0] = sc.nextInt();
                arr[i][1] = sc.nextInt();
                sumx += arr[i][0];
                sumy += arr[i][1];
            }

            res = Double.MAX_VALUE;

            rec(0, 0, sumx, sumy);

            System.out.println(res);
        }
    }

    private static void rec(int cnt, int prev, int x, int y) {
        if (cnt == N / 2) {
            res = Math.min(res, Math.sqrt((double) x * x + (double) y * y));
            return;
        }

        for (int i = prev; i < N; i++) {
            rec(cnt + 1, i + 1, x - 2 * arr[i][0], y - 2 * arr[i][1]);
        }
    }
}
