package Figura;

public class Prostokat extends Czworokat {
	public Prostokat(float b1, float b2, float b3, float b4, float k) throws IllegalArgumentException {
		bok1 = b1;
		bok2 = b2;
		bok3 = b3;
		bok4 = b4;
		kat = k;
	}
	public float calcArea() {
		return bok1 * bok3;
	}
	public float calcCircumference() {
		return bok1 + bok2 + bok3 + bok4;
	}
}
