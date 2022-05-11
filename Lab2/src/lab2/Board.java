package lab2;

import java.awt.Point;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Board {
	public int size = 5;

	// 2D Array of Cells for representation of the game board
	public final Cell[][] board = new Cell[size][size];

	private Piece.Type turn;
	private Piece.Type winner;

	/**
	 * Create a Board with the current player turn set.
	 */
	public Board() {
		this.loadBoard("./Boards/Starter.txt");
	}

	/**
	 * Create a Board with the current player turn set and a specified board.
	 * 
	 * @param boardFilePath The path to the board file to import (e.g.
	 *                      "Boards/Starter.txt")
	 */
	public Board(String boardFilePath) {
		this.loadBoard(boardFilePath);
	}

	/**
	 * Creates a Board copy of the given board.
	 * 
	 * @param board Board to copy
	 */
	public Board(Board board) {
		this.size = board.size;
		for (int row = 0; row < size; row++) {
			for (int col = 0; col < size; col++) {
				this.board[row][col] = new Cell(board.board[row][col]);
			}
		}
		this.turn = board.turn;
		this.winner = board.winner;
	}

	/**
	 * @return the Piece.Type (Muskeeteer or Guard) of the current turn
	 */
	public Piece.Type getTurn() {
		return turn;
	}

	/**
	 * Get the cell located on the board at the given coordinate.
	 * 
	 * @param coordinate Coordinate to find the cell
	 * @return Cell that is located at the given coordinate
	 */
	//return the coordinate from this board
	
	public Cell getCell(Coordinate coordinate) { // TODO
		return this.board[coordinate.row][coordinate.col];
	}

	/**
	 * @return the game winner Piece.Type (Muskeeteer or Guard) if there is one
	 *         otherwise null
	 */
	public Piece.Type getWinner() {
		return winner;
	}

	/**
	 * Gets all the Musketeer cells on the board.
	 * 
	 * @return List of cells
	 */
	public List<Cell> getMusketeerCells() { // TODO

		List<Cell> m = new ArrayList<Cell>();
		for (int i = 0; i < this.size; i++) {
			for (int j = 0; j < this.size; j++) {
				if (this.board[i][j].hasPiece() && this.board[i][j].getPiece().getType().equals(Piece.Type.MUSKETEER)) {
					m.add(board[i][j]);
				}
			}
		}

		return m;

	}

	/**
	 * Gets all the Guard cells on the board.
	 * 
	 * @return List of cells
	 */
	public List<Cell> getGuardCells() { // TODO
		List<Cell> g = new ArrayList<Cell>();
		for (int i = 0; i < this.size; i++) {
			for (int j = 0; j < this.size; j++) {
				if (this.board[i][j].hasPiece() && this.board[i][j].getPiece().getType().equals(Piece.Type.GUARD)) {
					g.add(board[i][j]);
				}
			}
		}
		return g;
	}

	/**
	 * Executes the given move on the board and changes turns at the end of the
	 * method.
	 * 
	 * @param move a valid move
	 */
	public void move(Move move) {
		// TODO

		Piece from = this.board[move.fromCell.getCoordinate().row][move.fromCell.getCoordinate().col].getPiece();

		this.board[move.toCell.getCoordinate().row][move.toCell.getCoordinate().col].setPiece(from);
		this.board[move.fromCell.getCoordinate().row][move.fromCell.getCoordinate().col].removePiece();

		if (this.turn.equals(Piece.Type.MUSKETEER)) {
			this.turn = Piece.Type.GUARD;
		} else {
			this.turn = Piece.Type.MUSKETEER;
		}

	}

	/**
	 * Undo the move given.
	 * 
	 * @param move Copy of a move that was done and needs to be undone. The move
	 *             copy has the correct piece info in the from and to cell fields.
	 *             Changes turns at the end of the method.
	 */
	public void undoMove(Move move) { // TODO
		Piece a = move.fromCell.getPiece();
		Piece b = move.toCell.getPiece();

		this.board[move.fromCell.getCoordinate().row][move.fromCell.getCoordinate().col].setPiece(a);
		this.board[move.toCell.getCoordinate().row][move.toCell.getCoordinate().col].setPiece(b);

		if (this.turn.equals(Piece.Type.MUSKETEER)) {
			this.turn = Piece.Type.GUARD;
		} else {
			this.turn = Piece.Type.MUSKETEER;
		}

	}

	/**
	 * Checks if the given move is valid. Things to check: (1) the toCell is next to
	 * the fromCell (2) the fromCell piece can move onto the toCell piece.
	 * 
	 * @param move a move
	 * @return True, if the move is valid, false otherwise
	 */
	public Boolean isValidMove(Move move) { // TODO

		boolean adjacent = false;
		boolean piece = false;

		int x = move.fromCell.getCoordinate().row;
		int y = move.fromCell.getCoordinate().col;

		// CHECKS FOR ADJACENT CELLS
		ArrayList<Cell> possibleToCells = new ArrayList<Cell>();

		if ((y + 1) < board.length) {
			Cell v1 = board[x][y + 1];
			possibleToCells.add(v1);
		}
		if ((y - 1) >= 0) {
			Cell v2 = board[x][y - 1];
			possibleToCells.add(v2);
		}
		if ((x + 1) < board.length) {
			Cell v3 = board[x + 1][y];
			possibleToCells.add(v3);
		}
		if ((x - 1) >= 0) {
			Cell v4 = board[x - 1][y];
			possibleToCells.add(v4);
		}

		if (possibleToCells.contains(move.toCell)) {
			adjacent = true;
		}
		// CAN MOVE ONTO IS DYNAMICALLY BINDED DEPENDING ON CURRENT PIECE
		if (move.fromCell.hasPiece() && move.fromCell.getPiece().canMoveOnto(move.toCell)) {

			piece = true;
		} else {
			piece = false;
		}

		return adjacent && piece;

	}

	/**
	 * Get all the possible cells that have pieces that can be moved this turn.
	 * 
	 * @return Cells that can be moved from the given cells
	 */
	public List<Cell> getPossibleCells() {

		// TODO
		List<Cell> possible = new ArrayList<Cell>();

		if (this.turn.equals(Piece.Type.GUARD)) {
			for (Cell c : getGuardCells()) {

				if (!this.getPossibleDestinations(c).isEmpty()) {
					possible.add(c);
				}

			}
		} else {
			for (Cell c : getMusketeerCells()) {
				if (!this.getPossibleDestinations(c).isEmpty()) {
					possible.add(c);
				}
			}

		}

		return possible;
	}

	/**
	 * Get all the possible cell destinations that is possible to move to from the
	 * fromCell.
	 * 
	 * @param fromCell The cell that has the piece that is going to be moved
	 * @return List of cells that are possible to get to
	 */
	public List<Cell> getPossibleDestinations(Cell fromCell) {
		// TODO
		int x = fromCell.getCoordinate().row;
		int y = fromCell.getCoordinate().col;

		// MAKING LIST OF ALL POSSIBLE DESTINATIONS
		List<Cell> possibleCells = new ArrayList<Cell>();

		if ((y + 1) < board.length) {
			Cell v1 = board[x][y + 1];
			possibleCells.add(v1);
		}
		if ((y - 1) >= 0) {
			Cell v2 = board[x][y - 1];
			possibleCells.add(v2);
		}
		if ((x + 1) < board.length) {
			Cell v3 = board[x + 1][y];
			possibleCells.add(v3);
		}
		if ((x - 1) >= 0) {
			Cell v4 = board[x - 1][y];
			possibleCells.add(v4);
		}

		List<Cell> result = new ArrayList<Cell>();
		// CREATING MOVES WITH POSSIBLE DESTINATIONS AND VALIDATING MOVES
		for (Cell c : possibleCells) {
			Move current = new Move(fromCell, c);
			if (isValidMove(current)) {
				result.add(c);
			}
		}

		return result;

	}

	/**
	 * Get all the possible moves that can be made this turn.
	 * 
	 * @return List of moves that can be made this turn
	 */
	public List<Move> getPossibleMoves() { // TODO
		List<Cell> mgcells = new ArrayList<Cell>();
		List<Cell> destinationcells = new ArrayList<Cell>();
		List<Move> result = new ArrayList<Move>();

		if (this.turn == Piece.Type.GUARD) {
			mgcells.addAll(getGuardCells());
		} else if (this.turn == Piece.Type.MUSKETEER) {
			mgcells.addAll(getMusketeerCells());
		}
		// GET ALL POSSIBLE MOVES FROM EACH MUSKETEER/ GUARD TO THEIR DESTINATIONS
		for (Cell from : mgcells) {
			destinationcells.addAll(getPossibleDestinations(from));
			for (Cell to : destinationcells) {
				result.add(new Move(from, to));
			}
		}

		return result;

	}

	/**
	 * Checks if the game is over and sets the winner if there is one.
	 * 
	 * @return True, if the game is over, false otherwise.
	 */
	public boolean isGameOver() { // TODO
		boolean flag = false;
		// CHECK FOR NO MOVES FOR GUARD
		if (this.getTurn() == Piece.Type.GUARD) {
			if (this.getPossibleMoves().isEmpty()) {
				flag = true;
				winner = Piece.Type.MUSKETEER;
				return flag;
			}

		} else {
			// CHECK FOR NO MOVES FOR MUSKETEER
			if (this.getPossibleMoves().isEmpty()) {
				flag = true;
				winner = Piece.Type.GUARD;
				return flag;
			}

		}

		// CHECK IF ALL MUSKETEERS IN SAME ROW
		int row = this.getMusketeerCells().get(0).getCoordinate().row;
		int countrow = 0;

		for (int i = 0; i < this.getMusketeerCells().size(); i++) {
			if (this.getMusketeerCells().get(i).getCoordinate().row == row) {
				countrow++;
				if (countrow == 3) {
					flag = true;
					winner = Piece.Type.GUARD;
					return flag;

				}
			}
		}
		// CHECK IF ALL MUSKETEERS IN SAME COLUMN
		int column = this.getMusketeerCells().get(0).getCoordinate().col;
		int countcolumn = 0;

		for (int i = 0; i < this.getMusketeerCells().size(); i++) {
			if (this.getMusketeerCells().get(i).getCoordinate().col == column) {
				countcolumn++;
				if (countcolumn == 3) {
					flag = true;
					winner = Piece.Type.GUARD;
					return flag;
				}
			}
		}
		if(this.getGuardCells().isEmpty()) {
			flag = true;
			winner = Piece.Type.GUARD;
			return flag;
			
		}

		return flag;
	}

	/**
	 * Saves the current board state to the boards directory
	 */
	public void saveBoard() {
		String filePath = String.format("boards/%s.txt",
				new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date()));
		File file = new File(filePath);

		try {
			file.createNewFile();
			Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));
			writer.write(turn.getType() + "\n");
			for (Cell[] row : board) {
				StringBuilder line = new StringBuilder();
				for (Cell cell : row) {
					if (cell.getPiece() != null) {
						line.append(cell.getPiece().getSymbol());
					} else {
						line.append("_");
					}
					line.append(" ");
				}
				writer.write(line.toString().strip() + "\n");
			}
			writer.close();
			System.out.printf("Saved board to %s.\n", filePath);
		} catch (IOException e) {
			e.printStackTrace();
			System.out.printf("Failed to save board to %s.\n", filePath);
		}
	}

	@Override
	public String toString() {
		StringBuilder boardStr = new StringBuilder("  | A B C D E\n");
		boardStr.append("--+----------\n");
		for (int i = 0; i < size; i++) {
			boardStr.append(i + 1).append(" | ");
			for (int j = 0; j < size; j++) {
				Cell cell = board[i][j];
				boardStr.append(cell).append(" ");
			}
			boardStr.append("\n");
		}
		return boardStr.toString();
	}

	/**
	 * Loads a board file from a file path.
	 * 
	 * @param filePath The path to the board file to load (e.g.
	 *                 "Boards/Starter.txt")
	 */
	private void loadBoard(String filePath) {
		File file = new File(filePath);
		Scanner scanner = null;
		try {
			scanner = new Scanner(file);
		} catch (FileNotFoundException e) {
			System.err.printf("File at %s not found.", filePath);
			System.exit(1);
		}

		turn = Piece.Type.valueOf(scanner.nextLine().toUpperCase());

		int row = 0, col = 0;
		while (scanner.hasNextLine()) {
			String line = scanner.nextLine();
			String[] pieces = line.trim().split(" ");
			for (String piece : pieces) {
				Cell cell = new Cell(new Coordinate(row, col));
				switch (piece) {
				case "O" -> cell.setPiece(new Guard());
				case "X" -> cell.setPiece(new Musketeer());
				default -> cell.setPiece(null);
				}
				this.board[row][col] = cell;
				col += 1;
			}
			col = 0;
			row += 1;
		}
		scanner.close();
		System.out.printf("Loaded board from %s.\n", filePath);
	}
}
