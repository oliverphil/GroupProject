package gameworld.holdables;

import gameworld.Player;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * This object deals with health flasks.
 * @author Dylan Ewens - ewensdyla 300423748
 *
 */
@XmlRootElement
public class HealthFlaskStrategy extends ContentsStrategy {

  @Override
  public void use(Player pl) {
    pl.giveHealth();
  }
  
  public String toString() {
    return "healthFlask";
  }
}