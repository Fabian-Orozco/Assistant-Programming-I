import java.util.Scanner;

/**
 * Indicates if numbers are happy or not in a limited number of steps
 */
public class Solution
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
		Solution solution = new Solution();
		solution.run();
	}

	/**
	 * Run the solution. This method is called from main()
	 */
	public void run()
	{
		// Create object to read data from standard input
		this.input = new Scanner(System.in);

		// The first number is the number of steps
		long maxSteps = this.input.nextLong();

		// Read numbers from standard input
		while ( this.input.hasNextLong() )
		{
			// Read and print the number
			final long number = this.input.nextLong();
			System.out.printf("%d: ", number);

			// Check for negatives or zero numbers
			if(number == 0)
				System.out.println("unhappy");
			else if (number < 0)
				System.out.println("invalid number");
			else
			{
				// Number is positive, check if it is happy in maxSteps
				final long totalSteps = isHappyIn(number, maxSteps);

				// Print the result
				if (totalSteps != -1)
					System.out.printf("happy in %d steps%n", totalSteps);
				else
					System.out.printf("unhappy in %d steps%n", maxSteps);
			}
		}

		// Remember to always close the input
		this.input.close();
	}
    
    /**
    * Calcula la suma iterada del cuadrado de los dígitos de un número. Retorna -1 si el   
    * resultado es distinto de 1 en un número máximo de iteraciones. De lo contrario 
    * retorna el número de pasos hasta llegar 1.
    * 
    * @param number Número que se verificara si es feliz en un número de pasos. 
    * @param maxSteps Número máxiomo de pasos. 
    * @return contador Número de iteraciones hasta que llegue a menos 1. Este número 
    *                  debe ser menos a el número de iteraciones. De lo contrario retorna
    *                  -1. 
    */
    public long isHappyIn(final long number, final long maxSteps) {
        long suma = number;
        long contador = 0;
        for (int index = 0; index < maxSteps; index++) {
            suma = sumaDeCuadrados(suma);
            contador++;
            if (suma == 1) {
                break;
            }
        }
        if (suma != 1){
            contador= -1;
        }
        return contador;
    }
    
    /**
    * Calcula la suma de cuadrados de los dígitos de un número.
    * @param number Número al que se le suman los cuadros de sus dígitos. 
    *
    * @return sumaDeCuadros Retorna la suma de los cuadrados. 
    */
    public long sumaDeCuadrados(final long number) {
        // Solución 3
        long myNumber = number;
        long sumaDeCuadrados = 0;
        while (myNumber > 0) {
            sumaDeCuadrados = sumaDeCuadrados + ((myNumber%10) * (myNumber%10));
            myNumber = myNumber / 10;
        }
        
        /*
        // Solución 2
        long myNumber = number;
        String strNumb = "" + number;
        int numDigitos = strNumb.length();
        long sumaDeCuadrados = 0;
        for (int index = 0; index < numDigitos; index++) {
            int digito = Integer.valueOf(strNumb.substring(index, index+1)); //""+strNumb.charAt(index)
            sumaDeCuadrados = sumaDeCuadrados + (digito * digito);
        }
        
        // Solución 1
        long myNumber = number;
        int numDigitos = ("" + number).length();
        long sumaDeCuadrados = 0;
        for (int index = 0; index < numDigitos; index++) {
            sumaDeCuadrados = sumaDeCuadrados + ((myNumber%10) * (myNumber%10));
            myNumber = myNumber / 10;
        }
        */
        return sumaDeCuadrados;
    }
}