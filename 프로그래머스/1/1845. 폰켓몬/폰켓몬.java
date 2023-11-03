import java.util.*;

class Solution {
    static int N;
    static int selec;
    static int type;
    static int[] arr;
    public int solution(int[] nums) {
        int answer = 0;
        
        N = nums.length;
        selec = N/2;
        type = 0;
        arr = new int[200000 + 1];
        for(int i = 0; i < N; i++) {
            arr[nums[i]]++;
        }
        for(int i = 0; i <= 200000; i++) {
            if(arr[i] >= 1) answer++;
        }
        
        answer = Math.min(selec, answer);
        
        
        return answer;
    }
}