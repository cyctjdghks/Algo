import java.util.Scanner;
import java.util.Stack;

public class Solution {

	public static void main(String[] args) {
		// 전체 TC 개수
		// 각 TC의 입력값
		
		int TC;
		String ironbar;
		int count;
		Stack<String> stack = new Stack<>();
		
		
		Scanner sc = new Scanner(System.in);
		
		TC = sc.nextInt();
		sc.nextLine();
		
		for(int tc = 1; tc <= TC; tc++) {
			count = 0;
			stack.clear();
			
			ironbar = sc.nextLine();
			
			for(int i = 0; i < ironbar.length(); i++) {
				if(ironbar.charAt(i) == '(') {
					stack.add(String.valueOf(ironbar.charAt(i)));
				} else if (ironbar.charAt(i) == ')') {
					if(ironbar.charAt(i-1) == '(') { // 자르기
						stack.pop();
						count += stack.size();
					} else if (ironbar.charAt(i-1) == ')'){ // 쇠막대 종료
						count += 1;
						stack.pop();
					}
				}
			}
			
			System.out.println("#" + tc + " " + count);
		}
	}

}
