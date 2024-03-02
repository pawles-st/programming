import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Dialog;

/** The class that deals with the "help" dialog window
 * @author Paweł Leśniak
 * @version 1.0
 */
class MenuHelpEventHandler implements EventHandler<ActionEvent> {
	/** Variable that holds the dialog window */
	private Dialog<String> dialogWindow;
	/** Main constructor
	 * @param dialogWindow the dialog window to handle
	 */
	public MenuHelpEventHandler(Dialog<String> dialogWindow) {
		this.dialogWindow = dialogWindow;
	}
	/** Method that deals with showing the window */
	@Override
	public void handle(ActionEvent ae) {
		dialogWindow.setTitle("Help");
		dialogWindow.setHeaderText("Instructions for usage below:");
		dialogWindow.setContentText("- Choose a shape from the panel on the left or \"select\" to select already drawn shapes\n- Press twice to draw a Circle/Rectangle, thrice to draw a Triangle\n- You can change the fill colour of a shape or rotate it by right-clicking on it\n- Shapes can be moved by dragging them around");
		dialogWindow.showAndWait();
	}
}
