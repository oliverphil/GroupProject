package gameWorld;

public class Monster extends FloorObject{
	private int health;

	public Monster(String name, int health) {
		super(name);
		this.health = health;
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
