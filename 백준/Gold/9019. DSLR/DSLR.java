import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
    static int T;
    static int A, B;
    static boolean[] v;
    static StringBuilder sb;

    public static void main(String[] args) {
        // D: D 는 n을 두 배로 바꾼다. 결과 값이 9999 보다 큰 경우에는 10000 으로 나눈 나머지를 취한다.
        // 그 결과 값(2n mod 10000)을 레지스터에 저장한다.
        // S: S 는 n에서 1 을 뺀 결과 n-1을 레지스터에 저장한다. n이 0 이라면 9999 가 대신 레지스터에 저장된다.
        // L: L 은 n의 각 자릿수를 왼편으로 회전시켜 그 결과를 레지스터에 저장한다.
        // 이 연산이 끝나면 레지스터에 저장된 네 자릿수는 왼편부터 d2, d3, d4, d1이 된다.
        // R: R 은 n의 각 자릿수를 오른편으로 회전시켜 그 결과를 레지스터에 저장한다.
        // 이 연산이 끝나면 레지스터에 저장된 네 자릿수는 왼편부터 d4, d1, d2, d3이 된다.

        Scanner sc = new Scanner(System.in);

        T = sc.nextInt();
        sb = new StringBuilder();

        for (int tc = 1; tc <= T; tc++) {
            // 입력
            A = sc.nextInt();
            B = sc.nextInt();
            v = new boolean[10000];

            // 로직
            bfs();

        }
        // 출력
        System.out.println(sb);
    }

    private static void bfs() {
        Queue<Data> q = new LinkedList<>();
        String str = "";
        int n = 0;

        q.offer(new Data("", A));
        v[A] = true;

        while (!q.isEmpty()) {
            Data data = q.poll();

            for (int i = 0; i < 4; i++) {
                str = data.dslr;
                n = data.num;

                switch (i) {
                    // D
                    case 0:
                        n = n * 2 % 10000;
                        str += "D";
                        break;
                    // S
                    case 1:
                        if (n == 0) n = 9999;
                        else n -= 1;
                        str += "S";
                        break;
                    // L
                    case 2:
                        n = (n % 1000) * 10 + n / 1000;
                        str += "L";
                        break;
                    // R
                    case 3:
                        n = (n % 10) * 1000 + n / 10;
                        str += "R";
                        break;
                    default:
                        break;
                }

                if (n == B) {
                    sb.append(str + "\n");
                    q.clear();
                    break;
                } else {
                    if (!v[n]) {
                        q.offer(new Data(str, n));
                        v[n] = true;
                    }
                }
            }
        }

    }

    public static class Data {
        String dslr;
        int num;

        public Data(String dslr, int num) {
            this.dslr = dslr;
            this.num = num;
        }
    }
}
