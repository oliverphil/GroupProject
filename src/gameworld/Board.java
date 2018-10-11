package gameworld;

import gameworld.barriers.Barrier;
import gameworld.barriers.WoodenPlanksStrategy;
import gameworld.holdables.Explosive;
import gameworld.holdables.Flask;
import gameworld.holdables.Item;
import gameworld.holdables.Tool;
import gameworld.holdables.Weapon;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import gameworld.barriers.ChainsStrategy;
import gameworld.barriers.PileOfRocksStrategy;

@XmlRootElement
public class Board {
  private Tile[][] board;
  @XmlTransient
  public static final int WIDTH = 15;
  @XmlTransient
  public static final int HEIGHT = 15;

  /**
   * This board constructs the standard board, new game board.
   */
  public Board() {
    board = new Tile[HEIGHT][WIDTH];

    initialiseBoard();
    initialiseObjects();
  }

  /**
   * Creates the starting board for the base game, adding all tiles and filling in the walls.
   */
  private void initialiseBoard() {
    for (int row = 0; row < HEIGHT; row++) {
      for (int col = 0; col < WIDTH; col++) {
        Tile tile = new Tile(row, col);
        board[row][col] = tile;

        // add all walls
        if (row % 3 == 0) {
          tile.addWall("north");
        }
        if (col % 3 == 0) {
          tile.addWall("west");
        }
        if (row % 3 == 2) {
          tile.addWall("south");
        }
        if (col % 3 == 2) {
          tile.addWall("east");
        }
      }
    }
    addDoors();

    // start the player in the centre square
    this.board[7][7].setOccupiedByPlayer(true);
  }

