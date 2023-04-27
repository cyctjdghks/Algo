import java.util.Arrays;
import java.util.Scanner;

public class Main {
    static int N, M;
    static int[] arr;
    static boolean[] v;
    static int[] output;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        M = sc.nextInt();
        arr = new int[N];
        v = new boolean[N];
        output = new int[M];
        for (int i = 0; i < N; i++) {
            arr[i] = sc.nextInt();
        }

        Arrays.sort(arr);

        perm(N, M, 0);
    }

    private static void perm(int n, int m, int depth) {
        if (depth == m) {
            print();
            return;
        }

        int before = 0;

        for (int i = 0; i < n; i++) {
            if(v[i]) {
                continue;
            }

            if(before != arr[i]) {
                v[i] = true;
                output[depth] = arr[i];
                before = arr[i];
                perm(n, m, depth + 1);
                v[i] = false;
            }
        }
    }

    private static void print() {
        for (int i = 0; i < M; i++) {
            System.out.print(output[i] + " ");
        }
        System.out.println();
    }
}
