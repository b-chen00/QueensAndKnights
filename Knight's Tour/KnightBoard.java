import java.util.Arrays;

/**
* Represents a chess board with Knight's Tour related functions.
* @author Brandon Chen
*/
public class KnightBoard{
	//the board that the knight will tranverse to see if a solution is valid
	private int[][] board;
	//this board contains an integer at every position which indicates how many valid moves can be made by a knight here
	private int[][] board2;
	//the different ways a knight can move
	private int[][] move = {{1,-2},{2,1},{2,-1},{-1,2},{-1,-2},{-2,1},{-2,-1},{1,2}};
	// number of solutions
	private int count;

	/**
	* initalizes knightboard and fills in initial state of board2
	* @param rows how many rows the board has
	* @param cols how many columns the board has
	*/
	public KnightBoard(int rows,int cols) {
		count = 0;
		board = new int[rows][cols];
		board2 = new int[rows][cols];
		fills();
	}

	/**
	* Fills the board2 moves which indicates the the number of valid moves that can be made by a knight at a location.
	*/
	private void fills() {
		if(board.length > 0) {
			board2 = new int[board.length][board[0].length];
		}
		for(int r = 0; r < board2.length; r++) {
			for(int c = 0; c < board2[r].length; c++) {
				for(int n = 0; n < move.length; n++) {
					if(r+move[n][0] >= 0 && r+move[n][0] < board2.length && c+move[n][1] >= 0 &&
					c+move[n][1] < board2[0].length && board[r+move[n][0]][c+move[n][1]] == 0){
						board2[r][c]++;
					}
				}
			}
		}
	}

	/**
	* Solves the knight's tour starting at position startRow and startCol.
	* @param startRow the row from which the knight will start the tour.
	* @param startCol the column from which the knight will start the tour.
	* @return a boolean indicating whether it is solvable or not.
	*/
	public boolean solve(int startRow, int startCol) {
		//starting row and col should be a valid index.
		if(startRow < 0 || startCol < 0) {
			throw new IllegalArgumentException();
		}
		for(int r = 0; r < board2.length; r++) {
			for(int c = 0; c < board2[r].length; c++) {
				if(board[r][c] != 0) {
					//the board should be completely empty.
					throw new IllegalStateException();
				}
			}
		}
		//there is no solution if there is no board.
		if(board.length == 0) {
			return false;
		}
		return solveHelper(startRow,startCol,1);
	}

	/**
	* A helper function for solve to compute a solution.
	* @param r the row that the next knight will be placed on.
	* @param c the column that the next knight will be placed on.
	* @param pos the number of the knight that is being place.
	* @return a boolean on whether a solution is found or not.
	*/
	private boolean solveHelper(int r, int c, int pos) {
		if(pos == board.length * board[0].length) {
			//a solution is found as the number of knights equal the total numbers of spots on the board.
			board[r][c] = pos;
			return true;
		}
		// gets all legal moves that can be made by the knight at position (r,c).
		int[][] moves = getMoves(r,c);
		// place the knight at (r,c)
		board[r][c] = pos;
		// update board2 because, by placing a knight, there should be at most 8 places with 1 less legal move that can be made by a knight at that location.
		for (int i = 0; i < move.length; i++) {
			if (move[i][0] + r < board.length && move[i][0] >= 0 && move[i][1] + c < board[0].length && move[i][1] + c >= 0 && board2[move[i][0] + r][move[i][1] + c] > 0){
				board2[move[i][0] + r][move[i][1] + c]--;
			}
		}
		for(int i = 0; i < moves.length; i++) {
			//attempt every possible move from board[r][c] and see if a solution can be found.
			if(solveHelper(moves[i][1],moves[i][2],pos+1)) {
				return true;
			}
		}
		//backtracks if a solution isn't found by unplacing the knight and updating board2 since a spot has been freed and another legal move could be made from surrounding positions.
		board[r][c] = 0;
		for (int i = 0; i < move.length; i++) {
			if (move[i][0] + r < board.length && move[i][0] >= 0 && move[i][1] + c < board[0].length && move[i][1] + c >= 0){
				board2[move[i][0] + r][move[i][1] + c]++;
			}
		}
		//clears the board.
		board = new int[board.length][board[0].length];
		return false;
	}

	/**
	* Counts how many different tours can be made by the knight starting at a specific row and column.
	* @param startRow the row that the knight will start the tour on.
	* @param startCol the column that the knight will start the tour on.
	* @return the number of solutions or unique tours found as an integer.
	*/
	public int countSolutions(int startRow, int startCol) {
		if(startRow < 0 || startCol < 0) {
			//starting row and col should be a valid index.
			throw new IllegalArgumentException();
		}
		for(int r = 0; r < board2.length; r++) {
			for(int c = 0; c < board2[r].length; c++) {
				if(board[r][c] != 0) {
					//the board should be completely empty.
					throw new IllegalStateException();
				}
			}
		}
		//there is no solution if there is no board.
		if(board.length == 0) {
			return 0;
		}
		count = 0;
		countSolutionHelper(startRow,startCol,1);
		return count;
	}

