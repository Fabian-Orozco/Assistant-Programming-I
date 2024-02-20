import java.util.Scanner;

/**
 * Prints the n-th line of Pascal's triangle for (a-b)^n
 */
public class Solution5_1
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
      Solution5_1 solution5_1 = new Solution5_1();
      solution5_1.run();
   }

// <body>

   /**
    * Runs the solution
    */
   public void run()
   {
      // Create object to read data from standard input
      this.input = new Scanner(System.in);

      // Repeat while there are exponents in standard input
      while ( this.input.hasNextInt() )
      {
         // Read exponents from standard input
         final int exponent = this.input.nextInt();

         // Calculate the polynomial and print it
         calculatePascalPolynomial(exponent);
      }

      // Close the standard input
      this.input.close();
   }

   /**
    * Imprime en la salida estandar los coeficientes del polinomio resultado
    * de (a+b)^n de acuerdo al Triangulo de Pascal.
    *
    * @param exponent El grado n que debe ser un numero natural
    */
   private void calculatePascalPolynomial(final int exponent)
   {
      // Check for invalid data or special cases
      if ( exponent < 0 )
         System.out.printf("%d: invalid data%n", exponent);
      else if ( exponent == 0 )
         System.out.println("0: 1");
      else if ( exponent == 1 )
         System.out.println("1: a - b");
      else
         printPascalPolynomial(exponent);
   }

   /**
    * Imprime en la salida estandar los coeficientes del polinomio resultado
    * de (a+b)^n de acuerdo al Triangulo de Pascal.
    *
    * @param exponent El grado n del polinomio que debe ser 2 o mas
    */
   private void printPascalPolynomial(final int exponent)
   {
      // Un arreglo de n+1 enteros es suficiente, se usa de derecha a izquierda
      final long[] coefficients = new long[exponent + 1];

      // Se inicializa en grado 2 cuya linea es [... 1 2 1]
      coefficients[exponent] = 1;
      coefficients[exponent - 1] = 2;
      coefficients[exponent - 2] = 1;

      // Se calculan los coeficientes de las restantes lineas usando el mismo arreglo
      for ( int row = 3; row <= exponent; ++row )
      {
         // Igual a que se hace en papel se agrega un 1, ej: [... 1 1 2 1]
         coefficients[exponent - row] = 1;
        // [0] = 1
         // Los demas coeficientes son el resultado de sumar los dos que estan sobre el
         // pero como es el mismo arreglo, es el resultado de sumar el cofieciente mismo
         // (puesto que tiene el de la linea anterior) mas el coeficiente siguiente que
         // (que tambien es de la linea anterior porque aun no ha sido actualizado)
         for ( int column = exponent - row + 1; column < exponent; ++column )
         {
            coefficients[column] += coefficients[column + 1];
         }
      }

      printFormattedPolynomial(coefficients, exponent);
   }

   /**
    * Imprime el arreglo de coeficientes con formato de polinomio
    *
    * @param coefficients Todos los coeficientes de (a-b)^n
    * @param exponent El grado n del polinomio que debe ser 2 o mas
    */
   private void printFormattedPolynomial(final long[] coefficients, final int exponent)
   {
      // Imprimir el polinomio en la salida estandar, siguiendo el formato solicitado. Ej:
      //  4: a^4 - 4a^3b + 6a^2b^2 - 4ab^3 + b^4
      //  6: a^6 - 6a^5b + 15a^4b^2 - 20a^3b^3 + 15a^2b^4 - 6ab^5 + b^6

      // Preceder al polinomio por su grado
      System.out.printf("%d: ", exponent);

      // Recorrer todos los coeficientes del polinomio (a - b)^n
      for ( int index = 0; index <= exponent; ++index )
      {
         // Coeficiente del monomio a^(n-index)b^index: se imprime si no es 1
         if ( coefficients[index] > 1 )
            System.out.print( coefficients[index] );

         // Exponente de a es n - index. Si n-1 es 0, la a no se imprime
         if ( exponent - index > 0 )
            System.out.print('a');
         // Si el exponente n - index es 1, no se imprime
         if ( exponent - index >= 2 )
            System.out.printf("^%d", exponent - index);

         // Exponente de b es index. Si index es 0, la b no se imprime
         if ( index > 0 )
            System.out.print('b');
         // Si el exponente en b^index es 1, no se imprime el exponente
         if ( index >= 2 )
            System.out.printf("^%d", index);

         // Si este no es el ultimo monomio, sigue otro, se separa por una suma o una resta
         // Ambas se intercalan dado que se debe imprimir (a-b)^n
         if ( index < exponent )
            System.out.printf(" %c ", index % 2 != 0 ? '+' : '-');
      }

      System.out.println();
   }
// </body>
}