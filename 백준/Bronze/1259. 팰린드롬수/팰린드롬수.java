import java.util.Arrays;
import java.util.Scanner;

/*
input 값

121
1231
12421
0

 */
public class Main {

	public static void main(String[] args) {
		// 거꾸로 뒤집어도 똑같다면 그 단어를 팰린드롬 이라 한다.
		// 1. 입력 받은 값을 String에 저장
		// 2. String 값을 한 글자씩 배열에 저장
		// 3. 배열을 뒤집고 원래 배열과 비교
		// 4. 같다면 yes, 다르다면 no 출력
		// 조건 - while 문으로 반복
		// 조건 - 만약 0이 입력받았다면 아무것도 출력하지 않고 종료

		Scanner sc = new Scanner(System.in);
		String input = "";
		char[] input_arr;
		String check;

		// 조건 - while 문으로 반복
		while (true) {
			check = ""; // 비교할 String 초기화
			// 입력 받은 값을 String에 저장
			input = sc.nextLine();
			
			// 조건 - 만약 0이 입력받았다면 아무것도 출력하지 않고 종료
			if (input.equals("0")) {
				break;
			}
			
			// 입력받은 String을 한 글자씩 넣을 배열 초기화
			input_arr = new char[input.length()];

			// 2. String 값을 한 글자씩 배열에 저장
			for (int i = 0; i < input.length(); i++) {
				input_arr[i] = input.charAt(i);
			}
			
			// 한 글자씩 받은 배열 확인용
//			System.out.println(Arrays.toString(input_arr));

			// 3. 배열을 뒤집고 원래 배열과 비교
			for (int i = input.length() - 1; i >= 0; i--) {
				check += input_arr[i];
			}
			
			// 뒤집어서 String에 저장한 값 확인용
//			System.out.println(check);
			
			// 4. 같다면 yes, 다르다면 no 출력
			if (check.equals(input)) {
				System.out.println("yes");
			} else {
				System.out.println("no");
			}
		}

	}

}
