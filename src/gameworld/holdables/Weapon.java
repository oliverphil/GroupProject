package gameworld.holdables;

import javax.xml.bind.annotation.XmlAttribute;
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

  @XmlAttribute(name = "damage")
  public int getDamage() {
    return damage;
  }

  public void setDamage(int damage) {
    this.damage = damage;
  }
  
  public String toString() {
    return this.getName();
  }
}
