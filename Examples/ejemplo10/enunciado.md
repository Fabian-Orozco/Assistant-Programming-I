# Calculadora fraccional

Casi todo el mundo tiene una calculadora, hasta en su celular. Pero no todos tienen una calculadora fraccional, y pocos una con precisión arbitraria... Se necesita una calculadora fraccional estándar de precisión arbitraria que permita calcular las cuatro operaciones básicas: suma (`+`), resta (`-`), multiplicación (`*`), y división (`/`) de fracciones. En esta versión no es necesaria la prioridad de operaciones.

En matemática, una fracción es un número representado de la forma `a/b`, donde `a`, `b` son enteros y `b` nunca es cero. Conceptualmente, `a` recibe el nombre de `numerador` y `b` el de `denominador`.

Su calculadora debe ser interactiva. Debe leer las fracciones y operaciones de la entrada estándar e imprimir los resultados en la salida estándar, siempre simplificados. Cuando la calculadora está lista para leer una fracción muestra el indicador `fr> ` para avisar al usuario de que espera una fracción. Cuando está lista para leer un operador, mostrará el indicador `op> `.

```sh
./frcalc
fr> 12/-8
op> =
-3/2
```

En el ejemplo anterior se introdujo la fracción `12 / -8` y el operador `=`, que muestra el último resultado de una operación. En este caso, la fracción se imprime, y como puede verse, la calculadora la imprime en forma simplificada `-3/2`. Nótese además que la calculadora ajustó los negativos, dado que por convención, no se escriben denominadores negativos en las fracciones.

La calculadora debe realizar las cuatro operaciones básicas. Si el usuario ingresa uno de los operadores cuando se presenta el indicador `op> `, la calculadora esperará por otra fracción. Una vez ingresada, se presenta el resultado de la operación. Por ejemplo:


```sh
./frcalc
fr> 12/8
op> +
fr> 1/4
-3/2
```

Ingrese fracción 1 en formato a b: 12 8
Ingrese fracción 2 en formato a b: 1 4

3/2 + 1/4 = 7/4


Para simplificar la fracción, implemente el [algoritmo de Euclides](http://es.wikipedia.org/wiki/Algoritmo_de_Euclides#Simplificar_fracciones), el cual consiste en encontrar el máximo común divisor (`mcd`) entre el numerador y denominador, y si este es diferente de cero, se divide tanto el numerador como el denominador por el `mcd` encontrado. Para calcular el máximo común divisor, implemente un método estático que recibe dos enteros `a` y `b` por parámetro, y retorna el máximo común divisor entre
ellos ó 0 si no hay, utilizando el algoritmo de Euclides, el cual se resume en el siguiente pseudocódigo:

```java
función maximoComunDivisor(a, b):
    mientras ( b != 0 )
        t = b
        b = a modulo b
        a = t

    El resultado es a
```