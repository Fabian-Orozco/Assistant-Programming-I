package cr.ac.ucr;

import java.util.Scanner;

/**
 * Calcula las permutaciones y combinaciones de r elementos tomados de un
 * conjunto de tamaño n.
 *
 * @author Gr901 Programación 1.
 * @version 1.0
 */
public class PermutacionCombinacion {
  /**
   * Lee la entrada del usuario por medio de la entrada estándar.
   */
  private Scanner input;

  /**
   * Método main.
   *
   * @param args Argumentos del programa.
   */
  public static void main(final String[] args) {
    PermutacionCombinacion myPerCom = new PermutacionCombinacion();
    myPerCom.run();
  }

  /**
   * Método que se encarga de la lógica de nuestro programa.
   */
  public void run() {
    this.input = new Scanner(System.in);
    long n = this.input.nextLong();
    long r = this.input.nextLong();

    if (n < r || n < 0 || r < 0) {
      System.err.println("Entrada inválida");
      return;
    }

    long permutacionNoRep = calcularPermutacionNoRep(n, r);
    long permutacionConRep = calcularPermutacionConRep(n, r);
    long combinacionNoRep = calcularCombinacionNoRep(n, r);
    long combinacionConRep = calcularCombinacionConRep(n, r);

    String resultado = crearTabla(permutacionNoRep, permutacionConRep,
        combinacionNoRep, combinacionConRep);

    System.out.print(resultado);
  }

  /**
   * Calcula el factorial de n.
   *
   * @param n {@code long} al que se le calcula el factorial.
   * @return Retorna un {@code long} con el factorial de n.
   */
  public long factorial(final long n) {
    if (n == 0) {
      return 1;
    }
    int index = 1;
    long factorial = 1;
    while (index <= n) {
      factorial = factorial * index;
      index++;
    }
    return factorial;
  }

  /**
   * Calcula las permutaciones de tamaño r utilizando un conjunto de n elemento
   * sin tomar elementos repetidos.
   *
   * @param n {@code long} que representa el número de elementos del conjunto.
   * @param r {@code long} que representa el número de elementos de la
   *                      permutación.
   * @return Retorna un {@code long} con el número de permutaciones sin
   *         repetición.
   */
  public long calcularPermutacionNoRep(final long n, final long r) {
    long miPermutacionNoRep = 1;
    for (int index = (int) (n - r + 1); index <= n; index++) {
      miPermutacionNoRep = miPermutacionNoRep * index;
    }
    return miPermutacionNoRep;
  }

  /**
   * Calcula las permutaciones de tamaño r utilizando un conjunto de n elemento
   * tomando elementos repetidos.
   *
   * @param n {@code long} que representa el número de elementos del conjunto.
   * @param r {@code long} que representa el número de elementos de la
   *                      permutación
   * @return Retorna un {@code long} con el número de permutaciones con
   *         repetición.
   */
  public long calcularPermutacionConRep(final long n, final long r) {
    long miPermutacionConRep = 1;
    for (int index = 1; index <= r; index++) {
      miPermutacionConRep = miPermutacionConRep * n;
    }
    return miPermutacionConRep;
  }

  /**
   * Calcula las combinaciones de tamaño r utilizando un conjunto de n elemento
   * sin tomar elementos repetidos.
   *
   * @param n {@code long} que representa el número de elementos del conjunto.
   * @param r {@code long} que representa el número de elementos de la
   *                      combinación
   * @return Retorna un {@code long} con el número de combinaciones sin
   *         repetición.
   */
  public long calcularCombinacionNoRep(final long n, final long r) {
    long miCombinacionNoRep = calcularPermutacionNoRep(n, r) / factorial(r);
    return miCombinacionNoRep;
  }

  /**
   * Calcula las combinaciones de tamaño r utilizando un conjunto de n elemento
   * tomando en cuenta elementos repetidos.
   *
   * @param n {@code long} que representa el número de elementos del conjunto.
   * @param r {@code long} que representa el número de elementos de la
   *                      combinación
   * @return Retorna un {@code long} con el número de combinaciones con
   *         repetición.
   */
  public long calcularCombinacionConRep(final long n, final long r) {
    long miCombinacionConRep = calcularCombinacionNoRep((n + r - 1), r);
    return miCombinacionConRep;
  }

  /**
   * Crea una tabla con el formato solicitado.
   *
   * @param permutacionNoRep {@code long} que representa una permutación sin
   *                         repetición.
   * @param permutacionConRep {@code long} que representa una permutación con
   *                          repetición.
   * @param combinacionNoRep {@code long} que representa una combinación sin
   *                       repetición.
   * @param combinacionConRep {@code long} que representa una combinación con
   *                          repetición.
   * @return Un {@code String} con la tabla.
   */
  public String crearTabla(final long permutacionNoRep,
                           final long permutacionConRep,
                           final long combinacionNoRep,
                           final long combinacionConRep) {
    String tabla = "                   No repetitions     With repetitions\n";
    String rowPermutacion = String.format("Permutations %20d %20d%n",
        permutacionNoRep, permutacionConRep);
    String rowCombinacion = String.format("Combinations %20d %20d",
        combinacionNoRep, combinacionConRep);
    tabla = tabla + rowPermutacion + rowCombinacion;

    return tabla;
  }
}
