import java.math.BigInteger;
import java.util.Scanner;

public class MCD {
  
  public static void main(String[] args) {
    System.out.println("Inicia");

    run();

    System.out.println("Termina");
  }

  private static void run() {
    // Ejemplo 1.
    int numero1 = 15000;
    int numero2 = 8540;
    System.out.println(calcularMCD(numero1, numero2));

    // Ejemplo 2.
    Scanner input = new Scanner(System.in);

    System.out.print("Ingrese el primer valor: ");
    String numero3 = input.next();

    System.out.print("Ingrese el segundo valor: ");
    String numero4 = input.next();
    
    System.out.println("Resultado: " + calcularMCD(new BigInteger(numero3), new BigInteger(numero4)));


  }

  // MCD de num1 y num2
  private static int calcularMCD (int num1, int num2) {
    if (num2 == 0) {
      return num1;
    } else {
      return calcularMCD(num2, num1 % num2);
    }
  }

  // MCD de num1 y num2 utilizando bigInteger
  private static BigInteger calcularMCD (BigInteger num1, BigInteger num2) {
    if (num2.equals(BigInteger.ZERO)) {
      return num1;
    } else {
      return calcularMCD(num2, num1.mod(num2));
    }
  }
}