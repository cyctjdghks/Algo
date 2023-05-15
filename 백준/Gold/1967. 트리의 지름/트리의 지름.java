import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static int N;
    static ArrayList<Node>[] list;
    static boolean[] v;
    static int max_idx;
    static int res;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        list = new ArrayList[N + 1];
        max_idx = 0;
        res = 0;
        for (int i = 0; i <= N; i++) {
            list[i] = new ArrayList<>();
        }

        for (int i = 0; i < N - 1; i++) {
            int start = sc.nextInt();
            int end = sc.nextInt();
            int weight = sc.nextInt();

            list[start].add(new Node(end, weight));
            list[end].add(new Node(start, weight));
        }

        v = new boolean[N + 1];
        v[1] = true;
        dfs(1, 0);

        v = new boolean[N + 1];
        v[max_idx] = true;
        dfs(max_idx, 0);

        System.out.println(res);
    }

    private static void dfs(int idx, int weight) {
        if (res < weight) {
            res = weight;
            max_idx = idx;
        }

        for (Node node : list[idx]) {
            if (!v[node.next]) {
                v[node.next] = true;
                dfs(node.next, weight + node.weight);
            }
        }
    }

    public static class Node {
        int next;
        int weight;

        public Node(int next, int weight) {
            this.next = next;
            this.weight = weight;
        }
    }
}
