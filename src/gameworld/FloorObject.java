package gameworld;

import java.awt.Point;

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
   * @param name the name of the FloorObject
   */
  public FloorObject(String name, Point p) {
    setName(name);
    setLocation(p);
  }

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
  public void setLocation(Point location) {
    this.location = location;
  }

}
