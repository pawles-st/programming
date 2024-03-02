#include <iostream>
#include <string>
#include "LiczbyPierwsze.hpp"

int main(int argc, char* argv[]) {
	if (argc == 1) {
		std::cout << "nieprawidlowy zakres\n";
	}
	int n = 0;
	try {
		n = std::stoi(argv[1]);
	} catch (const std::invalid_argument& e) {
		std::cout << argv[1] << " - nieprawidlowy zakres\n";
		return 0;
	} catch (const std::out_of_range& e) {
		std::cout << argv[1] << " - nieprawidlowy zakres\n";
		return 0;
	}
	if (n <= 0) {
		std::cout << argv[1] << " - nieprawidlowy zakres\n";
		return 0;
	}
	LiczbyPierwsze p(n);
	for (int i = 2; i < argc; ++i) {
		std::cout << argv[i] << " - ";
		try {
			int m = std::stoi(argv[i]);
			std::cout << p.liczba(m) << "\n";
		} catch (const std::invalid_argument& e) {
			std::cout << "nieprawidlowa dana\n";
		} catch (const std::out_of_range& e) {
			std::cout << "liczba spoza zakresu\n";
		} catch (const std::range_error& e) {
			std::cout << "liczba spoza zakresu\n";
		}
	}
	return 0;
}
