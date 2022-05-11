package lab2.testing;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import lab2.Board;
import lab2.Cell;
import lab2.Coordinate;
import lab2.Move;
import lab2.Piece;

public class BoardStudentTest {

	private Board board;

	@Before
	    public void setup() {
	        this.board = new Board();
	    }

	@Test
	public void testGetTurn() {
		Assert.assertEquals(Piece.Type.MUSKETEER, this.board.getTurn());
	}

	@Test
	public void testGetCell() {
		
	        Cell cell = board.getCell(new Coordinate(2, 2));  
	        Assert.assertEquals("X", cell.getPiece().getSymbol().toString());

	    
	}

	@Test
	public void testGetWinner() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetMusketeerCells() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetGuardCells() {
		fail("Not yet implemented");
	}

	@Test
	public void testMove() {
		Move move = new Move(new Cell(new Coordinate(2,2)), new Cell((new Coordinate(2,3))));
		
		Cell cellfrom = board.getCell(new Coordinate(2, 2));
		Cell cellto = board.getCell(new Coordinate(2, 3));
		
		board.move(move);
		
		//to cell changed?
		Assert.assertEquals("X", cellto.getPiece().getSymbol().toString());
		
		
	}

	@Test
	public void testUndoMove() {
		Move move = new Move(new Cell(new Coordinate(2,2)), new Cell((new Coordinate(2,3))));
		
		System.out.print(move.fromCell.getPiece().getSymbol());
		
		Cell cellfrom = board.getCell(new Coordinate(2, 2));
		Cell cellto = board.getCell(new Coordinate(2, 3));
		
		List<Move> moves = new ArrayList<>();
		
		
		moves.add(new Move(new Cell(move.fromCell), new Cell(move.toCell)));
		
		
		System.out.print(move.fromCell.getPiece().getSymbol());
		board.move(move);

		System.out.print(moves.toString());
		System.out.print(move.fromCell);
		System.out.print(board);
		board.undoMove(moves.get(0));
		
		System.out.print(board);
		
		
		Assert.assertEquals("O", cellto.getPiece().getSymbol().toString());
		Assert.assertEquals("X", cellfrom.getPiece().getSymbol().toString());
		
	}

	@Test
	public void testIsValidMove() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetPossibleCells() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetPossibleDestinations() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetPossibleMoves() {
		fail("Not yet implemented");
	}

	@Test
	public void testIsGameOver() {
		fail("Not yet implemented");
	}



}
