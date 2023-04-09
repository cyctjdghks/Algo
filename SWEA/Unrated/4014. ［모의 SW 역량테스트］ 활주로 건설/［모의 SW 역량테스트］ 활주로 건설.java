import java.util.Scanner;

public class Solution {
	static int n, x;
	static int map1[][], map2[][];
	static int cnt = 0, num = 0;
	static boolean check;
	static int count;

	private static boolean check(int[][] map, int idx) {
		int count = 1;
		int height = map[idx][0];// 1행, 1열

		for (int i = 1; i < n; i++) {
			if (height == map[idx][i]) {// 높이 같으면 계속 카운트
				count++;
			} else if (map[idx][i] - height == 1) {// 1 크고
				if (count < x) {// 그동안 높이 같았던거 카운트한거보다 경사로 길이 작으면
					return false;//버림
				} else {
					count = 1;//다시 1
					height = map[idx][i];//이제 중간 검사
				}
			} else if (height - map[idx][i] == 1) {// 1 작고
				if (n < x + i) {//남은거 없으면 버림
					return false;
				}
				for (int j = 1; j < x; j++) {
					if (height - map[idx][++i] != 1) {//1보다 차이 많이나면 버림
						return false;
					}
				}
				height = map[idx][i];
				count = 0;
			} else {//경사 2이상 4이하니까 첨부터 다르면 바로 버려
				return false;
			}
		}
		return true;
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int tc = 1; tc <= T; tc++) {
			n = sc.nextInt();// n*n
			x = sc.nextInt();
			count = 0;
			map1 = new int[n][n];
			map2 = new int[n][n];
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					map1[i][j] = sc.nextInt();
					map2[j][i] = map1[i][j];
				}
			}

			for (int i = 0; i < n; i++) {
				if (check(map1, i)) {// 가로 검사
					count++;
				}
				if (check(map2, i)) {// 세로 검사
					count++;
				}
			}
			System.out.println("#" + tc + " " + count);
		}
	}
}