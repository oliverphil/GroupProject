package gameWorld;

import java.awt.Point;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Weapon extends Item {
	private int damage;

	/**
	 * Creates a weapon.
	 * @param name
	 * @param damage
	 */
	public Weapon(String name, Point p, int damage) {
		super(name, p);
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
