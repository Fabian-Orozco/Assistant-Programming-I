import java.util.Scanner;

/**
 * Encrypts or decrypts texts using the Caesar algorithm
 */
class Solution3_5
{
   /**
    * Gets data from standard input
    */
   private Scanner input;

   /**
    * Start the execution of the solution
    * @param args Command line arguments
    */
   public static void main(String[] args)
   {
      Solution3_5 solution = new Solution3_5();
      solution.run();
   }

   /**
    * Run the solution. This method is called from main()
    */
   public void run()
   {
      // Create object to read data from standard input
      this.input = new Scanner(System.in);
// <body>
    final int key = this.input.nextInt() % 26;
    final int instruction = this.input.nextInt();
    this.input.nextLine();
    this.input.useDelimiter("");
    if (isValid(key, instruction)) {
        while (this.input.hasNext()) {
            char character = input.next().charAt(0);
            if (character >= 'a' && character <= 'z' 
                || character >= 'A' && character <= 'Z'){
                if (instruction == 1) {
                    character = encrypt(character, key);
                } else {
                    character = decrypt(character, key);
                }
                System.out.printf("%c", character);
            }
        }
      }
      // Close the standard input
      this.input.close();
   }
   private char encrypt(char character, final int key) {
       if (Character.isUpperCase(character) && character + key > 90) {
               return (char) (character + key - 26);
       } else if (Character.isLowerCase(character) && character + key > 122) {
           return (char) (character + key - 26);
       } else return (char) (character + key);
   }
   
   private char decrypt(char character, final int key) {
       if (Character.isUpperCase(character) && character - key < 65) {
           return (char) (character - key + 26);
       } else if (Character.isLowerCase(character) && character - key < 97) {
           return (char) (character - key + 26);
       } else return (char) (character - key);
   }
   
   private boolean isValid(final int key, final int instruction) {
       if (key < 0) {
           System.out.printf("invalid key");
           return false;
       } else if (instruction != 0 && instruction != 1) {
           System.out.printf("invalid instruction");
           return false;
       }
       return true;
   }
}





