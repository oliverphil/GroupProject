package gameWorld;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * A Character is a person or animal within the game but not necessarily a controlled player
 * 
 * @author Dylan
 *
 */
@XmlRootElement
public abstract class Character {
  @XmlAttribute(name = "name")
  private String name;

  /**
   * Character objects can be either played or unplayable
   * 
   * @param name
   */
  public Character(String name) {
    setName(name);
  }

  public Character() {
    setName("");
  }

  /**
   * @return the name
   */
  public String getName() {
    return name;
  }

  /**
   * @param name the name to set
   */
  public void setName(String name) {
    this.name = name;
  }

}