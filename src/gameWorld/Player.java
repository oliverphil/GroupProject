package gameWorld;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * A Player.
 *
 * @author Dylan
 *
 */
@XmlRootElement
public class Player extends Character {
  @XmlElement(name = "rightHand")
  private FloorObject rightHand;

  @XmlElement(name = "leftHand")
  private FloorObject leftHand;

  @XmlElementWrapper(name = "bag")
  @XmlElement(name = "item")
  private List<Item> bag;

  @XmlAttribute(name = "direction")
  private String direction;

  @XmlElement(name = "location")
  private Point location;

  @XmlElement(name = "view")
  private ViewDescriptor view;

  @XmlAttribute(name = "health")
  private int health = 100;

  /**
   * Constructs a player.
   */
  public Player() {
    super("Player");

    bag = new ArrayList<Item>();
    location = new Point(7, 7);
    this.setDirection("north");
  }

  /**
   * Changes the direction clockwise from where the customer is facing.
   */
  public void turnRight() {
    switch (getDirection()) {
      case "north":
        setDirection("east");
        break;
      case "east":
        setDirection("south");
        break;
      case "south":
        setDirection("west");
        break;
      case "west":
        setDirection("north");
        break;
      default:
        throw new RuntimeException("Unknown direction");
    }
  }

  /**
   * Changes the direction counter clockwise from where the customer is facing.
   */
  public void turnLeft() {
    switch (getDirection()) {
      case "north":
        setDirection("west");
        break;
      case "west":
        setDirection("south");
        break;
      case "south":
        setDirection("east");
        break;
      case "east":
        setDirection("north");
        break;
      default:
        break;
    }
  }

  /**
   * @return the direction
   */
  public String getDirection() {
    return direction;
  }

  /**
   * @param direction the direction to set
   */
  public void setDirection(String direction) {
    this.direction = direction;
  }

  /**
   * @return the location
   */
  public Point getLocation() {
    return location;
  }

  /**
   * @param loc the location to set
   */
  public void setLocation(Point loc) {
    this.location = loc;
  }

  /**
   * @return the view
   */
  public ViewDescriptor getView() {
    return view;
  }

  /**
   * @param view the view to set
   */
  public void setView(ViewDescriptor view) {
    this.view = view;
  }

  /**
   * @return the bag
   */
  public List<Item> getBag() {
    return bag;
  }

  /**
   * add the object to the players bag
   *
   * @param obj
   */
  public void addToBag(Item obj) {
    this.bag.add(obj);
  }

  /**
   * @return the health
   */
  public int getHealth() {
    return health;
  }

  /**
   * @param health the health to set
   */
  public void setHealth(int health) {
    this.health = health;
  }
}
