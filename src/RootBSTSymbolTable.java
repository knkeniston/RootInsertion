import java.util.Stack;

public class RootBSTSymbolTable<K extends Comparable<K>, V> implements SymbolTable<K, V> {
	
	private class Node {
		K key; 
		V val;
		Node left, right;
		Node (K k, V v) {
			key = k; val = v;
			left = right = null;
		}
	}
	
	private Node root;
	
	public RootBSTSymbolTable() {
		root = null;
	}
	
	private Node rotateRight(Node n) {
		assert n != null;
		assert n.left != null;
		Node tree = n.left;
		n.left = tree.right;
		tree.right = n;
		return tree;
	}
	
	private Node rotateLeft(Node n) {
		assert n != null;
		assert n.right != null;
		Node tree = n.right;
		n.right = tree.left;
		tree.left = n;
		return tree;
	}	
	
	private void rotateToRoot(Stack<Node> path) {
		assert !path.isEmpty();
		Node n = path.pop();
		while (!path.isEmpty()) {
			Node p = path.pop();
			Node r ;
			if (p.left == n) {
				r = rotateRight(p);
			} else {
				r = rotateLeft(p);
			}
			if (path.isEmpty()) {
				root = r;
				break;
			}
			Node g = path.peek();
			if (g.left == n) {
				g.left = r;
			} else {
				g.right = r;
			}
			n = r;
		}
	}
	
	public void insert(K key, V val) {
		if (root == null) {
			root = new Node(key, val);
			return;
		}
		Stack<Node> path = new Stack<>();
		Node n = root;
		while (true) {
			assert n != null;
			path.push(n);
			int cmp = key.compareTo(n.key);
			if (cmp == 0) {
				n.key = key;
				n.val = val;
				break;
			} else if (cmp < 0) {
				if (n.left == null) {
					n.left = new Node(key, val);
					path.push(n.left);
					break;
				}
				n = n.left;
			} else {
				if (n.right == null) {
					n.right = new Node(key, val);
					path.push(n.right);
					break;
				}
				n = n.right;
			}
		}
		rotateToRoot(path);
		// XXX rotate inserted leaf to root
	}
	
	public V search(K key) {
		return null;
	}
	
	public V remove(K key) {
		return null;
	}
	
	public static void main(String[] args) {
		
	}
}