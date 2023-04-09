import java.util.Arrays;
import java.util.Scanner;

public class Solution {

	static int D, W, K;
	static int[][] map;
	static int res;
	static int[] list;
	
	public static void main(String[] args) {
		int T;
		
		Scanner sc = new Scanner(System.in);
		
		T = sc.nextInt();
		
		for(int tc = 1; tc <= T; tc++) {
			
			D = sc.nextInt();
			W = sc.nextInt();
			K = sc.nextInt();
			map = new int[D][W];
			for(int i = 0; i < D; i++) {
				for(int j = 0; j < W; j++) { 
					map[i][j] = sc.nextInt();
				}
			}
			res = K;
			list = new int[D];
			Arrays.fill(list, -1);
			
			dfs(0, 0);
			
//			print();
			
			System.out.println("#" + tc + " " + res);
		}
	}
	
	private static void dfs(int idx, int cnt) {
		if(idx == D) {
			if(isCheck()) {
				res = Math.min(res, cnt);
			}
			return;
		}
		if(cnt >= res) {
			return;
		}
		
		list[idx] = -1;
		dfs(idx+1, cnt);
		list[idx] = 0;
		dfs(idx+1, cnt+1);
		list[idx] = 1;
		dfs(idx+1, cnt+1);
	}

	private static boolean isCheck() {
		int cnt = 0;
		int a, b;
		
		for(int j = 0; j < W; j++) {
			cnt = 1;
			
			for(int i = 0; i < D-1; i++) { 
				a = map[i][j];
				b = map[i+1][j];
				
				if(list[i] != -1) {
					a = list[i];
				}
				if(list[i+1] != -1) {
					b = list[i+1];
				}
				
				if(a == b) {
					cnt++;
					if(cnt >= K) {
						break;
					}
				} else {
					cnt = 1;
				}
			}
			if(cnt < K) {
				return false;
			}
		}
		
		
		
		return true;
	}

	public static void print() {
		for(int i = 0; i < D; i++) {
			for(int j = 0; j < W; j++) { 
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}

}
