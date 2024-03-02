import java.net.*;
import java.io.*;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import javafx.scene.Scene;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/** Client class for the binary tree application
 * @version 1.0
 * @author Paweł Leśniak
 */
public class TreeClient extends Application {

	/** Wrapper class for tree type */
	private abstract class StringWrap {
		public static String type;
	}

	/** Recursive method for GUI tree drawing
	 * @param x1 starting x coordinate for the line
	 * @param y1 starting y coordinate for the line
	 * @param x ending x coordinate for the line
	 * @param y ending y coordinate for the line
	 * @param node currently drawn node
	 * @param bg Pane on which the objects are drawn
	 * @param depth depth of the recursion
	 */
	private void drawTree(int x1, int y1, int x, int y, Node node, Pane bg, int depth) {

		// objects drawn

		Line line = new Line(x1, y1 + 5, x, y);
		Circle circle = new Circle(x, y, 15, Paint.valueOf("orange"));
		Text text = new Text(x - 3, y + 3, String.valueOf(node.getKey()));

		// addition to the pane

		bg.getChildren().add(line);
		bg.getChildren().add(circle);
		bg.getChildren().add(text);

		// recursion

		if (node.getLeft() != null) {
			drawTree(x, y, x - 190/depth, y + 30, node.getLeft(), bg, depth * 2);
		}
		if (node.getRight() != null) {
			drawTree(x, y, x + 190/depth, y + 30, node.getRight(), bg, depth * 2);
		}
	}

	/** Method that handles the tree, GUI, and server interaction
	 * @param primaryStage main stage of javafx
	 */
	private void runTree(Stage primaryStage) {
		
		// tree creation

		BinaryTree tree = null;
		switch (StringWrap.type) {
			case "Integer":
				tree = new BinaryTree<Integer>("Integer");
				break;
			case "Double":
				tree = new BinaryTree<Double>("Double");
				break;
			case "String":
				tree = new BinaryTree<String>("String");
				break;
		}

		// setting up GUI

		GridPane root = new GridPane();
		root.setGridLinesVisible(true);
		RowConstraints row1 = new RowConstraints();
		row1.setPercentHeight(25);
		RowConstraints row2 = new RowConstraints();
		row2.setPercentHeight(75);
		root.getRowConstraints().addAll(row1, row2);
		Pane background = new Pane();
		final ChoiceBox<String> action = new ChoiceBox<String>();
		action.getItems().addAll("insert", "search", "delete", "draw");
		action.setValue("draw");
		final TextField argument = new TextField();
		final Button confirm = new Button("OK");
		final Label output = new Label();
		HBox options = new HBox();
		options.prefWidthProperty().bind(primaryStage.widthProperty());
		options.setAlignment(Pos.CENTER);
		options.setSpacing(25);
		options.getChildren().addAll(action, argument, confirm, output);
		root.add(options, 0, 0);
		root.add(background, 0, 1);
		primaryStage.setScene(new Scene(root, 800, 640));

		// wrapper class for client-server IO

		abstract class IO {
			static public ObjectOutputStream out = null;
			static public ObjectInputStream in = null;
		}

		// setting up the socket

		try {
			final Socket socket = new Socket("localhost", 4444);
			primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>(){
				@Override
				public void handle(WindowEvent we) {
					try {
						IO.out.writeObject("quit");
						socket.close();
					} catch (IOException ioe) {
						System.out.println("Socket can't be closed");
						ioe.printStackTrace();
					}
				}
			});
			IO.out = new ObjectOutputStream(socket.getOutputStream());
			IO.in = new ObjectInputStream(socket.getInputStream());
			IO.out.writeObject(tree.getType());
		} catch (UnknownHostException uhe) {
			System.out.println("Server not found!");
			System.exit(0);
		} catch (IOException ioe) {
			System.out.println("Can't connect to the server!");
			System.exit(0);
		}
		
		// exchanging information with the server

		confirm.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent ae) {
				try {
					
					// output to the server

					String text = action.getValue() + " " + argument.getText();
					//System.out.println(text);
					IO.out.writeObject(text);

					// reading from the server 

					text = (String) IO.in.readObject();
					BinaryTree tree = (BinaryTree) IO.in.readObject();
					output.setText(text);
					background.getChildren().clear();

					// drawing the received tree

					if (tree.getRoot() != null) {
						drawTree((int) (primaryStage.getWidth() / 2), 0, (int) (primaryStage.getWidth() / 2), 30, tree.getRoot(), background, 1);
					}

					// output reset

					IO.out.reset();
				} catch (IOException ioe) {
					ioe.printStackTrace();
				} catch (ClassNotFoundException cnfe) {
					cnfe.printStackTrace();
				}
			}
		});
	}
	/** Main JavaFX method */
	@Override
	public void start(Stage primaryStage) {

		// type choice window

		Label l = new Label("Choose the type of the tree and press 'OK'");
		ChoiceBox<String> cb = new ChoiceBox<String>();
		cb.getItems().addAll("Integer", "Double", "String");
		cb.setValue("Integer");

		// confirming the type choice

		Button b = new Button("OK");
		b.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent ae) {
				StringWrap.type = cb.getValue();
				runTree(primaryStage);
			}
		});

		// layout for the choice window

		VBox vb = new VBox();
		vb.setAlignment(Pos.CENTER);
		vb.getChildren().addAll(l, cb, b);
		vb.setSpacing(15);

		// setting the stage

		primaryStage.setScene(new Scene(vb, 400, 320));
		primaryStage.show();
	}
	/** The main method of the programme
	 * @param args entry arguments
	 */
	public static void main(String[] args) {
		launch(args);
	}
}
