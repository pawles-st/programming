import Figura.*;
import java.util.Arrays;

public class Figury {
	public static void main(String[] args) {
		boolean[] is_f = new boolean[args[0].length()];
		Arrays.fill(is_f, true);
		Figura[] f = new Figura[args[0].length()];
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
						is_f[i] = false;
						++arg;
						break;
					}
					if (a < 0.0f) {
						System.out.println("o - " + a + " - dana figura nie istnieje");
						is_f[i] = false;
						++arg;
						break;
					}
					f[i] = new Kolo(a);
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
							is_f[i] = false;
							break;
						}
					}
					if (parsed == false) {
						break;
					}
					if (a[0] < 0.0f || a[1] < 0.0f || a[2] < 0.0f || a[3] < 0.0f || a[4] < 0.0f || a[4] > 90.0f) {
						System.out.println("c - " + args[arg] + " " + args[arg + 1] + " " + args[arg + 2] + " " + args[arg + 3] + " " + args[arg + 4] +" - dana figura nie istnieje");
						is_f[i] = false;
						arg += 5;
						break;
					}
					if (a[4] == 90 && a[0] == a[1] && a[1] == a[2] && a[2] == a[3]) {
						f[i] = new Kwadrat(a[0], a[1], a[2], a[3], a[4]);
					} else if (a[4] == 90 && (a[0] == a[1] && a[2] == a[3])) {
						f[i] = new Prostokat(a[0], a[1], a[2], a[3], a[4]);
					} else if (a[4] == 90 && (a[0] == a[2] && a[1] == a[3])) {
						f[i] = new Prostokat(a[0], a[2], a[1], a[3], a[4]);
					} else if (a[4] == 90 && (a[0] == a[3] && a[1] == a[2])) {
						f[i] = new Prostokat(a[0], a[3], a[1], a[2], a[4]);
					} else if (a[0] == a[1] && a[1] == a[2] && a[2] == a[3]) {
						f[i] = new Romb(a[0], a[1], a[2], a[3], a[4]);
					} else {
						System.out.println(args[arg] + " " + args[arg + 1] + " " + args[arg + 2] + " " + args[arg + 3] + " " + args[arg + 4]  + " - dany czworokat nie istnieje");
						is_f[i] = false;
					}
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
						is_f[i] = false;
						++arg;
						break;
					}
					if (a < 0.0f) {	
						System.out.println("p - " + a + " - dana figura nie istnieje");
						is_f[i] = false;
						++arg;
						break;
					}
					f[i] = new Pieciokat(a);
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
						is_f[i] = false;
						++arg;
						break;
					}
					if (a < 0.0f) {	
						System.out.println("s - " + a + " - dana figura nie istnieje");
						is_f[i] = false;
						++arg;
						break;
					}
					f[i] = new Szesciokat(a);
					++arg;
					break;
				}
				default:
					System.out.println(c + " - figura nie istnieje");
					is_f[i] = false;
			}
			if (is_f[i] == true) {
				System.out.println("Pole = " + f[i].calcArea() + ", Obwod = " + f[i].calcCircumference());
			}
		}
	}
}
