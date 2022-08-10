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
