package gameWorld;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * A Tile object is a single Tile making up the board. It may contain and
 * item or anywhere from 0 to 4 walls.
 * @author Dylan
 *
 */
@XmlRootElement
public class Tile {
	@XmlElementWrapper
	@XmlElement(name = "wall")
	private List<String> walls;
	@XmlElementWrapper
	@XmlElement(name = "door")
	private List<String> doors;
	@XmlElement
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
		this.obj = null;
	}

	/**
	 * Constructs a Tile object using a list of Strings to indicate walls.
	 * @param walls
	 */
	public Tile(List<String> walls) {
		this.walls.addAll(walls);
	}

	/**
	 * @return whether the Tile contains a wall int that direction
	 */
	public boolean hasWall(String dir) {
		return walls.contains(dir);
	}

	/**
	 * @return whether the Tile contains a door in the specified direction.
	 */
	public boolean hasDoor(String dir) {
		return doors.contains(dir);
	}

	/**
	 * Adds a wall to a tile if it is not already added
	 * @param wall
	 * @return whether the wall was successfully added
	 */
	public boolean addWall(String wall) {
		if (this.walls.contains(wall)) return false;
		this.walls.add(wall);
		return true;
	}

	/**
	 * Adds a door to a tile if it is not already added in that direction
	 * @param door
	 * @return whether the door was successfully added
	 */
	public boolean addDoor(String door) {
		if (this.doors.contains(door)) return false;
		this.doors.add(door);
		return true;
	}

	/**
	 * @return the object on
	 */
	public FloorObject getFloorObject() {
		return obj;
	}

	/**
	 * @param item the item to set
	 */
	public void setItem(FloorObject item) {
		if (!hasItem()) this.obj = item;
	}

	/**
	 * Returns whether the tile has an item.
	 * @return
	 */
	public boolean hasItem() {
		return obj != null;
	}

	/**
	 * Removes the specified wall from the Tile object
	 * @param wall
	 */
	public void removeWall(String wall) {
		if (this.walls.contains(wall)) this.walls.remove(wall);
	}

	/**
	 * @return the column
	 */
	public int getCol() {
		return col;
	}

	/**
	 * @return the row
	 */
	public int getRow() {
		return row;
	}

	/**
	 * @return the occupiedByPlayer
	 */
	public boolean isOccupiedByPlayer() {
		return occupiedByPlayer;
	}

	/**
	 * @param occupiedByPlayer the occupiedByPlayer to set
	 */
	public void setOccupiedByPlayer(boolean occupiedByPlayer) {
		this.occupiedByPlayer = occupiedByPlayer;
	}
}
