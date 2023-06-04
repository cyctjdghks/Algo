import java.util.Arrays;
import java.util.Scanner;

public class Main {
    static int N, M;
    static int[] input;
    static int[] output;
    static StringBuilder sb;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        M = sc.nextInt();
        input = new int[N + 1];
        output = new int[M];
        for (int i = 1; i <= N; i++) {
            input[i] = sc.nextInt();
        }

        Arrays.sort(input);
        sb = new StringBuilder();

        perm(N, M, 1, 0);

        System.out.println(sb);
    }

    public static void perm(int n, int m, int start, int depth) {
        if(depth == m) {
            for(int i = 0; i < M; i++) {
                sb.append(output[i]).append(" ");
            }
            sb.append("\n");
            return;
        }

        for(int i = start; i <= n; i++) {
            if(input[i - 1] == input[i] ) continue;

            output[depth] = input[i];
            perm(n, m, i, depth + 1);
        }
    }
}
