package gameWorld;

<<<<<<< src/gameWorld/Monster.java
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
=======
import java.awt.Point;

>>>>>>> src/gameWorld/Monster.java
public class Monster extends FloorObject{
	private int health;

	public Monster(String name, Point p, int health) {
		super(name, p);
		this.health = health;
	}

	/**
	 * @return the health
	 */
	public int getHealth() {
		return health;
	}

	/**
	 * @param health to add
	 */
	public void addHealth(int health) {
		this.health += health;
	}

	/**
	 * @param health to remove
	 */
	public void removeHealth(int health) {
		this.health -= health;
	}

}
