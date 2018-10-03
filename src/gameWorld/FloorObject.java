package gameWorld;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.awt.Point;

/**
 * Items are objects found in the game world that can be weapons or tools to help a player along
 * their journey.
 * 
 * @author ewensdyla
 *
 */
@XmlRootElement
public abstract class FloorObject {
  @XmlAttribute(name = "name")
  private String name;
  @XmlElement(name = "location")
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
