import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		// 리모컨에는 버튼이 0부터 9까지 숫자, +와 -
		// +를 누르면 현재 보고있는 채널에서 +1
		// -를 누르면 -1된 채널로 이동
		// 이동하려고 하는 채널 N (0 ≤ N ≤ 500,000)
		// 수빈이가 지금 보고 있는 채널은 100번
		// 고장난 버튼의 개수 M (0 ≤ M ≤ 10)
		// 고장나지 않은 리모콘 버튼으로만 target과 가장 근사한 번호를 만든 후 +, - 로 이동하는 값 중의 최소값을 선택

		int N;
		int M;
		int min, res;
		
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		M = sc.nextInt();

		boolean[] broken = new boolean[10];
		for (int i = 0; i < M; i++) {
			int num = sc.nextInt();
			broken[num] = true;
		}

		res = Math.abs(N - 100); // 초기값 설정 - 100번 채널에서 단순히 +- 눌러서 이동
		for (int i = 0; i <= 999999; i++) {
			String str = String.valueOf(i);

			boolean isBreak = false;
			for (int j = 0; j < str.length(); j++) {
				if (broken[str.charAt(j) - '0']) { // 고장난 버튼을 눌러야 하면
					isBreak = true;
					break; // 더 이상 탐색하지 않고 빠져나온다.
				}
			}
			if (!isBreak) { // i를 누를때 고장난 버튼을 누르지 않는다면
				min = Math.abs(N - i) + str.length(); // i를 누른 후(len) target까지 이동하는 횟수(target - i)
				res = Math.min(min, res);
			}
		}
		System.out.println(res);
	}

}
