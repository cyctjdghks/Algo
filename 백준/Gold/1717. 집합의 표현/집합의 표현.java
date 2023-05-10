import java.util.Scanner;

public class Main {
    static int N, M;
    static int[] parent;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        M = sc.nextInt();
        parent = new int[N + 1];

        init();

        for (int i = 0; i < M; i++) {
            int x = sc.nextInt();
            int a = sc.nextInt();
            int b = sc.nextInt();

            if (x == 0) {
                union(a, b);
            } else if (x == 1) {
                boolean ans = isEqual(a, b);
                System.out.println(ans ? "YES" : "NO");
            }
        }
    }

    private static void init() {
        for (int i = 0; i <= N; i++) {
            parent[i] = i;
        }
    }

    private static int find(int x) {
        if (x == parent[x]) {
            return x;
        } else {
            return parent[x] = find(parent[x]);
        }
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

        if (a < b) {
            parent[b] = a;
        } else {
            parent[a] = b;
        }

    }
}
