package cr.ac.ucr;

class Nodo {
  private final int llave;
  private cr.ac.ucr.Nodo hijoIzq;
  private cr.ac.ucr.Nodo hijoDer;
  private long valorAsociado;

  public Nodo(final int laLlave, final long elValorAsociado,
              final Nodo elHijoIzq, final Nodo elHijoDer) {
    this.llave = laLlave;
    this.valorAsociado = elValorAsociado;
    this.hijoIzq = elHijoIzq;
    this.hijoDer = elHijoDer;
  }

  public int getLlave() {
    return this.llave;
  }

  public long getValorAsociado() {
    return this.valorAsociado;
  }

  public void setValorAsociado(long elValorAsociado) {
    this.valorAsociado = elValorAsociado;
  }

  public Nodo getHijoIzq() {
    return this.hijoIzq;
  }

  public void setHijoIzq(Nodo elHijoIzq) {
    this.hijoIzq = elHijoIzq;
  }

  public Nodo getHijoDer() {
    return this.hijoDer;
  }

  public void setHijoDer(Nodo elHijoDer) {
    this.hijoDer = elHijoDer;
  }

  public void imprimirPreOrder () {
    System.out.println(this.llave);
    if (this.hijoIzq != null) {
      this.hijoIzq.imprimirPreOrder();
    }
    if (this.hijoDer != null) {
      this.hijoDer.imprimirPreOrder();
    }
  }

  public void imprimirEnOrder () {
    if (this.hijoIzq != null) {
      this.hijoIzq.imprimirPreOrder();
    }
    System.out.println(this.llave);
    if (this.hijoDer != null) {
      this.hijoDer.imprimirPreOrder();
    }
  }
}

class ArbolBinarioBusqueda {
  private Nodo raiz;
  private int size;

  public ArbolBinarioBusqueda() {
    this.raiz = null;
    this.size = 0;
  }

  public int getSize() {
    return this.size;
  }

  private boolean arbolVacio(){
    return (this.raiz == null);
  }

  public void agregar(int llave, long valorAsociado) {
    if (this.arbolVacio()) {
      this.raiz = new Nodo(llave, valorAsociado, null, null);
      this.size++;
    } else {
      Nodo nodo = this.raiz;
      while (nodo != null) {
        if (nodo.getLlave() > llave) {
          if (nodo.getHijoIzq() == null) {
            nodo.setHijoIzq(new Nodo(llave, valorAsociado, null, null));
            this.size++;
            break;
          }
          nodo = nodo.getHijoIzq();
        } else{
          if (nodo.getHijoDer() == null) {
            nodo.setHijoDer(new Nodo(llave, valorAsociado, null, null));
            this.size++;
            break;
          }
          nodo = nodo.getHijoDer();
        }
      }
    }
  }

  public void imprimirPreOrden () {
    this.raiz.imprimirPreOrder();
  }

  public void imprimirEnOrden () {
    this.raiz.imprimirEnOrder();
  }
}
