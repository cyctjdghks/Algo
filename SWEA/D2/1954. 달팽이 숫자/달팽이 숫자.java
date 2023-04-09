import java.util.Scanner;

class Solution {
	public static void main(String args[]) {
		// 테스트 케이스 개수 T
		// N 크기 의 달팽이
		Scanner sc = new Scanner(System.in);
		int T;
		T = sc.nextInt();

		int N;
		int[][] snail;
		int row;
		int col;
		int dir;

		for (int test_case = 1; test_case <= T; test_case++) {
			N = sc.nextInt();

			snail = new int[N][N];
			row = 0;
			col = 0;
			dir = 1;

			for (int i = 1; i <= N * N; i++) {
				snail[row][col] = i;

				switch (dir) {
				case 1: // 오른쪽으로
					if(col == N-1 || snail[row][col+1] != 0) {
						dir = 2;
						row++;
					} else {
						col++;
					}
					break;
				case 2:// 아래로
					if(row == N-1 || snail[row+1][col] != 0) {
						dir = 3;
						col--;
					} else {
						row++;
					}
					break;
				case 3:// 왼쪽으로
					if(col == 0 || snail[row][col-1] != 0) {
						dir = 4;
						row--;
					} else {
						col--;
					}
					break;
				case 4:// 위로
					if(row == 0 || snail[row-1][col] != 0) {
						dir = 1;
						col++;
					} else {
						row--;
					}
					break;
				default:
					break;
				}

			}

			System.out.println("#" + test_case);
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					System.out.print(snail[i][j] + " ");
				}
				System.out.println();
			}
		}
	}
}