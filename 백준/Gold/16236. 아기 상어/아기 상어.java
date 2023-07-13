import java.util.*;

public class Main {
    static int N;
    static int[][] map;
    static boolean[][] v;
    static Fish baby_shark;
    static ArrayList<Fish> feed_list;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};
    // 상 좌 하 우
    static int eaten = 0;
    static int res;

    public static void main(String[] args) {
        // 입력
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();

        map = new int[N][N];
        feed_list = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                map[i][j] = sc.nextInt();
                if (map[i][j] == 9) {
                    baby_shark = new Fish(i, j, 2, 0);
                    map[i][j] = 0;
                }
            }
        }
        res = 0;

        // 로직
        search();

        // 출력
        System.out.println(res);
    }

    private static void search() {
        // 더 이상 먹을 수 있는 물고기가 공간에 없다면 아기 상어는 엄마 상어에게 도움을 요청한다.
        // 먹을 수 있는 물고기가 1마리보다 많다면, 거리가 가장 가까운 물고기를 먹으러 간다.
        // 자신의 크기보다 큰 물고기가 있는 칸은 지나갈 수 없고, 나머지 칸은 모두 지나갈 수 있다.
        // 아기 상어는 자신의 크기보다 작은 물고기만 먹을 수 있다.
        // 따라서, 크기가 같은 물고기는 먹을 수 없지만, 그 물고기가 있는 칸은 지나갈 수 있다.
        // 거리가 가까운 물고기가 많다면, 가장 위에 있는 물고기, 그러한 물고기가 여러마리라면, 가장 왼쪽에 있는 물고기를 먹는다.
        // 아기 상어는 자신의 크기와 같은 수의 물고기를 먹을 때 마다 크기가 1 증가한다.
        // 예를 들어, 크기가 2인 아기 상어는 물고기를 2마리 먹으면 크기가 3이 된다.

        while (true) {
            v = new boolean[N][N];
            Queue<Fish> q = new LinkedList<>();

            q.offer(baby_shark);
            v[baby_shark.x][baby_shark.y] = true;

            while (!q.isEmpty()) {
                Fish fish = q.poll();
                // 일단 퍼트림, 위 > 왼 우선순위
                for (int d = 0; d < 4; d++) {
                    int nx = fish.x + dx[d];
                    int ny = fish.y + dy[d];

                    // 이동 시 같거나 작은 애들 있는 곳으로만 이동 가능
                    if (isInBound(nx, ny)) {
                        if (!v[nx][ny] && map[nx][ny] <= fish.size) {
                            q.offer(new Fish(nx, ny, fish.size, fish.cnt + 1));
                            v[nx][ny] = true;
                            // 작은 놈을 만나면 걔를 잡아먹음(여기서 카운트)
                            // 만약 카운트가 지금의 크기와 같다면 크기 증가
                            if (map[nx][ny] != 0 && map[nx][ny] < fish.size) {
                                // 크기가 작다면 잡아먹기
                                feed_list.add(new Fish(nx, ny, fish.size, fish.cnt + 1));
                            }
                        }
                    }

                }

                // 먹은 위치에서 다시 탐색
            }

            // 더이상 먹을게 없다면 종료
            if (!feed_list.isEmpty()) {
                eat();
            } else {
                break;
            }
        }

    }

    private static void eat() {
        Collections.sort(feed_list);

        Fish fish = feed_list.get(0);

        baby_shark.x = fish.x;
        baby_shark.y = fish.y;
        eaten++;

        if (eaten == baby_shark.size) {
            baby_shark.size++;
            eaten = 0;
        }

        res += fish.cnt;

        map[fish.x][fish.y] = 0;

        feed_list.clear();
    }

    public static class Fish implements Comparable<Fish> {
        int x;
        int y;
        int size;
        int cnt;

        public Fish(int x, int y, int size, int cnt) {
            this.x = x;
            this.y = y;
            this.size = size;
            this.cnt = cnt;
        }

        @Override
        public int compareTo(Fish o) {
            if (this.cnt == o.cnt) {
                if (this.x == o.x) {
                    return this.y - o.y;
                } else {
                    return this.x - o.x;
                }
            } else {
                return this.cnt - o.cnt;
            }
        }
    }

    public static boolean isInBound(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < N;
    }
}
