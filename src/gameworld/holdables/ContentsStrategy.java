package gameworld.holdables;

import gameworld.Player;

/**
 * This object facilitates the use of the strategy pattern to determine the 
 * contents of flask objects.
 * @author Dylan Ewens - ewensdyla 300423748
 *
 */
public abstract class ContentsStrategy {
  public abstract void use(Player pl);
}
