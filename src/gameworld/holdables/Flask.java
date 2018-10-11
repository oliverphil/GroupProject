package gameworld.holdables;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import gameworld.Player;
import gameworld.Tile;
import gameworld.barriers.ChainsStrategy;

/**
 * A Flask is a multi-use item that can be filled with different liquids. This uses the ***STRATEGY
 * PATTERN***.
 *
 * @author ewensdyla
 *
 */
@XmlRootElement
public class Flask extends Item {

  ContentsStrategy strat;

  /**
   * Constructs a new Flask open with the empty Strategy.
   */
  public Flask() {
    strat = new EmptyFlaskStrategy();
  }

  /**
   * Called by the Player to use the selected item.
   *
   * @param p player
   */
  public void use(Player p, Tile tile) {
    strat.use(p);
    if (strat instanceof HealthFlaskStrategy || strat instanceof PowerFlaskStrategy) {
      strat = new EmptyFlaskStrategy();
    }
    this.setWeight(1);
  }

  /**
   * Fills the selected flask with liquid from the chosen fountain.
   *
   * @param fountain type of fountain
   */
  public void fill(String fountain) {
    if (fountain.equals("health") && strat instanceof EmptyFlaskStrategy) {
      strat = new HealthFlaskStrategy();
    } else if (fountain.equals("power") && strat instanceof EmptyFlaskStrategy) {
      strat = new PowerFlaskStrategy();
    }
    this.setWeight(3);
  }

  /**
   * Returns true if the Flask is empty.
   * 
   * @return true or false
   */
  public boolean isEmpty() {
    return strat instanceof EmptyFlaskStrategy;
  }

  public ContentsStrategy getStrat() {
    return strat;
  }

  @XmlElements({ @XmlElement(name = "emptyStrat", type = EmptyFlaskStrategy.class),
      @XmlElement(name = "powerStrat", type = PowerFlaskStrategy.class),
      @XmlElement(name = "healthStrat", type = HealthFlaskStrategy.class), })
  public void setStrat(ContentsStrategy strat) {
    this.strat = strat;
  }

  public String toString() {
    return strat.toString();
  }
}
