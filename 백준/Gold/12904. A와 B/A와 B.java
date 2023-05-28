import java.util.Scanner;

public class Main {
    static String S, T;
    static int res;
    public static void main(String[] args) {
        // 문자열의 뒤에 A를 추가한다.
        // 문자열을 뒤집고 뒤에 B를 추가한다.
        Scanner sc = new Scanner(System.in);

        S = sc.next();
        T = sc.next();
        res = 0;

        search();

        System.out.println(res);
    }

    public static void search() {
        String str = T;

        while (true) {
            if (str.length() == S.length()) {
                break;
            }

            if(str.charAt(str.length() - 1) == 'A') {
                // 문자열의 뒤에 A를 추가한다.
                // => A는 그냥 자르기
                str = str.substring(0, str.length() - 1);
            } else if(str.charAt(str.length() - 1) == 'B') {
                // 문자열을 뒤집고 뒤에 B를 추가한다.
                // => B면 자르고 뒤집기
                str = str.substring(0, str.length() - 1);

                StringBuffer sb = new StringBuffer(str);
                str = sb.reverse().toString();
            }
        }

        if (str.equals(S)) {
            res = 1;
        }
    }
}
