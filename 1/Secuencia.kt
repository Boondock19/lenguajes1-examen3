package estructruras.secuencias

/* 
    Clase abstracta Secuencia que implementa 3 metodos abstractos:
    - agregar (Agrega un elemento a la secuencia)
    - remover (Elimina un elemento de la secuencia)
    - vacio (Devuelve un booleano indicando si la secuencia esta vacia)
 */

abstract class Secuencia<T>() {

    abstract fun agregar(element:T): Boolean

    abstract fun remover(): Boolean

    abstract fun vacio(): Boolean
}