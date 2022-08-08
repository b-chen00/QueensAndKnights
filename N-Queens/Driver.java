public class Driver{
	public static void runTest(int i){
  QueenBoard b;
  int[]tests =   {1,2,3,4,5,8};
  int[]answers = {1,0,0,2,10,92};
  if(i >= 0 && i < tests.length ){
    int size = tests[i];
    int correct = answers[i];
    b = new QueenBoard(size);
    int ans  = b.countSolutions();

    if(correct==ans){
      System.out.println("PASS board size: "+tests[i]+" "+ans);
    }else{
      System.out.println("FAIL board size: "+tests[i]+" "+ans+" vs "+correct);
    }
  }
}
  public static void main(String[] args){
runTest(0);
runTest(1);
runTest(2);
runTest(3);
runTest(4);
runTest(5);
/**
    QueenBoard board0 = new QueenBoard(1);
    QueenBoard board1 = new QueenBoard(3);
    QueenBoard board2 = new QueenBoard(4);
    QueenBoard board3 = new QueenBoard(8);
    QueenBoard board4 = new QueenBoard(10);
    QueenBoard board5 = new QueenBoard(0);
    try{
    System.out.println("1 by 1 board");
    System.out.println(board0.toString());

    System.out.println("Solving 1 by 1 board");
    System.out.println(board0.solve());
    System.out.println(board0.toString());

    System.out.println("Counting the solutions");
    System.out.println(board0.countSolutions());

    System.out.println("");

    System.out.println("3 by 3 board");
    System.out.println(board1.toString());

    System.out.println("Solving 3 by 3 board");
    System.out.println(board1.solve());
    System.out.println(board1.toString());

    System.out.println("Counting the solutions");
    System.out.println(board1.countSolutions());

    System.out.println("");

    System.out.println("4 by 4 board");
    System.out.println(board2.toString());

    System.out.println("Solving 4 by 4 board");
    System.out.println(board2.solve());
    System.out.println(board2.toString());

    System.out.println("Counting the solutions");
    System.out.println(board2.countSolutions());

    System.out.println("");

    System.out.println("8 by 8 board");
    System.out.println(board3.toString());

    System.out.println("Solving 8 by 8 board");
    System.out.println(board3.solve());
    System.out.println(board3.toString());

    System.out.println("Counting the solutions");
    System.out.println(board3.countSolutions());
    System.out.println(board3.toString());
    System.out.println("");

    System.out.println("10 by 10 board");
    System.out.println(board4.toString());

    System.out.println("Solving 10 by 10 board");
    System.out.println(board4.solve());
    System.out.println(board4.toString());

    System.out.println("Counting the solutions");
    System.out.println(board4.countSolutions());

    System.out.println("");

    System.out.println("0 by 0 board");
    System.out.println(board5.toString());
    //System.out.println("Solving 0 by 0 board");
    //System.out.println(board5.solve());
    //System.out.println(board5.toString());
    System.out.println("Counting the solutions");
    System.out.println(board5.countSolutions());
  }
  catch (IllegalStateException e){
    System.out.println(e.getMessage());
  }
**/
  }

}
