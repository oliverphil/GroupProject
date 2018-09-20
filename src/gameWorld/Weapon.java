package gameWorld;

public class Weapon implements Item {
	private int damage;
	private String name;
	
	/**
	 * Creates a weapon.
	 * @param name
	 * @param damage
	 */
	public Weapon(String name, int damage) {
		setName(name);
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
