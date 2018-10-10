package gameworld.barriers;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class WoodenPlanksStrategy extends BarrierStrategy {

  @Override
  public String tool() {
    return "crowbar";
  }

}
