
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Solution {

	public static void main(String[] args) {
		// 원본 암호문의 길이 N
		// 원본 암호문
		// 명령어의 개수
		// 명령어
		// I(삽입) x(x의 위치 다음에) y(y개의 숫자) s(덧붙일 숫자들)
		// D(삭제) x(x의 위치 다음에) y(y개의 숫자 삭제)
		// 10개의 테스트 케이스

		int N;
		ArrayList<Integer> ciphertext = new ArrayList<>();
		int command_count;
		String command;
		int x, y;
		int[] s;
		int TC = 10;

		Scanner sc = new Scanner(System.in);

		for (int tc = 1; tc <= TC; tc++) {
			ciphertext.clear();

			N = sc.nextInt();
			for (int i = 0; i < N; i++) {
				ciphertext.add(sc.nextInt());
			}
			command_count = sc.nextInt();
			for(int i = 0; i < command_count; i++) {
				command = sc.next();
				
				x = sc.nextInt();
				y = sc.nextInt();
				
				if(command.equals("I" )) {
					s = new int[y];
					for(int j = 0; j < y; j++) {
						s[j] = sc.nextInt();
					}
					
					for(int k = y-1 ; k >= 0; k--) {
						ciphertext.add(x, s[k]);
					}
				} else if(command.equals("D")) {
					for(int j = 0; j < y; j++) {
						ciphertext.remove(x);
					}
				}
			}
			
			System.out.print("#" + tc + " ");
			for(int i = 0; i < 10; i++) {
				System.out.print(ciphertext.get(i) + " ");
			}
			System.out.println();
		}
	}
}