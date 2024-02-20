package Practice2;

import java.util.Scanner;

/**
 * Calculates the number of tickes for a Christmas raffle
 */
public class Solution2_6
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
		Solution2_6 solution = new Solution2_6();
		solution.run();
	}

	/**
	 * Run the solution. This method is called from main()
	 */
	public void run()
	{
		// Create object to read data from standard input
		this.input = new Scanner(System.in);

        
        while ( this.input.hasNextDouble() ) {

            final double oldPrice = this.input.nextDouble();
            System.out.printf("%.2f", oldPrice);
            
            // Check if the amount is valid
            if ( oldPrice > 0.0 && oldPrice < 100000000.0 ) {
                double newPrice = 111.5 / 113.0 * oldPrice;
                System.out.printf(": %.2f (%.2f)\n", newPrice, oldPrice - newPrice );
            }
            else {
                // The amount is invalid, say so in the output
                System.out.printf(": invalid data: %f\n");
            }
        }
		// Close the standard input
		this.input.close();
	}
}
