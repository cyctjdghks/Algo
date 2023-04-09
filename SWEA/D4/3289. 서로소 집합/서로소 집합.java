import java.util.Scanner;

// 3. SWEA 3289 서로소집합
public class Solution {

	static int N;
	static int[] parents;
	
	public static void main(String[] args) {
		// 테스트 케이스 T
		// n 과 m
		// 1 ~ n 이 각각 n 개의 집합
		// m 은 입력으로 주어지는 연산의 개수
		// 합집합은 0 a b 의 형태
		// a 가 포함되어 있는 집합과 b 가 포함되어 있는 집합을 합침
		// 두 원소가 같은 집합에 포함여부 확인 연산
		// 1 a b
		// 1 로 시작하는 입력에 대해 속해있다면 1, 아니면 0을 순서대로 한줄에 연속으로 출력
		
		int T;
		int M;
		int command, a, b;
		StringBuilder sb = new StringBuilder();
		Scanner sc = new Scanner(System.in);
		
		T = sc.nextInt();
		
		for(int tc = 1; tc <= T; tc++) {
			sb.append("#").append(tc).append(" ");
			N = sc.nextInt();
			M = sc.nextInt();
			
			parents = new int[N+1];
			make();
			
			for(int i = 0; i < M; i ++) {
				command = sc.nextInt();
				a = sc.nextInt();
				b = sc.nextInt();
				
				if(command == 0) {
					union(a, b);
				} else if(command == 1) {
					if(find(a) == find(b)) {
						sb.append(1);
					} else {
						sb.append(0);
					}
				}
			}
			
			sb.append("\n");
		}
		System.out.println(sb);
	}
	public static void make() {
		for(int i = 0; i < N+1; i++) {
			parents[i] = i;
		}
	}
	
	public static boolean union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		if(aRoot == bRoot) {
			return false;
		}
		parents[bRoot] = aRoot;
		return true;
	}
	
	public static int find(int a) {
		if(a == parents[a]) {
			return a;
		}
		return parents[a] = find(parents[a]);
	}

}
