import java.util.Arrays;
import LiczbyPierwsze.LiczbyPierwsze;

public class Test {
	public static void main(String[] args) {
		if (args.length == 0) {
			System.out.println("nieprawidlowy zakres");
		}
		int n = 0;
		try {
			n = Integer.parseInt(args[0]);
		} catch (NumberFormatException e) {
			System.out.println(args[0] + " - nieprawidlowy zakres");
			return;
		}
		if (n <= 0) {
			System.out.println(args[0] + " - nieprawidlowy zakres");
			return;
		}
		LiczbyPierwsze p = new LiczbyPierwsze(n);
		for (int i = 1; i < args.length; ++i) {
			System.out.print(args[i] + " - ");
			try {
				int m = Integer.parseInt(args[i]);
				System.out.println(p.liczba(m));
			} catch (NumberFormatException e) {
				System.out.println("nieprawidlowa dana");
			} catch (IndexOutOfBoundsException e) {
				System.out.println("liczba spoza zakresu");
			}
		}
	}
}
