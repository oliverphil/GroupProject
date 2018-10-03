package gameWorld;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import persistence.*;
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
   * @return the direction
   */
  public String getDirection() {
    return direction;
  }

  /**
   * @param direction the direction to set
   */
  @XmlElement(name = "direction")
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
  @XmlElement(name = "location")
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
  @XmlElement(name = "view")
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
  @XmlElement(name = "health")
  public void setHealth(int health) {
    this.health = health;
  }
}
