/**
 * 
 */
package asteroids;

import java.awt.Graphics;

/**
 * A moving, accelerating, rotating something on the screen.
 * 
 * @author Tillmann Rendel
 */
public abstract class Element {
	/**
	 * Acceleration, in units per tick per tick.
	 */
	protected double acceleration;

	/**
	 * Direction the element is facing, in radians.
	 */
	protected double direction;

	/**
	 * Location of the element, X coordinate, in units.
	 */
	protected double locationX;

	/**
	 * Location of the element, Y coordinate, in units.
	 */
	protected double locationY;

	/**
	 * Turning moment of the element, in radians per tick.
	 */
	protected double rotation;

	/**
	 * Velocity of the element, X coordinate, in units per tick.
	 */
	protected double velocityX;

	/**
	 * Velocity of the element, Y coordinate, in units per tick.
	 */
	protected double velocityY;

	/**
	 * Create moving, accelerating, rotating something on the screen.
	 */
	public Element(final double locationX, final double locationY, final double velocityX, final double velocityY,
			final double acceleration, final double direction, final double rotation) {
		this.locationX = locationX;
		this.locationY = locationY;
		this.velocityX = velocityX;
		this.velocityY = velocityY;
		this.acceleration = acceleration;
		this.direction = direction;
		this.rotation = rotation;
	}

	/**
	 * Render this element on the screen.
	 * 
	 * @param g
	 *            the graphics context
	 */
	public abstract void render(final Graphics g);

	/**
	 * Advance the simulation of this element's state one step.
	 */
	public void tick() {
		locationX += velocityX;
		locationY += velocityY;
		velocityX += acceleration * Math.sin(direction);
		velocityY += acceleration * Math.cos(direction);
		direction += rotation;
	}
}
