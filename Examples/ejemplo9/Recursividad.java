package cr.ac.ucr.recursividad;

public class Recursividad {

  public static void main1(final String[] args) {
    System.out.println("Estoy funcionando");
    Recursividad rev = new Recursividad();

    String texto = rev.textoReverso("abcde");
    System.out.println(texto);

    String texto2 = "reconocer";
    boolean esPal = rev.esPalindromo(texto2);
    if (esPal) {
      System.out.println(texto2 + " es palíndromo.");
    } else {
      System.out.println(texto2 + " NO es palíndromo.");
    }

    int num = 39;
    int fibo = rev.fibonacci(num);
    System.out.println("Fibonacci de " + num + " es " + fibo);
  }

  public int fibonacci(int num) {
    if (num == 0 || num == 1) {
      return num;
    }
    return fibonacci(num - 1) + fibonacci(num - 2);
  }

  public boolean esPalindromo(String texto) {
    if (texto.length() <= 1) {
      return true;
    }
    if (texto.charAt(0) == texto.charAt(texto.length()-1)) {
      return esPalindromo(texto.substring(1,texto.length()-1));
    }
    return false;
  }

  public String textoReverso(String texto){
    if (texto.length() == 1) {
      return texto.charAt(0) + "";
    }
    return textoReverso(texto.substring(1)) + texto.charAt(0);
  }
}
