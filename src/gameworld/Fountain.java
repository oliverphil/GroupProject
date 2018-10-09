package gameworld;

public class Fountain extends FloorObject{
  
  private String liquid;

  /**
   * @return the liquid
   */
  public String getLiquid() {
    return liquid;
  }

  /**
   * @param liquid the liquid to set
   */
  public void setLiquid(String liquid) {
    this.liquid = liquid;
  }
  
  public String toString() {
    return this.getName();
  }
}
