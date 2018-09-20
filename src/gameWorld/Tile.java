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
	private Item item;
	
	/**
	 * Constructs a Tile object with no walls.
	 */
	public Tile() {
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
	public void removeTile(String wall) {
		if (this.walls.contains(wall)) this.walls.remove(wall);
	}
}
