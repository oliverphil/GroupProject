package gameWorld;

public class GameWorld {

	Player player;
	Board board;
	
	public GameWorld() {
		player = new Player("Player");
		board = new Board();
		player.setView(new ViewDescriptor(player, board));
	}
	
	public ViewDescriptor getViewDescriptor() {
		return player.getView();
	}
}
