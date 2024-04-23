import java.util.*;

public class Main {
    public static void main(String[] args) {

        // 리프 노드 = 자식이 없는 노드


        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int res = 0;

        boolean[] parents = new boolean[N]; // 부모 노드인지 여부를 저장하는 배열
        // 각 노드별 자신의 부모를 찾아갈 수 있는 2차원 리스트
        int[] arr = new int[N];

        for (int i = 0; i < N; i++) {
            int num = sc.nextInt();
            if(num != -1) parents[num] = true;
            arr[i] = num;
        }

        int delete = sc.nextInt();

        // 삭제하는 노드가 root 인 경우 0 return
        if (arr[delete] == -1) {
            System.out.println(0);
            return;
        }
        // 특정 노드를 삭제했을 떄 해당 부모 노드가 리프노드인지 판별
        int child = 0;
        for (int i = 0; i < N; i++) {
            if(i == delete) continue;
            if(arr[i] == arr[delete]) child++;
        }
        if (child == 0) {
            parents[arr[delete]] = false;
        }

        for (int i = 0; i < N; i++) {
            // 리프 노드일 떄 탐색
            if (!parents[i]) {
                if (dfs(i, arr, delete)) {
                    res++;
                }
            }
        }

        System.out.println(res);

    }

    private static boolean dfs(int i, int[] arr, int delete) {
        if (i == delete) {
            return false;
        }

        if (i == -1) {
            return true;
        }

        return dfs(arr[i], arr, delete);
    }

}
