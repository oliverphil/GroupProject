package gameworld.barriers;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import gameworld.FloorObject;

@XmlRootElement
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

  @XmlTransient
  interface BarrierStrategy {
    public String tool();
  }

  public BarrierStrategy getStrat() {
    return strat;
  }

  @XmlElement
  public void setStrat(BarrierStrategy strat) {
    this.strat = strat;
  }
}
