package Practice2;

import java.util.Scanner;

/**
 * Normalizes hours, minutes and seconds to the range {00, 59}
 */
public class Solution2_2
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
		Solution2_2 solution = new Solution2_2();
		solution.run();
	}

	/**
	 * Run the solution. This method is called from main()
	 */
	public void run()
	{
		// Create object to read data from standard input
		this.input = new Scanner(System.in);
		
    // Modify this code to solve the problem
        
    this.input.useDelimiter("[:\\s]+");
    long hours, minutes, seconds;
    long fix_hours, fix_minutes, fix_seconds;
    
    while ( input.hasNextLong() )
    {
        hours = this.input.nextLong();
        minutes = this.input.nextLong();
        seconds = this.input.nextLong();
        
        fix_seconds = seconds % 60;
        fix_minutes = minutes + seconds/60;
        fix_hours = hours + fix_minutes/60;
        fix_minutes = fix_minutes % 60;
        
        System.out.printf("%02d:%02d:%02d%n", fix_hours, fix_minutes, fix_seconds);
    }

		// Close the standard input
		this.input.close();
	}
}
