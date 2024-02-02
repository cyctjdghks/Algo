import java.util.*;

class Solution {
    public int[] solution(int[] array, int[][] commands) {
        int[] answer = new int[commands.length];
        int[] arr = {};
        
        for(int i = 0; i < commands.length; i++) {
            int num = commands[i][1] - commands[i][0] + 1;
            arr = new int[num];
            
            int idx = commands[i][0] - 1;
            
            for(int j = 0; j < num; j++) {
                arr[j] = array[idx++];
            }
            
            Arrays.sort(arr);
            
            answer[i] = arr[commands[i][2] - 1];
        }
        
        return answer;
    }
}