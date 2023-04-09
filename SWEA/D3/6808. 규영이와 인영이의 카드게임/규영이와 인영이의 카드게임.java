import java.util.Arrays;
import java.util.Scanner;

public class Solution {

	static int[] Kyu;
	static int[] In;
	static int[] output;
	static boolean[] visited;
	static int win, lose;
	
	public static void main(String[] args) {
		// 테스트 케이스 개수 TC
		// 각 TC 별 9개의 정수
		// 1 ~ 18 까지의 수가 적힌 카드
		// 규영이는 정수가 주어지는 순서대로 카드를 낸다
		// 인영이가 내는 카드에 따른 9! 가지 순서에 따라 승패 결정
		// 규영이가 이기는 경우, 지는 경우의 총 가지 수

		int TC;

		Scanner sc = new Scanner(System.in);

		TC = sc.nextInt();

		for (int tc = 1; tc <= TC; tc++) {
			win = 0;
			lose = 0;
			
			Kyu = new int[9];
			In = new int[9];
			output = new int[9];
			visited = new boolean[9];
			
			for (int i = 0; i < 9; i++) {
				Kyu[i] = sc.nextInt();
			}
			
			int index = 0;
			loop :
			for(int num = 1; num <= 18; num++) {
				for(int i = 0; i < 9; i++) {
					if(Kyu[i] == num) {
						continue loop;
					}
				}
				
				In[index] = num;
				index++;
			}
//			for (int i = 0; i < 9; i++) {
//				for (int j = 0; j < 9; j++) {
//					if (Kyu[j] == num) {
//						num++;
//						j = 0;
//					}
//				}
//				In[i] = num;
//				num++;
//			}

			// 인영이 가진 카드의 순열 구해서(9!) 규영 배열과 비교한 뒤 승패 기록
			perm(0);

			System.out.println("#" + tc + " " + win + " " + lose);
		}
	}

	// 인영이가 낼 수 있는 모든 수 탐색
	public static void perm(int depth) {
		// 하나의 경우의 수마다 game 진행
		if (depth == 9) {
			game();
			return;
		}

		for (int i = 0; i < 9; i++) {
			if (visited[i] != true) {
				visited[i] = true;
				output[depth] = In[i];
				perm(depth + 1);
				visited[i] = false;
			}
		}
	}
	
	public static void game() {
		int sum_k = 0;
		int sum_i = 0;
		
		for(int i = 0; i < 9; i++) {
			if(Kyu[i] > output[i]) {
				sum_k += Kyu[i] + output[i];
			} else {
				sum_i += Kyu[i] + output[i];
			}
		}
		
		if(sum_k > sum_i) {
			win++;
		} else {
			lose++;
		}
	}

}
