/**
 * 
 */
package asteroids;

import java.awt.Color;
import java.awt.Graphics;

/**
 * @author Tillmann Rendel
 * 
 */
public class Asteroid extends Element {
	private final Color color;
	private final double radius;

	public Asteroid(final double locationX, final double locationY, final double velocityX, final double velocityY,
			final double acceleration, final double direction, final double rotation, final Color color,
			final double radius) {
		super(locationX, locationY, velocityX, velocityY, acceleration, direction, rotation);
		this.radius = radius;
		this.color = color;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void render(final Graphics g) {
		g.setColor(color);
		g.fillOval((int) (locationX - radius), (int) (locationY - radius), (int) radius, (int) radius);
	}
}
