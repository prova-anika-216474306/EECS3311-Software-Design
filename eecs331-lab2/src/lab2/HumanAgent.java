package lab2;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import lab2.Exceptions.InvalidMoveException;

public class HumanAgent extends Agent {

	public HumanAgent(Board board) {
		super(board);
	}

	/**
	 * Asks the human for a move with from and to coordinates and makes sure its
	 * valid. Create a Move object with the chosen fromCell and toCell
	 * 
	 * @return the valid human inputted Move
	 */
	@Override
	public Move getMove() {
		// TODO
		final Scanner input1 = new Scanner(System.in);
		final Scanner input2 = new Scanner(System.in);
		
		Coordinate fromCoordinate;
		Cell fcell = null;
		Coordinate toCoordinate;
		Cell tcell = null;
		

		// creating an array of the possible cells to print out the possible pieces 
		
		List<Coordinate> arr = new ArrayList<Coordinate>();
		List<String> arrString = new ArrayList<String>();
		
		for (Cell c : board.getPossibleCells()) {
			arr.add(c.getCoordinate());
			arrString.add(c.getCoordinate().toString());
			
		}
		
		try {
// //PROMT FIRST INPUT FROM USER
			
			System.out.print("Possible pieces are " + arr + ". Enter the piece you want to move: ");
			
			// test if the from cell move is correct
		String fromCell = input1.next().toUpperCase();
			
			while (!(arrString.contains(fromCell))){
				System.out.print("Invalid piece. Select piece from: " + arr + " ");
				fromCell = input1.next().toUpperCase();
			}

			//initialize an array for listing the possible destinations 
			List<Coordinate> destinationsArray = new ArrayList<Coordinate>();
			List<String> destinationsArrayString = new ArrayList<String>();
			
			// USE UTIL CLASS TO TURN STRING INPUT INTO COORDINATE
			fromCoordinate = Utils.parseUserMove(fromCell);
			fcell = board.board[fromCoordinate.row][fromCoordinate.col];

			
			for (Cell c : board.getPossibleDestinations(fcell)) {
				destinationsArray.add(c.getCoordinate());
				destinationsArrayString.add(c.getCoordinate().toString());
				
			}
			
//PROMT SECOND INPUT FROM USER
			
			System.out.print("Possible destinations are " + destinationsArray +". Enter where you want to move:");

			String toCell = input2.next().toUpperCase();
			
			// test if the to cell move is correct
			while (!(destinationsArrayString.contains(toCell))) {
				System.out.print("Invalid destination. Select piece from: " + destinationsArrayString + " ");
				toCell = input2.next().toUpperCase();
			}
			
			// USE UTIL CLASS TO TURN STRING INPUT INTO COORDINATE
			toCoordinate = Utils.parseUserMove(toCell);
			tcell = board.board[toCoordinate.row][toCoordinate.col];
			

		} catch (InvalidMoveException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		

	//RETURN THE CONSTRUCTED MOVE
		return new Move (fcell, tcell);
	}

}
