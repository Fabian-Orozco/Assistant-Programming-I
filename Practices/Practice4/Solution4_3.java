package Practice4;
import java.util.Scanner;

/**
 * Print question texts and numeric fields for answering them
 */
public class Solution4_3 {
    /**
     * Gets data from standard input
     */
    private Scanner input;
  
    /**
     * Holds the text of the latest read question
     */
    private String questionText;
  
    /**
     * Start the execution of the solution
     * @param args Command line arguments
     */
    public static void main(String args[])
    {
      Solution4_3 solution = new Solution4_3();
      solution.run();
    }
    	/**
	 * Run the solution. This method is called from main()
	 */
	public void run()
	{
		// Create object to read data from standard input
		input = new Scanner(System.in);

		// The page width is the first number in the input
		int pageWidth = input.nextInt();

		// Read a question per line
		input.useDelimiter("[\\s\\[\\],]+");
		while ( input.hasNextLong() )
		{
			// Read the range for the question
			long rangeMin = input.nextLong();
			long rangeMax = input.nextLong();
			input.skip("\\] ");

			// Call other methods here to solve the problem
            readQuestionText();
            printQuestionText();
            
            int qtyNumberField = Math.max(countDigits(rangeMax), countDigits(rangeMin));
            int qtySeparator = pageWidth - getQuestionTextLength() - getFieldLength(qtyNumberField);
            printSeparator('.', qtySeparator);
            printNumberField(qtyNumberField);
            System.out.println();
		}

		// Remember to always close the input
		input.close();
	}

    
	/**
	 * Reads the question text from stdin
	 * @a returns true on success, false on error
	 */
	public boolean readQuestionText()
	{
		questionText = input.nextLine();
		return true;
	}

	/**
	 * Prints the last read question text to stdout
	 */
	void printQuestionText()
	{
		System.out.print(questionText);
	}

	/**
	 * Returns the length of the last read question text
	 */
	int getQuestionTextLength()
	{
		return questionText.length();
	}

	/**
	 * Returns the number of digits within number.
	 * For example, 1000 has 4 digits, 0 has 1 digit, -17 has 3 digits
	 * @return The number of digits, including negatives
	 */
	int countDigits(long number)
	{
		int digits = 1;
		if(number < 0)
			++digits;
		for( number /= 10; number != 0; number /= 10 )
			++digits;
		return digits;
	}

	/**
	 * Returns the length in characters required by a field for storing digits
	 * For example, the field for 2 digits is "|__|__|" and its length is 7 characters
	 * @return The length of the field to store the given amount of digits
	 */
	int getFieldLength(int digits)
	{
		return 1 + digits * 3;
	}

	/**
	 * Prints the separator character filling a field of @a width characters.
	 * A leading and trailing space character are added automatically within the field
	 * For example print_separator('.', 7) will print " ..... "
	 */
	void printSeparator(char separator, int width)
	{
		System.out.print(' ');
		width -= 2;
		while ( width-- > 0 )
			System.out.print(separator);
		System.out.print(' ');
	}

	/**
	 * Prints a box for storing numbers of @a digits digits in stdout
	 * For example print_number_field(4) will print "|__|__|__|__|"
	 */
	void printNumberField(int digits)
	{
		if ( digits > 0 )
		{
			System.out.print('|');
			while ( digits-- != 0)
				System.out.printf("__|");
		}
	}
}
