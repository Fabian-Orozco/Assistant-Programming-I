package Consultas;

public class Matriz {
  private int filas;
  private int columnas;
  private int[][] matriz;

  public Matriz(int filas, int columnas) {
    this.filas = filas;
    this.columnas = columnas;
    matriz = new int[this.filas][this.columnas];
  }

  private void llenar() {
    int counter = 1;
    for (int fila = 0; fila < this.filas; ++fila) {
      for (int columna = 0; columna < this.columnas; ++columna) {
        this.matriz[fila][columna] = counter++;
      }
    }
  }

  private void imprimir(String nombreMatriz) {
    System.out.println("matriz: " + nombreMatriz);
      for (int fila = 0; fila < this.filas; ++fila) {
        for (int columna = 0; columna < this.columnas; ++columna) {
          System.out.print("[" + this.matriz[fila][columna] + "]");
        }
        System.out.println();
    }
  }

  private void rotar() {
    int[][] nuevaMatriz = new int[columnas][filas];
    System.out.println("\npasos");

    int tamanio = this.matriz.length;
    for (int fila = 0; fila < this.filas; ++fila) {
      for (int columna = 0; columna < this.columnas; ++columna) {
        nuevaMatriz[columna][tamanio-fila-1] = this.matriz[fila][columna];
        System.out.println("pos N[" + columna + "][" + (tamanio-fila-1) + "] = M[" + fila + "][" + columna + "]");
      } 
    }
    this.matriz = nuevaMatriz;
  }

  
  public static void main(String[] args) {
    System.out.println("Start\n");

    Matriz m1 = new Matriz(3,3);
    m1.llenar();
    m1.imprimir("m1 inicial");
    m1.rotar();
    m1.imprimir("\nm1 rotada");

    System.out.println("\nEnd");

  }
}