import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

/** The class that deals with button events
 * @author Paweł Leśniak
 * @version 1.0
 */
class ButtonEventHandler implements EventHandler<ActionEvent> {
	/** Variable that holds the current state */
	private StateActionWrap stateWrap;
	/** Variable that holds the canvas to draw on */
	private Pane canvas;
	/** Main constructor
	 * @param stateWrap current state
	 * @param canvas the canvas to draw on
	 */
	public ButtonEventHandler(StateActionWrap stateWrap, Pane canvas) {
		this.stateWrap = stateWrap;
		this.canvas = canvas;
	}
	/** Method that deals with pressing the button
	 * @param ae action that caused the event
	 */
	@Override
	public void handle(ActionEvent ae) {
		Button aeSource = (Button) ae.getSource();
		if (aeSource.getText().equals("select")) {
			stateWrap.state = new SelectShape();
			StateActionWrap.select = true;
		}
		if (aeSource.getText().equals("circle")) {
			stateWrap.state = new DrawCircle();
			StateActionWrap.select = false;
		}
		if (aeSource.getText().equals("rectangle")) {
			stateWrap.state = new DrawRectangle();
			StateActionWrap.select = false;
		}
		if (aeSource.getText().equals("triangle")) {
			stateWrap.state = new DrawTriangle();
			StateActionWrap.select = false;
		}
	}
}
