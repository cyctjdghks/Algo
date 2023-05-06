import java.util.Scanner;

public class Main {

	static Node head = new Node('A', null, null);
	
	public static void main(String[] args) {
		// 이진 트리의 노드의 개수 N (1 ≤ N ≤ 26)
		// 노드와 그의 왼쪽 자식 노드, 오른쪽 자식 노드
		// A부터 차례대로
		// 항상 A가 루트 노드
		// 자식 노드가 없는 경우에는 .
		// 전위 순회(preorder) - root > left > right
		// 중위 순회(inorder) - left > root > right
		// 후위 순회(postorder) - left > right > root
		// root 방문시에 print
		
		int N;
		char data;
		char left;
		char right;
		
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		
		for(int i = 0; i < N; i++) {
			data = sc.next().charAt(0);
			left = sc.next().charAt(0);
			right = sc.next().charAt(0);
			
			insertNode(head, data, left, right);
		}
		
		preOrder(head);
		System.out.println();
		inOrder(head);
		System.out.println();
		postOrder(head);
		System.out.println();
		
	}
	
	public static void insertNode(Node temp, char root, char left, char right) {
		
		if (temp.data == root) {
			temp.left = (left == '.' ? null : new Node(left,null,null));
			temp.right = (right == '.' ? null : new Node(right,null,null)); 
		}
		else {
			if(temp.left != null) insertNode(temp.left, root, left, right);
			if(temp.right != null) insertNode(temp.right, root, left, right);
		}
	}
	
	public static void preOrder(Node node) {
		if(node ==null) return;
		System.out.print(node.data);
		preOrder(node.left);
		preOrder(node.right);
	}
	
	public static void inOrder(Node node) {
		if(node ==null) return;
		inOrder(node.left);
		System.out.print(node.data);
		inOrder(node.right);
	}
	
	public static void postOrder(Node node) {
		if(node ==null) return;
		postOrder(node.left);
		postOrder(node.right);
		System.out.print(node.data);
	}
	
	static class Node{
		char data;
		Node left;
		Node right;
		
		Node(char data, Node left, Node right){
			this.data = data;
			this.left = left;
			this.right = right;
		}
	}
}
