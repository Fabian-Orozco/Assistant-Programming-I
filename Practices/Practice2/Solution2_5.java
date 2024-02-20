package Practice2;

import java.util.Scanner;

/**
 * Calculates the number of tickes for a Christmas raffle
 */
public class Solution2_5
{
	/**
	 * Gets data from standard input
	 */
	private Scanner input = null;

	/**
	 * Start the execution of the solution
	 * @param args Command line arguments
	 */
	public static void main(String args[])
	{
		Solution2_5 solution = new Solution2_5();
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
        
        // Get the ticket value from the first line
        final double ticketValue = this.input.nextDouble();

        // While there are purchases left
        while ( this.input.hasNextDouble() ) {
            // Read the amount of money purchased and print it
            final double amount = this.input.nextDouble();
            System.out.printf("%11.2f ", amount);

            // Check if the amount is valid
            if ( amount > 0 && amount <= 10000000 ) {
                // Calculate the tickets by dividing the purchase amount
                // and the ticket value. Round the tickets down by getting
                // rid of the decimals using a type cast from double to long
                long tickets = (long)(amount / ticketValue);
                System.out.printf("%5d%n", tickets);
            }
            else {
                // The amount is invalid, say so in the output
                System.out.printf("  err%n");
            }
        }
		// Close the standard input
		this.input.close();
	}
}
