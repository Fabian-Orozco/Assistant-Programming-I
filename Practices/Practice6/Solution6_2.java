package Practice6;

import java.io.PrintStream;
import java.util.Scanner;

/**
 * Marks the solution path within a maze with breadcrumbs
 */
public class Solution6_2
{
   /**
    * Gets data from standard input
    */
   private Scanner input = null;
   
   /**
    * The maze to be solved is a matrix of chars
    * where '|' stands for a wall, ' ' a passage,
    * 'A' the entrance (start), and 'B' the exit (end)
    */
   private Maze maze = null;

   /**
    * Start the execution of the solution
    * @param args Command line arguments
    */
   public static void main(String[] args)
   {
      Solution6_2 solution = new Solution6_2();
      solution.run();
   }

   /**
    * Run the solution. This method is called from main()
    */
   public void run()
   {
      // Create object to read data from standard input
      this.input = new Scanner(System.in);
      
      // Create the model object in charge of manage the maze
      this.maze = new Maze();
      
      // Read the maze
      if ( this.maze.read(this.input) )
      {
         // If there is a path from A to B
         if ( this.maze.findPath() )
         {
            // Print the maze highlighting the path with dots
            this.maze.print(System.out);
         }
         else
         {
            // Print a warning for the king
            System.out.println("No solution path");
         }
      }

      // Close the standard input
      this.input.close();
   }
}

/**
 * Model class that manages a maze
 * The maze is represented as a matrix of characters
 * where '|' stands for a wall, ' ' a passage,
 * 'A' the entrance (start), and 'B' the exit (end)
 */
class Maze
{
   /**
    * The maze to be solved is a matrix of chars
    */
   private char[][] maze = null;
   
   /**
    * The row where the entrance to the maze is
    */
   private int entranceRow = -1;
   
   /**
    * The column where the entrance to the maze is
    */
   private int entranceCol = -1;
   
   /**
    * Read the maze from the given input file
    * @param input The file where the maze will be read
    * @return true on success
    */
   public boolean read(final Scanner input)
   {
      // Read the dimensions of the maze
      final int rows = input.nextInt();
      final int columns = input.nextInt();

      // Create the maze matrix
      this.maze = new char[rows][columns];
      
      // Ignore the new line chars
      input.nextLine();
      input.nextLine();

      // Read a character at time
      input.useDelimiter("");

      // Read all rows
      for ( int row = 0; row < this.maze.length; ++row )
      {
         // Read each column in this row
         for ( int column = 0; column < this.maze[row].length; ++column )
         {
            // Read a token containing just one char
            this.maze[row][column] = input.next().charAt(0);
            
            // If this cell has the entrance
            if ( this.maze[row][column] == 'A' )
            {
               // Store the row and column for convenience
               this.entranceRow = row;
               this.entranceCol = column;
            }
         }

         // Ignore the new line in the input
         input.nextLine();
      }
      
      // Future: Some checks may be done, for example, there is only
      // an entrance and exit point
      return true;
   }
   
   /**
    * Finds the path between the entrance (A) and the exit (B)
    * The path is marked with breadcrumbs (dots) in the maze only
    * if the there is a solution path
    * 
    * @return true if there is a path, false otherwise
    */
   public boolean findPath()
   {
      // Start to find with the entrance (A)
      return advancePath(this.entranceRow, this.entranceCol);
   }
   
   /**
    * Takes the given cell at (row,column) as part of the solution
    * path, and marks it with a breadcrumb. Then tries to reach the
    * exit (B) from this cell. If the path is found, returns true.
    * Otherwise, there is not possible path, the breadcrumb is removed
    * from the cell and it returns false  
    * 
    * @param row The row of the cell
    * @param column The column of the cell
    * @return true if a path to the exit pass by this cell, false otherwise
    */
   private boolean advancePath(final int row, final int column)
   {
      // This cells might be part of the path
      // Mark it tentatively as visited, except for the entrance
      if ( this.maze[row][column] != 'A' )
      {
         this.maze[row][column] = '.';
      }
      
      // If there IS path from this cell to the exit (B) for any
      // of the cardinal directions, we keep the breadcrumb in
      // this cell because it is part of the solution path
      if ( this.findPath(row - 1, column) ) return true; // North
      if ( this.findPath(row, column + 1) ) return true; // East
      if ( this.findPath(row + 1, column) ) return true; // South
      if ( this.findPath(row, column - 1) ) return true; // West
      
      // There is NO path from this cell to the exit (B)
      // Remove the breacrumb and warn the caller
      this.maze[row][column] = ' ';
      return false;
   }
   
   /**
    * Tries to find a path towards the exit (B) by the given cell.
    * This method check if this cell has a value that allows the path
    * to continue through it. Only empty cells allow this and this
    * method will recursively call others to check if the path can be
    * established. If the exit is found in this cell, this method
    * returns true. Otherwise it returns false, because the cell has
    * a wall, or a visited cell (marked with a breadcrumb).
    * 
    * @param row The row of the cell
    * @param column The column of the cell
    * @return true if a path to the exit pass by this cell, false otherwise
    */
   private boolean findPath(final int row, final int column)
   {
      // The traversal depends on the value of this cell
      switch ( this.getCellValue(row, column) )
      {
         case 'B': return true;  // Exit (B) found! 
         case   0: return false; // No trespass walls
         case '|': return false; // No trespass walls
         case '.': return false; // No return to visited cells
         case ' ': return advancePath(row, column); // Go ahead
         default : return false; // Error!!
      }
   }
   
   /**
    * Get the number of rows in this maze
    * @return The number of rows, zero if invalid
    */
   private int getRowCount()
   {
      return this.maze != null ? this.maze.length : 0;
   }

   /**
    * Get the number of columns in this maze
    * @return The number of columns, zero if invalid
    */
   private int getColumnCount()
   {
      // Avoid to check lengths for null matrix references
      // We assume all rows have the same amount of columns,
      // therefore, we use the first row length
      return this.maze != null && this.maze.length > 0
          ? this.maze[0].length : 0;
   }

   /**
    * Return the value for a cell in the maze.
    * If the asked row or column is invalid, zero is returned
    *
    * @param row    Row where the cell is
    * @param column Column where the cell is
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
            result = this.maze[row][column];
         }
      }

      // Done
      return result;
   }

   /**
    * Print the maze to the given output file
    * @param out The file where the maze will be printed
    */
   public void print(final PrintStream out)
   {
      // Print all rows
      for ( int row = 0; row < this.maze.length; ++row )
      {
         // Print each column in this row
         for ( int column = 0; column < this.maze[row].length; ++column )
         {
            // Print the cell
            out.print( this.maze[row][column] );
         }

         // Separate this line from the next one
         out.println();
      }
   }
}