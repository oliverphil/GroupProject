package gameworld;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Fountains can be interacted with for power-ups.
 * 
 * @author Dylan Ewens - ewensdyla 300423748
 *
 */
@XmlRootElement
public class Fountain extends FloorObject {

  private String liquid;

  public String getLiquid() {
    return liquid;
  }

  @XmlElement
  public void setLiquid(String liquid) {
    this.liquid = liquid;
  }

  public String toString() {
    return this.getName();
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = super.hashCode();
    result = prime * result + ((liquid == null) ? 0 : liquid.hashCode());
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
    Fountain other = (Fountain) obj;
    if (liquid == null) {
      if (other.liquid != null) {
        return false;
      }
    } else if (!liquid.equals(other.liquid)) {
      return false;
    }
    return true;
  }
}
