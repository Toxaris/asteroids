/**
 * 
 */
package asteroids;

/**
 * Coordinate manipulations
 * 
 * @author Tillmann Rendel
 */
public abstract class Coordinate {
	public static double wrapAroundFactor(final double x, final double y) {
		final double xxyy = x * x + y * y;
		double factor = 1.0;
		if (xxyy > 10000.0) {
			factor = Math.sqrt(xxyy) / 100;
			while (factor > 1.0) {
				factor -= 2.0;
			}
		}
		return factor;
	}
}
