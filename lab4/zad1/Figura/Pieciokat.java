package Figura;

import java.lang.Math;

public class Pieciokat extends Figura {
	private float bok;
	public Pieciokat(float b) {
		bok = b;
	}
	public float calcArea() {
		return bok * bok / 4 * (float)Math.sqrt(25.0f + 10.0f * (float)Math.sqrt(5));
	}
	public float calcCircumference() {
		return 5 * bok;
	}
}
