
import java.util.Arrays;
import java.util.Scanner;

class Solution {
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		int T;
		T = 10;

		int dump_num; // 덤프 횟수
		int[] arr; // 상자의 높이 값을 저장하는 배열
		int max; // 가장 높은 갓
		int min; // 가장 낮은 값
		int result; // 결과 값

		for (int test_case = 1; test_case <= T; test_case++) {
			dump_num = sc.nextInt();
			arr = new int[100];
			max = 1; // 높이 가장 낮을 때 1
			min = 100; // 높이 가장 높을 때 100
			result = 0; // 결과값 초기화
			
			for (int i = 0; i < 100; i++) {
				arr[i] = sc.nextInt();
			}
			
			for (int dump = 0; dump < dump_num; dump++) {
				// max min 초기화 후 찾기
				max = 1; // 높이 가장 낮을 때 1
				min = 100; // 높이 가장 높을 때 100
				for (int i = 0; i < 100; i++) { // 가장 높은 부분과 낮은 부분 찾기
					if (arr[i] > max) {
						max = arr[i];
					}
					if (arr[i] < min) {
						min = arr[i];
					}
				}
				
				for (int i = 0; i < 100; i++) {
					if (arr[i] == max) { // 가장 높은 부분은 -1 가장 낮은 부분은 +1
						arr[i]--;
						max = 0;
					}
					if (arr[i] == min) {
						arr[i]++;
						min = 101;
					}
				}
			}
			
			for (int i = 0; i < 100; i++) { // 덤프 횟수만큼 수행 후 가장 높은 부분과 낮은 부분 찾기
				if (arr[i] > max) {
					max = arr[i];
				}
				if (arr[i] < min) {
					min = arr[i];
				}
			}
			result = max - min; // 결과값 저장

			System.out.println("#" + test_case + " " + result);
		}
	}
}