
import java.util.Arrays;
import java.util.Scanner;

public class Main {

	static int[] swit; // 스위치
	static int length;

	public static void main(String[] args) {
		// 첫 줄 - 스위치 개수
		// 두번째 줄 - 스위치 상태
		// 세번째 줄 - 학생 수
		// 1은 남학생 - 배수의 스위치 상태 바꿈
		// 2는 여학생 - 받은 숫자 중심으로 가장 크기가 큰 대칭 구간의 모든 스위치 상태 바꿈

		int Num = 0; // 스위치 갯수
		int test_case = 0; // 학생 수
		int student = 0; // 학생
		int work; // 스위치 상태 변경 값

		Scanner sc = new Scanner(System.in);

		Num = sc.nextInt();

		swit = new int[Num];
		for (int i = 0; i < Num; i++) {
			swit[i] = sc.nextInt();
		}
		length = swit.length;

		test_case = sc.nextInt();

		for (int i = 0; i < test_case; i++) {
			student = sc.nextInt();
			work = sc.nextInt();

			if (student == 1) {
				Man(work);
			} else if (student == 2) {
				Woman(work);
			}

		}

		for (int i = 0; i < length; i++) {
			if (i % 20 == 0 && i != 0) {
				System.out.println();
			}
			System.out.print(swit[i] + " ");

		}
	}

	public static void Man(int work) {
		for (int i = 0; i < length; i++) {
			if ((i + 1) % work == 0) {
				swit[i] = (swit[i] == 1) ? 0 : 1;
			}
		}
	}

	public static void Woman(int work) {
		int index = 0;

		while (true) {
			index++;
			if ((work - 1) - index < 0 || (work - 1) + index > length - 1) {
				break;
			}

			if (swit[(work - 1) - index] != swit[(work - 1) + index]) {
				break;
			}
		}
		for (int i = (work - 1) - (index-1); i < work + (index-1); i++) {
			swit[i] = (swit[i] == 1) ? 0 : 1;
		}
	}

}
