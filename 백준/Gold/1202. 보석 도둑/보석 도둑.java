import java.util.*;

public class Main {
    static int N; // 보석의 갯수, N개의 줄 Mi, Vi
    static int K; // 가방의 갯수, K 개의줄 Ci
//    static int[] Mi; // 보석의 무게
//    static int[] Vi; // 보석의 가격
    static ArrayList<Jewel> list;
    static int[] Ci; // 가방이 담을 수 있는 최대 무게
    static long res;

    public static void main(String[] args) {
        // 가방에는 1개의 보석만 가능
        // 보석 가격의최대값

        // 입력
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        K = sc.nextInt();

        list = new ArrayList<>();
        Ci = new int[K];

        for (int i = 0; i < N; i++) {
            int mi = sc.nextInt();
            int vi = sc.nextInt();
            list.add(new Jewel(mi, vi));
        }

        for (int i = 0; i < K; i++) {
            Ci[i] = sc.nextInt();
        }
        res = 0;

        // 로직
        Arrays.sort(Ci);
        Collections.sort(list);

        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());

        // 보석은 무게에 대해 오름차순 정렬하되, 무게가 같을 경우 가격에 대해 내림차순 정렬
        // 가방은 오름차순 정렬
        // 모든 가방에 대해서 반복문을 수행
        for (int i = 0, j = 0; i < K; i++) {
            while (j < N && list.get(j).weight <= Ci[i]) {
                pq.offer(list.get(j).price);
                j++;
            }

            if (!pq.isEmpty()) {
                res += pq.poll();
            }
        }

        // 출력
        System.out.println(res);
    }

    static class Jewel implements Comparable<Jewel> {
        int weight;
        int price;

        public Jewel(int weight, int price) {
            this.weight = weight;
            this.price = price;
        }

        @Override
        public int compareTo(Jewel o) {
            if (this.weight == o.weight) {
                return o.price - this.price;
            }

            return this.weight - o.weight;
        }

        @Override
        public String toString() {
            return "Jewel{" +
                    "weight=" + weight +
                    ", price=" + price +
                    '}';
        }
    }
}
