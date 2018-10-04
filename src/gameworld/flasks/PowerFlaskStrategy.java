package gameworld.flasks;

import gameworld.Player;
import gameworld.flasks.Flask.ContentsStrategy;

class PowerFlaskStrategy implements ContentsStrategy {

  @Override
  public void use(Player pl) {
    pl.giveHealth();
  }

}
