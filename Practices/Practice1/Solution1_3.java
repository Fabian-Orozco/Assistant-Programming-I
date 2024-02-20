package Practice1;

import java.util.Scanner;

/**
 * Read double precision numbers from standard input and print only
 * their integer part to standard output, this is, truncating its decimals
 */
public class Solution1_3
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
        Solution1_3 solution = new Solution1_3();
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
        while ( this.input.hasNextLine() )
        {
            System.out.printf("%d%n", (long) this.input.nextDouble());
        }
        // Close the standard input
        this.input.close();
    }
}
