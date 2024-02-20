package cr.ac.ucr.recursividad;

public class Reinas {

  int contadorReinas = 0;
  boolean solucionEncontrada = false;

  public static void main(final String[] args) {
    System.out.println("Problemas de las reinas");
    Reinas reinas = new Reinas();
    reinas.run();
  }

  public void run() {
    final int n = 8;
    final int[][] tablero = new int[n][n];

    for (int row = 0; row < n; row++) {
      for (int col = 0; col < n; col++) {
        tablero[row][col] = 0;
      }
    }

    boolean haySolucion = resolverReinas(tablero, n, 0);

    if (haySolucion) {
      for (int row = 0; row < n; row++) {
        for (int col = 0; col < n; col++) {
          System.out.print(tablero[row][col] + " ");
        }
        System.out.println("");
      }
    }
  }

  public boolean resolverReinas(int[][] tablero, int n, int col) {
    // Caso base
    if (this.contadorReinas == 8) {  // Si reinas colocadas es igual 8
      return true;  // Retornar verdadero
    }
    // FOR filas igual a 0 hasta n menos 1
    for (int row = 0; row < n; row++) {
      if (celdaValida(tablero, n, row, col)
          && !this.solucionEncontrada) {  // Si celda es válida
        tablero[row][col] = 1;  // Poner reina en la celda
        this.contadorReinas++;  // Sumarle uno a contador de reinas
        // SoluciónEncontrada sería igual a resolverReinas(tablero, n, col+1)
        this.solucionEncontrada = resolverReinas(tablero, n, col+1);
      }
      // SoluciónEncontrada es falsa
      if (!solucionEncontrada && tablero[row][col] == 1) {
        this.contadorReinas--;
        tablero[row][col] = 0;  // Quitar reina de la celda
      }
    }
    return this.solucionEncontrada;
  }

  private boolean celdaValida(int[][] tablero, int n, int row, int col) {
    // Revisar filas
    for (int myCol = 0; myCol < n; myCol++) {
      if (tablero[row][myCol] == 1) {
        return false;
      }
    }
    // Revisar columna
    for (int myRow = 0; myRow < n; myRow++) {
      if (tablero[myRow][col] == 1) {
        return false;
      }
    }
    // Diagonal ascendente izquierda
    int myRow = row;
    int myCol = col;
    for (int index = 1; index < n; index++) {
      myRow--;
      myCol--;
      if (myRow >= 0 && myCol >= 0) {
        if (tablero[myRow][myCol] == 1) {
          return false;
        }
      } else {
        break;
      }
    }
    //Diagonal descendente derecha
    myRow = row;
    myCol = col;
    for (int index = 1; index < n; index++) {
      myRow++;
      myCol++;
      if (myRow < n && myCol < n) {
        if (tablero[myRow][myCol] == 1) {
          return false;
        }
      } else {
        break;
      }
    }
    //Diagonal ascendente derecha
    myRow = row;
    myCol = col;
    for (int index = 1; index < n; index++) {
      myRow--;
      myCol++;
      if (myRow >= 0 && myCol < n) {
        if (tablero[myRow][myCol] == 1) {
          return false;
        }
      } else {
        break;
      }
    }
    //Diagonal descendente izquierda
    myRow = row;
    myCol = col;
    for (int index = 1; index < n; index++) {
      myRow++;
      myCol--;
      if (myRow < n && myCol >= 0) {
        if (tablero[myRow][myCol] == 1) {
          return false;
        }
      } else {
        break;
      }
    }
    return true;
  }
}
