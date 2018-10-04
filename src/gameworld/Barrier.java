package gameworld;

public class Barrier extends FloorObject {
  private BarrierStrategy strat;

  /**
   * Constructs a new Flask open with the empty Strategy.
   */
  public Barrier() {
    strat = null;
  }

  public void setStrat(BarrierStrategy st) {
    strat = st;
  }

  interface BarrierStrategy {
    public void breakBarrier();
  }
}
