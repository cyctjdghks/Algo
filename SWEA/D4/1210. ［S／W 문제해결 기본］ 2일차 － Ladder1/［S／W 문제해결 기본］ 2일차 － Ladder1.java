import java.util.Scanner;

public class Solution {

	static int arr[][];
	static int row;
	static int col;

	public static void main(String[] args) {
		// 아래의 x지점인 2 부터 사다리 탐색 시작
		// 사다리의 특징 기억
		// 위로 쭉 이동하다가 왼쪽 혹은 오른쪽에 1이 나오면 그쪽으로 계속 이동
		// 0이 나올때까지 이동
		// 왼쪽 혹은 오른쪽으로 이동하다가 0이 나오면 다시 위로 쭉 이동
		// row 값이 0이 되게 되면 탐색 종료
		// 해당 col값 반환
		Scanner sc = new Scanner(System.in);
		int T;
		T = 10; // 테스트 케이스
		int num = 0; // 테스트 케이스 번호

		for (int test_case = 1; test_case <= T; test_case++) {
			row = 99;
			col = -1;
			num = sc.nextInt();

			arr = new int[100][100];
			for (int i = 0; i < 100; i++) {
				for (int j = 0; j < 100; j++) {
					arr[i][j] = sc.nextInt();
				}
			}

			for (int i = 0; i < 100; i++) {
				if (arr[99][i] == 2) {
					col = i;
					break;
				}
			}
			Move();

			System.out.println("#" + test_case + " " + col);
		}
	}

	public static void Move() {
		while (row > 0) {
			if (isInBound(100, 100, row, col - 1) && arr[row][col - 1] == 1) {// 왼쪽 이동
				while (isInBound(100, 100, row, col - 1) && arr[row][col - 1] == 1) {
					col--;
				}
			} else if (isInBound(100, 100, row, col + 1) && arr[row][col + 1] == 1) {// 오른쪽 이동
				while (isInBound(100, 100, row, col + 1) && arr[row][col + 1] == 1) {
					col++;
				}
			}
			row--;
		}
	}

	public static boolean isInBound(int r, int c, int x, int y) {
		return r >= 0 && y >= 0 && x < r && y < c;
	}

}
