import java.util.*;

class Solution {
    // 직사각형은 1 ~ 4 개
    // 직사각형 좌표는 좌측 하단 x, y 우측 상단 x, y
    // 모든 좌표는 1 ~ 50
    
    static int[][] map;
    static boolean[][] visit;
    // 동 서 남 북
    static int dx[] = {1, -1, 0, 0};
    static int dy[] = {0, 0, -1, 1};
    static int answer;
    
    public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
        // 최대값 초기화
        answer = 9999_9999;
        // 모서리 부분 처리를 위한 크기 2배
        map = new int[101][101];
        visit = new boolean[101][101];
        
        // 맵 초기화
        for(int[] rec : rectangle) {
            initMap(rec[0]*2, rec[1]*2, rec[2]*2, rec[3]*2);
        }
        
        // bfs로 탐색
        bfs(characterX*2, characterY*2, itemX*2, itemY*2);
        
        // 값에서 2를 나눠야됨
        return answer / 2;
    }
    
    public void bfs(int startX, int startY, int endX, int endY) {
        Queue<Data> q = new LinkedList<>();
        q.add(new Data(startX, startY, 0));
        visit[startX][startY] = true;
        
        while(!q.isEmpty()) {
            Data data = q.poll();
            
            if(data.x == endX && data.y == endY) {
                answer = Math.min(answer, data.dist);
                continue;
            }
            
            for(int d = 0; d < 4; d++) {
                int nx = data.x + dx[d];
                int ny = data.y + dy[d];
                
                if(isInBound(nx, ny)) {
                    if(map[nx][ny] == 1 && !visit[nx][ny]) {
                        q.add(new Data(nx, ny, data.dist + 1));
                        visit[nx][ny] = true;
                    }
                }
            }
        }
    }
    
    public void initMap(int x1, int y1, int x2, int y2) {
        for(int i = x1; i <= x2; i++) {
            for(int j = y1; j <= y2; j++) {
                if(map[i][j] == 2) continue;
                
                map[i][j]=2;
                // 테두리는 1
                if(i == x1 || i == x2 || j == y1 || j == y2){
                    map[i][j]=1;
                }
            }
        }
    }
    
    // 맵 범위 판단
    public boolean isInBound(int x, int y) {
        return x >= 0 && x <= 100 && y >= 0 && y <= 100;
    }
    
    public static class Data {
        int x;
        int y;
        int dist;
        
        public Data(int x, int y, int dist) {
            this.x = x;
            this.y = y;
            this.dist = dist;
        }
    }
}