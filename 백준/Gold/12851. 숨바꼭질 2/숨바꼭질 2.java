import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
    // 걷는다면 1초 후에 X-1 또는 X+1로 이동
    // 순간이동을 하는 경우에는 1초 후에 2*X의 위치로 이동
    // 첫째 줄에 수빈이가 동생을 찾는 가장 빠른 시간을 출력한다.
    // 둘째 줄에는 가장 빠른 시간으로 수빈이가 동생을 찾는 방법의 수를 출력한다.
    static int N, K;
    static int[] time = new int[100001];
    static int resTime = 9999_9999;
    static int resCount = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        K = sc.nextInt();

        if (N >= K) { // 동생이 뒤에 있으면 뒤로만 가야됨
            System.out.println(N - K);
            System.out.println(1);
            return;
        }
        
        bfs();

        System.out.println(resTime);
        System.out.println(resCount);
    }

    private static void bfs() {
        Queue<Integer> q = new LinkedList<Integer>();

        q.offer(N);
        time[N] = 1;

        while (!q.isEmpty()) {
            int now = q.poll();

            // now 방문 시간이 최소 시간보다 크면 뒤는 더 볼 필요 없음
            if (resTime < time[now]) return;

            for (int i = 0; i < 3; i++) {
                int next;

                if (i == 0) next = now + 1;
                else if (i == 1) next = now - 1;
                else next = now * 2;

                if (next < 0 || next > 100000) continue;

                if (next == K) {
                    resTime = time[now];
                    resCount++;
                }

                // 첫 방문이거나 (time[next] == 0)
                // 이미 방문한 곳이어도 같은 시간에 방문했다면 (time[next] == time[now] + 1)
                // 경우의 수에 추가될 수 있기 때문에 Queue 에 한번 더 넣어줌
                if (time[next] == 0 || time[next] == time[now] + 1) {
                    q.offer(next);
                    time[next] = time[now] + 1;
                }
            }
        }
    }

    public static class Data {
        int index; // 현재 위치
        int time; // 지난 시간

        public Data(int index, int time) {
            this.index = index;
            this.time = time;
        }
    }
}
