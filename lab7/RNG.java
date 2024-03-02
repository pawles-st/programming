import java.util.Random;

/** Random number generator class
 * @author Paweł Leśniak
 * @version 1.0
 */
final class RNG {
	/** The variable used to calculate the time between actions of a tile */
	static private double k;
	/** The generator used for number generation */
	static private final Random generator = new Random();
	/** Private constructor, creation of an object is impossible */
	private RNG(){}
	/** Method assigning a value to variable k */
	static public void initialise(double k) {
		RNG.k = k;
	}
	/** Method for creation of a random hue
	 * @return double randomly generated hue
	 */
	static public double randomHue() {
		return generator.nextDouble() * 360;
	}
	/** Method for creation of random delay
	 * @return long randomly generated number of milliseconds of delay
	 */
	static public long randomDelay() {
		return (long) ((generator.nextDouble() + 0.5) * k);
	}
	/** Method for creation of random probability event
	 * @return double the probability for deciding what the tile does (range 0.0 - 1.0)
	 */
	static public double randomProbability() {
		return generator.nextDouble();
	}
}
