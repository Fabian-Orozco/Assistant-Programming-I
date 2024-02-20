import java.io.*;
import java.util.*;

public class Sample {

    char laberinto[][] = {
        {'|', '|', '|'},
        {'a', '|', 'e'},
        {' ', '|', ' '},
        {' ', ' ', ' '},
        {'|', '|', '|'}
    };

    public static void main(String[] args) {
        Sample s1 = new Sample();
        s1.run();
    }
    // -------------------------------------------------------------------------

    private void run() {
        getInfo(this.laberinto, "Laberinto creado");

        if ( caminoEncontrado() ) {
            System.out.println("encontrado");
            getInfo(this.laberinto, "Laberinto resuelto");
        } else {
            System.out.println("no encontrado");
            getInfo(this.laberinto, "Laberinto NO resuelto");
        }
    }
    // -------------------------------------------------------------------------

    private boolean caminoEncontrado() {
        return avanzarEnCamino(1,0);
    }

    private boolean avanzarEnCamino(final int row,final  int column) {

        if(this.laberinto[row][column] != 'a') {
            this.laberinto[row][column] = '.';
        }

        if ( verificarCasilla(row - 1, column) ) return true;
        if ( verificarCasilla(row, column + 1) ) return true;
        if ( verificarCasilla(row + 1, column) ) return true;
        if ( verificarCasilla(row, column - 1) ) return true;

        this.laberinto[row][column] = ' ';
        return false;
    }

    private boolean verificarCasilla(final int row, final int column) {
        switch ( demeValor(row,column) ) {
            case 'e': return true;
            case '|': return false;
            case '.': return false;
            case ' ': return avanzarEnCamino(row, column);
        
            default: return false;
        }
    }

    private char demeValor(final int row, final int column) {
        char resultado = '0';

        if (row >= 0 && row < this.laberinto.length) {
            if (column >= 0 && column < this.laberinto[0].length) {
                resultado = laberinto[row][column];
            }
        }

        return resultado;
    }
    // -------------------------------------------------------------------------

    private void getInfo(char matriz[][], String nombre) {
        System.out.println("\n---Info de: " + nombre + "---");
        System.out.println("Cantidad de filas: " + matriz.length);
        System.out.println("Cantidad de columnas: " + matriz[0].length);
        System.out.println("Tamaño de la matriz: " + matriz.length * matriz[0].length + "\n");

        System.out.println("La matriz es: ");
        
        for (int fila = 0; fila < matriz.length; fila++) {
            for (int columna = 0; columna < matriz[0].length; columna++) {
                System.out.print(matriz[fila][columna] + " ");
            }
            System.out.println();
        }
        System.out.println("---------------------------");
    }

    private int[][] fillZero(int matriz[][], String nombre) {
        System.out.println("Fill zero - " + nombre);
        for (int fila = 0; fila < matriz.length; fila++) {
            for (int columna = 0; columna < matriz[0].length; columna++) {
                matriz[fila][columna] = 0;
            }
        }
        return matriz;
    }
}
