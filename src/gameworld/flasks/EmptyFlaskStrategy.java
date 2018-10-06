package gameworld.flasks;

import javax.xml.bind.annotation.XmlRootElement;

import gameworld.Player;
import gameworld.flasks.Flask.ContentsStrategy;

@XmlRootElement
class EmptyFlaskStrategy implements ContentsStrategy {

  @Override
  public void use(Player pl) {
    //do nothing
  }

}