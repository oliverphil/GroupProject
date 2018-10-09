package gameworld.holdables;

import javax.xml.bind.annotation.XmlRootElement;

import gameworld.Player;
import gameworld.holdables.Flask.ContentsStrategy;

@XmlRootElement
class PowerFlaskStrategy implements ContentsStrategy {

  @Override
  public void use(Player pl) {
    // TODO increase player damage or resistence
  }
  
  public String toString() {
    return "powerFlask";
  }
}
