package gameworld.barriers;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ChainsStrategy extends BarrierStrategy {

  @Override
  public String tool() {
    return "boltCutters";
  }
}
