package gameWorld;

<<<<<<< src/gameWorld/FloorObject.java
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
=======
import java.awt.Point;
>>>>>>> src/gameWorld/FloorObject.java

/**
 * Items are objects found in the game world that can be weapons or tools
 * to help a player along their journey.
 * @author ewensdyla
 *
 */
@XmlRootElement
public abstract class FloorObject {
	@XmlElement
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
