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

    Fraction f1 = new Fraction(num1, den1);
    Fraction f2 = new Fraction(num2, den2);

    switch (op.charAt(0)) {
      case '+':
        System.out.println(f1.sumar(f2).toString());
        break;
      case '*':
        System.out.println(f1.multiplicar(f2).toString());
        break;
      default:
        System.out.println("Operador " + op + " no es válido.");
        break;
    }
  }
}
