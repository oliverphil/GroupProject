package gameWorld;

/**
 * Items are objects found in the game world that can be weapons or tools
 * to help a player along their journey.
 * @author ewensdyla
 *
 */
public abstract class FloorObject {
	private String name;

	/**
	 * Construct a new FloorObject
	 * @param name
	 */
	public FloorObject(String name) {
		setName(name);
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
}
