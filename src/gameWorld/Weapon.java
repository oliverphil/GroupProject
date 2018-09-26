package gameWorld;

public class Weapon extends FloorObject {
	private int damage;

	/**
	 * Creates a weapon.
	 * @param name
	 * @param damage
	 */
	public Weapon(String name, int damage) {
		super(name);
		setDamage(damage);
	}

	/**
	 * @return the damage
	 */
	public int getDamage() {
		return damage;
	}

	/**
	 * @param damage the damage to set
	 */
	public void setDamage(int damage) {
		this.damage = damage;
	}
}
