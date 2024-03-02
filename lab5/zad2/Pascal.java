import java.io.*;
import java.awt.*;
import java.awt.event.*;

public class Pascal {
	public static void main(String[] args) {
		Frame window = new Frame("Pascal");
		Button exec = new Button("execute");
		TextField input = new TextField();
		TextArea output = new TextArea();
		output.setEditable(false);



		window.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		exec.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					output.setText("");
					Process p = Runtime.getRuntime().exec("./test " + input.getText());
					BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
					String line;
					while ((line = r.readLine()) != null) {
						output.append(line + "\n");
					}
					input.setText("");
				} catch (Exception ex) {
					output.setText("Error");
				}
			}
		});



		window.setLayout(new BorderLayout());
		window.setBounds(100, 100, 800, 640);
		window.add(exec, BorderLayout.SOUTH);
		window.add(output, BorderLayout.CENTER);
		window.add(input, BorderLayout.NORTH);
		window.setVisible(true);
	}
}

