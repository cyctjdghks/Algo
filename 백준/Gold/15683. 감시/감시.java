import java.util.*;

public class Main {
    // 0은 빈 칸, 6은 벽, 1~5는 CCTV를 나타내고, 문제에서 설명한 CCTV의 종류이다.
    static int N, M;
    static int[][] map;
    static ArrayList<CCTV> list;
    static int res;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        M = sc.nextInt();
        res = N * M;
        map = new int[N][M];
        list = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                map[i][j] = sc.nextInt();

                if (1 <= map[i][j] && map[i][j] <= 5) {
                    list.add(new CCTV(i, j, map[i][j]));
                }
            }
        }

        search(0, map);

        System.out.println(res);
    }

    private static void search(int index, int[][] nMap) {
        if (index == list.size()) {
            int cnt = 0;

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (nMap[i][j] == 0) cnt++;
                }
            }

            res = Math.min(res, cnt);

            return;
        }

        CCTV cctv = list.get(index);
        int x = cctv.x;
        int y = cctv.y;
        int type = cctv.type;
        int[][] vMap = new int[N][M];

        switch (type) {
            case 1:
                for (int d = 1; d <= 4; d++) {
                    for (int i = 0; i < N; i++) {
                        vMap[i] = Arrays.copyOf(nMap[i], M);
                    }

                    Init(x, y, vMap, d);
                    search(index + 1, vMap);
                }
                break;
            case 2:
                for (int d = 1; d <= 2; d++) {
                    for (int i = 0; i < N; i++) {
                        vMap[i] = Arrays.copyOf(nMap[i], M);
                    }

                    Init(x, y, vMap, d);
                    Init(x, y, vMap, d + 2);
                    search(index + 1, vMap);
                }
                break;
            case 3:
                for (int d = 1; d <= 4; d++) {
                    for (int i = 0; i < N; i++) {
                        vMap[i] = Arrays.copyOf(nMap[i], M);
                    }

                    Init(x, y, vMap, d);
                    Init(x, y, vMap, d - 1 <= 0 ? d - 1 + 4 : d - 1);
                    search(index + 1, vMap);
                }
                break;
            case 4:
                for (int d = 1; d <= 4; d++) {
                    for (int i = 0; i < N; i++) {
                        vMap[i] = Arrays.copyOf(nMap[i], M);
                    }

                    Init(x, y, vMap, d);
                    Init(x, y, vMap, d - 1 <= 0 ? d - 1 + 4 : d - 1);
                    Init(x, y, vMap, d - 2 <= 0 ? d - 2 + 4 : d - 2);
                    search(index + 1, vMap);
                }
                break;
            case 5:
                for (int i = 0; i < N; i++) {
                    vMap[i] = Arrays.copyOf(nMap[i], M);
                }

                Init(x, y, vMap, 1);
                Init(x, y, vMap, 2);
                Init(x, y, vMap, 3);
                Init(x, y, vMap, 4);
                search(index + 1, vMap);
                break;
            default:
                break;
        }

    }

    private static void Init(int x, int y, int[][] cMap, int d) {
        switch (d) {
            case 1:
                for (int k = y; k < M; k++) {
                    if (cMap[x][k] == 6) {
                        break;
                    }
                    if (cMap[x][k] <= 0) {
                        cMap[x][k] = -1;
                    }
                }
                break;
            case 2:
                for (int k = x; k < N; k++) {
                    if (cMap[k][y] == 6) {
                        break;
                    }
                    if (cMap[k][y] <= 0) {
                        cMap[k][y] = -1;
                    }
                }
                break;
            case 3:
                for (int k = y; k >= 0; k--) {
                    if (cMap[x][k] == 6) {
                        break;
                    }
                    if (cMap[x][k] <= 0) {
                        cMap[x][k] = -1;
                    }
                }
                break;
            case 4:
                for (int k = x; k >= 0; k--) {
                    if (cMap[k][y] == 6) {
                        break;
                    }
                    if (cMap[k][y] <= 0) {
                        cMap[k][y] = -1;
                    }
                }
                break;
            default:
                break;
        }
    }

    static class CCTV {
        int x, y;
        int type;

        public CCTV(int x, int y, int type) {
            this.x = x;
            this.y = y;
            this.type = type;
        }
    }
}
