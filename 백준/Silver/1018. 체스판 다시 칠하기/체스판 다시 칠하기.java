import java.util.Arrays;
import java.util.Scanner;

public class Main {
	static int[][] arr;
	static int min = 9999_9999; // 다시 칠해야 하는 정사각형 개수의 최솟값

	public static void main(String[] args) {
		// 입력
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		int M = sc.nextInt();
		
		arr = new int[N][M]; // N과 M 크기를 가진 배열
		// 체스 판은 8*8 경우의 수는 (N-8)*(M-8)
		int chess_r = N - 7;
		int chess_c = M - 7;
		
		int color = 0; // 정해진 위치의 정상적인 색 설정
		int sum = 0;
		
		int end_x = 0; // 체스판 행의 끝
		int end_y = 0; // 체스판 열의 끝
		
		
		// 배열 초기화
		for(int i = 0;i < N;i++) {
			String str = sc.next();
			
			for(int j = 0; j < M;j++) {
				if(str.charAt(j) == 'W') { // 흰색 일때는 1
					arr[i][j] = 1;
				} else { // 검은색 일때는 0
					arr[i][j] = 0;
				}
			}
		}
		
//		// 배열 확인용
//		for(int i = 0;i < N;i++) {
//			System.out.println(Arrays.toString(arr[i]));
//		}
		
		// 알고리즘 수행
		for(int i = 0;i < chess_r;i++) {
			for(int j = 0;j < chess_c;j++) {
//				find(i, j);
				color = arr[i][j]; // 가장 왼쪽 위 값 저장
				sum = 0; // 합 초기화
				end_x = i + 8;
				end_y = j + 8;
				
				for(int r = i; r < end_x;r++) {
					for(int c = j; c < end_y;c++ ) {
						if(arr[r][c] != color) { // 정해진 위치의 색이 다르다면
							sum += 1; // 1증가
						}
						// 수행했으니 color 바꿔주기
						// 1이면 0으로, 0이면 1로
						if(color == 1) {
							color = 0;
						} else {
							color = 1;
						}
					}
					if(color == 1) {
						color = 0;
					} else {
						color = 1;
					}
				}
				if(sum > 32) {
					sum = Math.min(sum, 64 - sum); // 만약 sum이 33이 넘어가면 반대로 수행하는 것이 더 적다.
				}
				
				
				min = Math.min(min, sum);
				
			}
		}
		
		// 출력
		System.out.println(min);
	}
}
