/**
 * 
 */
package asteroids;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

/**
 * Keyboard and other controls.
 * 
 * @author Tillmann Rendel
 */
public class Control implements KeyListener, WindowListener {
	/**
	 * Whether the user wants the spaceship to accelerate.
	 */
	private boolean accelerating = false;

	/**
	 * Whether the user wants the spaceship to decelerate.
	 */
	private boolean decelerating = false;

	/**
	 * Whether the game is currently running. When this becomes
	 * <code>false</code>, the game will automatically shutdown.
	 */
	private boolean running;

	/**
	 * The state this controls affect.
	 */
	private final State state;

	/**
	 * Whether the user wants the spaceship to turn left, that is,
	 * counterclockwise.
	 */
	private boolean turningLeft = false;

	/**
	 * Whether the user wants the spaceship to turn right, that is, clockwise.
	 */
	private boolean turningRight = false;

	/**
	 * Creates control.
	 * 
	 * @param state
	 *            the state the new controls affect
	 */
	public Control(final State state) {
		this.state = state;
		running = true;
	}

	/**
	 * Changes the state to reflect the actions requested by the user.
	 */
	public void control() {
		final Ship ship = state.getShip();

		ship.acceleration = 0;
		ship.acceleration += accelerating ? Rules.SHIP_ACCELERATION : 0.0;
		ship.acceleration -= decelerating ? Rules.SHIP_DECELERATION : 0.0;

		ship.rotation = 0;
		ship.rotation += turningLeft ? Rules.SHIP_ROTATION : 0.0;
		ship.rotation -= turningRight ? Rules.SHIP_ROTATION : 0.0;
	}

	/**
	 * Returns whether the game is still running.
	 * 
	 * @return <code>true</code> if the game is still running,
	 *         <code>false</code> otherwise
	 */
	public boolean isRunning() {
		return running;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void keyPressed(final KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_UP:
			accelerating = true;
			break;
		case KeyEvent.VK_DOWN:
			decelerating = true;
			break;
		case KeyEvent.VK_LEFT:
			turningLeft = true;
			break;
		case KeyEvent.VK_RIGHT:
			turningRight = true;
			break;
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void keyReleased(final KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_ESCAPE:
			running = false;
			break;
		case KeyEvent.VK_UP:
			accelerating = false;
			break;
		case KeyEvent.VK_DOWN:
			decelerating = false;
			break;
		case KeyEvent.VK_LEFT:
			turningLeft = false;
			break;
		case KeyEvent.VK_RIGHT:
			turningRight = false;
			break;
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void keyTyped(final KeyEvent e) {

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void windowActivated(final WindowEvent e) {
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void windowClosed(final WindowEvent e) {
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void windowClosing(final WindowEvent e) {
		running = false;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void windowDeactivated(final WindowEvent e) {
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void windowDeiconified(final WindowEvent e) {
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void windowIconified(final WindowEvent e) {
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void windowOpened(final WindowEvent e) {
	}

}
