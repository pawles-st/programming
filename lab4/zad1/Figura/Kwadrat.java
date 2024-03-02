package Figura;

public class Kwadrat extends Czworokat {
	public Kwadrat(float b1, float b2, float b3, float b4, float k) throws IllegalArgumentException {
		bok1 = b1;
		bok2 = b2;
		bok3 = b3;
		bok4 = b4;
		kat = k;
	} 
	public float calcArea() {
		return bok1 * bok1;
	}
	public float calcCircumference() {
		return 4 * bok1;
	}
}
