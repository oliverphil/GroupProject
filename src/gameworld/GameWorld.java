package gameworld;

import gameworld.barriers.Barrier;
import gameworld.holdables.Explosive;
import gameworld.holdables.Flask;
import gameworld.holdables.Item;
import gameworld.holdables.Tool;
import gameworld.holdables.Weapon;

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

  private boolean won;
  private boolean playerAlive;

  private Player player;
  private Board board;

  /**
   * Constructs the GameWorld object, allowing you to play the game.
   */
  public GameWorld() {
    player = new Player();
    board = new Board();
    player.setView(new ViewDescriptor(player, board, won));

    setWon(false);
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
    player.setView(new ViewDescriptor(player, board, won));
    setChanged();
    notifyObservers(getViewDescriptor());
  }

  /**
   * Called when the forward button is pressed. The player moves forward in the direction they're
   * facing.
   */
  public void moveForward() {
    board.goForwards(this.player, won);
    resetMonsterHealth();
    update();
  }

  /**
   * Called when the back button is pressed. The player moves in the opposite direction they're
   * facing.
   */
  public void moveBackwards() {
    board.goBack(this.player, won);
    resetMonsterHealth();
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

      //bosses
      case "david":
        attack("david");
        break;
      case "marco":
        attack("marco");
        break;
      case "thomas":
        attack("thomas");
        break;

      // Items
      case "emptyFlask":
        player.pickUp(new Flask());
        board.removeObject(player, name.getTile());
        break;
      case "powerFlask":
        Flask pf = new Flask();
        pf.fill("power");
        player.pickUp(pf);
        board.removeObject(player, name.getTile());
        break;
      case "healthFlask":
        Flask hf = new Flask();
        hf.fill("health");
        player.pickUp(hf);
        board.removeObject(player, name.getTile());
        break;

      // tools
      case "crowbar":
        if (player.hasTool()) {
          Tool cb = new Tool();
          cb.setMaterial("woodenBlockade");
          cb.setName("crowbar");
          Tool tool = player.getTool();
          player.pickUp(cb);
          player.dropItem(tool);
          board.removeObject(player, name.getTile());
          board.place(player, tool, name.getTile());
        } else {
          Tool cb = new Tool();
          cb.setMaterial("woodenBlockade");
          cb.setName("crowbar");
          player.pickUp(cb);
          board.removeObject(player, name.getTile());
        }
        break;
      case "pickaxe":
        if (player.hasTool()) {
          Tool pa = new Tool();
          pa.setMaterial("stoneBlockade");
          pa.setName("pickaxe");
          Tool tool = player.getTool();
          player.pickUp(pa);
          player.dropItem(tool);
          board.removeObject(player, name.getTile());
          board.place(player, tool, name.getTile());
        } else {
          Tool pa = new Tool();
          pa.setMaterial("stoneBlockade");
          pa.setName("pickaxe");
          player.pickUp(pa);
          board.removeObject(player, name.getTile());
        }
        break;
      case "boltCutters":
        if (player.hasTool()) {
          Tool bc = new Tool();
          bc.setMaterial("chainBlockade");
          bc.setName("boltCutters");
          Tool tool = player.getTool();
          player.pickUp(bc);
          player.dropItem(tool);
          board.removeObject(player, name.getTile());
          board.place(player, tool, name.getTile());
        } else {
          Tool bc = new Tool();
          bc.setMaterial("chainBlockade");
          bc.setName("boltCutters");
          player.pickUp(bc);
          board.removeObject(player, name.getTile());
        }
        break;

      // Weapons
      case "hammer":
        if (player.hasWeapon()) {
          Weapon hm = new Weapon();
          hm.setDamage(10);
          hm.setName("hammer");
          Weapon weap = player.getWeapon();
          player.pickUp(hm);
          player.dropItem(weap);
          board.removeObject(player, name.getTile());
          board.place(player, weap, name.getTile());
        } else {
          Weapon hm = new Weapon();
          hm.setDamage(10);
          hm.setName("hammer");
          player.pickUp(hm);
          board.removeObject(player, name.getTile());
        }
        break;
      case "torch":
        if (player.hasWeapon()) {
          Weapon tr = new Weapon();
          tr.setDamage(15);
          tr.setName("torch");
          Weapon weap = player.getWeapon();
          player.pickUp(tr);
          player.dropItem(weap);
          board.removeObject(player, name.getTile());
          board.place(player, weap, name.getTile());
        } else {
          Weapon tr = new Weapon();
          tr.setDamage(15);
          tr.setName("torch");
          player.pickUp(tr);
          board.removeObject(player, name.getTile());
        }
        break;
      case "khopesh":
        if (player.hasWeapon()) {
          Weapon kp = new Weapon();
          kp.setDamage(20);
          kp.setName("khopesh");
          Weapon weap = player.getWeapon();
          player.pickUp(kp);
          player.dropItem(weap);
          board.removeObject(player, name.getTile());
          board.place(player, weap, name.getTile());
        } else {
          Weapon kp = new Weapon();
          kp.setDamage(20);
          kp.setName("khopesh");
          player.pickUp(kp);
          board.removeObject(player, name.getTile());
        }
        break;

      // Barriers
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

      //fountains
      case "powerFountain":
        player.fill("power");
        break;
      case "healthFountain":
        player.fill("health");
        break;

        //game won
      case "ladder":
        win();
        break;
      default:
        break;
    }
    update();
  }

  private void win() {
    setChanged();
    notifyObservers("won");
  }

  /**
   * Called by the interact method when the player is fighting a boss.
   * @param name the name of the boss to fight
   */
  private void attack(String boss) {

    Weapon weap = player.getWeapon();

    switch (boss) {
      case "david":
        Monster dave = (Monster) this.board.getBoard()[13][0].getObj();

        if (weap != null) {
          if (weap.getName().equals("khopesh")) {
            dave.removeHealth(50);
          } else {
            dave.removeHealth(weap.getDamage());
          }
        } else {
          dave.removeHealth(5); //unarmed combat
        }
        if (dave.getHealth() > 0) {
          player.setHealth(player.getHealth() - dave.getDamage());
        } else {
          this.board.getBoard()[13][0].setObj(null);
          this.board.getBoard()[13][0].setObj(new Ladder());
          setWon(true);
        }
        break;

      case "marco":
        Monster marco = (Monster) this.board.getBoard()[0][1].getObj();

        if (weap != null) {
          if (weap.getName().equals("torch")) {
            marco.removeHealth(50);
          } else {
            marco.removeHealth(weap.getDamage());
          }
        } else {
          marco.removeHealth(5); //unarmed combat
        }
        if (marco.getHealth() > 0) {
          player.setHealth(player.getHealth() - marco.getDamage());
        } else {
          this.board.getBoard()[0][1].setObj(null);
        }
        break;

      case "thomas":
        Monster thomas = (Monster) this.board.getBoard()[1][14].getObj();

        if (weap != null) {
          if (weap.getName().equals("hammer")) {
            thomas.removeHealth(50);
          } else {
            thomas.removeHealth(weap.getDamage());
          }
        } else {
          thomas.removeHealth(5); //unarmed combat
        }
        if (thomas.getHealth() > 0) {
          player.setHealth(player.getHealth() - thomas.getDamage());
        } else {
          this.board.getBoard()[1][14].setObj(null);
        }
        break;
      default:
        break;
    }

    if (player.getHealth() < 1) {
      setPlayerAlive(false);
      setChanged();
      notifyObservers("dead");
    }

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
   * Uses the item that is selected on the players hot bar.
   * @param item the item to be used.
   */
  public void useItem(Item item) {
    item.use(player, board.getfacingTile(player));

    if (board.getfacingTile(player).getObj().getName().equals("ladder")) {
      setWon(true);
    }
  }

  /**
   * Drops the item that is selected on the players hot bar.
   * @param item the item to be dropped.
   */
  public void dropItem(Item item) {
    board.dropItem(player, item);
  }

  /**
   * Called when the player clicks on the door in front of them.
   */
  public void openDoor() {
    board.openDoor(player);
    update();
  }

  private void resetMonsterHealth() {
    if (!won) {
      Monster david = (Monster) board.getBoard()[13][0].getObj();
      david.setHealth(250);
      Monster marco = (Monster) board.getBoard()[0][1].getObj();
      marco.setHealth(250);
      Monster thomas = (Monster) board.getBoard()[1][14].getObj();
      thomas.setHealth(250);
    }
  }

  @Override
  public boolean equals(Object o) {
    if (o instanceof GameWorld) {
      GameWorld other = (GameWorld) o;
      return this.player.equals(other.getPlayer()) && this.board.equals(other.getBoard());
    }
    return false;
  }

  public boolean isWon() {
    return won;
  }

  @XmlElement
  public void setWon(boolean won) {
    this.won = won;
  }

  public boolean isPlayerAlive() {
    return playerAlive;
  }

  public void setPlayerAlive(boolean playerAlive) {
    this.playerAlive = playerAlive;
  }
}
