package cr.ac.ucr;

import java.util.ArrayList;
import java.util.Collections;
import  java.util.Scanner;

/**
 * Calcula la mediana de una lista.
 *
 * @author Programación 1 Gr901
 * @version 1
 */
public class Mediana {

  private Scanner input = null;

  public void run() {
    // Instancia de la clase Scanner que lee de la entrada estándar.
    this.input = new Scanner(System.in);

    // Creamos una lista de Doubles.
    ArrayList<Double> numeros = new ArrayList<Double>();

    // Leer la entrada y guardarla en la lista.
    while (this.input.hasNextDouble()) {
      numeros.add(this.input.nextDouble());
    }

    Collections.sort(numeros);

    double mediana = 0.0;

    if (numeros.size() % 2 == 0) {
      mediana = (numeros.get(numeros.size() / 2 )
          + numeros.get((numeros.size() - 1) / 2 )) / 2.0;
    } else {
      mediana = numeros.get(numeros.size()/2);
    }

    System.out.println("La mediana es " + mediana);

    // Close the standard input
    this.input.close();
  }
}

