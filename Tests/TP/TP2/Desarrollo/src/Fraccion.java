package Evaluaciones.TP.TP2.Desarrollo.src;

import java.awt.*;
import java.math.BigDecimal;

/**
 * Clase que maneja las fracciones y las operaciones que se puede realizar con
 * ellas.
 */
public class Fraccion {
  /**
   * Numerador de la fracción.
   */
  private BigDecimal numerador;
  
  /**
   * Denominador de la facción.
   */
  private BigDecimal denominador;

  // ------------------------------------------------------------------------------

  /**
   * Constructor por defecto.
   */
  public Fraccion() {
    this.numerador = new BigDecimal("0");
    this.denominador = new BigDecimal("1");
  }

  // ------------------------------------------------------------------------------

  /**
   * Constructor de número entero.
   *
   * @param pNumerador Numerador de la fracción.
   */
  public Fraccion(final String pNumerador) {
    this.numerador = new BigDecimal(pNumerador);
    this.denominador = new BigDecimal("1");
  }

  // ------------------------------------------------------------------------------

  /**
   * Constructor de la clase utilizando {@code String}.
   *
   * @param pNumerador Numerador de la fracción.
   * @param pDenominador Denominador de la fracción.
   */
  public Fraccion(final String pNumerador, final String pDenominador) {
    if (pDenominador.charAt(0) == '-') {
      this.numerador = new BigDecimal(pNumerador).multiply(new BigDecimal(-1));
      this.denominador = new BigDecimal(pDenominador).abs();
    } else {
      this.numerador = new BigDecimal(pNumerador);
      this.denominador = new BigDecimal(pDenominador);
    }
    simplificar();
  }
  // ------------------------------------------------------------------------------

  /**
   * Constructor de la clase utilizando {@code BigDecimal}.
   *
   * @param pNumerador Numerador de la fracción.
   * @param pDenominador Denominador de la fracción.
   */
  public Fraccion(final BigDecimal pNumerador, final BigDecimal pDenominador) {
    // System.out.println(pDenominador.compareTo(BigDecimal.ZERO));
    if (pDenominador.compareTo(BigDecimal.ZERO) < 0) {
      this.numerador = pNumerador.multiply(new BigDecimal(-1));
      this.denominador = pDenominador.abs();
    } else {
      this.numerador = new BigDecimal(pNumerador.toString());
      this.denominador = new BigDecimal(pDenominador.toString());
    }
    simplificar();
  }

  // ------------------------------------------------------------------------------

  /**
   * Método para acceder al valor del numerador.
   *
   * @return Retorna un {@code BigDecimal} con el valor del numerador.
   */
  public BigDecimal getNumerador() {
    return this.numerador;
  }

  // ------------------------------------------------------------------------------

  /**
   * Método para acceder al valor del denominador.
   *
   * @return Retorna un {@code BigDecimal} con el valor del denominador.
   */
  public BigDecimal getDenominador() {
    return this.denominador;
  }

  // ------------------------------------------------------------------------------

  /**
   * Representa la fracción como una cadena de caracteres.
   * @return Retorna un String con la representación de la fracción.
   */
  @Override
  public String toString() {
    return  numerador.toString() + "/" + denominador.toString();
  }

  // ------------------------------------------------------------------------------

  /**
   * Calcula el máximo común divisor (MCD) del numerador y denominador de una
   * fracción utilizando la fórmula de Euclides.
   *
   * @param pNumerador Numerador de la fracción.
   * @param pDenominador Denominador de la fracción
   * @return Retorna el MCD del numerador y denominador.
   */
  private BigDecimal maximoComunDivisor(final BigDecimal pNumerador,
                                        final BigDecimal pDenominador) {
    BigDecimal a = new BigDecimal(pNumerador.toString());
    BigDecimal b = new BigDecimal(pDenominador.toString());
    while (!b.equals(BigDecimal.ZERO)) {
      BigDecimal t = new BigDecimal(b.toString());
      b = a.remainder(b);
      a = new BigDecimal(t.toString());
    }
    return a;
  }

  /**
   * Simplifica la fracción.
   */
  private void simplificar() {
    BigDecimal mcd = maximoComunDivisor(this.numerador.abs(),
        this.denominador.abs());
    this.numerador = this.numerador.divide(mcd);
    this.denominador = this.denominador.divide(mcd);
  }

  // ------------------------------------------------------------------------------

