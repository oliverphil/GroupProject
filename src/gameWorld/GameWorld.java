package gameWorld;

import java.util.Observable;

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
public class GameWorld extends Observable {

  Player player;
  Board board;

  /**
   * Constructs the GameWorld object, allowing you to play the game.
   */
  public GameWorld() {
    player = new Player();
    board = new Board();
    player.setView(new ViewDescriptor(player, board));

//    walk to David
//    rotateLeft();
//    rotateLeft();
//    board.openDoor(player);
//    moveForward();
//    board.openDoor(player);
//    moveForward();
//    rotateRight();
//    board.openDoor(player);
//    moveForward();
//    board.openDoor(player);
//    moveForward();
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
    // whenever a view changes use update();
    player.setView(new ViewDescriptor(player, board));
    setChanged();
    notifyObservers(getViewDescriptor());
  }

  /**
   * Called when the forward button is pressed.
   * The player moves forward in the direction they're facing.
   */
  public void moveForward() {
    board.goForwards(this.player);
    update();
  }

  /**
   * Called when the back button is pressed.
   * The player moves in the opposite direction they're facing.
   */
  public void moveBackwards() {
    board.goBack(this.player);
    update();
  }

  /**
   * Rotates the player 90 degrees to the right.
   */
  public void rotateRight() {
    player.turnRight();
    update();
  }

  /**
   * Rotates the player 90 degrees to the left.
   */
  public void rotateLeft() {
    player.turnLeft();
    update();
  }

  /**
   * Called on click, passes the image clicked on.
   */
  public void interact(String name) {

  }

  @XmlElement(name = "player")
  public void setPlayer(Player player) {
    this.player = player;
  }

  @XmlElement(name = "board")
  public void setBoard(Board board) {
    this.board = board;
  }

  public Player getPlayer() {
    return player;
  }

  public Board getBoard() {
    return board;
  }



}
