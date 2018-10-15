package gameworld.holdables;

import gameworld.Player;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class PowerFlaskStrategy extends ContentsStrategy {

  @Override
  public void use(Player pl) {
    pl.setTime((double) System.currentTimeMillis());
  }

  public String toString() {
    return "powerFlask";
  }
}
