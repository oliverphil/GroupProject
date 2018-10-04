package gameworld;

import java.awt.Point;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Monster extends FloorObject {
  private int health;
  private int damage;

  public Monster(String name, Point p, int health) {
    super(name, p);
    this.health = health;
  }

  public Monster() {
    super();
  }

  /**
   * Return the current health of the Monster.
   * @return the health
   */
  public int getHealth() {
    return health;
  }

  /**
   * Sets the monsters health.
   * @param health health to set the monster to
   */
  @XmlElement(name = "health")
  public void setHealth(int health) {
    this.health = health;
  }

  /**
   * Add health to the Monster.
   * @param health to add
   */
  public void addHealth(int health) {
    this.health += health;
  }

  /**
   * Remove health from the Monster.
   * @param health to remove
   */
  public void removeHealth(int health) {
    this.health -= health;
  }

  /**
   * Returns damage dealt by monster.
   * @return the damage
   */
  public int getDamage() {
    return damage;
  }

  /**
   * Sets damage.
   * @param damage the damage to set
   */
  @XmlElement(name = "damage")
  public void setDamage(int damage) {
    this.damage = damage;
  }
}
