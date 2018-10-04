package gameworld;

import java.awt.Point;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public abstract class Item extends FloorObject {

  public Item(String name, Point p) {
    super(name, p);
  }

  /**
   * No-Args constructor for JAXB.
   */
  public Item() {
    super();
  }

}
