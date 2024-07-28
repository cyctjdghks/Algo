import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {

    static int V, E, K; // 정점 개수, 간선 개수, 출발점
    static ArrayList<Node>[] list;
    static int[] dijk;
    static int INF = 300_000 * 10 + 1;

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();

        V = sc.nextInt();
        E = sc.nextInt();
        K = sc.nextInt();

        list = new ArrayList[V + 1];
        dijk = new int[V + 1];
        for (int i = 1; i <= V; i++) {
            list[i] = new ArrayList<>();
        }
        Arrays.fill(dijk, INF);

        for (int i = 0; i < E; i++) {
            int start = sc.nextInt();
            int end = sc.nextInt();
            int value = sc.nextInt();
            list[start].add(new Node(end, value));
        }

        dijkstra();

        for (int i = 1; i <= V; i++) {
            sb.append(dijk[i] == INF ? "INF" : dijk[i])
                    .append("\n");
        }

        System.out.println(sb);
    }

    private static void dijkstra() {
        PriorityQueue<Node> q = new PriorityQueue();
        q.offer(new Node(K, 0));
        dijk[K] = 0;

        while (!q.isEmpty()) {
            Node node = q.poll();

            int next = node.nxt;
            int value = node.val;

            if (value > dijk[next]) {
                continue;
            }

            int size = list[next].size();
            for (int i = 0; i < size; i++) {
                int nextIdx = list[next].get(i).nxt;
                int nextVal = list[next].get(i).val;

                if (value + nextVal < dijk[nextIdx]) {
                    q.offer(new Node(nextIdx, value + nextVal));
                    dijk[nextIdx] = value + nextVal;
                }
            }
        }
    }

    public static class Node implements Comparable<Node>{
        int nxt;
        int val;

        public Node(int nxt, int val) {
            this.nxt = nxt;
            this.val = val;
        }

        @Override
        public int compareTo(Node o) {
            return this.val - o.val;
        }
    }
}
