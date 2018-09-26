package gameWorld;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

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

	/**
	 * Construct a new FloorObject
	 * @param name
	 */
	public FloorObject(String name) {
		setName(name);
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
}
