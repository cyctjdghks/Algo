import java.util.Arrays;
import java.util.Scanner;

public class Main {
    static int N, M;
    static int[][] map;
    static int[] parent;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        M = sc.nextInt();
        map = new int[N+1][N+1];
        parent = new int[N + 1];
        init();

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                map[i][j] = sc.nextInt();
                if(map[i][j] == 1) {
                    union(i, j);
                }
            }
        }

        int a = sc.nextInt();
        int b;

        for (int i = 0; i < M - 1; i++) {
            b = sc.nextInt();

            boolean ans = isEqual(a, b);
            if(!ans) {
                System.out.println("NO");
                return;
            }

            a = b;
        }

        System.out.println("YES");
    }

    private static boolean isEqual(int a, int b) {
        a = find(a);
        b = find(b);

        if(a == b) return true;
        else return false;
    }

    private static void union(int a, int b) {
        a = find(a);
        b = find(b);

        if(a < b) parent[b] = a;
        else parent[a] = b;
    }

    private static int find(int x) {
        if (x == parent[x]) {
            return x;
        } else {
            return parent[x] = find(parent[x]);
        }
    }

    private static void init() {
        for (int i = 1; i <= N; i++) {
            parent[i] = i;
        }
    }
}
