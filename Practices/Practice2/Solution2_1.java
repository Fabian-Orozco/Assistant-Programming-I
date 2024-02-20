


package Practice2;

import java.util.Scanner;

/**
 * Add line numbers to the input
 */
public class Solution2_1
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
		Solution2_1 solution = new Solution2_1();
		solution.run();
	}

	/**
	 * Run the solution. This method is called from main()
	 */
	public void run()
	{
		// Create object to read data from standard input
		this.input = new Scanner(System.in);
		int width = input.nextInt();
		Boolean addZero = ( input.nextInt() == 0 ) ? true : false ;
		String line = "";
		String format = "";
		
		int counter = 1;
		line = input.nextLine();
		while ( input.hasNextLine() ){
				line = input.nextLine();
				
				format = addZero ? "%0" + width + "d" : "%" + width + "d";
				System.out.printf(format, counter);
				System.out.println(" " + line);
				counter++;            
		}
		// Modify this code to solve the problem
 
		// Close the standard input
		this.input.close();
	}
}
