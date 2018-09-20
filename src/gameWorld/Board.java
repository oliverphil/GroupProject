package gameWorld;

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
				Tile tile = new Tile();
				board[row][col] = tile;
				
				//add all walls
				if (row%3 == 0) tile.addWall("north");
				if (col%3 == 0) tile.addWall("west");
				if (row%3 == 2)  tile.addWall("south");
				if (col%3 == 2)  tile.addWall("east");
				
				//TODO remove doors
			}
		}
		
	}
	
	/**
	 * @return the board
	 */
	public Tile[][] getBoard() {
		return board;
	}

	/**
	 * Sets the tile with the coords to the specified Tile object
	 * @param row
	 * @param col
	 * @param tile
	 */
	public void setTile(int row, int col, Tile tile) {
		board[row][col] = tile;
	}
}
