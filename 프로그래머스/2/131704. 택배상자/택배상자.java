import java.util.*;

class Solution {
    static int n;
    static Stack<Integer> stack; // 보조 컨베이어 벨트
    public int solution(int[] order) {
        // 1 ~ n 까지의 택배 상자
        // 택배 기사님이 알려준 순서대로
        // 보조 벨트는 가장 마지막에 보관한 상자만 꺼내기 가능 => 스택
        
        int answer = 0;
        
        n = order.length;
        stack = new Stack<>();
        
        int index = 1; // 순서대로 나오는 택배들
        
        for(int i = 0; i < n; i++) {
            // index 값이 order[i] 보다 클 때 => 스택에서 꺼내기, 꺼낸 값이 다르면 break
            if(index > order[i]) {
                int value = stack.pop();
                
                if(value == order[i]) {
                    answer++;
                    continue;
                } else {
                    break;
                }
            }
            
            // index 값이 order[i] 같을 때 => 바로 택배 내리기
            else if (index == order[i]) {
                index++;
                answer++;
                continue;
            }
            
            // index 값이 order[i] 보다 작을 때 => index가 order[i]와 같아질때까지 스택에 넣기
            else if (index < order[i]) {
                while(true) {
                    if(index == order[i]) {
                        answer++;
                        index++;
                        break;
                    }
                    
                    stack.push(index++);
                }
            }
        }
        
        
        return answer;
    }
}