public class Divisor {
	public static void main(String[] args) {
		for (int i = 0; i < args.length; ++i) {
			try {
				int n = Integer.parseInt(args[i]);
				int d = div(n);
				System.out.println(n + " " + d);
			} catch (NumberFormatException ex) {
				System.out.println(args[i] + " nie jest liczba calkowita");
			}
		}
	}
	public static int div(int n) {
		int d = 1;
		for (int i = 2; i < n; ++i) {
			if (n % i == 0) {
				d = i;
			}
		}
		return d;
	}
}
