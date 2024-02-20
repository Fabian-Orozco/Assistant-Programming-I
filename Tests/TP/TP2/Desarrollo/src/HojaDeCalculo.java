package Evaluaciones.TP.TP2.Desarrollo.src;

import java.io.PrintStream;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Scanner;

import Evaluaciones.TP.TP2.Desarrollo.src.Fraccion;

/**
 * 
 */
class HojaDeCalculo {
  private Fraccion matrix[][] = null;
  /**
   * Lleva la cuenta del número con más dígitos.
   */
  private int maxDigits = 0;

  /**
   * atributo que almacena los conjuntos (listas de fracciones)
   */
  private HashMap<String, ListaDeFracciones> conjuntos = null;

  /**
   * Atributo que representa la celda que está seleccionada actualmente.
   */
  private Celda celdaSeleccionada = null;


  private Celda beginCell = null;
  private Celda endCell = null;

  /**
   * Arreglo con las posiciones de las celdas (start/finish: row - colum)
   * // [0] startRow  [1]startCol  [2]finishRow  [3]finishCol
   */
  int [] positions = null;
  final int START_ROW = 0;
  final int START_COL = 1;
  final int FINISH_ROW = 2;
  final int FINISH_COL = 3;

  String route = null;

  /**
   * Metodo constructor por omisión. Deja la matriz en null.
   */
  public HojaDeCalculo() {
    matrix = new Fraccion[0][0];
    initSheet();
  }

  /**
   * Metodo constructor por parámetros, recibe el tamaño de las filas y columnas de la matriz.
   * @param rows tamaño de las filas de la matriz.
   * @param columns tamaño de las columnas de la matriz.
   */
  public HojaDeCalculo(final int rows, final int columns) {
    this.matrix = new Fraccion[rows][columns];
    initSheet();
  }

  /**
   * Método constructor por parámetro de una matriz cuadrada. Recibe un número que indica tanto el tamaño de las filas como el de las columnas.
   * @param size indica tanto el tamaño de las filas como el de las columnas.
   */
  public HojaDeCalculo(final int size) {
    this.matrix = new Fraccion[size][size];
    initSheet();
  }
  
  /**
   * Metodo que inicializa con 0s las entradas de la matriz.
   */
  public void initMatrix() {
    for (int row = 0; row < matrix.length; ++row) {
      for (int column = 0; column < matrix[row].length; ++column) {
        matrix[row][column] = new Fraccion();
      }
    }
  }

  /**
   * Metodo que inicializa los atributos de la clase.
   */
  public void initSheet() {
    initMatrix();
    this.conjuntos = new HashMap<String, ListaDeFracciones>();
    this.celdaSeleccionada = new Celda();
    this.positions = new int[4];
  }

  /**
   * Método encargado de rellenar la matriz con los datos leídos por el scanner enviado por parámetro.
   * @param reader Scanner que leerá los datos de la entrada estándar para almacenarlos en la matriz.
   * @return {@code true}  en caso de que la matriz se llene exitosamente, {@code false}  en caso contrario.
   */
  public boolean fillMatrix(final Scanner reader) {
    for (int row = 0; row < getRows(); ++row) {
      for (int column = 0; column < getCols(); ++column) {
        BigDecimal numerador = reader.nextBigDecimal();
        BigDecimal denominador = reader.nextBigDecimal();
        Fraccion nueva = new Fraccion(numerador, denominador);
        this.matrix[row][column] = nueva;
        checkCountDigits(nueva.getNumerador(), nueva.getDenominador());
      }
    }
    return true;
  }
  
  /**
   * Metodo que imprime por salida estándar las entradas de la matriz en forma de hoja de cálculo.
   * @param out flujo de salida para la impresión de los datos
   */
  public void print(final PrintStream out) {
    printHeader(out);
    for (int row = 0; row < matrix.length; ++row) {
      out.print("  " + (int) (row+1) + "|");
      for (int column = 0; column < matrix[row].length; ++column) {
        String format = "%" + (maxDigits + 1 ) + "s";
        out.printf(format, this.matrix[row][column].toString() + " ");
      }
      out.println();
    }
  }

