package gameworld.holdables;

import javax.xml.bind.annotation.XmlRootElement;

import gameworld.Player;
import gameworld.holdables.Flask.ContentsStrategy;

@XmlRootElement
class EmptyFlaskStrategy implements ContentsStrategy {

  @Override
  public void use(Player pl) {
    //do nothing
  }

}