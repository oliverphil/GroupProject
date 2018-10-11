package gameworld.holdables;

import gameworld.Player;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class EmptyFlaskStrategy extends ContentsStrategy {

  @Override
  public void use(Player pl) {
    //do nothing
  }
  
  public String toString() {
    return "emptyFlask";
  }
}