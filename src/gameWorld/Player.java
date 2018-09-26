package gameWorld;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

/**
 * A Player
 * @author Dylan
 *
 */
public class Player extends Character {
	private List<FloorObject> bag;
	private String direction;
	private Point location;
	private ViewDescriptor view;

	public Player (String name) {
		super(name);

		bag = new ArrayList<FloorObject>();
		location = new Point(7,7);
		this.setDirection("north");
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

	/**
	 * @return the location
	 */
	public Point getLocation() {
		return location;
	}

	/**
	 * @param loc the location to set
	 */
	public void setLocation(Point loc) {
		this.location = loc;
	}

	/**
	 * @return the view
	 */
	public ViewDescriptor getView() {
		return view;
	}

	/**
	 * @param view the view to set
	 */
	public void setView(ViewDescriptor view) {
		this.view = view;
	}

	/**
	 * @return the bag
	 */
	public List<FloorObject> getBag() {
		return bag;
	}

	/**
	 * @param bag the bag to set
	 */
	public void setBag(List<FloorObject> bag) {
		this.bag = bag;
	}
}
