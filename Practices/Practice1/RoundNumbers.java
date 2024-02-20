// package cr.ac.ucr.ecci.ci0112;
package Practice1;
import java.util.Scanner;

public class  RoundNumbers{

  private Scanner input;

  public static void main(final String[] args) {
    RoundNumbers myRoundApp = new RoundNumbers();
    myRoundApp.run();
  }

  public void run () {
    System.out.println("Hola mundo");
    System.out.println("Hola mundo 2");

    int charTemp1 = 67;
    int charTemp2 = 68;
    char charTemp3 = 'F';
    int tmp1 = 8;
    double tmp2 = 23.45039354;
    //Imprime char
    System.out.printf("1La variable charTmp1 es: %c %n", charTemp1);
    System.out.printf("2La variable charTmp2 es: %c %n", charTemp2);
    System.out.printf("3La variable charTmp3 es: %c %n", charTemp3);
    System.out.printf("4La variable charTmp3 es: %h %n", charTemp3);
    //Imprime entero
    System.out.printf("5La variable tmp1 es: %d %n", tmp1);
    //Imprime flotante
    System.out.printf("6La variable tmp2 es: %f %n", tmp2);
    System.out.printf("7La variable tmp2 es: %.2f %n", tmp2);
    System.out.printf("8La variable tmp2 es: %.8f %n", tmp2);

    System.out.print("9[println] La variable tmp2 es:" + tmp2 + "\n");
    //Imprime
    System.out.printf("10La variable tmp2 es: %.8f %n", tmp2);

    System.out.printf("11La variable tmp2 tamanio 10 es: %10.2f %n", tmp2);
    System.out.printf("12La variable tmp2 tamanio 10 es: %010.2f %n", tmp2);
    //Notacion cientifica
    System.out.printf("13La variable tmp2 es: %e %n", tmp2);
    System.out.printf("14La variable tmp2 s: %g %n", tmp2);
  }
}