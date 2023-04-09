import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Solution {

	static int res;
	static int[] pay;
	// 1일 이용권, 1달 이용권, 3달 이용권, 1년 이용권
	static int[] month;
	
	public static void main(String[] args) {
		
		int T;
		
		Scanner sc = new Scanner(System.in);
		
		T = sc.nextInt();
		
		for(int tc = 1; tc <= T; tc++) {
			pay = new int[4];
			month = new int[13];
			for(int i = 0; i < 4; i++) {
				pay[i] = sc.nextInt();
			}
			for(int i = 1; i <= 12; i++) {
				month[i] = sc.nextInt();
			}
			res = pay[3];
			
//			dfs(1, 0);
			bfs();
			
			System.out.println("#" + tc + " " + res);
		}
	}

	private static void dfs(int mon, int p) {
		if(mon > 12) {
			res = Math.min(res, p);
			return;
		}
		
		dfs(mon + 1, p + pay[0]*month[mon]);
		dfs(mon + 1, p + pay[1]);
		dfs(mon + 3, p + pay[2]);
		
	}

	private static void bfs() {
		Queue<Data> q = new LinkedList<Data>();
		
		q.offer(new Data(0, 0));
		
		while(!q.isEmpty()) {
			Data data =  q.poll();
			
			if(data.mon >= 12) {
				res = Math.min(res, data.p);
				continue;
			}
			
			q.offer(new Data(data.mon + 1, data.p + pay[0]*month[data.mon + 1])); // 1일 이용권
			q.offer(new Data(data.mon + 1, data.p + pay[1])); // 1달 이용권
			q.offer(new Data(data.mon + 3, data.p + pay[2])); // 3달 이용권
		}
	}
	
	static class Data {
		int mon; // 달
		int p; // 지출
		public Data(int mon, int p) {
			super();
			this.mon = mon;
			this.p = p;
		}
	}
}
