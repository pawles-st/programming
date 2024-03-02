package WierszTrojkataPascala;

public class WierszTrojkataPascala {
	private int[] row;
	public WierszTrojkataPascala(int n) throws NegativeValueException {
		if (n < 0) {
			throw new NegativeValueException("nieprawidlowy numer wiersza");
		}
		int[] r = new int[n + 1];
		int k = 1;
		int value = 1;
		for (int i = 0; i <= n/2; ++i) {
			r[i] = value;
			r[n - i] = value;
			value *= (n - k + 1);
			value /= k;
			++k;
		}
		row = r;
	}
	public int wspolczynnik(int m) throws ExceededBoundsException, NegativeValueException {
		if (m >= row.length) {
			throw new ExceededBoundsException("liczba spoza zakresu");
		}
		if (m < 0) {
			throw new NegativeValueException("liczba spoza zakresu");
		}
		return row[m];
	}
}
