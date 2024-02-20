import java.util.Scanner;

/**
 * Simulates an enchated forest
 */
public class Solution7_4
{
   /**
    * Gets data from standard input
    */
   private Scanner input = null;

   /**
    * Stores the enchanted forest
    */
   private Forest forest = null;

   /**
    * The number of midnights to be simulated
    * If this number is negative, only the first and last day
    * must be printed to standard output
    */
   private int midnights = 0;

   /**
    * Start the execution of the solution
    * @param args Command line arguments
    */
   public static void main(String[] args)
   {
      Solution7_4 solution = new Solution7_4();
      solution.run();
   }

   /**
    * Run the solution. This method is called from main()
    */
   public void run()
   {
      // Create object to read data from standard input
      this.input = new Scanner(System.in);

      // We create the forest according to the dimensions
      createForest();

      // Read the initial day from standard input
      readForest();

      // Simulate all the asked midnights, and print them
      simulateMidnights();

      // Close the standard input
      this.input.close();
   }

   /**
    * Read dimensions from standard input and creates a forest
    */
   private void createForest()
   {
      // Read the dimensions of the forest
      final int rows = this.input.nextInt();
      final int columns = this.input.nextInt();

      // Read the number of simulations to be done
      this.midnights = this.input.nextInt();

      // Create the forest
      this.forest = new Forest(rows, columns);
   }

   /**
    * Reads the forest's initial day state from standard input
    */
   private void readForest()
   {
      // Ignore the new line chars
      this.input.nextLine();
      this.input.nextLine();

      // Read a character at time
      this.input.useDelimiter("");

      // Read the forest from standad input
      this.forest.read(this.input);
   }

   /**
    * Simulates all the midnights and print each day, if asked.
    * If a negative number of midnights was given, only the first
    * and last day are printed
    */
   private void simulateMidnights()
   {
      // Print the intial day. The toString() method is used
      // to get the state of the forest as a text to be printed
      System.out.printf( "0:%n%s", this.forest );

      // The positive number of simulations
      final int steps = this.midnights >= 0 ? this.midnights : -this.midnights;

      // Simulate each midnight
      for ( int current = 1; current <= steps; ++current )
      {
         // Its midnight, transform the entire forest
         this.forest.updateAll();

         // Print the new day, if asked
         if ( this.midnights > 0 || current == steps )
         {
            // Print the current day using the toString() method
            System.out.printf( "%n%d:%n%s", current, this.forest );
         }
      }
   }
}

/**
 * Stores an enchated forest
 */
class Forest
{
   /**
    * State of the forest at the current day
    */
   private char[][] currentDay = null;

   /**
    * State of the forest at the next day
    */
   private char[][] nextDay = null;

   /**
    * Creates an forest with the given dimensions.
    * The created forest is invalid, containing only zeros.
    * @param  rows    Number of rows
    * @param  columns Number of columns
    */
   public Forest(final int rows, final int columns)
   {
      this.currentDay = new char[rows][columns];
   }

   /**
    * Get the number of rows in this forest
    * @return The number of rows, zero if invalid
    */
   private int getRowCount()
   {
      return this.currentDay != null ? this.currentDay.length : 0;
   }

   /**
    * Get the number of columns in this forest
    * @return The number of columns, zero if invalid
    */
   private int getColumnCount()
   {
      // Avoid to check lengths for null matrix references
      // We assume all rows have the same amount of columns,
      // therefore, we use the first row length
      return this.currentDay != null && this.currentDay.length > 0
          ? this.currentDay[0].length : 0;
   }

   /**
    * Return the value for a cell in the current day.
    * If the asked row or column is invalid, zero is returned
    *
    * @param row    Row where the cell to be updated is
    * @param column Column where the cell to be updated is
    */
   private char getCellValue(final int row, final int column)
   {
      // The result value
      char result = 0;

      // Check the row is valid
      if ( row >= 0 && row < this.getRowCount() )
      {
         // Check the column is valid
         if ( column >= 0 && column < this.getColumnCount() )
         {
            // Both, the row and the column are valid, get the value
            result = this.currentDay[row][column];
         }
      }

      // Done
      return result;
   }

   /**
    * Read this forest from the given input
    * @param input Source file to read this forest from
    */
   public void read(final Scanner input)
   {
      // Read all rows
      for ( int row = 0; row < this.currentDay.length; ++row )
      {
         // Read each column in this row
         for ( int column = 0; column < this.currentDay[row].length; ++column )
         {
            // Read a token containing just one char
            this.currentDay[row][column] = input.next().charAt(0);
         }

         // Ignore the new line in the input
         input.nextLine();
      }
   }

