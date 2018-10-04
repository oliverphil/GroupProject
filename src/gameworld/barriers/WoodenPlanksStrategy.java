package gameworld.barriers;

import gameworld.barriers.Barrier.BarrierStrategy;

public class WoodenPlanksStrategy implements BarrierStrategy {

  @Override
  public String tool() {
    return "crowbar";
  }

}
