import java.util.Scanner;

/**
 * Automatic judge that calculates the result of a tic-tac-toe game
 */
public class Solution5_4
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
      Solution5_4 solution = new Solution5_4();
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

      // A matrix to store the game state
      final char[][] grid = new char[3][3];

      // Read the grid from standard input
      if ( this.readGrid(grid) )
      {
         // The grid was read, judge it
         System.out.printf("%c\n", this.findGameResult(grid));
      }

      // Close the standard input
      this.input.close();
   }

   /**
    * Read a grid from standard input
    * @return true If it was successfully read, false otherwise
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
    * Finds out what the current game status is
    * @param grid the tic tac toe grid
    * @return the appropriate character for representing the game status
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
      else if ( emptyCount > 4 )
         result = '-';
      else if ( xLines > 0 && xLines > oLines )
         result ='X';
      else if ( oLines > 0 && oLines > xLines )
         result = 'O';
      else if ( oLines > 0 && oLines == xLines )
         result = 'E';
      else if ( emptyCount == 0 )
         result = '=';

      // Done
      return result;
   }

   /**
    * Counts how many empty grids there are
    * @param grid the tic-tac-toe grid
    * @return the amount of empty grids. If the number of
    * X or O moves are invalid, returns -1. If the grid has
    * an invalid symbol (e.g: 'a'), returns -2.
    */
   private int countEmptyCells(final char[][] grid)
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
    * Counts how many lines are for a given player
    * @param grid the tic tac toe grid
    * @param player the current plater value, X or O
    * @return the lines counted
    */
   private int countLinesFor(final char[][] grid, final char player)
   {
      int result = 0;

      // Check rows
      if ( isLine(grid, 0,0, 0,1, 0,2, player) ) ++result;
      if ( isLine(grid, 1,0, 1,1, 1,2, player) ) ++result;
      if ( isLine(grid, 2,0, 2,1, 2,2, player) ) ++result;

      // Check columns
      if ( isLine(grid, 0,0, 1,0, 2,0, player) ) ++result;
      if ( isLine(grid, 0,1, 1,1, 2,1, player) ) ++result;
      if ( isLine(grid, 0,2, 1,2, 2,2, player) ) ++result;

      // Check diagonals
      if ( isLine(grid, 0,0, 1,1, 2,2, player) ) ++result;
      if ( isLine(grid, 0,2, 1,1, 2,0, player) ) ++result;

      return result;
   }

   /**
    * Says if a given line has 3 in a row
    * @param grid the tic tac toe grid
    * @param r1 row 1
    * @param c1 column 1
    * @param r2 row 2
    * @param c2 column 2
    * @param r3 row 3
    * @param c3 column 3
    * @param player the current player value, X or O
    * @return
    */
   private boolean isLine(final char[][] grid, final int r1,final int c1, final int r2,final int c2, final int r3,final int c3, final char player)
   {
      return grid[r1][c1] == player
         && grid[r2][c2] == player
         && grid[r3][c3] == player;
   }

// </body>
}