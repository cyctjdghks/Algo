import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int M = sc.nextInt();

        int[] arr = new int[N];

        for (int i = 0; i < N; i++) {
            arr[i] = sc.nextInt();
        }

        Arrays.sort(arr);

        int res = 0;
        int left = 0;
        int right = N-1;

        while (left < right) {
            int sum = arr[left] + arr[right];

            if (sum < M) {
                left++;
            } else if (sum > M) {
                right--;
            } else {
                res++;
                left++;
                right--;
            }
        }

        System.out.println(res);
    }
}
