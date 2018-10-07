package gameworld;

import java.awt.Point;
import java.io.File;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import gameworld.holdables.Flask;

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
   * This board constructs the board from a saved file.
   */
  public Board(File file) {
    // TODO write method to load game
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
    //Add bosses
    Monster david = new Monster();
    david.setLocation(new Point(0, 13));
    david.setName("david");
    david.setDamage(30);
    david.setHealth(150);
    this.board[13][0].setObj(david);
    
    Monster marco = new Monster();
    marco.setLocation(new Point(1, 0));
    marco.setName("marco");
    marco.setDamage(20);
    marco.setHealth(150);
    this.board[0][1].setObj(marco);
    
    Monster thomas = new Monster();
    thomas.setLocation(new Point(14, 1));
    thomas.setName("thomas");
    thomas.setDamage(10);
    thomas.setHealth(150);
    this.board[1][14].setObj(thomas);

    //add flasks
    Flask flask1 = new Flask();
    flask1.setLocation(new Point(8, 6));
    flask1.setName("emptyFlask");
    this.board[6][8].setObj(flask1);
    
    Flask flask2 = new Flask();
    flask2.setLocation(new Point(0, 7));
    flask2.setName("emptyFlask");
    this.board[7][0].setObj(flask2);
    
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
    addADoor(board[4][11], "east");

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
   * @param row
   * @param col
   * @param tile
   */
  public void setTile(int row, int col, Tile tile) {
    board[row][col] = tile;
  }

  /**
   * Moves the player backwards.
   *
   * @param player
   */
  public void goBack(Player p) {
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
          // TODO cannot move
        }
        break;
      case "south":
        behind = board[point.y - 1][point.x];
        if (!behind.hasDoor("north") && !behind.hasWall("north")) {
          p.setLocation(new Point(point.x, point.y - 3));
        } else {
          ;// TODO cannot move
        }
        break;
      case "east":
        behind = board[point.y][point.x - 1];
        if (!behind.hasDoor("west") && !behind.hasWall("west")) {
          p.setLocation(new Point(point.x - 3, point.y));
        } else {
          ;// TODO cannot move
        }
        break;
      case "west":
        behind = board[point.y][point.x + 1];
        if (!behind.hasDoor("east") && !behind.hasWall("east")) {
          p.setLocation(new Point(point.x + 3, point.y));
        } else {
          ;// TODO cannot move
        }
        break;
      default:
        break;
    }

    // update the player's view.
    p.setView(new ViewDescriptor(p, this));
  }

  /**
   * Moves the player forwards.
   *
   * @param player
   */
  public void goForwards(Player p) {
    // check if there is a wall or an open door behind them
    String dir = p.getDirection();
    Point point = p.getLocation();
    Tile forward = null;

    // move the player backwards if there is nothing behind them
    switch (dir) {
      case "north":
        forward = board[point.y - 1][point.x];
        if (!forward.hasDoor("north") && !forward.hasWall("north")) {
          p.setLocation(new Point(point.x, point.y - 3));
        } else {
          ;// TODO cannot move
        }
        break;
      case "south":
        forward = board[point.y + 1][point.x];
        if (!forward.hasDoor("south") && !forward.hasWall("south")) {
          p.setLocation(new Point(point.x, point.y + 3));
        } else {
          ;// TODO cannot move
        }
        break;
      case "east":
        forward = board[point.y][point.x + 1];
        if (!forward.hasDoor("east") && !forward.hasWall("east")) {
          p.setLocation(new Point(point.x + 3, point.y));
        } else {
          ;// TODO cannot move
        }
        break;
      case "west":
        forward = board[point.y][point.x - 1];
        if (!forward.hasDoor("west") && !forward.hasWall("west")) {
          p.setLocation(new Point(point.x - 3, point.y));
        } else {
          ;// TODO cannot move
        }
        break;
      default:
        break;
    }

    // update the player's view.
    p.setView(new ViewDescriptor(p, this));
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

}
