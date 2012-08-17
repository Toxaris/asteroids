/**
 * 
 */
package asteroids;

/**
 * Game physics simulation.
 * 
 * @author Tillmann Rendel
 */
public class Physics {
	/**
	 * Length of a discrete time step, in nanoseconds.
	 */
	private static final long step = 10000000;

	/**
	 * How far the state is behind the current moment in time, in nanoseconds.
	 */
	private long behind = 0L;

	/**
	 * The game state manipulated by the physics simulation.
	 */
	private final State state;

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
	 * Create game physics simulation that manipulates a specific state.
	 * 
	 * @param state
	 *            the game state to be manipulated by the new physics simulation
	 */
	public Physics(final State state) {
		this.state = state;
	}

	/**
	 * Advance the state to catch up with the time.
	 */
	public void simulate() {
		measureTime();

		while (behind >= step) {
			for (final Element element : state.getElements()) {
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
