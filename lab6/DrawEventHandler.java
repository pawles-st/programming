import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

/** The class that deals with pressing the mouse on the canvas
 * @author Paweł Leśniak
 * @version 1.0
 */
class DrawEventHandler implements EventHandler<MouseEvent> {
	/** Variable that holds the canvas to draw on */
	private Pane canvas;
	/** Variable that holds the current state */
	private StateActionWrap stateWrap;
	/** Main constructor
	 * @param stateWrap current state
	 * @param canvas the canvas to draw on
	 */
	DrawEventHandler(StateActionWrap stateWrap, Pane canvas) {
		this.stateWrap = stateWrap;
		this.canvas = canvas;
	}
	/** Method that deals with pressing the mouse */
	@Override
	public void handle(MouseEvent me)  {
		stateWrap.state.action(me, canvas);
	}
}
