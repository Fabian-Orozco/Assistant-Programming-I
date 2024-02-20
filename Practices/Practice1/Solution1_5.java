package Practice1;
import java.io.PrintStream;
import java.util.Scanner;

/**
 * Format dates prepending leading zeros to years, months and days
 */
class Solution1_5
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
		Solution1_5 solution = new Solution1_5();
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
			this.input.useDelimiter("[/\\s]+");
			int year, month, day;
			while ( this.input.hasNextLine() )
			{
					year = this.input.nextInt();
					month = this.input.nextInt();
					day = this.input.nextInt();
					// AAAA/MM/DD
					System.out.printf("%04d/%02d/%02d%n", year, month, day);
					break;
			}
			// Close the standard input
			this.input.close();
	}
}
