import java.util.Scanner;

/**
 * Automatic judge that calculates the result of an arbitrary
 * sized tic-tac-toe game
 */
public class Solution5_5
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
      Solution5_5 solution = new Solution5_5();
      solution.run();
   }

// <body>

    /**
    * Run the solution. This method is called from main()
    */
   public void run()
   {
      // Create object to read data from standard input
      this.input = new Scanner(System.in);

      // Read a character at time
      this.input.useDelimiter("");

      // Read the size of the grid
      final int size = this.input.nextInt();

      // We continue only if the size is valid
      if ( size > 0 )
      {
         // Create the tic-tac-toe matrix
         final char[][] grid = new char[size][size];

         // Ignore the two new-line chars after the dimension
         this.input.next();
         this.input.next();

         // Read the grid from standard input
         if ( readGrid(grid) )
         {
            // It was read, find and print the result
            System.out.printf("%c\n", findGameResult(grid));
         }
      }

      // Close the standard input
      this.input.close();
   }

   /**
    * Reads the grid from input
    * @param grid the tic tac toe grid
    * @return
    */
   private boolean readGrid(final char[][] grid)
   {
      // Read each row
      for ( int row = 0; row < grid.length; ++row )
      {
         // Read each column in the current row
         for ( int column = 0; column < grid[row].length; ++column )
         {
            grid[row][column] = this.input.next().charAt(0);
         }

         // Ignore the new-line character, if any
         if ( this.input.hasNext() )
         {
            this.input.next();
         }
      }

      // Success
      return true;
   }

   /**
    * Finds out the current state of the game
    * @param grid the tic tac toe grid
    * @return the character representing the state of the game
    */
   private char findGameResult(final char[][] grid)
   {
      // Count the empty cells, X lines and O lines
      final int emptyCount = countEmptyCells(grid);
      final int xLines = countLinesFor(grid, 'X');
      final int oLines = countLinesFor(grid, 'O');

      // The result depends on the previous counts
      char result = '-';
      if ( emptyCount < 0 )
         result = 'E';
      else if ( emptyCount > getMaxEmptyCellsForALine(grid) )
         result = '-';
      else if ( xLines > 0 && xLines > oLines )
         result ='X';
      else if ( oLines > 0 && oLines > xLines )
         result = 'O';
      else if ( emptyCount == 0 )
         result = '=';

      // Done
      return result;
   }

   /**
    * Return the number of empty cells that left when
    * a player can do his/her first line
    *
    * @param  grid The grid to know the size
    * @return The number of empty cells
    */
   private long getMaxEmptyCellsForALine(final char[][] grid)
   {
      // Size of the grid as a math variable (n)
      final long n = grid.length;

      // n^2 = total number of cells
      // 2*n = number of non empty-cells for one or the two
      // players make a line
      // n^2 - 2*n = number of remaning empty cells
      return n*n - 2*n;
   }

   /**
    * Counts how many empty grids there are
    * @param grid the tic-tac-toe grid
    * @return the amount of empty grids. If the number of
    * X or O moves are invalid, returns -1. If the grid has
    * an invalid symbol (e.g: 'a'), returns -2.
    */
   protected int countEmptyCells(final char[][] grid)
   {
      // Count each type of symbol
      int xCount = 0;
      int oCount = 0;
      int emptyCount = 0;

      // Count into all rows
      for ( int row = 0; row < grid.length; ++row )
      {
         // Count each cell of current row
         for ( int col = 0; col < grid[row].length; ++col )
         {
            // Increase the respective counter according
            // to the cell value
            switch( grid[row][col] )
            {
               case 'X': ++xCount; break;
               case 'O': ++oCount; break;
               case '-': ++emptyCount; break;

               // We found an invalid symbol
               default: return -2;
            }
         }
      }

      // Players must have the same number of moves or
      // one of them may have one more move than the other
      if ( xCount > oCount + 1 || oCount > xCount + 1 )
         return -1;

      // Done
      return emptyCount;
   }

   /**
    * Checks how many wins a player has
    * @param grid the tic tac toe grid
    * @param player the current player
    * @return the amount of wins for the player
    */
   private int countLinesFor(final char[][] grid, final char player)
   {
      // Warning: this algorithm is not efficient
      int result = 0;

      // Check rows
      for ( int row = 0; row < grid.length; ++row )
         if ( isRow(grid, row, player) )
            ++result;

      // Check columns
      for ( int column = 0; column < grid.length; ++column )
         if ( isCol(grid, column, player) )
            ++result;

      // Check diagonal 1
      if ( isDiagonal1(grid, player) )
         ++result;

      // Check diagonal 2
      if ( isDiagonal2(grid, player) )
         ++result;

      return result;
   }

   /**
    * Checks if a row has a winner
    * @param grid the tic tac toe grid
    * @param row the row being checked
    * @param player the current player
    * @return true if winner, false if no winner
    */
   private boolean isRow(final char[][] grid, final int row, final char player)
   {
      for ( int column = 0; column < grid.length; ++column )
         if ( grid[row][column] != player )
            return false;

      return true;
   }

   /**
    * Checks if a column has a winner
    * @param grid the tic tac toe grid
    * @param col the column being checked
    * @param player the current player
    * @return true if winner, false if no winner
    */
   private boolean isCol(final char[][] grid, final int col, final char player)
   {
      for ( int row = 0; row < grid.length; ++row )
         if ( grid[row][col] != player )
            return false;

      return true;
   }

   /**
    * Checks if the diagonal has a winner
    * @param grid the tic tac toe grid
    * @param player the current player
    * @return true if winner, false if no winner
    */
   private boolean isDiagonal1(final char[][] grid, final char player)
   {
      for ( int index = 0; index < grid.length; ++index )
         if ( grid[index][index] != player )
            return false;

      return true;
   }

   /**
    * Checks if the diagonal has a winner
    * @param grid the tic tac toe grid
    * @param n the dimensions of the grid
    * @param player the current player
    * @return true if winner, false if no winner
    */
   private boolean isDiagonal2(final char[][] grid, final char player)
   {
      for ( int index = 0; index < grid.length; ++index )
         if ( grid[index][grid.length - index - 1] != player )
            return false;

      return true;
   }
// </body>
}