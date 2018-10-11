package gameworld.holdables;

import gameworld.Player;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
class PowerFlaskStrategy extends ContentsStrategy {

  @Override
  public void use(Player pl) {
    // TODO increase player damage or resistence
  }
  
  public String toString() {
    return "powerFlask";
  }
}
