import java.text.Normalizer.Form;
import java.util.Scanner;

/**
 * Print the minimum number of page turns from front or back
 * of a book to a desired page number
 */
public class Solution4_1
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
		Solution4_1 solution = new Solution4_1();
		solution.run();
	}

	/**
	 * Run the solution. This method is called from main()
	 */
	public void run()
	{
		// Create object to read data from standard input
		this.input = new Scanner(System.in);

		// Read all pair of numbers from standard input
		while ( this.input.hasNextLong() )
		{
			// Each line contains the book's pages and target page
			final long pages = input.nextLong();
			final long targetPage = input.nextLong();

			// Print the minimum amount of turns to the target page
			printMinPageTurns(pages, targetPage);
		}

		// Close the standard input
		this.input.close();
	}

  private void printMinPageTurns(long pages, final long targetPage) {
		System.out.printf("%d %d: ", pages, targetPage );

        if (inputIsValid(pages, targetPage)) {
            if (pages % 2 == 0)
                ++pages;

            final long forward = targetPage / 2;
            final long backward = (pages - targetPage) / 2;

            System.out.printf("%d\n", forward <= backward ? forward : -backward);
        } else {
            System.out.printf("invalid data\n");
        }
  }

    // Write your methods here
    private boolean inputIsValid(final long pages, final long targetPage) {
        return targetPage > 0 && pages >= targetPage;
    }

}