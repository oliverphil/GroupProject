package gameworld;

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
    won = false;
    player = new Player();
    board = new Board();
    player.setView(new ViewDescriptor(player, board, won));
    setPlayerAlive(true);

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
    interact(name.toString(), name.getTile());
    update();
  }

  /**
   * Overloading the other interact method for ease of testing.
   * 
   * @param name the name of the object
   * @param tile the tile it is on
   */
  public void interact(String name, int tile) {
    switch (name) {
      case "door":
        openDoor();
        break;

      // bosses
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
        Flask flask = new Flask();
        flask.setWeight(1);
        if (player.pickUp(flask)) {
          board.removeObject(player, tile);
        }
        break;
      case "powerFlask":
        Flask pf = new Flask();
        pf.fill("power");
        if (player.pickUp(pf)) {
          board.removeObject(player, tile);
        }
        break;
      case "healthFlask":
        Flask hf = new Flask();
        hf.fill("health");
        if (player.pickUp(hf)) {
          board.removeObject(player, tile);
        }
        break;

      case "bomb":
        Explosive bomb = new Explosive();
        bomb.setName("bomb");
        bomb.setWeight(3);
        if (player.pickUp(bomb)) {
          board.removeObject(player, tile);
        }
        break;

      // tools
      case "crowbar":
        if (player.hasTool()) {
          Tool cb = new Tool();
          cb.setMaterial("woodenBlockade");
          cb.setName("crowbar");
          cb.setWeight(4);
          Tool tool = player.getTool();
          if (player.pickUp(cb)) {
            player.dropItem(tool);
            board.removeObject(player, tile);
            board.place(player, tool, tile);
          }
        } else {
          Tool cb = new Tool();
          cb.setMaterial("woodenBlockade");
          cb.setName("crowbar");
          cb.setWeight(4);
          if (player.pickUp(cb)) {
            board.removeObject(player, tile);
          }
        }
        break;
      case "pickaxe":
        if (player.hasTool()) {
          Tool pa = new Tool();
          pa.setMaterial("stoneBlockade");
          pa.setName("pickaxe");
          pa.setWeight(5);
          Tool tool = player.getTool();
          if (player.pickUp(pa)) {
            player.dropItem(tool);
            board.removeObject(player, tile);
            board.place(player, tool, tile);
          }
        } else {
          Tool pa = new Tool();
          pa.setMaterial("stoneBlockade");
          pa.setName("pickaxe");
          pa.setWeight(5);
          if (player.pickUp(pa)) {
            board.removeObject(player, tile);
          }
        }
        break;
      case "boltCutters":
        if (player.hasTool()) {
          Tool bc = new Tool();
          bc.setMaterial("chainBlockade");
          bc.setName("boltCutters");
          bc.setWeight(4);
          Tool tool = player.getTool();
          if (player.pickUp(bc)) {
            player.dropItem(tool);
            board.removeObject(player, tile);
            board.place(player, tool, tile);
          }
        } else {
          Tool bc = new Tool();
          bc.setMaterial("chainBlockade");
          bc.setName("boltCutters");
          bc.setWeight(4);
          if (player.pickUp(bc)) {
            board.removeObject(player, tile);
          }
        }
        break;

      // Weapons
      case "hammer":
        if (player.hasWeapon()) {
          Weapon hm = new Weapon();
          hm.setDamage(10);
          hm.setName("hammer");
          hm.setWeight(6);
          Weapon weap = player.getWeapon();
          if (player.pickUp(hm)) {
            player.dropItem(weap);
            board.removeObject(player, tile);
            board.place(player, weap, tile);
          }
        } else {
          Weapon hm = new Weapon();
          hm.setDamage(10);
          hm.setName("hammer");
          hm.setWeight(6);
          if (player.pickUp(hm)) {
            board.removeObject(player, tile);
          }
        }
        break;
      case "torch":
        if (player.hasWeapon()) {
          Weapon tr = new Weapon();
          tr.setDamage(15);
          tr.setName("torch");
          tr.setWeight(2);
          Weapon weap = player.getWeapon();
          if (player.pickUp(tr)) {
            player.dropItem(weap);
            board.removeObject(player, tile);
            board.place(player, weap, tile);
          }
        } else {
          Weapon tr = new Weapon();
          tr.setDamage(15);
          tr.setName("torch");
          if (player.pickUp(tr)) {
            board.removeObject(player, tile);
          }
        }
        break;
      case "khopesh":
        if (player.hasWeapon()) {
          Weapon kp = new Weapon();
          kp.setDamage(20);
          kp.setName("khopesh");
          kp.setWeight(4);
          Weapon weap = player.getWeapon();
          if (player.pickUp(kp)) {
            player.dropItem(weap);
            board.removeObject(player, tile);
            board.place(player, weap, tile);
          }
        } else {
          Weapon kp = new Weapon();
          kp.setDamage(20);
          kp.setName("khopesh");
          kp.setWeight(4);
          if (player.pickUp(kp)) {
            board.removeObject(player, tile);
          }
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

      // fountains
      case "powerFountain":
        player.fill("power");
        break;
      case "healthFountain":
        player.fill("health");
        break;

      // game won
      case "ladder":
        win();
        break;
      default:
        break;
    }
  }

  private void win() {
    setChanged();
    notifyObservers("won");
  }

  /**
   * Called by the interact method when the player is fighting a boss.
   * 
   * @param name the name of the boss to fight
   */
  private void attack(String boss) {

    Weapon weap = player.getWeapon();

    switch (boss) {
      case "david":
        Monster dave = (Monster) this.board.getBoard()[13][0].getObj();

        if (weap != null) {
          if (weap.getName().equals("khopesh")) {
            if (player.isStrengthened()) {
              dave.removeHealth(70);
            } else {
              dave.removeHealth(50);
            }
          } else {
            if (player.isStrengthened()) {
              dave.removeHealth(weap.getDamage() + 20);
            } else {
              dave.removeHealth(weap.getDamage());
            }
          }
          // unarmed combat
        } else {
          if (player.isStrengthened()) {
            dave.removeHealth(25);
          } else {
            dave.removeHealth(5);
          }
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
            if (player.isStrengthened()) {
              marco.removeHealth(70);
            } else {
              marco.removeHealth(50);
            }
          } else {
            if (player.isStrengthened()) {
              marco.removeHealth(weap.getDamage() + 20);
            } else {
              marco.removeHealth(weap.getDamage());
            }
          }
          // unarmed combat
        } else {
          if (player.isStrengthened()) {
            marco.removeHealth(25);
          } else {
            marco.removeHealth(5);
          }
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
            if (player.isStrengthened()) {
              thomas.removeHealth(70);
            } else {
              thomas.removeHealth(50);
            }
          } else {
            if (player.isStrengthened()) {
              thomas.removeHealth(weap.getDamage() + 20);
            } else {
              thomas.removeHealth(weap.getDamage());
            }
          }
          // unarmed combat
        } else {
          if (player.isStrengthened()) {
            thomas.removeHealth(25);
          } else {
            thomas.removeHealth(5);
          }
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
   * 
   * @param item the item to be used.
   */
  public void useItem(Item item) {
    item.use(player, board.getfacingTile(player));

    FloorObject obj = board.getfacingTile(player).getObj();
    if (obj != null) {
      if (obj.getName().equals("ladder")) {
        setWon(true);
      }
    }
    update();
  }

  /**
   * Drops the item that is selected on the players hot bar.
   * 
   * @param item the item to be dropped.
   */
  public void dropItem(Item item) {
    board.dropItem(player, item);
    update();
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
      if (david != null) {
        david.setHealth(250);
      }
      Monster marco = (Monster) board.getBoard()[0][1].getObj();
      if (marco != null) {
        marco.setHealth(250);
      }
      Monster thomas = (Monster) board.getBoard()[1][14].getObj();
      if (thomas != null) {
        thomas.setHealth(250);
      }
    }
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((board == null) ? 0 : board.hashCode());
    result = prime * result + ((player == null) ? 0 : player.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    GameWorld other = (GameWorld) obj;
    if (board == null) {
      if (other.board != null) {
        return false;
      }
    } else if (!board.equals(other.board)) {
      return false;
    }
    if (player == null) {
      if (other.player != null) {
        return false;
      }
    } else if (!player.equals(other.player)) {
      return false;
    }
    return true;
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
