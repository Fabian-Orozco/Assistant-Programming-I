package cr.ac.ucr;

import java.util.Scanner;
import java.util.Arrays;

/***
 * Calcula la mediana estadística de una lista de números reales.
 *
 * @author Programación 1 Gr901
 * @version 1.0
 */
public class Mediana {

  /**
   * Realiza la lectura de los datos utilizando la entrada estándar.
   */
  private Scanner input;

  /**
   * Método main inicializa el programa.
   *
   * @param args Argumentos del programa.
   */
  public static void main(final String[] args) {
    System.out.println("Hola estoy funcionando!");
    Mediana calMediana = new Mediana();
    calMediana.ejecutar();
  }

  /**
   * El procedimiento {@code ejecutar} se encarga de la lógica del programa.
   */
  public void ejecutar() {
    this.input = new Scanner(System.in);
    int numElementos = this.input.nextInt();
    double[] arrayElementos = lectura(numElementos);
    double mediana = calcularMediana(numElementos, arrayElementos);
    imprimirMediana(mediana);
  }

  /**
   * El procedimiento {@code lectura} se encarga de la lectura de los números
   * reales que se introducen en la entrada estándar.
   *
   * @param numElementos Es un entero de tipo {@code in} que indica el número de
   *                     números reales que hay que leer de la entrada estándar.
   * @return Retorna un arreglo de {@code doubles} de una dimensión con los
   *         números reales.
   */
  public double[] lectura(final int numElementos) {
    double[] arrayElementos = new double[numElementos];
    for (int index = 0; index < numElementos; index++) {
      arrayElementos[index] = this.input.nextDouble();
    }
    return arrayElementos;
  }

  /**
   * El método {@code calcularMediana} calcula la mediana de un arreglo de
   * números reales.
   *
   * @param numElementos Es un número de tipo {@code int} que indica el tamaño
   *                     del arreglo.
   * @param arrayElementos Arreglo de {@code doubles} al que hay que sacar la
   *                       mediana.
   * @return Se retorna un {@code double} con el resultado de la mediana
   *         estadística de arreglo.
   */
  public double calcularMediana(final int numElementos,
                                final double[] arrayElementos) {
    double mediana = 0.0;
    double[] myArrayElementos = arrayElementos;

    //java.util.Arrays.sort(myArrayElementos);
    myArrayElementos = ordenarArray(numElementos, myArrayElementos);

    if ((numElementos % 2) == 0) {
      mediana = (myArrayElementos[(numElementos / 2) - 1 ]
          + myArrayElementos[(numElementos / 2)]) / 2;
    } else {
      mediana = myArrayElementos[(numElementos / 2)];
    }
    return mediana;
  }

  /**
   *
   * @param numElementos
   * @param arrayElementos
   * @return
   */
  public double[] ordenarArray(final int numElementos,
                               final double[] arrayElementos) {
    double[] array = arrayElementos;
    for (int indexI = 0; indexI <= numElementos - 2; indexI++) {
      for (int indexJ = indexI; indexJ <= numElementos - 2; indexJ++) {
        if (array[indexJ+1] < array[indexI]) {
          double tmp = array[indexI];
          array[indexI] = array[indexJ+1];
          array[indexJ+1] = tmp;
        }
      }
    }
    return array;
  }

  /**
   * Imprime la mediana con el formato solicitado.
   * @param mediana Recibe un {@code double}.
   */
  public void imprimirMediana(final double mediana) {
    System.out.printf("%.2f", mediana);
  }
}
