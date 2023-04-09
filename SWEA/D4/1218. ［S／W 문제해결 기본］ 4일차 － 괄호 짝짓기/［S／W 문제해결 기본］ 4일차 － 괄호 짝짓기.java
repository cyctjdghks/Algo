import java.util.Scanner;
import java.util.Stack;

/*

() - 0
[] - 1
{} - 2
<> - 3
 
 */
public class Solution {

	public static void main(String[] args) {
		// 10개의 테스트 케이스 길이
		// 테스트케이스의 길이

		int T = 10;
		int len;
		String str = "";
		Stack<String> stack = new Stack<>();
		int swit = -1;
		int valid = 1;
		char ch;

		Scanner sc = new Scanner(System.in);

		for (int tc = 1; tc <= T; tc++) {
			swit = -1;
			valid = 1;
			
			len = sc.nextInt();
			str = sc.next();
			
			loop:
			for (int i = 0; i < len; i++) {
				ch = str.charAt(i);
				if (ch == '(' || ch == ')') {
					swit = 0;
				} else if (ch == '[' || ch == ']') {
					swit = 1;
				} else if (ch == '{' || ch == '}') {
					swit = 2;
				} else if (ch == '<' || ch == '>') {
					swit = 3;
				}

				switch (swit) {
				case 0:
					if(ch == '(') {
						stack.push(String.valueOf(ch));
					} else {
						if(!stack.pop().equals("(")) {
							valid = 0;
							break loop;
						}
					}
					break;
				case 1:
					if(ch == '[') {
						stack.push(String.valueOf(ch));
					} else {
						if(!stack.pop().equals("[")) {
							valid = 0;
							break loop;
						}
					}
					break;
				case 2:
					if(ch == '{') {
						stack.push(String.valueOf(ch));
					} else {
						if(!stack.pop().equals("{")) {
							valid = 0;
							break loop;
						}
					}
					break;
				case 3:
					if(ch == '<') {
						stack.push(String.valueOf(ch));
					} else {
						if(!stack.pop().equals("<")) {
							valid = 0;
							break loop;
						}
					}
					break;
				default:
					break;
				}
			}

			System.out.println("#" + tc + " " + valid);
		}
	}

}
