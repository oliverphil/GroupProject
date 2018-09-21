package gameWorld;

import java.util.ArrayList;
import java.util.List;

/**
 * Provides a description of what the player can see given the room and direction
 * @author Dylan
 */
public class ViewDescriptor {
	//need to have left, middle, right walls or door
	//need to know what items are on the floor + monsters/weapons 
	List<String> view;
	
	public ViewDescriptor(Player p) {
		view = new ArrayList<String>();
		
		
	}
}
