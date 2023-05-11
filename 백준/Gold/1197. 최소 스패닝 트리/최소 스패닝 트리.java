import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Main {
    static int V, E;
    static int[] parents;
    static ArrayList<Node> list;
    static int res;

    public static void main(String[] args) {
        // ✅ 크루스칼 알고리즘
        // 간선의 가중치의 합이 최솟값이 되도록 모든 노드를 연결
        // 간선 데이터를 오름차순 정렬
        // 간선을 확인하며 현 간선이 사이클을 발생시키는지 확인
        // 발생시키지 않으면 MST에 포함
        // 모든 간선에 대해 반복한다.
        //
        // ✅ 프림 알고리즘
        // 시작 정점을 기준으로 가장 작은 간선과 연결된 정점을 선택하여 모든 노드를 연결
        // 임의의 간선 선택
        // 현 정점으로부터 가장 낮은 가중치를 갖는 정점 선택
        // 모든 간선에 대해 반복한다.

        Scanner sc = new Scanner(System.in);

        V = sc.nextInt();
        E = sc.nextInt();
        parents = new int[V + 1];
        list = new ArrayList<>();
        res = 0;

        for (int i = 0; i < E; i++) {
            int x = sc.nextInt();
            int y = sc.nextInt();
            int w = sc.nextInt();

            list.add(new Node(x, y, w));
        }

        Collections.sort(list);

        init();

        int cnt = 0;

        for (Node node : list) {
            if (union(node.from, node.to)) {
                res += node.weight;
                cnt++;

                if(cnt == E - 1) break;
            }
        }

        System.out.println(res);
    }

    private static boolean union(int from, int to) {
        int fromRoot = find(from);
        int toRoot = find(to);

        if (fromRoot == toRoot) return false;
        else parents[toRoot] = fromRoot;
        return true;
    }

    private static int find(int x) {
        if (parents[x] == x) {
            return x;
        } else {
            return parents[x] = find(parents[x]);
        }
    }

    private static void init() {
        for (int i = 1; i <= V; i++) {
            parents[i] = i;
        }
    }

    public static class Node implements Comparable<Node> {
        int from;
        int to;
        int weight;

        public Node(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node o) {
            return this.weight - o.weight;
        }
    }
}
