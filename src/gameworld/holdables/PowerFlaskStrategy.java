package gameworld.holdables;

import gameworld.Player;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * This object deals with power flasks.
 * @author Dylan Ewens - ewensdyla 300423748
 *
 */
@XmlRootElement
public class PowerFlaskStrategy extends ContentsStrategy {

  @Override
  public void use(Player pl) {
    pl.setTime((double) System.currentTimeMillis());
  }

  public String toString() {
    return "powerFlask";
  }
}
