import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

// 1. BOJ 1260-DFS와BFS
public class Main {

	static int N, M, V;
	static int[][] arr;
	static boolean[] visit;

	public static void main(String[] args) {
		// 정점의 개수 N
		// 간선의 개수 M
		// 탐색을 시작할 정점의 번호 V

		int r, c;

		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		M = sc.nextInt();
		V = sc.nextInt();
		arr = new int[N + 1][N + 1];
		visit = new boolean[N + 1];

		for (int i = 0; i < M; i++) {
			r = sc.nextInt();
			c = sc.nextInt();

			arr[r][c] = 1;
			arr[c][r] = 1;
		}

//		// 배열 확인
//		for(int i = 0; i <= N; i++) {
//			for(int j = 0; j <= N; j++) {
//				System.out.print(arr[i][j] + " ");
//			}
//			System.out.println();
//		}

		dfs(V);
		
		System.out.println();
		visit = new boolean[N + 1];
		
		bfs(V);

	}

	public static void dfs(int num) {
		visit[num] = true;
		System.out.print(num + " ");
		
		for(int i = 0; i <= N; i++) {
			if(arr[num][i] == 1 && visit[i] == false) {
				dfs(i);
			}
		}
		
		return;
	}

	public static void bfs(int num) {
		Queue<Integer> queue = new LinkedList<>();
		int idx;
		
		visit[num] = true;
		queue.add(num);
		
		while(queue.size() > 0) {
			idx = queue.poll();
			
			System.out.print(idx + " ");
			for(int i = 0; i <= N; i++) {
				if(arr[idx][i] == 1 && visit[i] == false) {
					visit[i] = true;
					queue.add(i);
				}
			}
		}
	}

}
