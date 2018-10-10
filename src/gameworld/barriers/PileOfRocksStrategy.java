package gameworld.barriers;

import gameworld.barriers.Barrier.BarrierStrategy;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class PileOfRocksStrategy implements BarrierStrategy {

  @Override
  public String tool() {
    return "pickaxe";
  }

}
