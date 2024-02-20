import java.util.Scanner;

/**
 * Prints electric stairs which size is indicated by standard input
 */
class Solution3_3
{
	/**
	 * Gets data from standard input
	 */
	private Scanner input = null;

	/**
	 * Start the execution of the solution
	 * @param args Command line arguments
	 */
	public static void main(String[] args)
	{
		Solution3_3 solution = new Solution3_3();
		solution.run();
	}

   /**
     * Run the solution. This method is called from main()
     */
    public void run()
    {
        // Create object to read data from standard input
        this.input = new Scanner(System.in);

        // This code replicates the input to the standard output
        // Modify this code to solve the problem
        char shape1  = ' ', shape2  = ' ';
        int attempts = 0, wins1 = 0, wins2 = 0, draws = 0;
        double percent1 = 0.0, percent2 = 0.0, percentDraw = 0.0, totalPercent = 100.0;
        while (this.input.hasNext()) {
            shape1 = Character.toUpperCase(this.input.next().charAt(0));
            shape2 = Character.toUpperCase(this.input.next().charAt(0));

            if (shape1 != 'R' && shape1 != 'P' && shape1 != 'S') 
                System.out.printf("invalid shape: %c\n", shape1);
            else if (shape2 != 'R' && shape2 != 'P' && shape2 != 'S')
                System.out.printf("invalid shape: %c\n", shape2);
            
            else if ( ((shape1 == 'R' && shape2 == 'S')) 
                    || ((shape1 == 'P' && shape2 == 'R')) 
                    || ((shape1 == 'S' && shape2 == 'P')) ) 
                ++wins1;
            else if ( ((shape2 == 'R' && shape1 == 'S')) 
                    || ((shape2 == 'P' && shape1 == 'R')) 
                    || ((shape2 == 'S' && shape1 == 'P')) ) 
                ++wins2;
            else if (shape1 == shape2)
                ++draws;
        }  // end while
        attempts = wins1 + wins2 + draws;
        percent1 = attempts > 0 ? 100.0 * wins1 / attempts : 0.0;
        System.out.printf("Player1: %3d %3.0f%%%n", wins1, percent1);

        percent2 = attempts > 0 ? 100.0 * wins2 / attempts : 0.0;
        System.out.printf("Player1: %3d %3.0f%%%n", wins2, percent1);
        
        percentDraw = attempts > 0 ? 100.0 * draws / attempts : 0.0;
        System.out.printf("  Draws: %3d %3.0f%%%n", draws, percentDraw);

        totalPercent = attempts > 0 ? 100.0 * attempts / attempts : 0.0;
        System.out.printf("  Total: %3d %3.0f%%%n%n", attempts, totalPercent);

        // Report final winner or draw
        if ( wins1 > wins2 )
            System.out.printf(" Winner: Player1%n");
        else if ( wins2 > wins1 )
            System.out.printf(" Winner: Player2%n");
        else
            System.out.printf("   Draw!%n");
        // Close the standard input
        this.input.close();
    }
}