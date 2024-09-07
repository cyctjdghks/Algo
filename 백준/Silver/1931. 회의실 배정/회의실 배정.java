import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		// 한개의 회의실, N개의 회의에 대하여 사용표
		// 회의 수 N
		// 두번째 줄 부터 N+1 줄 까지 각 회의의 정보
		// 회의 시작 시간과 끝나는 시간
		// 최대 사용할 수 있는 회의의 최대 개수
		
		int N;
		int[][] room;
		
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		room = new int[N][2];
		
		for(int i = 0; i < N; i++) {
			room[i][0] = sc.nextInt();	// 시작시간 
			room[i][1] = sc.nextInt();	// 종료시간 
		}
		
		
		// 끝나는 시간을 기준으로 정렬하기 위해 compare 재정의 
		Arrays.sort(room, new Comparator<int[]>() {
			
			@Override
			public int compare(int[] o1, int[] o2) {
				
				// 종료시간이 같을 경우 시작시간이 빠른순으로 정렬해야한다.  
				if(o1[1] == o2[1]) {
					return o1[0] - o2[0];
				}
				
				return o1[1] - o2[1];
			}
 
		});
		
		int count = 0;
		int prev_end_time = 0;
		
		for(int i = 0; i < N; i++) {
			
			// 직전 종료시간이 다음 회의 시작 시간보다 작거나 같다면 갱신 
			if(prev_end_time <= room[i][0]) {
				prev_end_time = room[i][1];
				count++;
			}
		}
		
		System.out.println(count);
	}

}
