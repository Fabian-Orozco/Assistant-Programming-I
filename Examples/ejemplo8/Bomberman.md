# Ejemplo 8 - Bomberman

## Introducción

Bomberman es un videojuego de acción de estilo arcada creado en 1938 por Hudson Soft (actualmente parte de Konami). Originalmente, el juego fue creado para una pequeña variedad de ordenadores PC. En 1985, se publicó una versión para NES (Nintendo Entertainment System).

En la versión de NES, el personaje Bomberman, es un robot que debe encontrar la salida de un laberinto mientras esquiva enemigos. Las puertas que llevan a la salida se encuentran escondidas debajo de parades de ladrillos. Para encontra la salida, Bomberman cuenta con bombas que al estallar pueden romper estas paredes de ladrillos. Debajo de las paredes también es posible encontrar de mejoras que ayudan a incremetar el poder de la explocioń de las bombas. Además, Bomberman puede hacer uso de sus bombas para eliminar enemigos.

Una descripción más detallada de bomberman puede encontrarse a través del siguiente enlace: [Bomberman (1983 video game)](https://en.wikipedia.org/wiki/Bomberman_(1983_video_game)).

## Enunciado

Queremos verificar el estado del juego Bomberman en los momentos en que una bomba estalla.

El tablero se representa de la siguiente manera:

|Símbolo | Descripción        |
|:-------|:-------------------|
|.       |Suelo               |
|#       |Pared indestructible|
|%       |Pared de ladrillo   |
|*       |Bombas              |
|!       |Enemigos            |
|@       |Jugador             |

En la entrada estándar vamos a recibir los datos de la siguiente manera:

*Entrada Estandar:*

```txt
13 14
4 7 3
3 7 12 9 7 10 11
2 7 10 9 5

##############
#.%%%%%......#
#.#.#.#.#%#.##
#.%...%...%..#
#%#.#.#.#.#%##
#.....%......#
#%#%#%#.#.#.##
#............#
#.#.#%#.#.#.##
#.........%..#
#.#.#%#%#%#.##
#............#
##############
```

Se reporta en la salida estandar:

- Mapa actualizado.
- Enemigos vivos.
- Lista de enemigos muertos.
- Si el jugador muere.
