/**
 * 
 */
package asteroids;

/**
 * Game rules.
 * 
 * @author Tillmann Rendel
 */
public abstract class Rules {
	/**
	 * Acceleration of the player's ship, in units per tick per tick.
	 */
	public static final double SHIP_ACCELERATION = 0.01;

	/**
	 * Deceleration of the player's ship, in units per tick per tick.
	 */
	public static final double SHIP_DECELERATION = 0.005;

	/**
	 * Rotation of the player's ship, in radians per tick.
	 */
	public static final double SHIP_ROTATION = Math.PI / 180;
}
