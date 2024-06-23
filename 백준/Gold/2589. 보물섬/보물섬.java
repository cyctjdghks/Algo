import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
    static int R, C;
    static char[][] map;
    static boolean[][] v;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    static int answer;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        R = sc.nextInt();
        C = sc.nextInt();

        map = new char[R][C];
        v = new boolean[R][C];

        for (int i = 0; i < R; i++) {
            String str = sc.next();
            for (int j = 0; j < C; j++) {
                map[i][j] = str.charAt(j);
            }
        }

        for(int i=0; i<R; i++) {
            for(int j=0; j<C; j++) {
                if(map[i][j] == 'L') {
                    v = new boolean[R][C];
                    int val = bfs(i,j);
                    answer = Math.max(answer, val);

                }
            }
        }

        System.out.println(answer);

    }

    private static int bfs(int i, int j) {
        Queue<Po> queue = new LinkedList<>();
        int val = 0;
        v[i][j] = true;
        queue.add(new Po(j,i,0));
        while(!queue.isEmpty()) {
            Po p = queue.poll();
            for(int d=0; d<4; d++) {
                int newX = p.x + dx[d];
                int newY = p.y + dy[d];
                if(0<=newX && newX<C && 0<=newY && newY<R && !v[newY][newX] && map[newY][newX]=='L') {
                    v[newY][newX] = true;
                    queue.add(new Po(newX, newY, p.cnt+1));
                    val = Math.max(val, p.cnt+1);
                }
            }
        }
        return val;
    }
    public static class Po{
        int x;
        int y;
        int cnt;
        public Po(int x, int y, int cnt) {
            this.x = x;
            this.y = y;
            this.cnt = cnt;
        }
    }
}
