package gameworld.barriers;

import gameworld.FloorObject;

public class Barrier extends FloorObject {

  private BarrierStrategy strat;

  /**
   * Constructs a new Flask open with the empty Strategy.
   */
  public Barrier() {
    strat = null;
  }

  /**
   * Sets the current Strategy.
   * @param st Strategy
   */
  public void setStrategy(BarrierStrategy st) {
    strat = st;
  }

  public String requireTool() {
    return strat.tool();
  }

  interface BarrierStrategy {
    public String tool();
  }
}