  /**
   * Adds all FloorObjects to the board.
   */
  private void initialiseObjects() {
    // Add bosses
    Monster david = new Monster();
    david.setLocation(new Point(0, 13));
    david.setName("david");
    david.setDamage(25);
    david.setHealth(250);
    this.board[13][0].setObj(david);

    Monster marco = new Monster();
    marco.setLocation(new Point(1, 0));
    marco.setName("marco");
    marco.setDamage(20);
    marco.setHealth(200);
    this.board[0][1].setObj(marco);

    Monster thomas = new Monster();
    thomas.setLocation(new Point(14, 1));
    thomas.setName("thomas");
    thomas.setDamage(10);
    thomas.setHealth(100);
    this.board[1][14].setObj(thomas);

    // add flasks
    Flask flask1 = new Flask();
    flask1.setLocation(new Point(8, 6));
    flask1.setName("emptyFlask");
    flask1.setWeight(1);
    this.board[6][8].setObj(flask1);

    Flask flask2 = new Flask();
    flask2.setLocation(new Point(0, 7));
    flask2.setName("emptyFlask");
    flask1.setWeight(1);
    this.board[7][0].setObj(flask2);

    // Add tools
    Tool crowbar = new Tool();
    crowbar.setMaterial("woodenBlockade");
    crowbar.setName("crowbar");
    crowbar.setWeight(4);
    crowbar.setLocation(new Point(11, 5));
    this.board[5][11].setObj(crowbar);

    Tool pickaxe = new Tool();
    pickaxe.setMaterial("stoneBlockade");
    pickaxe.setName("pickaxe");
    crowbar.setWeight(5);
    pickaxe.setLocation(new Point(14, 13));
    this.board[13][14].setObj(pickaxe);

    Tool boltCutters = new Tool();
    boltCutters.setMaterial("chainBlockade");
    boltCutters.setName("boltCutters");
    crowbar.setWeight(3);
    boltCutters.setLocation(new Point(11, 0));
    this.board[0][11].setObj(boltCutters);

    // Add weapons
    Weapon hammer = new Weapon();
    hammer.setName("hammer");
    hammer.setDamage(10);
    crowbar.setWeight(6);
    hammer.setLocation(new Point(4, 0));
    this.board[0][4].setObj(hammer);

    Weapon torch = new Weapon();
    torch.setName("torch");
    torch.setDamage(15);
    crowbar.setWeight(2);
    torch.setLocation(new Point(9, 13));
    this.board[13][9].setObj(torch);

    Weapon sword = new Weapon();
    sword.setName("khopesh");
    sword.setDamage(20);
    crowbar.setWeight(4);
    sword.setLocation(new Point(13, 3));
    this.board[3][13].setObj(sword);

    // Bombs
    Explosive ex1 = new Explosive();
    ex1.setName("bomb");
    ex1.setLocation(new Point(10, 6));
    this.board[6][10].setObj(ex1);

    Explosive ex2 = new Explosive();
    ex2.setName("bomb");
    ex2.setLocation(new Point(0, 11));
    this.board[11][0].setObj(ex2);

    Explosive ex3 = new Explosive();
    ex3.setName("bomb");
    ex3.setLocation(new Point(5, 14));
    this.board[14][5].setObj(ex3);

    // Fountains
    Fountain powerFountain = new Fountain();
    powerFountain.setName("powerFountain");
    powerFountain.setLiquid("power");
    powerFountain.setLocation(new Point(13, 14));
    this.board[14][13].setObj(powerFountain);

    Fountain healthFountain = new Fountain();
    healthFountain.setName("healthFountain");
    healthFountain.setLiquid("healthFountain");
    healthFountain.setLocation(new Point(5, 10));
    this.board[10][5].setObj(healthFountain);

    // Add barriers
    // wooden barriers
    Barrier wBar1 = new Barrier();
    wBar1.setName("woodenBlockade");
    wBar1.setStrat(new WoodenPlanksStrategy());
    wBar1.setLocation(new Point(1, 3));
    this.board[3][1].setObj(wBar1);

    Barrier wBar2 = new Barrier();
    wBar2.setName("woodenBlockade");
    wBar2.setStrat(new WoodenPlanksStrategy());
    wBar2.setLocation(new Point(3, 4));
    this.board[4][3].setObj(wBar2);

    Barrier wBar3 = new Barrier();
    wBar3.setName("woodenBlockade");
    wBar3.setStrat(new WoodenPlanksStrategy());
    wBar3.setLocation(new Point(8, 7));
    this.board[7][8].setObj(wBar3);

    Barrier wBar4 = new Barrier();
    wBar4.setName("woodenBlockade");
    wBar4.setStrat(new WoodenPlanksStrategy());
    wBar4.setLocation(new Point(7, 8));
    this.board[8][7].setObj(wBar4);

    Barrier wBar5 = new Barrier();
    wBar5.setName("woodenBlockade");
    wBar5.setStrat(new WoodenPlanksStrategy());
    wBar5.setLocation(new Point(11, 10));
    this.board[10][11].setObj(wBar5);

    Barrier wBar6 = new Barrier();
    wBar6.setName("woodenBlockade");
    wBar6.setStrat(new WoodenPlanksStrategy());
    wBar6.setLocation(new Point(13, 11));
    this.board[11][13].setObj(wBar6);

    Barrier wBar7 = new Barrier();
    wBar7.setName("woodenBlockade");
    wBar7.setStrat(new WoodenPlanksStrategy());
    wBar7.setLocation(new Point(7, 3));
    this.board[3][7].setObj(wBar7);

    // stone barriers
    Barrier sBar1 = new Barrier();
    sBar1.setName("stoneBlockade");
    sBar1.setStrat(new PileOfRocksStrategy());
    sBar1.setLocation(new Point(8, 1));
    this.board[1][8].setObj(sBar1);

    Barrier sBar2 = new Barrier();
    sBar2.setName("stoneBlockade");
    sBar2.setStrat(new PileOfRocksStrategy());
    sBar2.setLocation(new Point(11, 1));
    this.board[1][11].setObj(sBar2);

    Barrier sBar3 = new Barrier();
    sBar3.setName("stoneBlockade");
    sBar3.setStrat(new PileOfRocksStrategy());
    sBar3.setLocation(new Point(1, 8));
    this.board[8][1].setObj(sBar3);

    Barrier sBar4 = new Barrier();
    sBar4.setName("stoneBlockade");
    sBar4.setStrat(new PileOfRocksStrategy());
    sBar4.setLocation(new Point(2, 10));
    this.board[10][2].setObj(sBar4);

    Barrier sBar5 = new Barrier();
    sBar5.setName("stoneBlockade");
    sBar5.setStrat(new PileOfRocksStrategy());
    sBar5.setLocation(new Point(10, 11));
    this.board[11][10].setObj(sBar5);

    Barrier sBar6 = new Barrier();
    sBar6.setName("stoneBlockade");
    sBar6.setStrat(new PileOfRocksStrategy());
    sBar6.setLocation(new Point(13, 6));
    this.board[6][13].setObj(sBar6);

    // chain
    Barrier chBar1 = new Barrier();
    chBar1.setName("chainBlockade");
    chBar1.setStrat(new ChainsStrategy());
    chBar1.setLocation(new Point(4, 3));
    this.board[3][4].setObj(chBar1);

    Barrier chBar2 = new Barrier();
    chBar2.setName("chainBlockade");
    chBar2.setStrat(new ChainsStrategy());
    chBar2.setLocation(new Point(3, 7));
    this.board[7][3].setObj(chBar2);

    Barrier cBar3 = new Barrier();
    cBar3.setName("chainBlockade");
    cBar3.setStrat(new ChainsStrategy());
    cBar3.setLocation(new Point(7, 11));
    this.board[11][7].setObj(cBar3);

    Barrier cBar4 = new Barrier();
    cBar4.setName("chainBlockade");
    cBar4.setStrat(new ChainsStrategy());
    cBar4.setLocation(new Point(3, 13));
    this.board[13][3].setObj(cBar4);

    Barrier cBar5 = new Barrier();
    cBar5.setName("chainBlockade");
    cBar5.setStrat(new ChainsStrategy());
    cBar5.setLocation(new Point(6, 13));
    this.board[13][6].setObj(cBar5);
  }

