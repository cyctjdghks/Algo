import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;

public class Solution {

	static int K;
	static LinkedList<Integer>[] list;
	static int[][] rotation;
	static boolean[] v;
	static int[] t;
	static int res;

	public static void main(String[] args) {
		// 하나의 자석이 1 칸 회전될 때
		// 붙어 있는 자석은 서로 붙어 있는 날의 자성과 다를 경우에만 인력에 의해 반대 방향으로 1 칸 회전된다.
		// 1-2 <> 2-6
		// 2-2 <> 3-6
		// 3-2 <> 4-6
		// 회전시키는 방향은 시계방향이 1 로, 반시계 방향이 -1
		// N 극이 0, S 극이 1
		// 각 자석의 날 자성정보는 빨간색 화살표 위치의 날부터 시계방향 순서대로
		// 빨간 화살표는 0번 index 값
		// 회전하기 전 반대방향이면 회전
		// 회전시킨 뒤 좌우 값 비교 > 다르면 회전
		// > 반복(좌 : 1번 까지, 우 : 4번 까지)

//		- 1 번 자석에서 빨간색 화살표 위치에 있는 날의 자성이 N 극이면 0 점, S 극이면 1 점을 획득한다.
//		- 2 번 자석에서 빨간색 화살표 위치에 있는 날의 자성이 N 극이면 0 점, S 극이면 2 점을 획득한다.
//		- 3 번 자석에서 빨간색 화살표 위치에 있는 날의 자성이 N 극이면 0 점, S 극이면 4 점을 획득한다.
//		- 4 번 자석에서 빨간색 화살표 위치에 있는 날의 자성이 N 극이면 0 점, S 극이면 8 점을 획득한다.

		int T;

		Scanner sc = new Scanner(System.in);

		T = sc.nextInt();

		for (int tc = 1; tc <= T; tc++) {
			K = sc.nextInt();
			list = new LinkedList[4 + 1];
			rotation = new int[2][K];
			res = 0;
			
			for (int i = 1; i <= 4; i++) {
				list[i] = new LinkedList<Integer>();
			}
			for (int i = 1; i <= 4; i++) {
				for (int j = 0; j < 8; j++) {
					list[i].add(sc.nextInt());
				}
			}

//			print();

			for (int i = 0; i < K; i++) {
				rotation[0][i] = sc.nextInt();
				rotation[1][i] = sc.nextInt();
			}

			for (int i = 0; i < K; i++) {
				v = new boolean[4+1];
				t = new int[4+1];
				// 돌리기 전에 돌릴 톱니바퀴 정하기
				select(rotation[0][i], rotation[1][i]);
				// 정했으면 톱니바퀴 돌려라
//				System.out.println(Arrays.toString(v));
//				System.out.println(Arrays.toString(t));
				for(int j = 1; j <=4; j++) {
					if(v[j]) {
						turn(j, t[j]);
					}
				}
//				print();
			}
			
			for(int i = 1; i <= 4; i++) {
				if(list[i].get(0) == 1) {
					res += Math.pow(2, i-1);
				}
			}

			System.out.println("#" + tc + " " + res);
		}
	}

	private static void turn(int idx, int dir) {
		int temp;
		if (dir == 1) {
			temp = list[idx].get(7);
			list[idx].remove(7);
			list[idx].addFirst(temp);
		} else if (dir == -1) {
			temp = list[idx].get(0);
			list[idx].remove(0);
			list[idx].addLast(temp);
		}
	}

	private static void select(int idx, int dir) {
		v[idx] = true;
		t[idx] = dir;
		
		switch (idx) {
		case 1:
			if(list[idx].get(2) != list[idx+1].get(6)) {
				if(!v[idx+1]) {
					select(idx+1, -dir);
				}
			}
			break;
		case 2:
			if(list[idx].get(2) != list[idx+1].get(6)) {
				if(!v[idx+1]) {
					select(idx+1, -dir);
				}
			}
			if(list[idx].get(6) != list[idx-1].get(2)) {
				if(!v[idx-1]) {
					select(idx-1, -dir);
				}
			}
			break;
		case 3:
			if(list[idx].get(2) != list[idx+1].get(6)) {
				if(!v[idx+1]) {
					select(idx+1, -dir);
				}
			}
			if(list[idx].get(6) != list[idx-1].get(2)) {
				if(!v[idx-1]) {
					select(idx-1, -dir);
				}
			}
			break;
		case 4:
			if(list[idx].get(6) != list[idx-1].get(2)) {
				if(!v[idx-1]) {
					select(idx-1, -dir);
				}
			}
			break;
		default:
			break;
		}
	}

	private static void print() {
		for (int i = 1; i <= 4; i++) {
			for (int j = 0; j < 8; j++) {
				System.out.print(list[i].get(j) + " ");
			}
			System.out.println();
		}
		System.out.println();
	}

}
