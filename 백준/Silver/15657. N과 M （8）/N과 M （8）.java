import java.util.Arrays;
import java.util.Scanner;

public class Main {
    static int N, M;
    static int[] map;
    static int[] output;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        M = sc.nextInt();
        map = new int[N];
        for (int i = 0; i < N; i++) {
            map[i] = sc.nextInt();
        }
        output = new int[M];
        Arrays.sort(map);

        comb(N, M, 0, 0);
    }

    private static void comb(int n, int r, int start, int depth) {
        if(r == depth) {
            print();
            return;
        }

        for (int i = start; i < n; i++) {
            output[depth] = map[i];
            comb(n, r, i, depth + 1);
        }
    }

    private static void print() {
        for (int i = 0; i < M; i++) {
            System.out.print(output[i] + " ");
        }
        System.out.println();
    }
}
