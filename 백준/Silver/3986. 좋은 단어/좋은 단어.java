import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int res = 0;

        for (int i = 0; i < N; i++) {
            String word = sc.next();

            Stack<Character> stack = new Stack<>();

            stack.push(word.charAt(0));

            for (int j = 1; j < word.length(); j++) {
                char c = word.charAt(j);

                if (!stack.isEmpty() && stack.peek().equals(c)) {
                    stack.pop();
                } else {
                    stack.push(c);
                }
            }

            if (stack.isEmpty()) {
                res++;
            }
        }

        System.out.println(res);
    }
}
