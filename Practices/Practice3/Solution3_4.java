import java.util.Scanner;

/**
 * Prints electric stairs which size is indicated by standard input
 */
class Solution3_4
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
		Solution3_4 solution = new Solution3_4();
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
        final double baseAmount = this.input.nextDouble();
        double totalAidAmount = 0.0;
        while (this.input.hasNext()) {
            final int civilStatus = this.input.nextInt();
            final int qtyOfChildren = this.input.nextInt();

            double aidAmount = 0.0;

            for (int child = 1; child <= qtyOfChildren; ++ child) {
                int childAge = this.input.nextInt();
                double childsAmount = 0.0;
                if (childAge < 18) {
                    childsAmount = baseAmount * Math.pow(2, 1 - child);
                } 
                if (childAge >= 6 && childAge <= 12) {
                    childsAmount *= 1.5;
                }
                aidAmount += childsAmount;
            }
            if (civilStatus == 1 || civilStatus == 5) {
                aidAmount *= 1.25;
            }
            totalAidAmount += aidAmount;
            System.out.printf("%.2f%n", aidAmount);
        }
        System.out.printf("%n%.2f%n", totalAidAmount);

        // Close the standard input
        this.input.close();
    }
}