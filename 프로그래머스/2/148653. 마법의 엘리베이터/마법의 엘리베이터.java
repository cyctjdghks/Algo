import java.util.*;

class Solution {
    public int solution(int storey) {
        int answer = 0;
        
        while(storey > 0) {
            int tmp1 = storey % 10;
            int tmp2 = storey / 10;
            
        
            if(tmp1 == 5) {
                if(tmp2 % 10 >= 5) {
                    answer += 10 - tmp1;
                    tmp2++;
                } else {
                    answer += tmp1;
                }
            } else if(tmp1 > 5) {
                answer += 10 - tmp1;
                tmp2++;
            } else {
                answer += tmp1;
            }
            
            storey = tmp2;
        }
        
        return answer;
    }
}