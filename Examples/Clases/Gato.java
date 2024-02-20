package Sample.Clases;

public class Gato {
  /**
   * Atributos
   */
  private String color;
  private int edad;
  private String nombre;
  private String raza;

  /**
   * Constructores
   */
  // diferenciación de métodos es por: cantidad y tipo de parámetros.
  // pueden tener el mismo
  public Gato(String color, int edad, String nombre, String raza) {
    this.color = color;
    this.edad = edad;
    this.nombre = nombre;
    this.raza = raza;
  }

  public Gato() {
    this.color = "";
    this.edad = 0;
    this.nombre = "";
    this.raza = "";
  }
  
  /**
   * Métodos
   */
  // --------------------- metodos set ---------------
  // cambia la edad del gato
  public void setEdad(int nuevaEdad) {
    this.edad = nuevaEdad;
  }

  // cambia el nombre del gato
  public void setNombre(String nuevoNombre) {
    this.nombre = nuevoNombre;
  }

  // --------------------- metodos get ---------------

  // obtiene el nombre del gato
  public String getNombre() {
    return this.nombre;
  }

  // obtiene la raza del gato
  public String getRaza() {
    return this.raza;
  }

  // obtiene el color del gato
  public String getColor() {
    return this.color;
  }

  // obtiene la edad del gato
  public int getEdad() {
    return this.edad;
  }

  public String toString() {
    return "El gato se llama " + getNombre() + " es de la raza " + getRaza() + ", además es de color " + getColor() + " y tiene " + getEdad() + " años";
  }

  // --------------------- metodos privados ---------------

  
}
