package Practice1;

import java.util.Scanner;

/**
 * Read numbers from standard input, interpret them as
 * ASCII codes, and print them as characters.
 */
public class Solution1_7
{
	/**
	 * Gets data from standard input
	 */
	private Scanner input = null;

	/**
	 * Start the execution of the solution7
	 * @param args Command line arguments
	 */
	public static void main(String args[])
	{
		Solution1_7 solution = new Solution1_7();
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
			while ( this.input.hasNextInt() ) {
					System.out.printf("%c", this.input.nextInt());
			}

		// Close the standard input
		this.input.close();
	}
}
