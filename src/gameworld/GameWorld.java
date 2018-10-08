package gameworld;

import gameworld.holdables.Flask;
import gameworld.holdables.Tool;
import java.util.Observable;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import renderer.Renderer.ItemOnScreen;

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
  }

  /**
   * Returns the current ViewDescriptor.
   *
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
   * Called when the forward button is pressed. The player moves forward in the direction they're
   * facing.
   */
  public void moveForward() {
    board.goForwards(this.player);
    update();
  }

  /**
   * Called when the back button is pressed. The player moves in the opposite direction they're
   * facing.
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
  public void interact(ItemOnScreen name) {

    switch (name.toString()) {
      case "door":
        openDoor();
        break;

      //Items
      case "emptyFlask":
        player.pickUp(new Flask());
        // removeItem
        break;
      case "powerFlask":
        Flask pf = new Flask();
        pf.fill("power");
        player.pickUp(pf);
        // removeItem
        break;
      case "healthFlask":
        Flask hf = new Flask();
        hf.fill("health");
        player.pickUp(hf);
        // removeItem
        break;

      //tools
      case "crowbar":
        if (player.hasTool()) {
          Tool cb = new Tool();
          cb.setMaterial("woodenBlockade");
          Tool tool = player.getTool();
          player.pickUp(cb);
          player.dropItem(tool);

        } else {
          Tool cb = new Tool();
          cb.setMaterial("woodenBlockade");
          player.pickUp(cb);
        }
        break;
      case "pickaxe":
        if (player.hasTool()) {
          Tool pa = new Tool();
          pa.setMaterial("stoneBlockade");
          Tool tool = player.getTool();
          player.pickUp(pa);
          player.dropItem(tool);
          // remove Item from tile but add something else
        } else {
          Tool pa = new Tool();
          pa.setMaterial("stoneBlockade");
          player.pickUp(pa);
        }
        break;
      case "boltCutters":
        if (player.hasTool()) {
          Tool bc = new Tool();
          bc.setMaterial("chainBlockade");
          Tool tool = player.getTool();
          player.pickUp(bc);
          player.dropItem(tool);
          // remove Item from tile but add something else
        } else {
          Tool bc = new Tool();
          bc.setMaterial("chainBlockade");
          player.pickUp(bc);
        }
        break;

      //Barriers
      case "woodenBlockade":
        if (player.hasTool()) {
          if (player.getTool().getMaterial().equals("woodenBlockade")) {

            board.removeBarrier(player);
          }
        }
        break;
      case "stoneBlockade":
        if (player.hasTool()) {
          if (player.getTool().getMaterial().equals("stoneBlockade")) {
            System.out.println(player.getTool().getName());
            board.removeBarrier(player);
          }
        }
        break;
      case "chainBlockade":
        if (player.hasTool()) {
          if (player.getTool().getMaterial().equals("chainBlockade")) {
            board.removeBarrier(player);
          }
        }
        break;
      default:
        break;
    }
    update();
  }

  @XmlElement(name = "player")
  public void setPlayer(Player player) {
    this.player = player;
  }

  @XmlElement(name = "board")
  public void setBoard(Board board) {
    this.board = board;
  }

  /**
   * Returns the current player.
   *
   * @return the current player
   */
  public Player getPlayer() {
    return player;
  }

  /**
   * Returns the current board.
   *
   * @return the current board
   */
  public Board getBoard() {
    return board;
  }

  /**
   * Called when the player clicks on the door in front of them.
   */
  public void openDoor() {
    board.openDoor(player);
    update();
  }

  @Override
  public boolean equals(Object o) {
    if (o instanceof GameWorld) {
      GameWorld other = (GameWorld) o;
      return this.player.equals(other.getPlayer()) && this.board.equals(other.getBoard());
    }
    return false;
  }
}
