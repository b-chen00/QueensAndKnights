


/////////////////////////////////////////////////////////////////
/**
import java.util.ArrayList;
import java.util.List;
import java.util.*;
public class KnightBoard{
	private int[][] board;
	private int[][] move;
	private List<Integer> values = new ArrayList();
	private int[][] coord;
	//private boolean justsolved = false;
	//private int count = 0;
	public KnightBoard(int startingRows,int startingCols){
		if (startingRows < 0 ||startingCols < 0){
			throw new IllegalArgumentException("No Negatives");
		}

		board = new int[startingRows][startingCols];
		for (int r = 0; r < board.length; r++){
			for (int c = 0; c < board[r].length; c++){
				board[r][c] = 0;
			}
		}

		move = new int[startingRows][startingCols];
		int moveNumber = 0;
		for (int r = 0; r < board.length; r++){
				for (int c = 0; c < board[r].length; c++){
					moveNumber = 0;
					if (r > 1 && c > 0){
						moveNumber++;
					}
					if (r > 1 && c < board[r].length - 1){
						moveNumber++;
					}
					if (r > 0 && c > 1){
						moveNumber++;
					}
					if (r > 0 && c < board[r].length - 2){
						moveNumber++;
					}
					if (r < board.length - 1 && c > 1){
						moveNumber++;
					}
					if (r < board.length - 1 && c < board[r].length -2){
						moveNumber++;
					}
					if (r < board.length - 2 && c > 0){
						moveNumber++;
					}
					if (r < board.length - 2 && c < board[r].length - 1){
						moveNumber++;
					}
					move[r][c] = moveNumber;
				}
				moveNumber = 0;
		}
	}

	public String toStringOutGoingMoves(){
		String ans = "";
		for (int r = 0; r < move.length;r++){
			for (int c = 0; c < move[r].length;c++){
				ans += " " + move[r][c];
			}
			ans += "\n";
		}
		return ans;
	}

	public String toString(){
		String ans = "";
		for (int r = 0; r < board.length; r++){
			for (int c = 0; c < board[r].length; c++){
				if (board[r][c] == 0){
					ans += "   ";
				}
				else if (board[r][c] > 9){
					ans += "" + board[r][c] + " ";
				}
				else if (board[r][c] <= 9){
					ans += " " + board[r][c] + " ";
				}
			}
			ans += "\n";
		}
		return ans;
	}

	private void updateBoard(int row, int col,int amount){
		if (row > 1 && col > 0){// && board[row-2][col-1] == 0){ // up 2 left 1
			move[row-2][col-1] += amount;
		}
		if (row > 1 && col < board[row].length - 1){// && board[row-2][col+1] == 0){  // up 2 right 1
			move[row-2][col+1] += amount;
		}
		if (row > 0 && col > 1 ){//&& board[row-1][col-2] == 0){ // up 1 left 2
			move[row-1][col-2] += amount;
		}
		if (row > 0 && col < board[row].length - 2){// && board[row-1][col+2] == 0){ //up 1 right 2
			move[row-1][col+2] += amount;
		}
		if (row < board.length - 1 && col > 1){// && board[row+1][col-2] == 0){ // down 1 left 2
			move[row+1][col-2] += amount;
		}
		if (row < board.length - 1 && col < board[row].length -2){// && board[row+1][col+2] == 0){ // down 1 right 2
			move[row+1][col+2] += amount;
		}
		if (row < board.length - 2 && col > 0 && board[row + 2][col-1] == 0 ){//&& board[row+2][col-1] == 0){ // down 2 left 1
			move[row+2][col-1] += amount;
		}
		if (row < board.length - 2 && col < board[row].length - 1){// && board[row+2][col+1] == 0){ // down 2 right 1
			move[row+2][col+1] += amount;
		}
		//System.out.println("YES");
		//System.out.println(toStringOutGoingMoves());
	}
	public void addKnight(int row,int col,int level){
		if (board[row][col] == 0){
			//System.out.println("YES");
			board[row][col] = level;
			updateBoard(row,col,-1);
		}

	}

	public void removeKnight(int row, int col, int level){
		if (board[row][col] != 0){
			board[row][col] = 0;
			updateBoard(row,col,1);
		}
	}
//yay
	public boolean solve(int startingRow, int startingCol){
		for (int r = 0; r < board.length; r++){
			for (int c = 0; c < board[r].length;c++){
				if (board[r][c] != 0){
					throw new IllegalStateException("Board is not clear");
				}
			}
		}
		if (startingRow < 0 || startingCol < 0 || startingRow > board.length-1 || startingCol > board[startingRow].length - 1){
			throw new IllegalArgumentException("Out of bounds");
		}
		return solveHelper(startingRow,startingCol, 1);
	}

	public void fill(int row, int col){
		values = new ArrayList<>();
		//fills in the possible move's outgoing moves to later sort
		if (row > 1 && col > 0 && board[row-2][col-1] == 0){ // up 2 left 1
			values.add(move[row-2][col-1]);
		}
		if (row > 1 && col < board[row].length - 1 && board[row-2][col+1] == 0){  // up 2 right 1
			values.add(move[row-2][col+1]);
		}
		if (row > 0 && col > 1 && board[row-1][col-2] == 0){ // up 1 left 2
			values.add(move[row-1][col-2]);
		}
		if (row > 0 && col < board[row].length - 2 && board[row-1][col+2] == 0){ //up 1 right 2
			values.add(move[row-1][col+2]);
		}
		if (row < board.length - 1 && col > 1 && board[row+1][col-2] == 0){ // down 1 left 2
			values.add(move[row+1][col-2]);
		}
		if (row < board.length - 1 && col < board[row].length -2 && board[row+1][col+2] == 0){ // down 1 right 2
			values.add(move[row+1][col+2]);
		}
		if (row < board.length - 2 && col > 0 && board[row + 2][col-1] == 0 && board[row+2][col-1] == 0){ // down 2 left 1
			values.add(move[row+2][col-1]);
		}
		if (row < board.length - 2 && col < board[row].length - 1 && board[row+2][col+1] == 0){ // down 2 right 1
			values.add(move[row+2][col+1]);
		}
		//sorting outgoingmoves
		Collections.sort(values);
		//-------------------------------------------------------------------------------
		coord = new int[2][8]; //row 0 is x and row 1 is y
		//int currentIndex = 0;
		int index;
		if (row > 1 && col > 0 && board[row-2][col-1] == 0){ // up 2 left 1
			index = values.indexOf(move[row-2][col-1]);
			coord[0][index] = row - 2;
			coord[1][index] = col - 1;
		}
		if (row > 1 && col < board[row].length - 1 && board[row-2][col+1] == 0){  // up 2 right 1
			index = values.indexOf(move[row-2][col+1]);
			coord[0][index] = row - 2;
			coord[1][index] = col + 1;
		}
		if (row > 0 && col > 1 && board[row-1][col-2] == 0){ // up 1 left 2
			index = values.indexOf(move[row-1][col-2]);
			coord[0][index] = row - 1;
			coord[1][index] = col - 2;
		}
		if (row > 0 && col < board[row].length - 2 && board[row-1][col+2] == 0){ //up 1 right 2
			index = values.indexOf(move[row-1][col+2]);
			coord[0][index] = row - 1;
			coord[1][index] = col + 2;
		}
		if (row < board.length - 1 && col > 1  && board[row+1][col-2] == 0){ // down 1 left 2
			index = values.indexOf(move[row+1][col-2]);
			coord[0][index] = row + 1;
			coord[1][index] = col - 2;
		}
		if (row < board.length - 1 && col < board[row].length -2 && board[row+1][col+2] == 0){ // down 1 right 2
			index = values.indexOf(move[row+1][col+2]);
			coord[0][index] = row + 1;
			coord[1][index] = col + 2;
		}
		if (row < board.length - 2 && col > 0 && board[row + 2][col-1] == 0 && board[row+2][col-1] == 0){ // down 2 left 1
			index = values.indexOf(move[row+2][col-1]);
			coord[0][index] = row + 2;
			coord[1][index] = col - 1;
		}
		if (row < board.length - 2 && col < board[row].length - 1 && board[row+2][col+1] == 0 && board[row+2][col+1] == 0){ // down 2 right 1
			index = values.indexOf(move[row+2][col+1]);
			coord[0][index] = row + 2;
			coord[1][index] = col + 1;
		}
	}


private boolean solveHelper(int row, int col, int level){
	//possibleMo
	if (level == board.length * board[0].length){
		return true;
	}
	//boolean starting = true;
	//System.out.println(values.size() + "");
	for (int i = 0; i < numberOfMoves(row,col) ;  i++){
		//starting = false;
		//System.out.println("YES");
		if (board[row][col] == 0){
			//System.out.println("YES");
			addKnight(row,col,level);
			fill(row,col);
			if (solveHelper(coord[0][i],coord[1][i],level+1)){
				return true;
			}
			removeKnight(row,col,level);
		}
	}
	return false;
}

private int numberOfMoves(int row, int col){
	int ans = 0;
	if (row > 1 && col > 0 && board[row-2][col-1] == 0){// && board[row-2][col-1] == 0){ // up 2 left 1
		ans++;
	}
	if (row > 1 && col < board[row].length - 1 && board[row-2][col+1] == 0){// && board[row-2][col+1] == 0){  // up 2 right 1
		ans++;
	}
	if (row > 0 && col > 1 && board[row-1][col-2] == 0){ // up 1 left 2
		ans++;
	}
	if (row > 0 && col < board[row].length - 2 && board[row-1][col+2] == 0){ //up 1 right 2
		ans++;
	}
	if (row < board.length - 1 && col > 1 && board[row+1][col-2] == 0){ // down 1 left 2
		ans++;
	}
	if (row < board.length - 1 && col < board[row].length -2 && board[row+1][col+2] == 0){ // down 1 right 2
		ans++;
	}
	if (row < board.length - 2 && col > 0 && board[row + 2][col-1] == 0 && board[row+2][col-1] == 0){ // down 2 left 1
		ans++;
	}
	if (row < board.length - 2 && col < board[row].length - 1 && board[row+2][col+1] == 0){ // down 2 right 1
		ans++;
	}
	return ans;
}
/**
	private boolean solveHelper(int row, int col, int level){
		if (board[row][col] == 0){
			board[row][col] = level;
		}
		if (solved()){
			return true;
		}
		// checks for movement
		if (row > 1 && col > 0 && board[row-2][col-1] == 0){ // up 2 left 1
			if (solveHelper(row-2,col-1,level + 1)){
				return true;
			}
			board[row-2][col-1] = 0;
		}
		if (row > 1 && col < board[row].length - 1 && board[row-2][col+1] == 0){  // up 2 right 1
			if (solveHelper(row-2,col+1,level+1)){
				return true;
			}
			board[row-2][col+1] = 0;
		}
		if (row > 0 && col > 1 && board[row-1][col-2] == 0){ // up 1 left 2
			if (solveHelper(row-1,col-2,level+1)){
				return true;
			}
			board[row-1][col-2] = 0;
		}
		if (row > 0 && col < board[row].length - 2 && board[row-1][col+2] == 0){ //up 1 right 2
			if (solveHelper(row-1,col+2,level+1)){
				return true;
			}
			board[row-1][col+2] = 0;
		}
		if (row < board.length - 1 && col > 1 && board[row+1][col-2] == 0){ // down 1 left 2
			if (solveHelper(row + 1, col - 2,level+1)){
				return true;
			}
			board[row+1][col-2] = 0;
		}
		if (row < board.length - 1 && col < board[row].length -2 && board[row + 1][col+2] == 0){ // down 1 right 2
			if (solveHelper(row + 1, col + 2,level+1)){
				return true;
			}
			board[row+1][col+2] = 0;
		}
		if (row < board.length - 2 && col > 0 && board[row + 2][col-1] == 0){ // down 2 left 1
			if (solveHelper(row + 2, col -1, level+1)){
				return true;
			}
			board[row+2][col-1] = 0;
		}
		if (row < board.length - 2 && col < board[row].length - 1 && board[row+2][col+1] == 0){ // down 2 right 1
			if (solveHelper(row + 2, col + 1,level +1)){
				return true;
			}
			board[row+2][col+1] = 0;
		}
		return false;
	}
**/
/**
	private boolean solved(){
		boolean ans = true;
		for (int r = 0; r < board.length; r++){
			for (int c = 0; c < board[r].length; c++){
				if (board[r][c] < 1){
					ans = false;
				}
			}
		}
		return ans;
	}

	public int countSolutions(int startingRow, int startingCol){
		for (int r = 0; r < board.length; r++){
			for (int c = 0; c < board[r].length;c++){
				if (board[r][c] != 0){
					throw new IllegalStateException("Board is not clear");
				}
			}
		}
		if (startingRow < 0 || startingCol < 0 || startingRow > board.length-1 || startingCol > board[0].length - 1){
			throw new IllegalArgumentException("Out of bounds");
		}
		return countSolutionsHelper(startingRow,startingCol,1,0);
		}

	public int countSolutionsHelper(int row, int col, int level, int ans){
		//System.out.println(toString());
	//	System.out.println("-------------------");
		if (level == board.length * board[0].length){//solved() && !(justsolved) && (count != 1)){
			ans += 1;
		//	justsolved = true;
		//	count = 0;
		//	System.out.println(ans + "!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
			return ans;
		}
		//count++;
		//justsolved = false;
				if (row > 1 && col > 0 && board[row][col] == 0 && board[row-2][col-1] == 0){ //up 2 left 1
				//	System.out.println(1);
				//	System.out.println(solved());
					board[row][col] = level;
					ans = countSolutionsHelper(row-2,col-1,level+1,ans);
					board[row][col] = 0;
				}



				if (row > 1 && col < board[row].length - 1 && board[row][col] == 0 && board[row-2][col+1] == 0){ //up 2 right 1
				//	System.out.println(2);
					board[row][col] = level;
					ans = countSolutionsHelper(row-2,col+1,level+1,ans);
					board[row][col] = 0;
				}



				if (row > 0 && col > 1 && board[row][col] == 0 && board[row-1][col-2] == 0){ //left 2 up 1
				//	System.out.println(3);
					board[row][col] = level;
					ans = countSolutionsHelper(row-1,col-2,level+1,ans);
					board[row][col] = 0;
				}



				if (row > 0 && col < board[row].length - 2 && board[row][col] == 0 && board[row-1][col+2] == 0){ //right 2 up 1
				//	System.out.println(4);
					board[row][col] = level;
					ans = countSolutionsHelper(row-1,col+2,level+1,ans);
					board[row][col] = 0;
				}



				if (row < board.length - 1 && col > 1 && board[row][col] == 0 && board[row+1][col-2] == 0){ //down 1 left 2
				//	System.out.println(5);
					board[row][col] = level;
					ans = countSolutionsHelper(row+1,col-2,level+1,ans);
					board[row][col] = 0;
				}



				if (row < board.length - 1 && col < board[row].length - 2 && board[row][col] == 0 && board[row+1][col+2] == 0 ){ //down 1 right 2-----
				//	System.out.println(6);
					board[row][col] = level;
					ans = countSolutionsHelper(row+1,col+2,level+1,ans);
					board[row][col] = 0;
				}



				if (row < board.length - 2 && col > 0 && board[row][col] == 0 && board[row+2][col-1] == 0 ){ // down 2 left 1
				//	System.out.println(7);
					board[row][col] = level;
					ans = countSolutionsHelper(row+2,col-1,level+1,ans);
					board[row][col] = 0;
				}



				if (row < board.length - 2 && col < board[row].length - 1 && board[row][col] == 0 && board[row+2][col+1] == 0 ){ // down 2 right 1
				//	System.out.println(8);
					board[row][col] = level;
					ans = countSolutionsHelper(row+2,col+1,level+1,ans);
					board[row][col] = 0;
				}



		return ans;
	}
/**
public int countSolutionsHelper(int row, int col, int level, int ans){
	//base case
	//System.out.println(ans);
	if (board[row][col] == 0){
		board[row][col] = level;
	}
	if (solved()){
		ans += 1;
		return ans;
	}
	//recursion
	if (row > 1 && col > 0 && board[row-2][col-1] == 0){ // up 2 left 1
		if (countSolutionsHelper(row-2,col-1,level + 1,ans) > 0){
			ans += countSolutionsHelper(row-2,col-1,level + 1,ans);
		}
		//board[row-2][col-1] = 0;
	}
	if (row > 1 && col < board[row].length - 1 && board[row-2][col+1] == 0){  // up 2 right 1
		if (countSolutionsHelper(row-2,col+1,level+1,ans) > 0){
			ans += countSolutionsHelper(row-2,col+1,level+1,ans);
		}
		//board[row-2][col+1] = 0;
	}
	if (row > 0 && col > 1 && board[row-1][col-2] == 0){ // up 1 left 2
		if (countSolutionsHelper(row-1,col-2,level+1,ans) > 0){
			ans += countSolutionsHelper(row-1,col-2,level+1,ans);
		}
		//board[row-1][col-2] = 0;
	}
	if (row > 0 && col < board[row].length - 2 && board[row-1][col+2] == 0){ //up 1 right 2
		if (countSolutionsHelper(row-1,col+2,level+1,ans) > 0){
			ans += countSolutionsHelper(row-1,col+2,level+1,ans);
		}
		//board[row-1][col+2] = 0;
	}
	if (row < board.length - 1 && col > 1 && board[row+1][col-2] == 0){ // down 1 left 2
		if (countSolutionsHelper(row + 1, col - 2,level+1,ans) > 0){
			ans += countSolutionsHelper(row + 1, col - 2,level+1,ans);
		}
		//board[row+1][col-2] = 0;
	}
	if (row < board.length - 1 && col < board[row].length -2 && board[row + 1][col+2] == 0){ // down 1 right 2
		if (countSolutionsHelper(row + 1, col + 2,level+1,ans) > 0){
			ans += countSolutionsHelper(row + 1, col + 2,level+1,ans);
		}
		//board[row+1][col+2] = 0;
	}
	if (row < board.length - 2 && col > 0 && board[row + 2][col-1] == 0){ // down 2 left 1
		if (countSolutionsHelper(row + 2, col -1, level+1,ans) > 0){
			ans += countSolutionsHelper(row + 2, col -1, level+1,ans);
		}
		//board[row+2][col-1] = 0;
	}
	if (row < board.length - 2 && col < board[row].length - 1 && board[row+2][col+1] == 0){ // down 2 right 1
		if (countSolutionsHelper(row + 2, col + 1,level +1,ans) > 0){
			ans += countSolutionsHelper(row + 2, col + 1,level +1,ans);
		}
		//board[row+2][col+1] = 0;
	}
	board[row][col] = 0;
	return ans;
}
/**
 	public int countSolutionsHelper(int row, int col, int level,int ans){
		if (solved()){
			ans += 1;
			return ans;
		}
		//System.out.println("" + ans);
		if (board[row][col] == 0){
			board[row][col] = level;
		// checks for movement
		if (row > 1 && col > 0 && board[row-2][col-1] == 0){ // up 2 left 1
			//if (countSolutionsHelper(row-2,col-1,level + 1,ans) > 0){
				ans += countSolutionsHelper(row-2,col-1,level + 1,ans);
		//	}
			board[row-2][col-1] = 0;
		}
		if (row > 1 && col < board[row].length - 1 && board[row-2][col+1] == 0){  // up 2 right 1
			//if (countSolutionsHelper(row-2,col+1,level+1,ans) > 0){
				ans += countSolutionsHelper(row-2,col+1,level+1,ans);
		//	}
			board[row-2][col+1] = 0;
		}
		if (row > 0 && col > 1 && board[row-1][col-2] == 0){ // up 1 left 2
		//	if (countSolutionsHelper(row-1,col-2,level+1,ans) > 0){
				ans += countSolutionsHelper(row-1,col-2,level+1,ans);
	//		}
			board[row-1][col-2] = 0;
		}
		if (row > 0 && col < board[row].length - 2 && board[row-1][col+2] == 0){ //up 1 right 2
		//	if (countSolutionsHelper(row-1,col+2,level+1,ans) > 0){
				ans += countSolutionsHelper(row-1,col+2,level+1,ans);
		//	}
			board[row-1][col+2] = 0;
		}
		if (row < board.length - 1 && col > 1 && board[row+1][col-2] == 0){ // down 1 left 2
	//		if (countSolutionsHelper(row + 1, col - 2,level+1,ans) > 0){
				ans += countSolutionsHelper(row + 1, col - 2,level+1,ans);
		//	}
			board[row+1][col-2] = 0;
		}
		if (row < board.length - 1 && col < board[row].length -2 && board[row + 1][col+2] == 0){ // down 1 right 2
	//		if (countSolutionsHelper(row + 1, col + 2,level+1,ans) > 0){
				ans += countSolutionsHelper(row + 1, col + 2,level+1,ans);
	//		}
			board[row+1][col+2] = 0;
		}
		if (row < board.length - 2 && col > 0 && board[row + 2][col-1] == 0){ // down 2 left 1
		//	if (countSolutionsHelper(row + 2, col -1, level+1,ans) > 0){
				ans += countSolutionsHelper(row + 2, col -1, level+1,ans);
	//		}
			board[row+2][col-1] = 0;
		}
		if (row < board.length - 2 && col < board[row].length - 1 && board[row+2][col+1] == 0){ // down 2 right 1
		//	if (countSolutionsHelper(row + 2, col + 1,level +1,ans) > 0){
				ans += countSolutionsHelper(row + 2, col + 1,level +1,ans);
		//	}
			board[row+2][col+1] = 0;
		}
		board[row][col] = 0;
	}
		return ans;
	}
	**/



//}


import java.util.Arrays;

public class KnightBoard{
	private int[][] board;
	public int[][] board2;
	public int[][] move = {{1,-2},{2,1},{2,-1},{-1,2},{-1,-2},{-2,1},{-2,-1},{1,2}};
	public int count;
	public KnightBoard(int rows,int cols) {
		count = 0;
		board = new int[rows][cols];
		board2 = new int[rows][cols];
		fills();
	}

	public void fills() {
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

	public boolean solve(int startRow, int startCol) {
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
			return false;
		}
		return solveHelper(startRow,startCol,1);
	}

	private boolean solveHelper(int r, int c, int pos) {
		if(pos == board.length * board[0].length) {
			board[r][c] = pos;
			return true;
		}
		int[][] moves = getMoves(r,c);
		board[r][c] = pos;
		for(int i = 0; i < moves.length; i++) {
			if(solveHelper(moves[i][1],moves[i][2],pos+1)) {
				return true;
			}
		}
		board[r][c] = 0;
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
