#ifndef BINARY_TREE
#define BINARY_TREE

#include <iostream>

/** Struct whose objects serve as elements of the tree
 * @version 1.0
 * @author Paweł Leśniak
 */
template<typename T>
struct Node {
	
	/** Value of the node */
	T key;

	/** Pointer to the left child */
	Node<T>* left;

	/** Pointer to the right child */
	Node<T>* right;

	/** Pointer to the parent */
	Node<T>* parent;

	/** Main constructor
	 * @param item the value of the node
	 */
	Node(T item) : key(item), left(nullptr), right(nullptr), parent(nullptr) {}

	/** Destructor */
	~Node() = default;
};

/** Parent class for all binary tree types
 * @version 1.0
 * @author Paweł Leśniak
 */
class BinaryTreePtr {
	public:
		/** Destructor */
		~BinaryTreePtr() = default;

		/** Method for drawing the tree subclass */
		virtual void draw() = 0;
};

/** Binary tree class
 * @version 1.0
 * @author Paweł Leśniak
 */
template<typename T>
class BinaryTree : public BinaryTreePtr {
	public:
		/** Tree root pointer */
		Node<T>* root;

		/** Type of the tree */
		std::string type;

		/** Main constructor
		 * @param tree_type type of the tree
		 */
		BinaryTree(std::string tree_type) : root(nullptr), type(tree_type) {}

		/** Method for searching the tree for a given value
		 * @param value value to search for
		 * @return pointer to the node whose key is the given value if such exists, nullptr otherwise
		 */
		Node<T>* search(T value) {
			return search(value, root);
		}

		/** Method for inserting a given value into the tree
		 * @param value value to insert
		 */
		void insert(T value) {
			if (root == nullptr) {
				root = new Node<T>(value);
			} else {
				Node<T>* currentNode = nullptr;
				Node<T>* nextNode = root;
				while (nextNode != nullptr) {
					currentNode = nextNode;
					if (nextNode->key <= value) {
						nextNode = currentNode->right;
					} else {
						nextNode = currentNode->left;
					}
				}
				if (currentNode->key <= value) {
					currentNode->right = new Node<T>(value);
					currentNode->right->parent = currentNode;
				} else {
					currentNode->left = new Node<T>(value);
					currentNode->left->parent = currentNode;
				}
			}
		}

		/** Method for deleting a given value
		 * @param key the value to delete
		 */
		void delete_node(T key) {
			root = delete_node(root, key);
		}

		/** Method for drawing the tree in terminal */
		void draw() {
			std::cout << "tree (drawn InOrder, current root = ";
			if (root != nullptr) {
				std::cout << root->key;
			} else {
				std::cout << "null";
			}
			std::cout << "): ";
			draw(root);
			std::cout << "\n";
		}

		/** Destructor */
		virtual ~BinaryTree() = default;
	private:

		/** Recursive Method for drawing the tree
		 * @param node pointer to the currently drawn node
		 */
		void draw(Node<T>* node) {
			if (node == nullptr) {
				return;
			} else {
				draw(node->left);
				std::cout << node->key << " ";
				draw(node->right);
			}
		}

		/** Recurrsive method for searching for a value
		 * @param value value to search for
		 * @param node pointer to a currently compared node
		 * @return pointer for a node which contains the value if such exists, nullptr otherwise
		 */
		Node<T>* search(T value, Node<T>* node) {
			if (node == nullptr || node->key == value) {
				return node;
			} else {
				if (value < node->key) {
					return search(value, node->left);
				} else {
					return search(value, node->right);
				}
			}
		}

		/** Method for finding a minimum of a subtree
		 * @param node currently checked node
		 * @return pointer to the node with a minimum value
		 */
		Node<T>* minimum(Node<T>* node) {
			if (node->left == nullptr) {
				return node;
			} else {
				return minimum(node->left);
			}
		}

		/** Recursive method for deletion of a value
		 * @param node node which is currently checked for the deletion criterion
		 * @param key the value to delete
		 * @return root of the subtree after deletion
		 */
		Node<T>* delete_node(Node<T>* node, T key) {
			if (node == nullptr) {
				return node;
			} else if (key < node->key) {
				node->left = delete_node(node->left, key);
			} else if (key > node->key) {
				node->right = delete_node(node->right, key);
			} else {
				if (node->left == nullptr) {
					Node<T>* next = node->right;
					delete node;
					return next;
				} else if (node->right == nullptr) {
					Node<T>* next = node->left;
					delete node;
					return next;
				} else {
					node->key = minimum(node->right)->key;
					node->right = delete_node(node->right, node->key);
				}
			}
			return node;
		}
};

#endif
