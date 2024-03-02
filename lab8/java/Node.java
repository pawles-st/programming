import java.io.Serializable;
import java.lang.Comparable;

/** The node class whose instances are elements of a binary tree
 * @version 1.0
 * @author Paweł Leśniak
 */
class Node<T extends Comparable<T>> implements java.io.Serializable {
	/** The value of the node */
	private T key;

	/** Reference to the left child */
	private Node<T> left;

	/** Reference to the right child */
	private Node<T> right;

	/** Main constructor
	 * @param item the value of the node
	 */
	public Node (T item) {
		key = item;
		left = right = null;
	}

	/** Getter for the value of the node
	 * @return the value of the node
	 */
	public T getKey() {
		return key;
	}

	/** Setter for the vlaue of the node
	 * @param value the value of the node
	 */
	public void setKey(T value) {
		key = value;
	}

	/** Getter for the left child
	 * @return the left child
	 */
	public Node<T> getLeft() {
		return left;
	}

	/** Getter for the right child
	 * @return the right child
	 */
	public Node<T> getRight() {
		return right;
	}

	/** Setter for the left child
	 * @param node the left child
	 */
	public void setLeft(Node<T> node) {
		left = node;
	}

	/** Setter for the right child
	 * @param node the right child
	 */
	public void setRight(Node<T> node) {
		right = node;
	}
}
