import java.util.Scanner;

/**
 * Calculates the coins to give as exchange for some price
 */
public class Solution4_4
{
	/**
	 * Gets data from standard input
	 */
	private Scanner input = null;

	/**
	 * Start the execution of the solution
	 *
	 * @param args Command line arguments
	 */
	public static void main(String[] args)
	{
		Solution4_4 solution = new Solution4_4();
		solution.run();
	}

	/**
	 * Run the solution. This method is called from main()
	 */
	public void run()
	{
		// Create object to read data from standard input
		this.input = new Scanner(System.in);

		// Read the fare required to travel in this bus line
		final long fare = this.input.nextLong();

		// Print a table header to show payments and their exchanges
		printHeader(fare);

		// Read each payment
		while ( this.input.hasNextLong() )
		{
			// Process the payment and print its row in the table
			printPayment( this.input.nextLong(), fare);
		}

		// Close the standard input
		this.input.close();
	}
  void printPayment(long payment , final long fare) {
    System.out.printf("%5d", payment);
    if (payment >= fare) {
      printBreakdown(payment - fare);
    } else {
      System.out.printf("%5s", (payment > 0) ? " insuf" : " error");
    }
    System.out.println();
  }

  void printBreakdown(long exchange) {
    System.out.printf(" %5d", exchange);
    exchange = printExchangeCoin(exchange, 500);
    exchange = printExchangeCoin(exchange, 100);
    exchange = printExchangeCoin(exchange, 50);
    exchange = printExchangeCoin(exchange, 25);
    exchange = printExchangeCoin(exchange, 10);
    exchange = printExchangeCoin(exchange, 5);
  }

  void printHeader(final long fare) {
    if (fare > 0) {
      System.out.printf("%5d%n", fare);
      System.out.printf(" PAGO VUELT 500 100  50  25  10   5\n");
      System.out.printf("----- ----- --- --- --- --- --- ---\n");
    } else {
      System.out.printf("%5d error\n", fare);
      System.exit(0);
    }
  }
	/**
	 * Print the number of required coins of the given denomination. If no coins
	 * of this denomination are required, an empty field is printed instead.
	 *
	 * @param change The amount of money to give as exchange
	 * @param denomination The coin denomination, e.g: 500 or 25
	 *
	 * @return The exchange after subtract the coins for this denomiation
	 * @example printExchangeCoin(1700, 500) will print `   3` because 3 coins
	 * of 500 colones are required to give 3x500=1500, and it will return 200
	 * because 200 colones are left to be exchanged with other denominations.
	 */
	long printExchangeCoin(long exchange, long denomination)
	{
		// Calculate the number of required coins for this denomination
		final long coins = exchange / denomination;

		// If there are to give coins, print them, otherwise print an empy field
		if ( coins > 0 )
			System.out.printf(" %3d", coins);
		else
			System.out.printf("    ");

		// Return the remaining money to exchange with other denominations
		return exchange % denomination;
	}
}