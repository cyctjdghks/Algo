import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		// A B C
		// A 를 B 번 곱한 수 = A 의 B승
		// 이를 C 로 나눈 나머지
		// 1 ~ 2,147,483,647
		// 제곱의 성질 이용 - 지수 법칙
		// 10^11 = 10^5 * 10^5 * 10
		// 10^5 = 10^2 * 10^2 * 10
		// 10^2 = 10 * 10
		// 나머지의 성질  이용- 모듈러 성질
		// (a * b ) % C = (a % C * b % C ) % C
		
		// 따라서
		// 10^11 % 12
		// => ( 10^5 * 10^5 * 10 ) % 12
		// => ( (10^5 % 12) * (10^5 % 12) * (10 % 12) ) % 12
		// 10^2 % 12
		// => (10 * 10) % 12
		// => ( (10 % 12) * (10 % 12) ) % 12
		// => A(10) 의 제곱이 1이 될떄까지 반복
		// => A 와 C 는 놔둔채 B 값을 감소 시키면서 재귀함수 사용
		
		int A, B, C;
		int result;
		
		Scanner sc = new Scanner(System.in);
		
		A = sc.nextInt();
		B = sc.nextInt();
		C = sc.nextInt();
		
		// int 의 MAX_VALUE 는 2,147,483,647
		// 따라서 그대로 사용하면 X
//		result = ((int) Math.pow(A, B)) % C;
		
		result = (int) pow(A, B, C);
		
		System.out.println(result);
	}
	
	// int 형 선언 시 틀림 => Math.pow() 메소드도 long 형
	static long pow(int a, int b, int c) {
        if (b == 0)
            return 1;

        long n = pow(a, b / 2, c);
        if (b % 2 == 0)
            return n * n % c;
        else
            return (n * n % c) * a % c;
    }
}