	/**
	* A helper function for countSolution that attempts a tour and increases count if it is a solution.
	* @param r the row that the next knight will be placed on.
	* @param c the column that the next knight will be placed on.
	* @param pos the number of the knight that is being place.
	*/
	private void countSolutionHelper(int r, int c, int pos) {
		if(pos == board.length * board[0].length) {
			//a solution is found as the number of knights equal the total numbers of spots on the board.
			count++;
		}
		int[][] moves = getMoves(r,c); //getMoves sort the 2D array but there isn't a need here as all indices will be parsed through regardless.
		board[r][c] = pos;
		//board2 could be updated here but that isn't necessary as getMoves contains a check for legal moves so knights will never move to an illegal/tranversed spot.
		//This function counts solution so it doesn't matter how many legal moves can be moved from a specific location but rather if it is a possible solution.
		for(int i = 0; i < moves.length; i++) {
			countSolutionHelper(moves[i][1],moves[i][2],pos+1);
		}
		board[r][c] = 0;
	}

	/**
	* Determines all the possible legal moves that can be made by the knight at (r,c).
	* @param r the row from which we are trying to determine whether a legal move can be made from.
	* @param c the column from which we are trying to determine whether a legal move can be made from.
	* @return an 2D array where the first column is the number of legal moves that can be made at a position that (r,c) can legally move to.
	* The second column contains the row number of that position while the third column contains its column number.
	*/
	private int[][] getMoves(int r, int c) {
		fills();
		int[][] moves = new int[board2[r][c]][3];
		int i = 0;
		if(board2[r][c] > 0) {
			for(int n = 0; n < move.length; n++) {
				//checks if a possible move from (r,c) is legal in the respect that it is within the boundaries of the board and doesn't hold a knight already (ie. previously past by the knight in its tour).
				if(r+move[n][0] >= 0 && r+move[n][0] < board2.length && c+move[n][1] >= 0 && c+move[n][1] < board2[0].length && board[r+move[n][0]][c+move[n][1]] == 0) {
					moves[i][0] = board2[r+move[n][0]][c+move[n][1]];
					moves[i][1] = r+move[n][0];
					moves[i][2] = c+move[n][1];
					i++;
				}
			}
			//sorts all legal moves so that moves to positions with the lowest accessibility at placed at the front.
			return sort(moves);
		}
		return moves;
	}

	/**
	* A merge sort implementation to sort the 2D array of moves and their row/col number.
	* @param ary the 2D array of moves that is being sorted.
	* @return a new sorted array.
	*/
	private int[][] sort(int[][] ary) {
		if(ary.length == 1) {
			return ary;
		}
		else {
			int n = 0;
			int m = ary.length/2;
			//recursively splits the array in half until all that is left are a bunch of length 1 arrays.
			//these length 1 arrays are sorted and progressively merged together to created a sorted array.
			int[][] temp1 = new int[m][3];
			int[][] temp2 = new int[ary.length-m][3];
			for(int i = 0; i < temp1.length; i++) {
				temp1[i] = ary[n];
				n++;
			}
			for(int i = 0; i < temp2.length; i++) {
				temp2[i] = ary[n];
				n++;
			}
			return merge(sort(temp1),sort(temp2));
		}
	}

	/**
	* Merges two 2D arrays of moves such that they are sorted.
	* @param ary1 the first sorted array that is being sorted with the second.
	* @param ary2 the second sorted array that is being sorted with the first.
	* @return a new sorted array containing all elements of both ary1 and ary2.
	*/
	private int[][] merge(int[][] ary1,int[][] ary2) {
		int[][] out = new int[ary1.length+ary2.length][3];
		int o = 0;
		int t = 0;
		int n = 0;
		for(;n < out.length; n++) {
			//checks if all elements are ary1 are sort
			if(o == ary1.length) {
				//ary1 are all in the output so all that is left are elements of ary2.
				for(int i = n; i < out.length; i++) {
					out[i] = ary2[t];
					t++;
				}
				return out;
			}
			//checks if all elements are ary2 are sort
			if(t == ary2.length) {
				//ary2 are all in the output so all that is left are elements of ary1.
				for(int i = n; i < out.length; i++) {
					out[i] = ary1[o];
					o++;
				}
				return out;
			}
			//checks the next/lowest elements of both array and determines which is the lowest thus being put into the output array.
			if(ary1[o][0] >= ary2[t][0]) {
				out[n] = ary2[t];
				t++;
			}
			else {
				if(ary1[o][0] < ary2[t][0]) {
					out[n] = ary1[o];
					o++;
				}
			}
		}
		return out;
	}

	/**
	* Returns the current state of the board as a String.
	* @return a string representation of the baord.
	*/
	public String toString() {
		String out = "";
		for(int r = 0; r < board.length; r++) {
			for(int c = 0; c < board[r].length; c++) {
				if(board[r][c] < 10) {
				out += " " + board[r][c] + " ";
				}
				else {
					out += board[r][c] + " ";
				}
			}
			out += "\n";
		}
		return out;
	}
}
