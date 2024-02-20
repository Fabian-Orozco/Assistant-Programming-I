package cr.ac.ucr.ecci.ci0112;

import java.util.Scanner;

public class IndiceMasaCorporal {

  private Scanner input;

  public static void main(final String[] args) {
    IndiceMasaCorporal miIndice = new IndiceMasaCorporal();
    miIndice.run();
  }

  public void run() {
    input = new Scanner(System.in);

    final int maxMasa = 1000;
    final int minMasa = 0;

    final int maxAltura = 300;
    final int minAltura = 0;

    int masa = input.nextInt();
    int altura = input.nextInt();

    if (masa > maxMasa || masa < minMasa) {
      System.out.print("datos inválidos");
    }
    if (altura > maxAltura || altura <= minAltura) {
      System.out.print("datos inválidos");
    }

    double indiceMasaCorporal = calcularIMC(masa, altura);
    String estado = clasificarIMC(indiceMasaCorporal);
    imprimirRespuesta(masa, altura, indiceMasaCorporal, estado);
  }

  public double calcularIMC(int masa, int altura) {
    // create IMC := masa / (altura/100)^2
    double indice = (double)masa / Math.pow(((double)altura / 100), 2);
    return indice;
  }

  public String clasificarIMC (final double indiceMasaCorporal) {
    if (indiceMasaCorporal < 18.5) {
      return "Bajo peso";
    } else if (indiceMasaCorporal < 25) {
      return "Normal";
    } else if (indiceMasaCorporal < 30) {
      return "Sobrepeso";
    } else {
      return "Obesidad";
    }
  }

  public void imprimirRespuesta(int masa, int altura, double indice,
                                String estado) {
    System.out.printf("%d %d %.2f %s", masa, altura, indice, estado);
  }
}
