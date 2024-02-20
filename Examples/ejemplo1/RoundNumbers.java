package cr.ac.ucr.ecci.ci0112;

import java.util.Scanner;

public class RoundNumbers {

  private Scanner input;

  public static void main(final String[] args) {
    RoundNumbers myRoundApp = new RoundNumbers();
    myRoundApp.run();
  }

  public void run () {
    input = new Scanner(System.in);
    roundDecimals();
  }

  public void roundDecimals() {
    while(input.hasNextDouble()) {
      double originalNumber = input.nextDouble();
      for (int decimals = 0; decimals <= 6; decimals++) {
        double roundedNumber = round(originalNumber, decimals);
        printAnswer(originalNumber,decimals,roundedNumber);
      }
    }
    input.close();
  }

  public double round(double originalNumber, int numberDecimal) {
    int lastDecimal = ((int)(originalNumber * Math.pow(10, numberDecimal + 1)))
        % 10;
    double roundedNumber;
    if (lastDecimal < 5) {
      roundedNumber = ((int)(originalNumber * Math.pow(10, numberDecimal)))
          / Math.pow(10, numberDecimal);
    } else {
      roundedNumber = (((int)(originalNumber * Math.pow(10, numberDecimal))
          + 1)) / Math.pow(10, numberDecimal);
    }
    return roundedNumber;
  }

  public void printAnswer(double originalNumber, int numberDecimals,
                          double roundedNumber) {
    String myString = "El numero %f redondeado a %d decimales es = %."
        + numberDecimals + "f %n";
    System.out.printf(myString, originalNumber, numberDecimals, roundedNumber);
  }

}
