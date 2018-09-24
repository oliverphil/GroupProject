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
	private FloorObject rightHand;
	private FloorObject leftHand;
	private String direction;
	private Point location;
	private ViewDescriptor view;

	public Player (String name) {
		super(name);

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
	 * @return the leftHand
	 */
	public FloorObject getLeftHand() {
		return leftHand;
	}

	/**
	 * @param leftHand the leftHand to set
	 */
	public void setLeftHand(FloorObject leftHand) {
		this.leftHand = leftHand;
	}

	/**
	 * @return the rightHand
	 */
	public FloorObject getRightHand() {
		return rightHand;
	}

	/**
	 * @param rightHand the rightHand to set
	 */
	public void setRightHand(FloorObject rightHand) {
		this.rightHand = rightHand;
	}
}
