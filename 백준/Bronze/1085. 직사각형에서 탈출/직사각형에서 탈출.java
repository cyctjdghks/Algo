import java.util.Scanner;

public class Main {
    static int x, y, w, h;
    static int res;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        x = sc.nextInt();
        y = sc.nextInt();
        w = sc.nextInt();
        h = sc.nextInt();
        res = Math.min(Math.min(w - x, x - 0), Math.min(h - y, y - 0));

        System.out.println(res);
    }
}
