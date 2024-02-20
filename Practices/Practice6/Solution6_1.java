package Practice6;

import java.math.BigInteger;
import java.util.Scanner;

/**
 * Calculates the greatest common divisor of two integers recursively
 * and prints the number of function calls required to calculate them
 */
public class Solution6_1
{
	/**
	 * Gets data from standard input
	 */
	private Scanner input = null;

	/**
	 * Number of function calls requierd to calculate the gcd
	 */
	private long callCount = 0;

	/**
	 * Start the execution of the solution
	 * @param args Command line arguments
	 */
	public static void main(String[] args)
	{
		Solution6_1 solution = new Solution6_1();
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
		while ( this.input.hasNextInt() )
		{
      final long number1 = this.input.nextLong();
      final long number2 = this.input.nextLong();

      printResults(number1, number2, MCD(abs(number1), abs(number2)));
      this.callCount = 0;
		}

		// Close the standard input
		this.input.close();
	}

  private long abs(final long number) {
    return (number >= 0) ? number : number * -1;
  }

  private long MCD(final long number1, final long number2) {
    ++this.callCount;
    if (number2 == 0) {
      return number1;
    }
    return MCD(number2, number1 % number2);
  }

  private void printResults(final long number1, final long number2, final long mcd) {
    System.out.printf("%,d %,d: %d (%d)\n", number1, number2, mcd, this.callCount);
  }
}