  /**
   * Removes the walls and replaces them with doors. It calls the method and adds 1 door at a time.
   */
  private void addDoors() {
    addADoor(board[1][8], "east");
    addADoor(board[1][11], "east");

    addADoor(board[2][1], "south");
    addADoor(board[2][4], "south");
    addADoor(board[2][7], "south");
    addADoor(board[4][2], "east");
    addADoor(board[4][8], "east");

    addADoor(board[5][4], "south");
    addADoor(board[5][7], "south");
    addADoor(board[5][13], "south");
    addADoor(board[7][2], "east");
    addADoor(board[7][5], "east");
    addADoor(board[7][8], "east");
    addADoor(board[7][11], "east");

    addADoor(board[8][1], "south");
    addADoor(board[8][7], "south");
    addADoor(board[8][10], "south");
    addADoor(board[10][2], "east");
    addADoor(board[10][11], "east");

    addADoor(board[11][7], "south");
    addADoor(board[11][10], "south");
    addADoor(board[11][13], "south");
    addADoor(board[13][2], "east");
    addADoor(board[13][5], "east");
  }

  /**
   * Removes a the wall and replaces it with a door (on both sides).
   *
   * @param the tile
   * @param the direction
   */
  private void addADoor(Tile t, String dir) {
    // remove wall and door
    t.removeWall(dir);
    t.addDoor(dir);

    // remove the wall and add a door to the opposing side
    switch (dir) {
      case "east":
        board[t.getRow()][t.getCol() + 1].removeWall("west");
        board[t.getRow()][t.getCol() + 1].addDoor("west");
        break;
      case "south":
        board[t.getRow() + 1][t.getCol()].removeWall("north");
        board[t.getRow() + 1][t.getCol()].addDoor("north");
        break;
      default:
        break;
    }
  }

