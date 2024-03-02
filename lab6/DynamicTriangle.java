import javafx.collections.ObservableList;
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
import javafx.scene.shape.Polygon;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Scale;

/** The class for the triangle drawn
 * @author Paweł Leśniak
 * @version 1.0
 */
class DynamicTriangle extends Polygon {
	/** Variable that stores the last X position pressed on */
	private double pressX;
	/** Variable that stores the last Y position pressed on */
	private double pressY;
	/** Variable that stores whether the shape is currently selected */
	private boolean isSelected = false;
	/** Moin constructor
	 * @param p1 First point
	 * @param p2 Second point
	 * @param p3 Third point
	 */
	DynamicTriangle(Point2D p1, Point2D p2, Point2D p3) {
		super(p1.getX(), p1.getY(), p2.getX(), p2.getY(), p3.getX(), p3.getY());
		setOnMousePressed(new TrianglePressedEventHandler());
		setOnMouseDragged(new TriangleDraggedEventHandler());
		setOnScroll(new TriangleScrolledEventHandler());
		ContextMenu shapeMenu = new ContextMenu();
		ColorPicker shapeFill = new ColorPicker();
		MenuItem fillItem = new MenuItem(null, shapeFill);
		fillItem.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent ae) {
				setFill(shapeFill.getValue());
			}
		});
		setOnContextMenuRequested(new TriangleContextEventHandler(shapeMenu));
		MenuItem rotateItem = new MenuItem("Rotate");
		rotateItem.setOnAction(new TriangleRotateEventHandler());
		shapeMenu.getItems().addAll(fillItem, rotateItem);
	}
	/** A class that deals with pressing on the triangle */
	class TrianglePressedEventHandler implements EventHandler<MouseEvent> {
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
				pressX = me.getX() + 100;
				pressY = me.getY() + 20;
				isSelected = true;
				setStroke(Color.YELLOW);
				setStrokeWidth(2.5);
			}
		}
	}
	/** A class that deals with draggin the triangle */
	class TriangleDraggedEventHandler implements EventHandler<MouseEvent> {
		/** Method that deals with the dragging event
		 * @param me Event that caused the dragging event
		 */
		@Override
		public void handle(MouseEvent me) {
			if (isSelected) {
				setLayoutX(getLayoutX() + me.getSceneX() - pressX);
				setLayoutY(getLayoutY() + me.getSceneY() - pressY);
				pressX = me.getSceneX();
				pressY = me.getSceneY();
			}
		}
	}
	/** A class that deals with the scrolling on the triangle */
	class TriangleScrolledEventHandler implements EventHandler<ScrollEvent> {
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
			ObservableList<Double> points = getPoints();
			int i = 0;
			double sumX = 0;
			double sumY = 0;
			for (Double p : points) {
				if (i % 2 == 0) {
					sumX += p;
				} else {
					sumY += p;
				}
				++i;
			}
			s.setPivotX(sumX/3);
			s.setPivotY(sumY/3);
			getTransforms().add(s);
		}
	}
	/** A class that deals with showing the context menu on the triangle */
	class TriangleContextEventHandler implements EventHandler<ContextMenuEvent> {
		/** Variable that holds the context menu */
		ContextMenu shapeMenu;
		/** Main constructor
		 * @param shapeMenu The context menu to show
		 */
		TriangleContextEventHandler(ContextMenu shapeMenu) {
			this.shapeMenu = shapeMenu;
		}
		/** Method that deals with the conext menu event
		 * @param cme Event that caused the context menu popup
		 */
		@Override
		public void handle(ContextMenuEvent cme) {
			if (StateActionWrap.select == false) {
				return;
			}
			shapeMenu.show(DynamicTriangle.this, cme.getScreenX(), cme.getScreenY());
		}
	}
	/** A class that deals with rotating the triangle */
	private class TriangleRotateEventHandler implements EventHandler<ActionEvent> {
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
				ObservableList<Double> points = getPoints();
				int i = 0;
				double sumX = 0;
				double sumY = 0;
				for (Double p : points) {
					if (i % 2 == 0) {
						sumX += p;
					} else {
						sumY += p;
					}
					++i;
				}
				Rotate r = new Rotate();
				r.setAngle(angle);
				r.setPivotX(sumX/3);
				r.setPivotY(sumY/3);
				getTransforms().add(r);
			} catch (IllegalArgumentException e) {}
		}
	}
}