  /**
   * Suma {@code fraction} a la fracción y retorna una nueva fracción con el
   * resultado.
   *
   * @param fraction Fracción que se va a sumar.
   * @return Retorna una {@code Fraccion} con la suma de fracciones.
   */
  public Fraccion sumar(final Fraccion fraction) {
    BigDecimal num1 = this.numerador.multiply(fraction.getDenominador());
    BigDecimal num2 = this.denominador.multiply(fraction.getNumerador());
    BigDecimal nuevoNumerador = num1.add(num2);
    BigDecimal nuevoDenominador = this.denominador.multiply(
        fraction.getDenominador());
    return new Fraccion(nuevoNumerador, nuevoDenominador);
  }

  // ------------------------------------------------------------------------------

  /**
   * Resta {@code fraction} a la fracción y retorna una nueva fracción con
   * el resultado.
   *
   * @param fraccion
   * @return Retorna una {@code Fraccion} con la resta de fracciones;
   */
  public Fraccion restar(Fraccion fraccion) {
    Fraccion nueva = new Fraccion();
    nueva.numerador = this.numerador.multiply( fraccion.getDenominador() )
    .subtract( this.denominador.multiply(fraccion.getNumerador()) );
    nueva.denominador = this.denominador.multiply(fraccion.denominador);
    nueva.simplificar();
    return nueva;
  }

    // ------------------------------------------------------------------------------

  /**
   * Multiplica {@code fraction} a la fracción y retorna una nueva fracción con
   * el resultado.
   *
   * @param fraction Fracción que se va a multiplicar.
   * @return Retorna una {@code Fraccion} con la multiplicación de fracciones.
   */
  public Fraccion multiplicar(final Fraccion fraction) {
    return new Fraccion( this.numerador.multiply( fraction.getNumerador() )
      , this.denominador.multiply( fraction.getDenominador() ) );
  }

  // ------------------------------------------------------------------------------

  /**
   * Divide {@code fraction} a la fracción y retorna una nueva fracción con
   * el resultado.
   *
   * @param fraction Fracción que se va a dividir.
   * @return Retorna una {@code Fraccion} con la división de fracciones.
   */
  public Fraccion dividir(final Fraccion fraction) {
    return new Fraccion( this.numerador.multiply( fraction.getDenominador() )
      , this.denominador.multiply( fraction.getNumerador() ) );
  }

  // ------------------------------------------------------------------------------
  
  /**
   * Realiza la comparación entre {@code fraction} y la fracción, retorna un booleano dependiendo del resultado.
   *
   * @param fraction Fracción con la que se va a comparar.
   * @return Retorna un {@code boolean} con el resultado de la comparación:
   * true si {@code Fraccion} es menor que {@code fraccion}, false en caso contrario.
   */
  public boolean isLessThan(final Fraccion fraction) {
    BigDecimal num1 = this.numerador.multiply(fraction.getDenominador());  // fracc 1
    BigDecimal num2 = this.denominador.multiply(fraction.getNumerador());  // fracc 2

    return (num1.compareTo(num2) < 0) ? true : false;
  }

  // ------------------------------------------------------------------------------
  /**
   * Realiza la comparación entre {@code fraction} y la fracción, retorna un booleano dependiendo del resultado.
   *
   * @param fraccion Fracción con la que se va a comparar.
   * @return Retorna un {@code boolean} con el resultado de la comparación:
   * true si {@code Fraccion} es menor que {@code fraccion}, false en caso contrario.
   */
  public boolean isGreaterThan(final Fraccion fraccion) {
    BigDecimal num1 = this.numerador.multiply(fraccion.getDenominador());  // fracc 1
    BigDecimal num2 = this.denominador.multiply(fraccion.getNumerador());  // fracc 2

    return (num1.compareTo(num2) > 0) ? true : false;
  }

  // ------------------------------------------------------------------------------

  /**
   * Realiza la comparación entre {@code fraction} y la fracción, retorna un booleano dependiendo del resultado.
   *
   * @param fraccion Fracción con la que se va a comparar.
   * @return Retorna un {@code boolean} con el resultado de la comparación:
   * true si {@code Fraccion} es igual que {@code fraccion}, false en caso contrario.
   */
  public boolean isEqual(final Fraccion fraccion) {
    return (this.numerador.equals(fraccion.numerador) 
      && this.denominador.equals(fraccion.denominador));
  }
  // ------------------------------------------------------------------------------

    /**
   * Realiza la comparación entre {@code fraction} y la fracción, retorna un booleano dependiendo del resultado.
   *
   * @param fraccion Fracción con la que se va a comparar.
   * @return Retorna un {@code boolean} con el resultado de la comparación:
   * true si {@code Fraccion} es diferente que {@code fraccion}, false en caso contrario.
   */
  public boolean isDiff(final Fraccion fraccion) {
    return !isEqual(fraccion);
  }
}
