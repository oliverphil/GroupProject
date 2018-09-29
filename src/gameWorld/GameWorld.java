package gameWorld;

import java.util.Observable;

public class GameWorld extends Observable {

	Player player;
	Board board;
	
	public GameWorld() {
		player = new Player("Player");
		board = new Board();
		player.setView(new ViewDescriptor(player, board));
		
		setChanged();
		notifyObservers(getViewDescriptor());
	}
	
	/**
	 * @return the current ViewDescriptor of the player
	 */
	public ViewDescriptor getViewDescriptor() {
		return player.getView();
	}
	
	//whenever a view changes use setChanged(); notifyObservers(getViewDescriptor());
}
