package cr.ac.ucr;

import java.util.Scanner;

public class Bomberman {

  Scanner input = null;

  public static void main(final String[] args) {
    // System.out.println("Bomberman---");
    Bomberman bomberman = new Bomberman();
    bomberman.run();
  }

  public void run() {
    this.input = new Scanner(System.in);

    // Lectura de datos sobre el tamaño de la matriz.
    int nRows = this.input.nextInt();
    int nCols = this.input.nextInt();

    // Lectura de datos sobre el jugador.
    int poderAtaque = this.input.nextInt();
    int posJugRow = this.input.nextInt();
    int posJuCol = this.input.nextInt();

    // Lectura de los datos de los enemigos.
    int numEnemigos =  this.input.nextInt();
    int[][] coordEnemigos = new int[numEnemigos][2];
    for (int elEnemigo = 0; elEnemigo < numEnemigos; elEnemigo++) {
      coordEnemigos[elEnemigo][0] = this.input.nextInt();
      coordEnemigos[elEnemigo][1] = this.input.nextInt();
    }

    // Lectura de los datos de las bombas.
    int numBombas = this.input.nextInt();
    int[][] coordBombas = new int[numBombas][2];
    for (int laBomba = 0; laBomba < numBombas; laBomba++) {
      coordBombas[laBomba][0] = this.input.nextInt();
      coordBombas[laBomba][1] = this.input.nextInt();
    }

    //Leer matriz
    char[][] matriz = new char[nRows][nCols];
    this.input.useDelimiter("[\\s]*");
    for (int row = 0; row < nRows; row++) {
      for (int col = 0; col < nCols; col++) {
        matriz[row][col] = this.input.next().charAt(0);
      }
    }

    //Actualizar la Matriz con el jugador
    matriz[posJugRow][posJuCol] = '@';
    // Actualizar la Matriz con enemigos
    for (int elEnemigo = 0; elEnemigo < numEnemigos; elEnemigo++) {
      int posEneRow = coordEnemigos[elEnemigo][0];
      int posEneCol = coordEnemigos[elEnemigo][1];
      matriz[posEneRow][posEneCol] = '!';
    }

    // Actualizar la Matriz con bombas
    for (int laBomba = 0; laBomba < numBombas; laBomba++) {
      int posBomRow = coordBombas[laBomba][0];
      int posBomCol = coordBombas[laBomba][1];
      matriz[posBomRow][posBomCol] = '*';
    }

    // Actualizar matriz
    matriz = actualizarMatriz(matriz, nRows, nCols, poderAtaque, numBombas,
        coordBombas);

    // Imprimir matriz
    imprimirMatriz(matriz, nRows, nCols);
  }

