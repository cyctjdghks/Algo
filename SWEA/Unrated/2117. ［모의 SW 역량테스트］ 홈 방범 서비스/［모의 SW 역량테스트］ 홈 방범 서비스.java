import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

// 3. SWEA 2117 홈방범서비스
public class Solution {

	static int N, M;
	static int[][] city;
	static boolean[][] visit;
	static int count; // 서비스 가능한 집 카운트
	static int cost; // 운영 비용
	static int max; // 최대 이익일때 집들의 수 - 출력값
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	// 상 하 좌 우
	
	public static void main(String[] args) {
		// 테스트 케이스 개수 T
		// 도시의 크기 N
		// 집이 지불할 수 있는 비용 M
		// N*N 도시정보
		// 서비스 영역 면적 K
		// 운영 비용 = K*K + (K-1)*(K-1)
		// 운영 면적의 크기 배열은 K일때 K + (K-1)
		// 도시의 크기가 N 일 때
		// 도시 전체를 집어 삼킬 수 있는 운영 면적
		// => N이 홀루면 K 는 최대 N
		// => N이 짝수면 K 는 최대 N+1
		// 따라서 K의 범위 : 1 <= K <= (N+1)
		// 이 문제를 어떻게 bfs로 풀 것인가??
		// 운영 영역의 크기가 K 일때 하나의 집을 시작 위치로 잡았을때 그 위치에서 다음 집까지 최대 갈 수 있는 거리
		// => 사방위 탐색으로 K-1 까지 접근 가능
		// 손해를 보지 않는 선에서 최대로 많은 집에 서비스
		
		int T;
		
		
		Scanner sc = new Scanner(System.in);
		
		T = sc.nextInt();
		
		for(int tc = 1; tc <= T; tc++) {
			N = sc.nextInt();
			M = sc.nextInt();
			
			city = new int[N][N];
			max = 0;
			
			for(int i = 0; i < N; i ++) {
				for(int j = 0; j < N; j ++) {
					city[i][j] = sc.nextInt();
				}
			}
			
//			// 배열 확인
//			for(int i = 0; i < N; i ++) {
//				for(int j = 0; j < N; j ++) {
//					System.out.print(city[i][j] + " ");
//				}
//				System.out.println();
//			}
			
			for(int k = 1; k <= N+1; k++) {
				cost = k*k + (k-1)*(k-1);
				
				for(int i = 0; i < N; i++) {
					for(int j = 0; j < N; j ++) {
							// 탐색 전 초기화 먼저
							visit = new boolean[N][N];
							count = 0;
							
							bfs(i, j, k);
					}
				}
			}
			
			System.out.println("#" + tc + " " + max);
		}
	}
	static class Vertex {
		int a;
		int b;
;
		public Vertex(int a, int b) {
			super();
			this.a = a;
			this.b = b;
		}
		
	}
	
	public static void bfs(int x, int y, int k) {
		Queue<Vertex> q = new LinkedList<>();
		
		q.offer(new Vertex(x, y));
		visit[x][y] = true;
		if(city[x][y] == 1) {
			count++;
		}
		int depth = 0;
		while(depth < k-1) {
			int size = q.size();
			for (int i = 0; i < size; i++) {
				Vertex v = q.poll();
//				System.out.println(size+"만큼 탐색");
				
				for(int j = 0; j < 4; j++) {
					int nextX = v.a + dx[j];
					int nextY = v.b + dy[j];
					
					if(isInBound(nextX, nextY) && visit[nextX][nextY] == false) {
						q.offer(new Vertex(nextX, nextY));
						visit[nextX][nextY] = true;
						if(city[nextX][nextY] == 1) {
							count++;
						}
					}
					
				}
				
			}
			depth++;
		}
		
		if(count*M - cost >= 0) {
			max = Math.max(max, count);
		}
	}

	public static boolean isInBound(int x, int y) {
		return x >= 0 && x < N && y >= 0 && y < N;
	}
}
