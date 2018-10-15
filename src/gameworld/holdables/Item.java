package gameworld.holdables;

import gameworld.FloorObject;
import gameworld.Player;
import gameworld.Tile;

import javax.xml.bind.annotation.XmlElement;

/**
 * Items are FloorObjects that can be picked up.
 * @author Dylan Ewens - ewensdyla 300423748
 *
 */
public abstract class Item extends FloorObject {

  private int weight;

  /**
   * Constructs a new Item.
   */
  public Item() {
    super();
  }

  /**
   * get weight.
   *
   * @return the weight
   */
  public int getWeight() {
    return weight;
  }

  /**
   * sets the weight.
   *
   * @param weight the weight to set
   */
  @XmlElement
  public void setWeight(int weight) {
    this.weight = weight;
  }

  /**
   * Use the item if possible.
   * @param p the current player
   * @param tile the tile the player is facing
   */
  public abstract void use(Player p, Tile tile);

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = super.hashCode();
    result = prime * result + weight;
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (!super.equals(obj)) {
      return false;
    }
    Item other = (Item) obj;
    if (weight != other.weight) {
      return false;
    }
    return true;
  }
}
