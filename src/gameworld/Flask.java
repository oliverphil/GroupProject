package gameworld;

import gameworld.Flask.ContentsStrategy;

/**
 * A Flask is a multi-use item that can be filled with different liquids.
 * This uses the ***STRATEGY PATTERN***.
 * @author ewensdyla
 *
 */
public class Flask extends Item {

  ContentsStrategy strat;

  /**
   * Constructs a new Flask open with the empty Strategy.
   */
  public Flask() {
    strat = new EmptyFlaskStrategy();
  }

  /**
   * Called by the Player to use the selected item.
   * @param pl player
   */
  public void use(Player pl) {
    strat.use(pl);
    if (strat instanceof HealthFlaskStrategy || strat instanceof PowerFlaskStrategy) {
      strat = new EmptyFlaskStrategy();
    }
  }

  /**
   * Fills the selected flask with liquid from the chosen fountain.
   * @param fountain type of fountain
   */
  public void fill(String fountain) {
    if (fountain.equals("health") && strat instanceof EmptyFlaskStrategy) {
      strat = new HealthFlaskStrategy();
    } else if (fountain.equals("power") && strat instanceof EmptyFlaskStrategy) {
      strat = new PowerFlaskStrategy();
    }
  }

  interface ContentsStrategy {
    public void use(Player pl);
  }
}

class EmptyFlaskStrategy implements ContentsStrategy{

  @Override
  public void use(Player pl) {
    //do nothing
  }

}

class PowerFlaskStrategy implements ContentsStrategy{

  @Override
  public void use(Player pl) {
    pl.giveHealth();
  }

}

class HealthFlaskStrategy implements ContentsStrategy{

  @Override
  public void use(Player pl) {
    // TODO increase player damage or resistence

  }

}