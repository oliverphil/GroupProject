package gameWorld;

import java.util.ArrayList;
import java.util.List;

/**
 * A Player
 * @author Dylan
 *
 */
public class Player extends Character {
	private List<Item> bag;

	public Player (String name) {
		super(name);

		bag = new ArrayList<Item>();
	}

	/**
	 * @return the bag
	 */
	public List<Item> getBag() {
		return bag;
	}

	/**
	 * @param bag the bag to set
	 */
	public void setBag(List<Item> bag) {
		this.bag = bag;
	}
}
