package Practice4;

import java.util.Scanner;

/**
 * Read user's old and new pins and indicates if the pins
 * are the same inverting their digits
 */
public class Solution4_2
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
		Solution4_2 solution = new Solution4_2();
		solution.run();
	}

	/**
	 * Run the solution. This method is called from main()
	 */
	public void run()
	{
		// Create object to read data from standard input
		this.input = new Scanner(System.in);

		// First value in stdin indicates the number of digits of each pin
		final int digits = this.input.nextInt();

		// For statistics
		long invertedCount = 0, pin_count = 0;

		// Each line in stdin has a pin change made by a user
		while ( this.input.hasNextLong() )
		{

			// A change is a pair: oldPin newPin
			final long oldPin = this.input.nextLong();
			final long newPin = this.input.nextLong();

			// Determine if user inverted pin digits
			final boolean isInverted = isInverted(oldPin, newPin, digits);

			// Print old and new pins using leading zeros
			// according to number of digits
			printResult(oldPin, newPin, isInverted, digits);

			// For statistics
			++pin_count;
			if ( isInverted )
			{
				++invertedCount;
			}
		}

		// Print the number of inverted pins found, the total number
		// of pins processed, and the percent of inverted pins found
		printStatistics(invertedCount, pin_count);

		// Close the standard input
		this.input.close();
	}

	/**
	 * Returns true if newPin is an inversion of oldPin
	 * @param digits The number of digits that pin numbers should have
	 * @return true if newPin is equals to the inverted digits of oldPin
	 */
	private boolean isInverted(final long oldPin, final long newPin, final int digits)
	{
		return newPin == invertDigits(oldPin, digits);
	}

    private long invertDigits(long number, final int digits) {
        long digit = 0, reverse = 0;

        for (int i = 0; i < digits; ++i) {
            digit = number % 10;
            reverse = 10 * reverse + digit;
            number /= 10;
        }
        return reverse;
    }
    private void printResult(final long oldPin, final long newPin, boolean isInverted, int digits) {
        final String format = "%0" + digits + "d: %0" + digits + "d %d%n";
        System.out.printf(format, oldPin, newPin, isInverted ? 1 : 0);
    }
    private void printStatistics(final long invertedCount, final long pin_count) {
        final long percent = invertedCount * 100 / pin_count;
        System.out.printf("%n%d / %d = %d%%%n", invertedCount, pin_count, percent);
    }
}