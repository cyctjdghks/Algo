import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
    static int M, N, H;
    static int[][][] map;
    static boolean[][][] v;
    static int[] dx = {-1, 1, 0, 0, 0, 0};
    static int[] dy = {0, 0, -1, 1, 0, 0};
    static int[] dz = {0, 0, 0, 0, -1, 1};
    // 위 아래 왼쪽 오른쪽 앞 뒤
    static ArrayList<Point> tomato;
    static int res;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        M = sc.nextInt();
        N = sc.nextInt();
        H = sc.nextInt();

        map = new int[N][M][H];
        v = new boolean[N][M][H];
        tomato = new ArrayList<>();
        int cnt_toma = 0;
        res = 0;

        for (int h = 0; h < H; h++) {
            for (int n = 0; n < N; n++) {
                for (int m = 0; m < M; m++) {
                    map[n][m][h] = sc.nextInt();
                    if (map[n][m][h] == 1) {
                        tomato.add(new Point(n, m, h));
                    } else if (map[n][m][h] == 0) {
                        cnt_toma++;
                    }
                }
            }
        }
        if (cnt_toma == 0) {
            System.out.println(res);
            return;
        }


        bfs();

        print();
    }

    private static void bfs() {
        // 정수 1은 익은 토마토, 정수 0 은 익지 않은 토마토, 정수 -1은 토마토가 들어있지 않은 칸
        Queue<Point> q = new LinkedList<>();
        for (int i = 0; i < tomato.size(); i++) {
            Point point = tomato.get(i);
            q.offer(point);
            v[point.x][point.y][point.z] = true;
        }

        int day = 0;
        while (!q.isEmpty()) {
            int size = q.size();

            for(int s = 0; s < size; s++) {
                Point point = q.poll();

                for (int d = 0; d < 6; d++) {
                    int nx = point.x + dx[d];
                    int ny = point.y + dy[d];
                    int nz = point.z + dz[d];

                    if (isInBound(nx, ny, nz)) {
                        if (!v[nx][ny][nz] && map[nx][ny][nz] == 0) {
                            map[nx][ny][nz] = 1;
                            q.offer(new Point(nx, ny, nz));
                            v[nx][ny][nz] = true;
                        }
                    }
                }
            }
            day++;
        }

        day--;
        res = day;
    }

    public static boolean isInBound(int x, int y, int z) {
        return x >= 0 && x < N && y >= 0 && y < M && z >= 0 && z < H;
    }

    private static void print() {
        int cnt = 0;

        for (int h = 0; h < H; h++) {
            for (int n = 0; n < N; n++) {
                for (int m = 0; m < M; m++) {
                    if(map[n][m][h] == 0) cnt++;
                }
            }
        }

        if (cnt != 0) {
            res = -1;
            System.out.println(res);
        } else {
            System.out.println(res);
        }
    }

    public static class Point {
        int x;
        int y;
        int z;

        public Point(int x, int y, int z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }
    }
}
