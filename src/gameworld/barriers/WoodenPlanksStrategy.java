package gameworld.barriers;

import javax.xml.bind.annotation.XmlRootElement;

import gameworld.barriers.Barrier.BarrierStrategy;

@XmlRootElement
public class WoodenPlanksStrategy implements BarrierStrategy {

  @Override
  public String tool() {
    return "crowbar";
  }

}
