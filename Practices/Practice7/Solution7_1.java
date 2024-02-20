import java.util.Scanner;

/**
 * Solution for Table of contents version 1
 */
public class Solution7_1
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

      // Create the object to read and format the table of contents
      final TableOfContents tableOfContents = new TableOfContents();
      tableOfContents.readAndFormat(this.input);

      // Close the standard input
      this.input.close();
   }
}

/**
 * Read a serie of headings and page numbers from standard input, and
 * print them formatted as a table of contents for any book
 */
/*public*/ class TableOfContents
{
   // Store the page width, it is read from standard input
   private int pageWidth = 0;
   // Store the width for the page numbers, it is read from standard input
   private int pageNumberWidth = 0;
   // Store fill character
   private char fillCharacter = ' ';
   // Store the current title being processed
   private String title = null;
   // Store the current page number being processed
   private String pageNumber = null;

   /**
    * Read parameters from standard input and the lines containing titles
    * @param input Standard input object
    */
   public void readAndFormat(final Scanner input)
   {
      // Read configuration from standard input
      this.pageWidth = input.nextInt();
      this.pageNumberWidth = input.nextInt();
      this.fillCharacter = input.next().charAt(0);

      // Each remaining line has format title\tpageNumber\n
      input.useDelimiter("[\\t\\n]+");
      while ( input.hasNext() )
      {
         // Read the title and the page number
         this.title = input.next();
         this.pageNumber = input.next();

         // Format the line for the table of contents
         this.formatSectionTitle();
      }
   }

   /**
    * Format the current line for the table of contents
    */
   private void formatSectionTitle()
   {
      // Calculate the amount of fill chars that are needed. It is the page width without
      // title length, number width and an extra blank space.
      final int fillWidth =  this.pageWidth - this.title.length() - this.pageNumberWidth - 1;

      // Number of dots to be printed
      final int numberPadding = this.pageNumberWidth + 1;

      // Print the title
      System.out.print( this.title );

      // Print the fill char
      System.out.print( String.format("%" + fillWidth + "c", this.fillCharacter).replace(' ', this.fillCharacter) );

      // Print the page number
      System.out.print( String.format("%" + numberPadding + "s%n", this.pageNumber) );
   }
}