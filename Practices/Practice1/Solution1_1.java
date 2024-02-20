package Practice1;
import java.util.Scanner;

/**
 * Extracts three integers from standard input and print them without whitespace
 */
class Solution1_1
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
        Solution1_1 solution = new Solution1_1();
        solution.run();
    }

    /**
     * Run the solution. This method is called from main()
     */
    public void run()
    {
        // Create object to read data from standard input
        this.input = new Scanner(System.in);
        // Write code here to solve the problem
        // Close the standard input
        long num = 0;
        for (int counter = 0; counter < 3; ++counter) {
            num = this.input.nextLong();
            System.out.println(num);
        }
        this.input.close();
    }
}