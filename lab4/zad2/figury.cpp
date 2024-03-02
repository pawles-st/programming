#include "figura.hpp"
#include <iostream>
#include <algorithm>
//vector
#include <cstdlib>
#include <cstring>

int main(int argc, char* argv[]) {
	int l = strlen(argv[1]);
	//bool is_f[l];
	//std::fill(is_f, is_f + l, true);
	//Figura f[l];
	int arg = 2;
	for (int i = 0; i < l; ++i) {
		if (arg == argc) {
			std::cout << "brakuje argumentow\n";
			return 0;
		}
		char c = argv[1][i];
		switch (c) {
			case 'o':
			{
				char* c;
				float a = std::strtof(argv[arg], &c);
				if (a <= 0) {
					std::cout << "o - " << argv[arg] << " - dana figura nie istnieje\n";
					//is_f[i] = false;
					++arg;
					break;
				}
				Kolo k(a);
				//f[i] = k;
				std::cout << "Pole = " << k.calcArea() << ", Obwod = " << k.calcCircumference() << "\n";
				++arg;
				break;
			}
			case 'c':
			{
				if (arg + 4 >= argc) {
					std::cout << "brakuje argumentow\n";
					return 0;
				}
				float a[5];
				for (int j = 0; j < 5; ++j) {
					char* c;
					a[j] = std::strtof(argv[arg + j], &c);
				}
				if (a[0] <= 0 || a[1] <= 0 || a[2] <= 0 || a[3] <= 0 || a[4] <= 0 || a[4] > 90) {
					std::cout << "c - " << argv[arg] << " " << argv[arg + 1] << " " << argv[arg + 2] << " " << argv[arg + 3] << " " << argv[arg + 4] << " - dana figura nie istnieje\n";
					//is_f[i] = false;
					arg += 5;
					break;
				}
				if (a[4] == 90 && a[0] == a[1] && a[1] == a[2] && a[2] == a[3]) {
					Kwadrat k(a[0], a[1], a[2], a[3], a[4]);
					std::cout << "Pole = " << k.calcArea() << ", Obwod = " << k.calcCircumference() << "\n";
				} else if (a[4] == 90 && a[0] == a[1] && a[2] == a[3]) {
					Prostokat p(a[0], a[1], a[2], a[3], a[4]);
					std::cout << "Pole = " << p.calcArea() << ", Obwod = " << p.calcCircumference() << "\n";
				} else if (a[4] == 90 && a[0] == a[2] && a[1] == a[3]) {
					Prostokat p(a[0], a[2], a[1], a[3], a[4]);
					std::cout << "Pole = " << p.calcArea() << ", Obwod = " << p.calcCircumference() << "\n";
				} else if (a[4] == 90 && a[0] == a[3] && a[1] == a[2]) {
					Prostokat p(a[0], a[3], a[1], a[2], a[4]);
					std::cout << "Pole = " << p.calcArea() << ", Obwod = " << p.calcCircumference() << "\n";
				} else if (a[0] == a[1] && a[1] == a[2] && a[2] == a[3]) {
					Romb r(a[0], a[1], a[2], a[3], a[4]);
					std::cout << "Pole = " << r.calcArea() << ", Obwod = " << r.calcCircumference() << "\n";
				} else {
					std::cout << a[0] << " " << a[1] << " " << a[2] << " " << a[3] << " " << a[4] << " - dany czworokat nie istnieje\n";
					//is_f[i] = false;
				}
				arg += 5;
				break;
			}
			case 'p':
			{
				char* c;
				float a = std::strtof(argv[arg], &c);
				if (a <= 0) {
					std::cout << "p - " << argv[arg] << "- dana figura nie istnieje\n";
					//is_f[i] = false;
					++arg;
					break;
				}
				Pieciokat p(a);
				std::cout << "Pole = " << p.calcArea() << ", Obwod = " << p.calcCircumference() << "\n";
				++arg;
				break;
			}
			case 's':
			{
				char* c;
				float a = std::strtof(argv[arg], &c);
				if (a <= 0) {
					std::cout << "s - " << argv[arg] << " - dana figura nie istnieje\n";
					//is_f[i] = false;
					++arg;
					break;
				}
				Szesciokat s(a);
				std::cout << "Pole = " << s.calcArea() << ", Obwod = " << s.calcCircumference() << "\n";
				++arg;
				break;
			}
			default:
				std::cout << c << " - figura nie istnieje\n";
				//is_f[i] = false;
		}
	}
}
