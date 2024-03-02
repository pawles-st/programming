import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Dialog;

/** The class that deals with the "info" dialog window
 * @author Paweł Leśniak
 * @version 1.0
 */
class MenuInfoEventHandler implements EventHandler<ActionEvent> {
	/** Variable that holds the dialog window */
	private Dialog<String> dialogWindow;
	/** Main constructor
	 * @param dialogWindow the dialog window to handle
	 */
	public MenuInfoEventHandler(Dialog<String> dialogWindow) {
		this.dialogWindow = dialogWindow;
	}
	/** Method that deals with showing the window */
	@Override
	public void handle(ActionEvent ae) {
		dialogWindow.setTitle("Info");
		dialogWindow.setHeaderText("\"Graphic Editor\" by Paweł Leśniak");
		dialogWindow.setContentText("A simple graphic editor made for drawing shapes");
		dialogWindow.showAndWait();
	}
}
