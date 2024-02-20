import java.util.Scanner;

/**
 * Read cashier balancing records of store branches from standard
 * input and print averages for low and high tourist seasons
 */
public class Solution7_1
{
   /**
    * Gets data from standard input
    */
   private Scanner input = null;

   /**
    * The branch store located in Carrillo
    */
   private StoreBranch carrillo = null;

   /**
    * The branch store located in Liberia
    */
   private StoreBranch liberia = null;

   /**
    * The branch store located in Sardinal
    */
   private StoreBranch sardinal = null;

   /**
    * Start the execution of the solution
    * @param args Command line arguments
    */
   public static void main(String[] args)
   {
      Solution7_1 solution = new Solution7_1();
      solution.run();
   }

   /**
    * Run the solution. This method is called from main()
    */
   public void run()
   {
      // Create object to read data from standard input
      this.input = new Scanner(System.in);

      // We use as delimiter: spaces for fields and slashes in dates
      this.input.useDelimiter("[/\\s]+");

      // Create the three store branches
      this.carrillo = new StoreBranch();
      this.liberia = new StoreBranch();
      this.sardinal = new StoreBranch();

      // Each line in standard input has a cashier balancing
      while ( this.input.hasNextInt() )
      {
         readCashierBalancing();
      }

      // After we read all the cashier balancing lines, print statistics
      printStatistics();

      // Close the standard input
      this.input.close();
   }

   /**
    * Read a line from standard input containing a cashier balancing.
    * Add the balance to the respective store branch
    */
   private void readCashierBalancing()
   {
      // Read date
      /*int day =*/ this.input.nextInt();
      final int month = this.input.nextInt();
      /*int year =*/ this.input.nextInt();

      // Read name of the store branch
      final String branchName = this.input.next();

      // Read the cashier balancing
      final double cashierBalancing = this.input.nextDouble();

      // Add the cashier balancing to the respective branch
      switch ( branchName )
      {
         case "Carrillo": this.carrillo.add(month, cashierBalancing); break;
         case "Liberia": this.liberia.add(month, cashierBalancing); break;
         case "Sardinal": this.sardinal.add(month, cashierBalancing); break;
      }
   }

   /**
    * Print a table summarizing the balances for each store branch
    * in both, low and high tourist seasons
    */
   private void printStatistics()
   {
      // Print the table header
      System.out.println("TIENDA                     BAJA            ALTA");
      System.out.println("=============== =============== ===============");

      // Print the statistics for each branch
      this.carrillo.printStatistics("Carrillo");
      this.liberia.printStatistics("Liberia");
      this.sardinal.printStatistics("Sardinal");
   }
}

/**
 * Represents a branch of the store
 */
public class StoreBranch 
{
   /**
    * Total money made by this branch in tourist low season
    */
   private double lowSeasonMoney = 0.0;
   
   /**
    * Number of cashier balances made by this branch in low season
    */
   private long lowSeasonCount = 0;

   /**
    * Total money made by this branch in tourist high season
    */
   private double highSeasonMoney = 0.0;
   
   /**
    * Number of cashier balances made by this branch in high season
    */
   private long highSeasonCount = 0;

   /**
    * Add a cashier balance to this branch store
    * @param month The month when the balance was done
    * @param cashierBalance The total amount of money of the balance
    */
   public void add(final int month, final double cashierBalance)
   {
      // We add the cashier balance to the respective tourist season
      switch ( month )
      {
         // High season: Jan, Feb, Mar, Apr, Jul, Dec
         case 1: case 2: case 3: case 4: case 7: case 12:
         {
            // We accumulate the balance to calculate the average later
            this.highSeasonMoney += cashierBalance;
            ++this.highSeasonCount;
            break;
         }
         
         // Low season: the remaining months
         default:
         {
            // We accumulate the balance to calculate the average later
            this.lowSeasonMoney += cashierBalance;
            ++this.lowSeasonCount;
            break;
         }
      }
   }
   
   /**
    * Print the average balancing for this branch as a table row
    * 
    * @param branchName The name for this store branch
    */
   public void printStatistics(final String branchName)
   {
      // Print the branch name, low season average, and high season average
      System.out.printf("%-15s ", branchName);
      System.out.printf("%,15.2f ", calculateLowAverage() );
      System.out.printf("%,15.2f%n", calculateHighAverage() );
   }
   
   /**
    * Calculate the low season average balance
    * @return The average or 0.0 if no enough data is collected
    */
   private double calculateLowAverage()
   {
      return this.lowSeasonCount > 0 ? this.lowSeasonMoney / this.lowSeasonCount : 0.0; 
   }
   
   /**
    * Calculate the high season average balance
    * @return The average or 0.0 if no enough data is collected
    */
   private double calculateHighAverage()
   {
      return this.highSeasonCount > 0 ? this.highSeasonMoney / this.highSeasonCount : 0.0; 
   }
}