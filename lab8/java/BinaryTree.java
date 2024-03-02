import java.io.PrintWriter;
import java.io.Serializable;
import java.lang.Comparable;
import java.util.LinkedList;

/** The binary tree class
 * @version 1.0
 * @author Paweł Leśniak
 */
class BinaryTree<T extends Comparable<T>> implements java.io.Serializable {
	
	/** Root of the tree */
	private Node<T> root;

	/** Type of the tree */
	private String type;

	/** Recursive method for drawing the tree on the terminal InOrder
	 * @param node currently drawn node
	 */
	private void draw(Node<T> node) {
		if (node == null) {
			return;
		}
		draw(node.getLeft());
		System.out.print(node.getKey() + " ");
		draw(node.getRight());
	}

	/** Recursive method for checking if an element exists in the tree
	 * @param value value to search for
	 * @param node currently checked node
	 * @return the first node whose value equals the searched one if such exists, null otherwise
	 */
	private Node<T> search(T value, Node<T> node) {
		if (node == null || node.getKey().equals(value)) {
			return node;
		} else {
			if (value.compareTo(node.getKey()) < 0) {
				return search(value, node.getLeft());
			} else {
				return search(value, node.getRight());
			}
		}
	}

	/** Method for finding the minimum value of a subtree
	 * @param node root of the subtree
	 * @return node with the minimum value
	 */
	private Node<T> minimum(Node<T> node) {
		if (node.getLeft() == null) {
			return node;
		} else {
			return minimum(node.getLeft());
		}
	}

	/** Recursive method for deleting a given value
	 * @param node current node being checked for fitting the delete criterion
	 * @param key the value to delete
	 * @return root of a subtree after deletion
	 */
	private Node<T> delete(Node<T> node, T key) {
		if (node == null) {
			return node;
		} else if (key.compareTo(node.getKey()) < 0) {
			node.setLeft(delete(node.getLeft(), key));
		} else if (key.compareTo(node.getKey()) > 0) {
			node.setRight(delete(node.getRight(), key));
		} else {
			if (node.getLeft() == null) {
				return node.getRight();
			} else if (node.getRight() == null) {
				return node.getLeft();
			} else {
				node.setKey(minimum(node.getRight()).getKey());
				node.setRight(delete(node.getRight(), node.getKey()));
			}
		}
		return node;
	}

	/** Getter for the root
	 * @return root of the tree
	 */
	public Node<T> getRoot() {
		return root;
	}

	/** Main constructor
	 * @param type type of the tree
	 */
	public BinaryTree(String type) {
		root = null;
		this.type = type;
	}

	/** Getter for the tree type
	 * @return type of the tree
	 */
	public String getType() {
		return type;
	}

	/** Method for searching the tree for a given value
	 * @param value value to search for
	 * @return node which holds the value searched for, null otherwise
	 */
	public Node<T> search(T value) {
		return search(value, root);
	}

	/** Method for inserting a value into the tree
	 * @param value value to insert
	 */
	public void insert(T value) {
		if (root == null) {
			root = new Node<T>(value);
		} else {
			Node<T> currentNode = null;
			Node<T> nextNode = root;
			while (nextNode != null) {
				currentNode = nextNode;
				if (nextNode.getKey().compareTo(value) <= 0) {
					nextNode = currentNode.getRight();
				} else {
					nextNode = currentNode.getLeft();
				}
			}
			if (currentNode.getKey().compareTo(value) <= 0) {
				currentNode.setRight(new Node<T>(value));
			} else {
				currentNode.setLeft(new Node<T>(value));
			}
		}
	}

	/** Method for deleting a given value from the tree
	 * @param key the value to delete
	 */
	public void delete(T key) {
		root = delete(root, key);
	}

	/** Method for drawing the tree in terminal InOrder */
	public void draw() {
		draw(root);
		System.out.println();
	}
}
