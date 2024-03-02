#include "LiczbyPierwsze.hpp"
#include <algorithm>
#include <stdexcept>
#include <iostream>

LiczbyPierwsze::LiczbyPierwsze(int n) {
	bool p_test[n + 1];
	std::fill_n(p_test, n + 1, true);
	int p_no = 0;
	for (int i = 2; i < n + 1; ++i) {
		if (p_test[i] == true) {
			++p_no;
			for (int j = 2 * i; j < n + 2; j += i) {
				p_test[j] = false;
			}
		}
	}
	p_size = p_no;
	int* p = new int[p_no];
	for (int i = 2, j = 0; i < n + 1; ++i) {
		if (p_test[i] == true) {
			p[j] = i;
			++j;
		}
	}
	primes = p;
}

LiczbyPierwsze::~LiczbyPierwsze() {
	delete [] primes;
}

int LiczbyPierwsze::liczba(int m) {
	if (m < 0 || m >= p_size) {
		throw std::range_error("");
	}
	return primes[m];
}
