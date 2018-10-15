package gameworld;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * The object that appears when you defeat David.
 * @author Dylan Ewens - ewensdyla 300423748
 *
 */
@XmlRootElement
public class Ladder extends FloorObject {

  public Ladder() {
    this.setName("ladder");
  }
  
  public String toString() {
    return this.getName();
  }
}
