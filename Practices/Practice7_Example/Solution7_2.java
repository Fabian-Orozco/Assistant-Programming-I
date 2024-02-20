import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * Inicia el proceso de analisis de loteria
 */
public class Solution7_2
{
	/**
	 * Start the execution of the solution
	 * @param args Command line arguments
	 */
	public static void main(String args[])
	{
		// Instancia el objeto controlador que analiza loteria
		AnalizadorLoteria analizadorLoteria = new AnalizadorLoteria();

		// Realiza el analisis de loteria
		analizadorLoteria.correr();
	}
}

/**
 * Obtiene frecuencias de numeros bajos y altos en sorteos de azar
 * como loteria y chances. Los datos se leen de un archivo y los
 * resultados se imprimen en un archivo
 */
class AnalizadorLoteria
{
    /**
     * Lee datos de un archivo o la entrada estandar
     */
    private Scanner input = null;

    /**
     * Imprime los resultados a este archivo
     */
    private PrintWriter output = null;

    /**
     * Arreglo con los tipos de sorteos a analizar
     */
    private TipoSorteo[] sorteos = null;

    /**
     * Realiza el proceso de analisis
     */
    public void correr()
    {
        try
        {
            // Crea el objeto que leera los datos del archivo o la entrada
            this.input = this.abrirArchivoEntrada("");

            // Crea el objeto que escribira los datos en el archivo de salida
            this.output = this.abrirArchivoSalida("");

            // Realiza el analisis de frecuencia los datos del archivo de
            // entrada y escribe los resultados en el archivo de salida
            this.analizarDatos();

            // Es importantisimo cerrar los archivos cuando ya no se usen mas
            this.input.close();
            this.output.close();
        }
        catch ( java.io.FileNotFoundException excepcion )
        {
            System.err.println( excepcion );
        }
    }

    /**
     * Abre el archivo de entrada cuyo nombre es dado por parametro
     * @param nombreArchivo el nombre del archivo a abrir, si es vacio ("") se
     * usa la entrada estandar
     */
    public Scanner abrirArchivoEntrada(String nombreArchivo)
        throws java.io.FileNotFoundException
    {
        // Si se proveyo un nombre de archivo
        if ( nombreArchivo.length() > 0 )
        {
            // Crea el objeto que leera los datos del archivo
            return new Scanner( new File(nombreArchivo) );
        }
        else
        {
            // No se proveyo un nombre de archivo, usar la entrada estandar
            return new Scanner(System.in);
        }
    }

    /**
     * Abre el archivo de salida cuyo nombre es dado por parametro
     * @param nombreArchivo el nombre del archivo a abrir, si es vacio ("") se
     * usa la salida estandar
     */
    public PrintWriter abrirArchivoSalida(String nombreArchivo)
        throws java.io.FileNotFoundException
    {
        // Si se proveyo un nombre de archivo
        if ( nombreArchivo.length() > 0 )
        {
            // Crea el objeto que escribira los datos del archivo
            return new PrintWriter( new File(nombreArchivo) );
        }
        else
        {
            // No se proveyo un nombre de archivo, usar la salida estandar
            return new PrintWriter(System.out);
        }
    }

    /**
     * Inicia el proceso de analisis de datos de azar
     */
    public void analizarDatos()
    {
        // Leer la cantidad de tipos de sorteos
        int cantidadSorteos = this.input.nextInt();

        // Al menos un sorteo es requerido para estadisticas
        if ( cantidadSorteos >= 1 )
        {
            // Crear un arreglo de tipos de sorteos
            this.sorteos = this.crearSorteos(cantidadSorteos);

            // Ignorar el resto del encabezado
            this.input.nextLine();

            // Mientras hayan registros
            while ( this.input.hasNextInt() )
            {
                // Leer el tipo de sorteo
                int tipoSorteo = this.input.nextInt();

                // Decirle al sorteo que analice sus datos
                this.sorteos[tipoSorteo - 1].leer( this.input );
            }

            // Imprimir las estadisticas
            this.imprimirEstadisticas();
        }
        else
        {
            // Reportar el error en la salida
            this.output.println("datos invalidos");
        }
    }

    /**
     * Crea un arreglo de tipos de sorteos
     * @param  cantidadSorteos La cantidad de tipos de sorteos en el arreglo
     * @return Un arreglo con los tipos de sorteos creados
     */
    public TipoSorteo[] crearSorteos(int cantidadSorteos)
    {
        // Crear el arreglo de referencias a tipos de sorteos
        TipoSorteo[] sorteos = new TipoSorteo[cantidadSorteos];

        // Crear los objetos y guardar las referencias en el arreglo
        for ( int indice = 0; indice < sorteos.length; ++indice )
        {
            sorteos[indice] = new TipoSorteo();
        }

        // Retornar la referencia al arreglo de tipos de sorteos
        return sorteos;
    }

