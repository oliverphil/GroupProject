package gameworld.holdables;

import javax.xml.bind.annotation.XmlTransient;

import gameworld.FloorObject;

@XmlTransient
public abstract class Item extends FloorObject {

  private int weight;
  
  /**
   * Constructs a new Item.
   */
  public Item() {
    super();
  }
  
  /**
   * @return the weight
   */
  public int getWeight() {
    return weight;
  }
  
  /**
   * @param weight the weight to set
   */
  public void setWeight(int weight) {
    this.weight = weight;
  }

}
