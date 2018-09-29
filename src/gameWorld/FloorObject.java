package gameWorld;

import java.awt.Point;

/**
 * Items are objects found in the game world that can be weapons or tools
 * to help a player along their journey.
 * @author ewensdyla
 *
 */
public abstract class FloorObject {
	private String name;
	private Point location;

	/**
	 * Construct a new FloorObject
	 * @param name
	 */
	public FloorObject(String name, Point p) {
		setName(name);
		setLocation(p);
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the location
	 */
	public Point getLocation() {
		return location;
	}

	/**
	 * @param location the location to set
	 */
	public void setLocation(Point location) {
		this.location = location;
	}
}
