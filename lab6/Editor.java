import javafx.application.Application;
import javafx.geometry.Orientation;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.Dialog;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.Scene;
import javafx.stage.Stage;

/** Main class of the Editor program where every appliation object is defined
  * @author Paweł Leśniak
  * @version 1.0
  */
public class Editor extends Application {
	/** The main JavaFX function */
	@Override
	public void start(Stage primaryStage) {

		// initatialisation

		final BorderPane root = new BorderPane();
		final Pane canvas = new Pane();
		final MenuBar menuBarMain = new MenuBar();
		final Menu helpMenu = new Menu("Help");
		final MenuItem infoMenuItem = new MenuItem("Info");
		final MenuItem helpMenuItem = new MenuItem("Help");
		final ToolBar shapesPane = new ToolBar();
		final Button buttonSelect = new Button("select");
		final Button buttonCircle = new Button("circle");
		final Button buttonRectangle = new Button("rectangle");
		final Button buttonTriangle = new Button("triangle");
		StateActionWrap stateWrap = new StateActionWrap();

		// dialog windows

		final Dialog<String> infoDialogWindow = new Dialog<String>();
		final Dialog<String> helpDialogWindow = new Dialog<String>();
		final ButtonType dialogButton = new ButtonType("Ok", ButtonData.OK_DONE);
		infoDialogWindow.getDialogPane().getButtonTypes().add(dialogButton);
		helpDialogWindow.getDialogPane().getButtonTypes().add(dialogButton);

		// object setting
		
		shapesPane.setOrientation(Orientation.VERTICAL);
		buttonSelect.setPrefWidth(100.0);
		buttonCircle.setPrefWidth(100.0);
		buttonRectangle.setPrefWidth(100.0);
		buttonTriangle.setPrefWidth(100.0);

		// adding objects

		helpMenu.getItems().addAll(infoMenuItem, helpMenuItem);
		menuBarMain.getMenus().add(helpMenu);
		shapesPane.getItems().addAll(buttonSelect, buttonCircle, buttonRectangle, buttonTriangle);
		root.setCenter(canvas);
		root.setTop(menuBarMain);
		root.setLeft(shapesPane);

		// listeners & handlers

		infoMenuItem.setOnAction(new MenuInfoEventHandler(infoDialogWindow));
		helpMenuItem.setOnAction(new MenuHelpEventHandler(helpDialogWindow));
		ButtonEventHandler buttonEH = new ButtonEventHandler(stateWrap, canvas);
		buttonSelect.setOnAction(buttonEH);
		buttonCircle.setOnAction(buttonEH);
		buttonRectangle.setOnAction(buttonEH);
		buttonTriangle.setOnAction(buttonEH);
		canvas.setOnMouseClicked(new DrawEventHandler(stateWrap, canvas));

		// setting up the stage

		primaryStage.setTitle("Graphic Editor");
		primaryStage.setScene(new Scene(root, 800, 640));
		primaryStage.show();
	}
	/** The main function that starts the program
	 * @param args entry arguments
	 */
	public static void main(String[] args) {
		launch(args);
	}
}
