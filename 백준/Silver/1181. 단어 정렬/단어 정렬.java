import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		// 입력
		Scanner sc = new Scanner(System.in);
		
		// 필요 인자들 초기화
		String[] words; // 입력 받은 단어들을 저장할 배열
		int N = 0; // 입력 받을 횟수
		String data = ""; // 입력 받을 단어를 저장할 String
		
		// 입력 받은 데이터들 초기화
		// 조건1 - 크기 순 정렬
		// 조건2 - 사전 순 정렬
		N = sc.nextInt();
		sc.nextLine();
		words = new String[N];
		for(int i = 0; i < N; i++) {
			data = sc.nextLine();
			words[i] = data;
		}
		
		Arrays.sort(words, new Comparator<String>() { // 라이브러리 사용
			public int compare(String s1, String s2) {
				// 단어 길이가 같을 경우 
				if (s1.length() == s2.length()) {
					return s1.compareTo(s2);
				} 
				// 그 외의 경우 
				else {
					return s1.length() - s2.length();
				}
			}
		});
		// 참조 사이트
		// Arrays.sort 참조
		// https://codechacha.com/ko/java-sorting-array/
		// s1.compareTo(s2) 참조
		// https://tworab.tistory.com/16
		
		// 출력 - 중복단어의 경우 한번씩만 출력
		for(int i = 0; i < words.length; i++) {
			if(i >= 1) {
				if(!words[i].equals(words[i-1])) {
					System.out.println(words[i]);
				}
			} else {
				System.out.println(words[i]);
			}
		}
	}
}
