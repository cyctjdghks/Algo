import java.util.Scanner;

public class Main {

	static int N;
	static int[][] house;
	static int[][] D;

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		house = new int[N + 1][3];
		D = new int[N + 1][3];

		for (int i = 1; i <= N; i++) {
			house[i][0] = sc.nextInt(); // 빨
			house[i][1] = sc.nextInt(); // 초
			house[i][2] = sc.nextInt(); // 파
		}

		for (int i = 1; i <= N; i++) {
			D[i][0] = Math.min(D[i - 1][1], D[i - 1][2]) + house[i][0];
			D[i][1] = Math.min(D[i - 1][0], D[i - 1][2]) + house[i][1];
			D[i][2] = Math.min(D[i - 1][0], D[i - 1][1]) + house[i][2];
		}

		System.out.print(Math.min(Math.min(D[N][0], D[N][1]), D[N][2]));

	}

}