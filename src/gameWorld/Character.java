package gameWorld;

/**
 * A Character is a person or animal within the game but not
 * necessarily a controlled player
 * 
 * @author Dylan
 *
 */
public abstract class Character {
	private String name;

	/**
	 * Character objects can be either played or unplayable
	 * @param name
	 */
	public Character(String name) {
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