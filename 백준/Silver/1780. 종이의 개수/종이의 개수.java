import java.util.Scanner;

public class Main {

	static int N;
	static int[][] map;
	static int res1, res2, res3;

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		map = new int[N][N];
		res1 = 0; // -1 개수
		res2 = 0; // 0 개수
		res3 = 0; // 1 개수
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				map[i][j] = sc.nextInt();
			}
		}

		search(0, 0, N);

		System.out.println(res1);
		System.out.println(res2);
		System.out.println(res3);
	}

	private static void search(int r, int c, int size) {
		if (check(r, c, size)) {
			if (map[r][c] == -1) {
				res1++;
			} else if (map[r][c] == 0) {
				res2++;
			} else if (map[r][c] == 1) {
				res3++;
			}

			return;
		}
		
		size = size/3;
		
		search(r, c, size);
		search(r, c + size, size);
		search(r, c + 2*size, size);
		
		search(r + size, c, size);
		search(r + size, c + size, size);
		search(r + size, c + 2*size, size);
		
		search(r + 2*size, c, size);
		search(r + 2*size, c + size, size);
		search(r + 2*size, c + 2*size, size);
	}

	private static boolean check(int r, int c, int size) {
		int num = map[r][c];

		for (int i = r; i < r + size; i++) {
			for (int j = c; j < c + size; j++) {
				if(map[i][j] != num) {
					return false;
				}
			}
		}

		return true;
	}

}
