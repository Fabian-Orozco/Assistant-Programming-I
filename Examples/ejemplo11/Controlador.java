package cr.ac.ucr;

// import java.util.Scanner;

public class Controlador {
  // Scanner input = null;
  public static void main(final String[] args) {
    System.out.println("Controlador");
    Controlador miControlador = new Controlador();
    miControlador.run();
  }
  public void run() {
    ArbolBinarioBusqueda abb = new ArbolBinarioBusqueda();
    abb.agregar(6, 234);
    abb.agregar(1, 234);
    abb.agregar(4, 234);
    abb.agregar(9, 234);
    abb.agregar(5, 234);
    abb.agregar(7, 234);
    abb.imprimirEnOrden();
  }
}
