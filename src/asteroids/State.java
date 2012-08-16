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
	 * The elements.
	 */
	private final List<Element> elements = new ArrayList<Element>();

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
	 * Advance the state.
	 */
	public void tick() {
		for (final Element element : elements) {
			element.tick();
		}
	}
}
