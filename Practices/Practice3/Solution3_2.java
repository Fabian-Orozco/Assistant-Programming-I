import java.util.Scanner;

/**
 * Prints electric stairs which size is indicated by standard input
 */
class Solution3_2
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
		Solution3_2 solution = new Solution3_2();
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
        while (this.input.hasNextInt()) {

            int value = this.input.nextInt();
            int modify = value;
            int steps = 0;
            if (value > 0) {
            
                while (modify != 1) {
                    if (modify%2 == 0) {  // es par
                        modify /= 2;
                    } else {  // es impar
                        modify = modify * 3 + 1;
                    }
                    ++steps;
                }
                System.out.printf("%d: %d\n", value, steps);
            }
        }
        // Close the standard input
        this.input.close();
    }
}