package Practice1;
import java.util.Scanner;

/**
 * Encloses lines from standard input within [ brackets ]
 * in order to make whitespace visible
 */
public class Solution1_2
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
        Solution1_2 solution = new Solution1_2();
        solution.run();
    }

    /**
     * Run the solution. This method is called from main()
     */
    public void run()
    {
        // Create object to read data from standard input
        while (this.input.hasNextLine()) {
            System.out.printf("[%s]%n", this.input.nextLine());
        }
// Close the standard input
        this.input.close();
    }
}
