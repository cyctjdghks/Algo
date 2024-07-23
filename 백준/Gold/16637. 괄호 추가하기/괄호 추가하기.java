import java.util.Scanner;

// 2. BOJ 16637 괄호 추가하기(A형 보충 추가)
// BOJ 16637. 괄호 추가하기
public class Main {

	static int N; // 전체 수식의 길이
	static char[] map;
	static int res;

	public static void main(String[] args) {
		// 길이가 N인 수식
		// 괄호를 적절히
		// 괄호 안에 괄호 X
		// 수식을 계산할 때는 왼쪽에서부터 순서대로 계산
		// 수식에 괄호를 추가하면, 괄호 안에 들어있는 식은 먼저 계산

		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		map = sc.next().toCharArray(); // 문자열 배열로 읽기
		res = Integer.MIN_VALUE;
		dfs(2, map[0] - '0');
		System.out.println(res);
	}

	static void dfs(int idx, int sum) {
		// 종료조건
		if (idx >= N) {
			res = Math.max(res, sum);
			return;
		}
		// 현재 위치에 괄호가 없을 경우 , 연산자하나와 수를 사용했음으로 2;
		dfs(idx + 2, calc(sum, map[idx] - '0', map[idx - 1]));
		// 현재 위치에 괄호가 있을 경우
		if (idx + 2 < N) { // 뒤에 숫자가 있어야 괄호 연산을 먼저 할 수 있다.
			int ta = calc(map[idx] - '0', map[idx + 2] - '0', map[idx + 1]);
			// 다음 재귀 호출하기위해서 다음 다음숫자로 이동해야 함
			dfs(idx + 4, calc(sum, ta, map[idx - 1]));
		}
	}

	static int calc(int a, int b, char op) {
		if (op == '+') {
			return a + b;
		} else if (op == '-') {
			return a - b;
		} else {
			return a * b;
		}
	}
}
