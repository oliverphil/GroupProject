package gameworld.holdables;

import gameworld.FloorObject;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;

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
  @XmlElements({ @XmlElement(name = "explosiveWeight", type = Explosive.class),
      @XmlElement(name = "flaskWeight", type = Flask.class),
      @XmlElement(name = "toolWeight", type = Tool.class),
      @XmlElement(name = "weaponWeight", type = Weapon.class),

  })
  public void setWeight(int weight) {
    this.weight = weight;
  }

}
