import java.util.Scanner;

public class Main {
	
	static int N;
	static int[][] W;
	static boolean[] v;
	static int res = Integer.MAX_VALUE;
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		W = new int[N][N];
		v = new boolean[N];
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
			W[i][j] = sc.nextInt();
			}
		}
		
		v[0] = true;
		dfs(0, 0, 1, 0);
		
		System.out.println(res);
	}

	private static void dfs(int start, int now, int cnt, int cost) {
		if(now == start && cost > 0) {
			res = Math.min(res, cost);
			return;
		}
		
		for(int i = 0; i < N; i++) {
			if(W[now][i] > 0) {
				if(i == start && cnt == N) {
					cost += W[now][i];
					dfs(start, i, cnt + 1, cost);
				}
				
				if(!v[i]) {
					v[i] = true;
					cost += W[now][i];
					
					dfs(start, i, cnt+1, cost);
					
					v[i] = false;
					cost -= W[now][i];
				}
			}
		}
	}

}
