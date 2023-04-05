import javax.swing.text.Position;
import java.util.*;

public class Main {
    static int N, M, R;
    static int[] T;
    static ArrayList<Node>[] list;
    static int[] dijk;
//    static boolean[] v;
    static int res;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        M = sc.nextInt();
        R = sc.nextInt();
        T = new int[N + 1];
        list = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            T[i] = sc.nextInt();
            list[i] = new ArrayList<>();
        }

        for (int i = 0; i < R; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            int l = sc.nextInt();

            list[a].add(new Node(b, l));
            list[b].add(new Node(a, l));
        }

        res = 0;

        for(int i = 1; i <= N; i++) {
//            v = new boolean[N + 1];
            dijk = new int[N + 1];
            Arrays.fill(dijk, 9999_9999);
            bfs(i);
        }

        System.out.println(res);
    }

    private static void bfs(int start) {
        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(start, 0));
        dijk[start] = 0;
//        v[start] = true;

        while (!q.isEmpty()) {
            Node node = q.poll();
            int next_index = node.next;

                for(int i = 0; i < list[next_index].size(); i++) {
                    if (dijk[list[next_index].get(i).next] > dijk[next_index] + list[next_index].get(i).weight) {
                        dijk[list[next_index].get(i).next] = dijk[next_index] + list[next_index].get(i).weight;
                        q.offer(new Node(list[next_index].get(i).next, dijk[list[next_index].get(i).next]));
//                        v[list[next_index].get(i).next] = true;
                    }
                }

        }

        int max = 0;

        for(int i = 1; i <= N; i++) {
            if (dijk[i] <= M) {
                max += T[i];
            }
        }

        res = Math.max(res, max);
    }

    static class Node {
        int next;
        int weight;

        public Node(int next, int weight) {
            this.next = next;
            this.weight = weight;
        }
    }
}
