package gameworld.flasks;

import gameworld.Player;
import gameworld.flasks.Flask.ContentsStrategy;

class EmptyFlaskStrategy implements ContentsStrategy {

  @Override
  public void use(Player pl) {
    //do nothing
  }

}