import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

// 2. BOJ 2252 줄 세우기
public class Main {

	static int N; // 학생수
	static int M; // 관계수
	static ArrayList<Integer> result = new ArrayList<>();
	static ArrayList<Integer>[] list;
	// 위상정렬
	// inDegree 배열, 큐자료구조
	static int[] inDegree;
	
	public static void main(String[] args) {
		// N 명의 학생들을 키순서대로 줄 - 정점
		// M 은 키를 비교한 횟수 - 간선
		// 위상정렬
		// 학생들의 번호는 1 ~ N
		// 답이 여러개면 아무거나 출력
		
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		list = new ArrayList[N+1];
		
		// step1. 진입차수 관리배열 생성
		inDegree = new int[N+1];
		
		for(int i = 1;i <= N; i++) {
			list[i] = new ArrayList<>();
		}
		
		// step2. 입력받으면서 진입차수 관리 배열에 1씩 증가
		for(int i = 0; i < M; i ++) {
			int x = sc.nextInt(); // index
			int y = sc.nextInt(); // to
			list[x].add(y);
			inDegree[y]++;
		}
		
		// step3. 진입차수가 0인 객체를 모듀 큐에 삽입한다.
		Queue<Integer> q = new LinkedList<>();
		for(int i = 1; i <= N; i++) {
			if(inDegree[i] == 0) {
				q.offer(i);
			}
		}
		// 가지치기 큐사이즈가 0이면 종료
		if(q.size() == 0) {
			return;
		}
		
		// step4. 큐가 빌 때까지 반복
		while(!q.isEmpty()) {
			// step4-1. 큐에서 하나빼서 작업을 진행한다.
			int cur = q.poll();
			result.add(cur);
			
			// step4-2. 나랑 연결되어 있는 애들의 진입차수 배열에서 1씩 감소한다.
			for(Integer num : list[cur]) {
				inDegree[num]--;
				// step4-3. 현재 정점의 진입차수가 0인 정점은 큐에 삽입한다.
				if(inDegree[num] == 0) {
					q.offer(num);
				}
			}
		}
		// result.size() 값이 N이 아니면 사이클 존재
		
		for(Integer num : result) {
			System.out.print(num + " ");
		}
		System.out.println();
	}

}
