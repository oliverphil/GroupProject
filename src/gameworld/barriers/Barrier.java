package gameworld.barriers;

import gameworld.FloorObject;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

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
   * Returns the tool required to break the Barrier.
   * 
   * @return
   */
  public String requiredTool() {
    return strat.tool();
  }

  public BarrierStrategy getStrat() {
    return strat;
  }

  /**
   * Sets the current Strategy.
   * 
   * @param strat the Strategy
   */
  @XmlElements({ @XmlElement(name = "chainStrat", type = ChainsStrategy.class),
      @XmlElement(name = "pileOfRocksStrat", type = PileOfRocksStrategy.class),
      @XmlElement(name = "woodenPlanksStrat", type = WoodenPlanksStrategy.class) })
  public void setStrat(BarrierStrategy strat) {
    this.strat = strat;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = super.hashCode();
    result = prime * result + ((strat == null) ? 0 : strat.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (!super.equals(obj)) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    Barrier other = (Barrier) obj;
    if (strat == null) {
      if (other.strat != null) {
        return false;
      }
    }
    return true;
  }
}
