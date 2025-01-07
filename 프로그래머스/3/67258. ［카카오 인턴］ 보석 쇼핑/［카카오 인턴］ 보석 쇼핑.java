import java.util.*;

class Solution {
    
    Set<String> set = new HashSet<>(); // 보석 종류
    Map<String, Integer> map = new HashMap<>(); // 종류 별 갯수
    Queue<String> q = new LinkedList<>(); // 탐색 중인 구간
    
    public int[] solution(String[] gems) {
        int[] answer = new int[2];
        
        for(String gem : gems) {
            set.add(gem);
        }
        
        int start = 0, tempStart = 0; // 최종 구간 시작 인덱스, 현재 구간 시작 인덱스
        int len = gems.length; // 가장 짧은 구간의 길이

        // 보석 배열을 순회
        for (int i = 0; i < gems.length; i++) {
            // 현재 보석을 큐에 추가하고, 맵에서 해당 보석의 개수를 증가
            q.add(gems[i]);
            map.put(gems[i], map.getOrDefault(gems[i], 0) + 1);

            // 큐의 맨 앞 보석이 중복될 경우, 중복 제거를 위해 큐에서 제거
            while (true) {
                String gem = q.peek(); // 큐의 맨 앞 보석
                if (map.get(gem) > 1) { // 맵에서 해당 보석이 2개 이상일 경우
                    map.put(gem, map.get(gem) - 1); // 맵에서 보석 개수를 감소
                    q.poll(); // 큐에서 맨 앞 보석 제거
                    tempStart++; // 현재 구간의 시작 인덱스 이동
                } else {
                    break; // 중복이 없으면 종료
                }
            }

            // 현재 구간이 모든 보석 종류를 포함하는 경우
            if (map.size() == set.size()) {
                // 현재 구간이 기존 구간보다 짧은 경우 갱신
                if (len > q.size()) {
                    len = q.size(); // 최소 구간 길이 갱신
                    start = tempStart; // 최종 구간 시작 위치 갱신
                }
            }
        }

        return new int[] {start + 1, start + len};
    }
}