package cr.ac.ucr;

class Lista {

  class Nodo {
    private int valor;
    private Nodo siguienteNodo;

    public Nodo(int valorInicial, Nodo elSiguienteNodo){
      this.valor = valorInicial;
      this.siguienteNodo = elSiguienteNodo;
    }

    public int getValor(){
      return this.valor;
    }

    public void setValor(int nuevoValor) {
      this.valor = nuevoValor;
    }

    public Nodo getSiguienteNodo() {
      return this.siguienteNodo;
    }

    public void setSiguienteNodo(Nodo nuevoSiguiente) {
      this.siguienteNodo = nuevoSiguiente;
    }
  }

  private Nodo primerNodo;
  private Nodo ultimoNodo;
  private int cantidadNodos;

  public Lista(){
    primerNodo = null;
    ultimoNodo = null;
    this.cantidadNodos = 0;
  }

  public void agregarAlFinal(int valor) {
    Nodo nodo = new Nodo(valor, null);
    if (this.primerNodo == null) {
      this.primerNodo = nodo;
    } else {
      this.ultimoNodo.setSiguienteNodo(nodo);
    }
    this.ultimoNodo = nodo;
    this.cantidadNodos++;
  }

  public int getCantidadNodos() {
    return this.cantidadNodos;
  }

  public void remover(int posicion) {
    if (this.cantidadNodos == 1) {
      this.primerNodo = null;
      this.ultimoNodo = null;
      this.cantidadNodos--;
    } else if (this.cantidadNodos > 1) {
      if (posicion == 0) {
        this.primerNodo = this.primerNodo.getSiguienteNodo();
      } else {
        Nodo anterior = primerNodo;
        Nodo actual = primerNodo.getSiguienteNodo();
        for (int indice = 1; indice < posicion; indice++) {
          anterior = actual;
          actual = actual.getSiguienteNodo();
        }
        if (posicion + 1 == this.cantidadNodos) {
          this.ultimoNodo = anterior;
          anterior.setSiguienteNodo(null);
        } else {
          anterior.setSiguienteNodo(actual.getSiguienteNodo());
        }
      }
      this.cantidadNodos--;
    }
  }

  public void destruir(){

  }

  public int obtenerValor() {
    return  0;
  }

  public void imprimirLista() {
    imprimirListaRecursivo(this.primerNodo);
  }

  private void imprimirListaRecursivo(Nodo nodo) {
    if (nodo != null) {
      System.out.print(nodo.getValor() + " ");
      imprimirListaRecursivo(nodo.getSiguienteNodo());
    }
  }
}
