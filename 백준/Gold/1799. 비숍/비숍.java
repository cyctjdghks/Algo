import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static int N;
    static int[][] map;
    static boolean[][] v, isBlack;
    static int[] dx = {-1, -1};
    static int[] dy = {1, -1};
    // 우상 우하 좌상 좌하
    static int[] res;

    public static void main(String[] args) {
        // 비숍을 놓을 수 있는 곳에는 1, 비숍을 놓을 수 없는 곳에는 0이 빈칸을 사이에 두고 주어진다.

        // 입력
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        map = new int[N][N];
        v = new boolean[N][N];
        isBlack = new boolean[N][N];
        res = new int[2];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                map[i][j] = sc.nextInt();
                isBlack[i][j] = (i % 2 == 0 && j % 2 == 0) || (i % 2 == 1 && j % 2 == 1);
            }
        }

        // 로직
        dfs(0,true,0); // black 칸의 비숍
        dfs(0,false,0); // white 칸의 비숍

        // 출력
        System.out.println(res[0] + res[1]);
    }

    static void dfs(int idx, boolean black, int cnt) {

        for (int i = idx; i < N * N; i++) {
            int x = i / N;
            int y = i % N;

            if (map[x][y] == 0 || isBlack[x][y] != black || !check(x, y))
                continue;

            v[x][y] = true;
            dfs(i + 1, black, cnt + 1);
            v[x][y] = false;
        }

        res[black ? 0 : 1] = Math.max(res[black ? 0 : 1], cnt);
    }

    static boolean check(int x, int y) {
        for (int d = 0; d < 2; d++){
            int nx = x;
            int ny = y;
            while (true) {
                if (isInBound(nx, ny)) {
                    if (v[nx][ny])
                        return false;

                    nx += dx[d];
                    ny += dy[d];
                } else {
                    break;
                }
            }
        }
        return true;
    }

    public static boolean isInBound(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < N;
    }
}
