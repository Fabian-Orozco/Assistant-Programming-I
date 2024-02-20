package Practice2;

import java.util.Scanner;

/**
 * Helps tourists to calculate tips for services
 */
public class Solution2_7
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
        Solution2_7 solution = new Solution2_7();
        solution.run();
    }

    /**
     * Run the solution. This method is called from main()
     */
    public void run()
    {
        // Create object to read data from standard input
        this.input = new Scanner(System.in);

        // Read sales tax and exchange rate from first line
        final double salesTax = this.input.nextDouble();
        final double exchangeRate = this.input.nextDouble();

        // Read prices, one per line until EOF
        double taxedSum = 0.0, untaxedSum = 0.0;
        while ( this.input.hasNext() )
        {
            // Read the taxable symbol and the price of some purchase
            final char taxable = this.input.next().charAt(0);
            final double price = this.input.nextDouble();

            // If this price is not tax exent, apply sales tax
            double taxedPrice = price;
            if ( taxable == '+' )
                taxedPrice += salesTax / 100.0 * price;

            // Taxed and untaxed sums of prices are required later
            untaxedSum += price;
            taxedSum += taxedPrice;

            // Print each purchase in host and home currency
            System.out.printf("%12.2f %12.2f%n", taxedPrice, taxedPrice * exchangeRate);
        }

         // Print sum with taxes
        System.out.printf("------------ ------------%n");
        System.out.printf("%12.2f %12.2f%n", taxedSum, taxedSum * exchangeRate);
        System.out.printf("------------ ------------%n");

        // Calculate each tip (5%, 10%, 15%, 20%) from untaxed sum
        for ( int tipPercent = 5; tipPercent <= 20; tipPercent += 5 )
        {
            final double tip = tipPercent / 100.0 * untaxedSum;
            System.out.printf("%12.2f %12.2f %2d%%%n", tip, tip * exchangeRate, tipPercent);
        }

        // Add each tip to the taxed sum and print as a total
        System.out.printf("============ ============%n");
        for ( int tipPercent = 5; tipPercent <= 20; tipPercent += 5 )
        {
            final double tip = tipPercent / 100.0 * untaxedSum;
            final double total = taxedSum + tip;
            System.out.printf("%12.2f %12.2f %2d%%%n", total, total * exchangeRate, tipPercent);
        }

        // Close the standard input
        this.input.close();
    }
}