package gameWorld;

import java.awt.Point;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.NONE)
public class Monster extends FloorObject {
  @XmlAttribute(name = "health")
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
   * @param health
   */
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
  public void setDamage(int damage) {
    this.damage = damage;
  }
}
