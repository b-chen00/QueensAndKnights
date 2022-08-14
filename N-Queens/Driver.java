import java.util.Scanner;

public class Driver{
	public static void runTest(int i){
	  QueenBoard b;
	  int[]tests = {1,2,3,4,5,8};
	  int[]answers = {1,0,0,2,10,92};
	  if (i >= 0 && i < tests.length){
	    int size = tests[i];
	    int correct = answers[i];
	    b = new QueenBoard(size);
	    int ans = b.countSolutions();

	    if(correct==ans){
	      System.out.println("PASS board size: "+tests[i]+" with "+ ans + "answers.");
	    }
			else{
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
		System.out.println("\n========================================================");
    System.out.println("CUSTOM TESTS");
    System.out.println("========================================================");
    Scanner s = new Scanner(System.in);
    boolean exit = false;
    while(!exit){
      System.out.println("Enter the size of your board:");
      int size = 0;
      boolean valid = false;
      while (!valid){
        try{
          size = Integer.parseInt(s.nextLine());
          valid = true;
          System.out.println();
        } catch (NumberFormatException e){
          System.out.println("Invalid input. Try again.");
          continue;
        }
      }
			QueenBoard b = new QueenBoard(size);
			if (b.solve(true)){
				long startTime = System.nanoTime();
				System.out.println("There are " + b.countSolutions() + " solutions found in " + Math.round((System.nanoTime()-startTime)/10000.)/100.+"ms.\n");
			}
			System.out.println("Enter 'exit' to exit the program otherwise enter any key to continue.");
			if (s.nextLine().toLowerCase().equals("exit")){
				exit = true;
			}
		}
  }
}
