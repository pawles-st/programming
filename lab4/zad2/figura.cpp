#include <cmath>
#include "figura.hpp"

float Kolo::calcArea() {
	return M_PI * promien * promien;
}

float Kolo::calcCircumference() {
	return 2 * M_PI * promien;
}

float Kwadrat::calcArea() {
	return bok1 * bok1;
}

float Kwadrat::calcCircumference() {
	return 4 * bok1;
}

float Pieciokat::calcArea() {
	return bok * bok / 4 * sqrt(25 + 10 * sqrt(5));
}

float Pieciokat::calcCircumference() {
	return 5 * bok;
}

float Prostokat::calcArea() {
	return bok1 * bok3;
}

float Prostokat::calcCircumference() {
	return bok1 + bok2 + bok3 + bok4;
}

float Romb::calcArea() {
	return bok1 * bok1 * sin(kat * M_PI / 180);
}

float Romb::calcCircumference() {
	return bok1 + bok2 + bok3 + bok4;
}

float Szesciokat::calcArea() {
	return 3 * bok * bok * sqrt(3) / 2;
}

float Szesciokat::calcCircumference() {
	return 6 * bok;
}