  /**
   * Se encarga de imprimir el encabezado de la tabla
   * @param out
   */
  private void printHeader(final PrintStream out) {
    // primer linea
    out.print("   |");
    printSymbol(' ', maxDigits-1, out);
    for (int col = 0; col < getCols(); ++col) {
      out.print( ((char) ('A' + col)) + " ");
      printSymbol(' ', maxDigits-1, out);
    }

    // segunda linea
    out.print("\n---+");
    for (int col = 0; col < getCols(); ++col) {
      printSymbol('-', maxDigits, out);
      out.print(' ');
    }
    out.println();
  }

  private void printSymbol(final char symbol, int width, final PrintStream out) {
    while (width-- > 0) {
      out.print(symbol);
    }
  }


  /**
   * @return Retorna el tamaño de las filas de la matriz.
   */
  public int getRows() {
    return this.matrix.length;
  }

  /**
   * @return Retorna el tamaño de las columnas de la matriz.
   */
  public int getCols() {
    return this.matrix[0].length;
  }

  /**
   * Verifica si hay un nuevo numero con más dígitos que otro almacenado.
   * @param numerador
   * @param denominador
   */
  private void checkCountDigits(final BigDecimal numerador, final BigDecimal denominador) {
    // +1 por el '/'
    int qtyDigits = countDigits(numerador) + countDigits(denominador) + 1; 

    if (qtyDigits > this.maxDigits) 
      this.maxDigits = qtyDigits;
  }

  /**
	 * Returns the number of digits within number.
	 * For example, 1000 has 4 digits, 0 has 1 digit, -17 has 3 digits
	 * @return The number of digits, including negatives
   * adaptado de: https://stackoverflow.com/a/35792782
	 */
	private int countDigits(BigDecimal number)
	{
    return number.signum() == 0 ? 1 : number.precision() - number.scale();

	}

  /**
   * Método para posicionarse sobre una celda.
   * @param parametro simbolo de celda que representa a la que se quiere posicionar. Por ejemplo: "A1"
   * @return true si la celda está contenida en la matriz, false en caso contrario.
   */
  public boolean posicionarCelda(String parametro) {
    Celda celda = new Celda(parametro);
    if (celda.getRow() >= 0 && celda.getRow() <= matrix.length) {
      if (celda.getColumn() >= 0 && celda.getColumn() <= matrix[0].length) {
        this.celdaSeleccionada = celda;
        return true;
      }
    }
    return false;  // error, filas o columnas no están en la matriz.
  }

  /**
   * Metodo que retorna la celda seleccionada actualmente
   * @return
   */
  public Celda getCeldaSeleccionada() {
    return this.celdaSeleccionada;
  }

  public boolean crearConjunto(String parametros[]) {
    final String nombre = parametros[0];

    ListaDeFracciones list = new ListaDeFracciones();

    // recorre la lista de celdas a agregar a la lista de fracciones
    for (int simbCelda = 1; simbCelda < parametros.length; ++simbCelda) {
      Celda nueva = new Celda(parametros[simbCelda]);
      // System.out.println(nueva.toString()); 
      // agrega a la lista de fracciones la fracción que corresponde
      list.agregarAlFinal(matrix[nueva.getRow()][nueva.getColumn()]);

    }
    this.conjuntos.put(nombre, list);
    return true;
  }

  /**
   * Metodo que imprime el nombre del conjunto y su contenido si éste se encuentra almacenado.
   * @param key Nombre del conjunto que se quiere imprimir
   */
  public void printConjunto(String key) {
    if (this.conjuntos.containsKey(key)) {
      System.out.print(key + " -> ");
      this.conjuntos.get(key).imprimirLista();
      System.out.println();
    } else {
      System.out.println("sheet: printConj : conjunto no hallado");
    }
  }

  /**
   * Metodo que suma los valores del conjunto especificado por la llave pasada por parámetro (su nombre).
   * @param key Nombre del conjunto que se quiere sumar
   */
  public void sumarConjunto(String key) {
    if (this.conjuntos.containsKey(key)) {
      Fraccion sumatoria = this.conjuntos.get(key).getSumatoria();
      guardarResultado(sumatoria);
    } else {
      System.out.println("sheet: sumarConj : conjunto no hallado");
    }
  }

  /**
   * Metodo que suma los valores del conjunto especificado por la llave pasada por parámetro (su nombre).
   * @param key Nombre del conjunto que se quiere sumar
   */
  public void multiplicarConjunto(String key) {
    if (this.conjuntos.containsKey(key)) {
      Fraccion productoria = this.conjuntos.get(key).getProductoria();
      guardarResultado(productoria);
    } else {
      System.out.println("sheet: multConj : conjunto no hallado");
    }
  }

