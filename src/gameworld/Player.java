package gameworld;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * A Player is a character controlled by the user.
 *
 * @author Dylan
 *
 */
@XmlRootElement
public class Player extends Character {
  private FloorObject rightHand;
  private FloorObject leftHand;
  private List<Item> bag;
  private String direction;
  private Point location;
  private ViewDescriptor view;
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
   * Returns the contents of the player's right hand.
   *
   * @return the FloorObject in rightHand
   */
  public FloorObject getRightHand() {
    return rightHand;
  }

  /**
   * Sets the contents of rightHand.
   *
   * @param rightHand the new contents of rightHand
   */
  @XmlElement(name = "rightHand")
  public void setRightHand(FloorObject rightHand) {
    this.rightHand = rightHand;
  }

  /**
   * Returns the contents of the player's left hand.
   *
   * @return the FloorObject in leftHand
   */
  public FloorObject getLeftHand() {
    return leftHand;
  }

  /**
   * Sets the contents of leftHand.
   *
   * @param leftHand the new contents of leftHand
   */
  @XmlElement(name = "leftHand")
  public void setLeftHand(FloorObject leftHand) {
    this.leftHand = leftHand;
  }

  /**
   * Get the Direction.
   * @return the direction
   */
  public String getDirection() {
    return direction;
  }

  /**
   * Set the direction.
   * @param direction the direction to set
   */
  @XmlElement(name = "direction")
  public void setDirection(String direction) {
    this.direction = direction;
  }

  /**
   * Get location (Point).
   * @return the location
   */
  public Point getLocation() {
    return location;
  }

  /**
   * Set location (Point).
   * @param loc the location to set
   */
  @XmlElement(name = "location")
  public void setLocation(Point loc) {
    this.location = loc;
  }

  /**
   * Returns the viewDescriptor that the player current holds.
   * @return a view object
   */
  public ViewDescriptor getView() {
    return view;
  }

  /**
   * Sets the current ViewDescripor.
   * @param view the view to set
   */
  @XmlElement(name = "view")
  public void setView(ViewDescriptor view) {
    this.view = view;
  }

  /**
   * Gets the bag.
   * @return the bag
   */
  public List<Item> getBag() {
    return bag;
  }

  /**
   * sets the bag to a new List of items.
   *
   * @param bag the new bag contents
   */
  @XmlElementWrapper(name = "bag")
  @XmlElement(name = "item")
  public void setBag(List<Item> bag) {
    this.bag = bag;
  }

  /**
   * Add the object to the players bag.
   * @param obj the object to add to the bag
   */
  public void addToBag(Item obj) {
    this.bag.add(obj);
  }

  /**
   * Gets the health.
   * @return the health
   */
  public int getHealth() {
    return health;
  }

  /**
   * Sets the Health.
   * @param health the health to set
   */
  @XmlElement(name = "health")
  public void setHealth(int health) {
    this.health = health;
  }

  /**
   * Called when a player uses a health potion.
   * Gives the player 20 health.
   */
  public void giveHealth() {
    health += 20;
    if (health > 100) {
      health = 100;
    }
  }
}
