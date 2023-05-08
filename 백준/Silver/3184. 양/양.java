import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
    static int R, C;
    static String[][] map;
    static boolean[][] v;
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};
    static int res_y;
    static int res_w;
    public static void main(String[] args) {
        // 글자 '.' (점)은 빈 필드
        // 글자 '#'는 울타리를
        // 'o'는 양
        // 'v'는 늑대
        // 한 칸에서 수평, 수직만으로 이동

        Scanner sc = new Scanner(System.in);

        R = sc.nextInt();
        C = sc.nextInt();
        res_y = 0;
        res_w = 0;
        map = new String[R][C];
        v = new boolean[R][C];

        for (int i = 0; i < R; i++) {
            String str = sc.next();
            for (int j = 0; j < C; j++) {
                map[i][j] = String.valueOf(str.charAt(j));
                if (map[i][j].equals("#")) {
                    v[i][j] = true;
                }
            }
        }

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (!v[i][j]) {
                    bfs(i, j);
                }
            }
        }

        System.out.println(res_y + " " + res_w);
    }

    private static void bfs(int x, int y) {
        int yang = 0;
        int wolf = 0;

        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(x, y));
        v[x][y] = true;
        if (map[x][y].equals("o")) {
            yang++;
        } else if (map[x][y].equals("v")) {
            wolf++;
        }

        while (!q.isEmpty()) {
            Node node = q.poll();

            for (int d = 0; d < 4; d++) {
                int nx = node.x + dx[d];
                int ny = node.y + dy[d];

                if (isInBound(nx, ny)) {
                    if (!v[nx][ny] && !map[nx][ny].equals("#")) {
                        q.offer(new Node(nx, ny));
                        v[nx][ny] = true;
                        if (map[nx][ny].equals("o")) {
                            yang++;
                        } else if (map[nx][ny].equals("v")) {
                            wolf++;
                        }
                    }
                }
            }
        }

        if (yang > wolf) {
            res_y += yang;
        } else if (wolf >= yang) {
            res_w += wolf;
        }
    }

    private static boolean isInBound(int x, int y) {
        return x >= 0 && x < R && y >= 0 && y < C;
    }

    static public class Node {
        int x;
        int y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
