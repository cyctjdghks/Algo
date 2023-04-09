import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Solution {

	static int N, M, K;
	// 셀의 개수, 격리 기간, 군집의 개수
	static int[][] map;
	static Queue<Data> queue; // 군집 저장 큐
	static ArrayList<Locate> list; // 만났을때의 좌표 저장 리스트
	static int result;

	public static void main(String[] args) {
		// 정사각형 구역 안에 K개의 미생물 군집
		// N*N 셀
		// 가장 바깥은 특수한 약물 => 접근 X
		// 1. 가장 바깥으로 가면 절반( /2 ) and 방향 반대로
		// 만약 1마리라면 /2 = 0 이라 군집 삭제
		// 2. 한 셀에 모이면 합쳐짐
		// 이때 미생물 수가 더 많은 쪽의 이동방향
		// 1 - 상
		// 2 - 하
		// 3 - 좌
		// 4 - 우

		int T;

		Scanner sc = new Scanner(System.in);

		T = sc.nextInt();

		for (int tc = 1; tc <= T; tc++) {
			N = sc.nextInt();
			M = sc.nextInt();
			K = sc.nextInt();
			map = new int[N][N];
			queue = new LinkedList<Data>();
			result = 0;
			for (int i = 0; i < K; i++) {
				int x = sc.nextInt();
				int y = sc.nextInt();
				int num = sc.nextInt();
				int move = sc.nextInt();
				queue.offer(new Data(x, y, num, move));
//				map[x][y] = num; // 값 확인용
				map[x][y] = 1; // 중복 확인용
			}

//			print();
			
			
			for (int i = 0; i < M; i++) {
				movedata();
//				print();
				search();
				if (list.size() > 0) {
					sumdata();
				}
			}

			int size = queue.size();
			for (int i = 0; i < size; i++) {
				result += queue.poll().num;
			}

			System.out.println("#" + tc + " " + result);
		}
	}

	private static void sumdata() {
		for(int idx = 0; idx < list.size(); idx++) {
			int max = 0;
			int mv = 0;
			int n = 0;
			int size = queue.size();
			for(int i = 0; i < size; i++) {
				Data d = queue.poll();
				
				if(d.x == list.get(idx).x && d.y == list.get(idx).y) {
					n += d.num;
					if(max < d.num) {
						max = d.num;
						mv = d.move;
					}
				} else {
					queue.offer(d);
				}
			}
			
			queue.offer(new Data(list.get(idx).x, list.get(idx).y, n, mv));
//			map[list.get(idx).x][list.get(idx).y] = n; // 값 확인용
			map[list.get(idx).x][list.get(idx).y] = 1; // 중복 확인용
		}
	}

	private static void search() {
		list = new ArrayList<Locate>();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] >= 2) {
					list.add(new Locate(i, j));
				}
			}
		}
	}

	public static void print() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				System.out.print(map[i][j] + "\t");
			}
			System.out.println();
		}
		System.out.println();
	}

	private static void movedata() {
		int size = queue.size();
		for (int i = 0; i < size; i++) {
			Data d = queue.poll();
//			map[d.x][d.y] -= d.num; // 값 확인용
			map[d.x][d.y] -= 1; // 중복 확인용

			switch (d.move) {
			case 1:
				d.x -= 1;
				break;
			case 2:
				d.x += 1;
				break;
			case 3:
				d.y -= 1;
				break;
			case 4:
				d.y += 1;
				break;
			default:
				break;
			}

			if (isInBound(d.x, d.y)) {
				d.num /= 2;
				if (d.move % 2 == 0) { // 2 or 4 면 -1 => 하>상 우>좌
					d.move -= 1;
				} else { // 1 or 3 이면 +1 상>하 좌>우
					d.move += 1;
				}
			}

			if (d.num == 0) {
				continue;
			}

			queue.offer(d);
//			map[d.x][d.y] += d.num; // 값 확인용
			map[d.x][d.y] += 1; // 중복 확인용
		}
	}

	static public boolean isInBound(int x, int y) {
		return x == 0 || x == N - 1 || y == 0 || y == N - 1;
	}

	static class Locate {
		int x, y;

		public Locate(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	static class Data {
		int x, y;
		int num;
		int move;

		public Data(int x, int y, int num, int move) {
			this.x = x;
			this.y = y;
			this.num = num;
			this.move = move;
		}
	}

}
