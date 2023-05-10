import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static int R, C, T;
    static int[][] map;
    static ArrayList<Node> list;
    static ArrayList<Index_Node> dust;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    // y++ > x-- > y-- > x++
    static int[] cleaner1_dx = {0, -1, 0, 1};
    static int[] cleaner1_dy = {1, 0, -1, 0};
    // y++ > x++ > y-- > x--
    static int[] cleaner2_dx = {0, 1, 0, -1};
    static int[] cleaner2_dy = {1, 0, -1, 0};
    static int res;
    public static void main(String[] args) {
        // 공기청정기는 항상 1번 열에 설치
        // 크기는 두 행을 차지
        // 공기청정기가 설치되어 있지 않은 칸에는 미세먼지
        // (r, c)에 있는 미세먼지의 양은 Ar,c
        // 1초 동안 아래 적힌 일이 순서대로 일어난다.
        // 1. 미세먼지가 확산(모든 칸에서 동시)
        // (r, c)에 있는 미세먼지는 인접한 네 방향으로 확산
        // 인접한 방향에 공기청정기가 있거나, 칸이 없으면 그 방향으로는 확산이 일어나지 않는다.
        // 확산되는 양은 Ar,c/5이고 소수점은 버린다.
        // (r, c)에 남은 미세먼지의 양은 Ar,c - (Ar,c/5)×(확산된 방향의 개수)
        // 2. 공기청정기가 작동
        // 공기청정기에서는 바람
        // 위쪽 공기청정기의 바람은 반시계방향으로 순환
        // 아래쪽 공기청정기의 바람은 시계방향으로 순환
        // 바람이 불면 미세먼지가 바람의 방향대로 모두 한 칸씩 이동
        // 공기청정기에서 부는 바람은 미세먼지가 없는 바람이고, 공기청정기로 들어간 미세먼지는 모두 정화

        Scanner sc = new Scanner(System.in);

        R = sc.nextInt();
        C = sc.nextInt();
        T = sc.nextInt();
        map = new int[R][C];
        list = new ArrayList<>();
        res = 0;
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                map[i][j] = sc.nextInt();
                if (map[i][j] == -1) {
                    list.add(new Node(i, j));
                }
            }
        }

        for (int i = 0; i < T; i++) {
            // 미세먼지 확산
            dust = new ArrayList<>();
            search();
            // 공기청정기 작동
            cleaner();
        }

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (map[i][j] > 0) {
                    res += map[i][j];
                }
            }
        }

        System.out.println(res);
    }

    private static void print() {
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    private static void cleaner() {
        // 1번 공기청정기
        // dir
        // 0 > 1 > 2 > 3
        // c++ > r-- > c-- > r++
        // y++ > x-- > y-- > x++
        Node node1 = list.get(0);
        int x = node1.x;
        int y = node1.y;
        int dir = 0;
        int nx = x + cleaner1_dx[dir];
        int ny = y + cleaner1_dy[dir];
        int temp1 = 0;
        int temp2 = map[nx][ny];

        while (true) {
            if (nx == node1.x && ny == node1.y) {
                break;
            }

            temp2 = map[nx][ny];
            if(map[nx][ny] != -1) {
                map[nx][ny] = temp1;
            }
            temp1 = temp2;


            // 이동
            x = nx;
            y = ny;

            nx = x + cleaner1_dx[dir];
            ny = y + cleaner1_dy[dir];

            if (!isInBound(nx, ny)) {
                dir++;
                nx = x + cleaner1_dx[dir];
                ny = y + cleaner1_dy[dir];
            }
        }
        // 2번 공기청정기
        // c++ > r++ > c-- > r--
        // y++ > x++ > y-- > x--
        Node node2 = list.get(1);
        x = node2.x;
        y = node2.y;
        dir = 0;
        nx = x + cleaner2_dx[dir];
        ny = y + cleaner2_dy[dir];
        temp1 = 0;
        temp2 = map[nx][ny];

        while (true) {
            if (nx == node2.x && ny == node2.y) {
                break;
            }

            temp2 = map[nx][ny];
            if(map[nx][ny] != -1) {
                map[nx][ny] = temp1;
            }
            temp1 = temp2;


            // 이동
            x = nx;
            y = ny;

            nx = x + cleaner2_dx[dir];
            ny = y + cleaner2_dy[dir];

            if (!isInBound(nx, ny)) {
                dir++;
                nx = x + cleaner2_dx[dir];
                ny = y + cleaner2_dy[dir];
            }
        }
    }

    private static void search() {
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (map[i][j] > 0) {
                    dust.add(new Index_Node(i, j, map[i][j]));
                }
            }
        }
        int size = dust.size();
        for (int i = 0; i < size; i++) {
            spread(dust.get(i).x, dust.get(i).y, dust.get(i).index);
        }
    }

    private static void spread(int i, int j, int index) {
        int cnt_spread = 0;
        int weight = index / 5;
        for (int d = 0; d < 4; d++) {
            int nx = i + dx[d];
            int ny = j + dy[d];
            // 범위 안에 있고 공기 청정기가 있는 위치가 아니라면
            if (isInBound(nx, ny) && map[nx][ny] >= 0) {
                map[nx][ny] += weight;
                cnt_spread++;
            }
        }
        map[i][j] -= weight * cnt_spread;
    }

    private static boolean isInBound(int x, int y) {
        return x >= 0 && x < R && y >= 0 && y < C;
    }

    static public class Node {
        int x, y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static public class Index_Node {
        int x, y;
        int index;

        public Index_Node(int x, int y, int index) {
            this.x = x;
            this.y = y;
            this.index = index;
        }
    }
}
