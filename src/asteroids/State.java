/**
 * 
 */
package asteroids;

import java.util.ArrayList;
import java.util.List;

/**
 * The game state.
 * 
 * @author Tillmann Rendel
 */
public class State {
	/**
	 * Length of a discrete time step, in nanoseconds.
	 */
	private static final long step = 10000000;

	/**
	 * How far the state is behind the current moment in time, in nanoseconds.
	 */
	private long behind = 0L;

	/**
	 * The elements.
	 */
	private final List<Element> elements = new ArrayList<Element>();

	/**
	 * Whether we are started or stopped right now.
	 * 
	 * <p>
	 * <code>0</code> means that we are running, higher values means we are
	 * stopped.
	 * </p>
	 */
	private int stopped = 1;

	/**
	 * The most recent timestamp.
	 */
	private long timestamp;

	/**
	 * Adds an element.
	 * 
	 * @param element
	 *            the element to add
	 */
	public void addElement(final Element element) {
		elements.add(element);
	}

	/**
	 * Returns the list of elements.
	 * 
	 * @return the list of elements
	 */
	public List<Element> getElements() {
		return elements;
	}

	/**
	 * Advance the state to catch up with the time.
	 */
	public void simulate() {
		measureTime();

		while (behind >= step) {
			for (final Element element : elements) {
				element.tick();
			}
			behind -= step;
		}
	}

	/**
	 * Starts the simulation. You need a call of this method for every call of
	 * {@link #stop()}.
	 */
	public void start() {
		stopped--;
		if (stopped == 0) {
			timestamp = System.nanoTime();
		}
	}

	/**
	 * Stops the simulation. For every call of this method, you need a call of
	 * {@link #start()}.
	 */
	public void stop() {
		if (stopped == 0) {
			measureTime();
		}
		stopped++;
	}

	private void measureTime() {
		final long now = System.nanoTime();
		behind += (now - timestamp);
		timestamp = now;
	}
}
