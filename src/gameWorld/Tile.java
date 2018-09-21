package gameWorld;

import java.util.ArrayList;
import java.util.List;

/**
 * A Tile object is a single Tile making up the board. It may contain and 
 * item or anywhere from 0 to 4 walls.
 * @author Dylan
 *
 */
public class Tile {
	private List<String> walls;
	private List<String> doors;
	private Item item;
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
		this.item = null;
	}
	
	/**
	 * Constructs a Tile object using a list of Strings to indicate walls.
	 * @param walls
	 */
	public Tile(List<String> walls) {
		this.walls.addAll(walls);
	}
	
	/**
	 * @return whether the Tile contains a wall to the North
	 */
	public boolean hasWallNorth() {
		return walls.contains("north");
	}
	
	/**
	 * @return whether the Tile contains a wall to the South
	 */
	public boolean hasWallSouth() {
		return walls.contains("south");
	}
	
	/**
	 * @return whether the Tile contains a wall to the East
	 */
	public boolean hasWallEast() {
		return walls.contains("east");
	}
	
	/**
	 * @return whether the Tile contains a wall to the West
	 */
	public boolean hasWallWest() {
		return walls.contains("west");
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
	 * @return whether the Tile contains a door to the North
	 */
	public boolean hasDoorNorth() {
		return doors.contains("north");
	}
	
	/**
	 * @return whether the Tile contains a door to the South
	 */
	public boolean hasDoorSouth() {
		return doors.contains("south");
	}
	
	/**
	 * @return whether the Tile contains a door to the East
	 */
	public boolean hasDoorEast() {
		return doors.contains("east");
	}
	
	/**
	 * @return whether the Tile contains a door to the West
	 */
	public boolean hasDoorWest() {
		return doors.contains("west");
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
	 * @return the item
	 */
	public Item getItem() {
		return item;
	}

	/**
	 * @param item the item to set
	 */
	public void setItem(Item item) {
		if (!hasItem()) this.item = item;
	}
	
	/**
	 * Returns whether the tile has an item.
	 * @return
	 */
	public boolean hasItem() {
		return item!=null;
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
