import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;

/** Class that holds the JavaFX properties of a single button tile
 * @author Paweł Leśniak
 * @version 1.0
 */
class TileButton extends Button {
	/** Variable that stores the current hue of the button */
	private double hue;
	/** Variable that holds whether the current tile is active */
	private boolean active = true;
	/** The main constructor */
	public TileButton() {
		setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent ae) {
				active = (active ? false : true);
			}
		});
		setMaxWidth(Double.POSITIVE_INFINITY);
		setMaxHeight(Double.POSITIVE_INFINITY);
		setMinWidth(Double.MIN_VALUE);
		setMinHeight(Double.MIN_VALUE);
	}
	/** Method for changing the background hue of the button
	 * @param hue the hue to set the background to
	 */
	public void changeBackground(double hue) {
		Platform.runLater(() -> {
			setBackground(new Background(new BackgroundFill(Color.hsb(hue, 0.6, 1.0), CornerRadii.EMPTY, Insets.EMPTY)));
		});
	}
	/** Method for setting the value of the hue variable
	 * @param hue the value of the hue
	 */
	public void setHue(double hue) {
		this.hue = hue;
	}
	/** Method for getting the value of the hue variable
	 * @return double the value of the hue
	 */
	public double getHue() {
		return hue;
	}
	/** Method for getting the current state of the tile
	 * @return boolean whether the current tile is active or not
	 */
	 public boolean getState() {
		return active;
	 }
}
