import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

	static int N, M, W;
	static int[] dist;
	static ArrayList<Node>[] list;
	static final int INF = 9999_9999;

	public static void main(String[] args) {
		// 테스트 케이스 TC
		// 출발 위치로 돌아오면 YES 아니면 NO
		// 지점의 수 N - 정점
		// 도로의 개수 M - 간선
		// 웜홀의 개수 W
		// 도로 - S와 E는 연결된 지점의 번호, T는 걸리는 시간
		// 웜홀 - S는 시작 지점, E는 도착 지점, T는 줄어드는 시간
		// 도로는 방향이 없으며 웜홀은 방향이 있다.
		// 웜홀은 시작 위치에서 도착 위치로 가는 하나의 경로인데,
		// 특이하게도 도착을 하게 되면 시작을 하였을 때보다 시간이 뒤로 가게 된다.
		// 두 지점을 연결하는 도로가 한 개보다 많을 수도 있다.
		// 벨만-포드 알고리즘 사용
		// 벨만-포드 알고리즘이란 다익스트라 알고리즘과 유사

		int TC;
		int S, E, T;

		Scanner sc = new Scanner(System.in);

		TC = sc.nextInt();

		for (int tc = 1; tc <= TC; tc++) {
			N = sc.nextInt();
			M = sc.nextInt();
			W = sc.nextInt();

			dist = new int[N+1];
			Arrays.fill(dist, INF);
			list = new ArrayList[N + 1];

			for (int i = 1; i < N + 1; i++) {
				list[i] = new ArrayList<>();
			}

			// 각 도로의 정보
			for (int i = 0; i < M; i++) {
				S = sc.nextInt();
				E = sc.nextInt();
				T = sc.nextInt();

				list[S].add(new Node(E, T));
				list[E].add(new Node(S, T));
			}

			// 웜홀의 정보
			for (int i = 0; i < W; i++) {
				S = sc.nextInt();
				E = sc.nextInt();
				T = sc.nextInt();

				list[S].add(new Node(E, -T));
			}
			
//			// 리스트 확인
//			for(int i = 1; i <= N; i++) {
//				System.out.print("list[" + i + "] : ");
//				for(int j = 0; j < list[i].size(); j++) {
//					System.out.print("(" + list[i].get(j).e + ", "
//							+ list[i].get(j).cost + ")" + " ");
//				}
//				System.out.println();
//			}
			
			System.out.println(bellmanFord() ? "YES" : "NO");
		}

	}
	
	public static boolean bellmanFord() {
		dist[1] = 0;
		
		for(int i = 0; i < N; i++) {
			for(int j = 1; j < list.length; j++) {
				for(Node n: list[j]) {
					if(dist[j] + n.cost < dist[n.e]) {
						dist[n.e]= dist[j] + n.cost; 
					}
				}
			}
		}
		
		for(int i = 1; i < list.length; i++) {
			for(Node n: list[i]) {
				if(dist[i] + n.cost < dist[n.e]) {
					return true;
				}
			}
		}
		
		return false;
	}

	public static class Node {
		int e;
		int cost;

		public Node(int e, int cost) {
			this.e = e;
			this.cost = cost;
		}
	}

}
