package lab2;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
public class RandomAgent extends Agent {

    public RandomAgent(Board board) {
        super(board);
    }

    /**
     * Gets a valid random move the RandomAgent can do.
     * @return a valid Move that the RandomAgent can perform on the Board
     */
   
    
    
    public Move getMove() { // TODO
    	
    	List<Cell> possibleCells = board.getPossibleCells();
    	
    	//GENERATE RANDOM FROM CELL
    	int fromIndex = ((int) (Math.random() * 999)) %  possibleCells.size();
    	Cell fromCell = possibleCells.get(fromIndex);

    	//GENERATE RANDOM TO CELL
    	List<Cell> possiblbleDestinations = board.getPossibleDestinations(fromCell);
    	int toIndex = ((int) (Math.random() * 999)) % possiblbleDestinations.size();    	
    	Cell toCell = possiblbleDestinations.get(toIndex);
    	
    	//CREATE AND RETURN NEW MOVE
    	Move move = new Move(fromCell, toCell);
    	System.out.print("[" + board.getTurn() + "( Random Agent)] Moving piece " + fromCell.getCoordinate().toString() + " to " + toCell.getCoordinate().toString()+ ".");
    	return move;
    	
    }
}
