import java.util.*;

public class Main {
    static int N, M;
    static ArrayList<Node>[] list;
    static int A, B;
    static int[] d;
    static boolean[] v;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        M = sc.nextInt();
        list = new ArrayList[N + 1];
        d = new int[N + 1];
        v = new boolean[N + 1];
        Arrays.fill(d, Integer.MAX_VALUE);
        for (int i = 1; i <= N; i++) {
            list[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            int start = sc.nextInt();
            int end = sc.nextInt();
            int weight = sc.nextInt();
            list[start].add(new Node(end, weight));
        }

        A = sc.nextInt();
        B = sc.nextInt();

        dijk(A);

        System.out.println(d[B]);
    }

    private static void dijk(int start) {
        PriorityQueue<Node> q = new PriorityQueue<>();
        q.offer(new Node(start, 0));
        d[start] = 0;

        while (!q.isEmpty()) {
            Node node = q.poll();

            if(!v[node.next]) {
                v[node.next] = true;

                for (Node n : list[node.next]) {
                    if (node.weight + n.weight < d[n.next] && node.weight + n.weight < d[B]) {
                        q.offer(new Node(n.next, node.weight + n.weight));
                        d[n.next] = node.weight + n.weight;
                    }
                }
            }
        }
    }

    public static class Node implements Comparable<Node> {
        int next;
        int weight;

        public Node(int next, int weight) {
            this.next = next;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node o) {
            return weight - o.weight;
        }
    }
}
