package gameWorld;

public class GameWorld {

	Player player;
	
	public GameWorld() {
		player = new Player("");
	}
	
	public ViewDescriptor getViewDescriptor() {
		return player.getView();
	}
}
