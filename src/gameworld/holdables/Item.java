package gameworld.holdables;

import javax.xml.bind.annotation.XmlTransient;

import gameworld.FloorObject;

@XmlTransient
public abstract class Item extends FloorObject {

  /**
   * Constructs a new Item.
   */
  public Item() {
    super();
  }

}
