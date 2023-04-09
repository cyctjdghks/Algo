import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;

public class Solution {

	static int N, M;
	static int[] parent;

	public static void main(String[] args) {
		// 테스트 케이스 T
		// 마을 사람 수 N(정점)
		// 관계 M(간선)

		int T;
		int a, b;
		int count;

		Scanner sc = new Scanner(System.in);

		T = sc.nextInt();

		for (int tc = 1; tc <= T; tc++) {
			N = sc.nextInt();
			M = sc.nextInt();
			count = 0;

			parent = new int[N + 1];
			make();

			for (int i = 0; i < M; i++) {
				a = sc.nextInt();
				b = sc.nextInt();

				union(a, b);
//				System.out.println(Arrays.toString(parent));
			}
//			System.out.println();
			
			for(int i = 1; i < N+1; i++) {
				if(parent[i] == i) {
					count++;
				}
			}
			System.out.println("#" + tc + " " + count);
		}
	}

	// 상위 노드 초기화
	public static void make() {
		for (int i = 0; i < N + 1; i++) {
			parent[i] = i;
		}
	}

	// find
	public static int find(int a) {
		if (a == parent[a]) {
			return a;
		}
		return parent[a] = find(parent[a]);
	}

	// union
	public static void union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);

		if (aRoot != bRoot) {
			parent[bRoot] = aRoot;
		} else {
			return;
		}

	}

}
