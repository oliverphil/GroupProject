package gameworld;

import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement
public abstract class Item extends FloorObject {

  /**
   * Constructs a new Item.
   */
  public Item() {
    super();
  }

}
