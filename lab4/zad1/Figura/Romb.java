package Figura;

import java.lang.Math;

public class Romb extends Czworokat {
	public Romb(float b1, float b2, float b3, float b4, float k) throws IllegalArgumentException {
		bok1 = b1;
		bok2 = b2;
		bok3 = b3;
		bok4 = b4;
		kat = k;
	}
	public float calcArea() {
		return bok1 * bok1 * (float)Math.sin(Math.toRadians(kat));
	}
	public float calcCircumference() {
		return 4 * bok1;
	}
}
