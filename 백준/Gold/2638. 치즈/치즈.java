import javax.xml.crypto.Data;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
    static int N, M;
    static int[][] map; // 작업할 맵
    static int[][] map_copy; // 작업 이전의 맵
    static boolean[][] v;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static Queue<Data> cheese = new LinkedList<>();
    static int res;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        M = sc.nextInt();
        map = new int[N][M];
        map_copy = new int[N][M];
        v = new boolean[N][M];
        res = 0;

        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                map[i][j] = sc.nextInt();
                map_copy[i][j] = map[i][j];
            }
        }

//        print();

        while (true) {
            // step 0. 모든 지점이 0 이면(치즈가 엇으면) 종료
            // step 1. 0,0 에서 그래프 탐색을 통해 바깥과 맞닿은 곳을 찾는다.
            bfs();
            // step 2. 해당 지점들이 녹아 없어질 지점인지 확인
            int size = cheese.size();
            for(int i = 0; i < size; i++) {
                Data data = cheese.poll();
                if(isItMelt(data.x, data.y)) {
                    map[data.x][data.y] = 0;
                }
            }
            // 시간 경과
            res++;
            // step 3. 반복 -> 모든 지점이 0이 될때까지
            if(isItEnd()) {
                break;
            }

            for(int i = 0; i < N; i++) {
                for(int j = 0; j < M; j++) {
                    map_copy[i][j] = map[i][j];
                }
            }
        }

        System.out.println(res);
    }

    private static void bfs() {
        Queue<Data> q = new LinkedList<>();
        v = new boolean[N][M];

        q.offer(new Data(0, 0));
        v[0][0] = true;

        while(!q.isEmpty()) {
            Data data = q.poll();

            for(int d = 0; d < 4; d++) {
                int nx = data.x + dx[d];
                int ny = data.y + dy[d];

                if(isInBound(nx, ny) && !v[nx][ny]) {
                    if(map[nx][ny] == 0) {
                        q.offer(new Data(nx, ny));
                        v[nx][ny] = true;
                    } else if(map[nx][ny] == 1) {
                        cheese.offer(new Data(nx, ny));
                        v[nx][ny] = true;
                    }
                }

            }
        }
    }

    private static boolean isInBound(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < M;
    }

    private static boolean isItEnd() {
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                if(map[i][j] == 1) {
                    return false;
                }
            }
        }
        return true;
    }

    private static boolean isItMelt(int x, int y) {
        int cnt = 0;

        for(int d = 0; d < 4; d++) {
            int nx = x + dx[d];
            int ny = y + dy[d];

            if(map_copy[nx][ny] == 0 && v[nx][ny]) {
                cnt++;
                if(cnt >= 2) {
                    return true;
                }
            }
        }

        return false;
    }

    private static void print() {
        System.out.println();
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static class Data {
        int x, y;

        public Data(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
