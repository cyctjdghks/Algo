import java.util.Arrays;
import java.util.Scanner;

public class Main {
    static int N, M;
    static int[] output;
    public static void main(String[] args) {
        // 중복 조합

        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        M = sc.nextInt();
        output = new int[M];

        comb(M, N, 0, 0);
    }

    private static void comb(int n, int r, int start, int depth) {
        if (n == depth) {
            print();
            return;
        }

        for(int i = start; i < r; i++) {
            output[depth] = i + 1;
            comb(n, r, i, depth + 1);
        }

    }

    private static void print() {
        for(int i = 0; i < M; i++) {
            System.out.print(output[i] + " ");
        }
        System.out.println();
    }
}
