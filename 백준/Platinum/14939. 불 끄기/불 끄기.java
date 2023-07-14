import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
 
/**
 * 백준 14939 불 끄기. 완전탐색 사용
 *
 * @author 2sssg
 */
public class Main {
 
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	// 기본 입력 배열
	static int[][] arr = new int[10][10];
	// 임시 배열
	static int[][] temparr = new int[10][10];
	static int tempanswer, answer = Integer.MAX_VALUE;
	static int[] dx = {0, 0, 1, 0, -1};
	static int[] dy = {0, 1, 0, -1, 0};
 
 
	/**
	 * origin 배열을 풀이에 사용할 배열로 복사해주는 함수
	 */
	static void init() {
		tempanswer = 0;
		for (int i = 0; i < 10; ++i) {
			temparr[i] = arr[i].clone();
		}
	}
 
	/**
	 * 눌러지는 방의 좌표를 받고 상하좌우방의 상태값을 변경하는 함수입니다.
	 *
	 * @param x 클릭할 x좌표
	 * @param y 클릭할 y좌표
	 */
	static void click(int x, int y) {
		for (int i = 0; i < 5; ++i) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			if (nx < 0 || ny < 0 || nx >= 10 || ny >= 10) {
				continue;
			}
			temparr[nx][ny] ^= 1;
		}
	}
 
	public static void main(String[] args) throws IOException {
 
		// 각 줄을 입력받고 불이 켜져있으면 1 꺼져있으면 0
		for (int i = 0; i < 10; ++i) {
			arr[i] = Arrays.stream(br.readLine().split("")).mapToInt(p -> p.equals("#") ? 0 : 1)
				.toArray();
		}
 
		// 0000000000 ~ 1111111111 비트 순회 (1024가지)
		for (int i = 0; i < 1024; ++i) {
			//초기화
			init();
 
			// 첫 번째 줄 불 켜기
			for (int j = 1; j <= 10; ++j) {
				int num = 1024;
				num = num >> j;
				if ((i & num) > 0) {
					tempanswer++;
					click(0, j - 1);
				}
			}
 
			// 윗줄의 자신의 열이 불이 켜져있으면 불을 꺼주기
			for (int j = 1; j < 10; ++j) {
				for (int k = 0; k < 10; ++k) {
					if (temparr[j - 1][k] == 1) {
						tempanswer++;
						click(j, k);
					}
				}
			}
 
			// 0~9행은 모두 불이 꺼져있음
			// 마지막줄에 불이켜진 방이있는지만 확인해보면 됨
			if (Arrays.stream(temparr[9]).sum() == 0) {
				answer = Math.min(answer, tempanswer);
			}
		}
 
		// 초기값(0xffffffff)이면 불을 다 끌수있는 방법이 없음
		System.out.println(answer == Integer.MAX_VALUE ? "-1" : answer);
	}
}