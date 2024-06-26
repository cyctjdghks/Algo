import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {

		int M;
		int x = 0;
		int[] S = new int[20 + 1];

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		M = Integer.parseInt(br.readLine());

		for (int i = 0; i < M; i++) {
			String[] input = br.readLine().split(" ");

			switch (input[0]) {
			case "all": // 다 1로 초기화
				Arrays.fill(S, 1);
				break;
			case "empty": // 다 0으로 초기화
				Arrays.fill(S, 0);
				break;
			default:
				x = Integer.parseInt(input[1]);
				switch (input[0]) {
				case "add": // 0 > 1, 1 > 1 => or 1
					S[x] = S[x] | 1;
					break;
				case "remove": // 1 > 0, 0 > 0 => and 0
					S[x] = S[x] & 0;
					break;
				case "check": // 1 > 1, 0 > 0 => or 0 / and 1 + 값 출력
					S[x] = S[x] | 0;
					sb.append(S[x]).append('\n');
					break;
				case "toggle": // 1 > 0, 0 > 1 => xor 1
					S[x] = S[x] ^ 1;
					break;
				}
				break;
			}
		}

		System.out.println(sb);
	}

}
