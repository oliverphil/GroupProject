package gameWorld;

import java.awt.Point;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
//@XmlAccessorType(XmlAccessType.NONE)
public class Weapon extends Item {
  private int damage;

  /**
   * Creates a weapon.
   * 
   * @param name
   * @param damage
   */
  public Weapon(String name, Point p, int damage) {
    super(name, p);
    setDamage(damage);
  }
  
  public Weapon() {
    super();
  }

  @XmlAttribute(name = "damage")
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
