package gameworld;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Monster extends FloorObject {
  private int health;
  private int damage;

  public Monster() {
    super();
  }

  /**
   * Return the current health of the Monster.
   * 
   * @return the health
   */
  public int getHealth() {
    return health;
  }

  /**
   * Sets the monsters health.
   * 
   * @param health health to set the monster to
   */
  @XmlElement(name = "health")
  public void setHealth(int health) {
    this.health = health;
  }

  /**
   * Add health to the Monster.
   * 
   * @param health to add
   */
  public void addHealth(int health) {
    this.health += health;
  }

  /**
   * Remove health from the Monster.
   * 
   * @param health to remove
   */
  public void removeHealth(int health) {
    this.health -= health;
  }

  /**
   * Returns damage dealt by monster.
   * 
   * @return the damage
   */
  public int getDamage() {
    return damage;
  }

  /**
   * Sets damage.
   * 
   * @param damage the damage to set
   */
  @XmlElement(name = "damage")
  public void setDamage(int damage) {
    this.damage = damage;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = super.hashCode();
    result = prime * result + damage;
    result = prime * result + health;
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (!super.equals(obj)) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    Monster other = (Monster) obj;
    if (damage != other.damage) {
      return false;
    }
    if (health != other.health) {
      return false;
    }
    return true;
  }
}
