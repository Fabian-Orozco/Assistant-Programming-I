package Evaluaciones.TP.TP2.Desarrollo.src;

import java.math.BigDecimal;

import Evaluaciones.TP.TP2.Desarrollo.src.Fraccion;


class ListaDeFracciones {

  class Nodo {
    private Fraccion frac;
    private Nodo siguienteNodo;

    public Nodo(Fraccion fraccion, final Nodo elSiguienteNodo){
      this.frac = fraccion;
      this.siguienteNodo = elSiguienteNodo;
    }

    public Fraccion getValor(){
      return this.frac;
    }

    public void setValor(final Fraccion fraccion) {
      this.frac = fraccion;
    }

    public Nodo getSiguienteNodo() {
      return this.siguienteNodo;
    }

    public void setSiguienteNodo(Nodo nuevoSiguiente) {
      this.siguienteNodo = nuevoSiguiente;
    }

    public boolean isGreaterThan(Nodo otro) {
      return this.frac.isGreaterThan(otro.frac);
    }

    public boolean isLessThan(Nodo otro) {
      return this.frac.isLessThan(otro.frac);
    }

  }

  private Nodo primerNodo;
  private Nodo ultimoNodo;
  private int cantidadNodos;

  public ListaDeFracciones(){
    primerNodo = null;
    ultimoNodo = null;
    this.cantidadNodos = 0;
  }

  public void agregarAlFinal(Fraccion fraccion) {
    Nodo nodo = new Nodo(fraccion, null);
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

  public Fraccion obtenerValor(int index) {
    Nodo actual = this.primerNodo;
    if (actual != null) {
      for (int nodo = 0; nodo < index; nodo++) {
        actual = actual.siguienteNodo;
      }
      return actual.frac;
    }
    return new Fraccion();  // ERROR
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

  public String toString() {
    String resultado = "Lista:";
    Nodo actual = this.primerNodo;
    for (int i = 0; i < this.cantidadNodos; ++i) {
      resultado += " " + actual.frac.toString();
      actual = actual.siguienteNodo;
    }
    return resultado + "\n";
  }

  public boolean isEmpty() {
    return this.primerNodo == null;
  }

  public void ordenar() {
    if ( !this.isEmpty() ) {
      bubbleSort();
    }
  }

  /**
   * Realiza la suma de todos los elementos de la lista.
   * @return Retorna la suma de todos los elementos de la lista.
   */
  public Fraccion getSumatoria() {
    Nodo actual = this.primerNodo;
    if (actual != null) {
      Fraccion sumatoria = actual.frac;
      actual = actual.siguienteNodo;
      while (actual != null) {
        sumatoria = sumatoria.sumar(actual.frac);
        actual = actual.siguienteNodo;
      }
      return sumatoria;
    }
    return new Fraccion();  // ERROR
  }
  /**
   * Realiza la multiplicación de todos los elementos de la lista.
   * @return Retorna la multiplicación de todos los elementos de la lista.
   */
  public Fraccion getProductoria() {
    Nodo actual = this.primerNodo;
    if (actual != null) {
      Fraccion productoria = actual.frac;
      actual = actual.siguienteNodo;
      while (actual != null) {
        productoria = productoria.multiplicar(actual.frac);
        actual = actual.siguienteNodo;
      }
      return productoria;
    }
    return new Fraccion();  // ERROR
  }

  /**
   * Calcula la mediana en la lista
   * @return Retorna la mediana estadística de la lista.
   */
  public Fraccion getMediana() {
    if (!this.isEmpty()) {
      int middle = this.cantidadNodos / 2;
      Fraccion valorMedio = obtenerValor(middle);
      if (this.cantidadNodos  % 2 == 0) {  // es par

        Fraccion valorAuxiliar = obtenerValor((this.cantidadNodos - 1) / 2 );
        Fraccion dos = new Fraccion("2");
  
        return (valorMedio.sumar(valorAuxiliar)).dividir(dos);
        
      } else {  // es impar
        return valorMedio;
      }
    }
    return new Fraccion();  // ERROR
  }

  /**
   * Busca la fracción dentro de la lista y retorna la posición o -1 según conrresponda.
   * @param fraccion fraccion que se quiere buscar dentro de la lista.
   * @return Retorna el índice en el que se encuentra la fraccion, de hacerlo, sino retorna -1.
   */
  public int getIndex(final Fraccion fraccion) {
    if (!this.isEmpty()) {
      int position = 1;
      Nodo actual = this.primerNodo;
      while (actual != null && actual.frac.isDiff(fraccion)) {
        actual = actual.siguienteNodo;
        ++position;
      }
      if (actual != null && actual.frac.isEqual(fraccion)) return position;
    }
    return -1;
  }

  /**
   * Metodo que asigna un nuevo valor a un nodo de la lista.
   * @param fraccion nuevo valor que se quiere asignar
   * @param index posicion en que se encuentra el nodo al que se quiere reasignar
   */
  public void setFrac(final Fraccion fraccion, final int index) {
    Nodo actual = this.primerNodo;

    for (int pos = 1; pos < index; ++pos) {
      // avanza en la lista
      actual = actual.siguienteNodo;
    }
    // se encuentra en la posicion del índice indicado
    actual.frac = fraccion;
  }

  /**
   * Metodo que ordena los elementos de la lista de menor a mayor.
   */
  public void bubbleSort() {  
    boolean cambio = false;
    do {
      Nodo actual = this.primerNodo;
      Nodo anterior = null;
      Nodo siguiente = this.primerNodo.siguienteNodo;
      cambio = false;
      while ( siguiente != null ) {
        if ( actual.isGreaterThan(siguiente) ) {
            cambio = true;
            Nodo sig = siguiente.siguienteNodo;
            if ( anterior != null ) { // n-ésima iteración: anterior avanza
              anterior.siguienteNodo = siguiente;
            } else { // primer iteración: anterior es null => primer nodo avanza
              this.primerNodo = siguiente;
            }
            siguiente.siguienteNodo = actual;
            actual.siguienteNodo = sig;
            anterior = siguiente;
            siguiente = actual.siguienteNodo;
        } else { 
            anterior = actual;
            actual = siguiente;
            siguiente = siguiente.siguienteNodo;
        }
      }
    } while( cambio );
  }
}
