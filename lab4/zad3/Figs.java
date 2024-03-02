import Figury.Figury;

public class Figs {
	public static void main(String[] args) {
		int arg = 1;
		for (int i = 0; i < args[0].length(); ++i) {
			if (arg == args.length) {
				System.out.println("brakuje argumentow");
				return;
			}
			char c = args[0].charAt(i);
			switch (c) {
				case 'o':
				{
					float a = 0;
					try {
						a = Float.parseFloat(args[arg]);
					} catch (IllegalArgumentException e) {
						System.out.println("o - " + args[arg] + " - dana figura nie istnieje");
						++arg;
						break;
					}
					if (a < 0.0f) {
						System.out.println("o - " + a + " - dana figura nie istnieje");
						++arg;
						break;
					}
					System.out.println("Pole = " + Figury.Misc.Circle.ObliczPole(a) + ", Obwod = " + Figury.Misc.Circle.ObliczObwod(a));
					++arg;
					break;
				}
				case 'c':
				{
					if (arg + 4 >= args.length) {
						System.out.println("brakuje argumentow");
						return;
					}
					float[] a = new float[5];
					boolean parsed = true;
					for (int j = 0; j < 5; ++j) {
						try {
							a[j] = Float.parseFloat(args[arg + j]);
						} catch (IllegalArgumentException e) {
							System.out.println("o - " + args[arg] + " " + args[arg + 1] + " " + args[arg + 2] + " " + args[arg + 3] + " " + args[arg + 4] + " - dana figura nie istnieje");
							parsed = false;
							break;
						}
					}
					if (parsed == false) {
						break;
					}
					if (a[0] < 0.0f || a[1] < 0.0f || a[2] < 0.0f || a[3] < 0.0f || a[4] < 0.0f || a[4] > 90.0f) {
						System.out.println("c - " + args[arg] + " " + args[arg + 1] + " " + args[arg + 2] + " " + args[arg + 3] + " " + args[arg + 4] +" - dana figura nie istnieje");
						arg += 5;
						break;
					}
					if (a[4] == 90 && a[0] == a[1] && a[1] == a[2] && a[2] == a[3]) {
						System.out.println("Pole = " + Figury.Misc.Square.ObliczPole(a[0]) + ", Obwod = " + Figury.Misc.Square.ObliczObwod(a[0]));
					} else if (a[4] == 90 && (a[0] == a[1] && a[2] == a[3])) {
						System.out.println("Pole = " + Figury.Quadrangle.Rectangle.ObliczPole(a[0], a[2]) + ", Obwod = " + Figury.Quadrangle.Rectangle.ObliczObwod(a[0], a[2]));
					} else if (a[4] == 90 && (a[0] == a[2] && a[1] == a[3])) {
						System.out.println("Pole = " + Figury.Quadrangle.Rectangle.ObliczPole(a[0], a[1]) + ", Obwod = " + Figury.Quadrangle.Rectangle.ObliczObwod(a[0], a[1]));
					} else if (a[4] == 90 && (a[0] == a[3] && a[1] == a[2])) {
						System.out.println("Pole = " + Figury.Quadrangle.Rectangle.ObliczPole(a[0], a[1]) + ", Obwod = " + Figury.Quadrangle.Rectangle.ObliczObwod(a[0], a[1]));
					} else if (a[0] == a[1] && a[1] == a[2] && a[2] == a[3]) {
						System.out.println("Pole = " + Figury.Quadrangle.Rhombus.ObliczPole(a[0], a[4]) + ", Obwod = " + Figury.Quadrangle.Rhombus.ObliczObwod(a[0], a[4]));
					} else {
						System.out.println(args[arg] + " " + args[arg + 1] + " " + args[arg + 2] + " " + args[arg + 3] + " " + args[arg + 4]  + " - dany czworokat nie istnieje");					}
					arg += 5;
					break;
				}
				case 'p':
				{
					float a = 0;
					try {
						a = Float.parseFloat(args[arg]);
					} catch (IllegalArgumentException e) {
						System.out.println("p - " + args[arg] + " - dana figura nie istnieje");
						++arg;
						break;
					}
					if (a < 0.0f) {	
						System.out.println("p - " + a + " - dana figura nie istnieje");
						++arg;
						break;
					}
					System.out.println("Pole = " + Figury.Misc.Pentagon.ObliczPole(a) + ", Obwod = " + Figury.Misc.Pentagon.ObliczObwod(a));
					++arg;
					break;
				}
				case 's':
				{
					float a = 0;
					try {
						a = Float.parseFloat(args[arg]);
					} catch (IllegalArgumentException e) {
						System.out.println("s - " + args[arg] + " - dana figura nie istnieje");
						++arg;
						break;
					}
					if (a < 0.0f) {	
						System.out.println("s - " + a + " - dana figura nie istnieje");
						++arg;
						break;
					}
					System.out.println("Pole = " + Figury.Misc.Hexagon.ObliczPole(a) + ", Obwod = " + Figury.Misc.Hexagon.ObliczObwod(a));
					++arg;
					break;
				}
				default:
					System.out.println(args[i] + " - figura nie istnieje");
			}
		}
	}
}
