package gameWorld;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Monster extends FloorObject{
	private int health;

	public Monster(String name) {
		super(name);
	}

	/**
	 * @return the health
	 */
	public int getHealth() {
		return health;
	}

	/**
	 * @param health to add
	 */
	public void addHealth(int health) {
		this.health += health;
	}

	/**
	 * @param health to remove
	 */
	public void removeHealth(int health) {
		this.health -= health;
	}

}
