#include "binary_tree.hpp"
#include <iostream>
#include <sstream>
#include <string>

/** Main function of the programme */
int main() {
	
	// initialising the tree

	std::string type;
	std::cout << "tree type (Integer, Double, or String): ";
	std::cin >> type;

	BinaryTreePtr* tree;
	if (type == "Integer") {
		tree = new BinaryTree<int>(type);
	} else if (type == "Double") {
		tree = new BinaryTree<double>(type);
	} else if (type == "String") {
		tree = new BinaryTree<std::string>(type);
	} else {
		std::cout << "invalid tree type!\n";
		return 0;
	}

	// reading commands from the user

	std::string line;
	std::cin.ignore(1, '\n');
	do {
		
		// reading input

		std::cout << "enter a command (insert [value], search [value], delete [value], draw, quit): ";
		std::getline(std::cin, line);
		std::stringstream ss(line);
		std::string word;
		ss >> word;

		// processing the command

		try {
			if (word == "insert") {
				ss >> word;
				if (type == "Integer") {
					(static_cast<BinaryTree<int>*>(tree))->insert(std::stoi(word));
				} else if (type == "Double") {
					(static_cast<BinaryTree<double>*>(tree))->insert(std::stod(word));
				} else {
					(static_cast<BinaryTree<std::string>*>(tree))->insert(word);
				}
				tree->draw();
			} else if (word == "search") {
				ss >> word;
				if (type == "Integer") {
					if ((static_cast<BinaryTree<int>*>(tree))->search(std::stoi(word)) != nullptr) {
						std::cout << "found!\n";
					} else {
						std::cout << "not found...\n";
					}
				} else if (type == "Double") {
					if ((static_cast<BinaryTree<double>*>(tree))->search(std::stod(word)) != nullptr) {
						std::cout << "found!\n";
					} else {
						std::cout << "not found...\n";
					}
				} else {
					if ((static_cast<BinaryTree<std::string>*>(tree))->search(word) != nullptr) {
						std::cout << "found!\n";
					} else {
						std::cout << "not found...\n";
					}
				}
			} else if (word == "delete") {
				ss >> word;
				if (type == "Integer") {
					(static_cast<BinaryTree<int>*>(tree))->delete_node(std::stoi(word));
				} else if (type == "Double") {
					(static_cast<BinaryTree<double>*>(tree))->delete_node(std::stod(word));
				} else {
					(static_cast<BinaryTree<std::string>*>(tree))->delete_node(word);
				}
				tree->draw();
			} else if (word == "draw") {
				tree->draw();
			}
		} catch (const std::invalid_argument& e) {
			std::cout << "invalid argument!\n";
		}
	} while (line != "quit");
}
