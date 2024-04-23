import java.util.*;

public class Main {
    public static void main(String[] args) {
        // 오큰수란?
        // A 보다 오른쪽 + 큰 첫번째 수

        Scanner sc = new Scanner(System.in);

        Stack<Integer> stack = new Stack<>();

        int N = sc.nextInt();
        int[] arr = new int[N];

        for (int i = 0; i < N; i++) {
            arr[i] = sc.nextInt();
        }

        for (int i = 0; i < N; i++) {
            // 넣기 전에 이전 값들 중 더 작은게 있는지 검사
            while (!stack.isEmpty() && arr[stack.peek()] < arr[i]) {
                arr[stack.pop()] = arr[i];
            }

            // 값을 넣을거임
            stack.push(i);
        }

        // 남은 놈들(자기보다 우측에 큰 값이 없다)은 -1 로 초기화
        while (!stack.isEmpty()) {
            arr[stack.pop()] = -1;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            sb.append(arr[i] + " ");
        }

        System.out.println(sb);
    }
}
