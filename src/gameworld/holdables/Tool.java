package gameworld.holdables;

import javax.xml.bind.annotation.XmlElement;

public class Tool extends Item {
  private String material;

  /**
   * Gets the material that this tool clears.
   * @return the material
   */
  public String getMaterial() {
    return material;
  }

  /**
   * Sets the material that this tool clears.
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
}
