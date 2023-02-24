import java.util.ArrayList;
import java.util.Scanner;

public class Main {    
	static ArrayList<Node>[] list;
    static boolean[] visited;
    static int max = 0;
    static int node;
    
    public static void main(String args[]) {
    	// 가장 먼 거리를 이루는 두개의 노드를 찾을 때 굳이 모든 점에서 탐색하지 않아도 된다.
    	// 임의의 점에서 시작하여 가장 먼 거리를 가지는 노드를 찾고,
    	// 그 노드에서 다시 한번 가장 먼 거리를 가지는 노드를 찾게 되면 총 2번의 탐색으로 트리의 지름을 구할 수 있다.
    	
    	int V;
    	
        Scanner sc = new Scanner(System.in);
        
        V = sc.nextInt();
        list = new ArrayList[V + 1]; 
        for(int i = 1; i < V + 1; i++) {
            list[i] = new ArrayList<>();
        }
        
        for(int i = 0; i < V; i++) {
            int s = sc.nextInt();
            while(true) {
                int e = sc.nextInt();
                if(e == -1) break;
                int cost = sc.nextInt();
                list[s].add(new Node(e, cost));
            }
        }
        
        visited = new boolean[V + 1];
        dfs(1, 0);
        
        visited = new boolean[V + 1];
        dfs(node, 0);
        
        System.out.println(max);
    }
    
    public static void dfs(int x, int len) {
        if(len > max) {
            max = len;
            node = x;
        }
        visited[x] = true;
        
        for(int i = 0; i < list[x].size(); i++) {
            Node n = list[x].get(i);
            if(visited[n.e] == false) {
                dfs(n.e, n.cost + len);
                visited[n.e] = true;
            }
        }
        
    }
    
    public static class Node {
        int e;
        int cost;
        
        public Node(int e, int cost) {
            this.e = e;
            this.cost = cost;
        }
    }
}