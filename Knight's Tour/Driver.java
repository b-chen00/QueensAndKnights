public class Driver{

  //testcase must be a valid index of your input/output array
  public static void runTest(int i){

    KnightBoard b;
    int[]m =   {4,5,5,5,5};
    int[]n =   {4,5,4,5,5};
    int[]startx = {0,0,0,1,2};
    int[]starty = {0,0,0,1,2};
    int[]answers = {0,304,32,56,64};
    if(i >= 0 ){
      try{
        int correct = answers[i];
        b = new KnightBoard(m[i%m.length],n[i%m.length]);

        int ans  = b.countSolutions(startx[i],starty[i]);

        if(correct==ans){
          System.out.println("PASS board size: "+m[i%m.length]+"x"+n[i%m.length]+" "+ans);
        }else{
          System.out.println("FAIL board size: "+m[i%m.length]+"x"+n[i%m.length]+" "+ans+" vs "+correct);
        }
      }catch(Exception e){
        System.out.println("FAIL Exception case: "+i);

      }
    }
  }


	public static void main(String[] args){
    runTest(0);
    runTest(1);
    runTest(2);
    runTest(3);
    runTest(4);
/**
		KnightBoard board1 = new KnightBoard(1,1);
		KnightBoard board2 = new KnightBoard(2,2);
		KnightBoard board3 = new KnightBoard(3,3);
		KnightBoard board4 = new KnightBoard(4,4);
		KnightBoard board5 = new KnightBoard(5,5);
		KnightBoard board6 = new KnightBoard(6,6);
		KnightBoard board7 = new KnightBoard(7,7);
		KnightBoard board8 = new KnightBoard(8,8);
		KnightBoard board9 = new KnightBoard(37,37);
		//System.out.println(board4.toString());

	//	System.out.println(board1.solve(0,0));
		//System.out.println

		System.out.println(board2.solve(0,0));
	  System.out.println(board3.solve(0,0));
		System.out.println(board4.solve(0,0));
		System.out.println(board5.solve(0,0));
		System.out.println(board6.solve(0,0));
		System.out.println(board7.solve(0,0));
		System.out.println(board8.solve(0,0));
		System.out.println(board9.solve(0,0));

//	System.out.println(board5.countSolutions(2,2));
		//runTest(4);
		//System.out.println(board4.toString());
		//System.out.println(board5.countSolutions(0,0));
		//System.out.println(board5.toString());
		//System.out.println(board1.toStringOutGoingMoves());
		**/
		String time;
		System.out.println("SOLVE: (from (0, 0))\n");
		for (int r = 1; r < 100; r++) {
			KnightBoard b = new KnightBoard(r, r);
			long startTime = System.nanoTime();
			try {
				if (r == 51 || r == 52)
					time = "skipped";
				else if (b.solve(0, 0))
					time = Math.round((System.nanoTime()-startTime)/10000.)/100.+"ms";
				else
					time = "unsolvable";
				System.out.printf("\t%dx%d:\t%s%n", r, r, time);
			} catch (StackOverflowError e) {
				System.out.printf("\t%dx%d:\tstack overflow%n", r, r);
				break;
			}
		}
		System.out.println("\nCOUNT SOLUTIONS: (from (0, 0))\n");
		for (int r = 1; r < 6; r++) {
			for (int c = r; c < 6; c++) {
				KnightBoard b = new KnightBoard(r, c);
				long startTime = System.nanoTime();
				try {
					int n = b.countSolutions(0, 0);
					time = Math.round((System.nanoTime()-startTime)/10000.)/100.+"ms";
					System.out.printf("\t%dx%d:\t%d solutions\t\t%s%n", r, c, n, time);
				} catch (StackOverflowError e) {
					System.out.printf("\t%dx%d:\tstack overflow%n", r, c);
					break;
				}
			}
		}
		
	}
}
