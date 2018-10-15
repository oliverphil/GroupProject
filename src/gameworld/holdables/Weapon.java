package gameworld.holdables;

import gameworld.Player;
import gameworld.Tile;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Deals damage to the bosses of the game.
 * @author Dylan Ewens - ewensdyla 300423748
 *
 */
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

  @Override
  public void use(Player p, Tile tile) {
    // do nothing
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = super.hashCode();
    result = prime * result + damage;
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
    Weapon other = (Weapon) obj;
    if (damage != other.damage) {
      return false;
    }
    return true;
  }
}
