package gameWorld;

import java.awt.Point;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.NONE)
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
