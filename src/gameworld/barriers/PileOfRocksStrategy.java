package gameworld.barriers;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class PileOfRocksStrategy extends BarrierStrategy {

  @Override
  public String tool() {
    return "pickaxe";
  }

}
