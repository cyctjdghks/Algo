import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    static int T;
    static int F;
    static int[] parents;
    static int[] level;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        T = sc.nextInt();

        for (int tc = 1; tc <= T; tc++) {
            F = sc.nextInt();
            parents = new int[F * 2];
            level = new int[F * 2];

            init();

            int idx = 0;
            Map<String, Integer> map = new HashMap<>();

            for (int i = 0; i < F; i++) {
                String a = sc.next();
                String b = sc.next();

                if(!map.containsKey(a)) {
                    map.put(a, idx);
                    idx++;
                }
                if(!map.containsKey(b)) {
                    map.put(b, idx);
                    idx++;
                }

                System.out.println(union(map.get(a), map.get(b)));
            }
        }
    }

    private static int union(int a, int b) {
        a = find(a);
        b = find(b);

        if (a != b) {
            parents[b] = a;
            level[a] += level[b];

            level[b] = 1;
        }

        return level[a];
    }

    private static int find(int x) {
        if (x == parents[x]) {
            return x;
        } else {
            return parents[x] = find(parents[x]);
        }
    }

    private static void init() {
        for (int i = 0; i < F * 2; i++) {
            parents[i] = i;
            level[i] = 1;
        }
    }
}
