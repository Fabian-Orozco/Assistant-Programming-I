package Practice5;

import java.util.Scanner;

/**
 * Validates a treasure map and prints the path from a starting
 * point to the position where the trasure is
 */
public class Solution5_6
{
    /**
     * Gets data from standard input
     */
    private Scanner input = null;

    /**
     * Row where the path to the treasure starts
     */
    private int startingRow = 0;

    /**
     * Column where the path to the treasure starts
     */
    private int startingColumn = 0;

    /**
     * Treasure map provided in the test case
     * It is modified with values 0 indicanting the path
     * and 1 indicating the treasure
     */
    private short[][] treasureMap = null;

    /**
     * If the map has less than 10 rows this variable will
     * store 10 in order to get the row numbers from cells
     * If larger than 10 it will store 100.
     * E.g: if cell has value 74, 74/10==7 is the next row
     * E.g: if cell has value 2941, 2941/100==29 is the next row
     */
    private int rowsBase = 10;

    /**
     * If the map has less than 10 columns this variable will
     * store 10 in order to get the row numbers from cells
     * If larger than 10 it will store 100.
     * E.g: if cell has value 74, 74%10==4 is the next column
     * E.g: if cell has value 2941, 2941%100==41 is the next row
     */
    private int columnsBase = 10;

    /**
     * Start the execution of the solution
     * @param args Command line arguments
     */
    public static void main(String args[])
    {
        Solution5_6 solution = new Solution5_6();
        solution.run();
    }

    /**
     * Run the solution. This method is called from main()
     */
    public void run()
    {
        // Create object to read data from standard input
        this.input = new Scanner(System.in);

        // If there is a valid map
        if ( this.readTreasureMap() )
        {
            // Follow the path and mark it in the map
            String verdict = this.followPath();

            // Print the verdict
            System.out.printf("%s%n%n", verdict);

            // Print the plain solution map
            this.printSolution();
        }
        else
        {
            System.out.println("Invalid data");
        }

        // Close the standard input
        this.input.close();
    }

    // Read the coded treasure map
    public boolean readTreasureMap()
    {
        // Read the size of the map
        int rows = this.input.nextInt();
        int columns = this.input.nextInt();

        // Check the size is valid
        if ( inRange(rows) && inRange(columns) )
        {
            // Adjust the separator of digits according to the map's size
            this.rowsBase = rows < 10 ? 10 : 100;
            this.columnsBase = columns < 10 ? 10 : 100;

            // Read the starting cell of the path
            this.startingRow = this.input.nextInt();
            this.startingColumn = this.input.nextInt();

            // Check the starting position is valid
            if ( inRange(this.startingRow) && inRange(this.startingColumn) )
            {
                // Create a matrix and read the map
                this.treasureMap = new short[rows][columns];
                return readMatrix(this.treasureMap);
            }
            else
            {
                return false;
            }
        }
        else
        {
            return false;
        }
    }

    /**
     * Return true if the index is in range {1,2,...,99}
     * @param  index to be tested
     * @return true if the index is in range {1,2,...,99}
     */
    public boolean inRange(int index)
    {
        return index > 0 && index < 100;
    }

    /**
     * Return the number of rows in the treasure map
     * @return the number of rows in the treasure map
     */
    public int getRows()
    {
        return this.treasureMap.length;
    }

    /**
     * Return the number of columns in the treasure map
     * @return the number of columns in the treasure map
     */
    public int getColumns()
    {
        return this.treasureMap[0].length;
    }

    /**
     * Return true if the given row number exists in the treasure map
     * @param  row The index of the row to be tested
     * @return true if the given row number exists in the treasure map
     */
    public boolean isValidRow(int row)
    {
        return row >= 0 && row < this.getRows();
    }

    /**
     * Return true if the given column number exists in the treasure map
     * @param  row The index of the column to be tested
     * @return true if the given column number exists in the treasure map
     */
    public boolean isValidColumn(int column)
    {
        return column >= 0 && column < this.getColumns();
    }

    /**
     * Read the given matrix from standard input
     * @param  matrix The matrix to be filled
     * @return true on success, false otherwise
     */
    public boolean readMatrix(short[][] matrix)
    {
        // Read rows
        for ( int row = 0; row < matrix.length; ++row )
        {
            // Read columns
            for ( int column = 0; column < matrix[row].length; ++column )
            {
                // Read cell
                matrix[row][column] = this.input.nextShort();
            }
        }

        // We assume success
        return true;
    }

    /**
     * Follow the path in the treasure map from the starting position
     * @return One of the following verdict texts: error, sea, loop, treasure
     */
    public String followPath()
    {
        // Start in the cell given as starting point
        int row = this.startingRow;
        int column = this.startingColumn;

        // While the cell is valid, follow the path
        while ( isValidRow(row - 1) && isValidColumn(column - 1) )
        {
            // in the sea, return the "sea" verdict
            short cellValue = this.treasureMap[row - 1][column - 1];
            if ( cellValue < 0 )
            {
                return "sea";
            }

            // a visited cell, return the "loop" verdict
            if ( cellValue == 0 )
            {
                return "loop";
            }

            // Get the row of the next cell using the first digits
            // and the column from the last digits
            int nextRow = cellValue / this.rowsBase;
            int nextColumn = cellValue % this.columnsBase;

            // the treasure, return the "treasure" verdict
            if ( row == nextRow && column == nextColumn )
            {
                // Mark the current cell as treasure
                this.treasureMap[row - 1][column - 1] = 1;
                return "treasure";
            }

            // Mark the current cell as part of the path
            this.treasureMap[row - 1][column - 1] = 0;

            // other cell, continue to the next one
            row = nextRow;
            column = nextColumn;
        }

        // If the while finished, it is an invalid cell
        // An inexistent cell, return the "error" verdict
        return "error";
    }

    /**
     * Print the treasure map highlighting the path
     */
    public void printSolution()
    {
        // Print rows
        for ( int row = 0; row < this.treasureMap.length; ++row )
        {
            // Print columns
            for ( int column = 0; column < this.treasureMap[row].length; ++column )
            {
                // Print a symbol for each cell
                short cellValue = this.treasureMap[row][column];

                // Print a character according to the value of the cell
                if ( cellValue < 0 )
                {
                    // Sea
                    System.out.print('~');
                }
                else if ( cellValue == 0 )
                {
                    // Path
                    System.out.print('.');
                }
                else if ( cellValue == 1 )
                {
                    // Treasure
                    System.out.print('X');
                }
                else if ( isItself(row, column) )
                {
                    // Treasure
                    System.out.print('!');
                }
                else
                {
                    // Island grass
                    System.out.print(' ');
                }

                // Print cell separator
                if ( column < this.treasureMap[row].length - 1 )
                {
                    System.out.print(' ');
                }
            }

            // Separate with next line
            System.out.println();
        }
    }

    /**
     * Return true if a cell in the treasure map refers to itself
     * @param  row    Row of the cell
     * @param  column Column of the cell
     * @return true if the cell refers to itself, false otherwise
     */
    public boolean isItself(int row, int column)
    {
        // Get the cell value
        short cellValue = this.treasureMap[row][column];

        // Separate tenths and units
        int nextRow = cellValue / this.rowsBase;
        int nextColumn = cellValue % this.columnsBase;

        // If the cell refers to itself return true
        return row + 1 == nextRow && column + 1 == nextColumn;
    }
}