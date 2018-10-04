package gameWorld;

import gameWorld.Flask.ContentsStrategy;

public class Flask extends Item{

  ContentsStrategy strat;

  public Flask() {
    strat = new EmptyFlaskStrategy();
  }

  public void use(Player pl) {
    strat.use(pl);
    if (strat instanceof HealthFlaskStrategy || strat instanceof HealthFlaskStrategy) {
      strat = new EmptyFlaskStrategy();
    }
  }

  public void fill(String fountain) {
    if (fountain.equals("health") && strat instanceof EmptyFlaskStrategy) {
      strat = new HealthFlaskStrategy();
    }
    else if (fountain.equals("power") && strat instanceof EmptyFlaskStrategy) {
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
    // do nothing
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