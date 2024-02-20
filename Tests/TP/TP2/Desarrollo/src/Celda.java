package Evaluaciones.TP.TP2.Desarrollo.src;

/**
 * Clase que representa una celda de una hoja de cálculo y la transforma en las posiciones respectivas de una matriz.
 * Por ejemplo: A1 equivale a Fila : 0 Columna : 0.
 */
public class Celda {

  private int row = -1;
  private int column = -1;
  private String simbolo = "";

  /**
   * Método constructor por parámetro, recibe un símbolo de tipo hoja de cálculo. Por ejemplo: X7.
   * @param simbolo String conformada por una letra y un número o más.
   */
  Celda(String simbolo) {
    this.simbolo = simbolo;
    // Letra - ascii 65 que es 'A'.
    this.column = simbolo.charAt(0) - 65;
    // del segundo caracter en adelante - 1 (no contempla el 0)
    this.row = Integer.parseInt( simbolo.substring(1)) - 1;
    // System.out.println("simbol: [" + simbolo.charAt(0) + "," + simbolo.substring(1) + "]");
    // System.out.println("pos: [" + this.row + "," + this.column + "]");
  }

  /**
   * Metodo constructor por parámetros que recibe las posiciones de las celdas.
   * @param row fila a la que representa la celda.
   * @param column columna a la que representa la celda.
   */
  Celda(final int row, final int column) {
    this.column = column;
    this.row = row;
  }

  /**
   * Metodo constructor por omisión. Inicializa los valores sin sentido.
   */
  Celda() {
    this.column = -1;
    this.row = -1;
    this.simbolo = "EmptyCell";
  }
  /**
   * Metodo que retorna la fila en la que se encuentra
   * @return Retorna la fila que representa.
   */
  public int getRow() {
    return this.row;
  }

  /**
   * Metodo que retorna la columna en la que se encuentra.
   * @return Retorna la columna que representa.
   */
  public int getColumn() {
    return this.column;
  }

  /**
   * Metodo que retorna el símbolo de la celda.
   * @return Retorna el símbolo que la representa.
   */
  public String getSymbol() {
    return this.simbolo;
  }

  /**
   * Metodo toString que imprime los atributos de la celda.
   */
  public String toString() {
    return this.simbolo + " : [" + this.row + "][" + this.column + "]";
  }
  /**
   * Metodo que compara la columna de una celda con otra
   * @param other
   * @return Retorna true si son iguales, false en caso contrario
   */
  public boolean equalColumn(Celda other) {
    return other.getSymbol().charAt(0) == this.simbolo.charAt(0);
  }
}
