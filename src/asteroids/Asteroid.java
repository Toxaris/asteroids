/**
 * 
 */
package asteroids;

import java.awt.Color;
import java.awt.Graphics;

/**
 * An asteroid.
 * 
 * @author Tillmann Rendel
 */
public class Asteroid extends Element {
	/**
	 * The color of this asteroid.
	 */
	private final Color color;

	/**
	 * The radius of this asteroid, in units.
	 */
	private final double radius;

	/**
	 * Creates an asteroid.
	 */
	public Asteroid(final double locationX, final double locationY, final double velocityX, final double velocityY,
			final double acceleration, final double direction, final double rotation, final Color color,
			final double radius) {
		super(locationX, locationY, velocityX, velocityY, acceleration, direction, rotation);
		this.radius = radius;
		this.color = color;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * <p>
	 * This implementation renders the asteroid as a filled circle.
	 * </p>
	 */
	@Override
	public void render(final Graphics g) {
		g.setColor(color);
		g.fillOval((int) -radius, (int) -radius, (int) radius, (int) radius);
	}
}
