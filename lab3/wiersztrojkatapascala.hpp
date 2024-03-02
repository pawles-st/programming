#ifndef WIERSZTROJKATAPASCALA_H
#define WIERSZTROJKATAPASCALA_H

#include <stdexcept>
#include <string>

class NegativeValueException : public std::runtime_error {
	public:
		NegativeValueException(const std::string& message);
};

class ExceededBoundsException : public std::runtime_error {
	public:
		ExceededBoundsException(const std::string& message);
};

class WierszTrojkataPascala {
	private:
		int* row;
		int row_size;
	public:
		WierszTrojkataPascala(int n);
		~WierszTrojkataPascala();
		int wspolczynnik(int m);
};

#endif
