package Practice5;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Compares student homeworks against a model homework for plagiarism
 */
public class Solution5_3
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
      Solution5_3 solution = new Solution5_3();
      solution.run();
   }

// <body>

   /**
    * Run the solution. This method is called from main()
    */
   public void run()
   {
      // Create object to read data from standard input
      this.input = new Scanner( System.in );

      // Numbers are separed by :, items by |, and students by \n
      this.input.useDelimiter("[\\|\\:\\n]+");

      // Read the count of elements for all the homeworks
      final int elementCount = this.input.nextInt();

      // Read the elements for the model homework in the first line
      final String[] modelElements = new String[ elementCount ];
      for ( int index = 0; index < modelElements.length; ++index )
      {
         modelElements[index] = this.input.next().trim();
      }

      // For convenience, have the model array sorted
      Arrays.sort(modelElements);

      // Read homeworks for the rest of students
      while ( this.input.hasNextInt() )
      {
         // Read a compare one more homework
         compareHomework(modelElements);
      }

      // Close the standard input
      this.input.close();
   }

   /**
    * Read a homework, compare it against the given model and print
    * the result to standard output
    * @param modelElements The elements of the model homework
    */
   private void compareHomework(final String[] modelElements)
   {
      // Read the student number
      final long studentNumber = this.input.nextLong();

      // Count the number of matching elements against the model
      int matchCount = 0;

      // Read each student's element
      for ( int index = 0; index < modelElements.length; ++index )
      {
         // Read the element
         final String element = this.input.next().trim();

         // Check if the element is in the model
         if ( Arrays.binarySearch(modelElements, element) >= 0 )
         {
            // We found a match, increase the counter
            ++matchCount;
         }
      }

      // Print results
      printStatistics(studentNumber, matchCount, modelElements.length);
   }

   /**
    * Print statistics about a student homework
    * @param studentNumber Number of student given in standard input
    * @param matchCount    Number of matches found with model homework
    * @param elementCount  Number of elements in mode homework
    */
   private void printStatistics(final long studentNumber, final int matchCount, final int elementCount)
   {
      System.out.printf( "%d: ",studentNumber );

      if ( matchCount == 0 )
      {
         System.out.println( "original" );
      }
      else if ( matchCount == elementCount )
      {
         System.out.println( "plagiarism" );
      }
      else
      {
         System.out.printf("%.0f%%%n", 100.0 * matchCount / elementCount);
      }
   }
// </body>
}