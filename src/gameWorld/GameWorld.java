package gameWorld;

import java.util.Observable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * GameWorld class is the API for the game.
 * 
 * @author Dylan
 *
 */
//*******OBSERVER PATTERN*******
@XmlRootElement
@XmlAccessorType(XmlAccessType.NONE)
public class GameWorld extends Observable {

  @XmlElement(name = "player")
  Player player;
  @XmlElement(name = "board")
  Board board;

  /**
   * Constructs the GameWorld object, allowing you to play the game.
   */
  public GameWorld() {
    player = new Player();
    board = new Board();
    player.setView(new ViewDescriptor(player, board));
  }

  /**
   * Returns the current ViewDescriptor.
   * @return the current ViewDescriptor of the player
   */
  public ViewDescriptor getViewDescriptor() {
    return player.getView();
  }

  /**
   * Updates the renderer.
   */
  public void update() {
    setChanged();
    notifyObservers(getViewDescriptor());
  }
  // whenever a view changes use update();
}
