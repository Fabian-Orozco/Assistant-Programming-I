package Evaluaciones.TP.TP2.Desarrollo.src;

import java.math.BigDecimal;
import java.nio.file.attribute.UserDefinedFileAttributeView;
import java.util.Scanner;

/**
 * Se encarga de manejar la lógica del programa. Posee un método main y run.
 * Además, se encarga de la lectura e impresión de los datos.
 */
class Controlador {

  /**
   * Atributo de la clase. Flujo de entrada desde la entrada estándar para leer los datos necesarios.
   */
  private Scanner reader = null;
  
  /**
   * Metodo por omisión de la clase Controlador. Inicializa el objeto escáner que leerá de la entrada estándar.
   */
  Controlador(){
    this.reader = new Scanner(System.in);
  }
  
  /**
   * Método que ejecuta la lógica del programa.
   */
  private void run(){
    final int rows = reader.nextInt();
    final int columns = reader.nextInt();

    HojaDeCalculo sheet = new HojaDeCalculo(rows, columns);

    reader.nextLine(); reader.nextLine(); // flush line feed

    if (readFractions(sheet)) {
      readInstructions(sheet);
    } else {
      System.out.println("Error al leer las fracciones");
    }
  }
  /**
   * Metodo encargado de invocar la lectura de las fracciones dadas por entrada estándar.
   * @param sheet objeto donde se quiere almacenar las fracciones.
   * @return Retorna true en caso de que se complete con éxito el llenado de la matriz, en caso contrario retorna false.
   */
  private boolean readFractions(final HojaDeCalculo sheet) {
    this.reader.useDelimiter("[/,\\s]+");
    return sheet.fillMatrix(this.reader);
  }

  /**
   * Metodo que lee las instrucciones dadas por entrada estándar.
   * @param sheet objeto donde se quiere crear el conjunto.
   */
  private boolean readInstructions(final HojaDeCalculo sheet) {
    this.reader.reset();
    this.reader.useDelimiter("[>=\\n]+");
    while (reader.hasNext()) {
      String instruction = reader.next();
      operar(sheet, instruction);
      // System.out.println(reader.next());
    }
    return true;
  }
  
  private void operar(final HojaDeCalculo sheet, String instruction) {
                                                                        // CELDA
    if (instruction.substring(0, 3).equals("CEL")) {
      CEL(sheet, instruction);
    }                                                                // CONJUNTO
    else if (instruction.substring(0, 3).equals("CON")) {
      CONJUNTO(sheet, instruction);
    }                                                                    // SUMA
    else if (instruction.substring(0, 3).equals("SUM")) {
      SUMA(sheet, instruction);
    }                                                          // MULTIPLICACION
    else if (instruction.substring(0, 3).equals("MUL")) {
      MULT(sheet, instruction);
    }                                                               // PROMEDIO
    else if (instruction.substring(0, 3).equals("PRO")) {
      PROMEDIO(sheet, instruction);
    }                                                                 // MEDIANA
    else if (instruction.substring(0, 3).equals("MED")) {
      MEDIANA(sheet, instruction);
    }                                                                  // MINIMO
    else if (instruction.substring(0, 3).equals("MIN")) {
      MIN(sheet, instruction);
    }                                                                  // MAXIMO
    else if (instruction.substring(0, 3).equals("MAX")) {
      MAX(sheet, instruction);
    }                                                                // IMPRIMIR
    else if (instruction.substring(0, 3).equals("IMP")) {
      IMPRIMIR(sheet, instruction);
    }
    else {
      System.out.println("La instrucción " + instruction + " no es valida");
    }
  }

  /**
   * Metodo encargador de verificar el tipo de suma requerido e invocar al respectivo. Suma ya sea el conjunto solicitado o las celdas.
   * @param sheet objeto donde se quiere realizar la suma.
   * @param instruction instruccion con la información necesaria para realizar la suma (conjunto o celdas).
   */
  private void SUMA(final HojaDeCalculo sheet, String instruction) {
    instruction = instruction.substring(5, instruction.length() -1 );
    if (instruction.indexOf(":") != -1 ) {
      // existe => suma de celdas
      String celdas[] = instruction.split(":");
      sheet.sumOrMult(celdas[0], celdas[1], "suma");
    } else {
      // suma de conjuntos
      sheet.sumarConjunto(instruction);
    }
  }

