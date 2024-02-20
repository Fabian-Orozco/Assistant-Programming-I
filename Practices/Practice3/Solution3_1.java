import java.util.Scanner;

/**
 * Prints electric stairs which size is indicated by standard input
 */
class Solution3_1
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
		Solution3_1 solution = new Solution3_1();
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
        int size = this.input.nextInt();
        if (size > 0) {
            final String format = "%" + size + "s\n";
            for (int current_step = 0; current_step <= size; ++current_step) {
                String row = "";
                for (int counter = 0; counter < current_step; ++counter) {
                    row += "#";
                }
                System.out.printf(format, row);
            }
        }

        // Close the standard input
        this.input.close();
    }
}