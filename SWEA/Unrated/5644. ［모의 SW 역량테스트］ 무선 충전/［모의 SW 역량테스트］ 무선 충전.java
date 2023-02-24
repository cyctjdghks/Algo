import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Solution {

	static int M, A;
	static int[] user_A;
	static int[] user_B;
//	static int sum_A;
//	static int sum_B;
	static boolean[] va;
	static boolean[] vb;
	static int[][] BC;
	static int[][] map;
	static int[] dx = { 0, -1, 0, 1, 0 };
	static int[] dy = { 0, 0, 1, 0, -1 };
	// 0 : X
	// 1 : 상
	// 2 : 우
	// 3 : 하
	// 4 : 좌
	static int res;

	public static void main(String[] args) {
		// BC의 충전 범위가 C일 때, BC와 거리가 C 이하이면 BC에 접속할 수 있다.
		// 두 지점 A(XA, YA), B(XB, YB) 사이의 거리
		// D = |XA – XB| + |YA – YB|
		// 충전 범위에 모두 속하면, 속하는 BC 중 하나를 선택하여 접속
		// 만약 한 BC에 두 명의 사용자가 접속한 경우, 접속한 사용자의 수만큼 충전 양을 균등하게 분배
		// 모든 사용자가 충전한 양의 합의 최댓값을 구하는 프로그램

		int T;

		Scanner sc = new Scanner(System.in);

		T = sc.nextInt();

		for (int tc = 1; tc <= T; tc++) {
			M = sc.nextInt(); // 이동시간 M 초기화
			A = sc.nextInt(); // BC의 개수 A 초기화
			// 유저 A, B 초기화
			user_A = new int[M];
			user_B = new int[M];
			for (int i = 0; i < M; i++) {
				user_A[i] = sc.nextInt();
			}
			for (int i = 0; i < M; i++) {
				user_B[i] = sc.nextInt();
			}
			// BC의 정보 초기화
			// 좌표(x, y), 충전범위(C), 처리량(P)
			BC = new int[A][4];
			for (int i = 0; i < A; i++) {
				BC[i][1] = sc.nextInt() - 1;
				BC[i][0] = sc.nextInt() - 1;
				BC[i][2] = sc.nextInt();
				BC[i][3] = sc.nextInt();
			}
			map = new int[10][10]; // 멥 초기화
			res = 0; // 결과값 초기화

//			print(); // 초기화 확인

			search();

			System.out.println("#" + tc + " " + res);
		}
	}

	private static void search() {
		Queue<Data> q = new LinkedList<Data>();
		q.offer(new Data(0, 0, 0)); // 유저 A 위치로
		q.offer(new Data(9, 9, 0)); // 유저 B 위치로

		while (!q.isEmpty()) {
			// 위치 가져오기
			Data userA = q.poll();
			Data userB = q.poll();

//			System.out.println("T : " + userA.time);
//			System.out.println("유저a 좌표 : " + userA.x + ", " + userA.y);
//			System.out.println("유저b 좌표 : " + userB.x + ", " + userB.y);

			// 충전 가능한 BC 초기화
			va = new boolean[A];
			vb = new boolean[A];

			// 충전 가능한 BC 탐색
			for (int i = 0; i < A; i++) {
				int D = Math.abs(BC[i][0] - userA.x) + Math.abs(BC[i][1] - userA.y);
				if (D <= BC[i][2]) {
					va[i] = true;
				}

				D = Math.abs(BC[i][0] - userB.x) + Math.abs(BC[i][1] - userB.y);
				if (D <= BC[i][2]) {
					vb[i] = true;
				}
			}

//			// BC 확인
//			System.out.println(Arrays.toString(va));
//			System.out.println(Arrays.toString(vb));

			// 누굴 선택할지
			// 서로 다르면 그냥 각각 max 값 충전
			// 둘이 같다면
			// 1. 한명이 2개 이상인지
			// 2. 없다면 /2 해서 저장
			// => 어차피 둘의 합을 구할거니 한명이 true라면 다른 유저를 false해서 계산하면 되지않을까?
			// 그냥 둘이 순서바꿔서 한 뒤에 더 큰쪽 저장하면 편하긴함

			int sumA, sumB;
			int cooa = -1, maxa = 0; // a가 충전할 위치
			int coob = -1, maxb = 0; // b가 충전할 위치

			// A부터 큰값 설정
			// A가 충전할 위치 저장
			for (int i = 0; i < A; i++) {
				if (va[i] && maxa < BC[i][3]) {
					cooa = i;
					maxa = BC[i][3];
				}
			}
			// B가 충전할 위치 저장
			for (int i = 0; i < A; i++) {
				if (vb[i] && maxb < BC[i][3] && i != cooa) { // A가 충전한 곳이랑 다른곳에서 충전
					coob = i;
					maxb = BC[i][3];
				}
			}
			sumA = maxa + maxb;
			
			cooa = -1;
			maxa = 0; // a가 충전할 위치
			coob = -1;
			maxb = 0; // b가 충전할 위치
			// B부터 큰값 설정
			// B가 충전할 위치 저장
			for (int i = 0; i < A; i++) {
				if (vb[i] && maxb < BC[i][3]) { // A가 충전한 곳이랑 다른곳에서 충전
					coob = i;
					maxb = BC[i][3];
				}
			}
			// A가 충전할 위치 저장
			for (int i = 0; i < A; i++) {
				if (va[i] && maxa < BC[i][3] && i != coob) {
					cooa = i;
					maxa = BC[i][3];
				}
			}
			sumB = maxa + maxb;

//			System.out.println(Math.max(sumA, sumB));
//			System.out.println();
			
			// 충전
			res += Math.max(sumA, sumB);
			
			// M 만큼 이동했으면 종료
			if (userA.time == M) {
				break;
			}

			// 이동
			int nx, ny;
			nx = userA.x + dx[user_A[userA.time]];
			ny = userA.y + dy[user_A[userA.time]];
			q.offer(new Data(nx, ny, userA.time + 1)); // 유저 A 이동

			nx = userB.x + dx[user_B[userB.time]];
			ny = userB.y + dy[user_B[userB.time]];
			q.offer(new Data(nx, ny, userB.time + 1)); // 유저 B 이동
		}
	}

	private static void print() {
		System.out.println("userA");
		System.out.println(Arrays.toString(user_A));
		System.out.println("userB");
		System.out.println(Arrays.toString(user_B));

		for (int i = 0; i < A; i++) {
			for (int j = 0; j < 4; j++) {
				System.out.print(BC[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}

	static class Data {
		int x, y;
		int time;

		public Data(int x, int y, int time) {
			this.x = x;
			this.y = y;
			this.time = time;
		}

	}

}
