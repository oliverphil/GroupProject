package gameworld.flasks;

import javax.xml.bind.annotation.XmlRootElement;

import gameworld.Player;
import gameworld.flasks.Flask.ContentsStrategy;

@XmlRootElement
class HealthFlaskStrategy implements ContentsStrategy {

  @Override
  public void use(Player pl) {
    // TODO increase player damage or resistence

  }

}