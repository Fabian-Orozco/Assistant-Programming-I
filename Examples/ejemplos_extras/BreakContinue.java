package cr.ac.ucr;

/**
 * Clase para mostrar funcionamiento de la instrucción CONTINUE Y BREAK.
 *
 * @author Programación 1 Gr901
 * @version 1.0
 */
public class BreakContinue {

  /**
   * Método {@code main} inicia la ejecución del programa.
   * @param arg Argumentos del programa.
   */
  public static void main(final String[] arg) {
    System.out.println("Todo funcionando!");
    BreakContinue bc = new BreakContinue();
    bc.run();
  }

  /**
   * Se encarga de la lógica del programa.
   */
  public void run() {
    ejemploBreakFor();
    System.out.println("-----------------------------");
    ejemploBreakWhile();
    System.out.println("-----------------------------");
    ejemploContinueFor();
    System.out.println("-----------------------------");
    ejemploContinueWhile();
  }

  /**
   * Procedimiento para mostrar el funcionamiento de la instrucción BREAK en un
   * ciclo FOR.
   */
  public void ejemploBreakFor() {
    for (int index = 0; index < 10; index++) {
      if (index == 5) {
        break;
      }
      System.out.println("Voy por: " + index);
    }
    System.out.println("Fin del for");
  }

  /**
   * Procedimiento para mostrar el funcionamiento de la instrucción BREAK en un
   * ciclo WHILE.
   */
  public void ejemploBreakWhile() {
    int contador = 0;
    while (contador < 10) {
      if (contador == 5) {
        break;
      }
      System.out.println("Voy por:" + contador);
      contador++;
    }
    System.out.println("Fin del while");
  }

  /**
   * Procedimiento para mostrar el funcionamiento de la instrucción CONTINUE en
   * un ciclo WHILE.
   */
  public void ejemploContinueFor() {
    for (int index = 0; index < 10; index++) {
      //System.out.println("Trabajando...");
      if (index == 5) {
        continue;
      }
      System.out.println("Voy por: " + index);
    }
    System.out.println("Fin del for");
  }
  /**
   * Procedimiento para mostrar el funcionamiento de la instrucción CONTINUIE en
   * un ciclo WHILE.
   */
  public void ejemploContinueWhile() {
    int contador = 0;
    while (contador < 10) {
      if (contador == 5) {
        contador++;
        continue;
      }
      System.out.println("Voy por:" + contador);
      contador++;
    }
    System.out.println("Fin del while");
  }
}
