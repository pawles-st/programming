#include "wiersztrojkatapascala.hpp"
#include <string>

NegativeValueException::NegativeValueException(const std::string& message) : std::runtime_error(message) {}

ExceededBoundsException::ExceededBoundsException(const std::string& message) : std::runtime_error(message) {}

WierszTrojkataPascala::WierszTrojkataPascala(int n) {
	if (n < 0) {
		throw NegativeValueException("nieprawidlowy numer wiersza");
	}
	int* r = new int[n + 1];
	row_size = n + 1;
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

int WierszTrojkataPascala::wspolczynnik(int m) {
	if (m >= row_size) {
		throw ExceededBoundsException("liczba spoza zakresu");
	}
	if (m < 0) {
		throw NegativeValueException("liczba spoza zakresu");
	}
	return row[m];
}

WierszTrojkataPascala::~WierszTrojkataPascala() {
	delete [] row;
}
