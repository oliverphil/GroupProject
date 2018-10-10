package gameworld.holdables;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Explosive extends Item {

  public Explosive() {
    setWeight(3);
  }
  
  public String toString() {
    return this.getName();
  }
}
