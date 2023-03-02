import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] a = new int[n];
        int[] d1 = new int[n];
        int[] d2 = new int[n];
        
        for (int i = 0; i < n; i++) {
            a[i] = sc.nextInt();
            d1[i] = 1;
            d2[i] = 1;
        }
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (a[j] < a[i] && d1[i] < d1[j] + 1) {
                    d1[i] = d1[j] + 1;
                }
            }
        }
        
        for (int i = n - 1; i >= 0; i--) {
            for (int j = n - 1; j > i; j--) {
                if (a[j] < a[i] && d2[i] < d2[j] + 1) {
                    d2[i] = d2[j] + 1;
                }
            }
        }
        
        int ans = 0;
        for (int i = 0; i < n; i++) {
            ans = Math.max(ans, d1[i] + d2[i] - 1);
        }
        
        System.out.println(ans);
    }
}
