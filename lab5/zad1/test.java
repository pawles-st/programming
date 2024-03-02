import javafx.application.Application;

public class test extends Application {
	public static void main(String[] args) {
		launch(args);
	}
	@Override
	public void start(Stage primaryStage) {
		primaryStage.setTitle("Test");
		primaryStage.setLayout(new GridLayout(2,1));
		Button b1 = new Button("in");
		button b2 = new Button("out");
		StackPane root = new StackPane();
		root.getChildren.add(b1);
		root.getChildren.add(b2);
		primaryStage.setScene(new Scene(root, 800, 600));
		primaryStage.show();
	}
}
