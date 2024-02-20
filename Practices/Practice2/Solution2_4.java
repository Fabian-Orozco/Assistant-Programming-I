import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution2_4 {

    public static void main(String args[] ) throws Exception {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT */
        Scanner input = new Scanner(System.in);
        while (input.hasNextDouble()) {
          // Get the professor's grade
          final double profGrade = input.nextDouble();

          // If the grade is valid
          if ( profGrade >= 0.0 && profGrade <= 100.0 ) {
              // Calculate the 10-based grade and print it
              final double registerGrade = Math.round(profGrade * 0.2) * 0.5;
              System.out.printf("%4.1f ", registerGrade);

              // Print the result's acronym
              if ( registerGrade >= 7.0 )
                  System.out.printf("AP\n");
              else if ( registerGrade < 6.0 )
                  System.out.printf("PE\n");
              else
                  System.out.printf("AMP\n");
          }
          else {
              // Report that the grade given by professor was invalid
              System.out.printf("%.2f ERR\n", profGrade);
            }
        }
    }
}