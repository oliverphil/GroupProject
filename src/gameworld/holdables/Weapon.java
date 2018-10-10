package gameworld.holdables;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Weapon extends Item {
  private int damage;

  /**
   * Creates a weapon.
   * 
   */
  public Weapon() {
    super();
  }

  public int getDamage() {
    return damage;
  }

  @XmlElement
  public void setDamage(int damage) {
    this.damage = damage;
  }
  
  public String toString() {
    return this.getName();
  }
}
