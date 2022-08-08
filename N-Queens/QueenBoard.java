public class QueenBoard{
	private int[][] board;

	public QueenBoard(int size){
		board = new int[size][size];
		for (int r = 0; r < size; r++){
			for (int c = 0; c < size; c++){
				board[r][c] = 0; //0 is a blank spot
			}
		}
	}

	private boolean addQueen(int r, int c){
		if (board[r][c] == 0){
			board[r][c] = -1; // -1 is a queen
			for (int i = 0; i < board.length; i++){
				if (i != r){
				board[i][c] = board[i][c] + 1;
			}
			}
			for (int k = 0; k < board[r].length; k++){
				if (k != c){
				board[r][k] = board[r][k] + 1;
			}
			}

			int space = 1;
			for (int j = c + 1; j < board[r].length; j++){
				if (r + space < board.length){
					board[r + space][j] = board[r + space][j] + 1;
					//space++;
				}
				if (r - space >= 0){
					board[r - space][j] = board[r-space][j] + 1;
					//space++;
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

	private boolean removeQueen(int r, int c){
		if (board[r][c] == -1){
			board[r][c] = 0;
			for (int i = 0; i < board.length; i++){
				if (i != r){
				board[i][c] = board[i][c] - 1;
			}
		}
			for (int k = 0; k < board[r].length; k++){
				if (k != c){
				board[r][k] = board[r][k] - 1;
			}
		}
			int space = 1;
			for (int j = c + 1; j < board[r].length; j++){
				if (r + space < board.length){
					board[r + space][j] = board[r + space][j] - 1;
					//space++;
				}
				if (r - space >= 0){
					board[r - space][j] = board[r-space][j] - 1;
					//space++;
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

	public String toString(){
		String ans = "";
		for (int r = 0; r < board.length; r++){
			for (int c = 0; c < board[r].length; c++){
				if (board[r][c] == -1){
					ans += "Q ";
				}
				else{
					ans += "_ ";//board[r][c] + " ";//"_ ";
				}
			}
			ans += "\n";
		}
		return ans;
	}

	public boolean solve(){
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
		return solveHelper(0);
	}

	private boolean solveHelper(int row){ //goes row by row and col by col and check if its avaliable
		if (check()){
			return true;
		}

		for (int c = 0; c < board[row].length; c++){
			if (board[row][c] == 0){
				addQueen(row,c);
				if (solveHelper(row+1)){
					return true;
				}
				removeQueen(row,c);
			}
		}
		return false;
	}


	private boolean check(){
		boolean found = false;
		//checks every row
		for (int r = 0; r < board.length; r++){
			found = false;
			for (int c = 0; c < board[r].length; c++){
				if (board[r][c] == -1){
					found = true;
				}
				if (c == board[r].length - 1 && found == false){
					return false;
				}
			}
		}
		found = false;
		//checks every column
		for (int c = 0; c < board[0].length; c++){
			for (int r = 0; r < board.length; r++){
				if (board[r][c] == -1){
					found = true;
				}
				if (r == board.length - 1 && found == false){
					return false;
				}
			}
		}
		return found;
	}

	public int countSolutions(){
		for (int r = 0; r < board.length; r++){
			for (int c = 0; c < board[r].length;c++){
				if (board[r][c] != 0){
					throw new IllegalStateException("Board is not clear");
				}
			}
		}
		if (board.length == 0){
			return 0;
		}
		return countSolutionsHelper(0);
		//for (int r = 0; r < board.length; r++){
			//for (int c = 0; c < board[r].length; c++){
				//board[r][c] = 0;
			//}
		//}
	}


	public int countSolutionsHelper(int row) {
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
