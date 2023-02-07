import java.util.Scanner;

public class Main {
	
	static int N;
	static String map[];
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		
		map = new String[N];
		map[0] = "  *  ";
		map[1] = " * * ";
		map[2] = "*****";
		
		for(int k = 0; k <= (int) (Math.log10(N / 3) / Math.log10(2)); k++) {
			if(k == 0) continue;
			star(k);
		}
		
		for(int i = 0; i < N; i++) {
			System.out.println(map[i]);
		}
		
	}

	private static void star(int k) {
		int depth = 3 * (int) Math.pow(2, k) / 2;
		
		for(int i = 0; i < depth; i++) {
			map[depth + i] = map[depth + i - depth] + " " + map[depth + i - depth];
		}
		
		String space = "";
		while (space.length() < depth) {
			space += " ";
		}
		for (int i = 0; i < depth; ++i) {
			map[i] = space + map[i] + space;
		}
//		for(int i = 0; i < depth; i++) {
//			String temp = map[i];
//			map[i] = "";
//			
//			for(int j = 0; j < depth; j++) {
//				map[i] += " ";
//			}
//			map[i] += temp;
//			for(int j = 0; j < depth; j++) {
//				map[i] += " ";
//			}
//		}
	}

}