  /**
   * Guarda el resultado en la celda seleccionada
   * @param fraccion es la fraccion con el resultado que se quiere guardar en la celda seleccionada.
   */
  private void guardarResultado(Fraccion fraccion) {
    int row = this.celdaSeleccionada.getRow();
    int col = this.celdaSeleccionada.getColumn();

    // actualiza el valor en la listas
    for (ListaDeFracciones list : this.conjuntos.values()) {
      // busca en la lista si la celda estaba en la lista
      int index = list.getIndex(this.matrix[row][col]);
      if ( index != -1 ) {  // sí estaba
        // modifica el valor de la lista por el nuevo
        list.setFrac(fraccion, index);
      }
    }
    // actualiza la celda en la matriz
    this.matrix[row][col] = fraccion;
    checkCountDigits(fraccion.getNumerador(), fraccion.getDenominador());
  }

  private void setPositionsAndRoute(String begin, String end) {
    this.beginCell = new Celda(begin);
    this.endCell = new Celda(end);

    this.positions[0] = Math.min(this.beginCell.getRow(), this.endCell.getRow());
    this.positions[1] = Math.min(this.beginCell.getColumn(), this.endCell.getColumn());
    this.positions[2] = Math.max(this.beginCell.getRow(), this.endCell.getRow());
    this.positions[3] = Math.max(this.beginCell.getColumn(), this.endCell.getColumn());
    analizarRuta(beginCell, endCell);
  }

  public void minOrMax(String begin, String end, String operator) {
    setPositionsAndRoute(begin, end);

    Fraccion min = this.matrix[positions[START_ROW]][positions[START_COL]];
    Fraccion max = this.matrix[positions[START_ROW]][positions[START_COL]];

    // traverse
    if (route.equalsIgnoreCase("through a column")) { // by column
      // static column.
      for ( ; positions[START_ROW] <= positions[FINISH_ROW]; ++positions[START_ROW]) {
        // verify min
        if (this.matrix[positions[START_ROW]][positions[START_COL]].isLessThan(min))
          min = this.matrix[positions[START_ROW]][positions[START_COL]];
        // verify max
        if (this.matrix[positions[START_ROW]][positions[START_COL]].isGreaterThan(max))
          max = this.matrix[positions[START_ROW]][positions[START_COL]];
      }  // end for 

    } else {                                        // traverse by row
      // static row.
      for (; positions[START_COL] <= positions[FINISH_COL]; ++positions[START_COL]) {
        // verify min
        if (this.matrix[positions[START_ROW]][positions[START_COL]].isLessThan(min))
          min = this.matrix[positions[START_ROW]][positions[START_COL]];
        // verify max
        if (this.matrix[positions[START_ROW]][positions[START_COL]].isGreaterThan(max))
          max = this.matrix[positions[START_ROW]][positions[START_COL]];
      }
    }  // end else

    verifyOperator(operator, min, max);
  }

  // public void minOrMax(String begin, String end, String operator) {
  //   final Celda beginCell = new Celda(begin);
  //   final Celda endCell = new Celda(end);

  //   int startRow = Math.min(beginCell.getRow(), endCell.getRow());
  //   int startCol = Math.min(beginCell.getColumn(), endCell.getColumn());

  //   final int finishRow = Math.max(beginCell.getRow(), endCell.getRow());
  //   final int finishCol = Math.max(beginCell.getColumn(), endCell.getColumn());

  //   final String route = analizarRuta(beginCell, endCell);

  //   Fraccion min = this.matrix[startRow][startCol];
  //   Fraccion max = this.matrix[startRow][startCol];
  
  //   // traverse
  //   if (route.equalsIgnoreCase("through a column")) { // by column
  //     // static column.
  //     for ( ; startRow <= finishRow; ++startRow) {
  //       // verify min
  //       if (this.matrix[startRow][startCol].isLessThan(min))
  //         min = this.matrix[startRow][startCol];
  //       // verify max
  //       if (this.matrix[startRow][startCol].isGreaterThan(max))
  //         max = this.matrix[startRow][startCol];
  //     }  // end for 

