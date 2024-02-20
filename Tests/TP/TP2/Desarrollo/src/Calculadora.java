package Evaluaciones.TP.TP2.Desarrollo.src;

import java.math.BigDecimal;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * Se encarga de ejecutar el programa y la lógica del programa.
 */
public class Calculadora {

  private Scanner input;

  /**
   * Método main inicia el programa.
   * @param args Argumentos del programa.
   */
  public static void main(final String[] args) {
    System.out.println("Fraction");
    Calculadora cal = new Calculadora();
    cal.run();
  }

  /**
   * Método se encarga de la lógica de la calculadora.
   */
  public void run() {

    Fraccion productoria = new Fraccion(new BigDecimal(2), new BigDecimal(1));
    productoria = productoria.multiplicar(new Fraccion(new BigDecimal(34), new BigDecimal(1)));
    productoria = productoria.multiplicar(new Fraccion(new BigDecimal(35), new BigDecimal(1)));
    System.out.println(productoria.toString());
    this.input = new Scanner(System.in);

    Pattern patron = this.input.delimiter();
    this.input.useDelimiter("[\\s/]+");

    System.out.print("fr> ");
    String num1 = this.input.next();
    String den1 = this.input.next();
    System.out.print("op> ");
    String op = this.input.next();
    System.out.print("fr> ");
    String num2 = this.input.next();
    String den2 = this.input.next();

    this.input.useDelimiter(patron);

    Fraccion f1 = new Fraccion(num1, den1);
    Fraccion f2 = new Fraccion(num2, den2);

    switch (op.charAt(0)) {
      case '+':
        System.out.println(f1.sumar(f2).toString());
        break;
      case '*':
        System.out.println(f1.multiplicar(f2).toString());
        break;
      case '-':
        System.out.println(f1.restar(f2).toString());
      break;
      case '/':
        System.out.println(f1.dividir(f2).toString());
      break;
      case '.':  // all
        System.out.println("+: " + f1.sumar(f2).toString());
        System.out.println("-: " + f1.restar(f2).toString());
        System.out.println("/: " + f1.dividir(f2).toString());
        System.out.println("*: " + f1.multiplicar(f2).toString());
        System.out.println(f1.toString() + " is less than " + f2.toString() + "? : " + f1.isLessThan(f2));
        System.out.println(f1.toString() + " is grater than " + f2.toString() + "? : " + f1.isGreaterThan(f2));
        break;
      default:
        System.out.println("Operador " + op + " no es válido.");
        break;
    }
  }
}
