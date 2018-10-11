package gameworld;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;

import gameworld.holdables.Explosive;
import gameworld.holdables.Flask;
import gameworld.holdables.Item;
import gameworld.holdables.Tool;
import gameworld.holdables.Weapon;

/**
 * A Player is a character controlled by the user.
 *
 * @author Dylan
 *
 */
@XmlRootElement
public class Player {
  private List<Item> bag;
  private String direction;
  private Point location;
  private ViewDescriptor view;
  private int health = 100;
  private double time;

  private int currentWeight;
  private static final int MAX_WEIGHT = 15;

  /**
   * Constructs a player.
   */
  public Player() {
    bag = new ArrayList<Item>();
    location = new Point(7, 7);
    this.setDirection("north");

    setCurrentWeight(0);
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
   * Get the Direction.
   *
   * @return the direction
   */
  public String getDirection() {
    return direction;
  }

  /**
   * Set the direction.
   *
   * @param direction the direction to set
   */
  @XmlElement(name = "direction")
  public void setDirection(String direction) {
    this.direction = direction;
  }

  /**
   * Get location (Point).
   *
   * @return the location
   */
  public Point getLocation() {
    return location;
  }

  /**
   * Set location (Point).
   *
   * @param loc the location to set
   */
  @XmlElement(name = "location")
  public void setLocation(Point loc) {
    this.location = loc;
  }

  /**
   * Returns the viewDescriptor that the player current holds.
   *
   * @return a view object
   */
  public ViewDescriptor getView() {
    return view;
  }

  /**
   * Sets the current ViewDescripor.
   *
   * @param view the view to set
   */
  @XmlElement(name = "view")
  public void setView(ViewDescriptor view) {
    this.view = view;
  }

  /**
   * Gets the bag.
   *
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
  @XmlElements({ @XmlElement(name = "explosive", type = Explosive.class),
      @XmlElement(name = "flask", type = Flask.class),
      @XmlElement(name = "tool", type = Tool.class),
      @XmlElement(name = "weapon", type = Weapon.class) })
  public void setBag(List<Item> bag) {
    this.bag = bag;
  }

  /**
   * Add the object to the players bag.
   *
   * @param obj the object to add to the bag
   */
  public void addToBag(Item obj) {
    if (obj.getWeight() + currentWeight <= MAX_WEIGHT) {
      this.bag.add(obj);
    }
  }

  /**
   * Gets the health.
   *
   * @return the health
   */
  public int getHealth() {
    return health;
  }

  /**
   * Sets the Health.
   *
   * @param health the health to set
   */
  @XmlElement(name = "health")
  public void setHealth(int health) {
    this.health = health;
  }

  /**
   * Called when a player uses a health potion. Gives the player 20 health.
   */
  public void giveHealth() {
    health += 20;
    if (health > 100) {
      health = 100;
    }
  }

  /**
   * Picks up the item clicked on.
   *
   * @param item item to pick up
   */
  public void pickUp(Item item) {
    this.bag.add(item);
  }

  /**
   * Drops the item selected in the hot bar.
   *
   * @param item item to be dropped.
   */
  public void dropItem(Item item) {
    this.bag.remove(item);
  }

  /**
   * Returns true if the player has a weapon in their bag.
   *
   * @return a boolean
   */
  public boolean hasWeapon() {
    for (FloorObject obj : bag) {
      if (obj instanceof Weapon) {
        return true;
      }
    }
    return false;
  }

  /**
   * Returns true if the player has a Tool in their bag.
   *
   * @return
   */
  public boolean hasTool() {
    for (FloorObject obj : bag) {
      if (obj instanceof Tool) {
        return true;
      }
    }
    return false;
  }

  /**
   * Returns the weapon the player in holding.
   *
   * @return a boolean
   */
  public Weapon getWeapon() {
    for (FloorObject obj : bag) {
      if (obj instanceof Weapon) {
        return (Weapon) obj;
      }
    }
    return null;
  }

  /**
   * Returns the tool the player in holding.
   *
   * @return the players tool
   */
  public Tool getTool() {
    for (FloorObject obj : bag) {
      if (obj instanceof Tool) {
        return (Tool) obj;
      }
    }
    return null;
  }

  private boolean hasEmptyFlask() {
    for (FloorObject obj : bag) {
      if (obj instanceof Flask && ((Flask) obj).isEmpty()) {
        return true;
      }
    }
    return false;
  }

  private boolean hasPowerFlask() {
    for (FloorObject obj : bag) {
      if (obj instanceof Flask && ((Flask) obj).isEmpty()) {
        return true;
      }
    }
    return false;
  }

  private Flask getEmptyFlask() {
    for (FloorObject obj : bag) {
      if (obj instanceof Flask && ((Flask) obj).isEmpty()) {
        return (Flask) obj;
      }
    }
    return null;
  }

  /**
   * fills the first empty flask with the liquid from the fountain.
   *
   * @param fountain the fountain to fill from
   */
  public void fill(String fountain) {
    if (hasEmptyFlask()) {
      getEmptyFlask().fill(fountain);
    }
  }

  /**
   * returns the current weight.
   *
   * @return the currentWeight
   */
  public int getCurrentWeight() {
    return currentWeight;
  }

  /**
   * sets the current weight.
   *
   * @param currentWeight the currentWeight to set
   */
  @XmlElement(name = "weight")
  public void setCurrentWeight(int currentWeight) {
    this.currentWeight = currentWeight;
  }

  /**
   * Determine whether the player used a power potion within
   * the past 10 seconds.
   * @return true if the player drank a power potion within 10 secs ago
   */
  public boolean isStrengthened() {
    double newTime = System.currentTimeMillis() - time;
    return (newTime > 0.0 && newTime < 10000.0);
  }

  public double getTime() {
    return time;
  }

  public void setTime(double time) {
    this.time = time;
  }
}