  /**
   * Opens the door the player is facing.
   *
   * @param pl
   * @return
   */
  public boolean openDoor(Player pl) {
    String dir = pl.getDirection();
    Point pt = pl.getLocation();

    switch (dir) {
      case "north":
        if (board[pt.y - 1][pt.x].hasDoor("north")) {
          board[pt.y - 2][pt.x].removeDoor("south");
          board[pt.y - 1][pt.x].removeDoor("north");
          return true;
        }
        return false;
      case "south":
        if (board[pt.y + 1][pt.x].hasDoor("south")) {
          board[pt.y + 2][pt.x].removeDoor("north");
          board[pt.y + 1][pt.x].removeDoor("south");
          return true;
        }
        return false;
      case "east":
        if (board[pt.y][pt.x + 1].hasDoor("east")) {
          board[pt.y][pt.x + 1].removeDoor("east");
          board[pt.y][pt.x + 2].removeDoor("west");
          return true;
        }
        return false;
      case "west":
        if (board[pt.y][pt.x - 1].hasDoor("west")) {
          board[pt.y][pt.x - 1].removeDoor("west");
          board[pt.y][pt.x - 2].removeDoor("east");
          return true;
        }
        return false;
      default:
        return false;
    }
  }

  /**
   * Gets the board.
   *
   * @return the board
   */
  public Tile[][] getBoard() {
    return board;
  }

  /**
   * Return the tile in the specified position.
   *
   * @param row num
   * @param col num
   * @return
   */
  public Tile getTile(int row, int col) {
    return board[row][col];
  }

  /**
   * Returns the tile the player is facing.
   *
   * @param p player of the game
   * @return a Tile
   */
  public Tile getfacingTile(Player p) {
    // get the players current location
    int dx = p.getLocation().x;
    int dy = p.getLocation().y;

    // use the players current direction to determine the
    // tile they are looking at
    String dir = p.getDirection();

    // change the position depending on whether
    switch (dir) {
      case "north":
        dy -= 1;
        break;
      case "south":
        dy += 1;
        break;
      case "east":
        dx += 1;
        break;
      case "west":
        dx -= 1;
        break;
      default:
        break;
    }

    return board[dy][dx];
  }

  /**
   * Sets the tile with the coords to the specified Tile object.
   *
   * @param row row
   * @param col column
   * @param tile Tile
   */
  public void setTile(int row, int col, Tile tile) {
    board[row][col] = tile;
  }

  /**
   * Moves the player backwards.
   *
   * @param p the current player
   */
  public void goBack(Player p, boolean won) {
    // check if there is a wall or an open door behind them
    String dir = p.getDirection();
    Point point = p.getLocation();
    Tile behind = null;

    // move the player backwards if there is nothing behind them
    switch (dir) {
      case "north":
        behind = board[point.y + 1][point.x];
        if (!behind.hasDoor("south") && !behind.hasWall("south")) {
          p.setLocation(new Point(point.x, point.y + 3));
        } else {
          // cannot move
        }
        break;
      case "south":
        behind = board[point.y - 1][point.x];
        if (!behind.hasDoor("north") && !behind.hasWall("north")) {
          p.setLocation(new Point(point.x, point.y - 3));
        } else {
          ;// cannot move
        }
        break;
      case "east":
        behind = board[point.y][point.x - 1];
        if (!behind.hasDoor("west") && !behind.hasWall("west")) {
          p.setLocation(new Point(point.x - 3, point.y));
        } else {
          ;// cannot move
        }
        break;
      case "west":
        behind = board[point.y][point.x + 1];
        if (!behind.hasDoor("east") && !behind.hasWall("east")) {
          p.setLocation(new Point(point.x + 3, point.y));
        } else {
          ;// cannot move
        }
        break;
      default:
        break;
    }

    // update the player's view.
    p.setView(new ViewDescriptor(p, this, won));
  }

