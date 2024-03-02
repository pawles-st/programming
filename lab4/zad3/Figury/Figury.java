package Figury;

import java.lang.Math;

public class Figury {
	private interface QuadrangleMethods {
		public float ObliczPole(float arg1, float arg2);
		public float ObliczObwod(float arg1, float arg2);
	}
	private interface MiscMethods {
		public float ObliczPole(float arg);
		public float ObliczObwod(float arg);
	}
	public enum Quadrangle implements QuadrangleMethods {
		Rectangle {
			public float ObliczPole(float side1, float side2) {
				return side1 * side2;
			}
			public float ObliczObwod(float side1, float side2) {
				return 2.0f * side1 +  2.0f * side2;
			}
		},
		Rhombus {
			public float ObliczPole(float side, float angle) {
				return side * side * (float) Math.sin(Math.toRadians(angle));
			}
			public float ObliczObwod(float side, float angle) {
				return 4 * side;
			}
		};
	}
	public enum Misc implements MiscMethods {
		Square {
			public float ObliczPole(float arg) {
				return arg * arg;
			}
			public float ObliczObwod(float arg) {
				return 4.0f * arg;
			}
		},
		Circle {
			public float ObliczPole(float arg) {
				return (float) Math.PI * arg * arg;
			}
			public float ObliczObwod(float arg) {
				return 2.0f * (float) Math.PI * arg;
			}
		},
		Pentagon {
			public float ObliczPole(float arg) {
				return arg * arg * (float) Math.sqrt(25 + 10 * (float) Math.sqrt(5)) / 4.0f;
			}
			public float ObliczObwod(float arg) {
				return 5 * arg;
			}
		},
		Hexagon {
			public float ObliczPole(float arg) {
				return 3.0f * arg * arg * (float) Math.sqrt(3) / 2.0f;
			}
			public float ObliczObwod(float arg) {
				return 6 * arg;
			}
		};
	}
}
