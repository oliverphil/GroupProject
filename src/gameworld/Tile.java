package gameworld;

import gameworld.barriers.Barrier;
import gameworld.holdables.Explosive;
import gameworld.holdables.Flask;
import gameworld.holdables.Tool;
import gameworld.holdables.Weapon;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * A Tile object is a single Tile making up the board. It may contain and item or anywhere from 0 to
 * 4 walls.
 *
 * @author Dylan
 *
 */
@XmlRootElement
public class Tile {
  private List<String> walls;
  private List<String> doors;
  private FloorObject obj;
  private int col;
  private int row;
  private boolean occupiedByPlayer;

  /**
   * Constructs a Tile object with no walls.
   */
  public Tile(int row, int col) {
    this.row = row;
    this.col = col;

    setOccupiedByPlayer(false);
    this.walls = new ArrayList<String>();
    this.doors = new ArrayList<String>();
    this.obj = null;
  }

  /**
   * Zero-args constructor for JAXB.
   */
  public Tile() {
    this.row = 0;
    this.col = 0;

    setOccupiedByPlayer(false);
    this.walls = new ArrayList<String>();
    this.doors = new ArrayList<String>();
    this.obj = null;
  }

  /**
   * Constructs a Tile object using a list of Strings to indicate walls.
   *
   * @param walls a list of walls in the tile
   */
  public Tile(List<String> walls) {
    this.row = 0;
    this.col = 0;

    setOccupiedByPlayer(false);
    this.walls = new ArrayList<String>();
    this.doors = new ArrayList<String>();
    this.obj = null;
    this.walls.addAll(walls);
  }

  public boolean hasWall(String dir) {
    return walls.contains(dir);
  }

  public boolean hasDoor(String dir) {
    return doors.contains(dir);
  }

  /**
   * Adds a wall to a tile if it is not already added.
   *
   * @param wall wall to add
   * @return whether the wall was successfully added
   */
  public boolean addWall(String wall) {
    if (this.walls.contains(wall)) {
      return false;
    }
    this.walls.add(wall);
    return true;
  }

  /**
   * Adds a door to a tile if it is not already added in that direction.
   *
   * @param door the door to add
   * @return whether the door was successfully added
   */
  public boolean addDoor(String door) {
    if (this.doors.contains(door)) {
      return false;
    }
    this.doors.add(door);
    return true;
  }

  /**
   * Removes the floor object.
   */
  public void removeFloorObject() {
    this.obj = null;
  }

  /**
   * Returns whether the tile has an item.
   *
   * @return
   */
  public boolean hasObject() {
    return obj != null;
  }

  /**
   * Removes the specified wall from the Tile object.
   *
   * @param wall wall to remove
   */
  public void removeWall(String wall) {
    if (this.walls.contains(wall)) {
      this.walls.remove(wall);
    }
  }

  /**
   * Removes the specified door from the Tile object.
   *
   * @param door door to remove
   */
  public void removeDoor(String door) {
    if (this.doors.contains(door)) {
      this.doors.remove(door);
    }
  }

  public int getCol() {
    return col;
  }

  public int getRow() {
    return row;
  }

  public boolean isOccupiedByPlayer() {
    return occupiedByPlayer;
  }

  @XmlElement(name = "occupiedByPlayer")
  public void setOccupiedByPlayer(boolean occupiedByPlayer) {
    this.occupiedByPlayer = occupiedByPlayer;
  }

  @XmlElementWrapper(name = "walls")
  @XmlElement(name = "wall")
  public void setWalls(List<String> walls) {
    this.walls = walls;
  }

  @XmlElementWrapper(name = "doors")
  @XmlElement(name = "door")
  public void setDoors(List<String> doors) {
    this.doors = doors;
  }

  /**
   * Sets the object to be the selected object or null, if there is no object currently in this
   * Tile.
   *
   * @param obj object to add to the tile
   */
  @XmlElements({ @XmlElement(name = "barrier", type = Barrier.class),
      @XmlElement(name = "fountain", type = Fountain.class),
      @XmlElement(name = "explosive", type = Explosive.class),
      @XmlElement(name = "flask", type = Flask.class),
      @XmlElement(name = "tool", type = Tool.class),
      @XmlElement(name = "weapon", type = Weapon.class),
      @XmlElement(name = "ladder", type = Ladder.class),
      @XmlElement(name = "monster", type = Monster.class) })
  public boolean setObj(FloorObject obj) {
    if (this.obj == null || obj == null) {
      this.obj = obj;
      return true;
    }
    return false;
  }

  @XmlElement(name = "col")
  public void setCol(int col) {
    this.col = col;
  }

  @XmlElement(name = "row")
  public void setRow(int row) {
    this.row = row;
  }

  public List<String> getWalls() {
    return walls;
  }

  public List<String> getDoors() {
    return doors;
  }

  public FloorObject getObj() {
    return obj;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + col;
    result = prime * result + ((doors == null) ? 0 : doors.hashCode());
    result = prime * result + ((obj == null) ? 0 : obj.hashCode());
    result = prime * result + (occupiedByPlayer ? 1231 : 1237);
    result = prime * result + row;
    result = prime * result + ((walls == null) ? 0 : walls.hashCode());
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
    Tile other = (Tile) obj;
    if (col != other.col) {
      return false;
    }
    if (doors == null) {
      if (other.doors != null) {
        return false;
      }
    } else if (!doors.equals(other.doors)) {
      return false;
    }
    if (this.obj == null) {
      if (other.obj != null) {
        return false;
      }
    } else if (!this.obj.equals(other.obj)) {
      return false;
    }
    if (occupiedByPlayer != other.occupiedByPlayer) {
      return false;
    }
    if (row != other.row) {
      return false;
    }
    if (walls == null) {
      if (other.walls != null) {
        return false;
      }
    } else if (!walls.equals(other.walls)) {
      return false;
    }
    return true;
  }

}
