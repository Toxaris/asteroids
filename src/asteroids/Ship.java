/**
 * 
 */
package asteroids;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Polygon;

/**
 * The spaceship the player controls.
 * 
 * @author Tillmann Rendel
 */
public class Ship extends Element {
	/**
	 * The color of the ship.
	 */
	private static final Color color = Color.cyan;

	/**
	 * The shape of the ship, as a polygon around the origin.
	 */
	private static final Polygon polygon = new Polygon(new int[] { -10, 0, 10, 0 }, new int[] { -10, 25, -10, 0 }, 4);

	/**
	 * Creates ship.
	 * 
	 */
	public Ship(final double locationX, final double locationY, final double velocityX, final double velocityY,
			final double acceleration, final double direction, final double rotation) {
		super(locationX, locationY, velocityX, velocityY, acceleration, direction, rotation);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void render(final Graphics2D g) {
		g.setColor(color);
		g.fillPolygon(polygon);
	}
}
