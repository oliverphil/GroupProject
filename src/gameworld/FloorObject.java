package gameworld;

import javax.xml.bind.annotation.XmlElement;

/**
 * Items are objects found in the game world that can be weapons or tools to help a player along
 * their journey.
 *
 * @author Dylan Ewens - ewensdyla 300423748
 *
 */
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
  @XmlElement
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

  @XmlElement
  public void setLocation(Point location) {
    this.location = location;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((location == null) ? 0 : location.hashCode());
    result = prime * result + ((name == null) ? 0 : name.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    FloorObject other = (FloorObject) obj;
    if (location == null) {
      if (other.location != null) {
        return false;
      }
    } else if (!location.equals(other.location)) {
      return false;
    }
    if (name == null) {
      if (other.name != null) {
        return false;
      }
    } else if (!name.equals(other.name)) {
      return false;
    }
    return true;
  }

}
