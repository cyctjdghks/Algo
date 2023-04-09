import java.util.Scanner;

public class Solution {

	public static void main(String[] args) {
		// 트리가 갖는 정점의 총 수 N
		// N줄에 걸쳐 각각의 정점 정보
		// 정점 번호, 알파벳, 왼쪽 자식, 오른쪽 자식
		// 번호는 1 ~ N까지 정수로
		// 루트는 1
		// 10개의 테스트 케이스
		// 계산이 가능하면 1 불가능하면 0
		// 단말 노드라면 숫자
		// 아니라면 사칙연산

		int N;
		int num;
		String alphabet;
		int data;
		String line;
		int validation;
		int TC = 10;

		Scanner sc = new Scanner(System.in);

		for (int tc = 1; tc <= TC; tc++) {
			validation = 1;

			N = sc.nextInt();
			sc.nextLine();
			for (int i = 0; i < N; i++) {
				num = sc.nextInt();
				alphabet = sc.next();
				line = sc.nextLine();
				
				
				if (!line.equals("")) { // 자식 노드가 있는 노드
					if(alphabet.equals("+") || alphabet.equals("-") 
							|| alphabet.equals("*") || alphabet.equals("/")) {
					} else {
						validation = 0;
					}
				} else { // 자식 노드가 없는 단말 노드
					if(alphabet.equals("+") || alphabet.equals("-") 
							|| alphabet.equals("*") || alphabet.equals("/")) {
						validation = 0;
					}
				}
			}
			
			System.out.println("#" + tc + " " + validation);
		}
	}
}