  /**
   * Metodo encargador de verificar el tipo de multiplicacion requerido e invocar al respectivo. Multiplica ya sea el conjunto solicitado o las celdas.
   * @param sheet objeto donde se quiere realizar la multiplicacion.
   * @param instruction instruccion con la información necesaria para realizar la multiplicacion (conjunto o celdas).
  */
  private void MULT(final HojaDeCalculo sheet, String instruction) {
    instruction = instruction.substring(5, instruction.length() -1 );
    if (instruction.indexOf(":") != -1 ) {
      // existe => mult de celdas
      String celdas[] = instruction.split(":");
      sheet.sumOrMult(celdas[0], celdas[1], "mult");
    } else {
      // mult de conjuntos
      sheet.multiplicarConjunto(instruction);
    }
  }
  
  /**
   * Metodo encargador de invocar a encontrar el promedio de las fracciones en un rango en la hoja de cálculo.
   * @param sheet objeto donde se quiere buscar el promedio.
   * @param instruction instruccion con el rango de las celdas donde se buscará el promedio.
   */
  private void PROMEDIO(final HojaDeCalculo sheet, String instruction) {
    instruction = instruction.substring(9, instruction.length() -1 );
    String celdas[] = instruction.split(":");
    sheet.promOrMed(celdas[0], celdas[1], "prom");
  }

  /**
   * Metodo encargador de invocar a encontrar la mediana de las fracciones en un rango en la hoja de cálculo.
   * @param sheet objeto donde se quiere buscar la mediana.
   * @param instruction instruccion con el rango de las celdas donde se buscará el la mediana.
   */
  private void MEDIANA(final HojaDeCalculo sheet, String instruction) {
    instruction = instruction.substring(8, instruction.length() -1 );
    String celdas[] = instruction.split(":");
    sheet.promOrMed(celdas[0], celdas[1], "med");
  }


  /**
   * Metodo encargado de la instruccion CEL, que posiciona una celda como la seleccionada en la hoja de cálculo.
   * @param sheet objeto donde se quiere crear el conjunto.
   * @param instruction instruccion con la información necesaria para formar el conjunto (nombre y celdas).
   */
  private void CEL(final HojaDeCalculo sheet, String instruction) {
    sheet.posicionarCelda( instruction.substring(4, instruction.length()-1) );
    //System.out.println("celda seleccionada: " + sheet.getCeldaSeleccionada().toString());
  }
  
  /**
   * Metodo que se encarga de la operación conjunto: substrae la instrucción y la separa en cadenas para formar el nombre del conjunto y sus celdas.
   * @param sheet objeto donde se quiere crear el conjunto.
   * @param instruction instruccion con la información necesaria para formar el conjunto (nombre y celdas).
   */
  private void CONJUNTO(final HojaDeCalculo sheet, String instruction) {
    instruction = instruction.substring(9, instruction.length()-1);
    String parametros[] = instruction.split(",");
  
    for (int index = 0; index < parametros.length; ++index) {
      parametros[index] = parametros[index].trim();
    }
    sheet.crearConjunto(parametros);
  }

  /**
   * Metodo encargador de invocar a encontrar el valor minimo de las fracciones en un rango en la hoja de cálculo.
   * @param sheet objeto donde se quiere buscar el minimo.
   * @param instruction instruccion con el rango de las celdas donde se buscará el minimo.
   */
  private void MIN(final HojaDeCalculo sheet, String instruction) {
    instruction = instruction.substring(4, instruction.length()-1);
    String celdas[] = instruction.split(":");
    sheet.minOrMax(celdas[0], celdas[1], "min");
  }

  /**
   * Metodo encargador de invocar a encontrar el valor maximo de las fracciones en un rango en la hoja de cálculo.
   * @param sheet objeto donde se quiere buscar el maximo.
   * @param instruction instruccion con el rango de las celdas donde se buscará el máximo.
    */
  private void MAX(final HojaDeCalculo sheet, String instruction) {
    instruction = instruction.substring(4, instruction.length()-1);
    String celdas[] = instruction.split(":");
    sheet.minOrMax(celdas[0], celdas[1], "max");
  }
  /**
   * Metodo encargador de verificar el tipo de impresión requerido e invocar al respectivo. Imprime ya sea la tabla completa o el conjunto solicitado.
   * @param sheet objeto donde se quiere crear el conjunto.
   * @param instruction instruccion con la información necesaria para formar el conjunto (nombre y celdas).
   */
  private void IMPRIMIR(final HojaDeCalculo sheet, String instruction) {
    if (instruction.equals("IMPRIMIR()")) {
      // Imprime la hoja
      sheet.print(System.out);
    } else {
      // Imprime el conjunto
      sheet.printConjunto(instruction.substring(9, instruction.length()-1));
    }
  }

  public static void main(String[] args) {
    System.out.println("Program started");
    Controlador manager = new Controlador();
    manager.run();
    System.out.println("Finished program");
  }
} // class Controlador