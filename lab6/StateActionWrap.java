import java.util.Arrays;
import javafx.geometry.Point2D;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

/** The wrapper class for the StateAction class
 * @author Paweł Leśniak
 * @version 1.0
 * @see StateAction
 */
class StateActionWrap {
	/** The StateAction object */
	public StateAction state = new SelectShape();
	/** A boolean to check whether the state is "select" */
	public static boolean select;
}

/** The class that serves as a parent for other classes describing the behaviour depending on the state
 * @author Paweł Leśniak
 * @version 1.0
 */
abstract class StateAction {
	/** Variable that stores points for drawing shapes */
	protected Point2D[] points;
	/** Variable that store how many points have been chosen */
	protected int pointNumber = 0;
	/** Method that deals with mouse pressing on the canvas
	 * @param me Mouse event that caused the action
	 * @param canvas The canvas to draw on
	 */
	abstract public void action(MouseEvent me, Pane canvas);
}

/** The class that deals with drawing circles
 * @author Paweł Leśniak
 * @version 1.0
 */
class DrawCircle extends StateAction {
	/** Main constructor */
	public DrawCircle() {
		points = new Point2D[2];
		pointNumber = 0;
	}
	/** Method that deals with mouse pressing on the canvas
	 * @param me Mouse event that caused the action
	 * @param canvas The canvas to draw on
	 */
	public void action(MouseEvent me, Pane canvas) {
		points[pointNumber] = new Point2D(me.getX(), me.getY());
		++pointNumber;
		if (pointNumber == 2) {
			DynamicCircle dc = new DynamicCircle(points[0], points[1]);
			canvas.getChildren().add(dc);
			Arrays.fill(points, null);
			pointNumber = 0;
		}
	}
}

/** The class that deals with drawing rectangles
 * @author Paweł Leśniak
 * @version 1.0
 */
class DrawRectangle extends StateAction {
	/** Main constructor */
	public DrawRectangle() {
		points = new Point2D[2];
		pointNumber = 0;
	}
	/** Method that deals with mouse pressing on the canvas
	 * @param me Mouse event that caused the action
	 * @param canvas The canvas to draw on
	 */
	public void action(MouseEvent me, Pane canvas) {
		points[pointNumber] = new Point2D(me.getX(), me.getY());
		++pointNumber;
		if (pointNumber == 2) {
			DynamicRectangle dr = new DynamicRectangle(points[0], points[1]);
			canvas.getChildren().add(dr);
			Arrays.fill(points, null);
			pointNumber = 0;
		}
	}
}

/** The class that deals with drawing triangles
 * @author Paweł Leśniak
 * @version 1.0
 */
class DrawTriangle extends StateAction {
	/** Main constructor */
	public DrawTriangle() {
		points = new Point2D[3];
		pointNumber = 0;
	}
	/** Method that deals with mouse pressing on the canvas
	 * @param me Mouse event that caused the action
	 * @param canvas The canvas to draw on
	 */
	public void action(MouseEvent me, Pane canvas) {
		points[pointNumber] = new Point2D(me.getX(), me.getY());
		++pointNumber;
		if (pointNumber == 3) {
			DynamicTriangle dt = new DynamicTriangle(points[0], points[1], points[2]);
			canvas.getChildren().add(dt);
			Arrays.fill(points, null);
			pointNumber = 0;
		}
	}
}

/** THe class that serves as a placeholder while the "select" state is active
 * @author Paweł Leśniak
 * @version 1.0
 */
class SelectShape extends StateAction {
	/** Empty method dealing with mouse pressing */
	public void action(MouseEvent me, Pane canvas) {}
}
