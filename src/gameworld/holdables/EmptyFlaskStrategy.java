package gameworld.holdables;

import gameworld.Player;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * This object deals with empty flasks.
 * @author Dylan Ewens - ewensdyla 300423748
 *
 */
@XmlRootElement
public class EmptyFlaskStrategy extends ContentsStrategy {

  @Override
  public void use(Player pl) {
    //do nothing
  }
  
  public String toString() {
    return "emptyFlask";
  }
}