  /**
   * Moves the player forwards.
   *
   * @param p the current player
   * @param won whether the game is won
   */
  public void goForwards(Player p, boolean won) {
    // check if there is a wall or an open door behind them
    String dir = p.getDirection();
    Point point = p.getLocation();
    Tile forward = null;

    // move the player forwards if there is nothing in front them
    switch (dir) {
      case "north":
        forward = board[point.y - 1][point.x];
        if (!forward.hasDoor("north") && !forward.hasWall("north")) {
          p.setLocation(new Point(point.x, point.y - 3));
        } else {
          ;// cannot move
        }
        break;
      case "south":
        forward = board[point.y + 1][point.x];
        if (!forward.hasDoor("south") && !forward.hasWall("south")) {
          p.setLocation(new Point(point.x, point.y + 3));
        } else {
          ;// cannot move
        }
        break;
      case "east":
        forward = board[point.y][point.x + 1];
        if (!forward.hasDoor("east") && !forward.hasWall("east")) {
          p.setLocation(new Point(point.x + 3, point.y));
        } else {
          ;// cannot move
        }
        break;
      case "west":
        forward = board[point.y][point.x - 1];
        if (!forward.hasDoor("west") && !forward.hasWall("west")) {
          p.setLocation(new Point(point.x - 3, point.y));
        } else {
          ;// cannot move
        }
        break;
      default:
        break;
    }

    // update the player's view.
    p.setView(new ViewDescriptor(p, this, won));
  }

  @XmlElementWrapper(name = "board")
  @XmlElement(name = "tile")
  public void setBoard(Tile[][] board) {
    this.board = board;
  }

  public static int getWidth() {
    return WIDTH;
  }

  public static int getHeight() {
    return HEIGHT;
  }

  /**
   * Removes the barrier in front of the player.
   *
   * @param p the current player
   */
  public void removeBarrier(Player p) {
    getfacingTile(p).removeFloorObject();
  }

  /**
   * Called by the GameWorld to place an item on the floor.
   *
   * @param p the current player
   * @param item the FloorObject to place
   * @param location location to place the object
   */
  public void place(Player p, FloorObject item, int location) {
    String dir = p.getDirection();
    Point point = p.getLocation();

    // place the object on the floor in the selected position
    switch (dir) {
      case "north":
        getfacingTile(p).setObj(item);
        break;
      case "south":
        board[point.y + 1][point.x + (2 - location)].setObj(item);
        break;
      case "east":
        board[point.y - (2 - location)][point.x + 1].setObj(item);
        break;
      case "west":
        board[point.y + (2 - location)][point.x - 1].setObj(item);
        break;
      default:
        break;
    }
  }

  /**
   * Removes the object in the specified location.
   *
   * @param p the current player
   * @param location the location of the item in relation to the player
   */
  public void removeObject(Player p, int location) {
    String dir = p.getDirection();
    Point point = p.getLocation();

    // remove the object on the floor in the selected position
    switch (dir) {
      case "north":
        board[point.y - 1][point.x - (2 - location)].setObj(null);
        break;
      case "south":
        board[point.y + 1][point.x + (2 - location)].setObj(null);
        break;
      case "east":
        board[point.y - (2 - location)][point.x + 1].setObj(null);
        break;
      case "west":
        board[point.y + (2 - location)][point.x - 1].setObj(null);
        break;
      default:
        break;
    }
  }

  /**
   * Called when a player selects an item and drops it.
   *
   * @param p the current player
   * @param item the item to be dropped
   */
  public void dropItem(Player p, Item item) {
    Point point = p.getLocation();

    //go around each corner of the room trying to drop the item
    boolean dropped = board[point.y - 1][point.x - 1].setObj(item);
    if (!dropped) {
      dropped = board[point.y - 1][point.x + 1].setObj(item);
    } else if (!dropped) {
      dropped = board[point.y + 1][point.x + 1].setObj(item);
    } else if (!dropped) {
      dropped = board[point.y + 1][point.x - 1].setObj(item);
    }

    //only drop the item from the bag if item was dropped
    if (dropped) {
      p.dropItem(item);
    }
  }
}
