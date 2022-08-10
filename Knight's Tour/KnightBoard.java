import java.util.Arrays;

/** Represents a chess board with functions.
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
			board[r][c] = pos;
			return true;
		}
		int[][] moves = getMoves(r,c);
		board[r][c] = pos;
		for(int i = 0; i < moves.length; i++) {
			//attempt every possible move from board[r][c] and see if a solution can be found.
			if(solveHelper(moves[i][1],moves[i][2],pos+1)) {
				return true;
			}
		}
		//backtracks if a solution isn't found.
		board[r][c] = 0;
		//clears the board.
		board = new int[board.length][board[0].length];
		return false;
	}

	public int countSolutions(int startRow, int startCol) {
		if(startRow < 0 || startCol < 0) {
			throw new IllegalArgumentException();
		}
		for(int r = 0; r < board2.length; r++) {
			for(int c = 0; c < board2[r].length; c++) {
				if(board[r][c] != 0) {
					throw new IllegalStateException();
				}
			}
		}
		if(board.length == 0) {
			return 0;
		}
		count = 0;
		countSolutionHelper(startRow,startCol,1);
		return count;
	}

	private void countSolutionHelper(int r, int c, int pos) {
		if(pos == board.length * board[0].length) {
			count++;
		}
		int[][] moves = getMoves(r,c);
		board[r][c] = pos;
		for(int i = 0; i < moves.length; i++) {
			countSolutionHelper(moves[i][1],moves[i][2],pos+1);
		}
		board[r][c] = 0;
	}

	public int[][] getMoves(int r, int c) {
		fills();
		int[][] moves = new int[board2[r][c]][3];
		int i = 0;
		if(board2[r][c] > 0) {
			for(int n = 0; n < move.length; n++) {
				if(r+move[n][0] >= 0 && r+move[n][0] < board2.length && c+move[n][1] >= 0 && c+move[n][1] < board2[0].length && board[r+move[n][0]][c+move[n][1]] == 0) {
					moves[i][0] = board2[r+move[n][0]][c+move[n][1]];
					moves[i][1] = r+move[n][0];
					moves[i][2] = c+move[n][1];
					i++;
				}
			}
			return sort(moves);
		}
		return moves;
	}

	private int[][] sort(int[][] ary) {
		if(ary.length == 1) {
			return ary;
		}
		else {
			int n = 0;
			int m = ary.length/2;
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

	private int[][] merge(int[][] ary1,int[][] ary2) {
		int[][] out = new int[ary1.length+ary2.length][3];
		int o = 0;
		int t = 0;
		int n = 0;
		for(;n < out.length; n++) {
			if(o == ary1.length) {
				for(int i = n; i < out.length; i++) {
					out[i] = ary2[t];
					t++;
				}
				return out;
			}
			if(t == ary2.length) {
				for(int i = n; i < out.length; i++) {
					out[i] = ary1[o];
					o++;
				}
				return out;
			}
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
	 * Prints the current state of the board as a String.
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
