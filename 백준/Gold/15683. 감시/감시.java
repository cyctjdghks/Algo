import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

// 8. BOJ 15683 감시
// 시간초과 수정
public class Main {

	static int N;
	static int M;
	static int[][] map;
	static int min;
	static ArrayList<CCTV> list = new ArrayList<>();

	static class CCTV {
		int x, y;
		int type;

		public CCTV(int x, int y, int type) {
			this.x = x;
			this.y = y;
			this.type = type;
		}
	}

	public static void main(String[] args) {
		// 0은 빈칸
		// 6은 벽
		// 1 ~ 5 는 cctv
		// cctv로 탐색이 가능하면 -1
		// 1번 - 우측, 2번 - 아래, 3번 - 좌측, 4번 - 위

		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		M = sc.nextInt();
		map = new int[N][M];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				map[i][j] = sc.nextInt();
				if (1 <= map[i][j] && map[i][j] <= 5) {
					list.add(new CCTV(i, j, map[i][j]));
				}
			}
		}
		min = N * M;
		search(0, map);

		System.out.println(min);
	}

	static void search(int idx, int[][] nMap) {
		//		종료
		if(idx == list.size()) {
			int cnt = 0;
			//			사작지대 갯수 세기
			for(int i = 0 ; i < N; i++) {
				for(int j = 0; j < M ;j++) {
					if(nMap[i][j] == 0) {
						cnt++;
					}
				}
			}
			min = Math.min(min, cnt);
			return;
		}
		//		재귀호출
		//		리스트에서 CCTV 뽑아서 감시 솔루션
		CCTV cctv = list.get(idx);
		int x = cctv.x;
		int y = cctv.y;
		int[][] vMap = new int[N][M];
		switch(cctv.type) {
		case 1 : //  1번 감시 카메라
			for(int d = 1; d <= 4 ; d++) {
				//				감시
				for(int i = 0; i < N; i++) {
					vMap[i] = Arrays.copyOf(nMap[i],M);
				}
				Init(x, y,vMap, d);
				//				다음번째  CCTV 호출
				search(idx + 1, vMap);
				//				백트래킹 (X)
			}
			break;
		case 2 : //  2번 감시 카메라
			for(int d = 1; d <= 2 ; d++) {
				//				감시
				for(int i = 0; i < N; i++) {
					vMap[i] = Arrays.copyOf(nMap[i],M);
				}
				Init(x, y,vMap, d);
				Init(x, y,vMap, d+2);
				//				다음번째  CCTV 호출
				search(idx + 1, vMap);
				//				백트래킹 (X)
			}
			break;	
		case 3 : //  3번 감시 카메라
			for(int d = 1; d <= 4 ; d++) {
				//				감시
				for(int i = 0; i < N; i++) {
					vMap[i] = Arrays.copyOf(nMap[i],M);
				}
				Init(x, y,vMap, d);
				Init(x, y,vMap, d-1 <= 0 ? d-1 + 4 : d-1);
				//				다음번째  CCTV 호출
				search(idx + 1, vMap);
				//				백트래킹 (X)
			}
			break;	
		case 4 : //  4번 감시 카메라
			for(int d = 1; d <= 4 ; d++) {
				//				감시
				for(int i = 0; i < N; i++) {
					vMap[i] = Arrays.copyOf(nMap[i],M);
				}
				Init(x, y,vMap, d);
				Init(x, y,vMap, d-1 <= 0 ? d-1 + 4 : d-1);
				Init(x, y,vMap, d-2 <= 0 ? d-2 + 4 : d-2);
				//				다음번째  CCTV 호출
				search(idx + 1, vMap);
				//				백트래킹 (X)
			}
			break;	
		case 5 : //  5번 감시 카메라
			//				감시
			for(int i = 0; i < N; i++) {
				vMap[i] = Arrays.copyOf(nMap[i],M);
			}
			Init(x, y,vMap, 1);
			Init(x, y,vMap, 2);
			Init(x, y,vMap, 3);
			Init(x, y,vMap, 4);			
			//				다음번째  CCTV 호출
			search(idx + 1, vMap);
			//				백트래킹 (X)
			break;			
		}

	}

	// 설정한 위치로 cctv가 탐색 - 탐색된 구역은 음수
	public static void Init(int r, int c, int[][] cMap, int num) {
		// 1번 - 우측, 2번 - 아래, 3번 - 좌측, 4번 - 위
		switch (num) {
		case 1:
			for (int k = c; k < M; k++) {
				if (cMap[r][k] == 6) {
					break;
				}
				if (cMap[r][k] <= 0) {
					cMap[r][k] = -1;
				}
			}
			break;
		case 2:
			for (int k = r; k < N; k++) {
				if (cMap[k][c] == 6) {
					break;
				}
				if (cMap[k][c] <= 0) {
					cMap[k][c] = -1;
				}
			}
			break;
		case 3:
			for (int k = c; k >= 0; k--) {
				if (cMap[r][k] == 6) {
					break;
				}
				if (cMap[r][k] <= 0) {
					cMap[r][k] = -1;
				}
			}
			break;
		case 4:
			for (int k = r; k >= 0; k--) {
				if (cMap[k][c] == 6) {
					break;
				}
				if (cMap[k][c] <= 0) {
					cMap[k][c] = -1;
				}
			}
			break;
		default:
			break;
		}
	}

}
