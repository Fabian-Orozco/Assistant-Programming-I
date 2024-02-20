package src;
import java.util.Scanner;

public class Solution {

  private Scanner input = null;

  public static void main(final String[] args) {
    Solution solution = new Solution();
    solution.run();
  }

  public void run() {
    this.input = new Scanner(System.in);
    problemOne();
    //problemTwo();
    // calculateFee();
    // formatBills();

  }

  public void problemOne() {
    while (this.input.hasNextInt()) {
      int arraySize = this.input.nextInt();
      int[] array = new int[arraySize];
      for (int i = 0; i < arraySize; i++) {
        array[i] = this.input.nextInt();
      }
      int max = largerNumberOfAscendingNumbers(array);
      System.out.println(max);
    }
  }

  public int largerNumberOfAscendingNumbers(final int[] array) {
    int max = 1;
    int tempMax = 1;
    for(int i = 0; i < array.length - 1; i++) {
      if (array[i]+1 == array[i+1]) {
        tempMax++;
        if(tempMax >= max) {
          max = tempMax;
        }
      } else {
        tempMax = 1;
      }
    }
    return max;
  }

  public void problemTwo() {
    System.out.printf("Palabra    Total%n");
    System.out.printf("---------- -----%n");
    while (this.input.hasNext()) {
      String text = this.input.nextLine();
      String subText = this.input.next();
      if (this.input.hasNextLine()) {
        this.input.nextLine();
        this.input.nextLine();
      }
      int count = countSubStrings(text, subText);
      System.out.printf("%10s %5d%n", subText, count);
    }
  }

  public int countSubStrings(final String text, final String subText) {
    int counter = 0;
    for (int i = 0; i < text.length(); i++) {
      boolean occurrence = false;
      if (text.charAt(i) == subText.charAt(0)) {
        occurrence = true;
        for (int j = 1; j < subText.length(); j++) {
          if (text.charAt(i+j) != subText.charAt(j)) {
            occurrence = false;
            break;
          }
        }
      }
      if (occurrence) {
        counter++;
      }
    }
    return counter;
  }

  public void calculateFee() {
    while (this.input.hasNext()) {
      String id = this.input.next();
      double totalIncome = 0;
      int counter = 0;
      while (this.input.hasNextDouble()) {
        counter++;
        totalIncome = totalIncome + this.input.nextDouble();
      }
      double fee = (totalIncome/counter) / 100.0 * 20.0;
      System.out.printf("%6s %7.2f%n", id, fee);
    }
  }

  public void formatBills() {
    int billNumber = 0;
    while (this.input.hasNext()) {
      billNumber++;
      String coupon = this.input.next();
      System.out.printf("FACTURA %03d%n", billNumber);
      double discount;
      if (coupon.charAt(0) == 'C') {
        discount = this.input.nextDouble();
        System.out.printf("Con cupon %2.0f%%%n", discount);
        System.out.printf("%10s %8s %8s%n", "ARTICULO","PRECIO", "DESCUENTO");
        //System.out.printf("Descuento:  %7.0f%%%n", discount);
      } else {
        discount = 0;
        System.out.println("Sin cupon");
        System.out.printf("%10s %8s %8s%n", "ARTICULO","PRECIO", "DESCUENTO");
        //System.out.printf("Producto: Precio  %7.0f %% %n", discount);
      }
      double totalWithoutDiscount = 0;
      double totalWithDiscount = 0;
      System.out.printf("---------- -------- --------%n");
      while (!this.input.hasNext("[CS]") && this.input.hasNext()) {
        String article = this.input.next();
        double articlePrice = this.input.nextDouble();
        double articlePriceAbs = Math.abs(articlePrice);
        if (articlePrice >= 0) {
          System.out.printf("%10s %8.2f%n", article, articlePriceAbs);
        } else {
          System.out.printf("%10s %8.2f %8.2f%n", article, articlePriceAbs,
              (articlePriceAbs / 100 * discount));
        }
        if (articlePrice >= 0) {
          totalWithoutDiscount = totalWithoutDiscount + articlePriceAbs;
        } else {
          totalWithDiscount = totalWithDiscount + articlePriceAbs;
        }
      }
      if (discount != 0) {
        totalWithDiscount = totalWithDiscount
            - (totalWithDiscount / 100 * discount);
      }
      double total = totalWithoutDiscount + totalWithDiscount;
      System.out.printf("---------- -------- --------%n");
      System.out.printf("%10s %8.2f %8.2f%n", "Subtotal", totalWithoutDiscount,
          (totalWithDiscount * -1));
      System.out.printf("%10s %8.2f%n", "Total", total);
      System.out.printf("============================%n");
    }
  }

}

