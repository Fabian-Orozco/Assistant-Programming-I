import Sample.Clases.Perro;
import Sample.Clases.Gato;

public class Controlador {
  public static void main(String[] args) {


    Perro perrito = new Perro();
    Perro perrito2 = new Perro("negro", 2, "fluffy", "french");
    
    System.out.println("primer llamado:\n" + perrito2.toString());
    perrito2.setEdad(5);
    System.out.println("segundo llamado:\n" + perrito2.toString());

    /** ejemplo 2 */
    Gato gatito1 = new Gato("blanco", 3, "ari", "siames");
    Gato gatito2 = new Gato("negro con café", 2, "kira", "egipcio");
    
    System.out.println(perrito2.getNombre() + " abrazará a " + gatito1.getNombre() + "?: " + perrito2.abrazar(gatito1));
    
    System.out.println(perrito2.getNombre() + " abrazará a " + gatito2.getNombre() + "?: " + perrito2.abrazar(gatito2));
    

    /** ejemplo 3 */
    Perro perrito3 = new Perro("negro", 2, "matón", "pitbull");

    // si un perro ladra, el otro también
    if (perrito2.estaLadrando() == true) { 
      perrito3.setLadrido(true);
    } else if (perrito3.estaLadrando()) {
      perrito2.setLadrido(true);
    }
  }
}
