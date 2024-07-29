import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {

	static int N;
	static int K;
	static int[] arr;
	static int res;
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		K = sc.nextInt();
		arr = new int[100001];
		Arrays.fill(arr, Integer.MAX_VALUE);
		res = 0;
		
		bfs();
		
		System.out.println(arr[K]);
	}

	private static void bfs() {
		Queue<Data> q = new LinkedList<>();
		
		q.offer(new Data(N, 0));
		arr[N] = 0;
		
		while(!q.isEmpty()) {
			Data d = q.poll();
			
//			if(d.x == K) {
//				res = d.time;
//				break;
//			}
			
			if(isInBound(d.x - 1) && arr[d.x - 1] > d.time + 1) {
				q.offer(new Data(d.x - 1, d.time + 1));
				arr[d.x - 1] = d.time + 1;
			}
			if(isInBound(d.x + 1) && arr[d.x + 1] > d.time + 1) {
				q.offer(new Data(d.x + 1, d.time + 1));
				arr[d.x + 1] = d.time + 1;
			}
			if(isInBound(2 * d.x) && arr[2 * d.x] > d.time) {
				q.offer(new Data(2 * d.x, d.time));
				arr[2 * d.x] = d.time;
			}
		}
	}
	
	public static boolean isInBound(int x) {
		return x >= 0 && x <= 100000;
	}
	
	static class Data{
		int x;
		int time;
		public Data(int x, int time) {
			this.x = x;
			this.time = time;
		}
	}

}
