package Sample.Clases;

public class Perro {
  /**
   * Atributos
   */
  private String color;
  private int edad;
  private String nombre;
  private String raza;

  private boolean estaLadrando;

  /**
   * Constructores
   */
  // diferenciación de métodos es por: cantidad y tipo de parámetros.
  // pueden tener el mismo
  public Perro(String color, int edad, String nombre, String raza) {
    this.color = color;
    this.edad = edad;
    this.nombre = nombre;
    this.raza = raza;
    
    this.estaLadrando = false;
  }

  public Perro() {
    this.color = "";
    this.edad = 0;
    this.nombre = "";
    this.raza = "";
    this.estaLadrando = false;

  }
  
  /**
   * Métodos
   */
  // --------------------- metodos set ---------------
  // cambia la edad del perro
  public void setEdad(int nuevaEdad) {
    this.edad = nuevaEdad;
  }

  // cambia el nombre del perro
  public void setNombre(String nuevoNombre) {
    this.nombre = nuevoNombre;
  }

  // cambia el estado del ladrido del perro
  public void setLadrido(boolean estaLadrando) {
    this.estaLadrando = estaLadrando;

  }
  // --------------------- metodos get ---------------

  // obtiene el nombre del perro
  public String getNombre() {
    return this.nombre;
  }

  // obtiene la raza del perro
  public String getRaza() {
    return this.raza;
  }

  // obtiene el color del perro
  public String getColor() {
    return this.color;
  }

  // obtiene la edad del perro
  public int getEdad() {
    return this.edad;
  }  

  // obtiene el estado del ladrido del perro
  public boolean estaLadrando() {
    return this.estaLadrando;
  }

  public String toString() {
    return "El perro se llama " + getNombre() + " es de la raza " + getRaza() + ", además es de color " + getColor() + " y tiene " + getEdad() + " años";
  }

  // --------------------- otros métodos ---------------

  // el perro abraza al gato si es blanco y sino le ladra
  public boolean abrazar(Gato gato) {
    if (gato.getColor().equalsIgnoreCase("blanco")) {
      return true;
    }
    this.setLadrido(true);
    System.out.println(this.ladrar());
    return false;
  }

  // --------------------- metodos privados ---------------
  
  // retorna una hilera con el ladrido
  private String ladrar() {
    return " {{ Guau guau";
  }

}
