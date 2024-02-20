package Practice1;

import java.util.Scanner;
import java.util.concurrent.atomic.DoubleAdder;

import javax.swing.SortingFocusTraversalPolicy;

/**
 * Read double precision numbers from standard input and print them
 * to standard output rounded to 2 decimals in fixed notation
 */
public class Solution1_6
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
		Solution1_6 solution = new Solution1_6();
		solution.run();
	}

	/**
	 * Run the solution. This method is called from main()
	 */
	public void run()
	{
		// Create object to read data from standard input
		this.input = new Scanner(System.in);

		double amount = 0.0; //Variable for storing the read amount
        double newAmount = 0.0; //Variable to calculate the new amount with taxes
        double difference = 0.0; //Variable to store the difference between original amount and the new amount with taxes
        final double taxPercent = 111.5 / 113.0;
        
        // Modify this code to solve the problem
        while ( input.hasNextDouble())
        {
            //Read the value from standar input file
            amount = input.nextDouble();
            
            //Print the read amount value
            System.out.printf("%.2f: ", amount);
            
            //Verify if the read amount is valid in range
            if ((amount >= 0.0) && (amount <= 100000000.0))
            {
                //Calulate the new tax amount
                newAmount = amount * taxPercent;
                difference = amount - newAmount;
                System.out.printf("%.2f (%.2f)\n", newAmount, difference);
            }
            else
            {
                //Othervise, input data in not valid.
                System.out.println("invalid data");        
            }
        }
		// Close the standard input
		this.input.close();
	}
}

