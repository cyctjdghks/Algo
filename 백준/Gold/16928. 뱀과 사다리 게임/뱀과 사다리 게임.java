import org.w3c.dom.Node;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
    static int N, M;
    static int[] move;
    static int[] dijk;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        M = sc.nextInt();
        move = new int[100 + 1];
        dijk = new int[100 + 1];
        Arrays.fill(dijk, Integer.MAX_VALUE);
        for(int i = 0; i < N + M; i++) {
            int x = sc.nextInt();
            int y = sc.nextInt();
            move[x] = y;
        }

        bfs();

        System.out.println(dijk[100]);
    }

    private static void bfs() {
        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(1, 0));
        dijk[1] = 0;

        while (!q.isEmpty()) {
            Node node = q.poll();

            for(int i = 1; i <= 6; i++) {
                int nl = node.location + i;
                if(nl > 100) {
                    continue;
                }

                if(move[nl] != 0) {
                    if(node.time+1 < dijk[move[nl]]) {
                        q.offer(new Node(move[nl], node.time + 1));
                        dijk[move[nl]] = node.time + 1;
                    }
                } else {
                    if(node.time+1 < dijk[nl]) {
                        q.offer(new Node(nl, node.time + 1));
                        dijk[nl] = node.time + 1;
                    }
                }
            }
        }
    }

    public static class Node {
        int location;
        int time;

        public Node(int location, int time) {
            this.location = location;
            this.time = time;
        }
    }
}
