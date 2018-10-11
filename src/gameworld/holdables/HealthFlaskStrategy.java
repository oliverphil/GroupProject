package gameworld.holdables;

import gameworld.Player;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
class HealthFlaskStrategy extends ContentsStrategy {

  @Override
  public void use(Player pl) {
    pl.giveHealth();
  }
  
  public String toString() {
    return "healthFlask";
  }
}