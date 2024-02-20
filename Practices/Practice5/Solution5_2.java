package Practice5;

import java.util.Scanner;

/**
 * Solution for histogram problem
 */
public class Solution5_2
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
      Solution5_2 solution = new Solution5_2();
      solution.run();
   }

   /**
    * Run the solution. This method is called from main()
    */
   public void run()
   {
      // Create object to read data from standard input
      this.input = new Scanner(System.in);

      // Read the parameters in the first line
      final int size = this.input.nextInt();
      final int classes = this.input.nextInt();
      final int integerDigits = this.input.nextInt();
      final int decimalDigits = this.input.nextInt();

      // Check the size of data is valid
      if ( size > 0 && classes > 0 )
      {
         // Create an array to store the values
         final double[] values = new double[size];

         // Read the values and store them into the array
         readValues(values);

         // Print a histogram for the data
         printHistogram(values, classes, integerDigits, decimalDigits);
      }

      // Close the standard input
      this.input.close();
   }

   /**
    * Read values from standard input and store them into the array
    * @param values The array to store the data
    */
   private void readValues(final double[] values)
   {
      for (int index = 0; index < values.length; ++index)
      {
         values[index] = input.nextDouble();
      }
   }

   //<body>

   /**
    * This is the principal method for creating and printing the histogram
    *
    * @param values Array of values read from input
    * @param classes How many classes the histogram will have
    * @param integerDigits Amount of integer numberWidth to be printed
    * @param decimalDigits Amount of decimal numberWidth to be printed
    */
   private void printHistogram(final double[] values, final int classes, final int integerDigits, final int decimalDigits)
   {
      // Find the min and max values in the array
      final double[] maxAndMin = findMaxMin(values);
      final double min = maxAndMin[0];
      final double max = maxAndMin[1];

      // The width of each class
      final double width = (max - min) / classes;

      // Counters for each class, initialized in zero
      // An additional class for count the values that equal to the max
      final long[] classCounts = new long[ classes + 1 ];

      // Classify each value and increase its counter
      for ( int index = 0; index < values.length; ++index )
      {
         final int classNumber = (int)((values[index] - min) / width);
         ++classCounts[classNumber];
      }

      // The data is ready to be printed as a histogram
      printHistogram(classCounts, min, max, width, integerDigits, decimalDigits);
   }

   /**
    * Finds the max and min values in the values array
    * @param values array of values
    * @return An array with the min on the first pos and the max on the second one
    */
   private double[] findMaxMin(final double[] values)
   {
      // Two variables to store copies of the min and max
      double max = values[0];
      double min = values[0];

      // Find the min and max in the array
      for ( int index = 1; index < values.length; ++index )
      {
         if ( values[index] > max )
         {
            // We found a higher value than our current max, update it
            max = values[index];
         }
         else if ( values[index] < min )
         {
            // We found a lower value than our current min, update it
            min = values[index];
         }
      }

      // Return both values into an array
      return new double[]{ min, max };
   }

   /**
    * This method only prints a created histogram
    *
    * @param classCounts An array containing the number of values found in each class
    * @param min Minimum value in values array
    * @param max Maximum value in values array
    * @param width Width of each class
    * @param integerDigits Amount of integer numberWidth to be printed
    * @param decimalDigits Amount of decimal numberWidth to be printed
    */
   private void printHistogram(long[] classCounts, double min, double max
         , double width, int integerDigits, int decimalDigits)
   {
      // The width of a number to be printed is the sum of:
      // the numberWidth of the integer part, the numberWidth of decimal part, and the dot
      final int fieldWidth = integerDigits + decimalDigits + (decimalDigits > 0 ? 1 : 0);

      // The first left limit is the mininum value
      double left = min;

      // Traverse each class printing it
      final int normalClasses = classCounts.length - 2;
      for ( int index = 0; index < normalClasses; ++index )
      {
         // Print the range "[left, right["
         printRange(left, left + width, '[', fieldWidth, decimalDigits);

         // Print the bar made of asterisks for this clas
         printAsterisks(classCounts[index]);

         // Move to the next class
         left += width;
      }

      // Print the final class, which includes the last right limit
      printRange(left, max, ']', fieldWidth, decimalDigits);
      printAsterisks( classCounts[normalClasses] + classCounts[normalClasses + 1] );
   }

   /**
    * Prints the given range in the form [left, right[
    *
    * @param left Left value of the range
    * @param right Right value of the range
    * @param bracket Bracket character, must be '[' or ']'
    * @param numberWidth The width in chracters of the field to print each number
    * @param decimalDigits Amount of decimal digits to be printed
    */
   private void printRange(final double left, final double right, final char bracket, final int numberWidth, final int decimalDigits)
   {
      final String format = "%" + numberWidth + "." + decimalDigits + "f";
      System.out.printf("[" + format + ", " + format + "%c | ", left, right, bracket);
   }

   /**
    * prints the specified amount of *
    * @param length how many * are desired
    */
   private void printAsterisks(final long length)
   {
      // Print the asterisks
      for ( int index = 0; index < length; ++index )
      {
         System.out.print('*');
      }

      // Print a new line separator
      System.out.println();
   }

// </body>
}
