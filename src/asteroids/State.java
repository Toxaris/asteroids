/**
 * 
 */
package asteroids;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * The game state.
 * 
 * @author Tillmann Rendel
 */
public class State {

	/**
	 * The elements.
	 */
	private final List<Element> elements = new ArrayList<Element>();

	/**
	 * The spaceship controlled by the player.
	 */
	private Ship ship;

	/**
	 * Adds an element.
	 * 
	 * @param element
	 *            the element to add
	 */
	public void addElement(final Element element) {
		if (element instanceof Ship) {
			if (ship == null) {
				ship = (Ship) element;
			} else {
				throw new IllegalArgumentException("only one ship allowed");
			}
		}
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
	 * Returns the spaceship the player controls.
	 */
	public Ship getShip() {
		if (ship == null) {
			throw new NoSuchElementException("No ship");
		}
		return ship;
	}
}
