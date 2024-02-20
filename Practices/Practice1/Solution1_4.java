package Practice1;

import java.util.Scanner;

/**
 * Read floating point numbers and print them using three common
 * formats: fixed, exponential, and a suggested one automatically
 * chosed from the previous two.
 */
public class Solution1_4
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
		Solution1_4 solution = new Solution1_4();
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
        double value = 0.0;
        while ( this.input.hasNextDouble() )
        {
            value = this.input.nextDouble();
            System.out.printf("%13s%f %n", "fixed: ", value);
            System.out.printf("%13s%e %n", "exponential: ", value);
            System.out.printf("%13s%g %n %n", "preferred: ", value);
        }
		// Close the standard input
		this.input.close();
	}
}
