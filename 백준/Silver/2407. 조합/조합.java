import java.math.BigInteger;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		// nCm (5 ≤ n ≤ 100, 5 ≤ m ≤ 100, m ≤ n)
		// nCr = n! / ( (n-r)! * r! )
		// 일정 숫자 이상 넘어가게되면 int형으론 택도없음
		// => BigInteger 이란 자료형을 사용해야함
//		BigInteger bigNumber1 = new BigInteger("100000");
//		BigInteger bigNumber2 = new BigInteger("10000");
//				
//		System.out.println("덧셈(+) :" +bigNumber1.add(bigNumber2));
//		System.out.println("뺄셈(-) :" +bigNumber1.subtract(bigNumber2));
//		System.out.println("곱셈(*) :" +bigNumber1.multiply(bigNumber2));
//		System.out.println("나눗셈(/) :" +bigNumber1.divide(bigNumber2));
//		System.out.println("나머지(%) :" +bigNumber1.remainder(bigNumber2));
		
		int n, m;
		BigInteger[] fac;
		BigInteger res;
		
		Scanner sc = new Scanner(System.in);
		
		n = sc.nextInt();
		m = sc.nextInt();
		fac = new BigInteger[n+1];
		fac[0] = BigInteger.ONE;
		fac[1] = BigInteger.ONE
				;
		for(int i = 1; i <= n; i++) {
			BigInteger bigNumber = new BigInteger(Integer.toString(i));
			fac[i] = fac[i-1].multiply(bigNumber);
		}
		
		res = fac[n].divide(fac[n-m].multiply(fac[m]));
		
		System.out.println(res);
	}

}