   /**
    * Gets a String representation of the current state of the forest
    * @return A text of a formated forest
    */
   public String toString()
   {
      // The resulting text
      String result = "";

      // Add all rows
      for ( int row = 0; row < this.currentDay.length; ++row )
      {
         // Add each column in this row
         for ( int column = 0; column < this.currentDay[row].length; ++column )
         {
            // Read a token containing just one char
            result += this.currentDay[row][column];
         }

         // Separate this line from the next one
         result += '\n';
      }

      return result;
   }

   /**
    * Updates the entire forest when it is midnight
    */
   void updateAll()
   {
      // Create a matrix for the new day
      this.nextDay = new char[ this.getRowCount() ][ this.getColumnCount() ];

      // Update all rows from the previous day to the next one
      for ( int row = 0; row < this.currentDay.length; ++row )
      {
         // Update each cell
         for ( int column = 0; column < this.currentDay[row].length; ++column )
         {
            // Apply the magic rules
            this.updateCell(row, column);
         }
      }

      // Midnight has passed. The new day becomes the current day
      this.currentDay = this.nextDay;

      // The matrix for the new day is not longer required
      this.nextDay = null;
   }

   /**
    * Updates a cell from the previous day to the next day during midnight
    * The this.nextDay[row][column] is updated, the this.currentDay is untouched
    *
    * @param row    Row where the cell to be updated is
    * @param column Column where the cell to be updated is
    */
   private void updateCell(final int row, final int column)
   {
      // Count neighbours of each type
      final char cell = this.getCellValue(row, column);
      final int trees = this.countNeighbours(row, column, 'a');
      final int lakes = this.countNeighbours(row, column, 'l');
      final int grass = this.countNeighbours(row, column, '-');

      // Apply the rules to the cell, and count them:
      int count = 0;
      // 1. Enchanting: a tree is replaced by lake if 4+ lakes are neighbours
      count += applyRule( cell == 'a' && lakes >= 4, row, column, 'l' );
      // 2. Drying: A lake sourrounded by 2- lakes, becomes grass
      count += applyRule( cell == 'l' && lakes <= 2, row, column, '-' );
      // 3. Reforesting: A grass surrounded by 3+ trees becomes tree
      count += applyRule( cell == '-' && trees >= 3, row, column, 'a' );
      // 4. Overcrowding: A tree sourrounde by 5+ trees becomes grass
      count += applyRule( cell == 'a' && trees >= 5, row, column, '-' );
      // 5. Stability: otherwise, the cell stays with no change
      applyRule( count == 0, row, column, this.currentDay[row][column] );

      // If two or more rules were applied, generate a warning
      if ( count > 1 )
      {
         System.err.printf("%c[%d][%d] changed %d times%n"
            , currentDay[row][column], row, column, count);
      }
   }

   /**
    * If the condition is true, the cell at nextDay[row][column] becomes newState
    * @param  condition The result of a rule condition. If thre the rule applies
    * @param  row       The row of the cell to be changed
    * @param  column    The column of the cell to be changed
    * @param  newState  The new value for the cell
    * @return           1 if the cell was changed, 0 if the condition was false
    */
   private int applyRule(final boolean condition, final int row, final int column, final char newState)
   {
      // Number of changes
      int count = 0;

      // If the rule applies to this cell
      if ( condition )
      {
         // Change the cell to the new state and increase the change counter
         this.nextDay[row][column] = newState;
         ++count;
      }

      // Return the number of changes made
      return count;
   }

   /**
    * Count the number of neighbours of the cell at currentDay[row][column]
    * that are of the given type
    *
    * @param  row    Row of the reference cell
    * @param  column Column of the reference cell
    * @param  type   Type of neighbours to be counted around the reference cell
    * @return        The count of neighbours
    */
   private int countNeighbours(final int row, final int column, final char type)
   {
      // The resulting count
      int count = 0;

      // Check each neighbour in:
      // Previous row
      if ( this.getCellValue(row - 1, column - 1) == type ) ++count;
      if ( this.getCellValue(row - 1, column + 0) == type ) ++count;
      if ( this.getCellValue(row - 1, column + 1) == type ) ++count;

      // Current row
      if ( this.getCellValue(row + 0, column - 1) == type ) ++count;
      if ( this.getCellValue(row + 0, column + 1) == type ) ++count;

      // Next row
      if ( this.getCellValue(row + 1, column - 1) == type ) ++count;
      if ( this.getCellValue(row + 1, column + 0) == type ) ++count;
      if ( this.getCellValue(row + 1, column + 1) == type ) ++count;

      // Done
      return count;
   }
}