
import java.io.*;
import java.util.*;
 
public class Main {
    static Point first = new Point(Integer.MAX_VALUE, Integer.MAX_VALUE);
     
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new   InputStreamReader(System.in));
        StringTokenizer st = null;
         
        List<Point> points = new ArrayList<Point>();
        int N = Integer.parseInt(br.readLine());
        for(int i=1; i<=N; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            points.add(new Point(x, y));
        }
         
        Stack cvh = convexHull(points);
         
        // 정답 출력
        System.out.println(cvh.size());
    }
     
    private static Stack convexHull(List<Point> points) {
         //y좌표가 가장 작은 점을 기준점(first)으로 잡습니다.
        for (int i = 0; i < points.size(); i++) {
            if (points.get(i).y < first.y) {
                first = points.get(i);
            }
            // y좌표가 동일한 경우 x좌표 비교
            else if (points.get(i).y == first.y) {
                if (points.get(i).x < first.x) {
                    first = points.get(i);
                }
            }
        }
 
        // 기준점과 나머지점들이 ccw로 반시계방향(좌회전)이 되도록 정렬.
        // 만약, 세점이 일직선상에 있으면 거리가 증가하도록 정렬
        points.sort(new Comparator<Point>() {
            @Override
            public int compare(Point second, Point third) {
                int result = ccw(first, second, third);
                if (result > 0)
                    return -1;
                else if (result < 0)
                    return 1;
                else { // result == 0으로 세 점이 일직선상에 있는 경우
                    if (dist(first, second) > dist(first, third))
                        return 1;
                }
                return -1;
            }
        });
         
        // Graham Scan 알고리즘
        Stack<Point> stack = new Stack<Point>();
        stack.add(first);
        for(int i = 1; i < points.size(); i++){
            while(stack.size() > 1 && ccw(stack.get(stack.size() - 2), stack.get(stack.size() - 1), points.get(i)) <= 0){
                stack.pop();
            }
            stack.add(points.get(i));
        }
         
        return stack;
    }
 
    private static int ccw(Point a, Point b, Point c) {
        long result = (a.x * b.y + b.x * c.y + c.x * a.y) - (b.x * a.y + c.x * b.y + a.x * c.y);
        if (result > 0)
            return 1;
        if (result < 0)
            return -1;
        return 0;
    }
 
    private static long dist(Point a, Point b) {
        return (b.x - a.x) * (b.x - a.x) + (b.y - a.y) * (b.y - a.y);
    }
}
 
class Point {
    long x, y;
    public Point(long x, long y) {
        this.x = x;
        this.y = y;
    }
}