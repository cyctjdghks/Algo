import java.util.*;

public class Main {
    static int T;
    static String p;
    static int n;
    static String Xi;
    static String[] ni;
    static ArrayDeque<Integer> dq;
    static StringBuilder sb;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        T = sc.nextInt();
        sb = new StringBuilder();
        for (int tc = 1; tc <= T; tc++) {
            // 입력
            p = sc.next();
            n = sc.nextInt();
            Xi = sc.next();
            Xi = Xi.substring(1, Xi.length() - 1);
            ni = Xi.split(",");

            dq = new ArrayDeque<>();
            if(n > 0) {
                for (int i = 0; i < ni.length; i++) {
                    dq.add(Integer.parseInt(ni[i]));
                }
            }

            // 로직
            boolean dir = false;
            for (int i = 0; i < p.length(); i++) {
                char c = p.charAt(i);

                switch (c) {
                    case 'R' :
                        // 뒤집기
                        dir = !dir;
                        break;
                    case 'D' :
                        // 첫번째 버리기
                        // flase 면 앞에 삭제
                        // true 면 뒤에 삭제
                        if (!dir) {
                            dq.pollFirst();
                        } else {
                            dq.pollLast();
                        }
                        n--;
                        break;
                    default:
                        break;
                }
            }

            // 출력
            if (n < 0) {
                sb.append("error\n");
            } else {
                print(dir);
            }
        }

        System.out.println(sb);
    }

    private static void print(boolean dir) {
        sb.append("[");
        if (dir) {
            while (!dq.isEmpty()) {
                sb.append(dq.pollLast());

                if (dq.isEmpty()) {
                    break;
                } else {
                    sb.append(",");
                }
            }
        } else {
            while (!dq.isEmpty()) {
                sb.append(dq.pollFirst());

                if (dq.isEmpty()) {
                    break;
                } else {
                    sb.append(",");
                }
            }
        }
        sb.append("]\n");
    }
}
