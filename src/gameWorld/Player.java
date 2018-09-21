package gameWorld;

import java.util.ArrayList;
import java.util.List;

/**
 * A Player
 * @author Dylan
 *
 */
public class Player extends Character {
	private List<Item> bag;
	private String direction;

	public Player (String name) {
		super(name);

		this.setDirection("north");
		bag = new ArrayList<Item>();
	}

	/**
	 * @return the bag
	 */
	public List<Item> getBag() {
		return bag;
	}

	/**
	 * @param bag the bag to set
	 */
	public void setBag(List<Item> bag) {
		this.bag = bag;
	}

	/**
	 * Changes the direction clockwise from where the customer is facing.
	 */
	public void turnRight() {
		switch (getDirection()) {
			case "north" : 
				setDirection("east");
				break;
			case "east" : 
				setDirection("south");
				break;
			case "south" : 
				setDirection("west");
				break;
			case "west" : 
				setDirection("north");
				break;
		}
	}
	
	/**
	 * Changes the direction counter clockwise from where the customer is facing.
	 */
	public void turnLeft() {
		switch (getDirection()) {
			case "north" : 
				setDirection("west");
				break;
			case "west" : 
				setDirection("south");
				break;
			case "south" : 
				setDirection("east");
				break;
			case "east" : 
				setDirection("north");
				break;
		}
	}
	
	/**
	 * @return the direction
	 */
	public String getDirection() {
		return direction;
	}

	/**
	 * @param direction the direction to set
	 */
	public void setDirection(String direction) {
		this.direction = direction;
	}
}
