package LiczbyPierwsze;

import java.util.Arrays;

public class LiczbyPierwsze {
	private int[] primes;
	public LiczbyPierwsze(int n) {
		boolean[] p_test = new boolean[n + 1];
		Arrays.fill(p_test, true);
		int p_no = 0;
		for (int i = 2; i < n + 1; ++i) {
			if (p_test[i] == true) {
				++p_no;
				for (int j = 2 * i; j < n + 1; j += i) {
					p_test[j] = false;
				}
			}
		}
		int[] p = new int [p_no];
		for (int i = 2, j = 0; i < n + 1; ++i) {
			if (p_test[i] == true) {
				p[j] = i;
				++j;
			}
		}
		primes = p;
	}
	public int liczba(int m) throws IndexOutOfBoundsException {
		if (m < 0 || m > primes.length) {
			throw new IndexOutOfBoundsException();
		}
		return primes[m];
	}
}
