import WierszTrojkataPascala.*;

public class Test {
	public static void main(String[] args) {
		if (args.length == 0) {
			return;
		}
		WierszTrojkataPascala values;
		try {
			values = new WierszTrojkataPascala(Integer.parseInt(args[0]));
		} catch (NegativeValueException e) {
			System.out.println(args[0] + " - nieprawdilowy zakres");
			return;
		} catch (NumberFormatException e) {
			System.out.println(args[0] + " - nieprawidlowy zakres");
			return;
		}
		for (int i = 1; i < args.length; ++i) {
			try {
				System.out.println(values.wspolczynnik(Integer.parseInt(args[i])));
			} catch (NegativeValueException e) {
				System.out.println(args[i] + " - " + e.getMessage());
				continue;
			} catch (ExceededBoundsException e) {
				System.out.println(args[i] + " - " + e.getMessage());
				continue;
			} catch (NumberFormatException e) {
				System.out.println(args[i] + " - nieprawidlowa dana");
				continue;
			} 
		}
	}
}
