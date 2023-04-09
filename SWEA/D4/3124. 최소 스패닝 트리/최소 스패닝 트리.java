import java.util.PriorityQueue;
import java.util.Scanner;

// 4. SWEA 3124 최소 스패닝 트리(크루스칼)
public class Solution {

	static int V, E;
	static int[] parent;
	static PriorityQueue<Edge> queue = new PriorityQueue<Edge>();
	static long result;
	
	public static void main(String[] args) {
		// 테스트 케이스 T
		// 정점의 갯수 V 간선의 개수 E
		// A 번 정점과 B 번 정점이 가중치 C 인 간선으로 연결
		
		int T;
		
		int A, B, val;
		
		Scanner sc = new Scanner(System.in);
		
		T = sc.nextInt();
		
		for(int tc = 1; tc <= T; tc++) {
			V = sc.nextInt();
			E = sc.nextInt();
			result = 0;
			
			parent = new int[V+1];
			make();
			
			for(int i = 0; i < E; i++) {
				A = sc.nextInt();
				B = sc.nextInt();
				val = sc.nextInt();
				queue.add(new Edge(A, B, val));
			}
			
			Kruscal();
			
			System.out.println("#" + tc + " " + result);
		}
	}
	
	public static void Kruscal() {
		int cnt = 0;
		
		while(!queue.isEmpty()) {
			if(cnt == V) {
				break;
			}
			
			Edge temp = queue.poll();
			
			int a = find(temp.start);
			int b = find(temp.end);
			
			if(a == b) {
				continue;
			}
			
			union(a, b);
			
			result += temp.value;
			cnt++;
			
		}
	}
	
	// 상위 노드 초기화
	public static void make() {
		for(int i = 0; i < V + 1; i++) {
			parent[i] = i;
		}
	}
	
	// find
	public static int find(int a) {
		if(a == parent[a]) {
			return a;
		}
		return parent[a] = find(parent[a]);
	}
	
	// union
	public static void union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		
		if(aRoot != bRoot) {
			parent[bRoot] = aRoot;
		} else {
			return;
		}
		
	}
}

class Edge implements Comparable<Edge> {
	int start;
	int end;
	int value;
	public Edge(int start, int end, int value) {
		this.start = start;
		this.end = end;
		this.value = value;
	}

	@Override
	public int compareTo(Edge o) {
		return o.value >= this.value ? -1 : 1;
	}
	
}