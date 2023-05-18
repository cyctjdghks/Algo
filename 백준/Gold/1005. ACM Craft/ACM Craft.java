import java.util.*;

public class Main {
    static int T;
    static int N, K, W;
    static ArrayList<Integer>[] list;
    static int[] D;
    static int[] indegree;
    static int[] dp;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        T = sc.nextInt();

        for (int tc = 1; tc <= T; tc++) {
            N = sc.nextInt();
            K = sc.nextInt();
            D = new int[N + 1];
            list = new ArrayList[N + 1];
            for (int i = 1; i <= N; i++) {
                D[i] = sc.nextInt();
                list[i] = new ArrayList<>();
            }

            indegree = new int[N + 1];
            for (int i = 0; i < K; i++) {
                int x = sc.nextInt();
                int y = sc.nextInt();
                list[x].add(y);
                indegree[y]++;
            }

            dp = new int[N + 1];
            W = sc.nextInt();

            sort();

            System.out.println(dp[W]);
        }
    }

    private static void sort() {
        Queue<Integer> q = new LinkedList<>();
        for (int i = 1; i <= N; i++) {
            if (indegree[i] == 0) {
                dp[i] = D[i];
                q.offer(i);
            }
        }

        while (!q.isEmpty()) {
            int idx = q.poll();

            for (int i = 0; i < list[idx].size(); i++) {
                int next = list[idx].get(i);

                dp[next] = Math.max(dp[idx] + D[next], dp[next]);
                indegree[next]--;
                if (indegree[next] == 0) {
                    q.offer(next);
                }
            }
        }
    }
}
