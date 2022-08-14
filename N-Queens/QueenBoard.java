/**
* Represents a chess board with N-Queens related functions.
* @author Brandon Chen
*/
public class QueenBoard{
	private int[][] board;

	/**
	* Initializes a chess board of a specific size with every blank spot being represented by 0.
	* @param size the size of the board's length and width.
	*/
	public QueenBoard(int size){
		board = new int[size][size];
		for (int r = 0; r < size; r++){
			for (int c = 0; c < size; c++){
				board[r][c] = 0; //0 is a blank spot
			}
		}
	}

	/**
	* Adds a queen onto the board at a specific location and adjusts the board accordingly.
	* @param r the row that the new queen should be placed at.
	* @param c the column that the new queen should be placed at.
	* @return a boolean whether the queen was added or not.
	*/
	private boolean addQueen(int r, int c){
		//Checks if the spot is empty.
		if (board[r][c] == 0){
			board[r][c] = -1; // -1 represents a queen.
			//Mark all areas that this queen attacks as unusable by making it a positive number.
			//Vertical attacks.
			for (int i = 0; i < board.length; i++){
				if (i != r){
					board[i][c] = board[i][c] + 1;
				}
			}
			//Horizontal attacks.
			for (int k = 0; k < board[r].length; k++){
				if (k != c){
					board[r][k] = board[r][k] + 1;
				}
			}
			//Diagonal attacks
			int space = 1;
			for (int j = c + 1; j < board[r].length; j++){
				if (r + space < board.length){
					board[r + space][j] = board[r + space][j] + 1;
				}
				if (r - space >= 0){
					board[r - space][j] = board[r-space][j] + 1;
				}
				space++;
			}
			space = 1;
			for (int h = c - 1; h >= 0; h--){
				if (r + space < board.length){
					board[r + space][h] = board[r + space][h] + 1;
				}
				if (r - space >= 0){
					board[r - space][h] = board[r - space][h] + 1;
				}
				space++;
			}
			return true;
		}
		return false;
	}

	/**
	* Removes the queen from the board at a specific location.
	* @param r the row that the queen should be removed from.
	* @param c the column that the queen should be removed from.
	* @return a boolean whether the queen was removed or not.
	*/
	private boolean removeQueen(int r, int c){
		//Checks if the spot is occupied by a queen.
		if (board[r][c] == -1){
			board[r][c] = 0;
			//Unmark all areas that this queen attacks as usable by making it a decrement but not necessarily free.
			//Vertical attacks.
			for (int i = 0; i < board.length; i++){
				if (i != r){
					board[i][c] = board[i][c] - 1;
				}
			}
			//Horizontal attacks.
			for (int k = 0; k < board[r].length; k++){
				if (k != c){
					board[r][k] = board[r][k] - 1;
				}
			}
			//Diagonal attacks.
			int space = 1;
			for (int j = c + 1; j < board[r].length; j++){
				if (r + space < board.length){
					board[r + space][j] = board[r + space][j] - 1;
				}
				if (r - space >= 0){
					board[r - space][j] = board[r-space][j] - 1;
				}
				space++;
			}
			space = 1;
			for (int h = c - 1; h >= 0; h--){
				if (r + space < board.length){
					board[r + space][h] = board[r + space][h] - 1;
				}
				if (r - space >= 0){
					board[r - space][h] = board[r - space][h] - 1;
				}
				space++;
			}
			return true;
		}
		return false;
	}

	/**
	* A String representation of the board showing queen placements.
	* @return a String representing the board and its queens.
	*/
	public String toString(){
		String ans = "";
		for (int r = 0; r < board.length; r++){
			for (int c = 0; c < board[r].length; c++){
				if (board[r][c] == -1){
					ans += "Q ";
				}
				else{
					ans += "_ ";
				}
			}
			ans += "\n";
		}
		return ans;
	}

	/**
	* Solves the N-Queen problem and throws an exception if the board isn't clear.
	* @param display a boolean determining whether the board solution should be printed.
	* @exception IllegalStateException if the board is not empty.
	* @returns true if a solution was found and false otherwise.
	*/
	public boolean solve(boolean display){
		if (board.length == 0){
			return false;
		}
		for (int r = 0; r < board.length; r++){
			for (int c = 0; c < board[r].length;c++){
				if (board[r][c] != 0){
					throw new IllegalStateException("Board is not clear");
				}
			}
		}
		return solveHelper(0, display);
	}

	/**
	* A helper function for solve in order to solve the N-Queens problem.
	* @param row the row that queens will start to be placed on. There should only be one queen per row due to how they attack.
	* @return true if a solution was found and false otherwise.
	*/
	private boolean solveHelper(int row, boolean display){ //goes row by row and col by col and check if its avaliable
		if (check()){
			if (display){
				System.out.println(this.toString());
			}
			//clears the board.
			board = new int[board.length][board[0].length];
			return true;
		}
		for (int c = 0; c < board[row].length; c++){
			if (board[row][c] == 0){
				addQueen(row,c);
				if (solveHelper(row+1, display)){
					return true;
				}
				removeQueen(row,c);
			}
		}
		return false;
	}

	/**
	*	Checks the board and see if the N-Queen is completed. A solution is found when there is only 1 queen in every row and column.
	* @return true if the board has only one queen in every row and column. Returns false otherwise.
	*/
	private boolean check(){
		boolean found = false;
		//checks every row
		for (int r = 0; r < board.length; r++){
			found = false;
			for (int c = 0; c < board[r].length; c++){
				if (board[r][c] == -1){
					if (!found){
						//first queen in this row is found.
						found = true;
					}
					else{
						//there should only be one queen per row.
						return false;
					}
				}
				//if the end of the row is reached and no queens are found then return false.
				if (c == board[r].length - 1 && found == false){
					return false;
				}
			}
		}
		//checks every column
		for (int c = 0; c < board[0].length; c++){
			found = false;
			for (int r = 0; r < board.length; r++){
				if (board[r][c] == -1){
					if (!found){
						//first queen in this column is found.
						found = true;
					}
					else{
						//there should only be one queen per column.
						return false;
					}
				}
				if (r == board.length - 1 && found == false){
					return false;
				}
			}
		}
		return true;
	}

	/**
	*	Counts the number of N-Queens solutions for this specific size board.
	* @exception IllegalStateException if the board is not empty.
	* @return an integer of the number of solutions for the N-Queens problem.
	*/
	public int countSolutions(){
		for (int r = 0; r < board.length; r++){
			for (int c = 0; c < board[r].length;c++){
				if (board[r][c] != 0){
					throw new IllegalStateException("Board is not clear");
				}
			}
		}
		//There is no solution if there is no board.
		if (board.length == 0){
			return 0;
		}
		return countSolutionsHelper(0);
	}

	/**
 	* Helper function to count the number of solutions to the N-Queens problem.
	* @param row the starting row from which the queen will be placed.
	* @return an integer representing the number of solutions.
	*/
	private int countSolutionsHelper(int row) {
		int count = 0;
		if (row >= board.length) {
			return 1;
		}
		for (int i = 0; i < board.length; i++) {
			if (addQueen(row, i)) {
				count += countSolutionsHelper(row + 1);
				removeQueen(row, i);
			}
		}
		return count;
	}
}
