import java.io.*;
import java.lang.*;
import java.net.*;
import java.util.*;

/** Thread class for communication with the client
 * @version 1.0
 * @author Paweł Leśniak
 */
class MultiThread extends Thread {
	
	/** the client socket */
	private Socket socket;

	/** Main constructor
	 * @param socket client socket
	 */
	public MultiThread(Socket socket) {
		this.socket = socket;
	}
	/** Main Thread method where the interactions happen */
	@SuppressWarnings("unchecked") // warning generated due to casting BinaryTree<?> into specific generic types
	@Override
	public void run() {
		try {

			// the client's tree

			BinaryTree<?> tree = new BinaryTree<Integer>("Integer");

			// streams for communication with the client

			ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
			ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());

			// setting the tree's type

			String type = (String) in.readObject();
			switch (type) {
				case "String":
					tree = new BinaryTree<String>("String");
					break;
				case "Double":
					tree = new BinaryTree<Double>("Double");
					break;
				case "Integer":
					tree = new BinaryTree<Integer>("Integer");
					break;
			}

			// communicating with the client

			while (true) {

				// reading the client's input

				String line = (String) in.readObject();
				String[] command = line.split(" ");
				String message = "";
				//System.out.println(command);
				try {
					switch(command[0]) {
						
						// closing the socket

						case "quit":
							socket.close();
							return;

						// searching for an element

						case "search":
							if (tree.getType().equals("Integer")) {
								if (((BinaryTree<Integer>)tree).search(Integer.parseInt(command[1])) != null) {
									message = "found!";
								} else {
									message = "not found...";
								}
							}
							else if (tree.getType().equals("Double")) {
								if (((BinaryTree<Double>)tree).search(Double.parseDouble(command[1])) != null) {
									message = "found!";
								} else {
									message = "not found...";
								}
							}
							else {
								if (((BinaryTree<String>)tree).search(command[1]) != null) {
									message = "found!";
								} else {
									message = "not found...";
								}
							}
							break;

						// inserting an element

						case "insert":
							if (tree.getType().equals("Integer")) {
								((BinaryTree<Integer>)tree).insert(Integer.parseInt(command[1]));
								message = "inserted!";
							}
							else if (tree.getType().equals("Double")) {
								((BinaryTree<Double>)tree).insert(Double.parseDouble(command[1]));
								message = "inserted!";
							}
							else {
								((BinaryTree<String>)tree).insert(command[1]);
								message = "inserted!";
							}
							break;

						// deleting an element, if such exists

						case "delete":
							if (tree.getType().equals("Integer")) {
								((BinaryTree<Integer>)tree).delete(Integer.parseInt(command[1]));
								message = "deleted!";
							}
							else if (tree.getType().equals("Double")) {
								((BinaryTree<Double>)tree).delete(Double.parseDouble(command[1]));
								message = "deleted!";
							}
							else {
								((BinaryTree<String>)tree).delete(command[1]);
								message = "deleted";
							}
							break;

						// drawing the tree (doesn't do anything special, the tree is drawn anyway)

						case "draw":
							message = "drawing...";
							break;
					}
				} catch (IndexOutOfBoundsException ioobe) {
					message = "Error: invalid argument!";
				} catch (NumberFormatException nfe) {
					message = "Error: invalid argument!";
				}

				// outputting to the client

				out.writeObject(message);
				out.writeObject(tree);
				//tree.draw();

				// resetting the output

				out.reset();
			}
			//socket.close();
		} catch (IOException ioe) {
			System.out.println("I/O Exception!");
			ioe.printStackTrace();
		} catch (ClassNotFoundException cnfe) {
			System.out.println("Class not found!");
			cnfe.printStackTrace();
		}
	}
}

/** The main server class
 * @version 1.0
 * @author Paweł Leśniak
 */
class TreeServer {
	/** The Main method for the server
	 * @param args the entry arguments (unused)
	 */
	public static void main(String[] args) {
		
		// accepting the client and creating a thread

		try (ServerSocket serverSocket = new ServerSocket(4444)) {
			System.out.println("Server is listening on port 4444");
			while (true) {
				Socket socket = serverSocket.accept();
				System.out.println("new client connected");
				new MultiThread(socket).start();
			}
		} catch (IOException ioe) {
			System.out.println("I/O Exception!");
		}
	}
}
