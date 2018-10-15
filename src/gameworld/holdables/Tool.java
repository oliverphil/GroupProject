package gameworld.holdables;

import gameworld.Player;
import gameworld.Tile;

import javax.xml.bind.annotation.XmlElement;

/**
 * Used to break through barriers blocking doors.
 * @author Dylan Ewens - ewensdyla 300423748
 *
 */
public class Tool extends Item {
  private String material;

  /**
   * Gets the material that this tool clears.
   * 
   * @return the material
   */
  public String getMaterial() {
    return material;
  }

  /**
   * Sets the material that this tool clears.
   * 
   * @param material the material to set
   */
  @XmlElement
  public void setMaterial(String material) {
    this.material = material;
  }

  /**
   * To string.
   */
  public String toString() {
    return this.getName();
  }

  @Override
  public void use(Player p, Tile tile) {
    // do nothing
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = super.hashCode();
    result = prime * result + ((material == null) ? 0 : material.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (!super.equals(obj)) {
      return false;
    }
    Tool other = (Tool) obj;
    if (material == null) {
      if (other.material != null) {
        return false;
      }
    } else if (!material.equals(other.material)) {
      return false;
    }
    return true;
  }
}
