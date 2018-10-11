package gameworld.holdables;

import gameworld.FloorObject;
import gameworld.Player;
import gameworld.Tile;

import javax.xml.bind.annotation.XmlElement;

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
   * @return the weight
   */
  public int getWeight() {
    return weight;
  }

  /**
   * sets the weight.
   * @param weight the weight to set
   */
  @XmlElement
  public void setWeight(int weight) {
    this.weight = weight;
  }

  /**
   * Use the item if possible.
   */
  public abstract void use(Player p, Tile tile);
}