    /**
     * Imprime una tabla con las frecuencias de numeros bajos y altos
     */
    public void imprimirEstadisticas()
    {
        // Imprimir encabezado
        this.output.println(" NOMBRE DS BAJO1 ALTO1 BAJO2 ALTO2 BAJO3 ALTO3");

        // Imprimir las frecuencias de cada tipo de sorteo
        for ( int indice = 0; indice < this.sorteos.length; ++indice )
        {
            this.sorteos[indice].imprimirFrecuencias(this.output);
        }
    }
}

/**
 * Se encarga de recopilar los datos de un tipo de sorteo (ej: chances de
 * los viernes) y llevar el conteo de los numeros bajos y altos para ese
 * sorteo. Es capaz de imprimir frecuencias de esos numeros.
 */
class TipoSorteo
{
    /**
     * Nombre del sorteo. Se llena la primera vez que se lee una linea
     * para este sorteo
     */
    private String nombre = null;

    /**
     * El dia de la semana en que ocurre este sorteo. Es una letra. Solo
     * sirve de referencia para que el usuario pueda distinguir si un
     * sorteo se efectua varias veces a la semana, como ocurre con los
     * chances que se deben distinguir entre los del martes y del viernes.
     */
    private char diaDelaSemana = '\0';

    /**
     * Cuenta la cantidad de numeros bajos que salieron favorecidos como
     * primer premio (conteoBajos[0]), segundo premio (contejoBajos[1]) y
     * tercer premio (conteoBajos[2])
     */
    private int[] conteoBajos = new int[3];

    /**
     * Cuenta la cantidad de numeros altos que salieron favorecidos como
     * primer premio (conteoAltos[0]), segundo premio (conteoAltos[1]) y
     * tercer premio (conteoAltos[2])
     */
    private int[] conteoAltos = new int[3];

    /**
     * Cuenta la cantidad total de sorteos que este tipo de sorteo tuvo
     * para poder calcular las frecuencias de numeros bajos y altos.
     */
    private int totalSorteos = 0;

    /**
     * Lee un sorteo desde el archivo de entrada que corresponde a este tipo
     * de sorteo. Actualiza los contadores para poder calcular las frecuencias
     * luego.
     * @param input El archivo desde el cual se cargaran los datos del sorteo.
     */
    public void leer(Scanner input)
    {
        // Leer los campos desde la linea en el archivo de entrada
        // No todos los campos se usaran luego
        String nombre = input.next();
        char diaDelaSemana = input.next().charAt(0);
        short diaDelMes = input.nextShort();
        short mes = input.nextShort();
        int anno = input.nextInt();
        short numero1 = input.nextShort();
        short serie1 = input.nextShort();
        short numero2 = input.nextShort();
        short serie2 = input.nextShort();
        short numero3 = input.nextShort();
        short serie3 = input.nextShort();

        // Guardar el tipo y dia de la semana si no se conoce
        if ( this.nombre == null )
        {
            this.nombre = nombre;
            this.diaDelaSemana = diaDelaSemana;
        }

        // Acumular para las estadisticas
        if ( numero1 < 50 ) ++this.conteoBajos[0]; else ++this.conteoAltos[0];
        if ( numero2 < 50 ) ++this.conteoBajos[1]; else ++this.conteoAltos[1];
        if ( numero3 < 50 ) ++this.conteoBajos[2]; else ++this.conteoAltos[2];

        // Contar un sorteo mas
        ++this.totalSorteos;
    }

    /**
     * Imprime las frecuencias en que los numeros bajos y altos salieron
     * favorecidos en los tres premios
     * @param output El archivo de salida donde se debe imprimir las estadisticas
     */
    public void imprimirFrecuencias(PrintWriter output)
    {
        // No imprimir tipos de sorteos que no tuvieron datos
        if ( this.nombre != null )
        {
            output.printf("%7s %2c %4.0f%% %4.0f%% %4.0f%% %4.0f%% %4.0f%% %4.0f%%%n"
                , this.nombre
                , this.diaDelaSemana
                , 100.0 * this.conteoBajos[0] / this.totalSorteos
                , 100.0 * this.conteoAltos[0] / this.totalSorteos
                , 100.0 * this.conteoBajos[1] / this.totalSorteos
                , 100.0 * this.conteoAltos[1] / this.totalSorteos
                , 100.0 * this.conteoBajos[2] / this.totalSorteos
                , 100.0 * this.conteoAltos[2] / this.totalSorteos );
        }
    }
}