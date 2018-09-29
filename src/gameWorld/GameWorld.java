package gameWorld;

import java.util.Observable;

/**
 * GameWorld class is the API for the game.
 * @author Dylan
 *
 */
//*******OBSERVER PATTERN*******
public class GameWorld extends Observable {

	Player player;
	Board board;
	
	/**
	 * Constructs the GameWorld object, allowing you to play the game.
	 */
	public GameWorld() {
		player = new Player("Player");
		board = new Board();
		player.setView(new ViewDescriptor(player, board));
	}
	
	/**
	 * @return the current ViewDescriptor of the player
	 */
	public ViewDescriptor getViewDescriptor() {
		return player.getView();
	}
	
	/**
	 * Updates the renderer.
	 */
	public void update() {
		setChanged();
		notifyObservers(getViewDescriptor());
	}
	//whenever a view changes use update();
}
