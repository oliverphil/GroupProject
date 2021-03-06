package gameworld.barriers;

import gameworld.FloorObject;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Obstacles blocking doors, removed using tools.
 * @author Dylan Ewens - ewensdyla 300423748
 *
 */
@XmlRootElement
public class Barrier extends FloorObject {

  private BarrierStrategy strat;

  /**
   * Constructs a new Barrier without a Strategy.
   */
  public Barrier() {
    strat = null;
  }

  public BarrierStrategy getStrat() {
    return strat;
  }

  public String toString() {
    return this.getName();
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
    Barrier other = (Barrier) obj;
    if (strat == null) {
      if (other.strat != null) {
        return false;
      }
    }
    return true;
  }
}
