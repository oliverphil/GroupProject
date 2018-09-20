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
	}

	/**
	 * This board constructs the board from a saved file
	 */
	public Board(File file) {

	}

	/**
	 * @return the board
	 */
	public Tile[][] getBoard() {
		return board;
	}


	public void setTile(int row, int col, Tile tile) {
		board[row][col] = tile;
	}
}
