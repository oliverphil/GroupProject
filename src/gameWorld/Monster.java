package gameWorld;

import java.awt.Point;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.NONE)
public class Monster extends FloorObject {
  @XmlAttribute(name = "health")
  private int health;

  public Monster(String name, Point p, int health) {
    super(name, p);
    this.health = health;
  }
  
  public Monster() {
    super();
  }

  /**
   * @return the health
   */
  public int getHealth() {
    return health;
  }

  /**
   * @param health to add
   */
  public void addHealth(int health) {
    this.health += health;
  }

  /**
   * @param health to remove
   */
  public void removeHealth(int health) {
    this.health -= health;
  }

}
