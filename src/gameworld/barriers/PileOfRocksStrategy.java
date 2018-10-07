package gameworld.barriers;

import gameworld.barriers.Barrier.BarrierStrategy;

public class PileOfRocksStrategy implements BarrierStrategy {

  @Override
  public String tool() {
    return "pickaxe";
  }

}
