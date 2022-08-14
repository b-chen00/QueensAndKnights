import java.util.Scanner;

public class Driver{

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
        System.out.println("Testing board of size " + String.valueOf(m[i]) + " by " + String.valueOf(n[i]) + " with starting location (" + String.valueOf(startx[i]) + "," + String.valueOf(starty[i]) + ")");
        System.out.println("Expected answer: " + String.valueOf(ans));
        System.out.println("Got answer: " + String.valueOf(answers[i]));
        if(correct == ans){
          System.out.println("PASS board size: " + m[i%m.length] + "x" + n[i%m.length] + " " + ans + "\n");
        }
        else{
          System.out.println("FAIL board size: "+m[i%m.length]+ "x" + n[i%m.length] + " " + ans + " vs " + correct + "\n");
        }
      }
      catch(Exception e){
        System.out.println("FAIL Exception case: "+i);
      }
    }
  }


	public static void main(String[] args){
    System.out.println("========================================================");
    System.out.println("TESTING BASE CASES");
    System.out.println("========================================================");
    runTest(0);
    runTest(1);
    runTest(2);
    runTest(3);
    runTest(4);
    System.out.println("All base cases passed.\n");

		String time;
    System.out.println("========================================================");
		System.out.println("SOLVE: (from (0, 0))");
    System.out.println("========================================================");
		for (int r = 1; r < 100; r++) {
			KnightBoard b = new KnightBoard(r, r);
			long startTime = System.nanoTime();
			try {
				if (b.solve(0, 0, false)){
					time = Math.round((System.nanoTime()-startTime)/10000.)/100.+"ms";
        }
				else{
					time = "unsolvable";
        }
				System.out.printf("\t%dx%d:\t%s%n", r, r, time);
			} catch (StackOverflowError e) {
				System.out.printf("\t%dx%d:\tstack overflow%n", r, r);
				break;
			}
		}
    System.out.println("========================================================");
		System.out.println("COUNT SOLUTIONS: (from (0, 0))");
    System.out.println("========================================================");
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
    System.out.println("\n========================================================");
    System.out.println("CUSTOM TESTS");
    System.out.println("========================================================");
    Scanner s = new Scanner(System.in);
    boolean exit = false;
    while(!exit){
      System.out.println("Enter the number of rows on the board:");
      int r = 0, c = 0, startR = 0, startC = 0;
      boolean valid = false;
      while (!valid){
        try{
          r = Integer.parseInt(s.nextLine());
          valid = true;
          System.out.println();
        } catch (NumberFormatException e){
          System.out.println("Invalid input. Try again.");
          continue;
        }
      }
      System.out.println("Enter the number of columns on the board:");
      valid = false;
      while (!valid){
        try{
          c = Integer.parseInt(s.nextLine());
          valid = true;
          System.out.println();
        } catch (NumberFormatException e){
          System.out.println("Invalid input. Try again.");
          continue;
        }
      }
      System.out.println("Enter the starting row:");
      valid = false;
      while (!valid){
        try{
          startR = Integer.parseInt(s.nextLine());
          valid = true;
          System.out.println();
        } catch (NumberFormatException e){
          System.out.println("Invalid input. Try again.");
          continue;
        }
      }
      System.out.println("Enter the starting column:");
      valid = false;
      while (!valid){
        try{
          startC = Integer.parseInt(s.nextLine());
          valid = true;
          System.out.println();
        } catch (NumberFormatException e){
          System.out.println("Invalid input. Try again.");
          continue;
        }
      }
      KnightBoard b = new KnightBoard(r, c);
      boolean solvable = b.solve(startR, startC, true);
      if (!solvable){
        System.out.println("Board of size " + String.valueOf(r) + " by " + String.valueOf(c)+ " with starting location (" + String.valueOf(startR) + ", " + String.valueOf(startC) + ") is not solvable.");
      }
      else{
        long startTime = System.nanoTime();
        int numOfSolutions = b.countSolutions(startR, startC);
        System.out.println("There are " + String.valueOf(numOfSolutions) + " solutions found in " + Math.round((System.nanoTime()-startTime)/10000.)/100.+"ms\n.");
      }
      System.out.println("Enter 'exit' to exit the program otherwise enter any key to continue.");
      if (s.nextLine().toLowerCase().equals("exit")){
        exit = true;
      }
    }
  }
}
