import java.awt.*;
import java.awt.event.*;
import WierszTrojkataPascala.*;

public class TrojkatPascala {
	public static void main(String[] args) {

		/* frame */

		final Frame window = new Frame("Trojkat Pascala");
		window.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				//System.out.println("closed");
				System.exit(0);
			}
		});

		/* buttons */

		final int limit = 2;
		final TextField input = new TextField();
		final TextArea output = new TextArea();
		input.setFont(new Font("Verdana", Font.PLAIN, 30));
		output.setFont(new Font("Verdana", Font.PLAIN, 18));
		output.setEditable(false);

		/* events */

		input.addTextListener(new TextListener() {
			@Override
			public void textValueChanged(TextEvent event) {
				//System.out.println("changed");
				if (input.getText().length() > limit) {
					input.setText(input.getText().substring(0, input.getText().length() - 1));
				}
				try {
					int in = Integer.parseInt(input.getText());
					output.setText("");
					if (in >= 30) {
						throw new ExceededBoundsException("");
					}
					//output.setRows(in);
					for (int j = 0; j <= in; ++j) {
						WierszTrojkataPascala v = new WierszTrojkataPascala(j);
						for (int i = 0; i <= j; ++i) {
							output.append(v.wspolczynnik(i) + " ");
						}
						output.append("\n");
					}
				} catch (NegativeValueException e) {
					output.setText("ujemna wartosc!");
				}
				catch (NumberFormatException e) {
					output.setText("niepoprawna wartosc!");
				}
				catch (ExceededBoundsException e) {
					output.setText("zbyt duza liczba!");
				}
			}
		});

		/* window */

		window.setLayout(new BorderLayout());
		window.add(input, BorderLayout.NORTH);
		window.add(output, BorderLayout.CENTER);
		window.setSize(1920, 1080);
		window.setVisible(true);
	}	
}
