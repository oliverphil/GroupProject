package gameworld;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Ladder extends FloorObject {

  public Ladder() {
    this.setName("ladder");
  }
}
