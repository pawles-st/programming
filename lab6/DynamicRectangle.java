import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextInputDialog;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Scale;

/** The class for the rectangle draw
 * @author Paweł Leśniak
 * @version 1.0
 */
class DynamicRectangle extends Rectangle {
	/** Variable that stores whether the shape is currently selected */
	private boolean isSelected = false;
	/** Main constructor
	 * @param p1 Starting point
	 * @param p2 Point determining the diagonal line
	 */
	DynamicRectangle(Point2D p1, Point2D p2) {
		super(Math.min(p1.getX(), p2.getX()), Math.min(p1.getY(), p2.getY()), Math.abs(p1.getX() - p2.getX()), Math.abs(p1.getY() - p2.getY()));
		setOnMousePressed(new RectanglePressedEventHandler());
		setOnMouseDragged(new RectangleDraggedEventHandler());
		setOnScroll(new RectangleScrolledEventHandler());
		ContextMenu shapeMenu = new ContextMenu();
		ColorPicker shapeFill = new ColorPicker();
		MenuItem fillItem = new MenuItem(null, shapeFill);
		fillItem.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent ae) {
				setFill(shapeFill.getValue());
			}
		});
		setOnContextMenuRequested(new RectangleContextEventHandler(shapeMenu));
		MenuItem rotateItem = new MenuItem("Rotate");
		rotateItem.setOnAction(new RectangleRotateEventHandler());
		shapeMenu.getItems().addAll(fillItem, rotateItem);
	}
	/** A class that deals with pressing on the rectangle */
	class RectanglePressedEventHandler implements EventHandler<MouseEvent> {
		/** Method that deals with the pressing event
		 * @param me Event that caused the pressing event
		 */
		@Override
		public void handle(MouseEvent me) {
			if (StateActionWrap.select == false) {
				return;
			}
			Point2D pointHit = new Point2D(me.getX(), me.getY());
			if (isSelected) {
				isSelected = false;
				setStroke(Color.BLACK);
				setStrokeWidth(1.0);
			} else {
				isSelected = true;
				setStroke(Color.YELLOW);
				setStrokeWidth(2.5);
			}
		}
	}
	/** A class that deals with dragging the rectangle */
	class RectangleDraggedEventHandler implements EventHandler<MouseEvent> {
		/** Method that deals with the dragging event
		 * @param me Event that caused the dragging event
		 */
		@Override
		public void handle(MouseEvent me) {
			if (isSelected) {
				setX(me.getX());
				setY(me.getY());
			}
		}
	}
	/** A class that deals with scrolling on the rectangle */
	class RectangleScrolledEventHandler implements EventHandler<ScrollEvent> {
		/** Method that deals with the scrolling event
		 * @param se Event that caused the scrolling event
		 */
		@Override
		public void handle(ScrollEvent se) {
			Scale s = new Scale();
			if (se.getDeltaY() > 0) {
				s.setX(se.getDeltaY() * 0.03);
				s.setY(se.getDeltaY() * 0.03);
			} else if (se.getDeltaY() < 0) {
				s.setX(-1 / (se.getDeltaY() * 0.03));
				s.setY(-1 / (se.getDeltaY() * 0.03));
			}
			s.setPivotX(getX() + getWidth()/2);
			s.setPivotY(getY() + getHeight()/2);
			getTransforms().add(s);
		}
	}
	/** A class that deals with showing the context menu on the rectangle */
	class RectangleContextEventHandler implements EventHandler<ContextMenuEvent> {
		/** Variable that holds the context menu*/
		ContextMenu shapeMenu;
		/** Main constructor
		 * @param shapeMenu The context menu to show
		 */
		RectangleContextEventHandler(ContextMenu shapeMenu) {
			this.shapeMenu = shapeMenu;
		}
		/** Method that deals with the context menu event
		 * @param cme Event that caused the context menu popup
		 */
		@Override
		public void handle(ContextMenuEvent cme) {
			if (StateActionWrap.select == false) {
				return;
			}
			shapeMenu.show(DynamicRectangle.this, cme.getScreenX(), cme.getScreenY());
		}
	}
	/** A class that deals with rotating the rectangle */
	private class RectangleRotateEventHandler implements EventHandler<ActionEvent> {
		/** Method that deals with the rotation event
		 * @param ae Event that caused the rotation
		 */
		@Override
		public void handle(ActionEvent ae) {
			TextInputDialog rotateWindow = new TextInputDialog();
			rotateWindow.setTitle("Rotation");
			rotateWindow.setContentText("Rotate by: ");
			rotateWindow.showAndWait();
			try {
				double angle = Double.parseDouble(rotateWindow.getEditor().getText());
				Rotate r = new Rotate();
				r.setAngle(angle);
				r.setPivotX(getX() + getWidth()/2);
				r.setPivotY(getY() + getHeight()/2);
				getTransforms().add(r);
			} catch (IllegalArgumentException e) {}
		}
	}
}
