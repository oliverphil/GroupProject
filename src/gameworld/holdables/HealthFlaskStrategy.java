package gameworld.holdables;

import javax.xml.bind.annotation.XmlRootElement;

import gameworld.Player;
import gameworld.holdables.Flask.ContentsStrategy;

@XmlRootElement
class HealthFlaskStrategy implements ContentsStrategy {

  @Override
  public void use(Player pl) {
    pl.giveHealth();
  }
  
  public String toString() {
    return "healthFlask";
  }
}