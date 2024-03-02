import java.util.ArrayList;
import java.util.List;
import javafx.application.Application;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.Scene;
import javafx.stage.Stage;

/** main class of the Board application
 * @author Paweł Leśniak
 * @version 1.0
 */
public class Board extends Application {
	@Override
	/** main JavaFX method to start the application */
	public void start(Stage primaryStage) {

		// arguments

		final Application.Parameters param = this.getParameters();
		final List<String> args = param.getUnnamed();
		if (args.size() < 4) {
			System.out.println("error: not enough arguments!");
			System.exit(0);
		}
		int n = 0;
		int m = 0;
		double k = 0.0;
		double p = 0.0;
		try {
			n = Integer.parseInt(args.get(0));
			m = Integer.parseInt(args.get(1));
			k = Double.parseDouble(args.get(2));
			p = Double.parseDouble(args.get(3));
			if (n <= 0 || m <= 0 || k <= 0 || p < 0 || p > 1) {
				throw new Exception();
			}
		} catch (Exception e) {
			System.out.println("error: invalid arguments!");
			System.exit(0);
		}

		// root pane preparation

		final GridPane root = new GridPane();
		final ArrayList<ColumnConstraints> columnList = new ArrayList<ColumnConstraints>();
		for (int i = 0; i < n; ++i) {
			ColumnConstraints c = new ColumnConstraints();
			c.setPercentWidth(100.0/n);
			c.setFillWidth(true);
			c.setHgrow(Priority.ALWAYS);
			columnList.add(c);
		}
		root.getColumnConstraints().addAll(columnList);
		final ArrayList<RowConstraints> rowList = new ArrayList<RowConstraints>();
		for (int i = 0; i < m; ++i) {
			RowConstraints c = new RowConstraints();
			c.setPercentHeight(100.0/m);
			c.setFillHeight(true);
			c.setVgrow(Priority.ALWAYS);
			rowList.add(c);
		}
		root.getRowConstraints().addAll(rowList);

		// RNG

		RNG.initialise(k);

		// gui objects

		final ArrayList<TileButton> tileList = new ArrayList<TileButton>();
		final ArrayList<TileThread> threadList = new ArrayList<TileThread>();
		TileThread.initValues(p, tileList, n, m);
		for (int i = 0; i < n; ++i) {
			for (int j = 0; j < m; ++j) {
				final TileButton b = new TileButton();
				final double hue = RNG.randomHue();
				b.changeBackground(hue);
				b.setHue(hue);
				tileList.add(b);
				TileThread t = new TileThread(b, i, j);
				t.setDaemon(true);
				root.add(b, i, j);
				threadList.add(t);
			}
		}

		// starting the threads

		for (int i = 0; i < n*m; ++i) {
			threadList.get(i).start();
		}

		// setting the stage

		primaryStage.setTitle("Board");
		primaryStage.setScene(new Scene(root, 800, 640));
		primaryStage.show();
	}
	/** The main method
	 * @param args an array of arguments - width (positive integer), height (positive integer), speed (number of milliseconds), probability (between 0.0 and 1.0)
	 */
	public static void main(String[] args) {
		launch(args);
	}
}
