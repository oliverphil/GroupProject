package gameworld;

import gameworld.barriers.Barrier;
import gameworld.holdables.Item;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlTransient;

/**
 * Items are objects found in the game world that can be weapons or tools to help a player along
 * their journey.
 * 
 * @author ewensdyla
 *
 */
@XmlTransient
public abstract class FloorObject {
  private String name;
  private Point location;

  /**
   * Construct a new FloorObject.
   * 
   */
  public FloorObject() {
    setName("");
    setLocation(new Point(0, 0));
  }

  /**
   * getName.
   * 
   * @return the name
   */
  public String getName() {
    return name;
  }

  /**
   * setName.
   * 
   * @param name the name to set
   */
  @XmlElements({ @XmlElement(name = "fountainName", type = Fountain.class),
      @XmlElement(name = "itemName", type = Item.class),
      @XmlElement(name = "ladderName", type = Ladder.class),
      @XmlElement(name = "monsterName", type = Monster.class),
      @XmlElement(name = "barrierName", type = Barrier.class) })
  public void setName(String name) {
    this.name = name;
  }

  /**
   * getLocation.
   * 
   * @return the location
   */
  public Point getLocation() {
    return location;
  }

  /**
   * setLocation.
   * 
   * @param location the location to set
   */
  @XmlElements({ @XmlElement(name = "fountainLocation", type = Fountain.class),
      @XmlElement(name = "itemLocation", type = Item.class),
      @XmlElement(name = "ladderLocation", type = Ladder.class),
      @XmlElement(name = "monsterLocation", type = Monster.class),
      @XmlElement(name = "barrierLocation", type = Barrier.class) })
  public void setLocation(Point location) {
    this.location = location;
  }

}
