#include <iostream>
#include <string>
#include "wiersztrojkatapascala.hpp"

int main(int argc, char* argv[]) {
	if (argc == 1) {
		return 0;
	}
	WierszTrojkataPascala* values;
	try {
		values = new WierszTrojkataPascala(std::stoi(std::string(argv[1])));
	} catch (const NegativeValueException& e) {
		std::cout << argv[1] << " - nieprawidlowy zakres\n";
		return 0;
	} catch (const std::invalid_argument& e) {
		std::cout << argv[1] << " - nieprawdilowy zakres\n";
		return 0;
	}
	for (int i = 2; i < argc; ++i) {
		try {
			std::cout << values->wspolczynnik(std::stoi(std::string(argv[i]))) << "\n";
		} catch (const NegativeValueException& e) {
			std::cout << argv[i] << " - " << e.what() << "\n";
			continue;
		} catch (const ExceededBoundsException& e) {
			std::cout << argv[i] << " - " << e.what() << "\n";
			continue;
		} catch (const std::invalid_argument& e) {
			std::cout << argv[i] << " - nieprawidlowa dana" << "\n";
			continue;
		}
	}
	free(values);
}
