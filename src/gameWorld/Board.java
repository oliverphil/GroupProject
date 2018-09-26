package gameWorld;

import java.awt.Point;
import java.io.File;

public class Board {
	private Tile[][] board;
	public static final int WIDTH = 15;
	public static final int HEIGHT = 15;

	/**
	 * This board constructs the standard board, new game board
	 */
	public Board() {
		board = new Tile[HEIGHT][WIDTH];

		initialiseBoard();
	}

	/**
	 * This board constructs the board from a saved file
	 */
	public Board(File file) {
		//TODO write method to load game
	}

	/**
	 * Creates the starting board for the base game, adding all tiles
	 * and filling in the walls
	 */
	private void initialiseBoard() {
		for (int row = 0; row < HEIGHT; row++) {
			for (int col = 0; col < WIDTH; col++) {
				Tile tile = new Tile(row, col);
				board[row][col] = tile;

				//add all walls
				if (row%3 == 0) tile.addWall("north");
				if (col%3 == 0) tile.addWall("west");
				if (row%3 == 2)  tile.addWall("south");
				if (col%3 == 2)  tile.addWall("east");
			}
		}
		addDoors();
		//start the player in the centre square
		this.board[7][7].setOccupiedByPlayer(true);
	}

	/**
	 * Removes the walls and replaces them with doors. It calls the method
	 * and adds 1 door at a time.
	 */
	private void addDoors() {
		addADoor(board[1][8], "east"); addADoor(board[1][11], "east");

		addADoor(board[2][1], "south"); addADoor(board[2][4], "south"); addADoor(board[2][7], "south");
		addADoor(board[4][2], "east"); addADoor(board[4][8], "east"); addADoor(board[4][11], "east");

		addADoor(board[5][4], "south"); addADoor(board[5][7], "south"); addADoor(board[5][13], "south");
		addADoor(board[7][2], "east"); addADoor(board[7][5], "east"); addADoor(board[7][8], "east"); addADoor(board[7][11], "east");

		addADoor(board[8][1], "south"); addADoor(board[8][7], "south"); addADoor(board[8][10], "south");
		addADoor(board[10][2], "east"); addADoor(board[10][11], "east");

		addADoor(board[11][7], "south"); addADoor(board[11][10], "south"); addADoor(board[11][13], "south");
		addADoor(board[13][2], "east"); addADoor(board[13][5], "east");
	}

	/**
	 * Removes a the wall and replaces it with a door (on both sides)
	 * @param tile
	 * @param direction
	 */
	private void addADoor(Tile t, String dir) {
		//remove wall and door
		t.removeWall(dir);
		t.addDoor(dir);

		//remove the wall and add a door to the opposing side
		switch (dir) {
			case "east" :
				board[t.getRow()][t.getCol()].removeWall("east");
				board[t.getRow()][t.getCol()].addDoor("east");
				board[t.getRow()][t.getCol()+1].removeWall("west");
				board[t.getRow()][t.getCol()+1].addDoor("west");
				break;
			case "south" :
				board[t.getRow()][t.getCol()].removeWall("south");
				board[t.getRow()][t.getCol()].addDoor("south");
				board[t.getRow()+1][t.getCol()].removeWall("north");
				board[t.getRow()+1][t.getCol()].addDoor("north");
				break;
		}
	}

	/**
	 * @return the board
	 */
	public Tile[][] getBoard() {
		return board;
	}

	/**
	 * Return the tile in the specified position.
	 * @param row
	 * @param col
	 * @return
	 */
	public Tile getTile(int row, int col) {
		return board[row][col];
	}

	/**
	 * Sets the tile with the coords to the specified Tile object.
	 * @param row
	 * @param col
	 * @param tile
	 */
	public void setTile(int row, int col, Tile tile) {
		board[row][col] = tile;
	}

	/**
	 * Moves the player backwards.
	 * @param player
	 */
	public void goBack(Player p) {
		//check if there is a wall or an open door behind them
		String dir = p.getDirection();
		Point point = p.getLocation();
		Tile behind = null;

		//move the player backwards if there is nothing behind them
		switch (dir) {
			case "north" :
				behind = board[point.y+1][point.x];
				if (!behind.hasDoor("south") && !behind.hasWall("south"))
					p.setLocation(new Point(point.x, point.y + 3));
				else ;//TODO cannot move
				break;
			case "south" :
				behind = board[point.y-1][point.x];
				if (!behind.hasDoor("north") && !behind.hasWall("north"))
					p.setLocation(new Point(point.x, point.y - 3));
				else ;//TODO cannot move
				break;
			case "east" :
				behind = board[point.y][point.x-1];
				if (!behind.hasDoor("west") && !behind.hasWall("west"))
					p.setLocation(new Point(point.x - 3, point.y));
				else ;//TODO cannot move
				break;
			case "west" :
				behind = board[point.y][point.x+1];
				if (!behind.hasDoor("east") && !behind.hasWall("east"))
					p.setLocation(new Point(point.x + 3, point.y));
				else ;//TODO cannot move
				break;
		}

		//update the player's view.
		p.setView(new ViewDescriptor(p, this));
	}

	/**
	 * Moves the player forwards.
	 * @param player
	 */
	public void goForwards(Player p) {
		//check if there is a wall or an open door behind them
		String dir = p.getDirection();
		Point point = p.getLocation();
		Tile forward = null;

		//move the player backwards if there is nothing behind them
		switch (dir) {
			case "north" :
				forward = board[point.y-1][point.x];
				if (!forward.hasDoor("south") && !forward.hasWall("south"))
					p.setLocation(new Point(point.x, point.y - 3));
				else ;//TODO cannot move
				break;
			case "south" :
				forward = board[point.y+1][point.x];
				if (!forward.hasDoor("north") && !forward.hasWall("north"))
					p.setLocation(new Point(point.x, point.y + 3));
				else ;//TODO cannot move
				break;
			case "east" :
				forward = board[point.y][point.x+1];
				if (!forward.hasDoor("west") && !forward.hasWall("west"))
					p.setLocation(new Point(point.x + 3, point.y));
				else ;//TODO cannot move
				break;
			case "west" :
				forward = board[point.y][point.x-1];
				if (!forward.hasDoor("east") && !forward.hasWall("east"))
					p.setLocation(new Point(point.x - 3, point.y));
				else ;//TODO cannot move
				break;
		}

		//update the player's view.
		p.setView(new ViewDescriptor(p, this));
	}
}