  public char[][] actualizarMatriz(final char[][] matriz, final int nRows,
                                   final int nCols, final int poderAtaque,
                                   final int numBombas,
                                   final int[][] coordBombas) {
    char[][] nuevaMatriz = matriz.clone();
    if (poderAtaque > 0) {
      for (int laBomba = 0; laBomba < numBombas; laBomba++) {
        int posLaBomRow = coordBombas[laBomba][0];
        int posLaBomCol = coordBombas[laBomba][1];

        //Estalla la bomba y queda en vacío
        nuevaMatriz[posLaBomRow][posLaBomCol] = '.';

        // Recorrido explosión hacia arriba
        for (int recorrido = 0; recorrido <= poderAtaque; recorrido++)  {
          if (nuevaMatriz[posLaBomRow - recorrido][posLaBomCol] == '.'
              || nuevaMatriz[posLaBomRow - recorrido][posLaBomCol] == '*') {
            continue;
          } else if (nuevaMatriz[posLaBomRow - recorrido][posLaBomCol] == '#') {
            break;
          } else if (nuevaMatriz[posLaBomRow - recorrido][posLaBomCol] == '%') {
            nuevaMatriz[posLaBomRow - recorrido][posLaBomCol] = '.';
            break;
          } else if (nuevaMatriz[posLaBomRow - recorrido][posLaBomCol] == '!'
              || nuevaMatriz[posLaBomRow - recorrido][posLaBomCol] == '@') {
            nuevaMatriz[posLaBomRow - recorrido][posLaBomCol] = '.';
          }
        }
        // Recorrido de la explosión hacia abajo
        for (int recorrido = 0; recorrido <= poderAtaque; recorrido++)  {
          if (nuevaMatriz[posLaBomRow + recorrido][posLaBomCol] == '.'
              || nuevaMatriz[posLaBomRow + recorrido][posLaBomCol] == '*') {
            continue;
          } else if (nuevaMatriz[posLaBomRow + recorrido][posLaBomCol] == '#') {
            break;
          } else if (nuevaMatriz[posLaBomRow + recorrido][posLaBomCol] == '%') {
            nuevaMatriz[posLaBomRow + recorrido][posLaBomCol] = '.';
            break;
          } else if (nuevaMatriz[posLaBomRow + recorrido][posLaBomCol] == '!'
              || nuevaMatriz[posLaBomRow + recorrido][posLaBomCol] == '@') {
            nuevaMatriz[posLaBomRow + recorrido][posLaBomCol] = '.';
          }
        }
        // Recorrido de la explosión hacia izquierda
        for (int recorrido = 0; recorrido <= poderAtaque; recorrido++)  {
          if (nuevaMatriz[posLaBomRow][posLaBomCol - recorrido] == '.'
              || nuevaMatriz[posLaBomRow][posLaBomCol - recorrido] == '*') {
            continue;
          } else if (nuevaMatriz[posLaBomRow][posLaBomCol - recorrido] == '#') {
            break;
          } else if (nuevaMatriz[posLaBomRow][posLaBomCol - recorrido] == '%') {
            nuevaMatriz[posLaBomRow][posLaBomCol - recorrido] = '.';
            break;
          } else if (nuevaMatriz[posLaBomRow][posLaBomCol - recorrido] == '!'
              || nuevaMatriz[posLaBomRow][posLaBomCol - recorrido] == '@') {
            nuevaMatriz[posLaBomRow][posLaBomCol - recorrido] = '.';
          }
        }
        // Recorrido de la explosión hacia derecha
        for (int recorrido = 0; recorrido <= poderAtaque; recorrido++)  {
          if (nuevaMatriz[posLaBomRow][posLaBomCol + recorrido] == '.'
              || nuevaMatriz[posLaBomRow][posLaBomCol + recorrido] == '*') {
            continue;
          } else if (nuevaMatriz[posLaBomRow][posLaBomCol + recorrido] == '#') {
            break;
          } else if (nuevaMatriz[posLaBomRow][posLaBomCol + recorrido] == '%') {
            nuevaMatriz[posLaBomRow][posLaBomCol + recorrido] = '.';
            break;
          } else if (nuevaMatriz[posLaBomRow][posLaBomCol + recorrido] == '!'
              || nuevaMatriz[posLaBomRow][posLaBomCol + recorrido] == '@') {
            nuevaMatriz[posLaBomRow][posLaBomCol + recorrido] = '.';
          }
        }
      }
    } else if (poderAtaque < 0) {
      for (int laBomba = 0; laBomba < numBombas; laBomba++) {
        // Encontrar la fila (row) inicial y la fila final del área de explosión
        int myPosRowIni = Math.max(coordBombas[laBomba][0] - poderAtaque, 0);
        int myPosRowFin = Math.min(coordBombas[laBomba][0]
            + poderAtaque, nRows);
        // Encontrar la columna inicial y la final del área de explosión
        int myPosColIni = Math.max(coordBombas[laBomba][1] - poderAtaque, 0);
        int myPosColFin = Math.min(coordBombas[laBomba][1]
            + poderAtaque, nCols);
        //Revisar el area de explosión
        for (int row = myPosRowIni; row < myPosRowFin; row++) {
          for (int col = myPosColIni; col < myPosColFin; col++) {
            if (nuevaMatriz[row][col] == '.' || nuevaMatriz[row][col] == '*'
                || nuevaMatriz[row][col] == '#') {
              continue;
            } else if (nuevaMatriz[row][col] == '%'
                || nuevaMatriz[row][col] == '!'
                || nuevaMatriz[row][col] == '@') {
              nuevaMatriz[row][col] = '.';
            }
          }
        }
      }
    }
    return nuevaMatriz;
  }

  public void imprimirMatriz(final char[][] matriz, final int nRows,
                             final int nCols) {
    for (int row = 0; row < nRows; row++) {
      for (int col = 0; col < nCols; col++) {
        System.out.print("" + matriz[row][col]);
      }
      System.out.println("");
    }
  }
}





