  //   } else {                                        // traverse by row
  //     // static row.
  //     for (; startCol <= finishCol; ++startCol) {
  //       // verify min
  //       if (this.matrix[startRow][startCol].isLessThan(min))
  //         min = this.matrix[startRow][startCol];
  //       // verify max
  //       if (this.matrix[startRow][startCol].isGreaterThan(max))
  //         max = this.matrix[startRow][startCol];
  //     }
  //   }  // end else

  //   verifyOperator(operator, min, max);
  // }

  public void sumOrMult(String begin, String end, String operator) {
    final Celda beginCell = new Celda(begin);
    final Celda endCell = new Celda(end);

    int startRow = Math.min(beginCell.getRow(), endCell.getRow());
    int startCol = Math.min(beginCell.getColumn(), endCell.getColumn());

    final int finishRow = Math.max(beginCell.getRow(), endCell.getRow());
    final int finishCol = Math.max(beginCell.getColumn(), endCell.getColumn());

    final String route = analizarRuta(beginCell, endCell);

    Fraccion sumatoria = this.matrix[startRow][startCol];
    Fraccion productoria = this.matrix[startRow][startCol];
  
    // traverse
    if (route.equalsIgnoreCase("through a column")) { // by column
      // static column.
      for ( ; startRow < finishRow; ++startRow) {
          // suma y multiplica la actual con la de la siguiente fila
          sumatoria = sumatoria.sumar(this.matrix[startRow+1][startCol]);
          productoria = productoria.multiplicar(this.matrix[startRow+1][startCol]);
        }  // end for 

    } else {                                        // traverse by row
      // static row.
      for (; startCol < finishCol; ++startCol) {
        // suma y multiplica la actual con la de la siguiente fila
        sumatoria = sumatoria.sumar(this.matrix[startRow][startCol+1]);
        productoria = productoria.multiplicar(this.matrix[startRow][startCol+1]);
      }
    }  // end else

    verifyOperator(operator, sumatoria, productoria);
  }
  public void promOrMed(String begin, String end, String operator) {

    final Celda beginCell = new Celda(begin);
    final Celda endCell = new Celda(end);

    int startRow = Math.min(beginCell.getRow(), endCell.getRow());
    int startCol = Math.min(beginCell.getColumn(), endCell.getColumn());

    final int finishRow = Math.max(beginCell.getRow(), endCell.getRow());
    final int finishCol = Math.max(beginCell.getColumn(), endCell.getColumn());

    final String route = analizarRuta(beginCell, endCell);

    Fraccion promedio = this.matrix[startRow][startCol];
    Fraccion mediana = this.matrix[startRow][startCol];

    ListaDeFracciones list = new ListaDeFracciones();
  
    // traverse
    if (route.equalsIgnoreCase("through a column")) { // by column
      // static column.
      for ( ; startRow <= finishRow; ++startRow) {
          // agrega las fracciones a la lista
          list.agregarAlFinal( this.matrix[startRow][startCol] );
        }  // end for 

    } else {                                        // traverse by row
      // static row.
      for (; startCol <= finishCol; ++startCol) {
          // agrega las fracciones a la lista
          list.agregarAlFinal( this.matrix[startRow][startCol] );
      }
    }  // end else
    list.ordenar();
    promedio = list.getSumatoria().dividir(new Fraccion((String.valueOf(list.getCantidadNodos()))));
    mediana = list.getMediana();

    verifyOperator(operator, promedio, mediana);
  }

  private void verifyOperator(String operator, Fraccion min_suma_prom, Fraccion max_mult_med) {
    // la operacion es minimo, suma o promedio
    if (operator.equalsIgnoreCase("min") 
      || operator.equalsIgnoreCase("suma") 
      || operator.equalsIgnoreCase("prom")) {
      guardarResultado(min_suma_prom);

    } else {  // la operacion es maximo, multiplicacion o mediana
      guardarResultado(max_mult_med);
    }
  }


  /**
   * Metodo que analiza las celdas para definir la ruta: 
   * Ya sea recorriendo una fila o una columna
   * @param beginCell celda inical.
   * @param endCell celda final
   * @return Retorna una cadena dependiendo del recorrido.
   */
  private String analizarRuta(Celda beginCell, Celda endCell) {
    if (beginCell.equalColumn(endCell)) {
      return "through a column";
    }
    return "through a row";
  }


}  // class HojaDeCalculo