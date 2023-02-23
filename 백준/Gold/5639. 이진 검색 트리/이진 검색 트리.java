import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
//	전위 순회(preorder traverse) : 뿌리(root)를 먼저 방문 => root > left > right 
//	중위 순회(inorder traverse) : 왼쪽 하위 트리를 방문 후 뿌리(root)를 방문 => left > root > right
//	후위 순회(postorder traverse) : 하위 트리 모두 방문 후 뿌리(root)를 방문 => left > right > root

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		Node root = new Node(Integer.parseInt(in.readLine()));
		
		while (true) {
			String s = in.readLine();
			if (s == null)
				break;
			root.insert(Integer.parseInt(s));
		}
		postOrder(root);
	}

	private static void postOrder(Node root) {
		if (root.left != null)
			postOrder(root.left);
		if (root.right != null)
			postOrder(root.right);
		System.out.println(root.root);
	}

	public static class Node {
		int root;
		Node left, right;

		public Node(int root) {
			this.root = root;
		}

		void insert(int root) {
			if (root < this.root) {
				if (this.left == null) {
					this.left = new Node(root);
				} else {
					this.left.insert(root);
				}
			} else {
				if (this.right == null) {
					this.right = new Node(root);
				} else {
					this.right.insert(root);
				}
			}
		}
	}

}
