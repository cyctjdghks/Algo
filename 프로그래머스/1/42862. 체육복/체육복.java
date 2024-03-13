import java.util.*;

class Solution {
    
    // 학생 수 2 ~ 30
    // 도난 당한 수 1 ~ n
    // 여벌 체육복 1 ~ n
    // 여벌 있는 경우만 빌려줄 수 있음
    // 여벌있는 학생이 도난 당하면 빌려줄 수 없음
    // 앞 뒤로만 빌려줄 수 잇음
    
    public int solution(int n, int[] lost, int[] reserve) {
        int answer = 0;
        
        Arrays.sort(lost);
        Arrays.sort(reserve);
        
        // 도난 당하지 않은 학생 수
        answer = n - lost.length;
        
        // 여벌있는 학생이 도난 당하면 빌려줄 수 없음
        for (int i = 0; i < lost.length; i++) {
			for (int j = 0; j < reserve.length; j++) {
				if (lost[i] == reserve[j]) {
					answer++;
					lost[i] = -1;
					reserve[j] = -1;
                    break;
				}
			}
		}
        
        // 여벌 있는 경우만 빌려줄 수 있음
        for (int i = 0; i < lost.length; i++) {
			for (int j = 0; j < reserve.length; j++) {
				if (lost[i] - 1 == reserve[j] || lost[i] + 1 == reserve[j]) {
					answer++;
					reserve[j] = -1;
					break;
				}
			}
		}
        
        
        return answer;
    }
}