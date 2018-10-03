package gameWorld;

import java.awt.Point;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Weapon extends Item {
  private int damage;

  /**
   * Creates a weapon.
   * 
   * @param name   name of the weapon
   * @param damage damage the weapon does
   */
  public Weapon(String name, Point p, int damage) {
    super(name, p);
    setDamage(damage);
  }

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
}
