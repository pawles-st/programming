package Figura;

import java.lang.Math;

public class Szesciokat extends Figura {
	private float bok;
	public Szesciokat(float b) {
		bok = b;
	}
	public float calcArea() {
		return 3 * bok * bok * (float)Math.sqrt(3) / 2;
	}
	public float calcCircumference() {
		return 6 * bok;
	}
}
