class Solution {
    public long solution(int n, int m, int x, int y, int[][] queries) {
        long left = y;
        long right = y;
        long top = x;
        long bottom = x;

        for (int i = queries.length - 1; i >= 0; i--) {
            int direction = queries[i][0];
            int dist = queries[i][1];

            if (direction == 0) { // LEFT
                if (left != 0) 
                    left += dist;
                right = Math.min(m - 1, right + dist);
            } else if (direction == 1) { // RIGHT
                if (right != m - 1) 
                    right -= dist;
                left = Math.max(0, left - dist);
            } else if (direction == 2) { // UP
                if (top != 0) 
                    top += dist;
                bottom = Math.min(n - 1, bottom + dist);
            } else if (direction == 3) { // DOWN
                if (bottom != n - 1) 
                    bottom -= dist;
                top = Math.max(0, top - dist);
            }

            // 범위가 격자를 벗어나면 더 이상 유효한 시작점이 없음
            if (left >= m || right < 0 || top >= n || bottom < 0) {
                return 0;
            }
        }

        // 최종적으로 가능한 시작점의 범위가 격자 내에 있다면 개수를 반환
        return (right - left + 1) * (bottom - top + 1);
    }
}