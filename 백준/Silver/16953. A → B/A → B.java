import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
    static long A, B;
    static int res;
    public static void main(String[] args) {
        // A 를 B로 바꿀려고 함.
        // 2를 곱한다.
        // 1을 수의 가장 오른쪽에 추가한다.

        Scanner sc = new Scanner(System.in);

        A = sc.nextInt();
        B = sc.nextInt();
        res = -1;

        bfs();

        System.out.println(res);
    }

    private static void bfs() {
        Queue<Data> q = new LinkedList<>();

        q.offer(new Data(A, 0));

        while (!q.isEmpty()) {
            Data data = q.poll();

            if(data.num == B) {
                res = data.operations + 1;
            }

            // * 2
            if(data.num * 2 <= B) {
                q.offer(new Data(data.num * 2, data.operations + 1));
            }
            // 1 추가
            if(data.num * 10 + 1 <= B) {
                q.offer(new Data(data.num * 10 + 1, data.operations + 1));
            }

        }
    }

    public static class Data {
        long num;
        int operations;

        public Data(long num, int operations) {
            this.num = num;
            this.operations = operations;
        }
    }
}

