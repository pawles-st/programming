package Figura;

import java.lang.Math;

public class Kolo extends Figura {
	private float promien;
	public Kolo(float p) {
		promien = p;
	}
	public float calcArea() {
		return (float)Math.PI * promien * promien;
	}
	public float calcCircumference() {
		return 2 * (float)Math.PI * promien;
	}
}
